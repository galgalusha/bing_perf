package com.kenshoo.mini.ks;

import com.google.common.base.Throwables;
import com.microsoft.bingads.ApiEnvironment;
import com.microsoft.bingads.AuthorizationData;
import com.microsoft.bingads.OAuthTokenRequestException;
import com.microsoft.bingads.OAuthWebAuthCodeGrant;

import javax.annotation.PostConstruct;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: royh
 * Date: 05/03/14
 * Time: 14:36
 */
public class BingSetStubHeaders {

    public static final String LIVE_CLIENT_ID_PARAM = "CLIENT_ID_BING";
    public static final String LIVE_CLIENT_SECRET_PARAM = "CLIENT_SECRET_BING";
    public static final String CLIENT_ID_PARAM = "CLIENT_ID_MICROSOFT";
    public static final String CLIENT_SECRET_PARAM = "CLIENT_SECRET_MICROSOFT";
    public static final String REDIRECT_URI_PARAM = "OAUTH_REDIRECT_URI_BING";
    static final String REDIRECT_URI = "https://oauth-redirect.kenshoo.com/v1/channel/oauth-redirect/code";
    String clientId;
    String clientSecret;
    String redirectUri = "https://oauth-redirect.kenshoo.com/v1/channel/oauth-redirect/code";
    String liveClientId;
    String liveClientSecret;

    @PostConstruct
    void init() {
//        liveClientId = globalParameterService.readStringByName(LIVE_CLIENT_ID_PARAM);
//        liveClientSecret = globalParameterService.readStringByName(LIVE_CLIENT_SECRET_PARAM);
//        clientId = globalParameterService.readStringByName(CLIENT_ID_PARAM);
//        clientSecret = globalParameterService.readStringByName(CLIENT_SECRET_PARAM);
//        redirectUri = globalParameterService.readStringByName(REDIRECT_URI_PARAM, REDIRECT_URI);

        liveClientId = "1";
        liveClientSecret = "1";
        clientId = "1";
        clientSecret = "1";
        redirectUri = REDIRECT_URI;
    }

    public AuthorizationData createClientAuthorizationData(final BingCredentials bingCredentials) {
        return isAccountAuthenticationMigrated(bingCredentials.getChannelAccountId())?
                createAuthorizationData(bingCredentials):
                createLiveAuthorizationData(bingCredentials);
    }

    public AuthorizationData createAuthorizationDataWithNewRefreshToken(final BingCredentials bingCredentials) {
        return createClientAuthorizationData(bingCredentials);
    }

    private AuthorizationData createAuthorizationData(final BingCredentials bingCredentials) {
        System.out.println(String.format("Create Bing Service via microsoft end point for account {} and refresh token {}", bingCredentials.getChannelAccountId(), bingCredentials.getRefreshToken()));
        AuthorizationData authorizationData = createAuthentication(bingCredentials);
        if(!isOAuth(authorizationData)){
            authorizationData = createLiveAuthentication(bingCredentials);
            System.out.println(String.format("Retry to create Bing Service via live end point for account {}", bingCredentials.getChannelAccountId()));
            if(isOAuth(authorizationData)){
//                bingAuthenticationMigrationAccounts.markAccountAsUnmigrated(bingCredentials.getChannelAccountId());
            }
        }
        return authorizationData;
    }

    private AuthorizationData createLiveAuthorizationData(final BingCredentials bingCredentials) {
        System.out.println(String.format("Create Bing Service via live end point for account {}", bingCredentials.getChannelAccountId()));
        AuthorizationData authorizationData = createLiveAuthentication(bingCredentials);
        if(!isOAuth(authorizationData)){
            authorizationData = createAuthentication(bingCredentials);
            System.out.println(String.format("Retry to create Bing Service via microsoft end point for account {}", bingCredentials.getChannelAccountId()));
            if(isOAuth(authorizationData)){
//                bingAuthenticationMigrationAccounts.markAccountAsMigrated(bingCredentials.getChannelAccountId());
            }
        }
        return authorizationData;
    }

    protected AuthorizationData createLiveAuthentication(final BingCredentials bingCredentials) {
        try {
            OAuthWebAuthCodeGrant authentication =  new OAuthWebAuthCodeGrant(liveClientId, liveClientSecret, new URL(redirectUri), bingCredentials.getRefreshToken(), ApiEnvironment.PRODUCTION, true);
            return createAuthorizationData(bingCredentials, authentication);
        }catch (MalformedURLException e){
            throw Throwables.propagate(e);
        }

    }

    protected AuthorizationData createAuthentication(final BingCredentials bingCredentials) {
        try{
            OAuthWebAuthCodeGrant authentication = new OAuthWebAuthCodeGrant(clientId, clientSecret, new URL(redirectUri), bingCredentials.getRefreshToken());
            return createAuthorizationData(bingCredentials, authentication);
        }catch (MalformedURLException e){
            throw Throwables.propagate(e);
        }
    }

    private AuthorizationData createAuthorizationData(final BingCredentials bingCredentials, final OAuthWebAuthCodeGrant authentication) {
        AuthorizationData authorizationData = new AuthorizationData();
        authorizationData.setAuthentication(authentication);
        authorizationData.setCustomerId((Long.parseLong(bingCredentials.getMsnCustomerId())));
        authorizationData.setDeveloperToken(bingCredentials.getDevToken());
        if(bingCredentials.getChannelAccountIdInTarget() != null){
            authorizationData.setAccountId(Long.parseLong(bingCredentials.getChannelAccountIdInTarget()));
        }
        return authorizationData;
    }

    protected boolean isOAuth(AuthorizationData authorizationData){
        try {
            ((OAuthWebAuthCodeGrant) authorizationData.getAuthentication()).refreshTokensIfNeeded(false);
            return true;
        } catch (OAuthTokenRequestException e) {
            return false;
        }
    }

    private boolean isAccountAuthenticationMigrated(final int channelAccountId) {
//        return bingAuthenticationMigrationAccounts.isAccountAuthenticationMigrated(channelAccountId);
        return false;
    }
}