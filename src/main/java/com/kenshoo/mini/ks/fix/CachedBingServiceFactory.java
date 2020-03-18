package com.kenshoo.mini.ks.fix;

import com.microsoft.bingads.ApiEnvironment;
import com.microsoft.bingads.internal.ServiceFactory;
import com.microsoft.bingads.internal.ServiceFactoryFactory;
import com.microsoft.bingads.internal.ServiceFactoryImpl;
import com.sun.xml.ws.Closeable;
import com.sun.xml.ws.binding.BindingImpl;
import com.sun.xml.ws.client.sei.SEIStub;
import com.sun.xml.ws.developer.WSBindingProvider;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceFeature;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class CachedBingServiceFactory implements ServiceFactory {

    private final ServiceFactory theRealBingFactory = new ServiceFactoryImpl();
    private final Map<Class, Service> cachedWebServices = new HashMap<>();
    private final Map<Class, SEIStub> cachedStubs = new HashMap<>();

    public static void configure() {
        CachedBingServiceFactory theInstance = new CachedBingServiceFactory();
        ServiceFactoryFactory.setCustomServiceFactorySupplier(() -> theInstance);
    }

    @Override
    synchronized
    public Service createService(Class clazz, ApiEnvironment env) {
        if (!cachedWebServices.containsKey(clazz)) {
            cachedWebServices.put(clazz, theRealBingFactory.createService(clazz, env));
        }
        return AuthHeadersCaptor();
    }

    private Service AuthHeadersCaptor() {
        return Service.create(QName.valueOf(""));
    }

    @Override
    public <T> T createProxyFromService(Service capturedAuthHeaders, ApiEnvironment env, Class<T> clazz) {

        SEIStub stub = cachedStubs.computeIfAbsent(clazz, __ -> {
            Service webService = cachedWebServices.get(clazz);
            return (SEIStub) Proxy.getInvocationHandler(theRealBingFactory.createProxyFromService(webService, env, clazz));
        });

        BindingImpl newBinding = ((com.sun.xml.ws.client.PortInfo) stub.getPortInfo()).createBinding(new WebServiceFeature[0], clazz);

        newBinding.setHandlerChain(capturedAuthHeaders.getHandlerResolver().getHandlerChain(stub.getPortInfo()));

        SEIStub newStub = new SEIStub(stub.getPortInfo(), newBinding, stub.seiModel, stub.getWSEndpointReference());

        T proxy = (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz, WSBindingProvider.class, Closeable.class}, newStub);

        return proxy;
    }


}
