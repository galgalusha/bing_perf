package com.kenshoo.mini.ks;

/**
 * Created by IntelliJ IDEA.
 * User: netanelt
 * Date: 1/11/12
 * Time: 7:34 AM
 */
public enum ChannelType {
    ALL(-1, "All"),
    INVALID(0, "Invalid"),
    GOOGLE(1, "Google"),
    AOL(1, "AOL"),
    MSN(2, "MSN"),
    YAHOO(3, "Yahoo"), //READ ONLY SINCE JAN 2014 AND DEATH IN July 2014
    UNIVERSAL(5, "Universal"),
    YANDEX(6, "Yandex"),
    BAIDU(7, "Baidu"),
    YAHOO_JAPAN(8, "YahooJapan"),
    @Deprecated
    SOCIAL2(10, "Social2"),
    @Deprecated CITYGRID(11, "CityGrid"),
    YAHOO_GEMINI(12, "YahooGemini"),
    @Deprecated APNXS(13, "KenshooDisplay"),
    PINTEREST(14,"Pinterest"),
    SNAPCHAT(15,"Snapchat"),
    AMAZON(16, "Amazon"),
    FB(17,"FB"),
    TWITTER(18, "Twitter"),
    WALMART(19, "Walmart");

    private int id;
    private String name;

    ChannelType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static ChannelType parseId(int id) {
        for (ChannelType t : values()) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    public static ChannelType parseName(String name) {
        for (ChannelType t : values()) {
            if (t.getName().equalsIgnoreCase(name)) {
                return t;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return getName();
    }
}
