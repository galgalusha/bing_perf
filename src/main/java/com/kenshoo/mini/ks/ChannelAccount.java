package com.kenshoo.mini.ks;

import java.sql.Timestamp;
import java.util.function.Supplier;

/**
 * Created by IntelliJ IDEA.
 * User: netanelt
 * Date: 1/11/12
 * Time: 7:29 AM
 */
public class ChannelAccount {
    private int id;
    private String name;
    private int containerId; // profile id
    private ChannelType channelType;
    private String agencyChannelDisplayName;
    private String agencyChannelName;
    private int agencyChannelId;
    private String accountIdentifier;
    private String username;
    private String password;
    private String masterAccountId;
    private String masterAccountUsername;
    private String masterAccountPassword;
    private String customerId;
    private Float minBid;
    private String idInTarget;
    private String devToken;
    private String channelUniqueIdentifier;
    private String msnCustomerId;
    private String msnCustomerName;
    private String refreshToken;
    private boolean active;
    private String userIdInTarget;
    private int clickDataSource;
    private String timezone;
    private String currencyCode;
    private String marketPlace;
    private String brandName;
    private Timestamp lastUpdated;
    private boolean isAuthorized;
    private String channelStatus;
    private String authorizationStatus;
    private Supplier<String> masterAccountCustomerIdSupplier = () -> null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getContainerId() {
        return containerId;
    }

    public void setContainerId(int containerId) {
        this.containerId = containerId;
    }

    public ChannelType getChannelType() {
        return channelType;
    }

    public String getDisplayChannelType() {
        return channelType.toString();
    }

    public void setChannelType(ChannelType channelType) {
        this.channelType = channelType;
    }

    public String getAgencyChannelDisplayName() {
        return agencyChannelDisplayName;
    }

    public void setAgencyChannelDisplayName(String agencyChannelDisplayName) {
        this.agencyChannelDisplayName = agencyChannelDisplayName;
    }

    public int getAgencyChannelId() {
        return agencyChannelId;
    }

    public void setAgencyChannelId(int agencyChannelId) {
        this.agencyChannelId = agencyChannelId;
    }

    public String getAccountIdentifier() {
        return accountIdentifier;
    }

    public void setAccountIdentifier(String accountIdentifier) {
        this.accountIdentifier = accountIdentifier;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMasterAccountId() {
        return masterAccountId;
    }

    public void setMasterAccountId(String masterAccountId) {
        this.masterAccountId = masterAccountId;
    }

    public String getMasterAccountUsername() {
        return masterAccountUsername;
    }

    public void setMasterAccountUsername(String masterAccountUsername) {
        this.masterAccountUsername = masterAccountUsername;
    }

    public String getMasterAccountPassword() {
        return masterAccountPassword;
    }

    public void setMasterAccountPassword(String masterAccountPassword) {
        this.masterAccountPassword = masterAccountPassword;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setMinBid(Float minBid) {
        this.minBid = minBid;
    }

    public Float getMinBid() {
        return minBid;
    }

    public String getIdInTarget() {
        return idInTarget;
    }

    public void setIdInTarget(String idInTarget) {
        this.idInTarget = idInTarget;
    }

    public String getDevToken() {
        return devToken;
    }

    public void setDevToken(String devToken) {
        this.devToken = devToken;
    }

    public String getChannelUniqueIdentifier() {
        return channelUniqueIdentifier;
    }

    public void setChannelUniqueIdentifier(String channelUniqueIdentifier) {
        this.channelUniqueIdentifier = channelUniqueIdentifier;
    }

    public String getMsnCustomerId() {
        return msnCustomerId;
    }

    public void setMsnCustomerId(String msnCustomerId) {
        this.msnCustomerId = msnCustomerId;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUserIdInTarget() {
        return userIdInTarget;
    }

    public void setUserIdInTarget(String userIdInTarget) {
        this.userIdInTarget = userIdInTarget;
    }

    public String getAgencyChannelName() {
        return agencyChannelName;
    }

    public void setAgencyChannelName(String agencyChannelName) {
        this.agencyChannelName = agencyChannelName;
    }

    public int getClickDataSource() {
        return clickDataSource;
    }

    public void setClickDataSource(int clickDataSource) {
        this.clickDataSource = clickDataSource;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getMarketPlace() {
        return marketPlace;
    }

    public void setMarketPlace(String marketPlace) {
        this.marketPlace = marketPlace;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getMsnCustomerName() {
        return msnCustomerName;
    }

    public void setMsnCustomerName(String msnCustomerName) {
        this.msnCustomerName = msnCustomerName;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public void setAuthorized(boolean authorized) {
        isAuthorized = authorized;
    }

    public String getChannelStatus() {
        return channelStatus;
    }

    public void setChannelStatus(String channelStatus) {
        this.channelStatus = channelStatus;
    }

    public String getAuthorizationStatus() {
        return authorizationStatus;
    }

    public void setAuthorizationStatus(String authorizationStatus) {
        this.authorizationStatus = authorizationStatus;
    }

    public String getMasterAccountCustomerId() {
        return masterAccountCustomerIdSupplier.get();
    }

    public void setMasterAccountCustomerIdSupplier(Supplier<String> masterAccountCustomerIdSupplier) {
        this.masterAccountCustomerIdSupplier = masterAccountCustomerIdSupplier;
    }
}
