package org.rockey.wechat.mp.sdk.vo;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author RockeyHoo
 */
public enum BizRequest {
    SEND_BIZ_MSG("http://42.121.16.232:9003/agencyapi/wechat/sendMsg.service");

    private String url;

    private BizRequest(String url) {
        this.url = url;
    }

    public String getName() {
        return StringUtils.lowerCase(StringUtils.replace(name(), "_", " "));
    }

    public String getUrl() {
        return url;
    }

}
