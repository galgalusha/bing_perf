package com.kenshoo.mini.ks;

import com.google.common.base.Throwables;
import com.kenshoo.mini.ks.fix.CachedBingServiceFactory;
import com.microsoft.bingads.ApiEnvironment;
import com.microsoft.bingads.AuthorizationData;
import com.microsoft.bingads.OAuthWebAuthCodeGrant;
import com.microsoft.bingads.ServiceClient;
import com.microsoft.bingads.internal.ServiceFactoryFactory;
import com.microsoft.bingads.v12.reporting.IReportingService;
import com.microsoft.bingads.v13.customermanagement.GetAccountsInfoRequest;
import com.microsoft.bingads.v13.customermanagement.GetAccountsInfoResponse;
import com.microsoft.bingads.v13.customermanagement.ICustomerManagementService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;

public class App {
    static ChannelAccount channelAccount = newChannelAccount();

    public static void main(String[] args) throws Exception {

        CachedBingServiceFactory.configure();


        GetAccountsInfoRequest req = new GetAccountsInfoRequest();
        req.setCustomerId(Long.parseLong(newChannelAccount().getCustomerId()));

        Instant before = Instant.now();
        for (int i=0; i<20; i++) {
            ICustomerManagementService srv = createReal(ICustomerManagementService.class);
//            GetAccountsInfoResponse response = srv.getAccountsInfo(req);
//            System.out.println(response.getAccountsInfo().getAccountInfos().size());
        }
        System.out.println("duration: " + Duration.between(before, Instant.now()).toMillis() + " millis");
    }

    public static String sendToServlet() {
        Instant before = Instant.now();
        int i;
        for (i=0; i<50; i++) {
            createReal(IReportingService.class);
        }
        return "duration of " + i + " bing clients creation: " + Duration.between(before, Instant.now()).toMillis() + " millis";
    }

    public String getJarForClass(String className) {
        try {
            Class<?> klass = Class.forName(className);
            return klass.getProtectionDomain().getCodeSource().getLocation().toString();
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    private static <T> T createReal(Class<T> serviceInterface) {
        System.out.println(String.format("Create Bing Service %s for account %s Started", serviceInterface.getSimpleName(), 1));
//        try (Timer.Context time = metrics.timer("createService.Timer." + serviceInterface.getSimpleName()).time()) {
        try {
//            apiCallService.get(() -> new ServiceClient<>(setHeader(channelAccount), serviceInterface).getService(), channelAccount);
            T srv = new ServiceClient<>(setHeader(channelAccount), serviceInterface).getService();
            System.out.println(String.format("Create Bing Service %s for account %s Ended with succeed", serviceInterface.getSimpleName(), 1));
            return srv;
        } catch (Exception e) {
//            bingSyncMetricsService.sendAlarmMetric("createService." + serviceInterface.getSimpleName() + "." + e.getClass().getSimpleName());
            System.out.println(String.format("Create Bing Service %s for account %s Ended with error message %s", serviceInterface.getSimpleName(), 1, e.getMessage()));
            throw new RuntimeException(e);
        }
    }

    private static AuthorizationData setHeader(ChannelAccount channelAccount) throws MalformedURLException {
        AuthorizationData authorizationData = new AuthorizationData();

        authorizationData.setDeveloperToken(channelAccount.getDevToken());
        authorizationData.setAuthentication(new OAuthWebAuthCodeGrant("000000004411C0A0", "eZGXLuYzm9P6UV1vE9yhY-2NE4yc7d3E", new URL("https://oauth-redirect.kenshoo.com/v1/channel/oauth-redirect/code"), channelAccount.getRefreshToken(), ApiEnvironment.PRODUCTION, true));
        authorizationData.setCustomerId(Long.parseLong(channelAccount.getMsnCustomerId()));
        authorizationData.setAccountId(Long.parseLong(channelAccount.getIdInTarget()));

        return authorizationData;
    }

    private static ChannelAccount newChannelAccount() {
        ChannelAccount channelAccount = new ChannelAccount();

        channelAccount.setId(1);
        channelAccount.setIdInTarget(String.valueOf(1588676));
        channelAccount.setUsername("savethechoopchick@hotmail.com");
        channelAccount.setPassword("Qwe123!!");
        channelAccount.setDevToken("QOTKGA69");
        channelAccount.setRefreshToken("MCW!*Tgow6UAMAgnfiyHi4v3uVxd5nanqUlF9znc1BBbfK6l7FhmT2m!fTh5XGjg0LtOXCMM1PpBKiqCltH99w9q9kpQFqs0JTzs!icjjcCMXUX12SHkQfGJhiKaQ0VXdiJw1DF3FBAkvsksw3kzR7CFpQPxf!giPSPeds!IeZW*Kgijqy!93WEMfB7PbPzaK3ROlinKqNEhoiCnGTIUyH2psZJtGDpIRJc9D8iNbkbYV24oBorCFAQaTUue6BjUxont4hhg4Y!4oWwa2wU1n7rufAn6kAJlO4AQ9TKHLfKAY6eUIdbiS8L85UMBOdWvtOdFVxANcdLnZlDAyBe77DYMpjrLyY2ZzC77RtjIFHnuc3LxzyIP8Cn0IG7M1fpP7Qg$$");
        channelAccount.setMsnCustomerId("11060362");
        channelAccount.setCustomerId("11060362");

        return channelAccount;
    }
}
