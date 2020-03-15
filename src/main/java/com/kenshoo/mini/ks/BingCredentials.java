package com.kenshoo.mini.ks;

public class BingCredentials {
    private int channelAccountId;
    private String channelAccountIdInTarget;
    private String msnCustomerId;
    private String refreshToken;
    private String devToken;
    private int agencyId;

    public int getChannelAccountId() {
        return channelAccountId;
    }

    public String getChannelAccountIdInTarget() {
        return channelAccountIdInTarget;
    }

    public String getMsnCustomerId() {
        return msnCustomerId;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getDevToken() {
        return devToken;
    }

    public int getAgencyId() {
        return agencyId;
    }

    public BingCredentials(BingCredentialsBuilder builder) {
        this.channelAccountId = builder.channelAccountId;
        this.channelAccountIdInTarget = builder.channelAccountIdInTarget;
        this.msnCustomerId = builder.msnCustomerId;
        this.refreshToken = builder.refreshToken;
        this.devToken = builder.devToken;
        this.agencyId = builder.agencyId;
    }

    public static class BingCredentialsBuilder {
        private int channelAccountId;
        private String channelAccountIdInTarget;
        private String msnCustomerId;
        private String refreshToken;
        private String devToken;
        private int agencyId;

        public BingCredentialsBuilder withChannelAccountId(int channelAccountId) {
            this.channelAccountId = channelAccountId;
            return this;
        }

        public BingCredentialsBuilder withChannelAccountIdInTarget(String channelAccountIdInTarget) {
            this.channelAccountIdInTarget = channelAccountIdInTarget;
            return this;
        }

        public BingCredentialsBuilder withMsnCustomerId(String msnCustomerId) {
            this.msnCustomerId = msnCustomerId;
            return this;
        }

        public BingCredentialsBuilder withRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public BingCredentialsBuilder withDevToken(String devToken) {
            this.devToken = devToken;
            return this;
        }

        public BingCredentialsBuilder withAgencyId(int agencyId) {
            this.agencyId = agencyId;
            return this;
        }

        public BingCredentials build() {
            return new BingCredentials(this);
        }
    }
}
