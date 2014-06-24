package org.usc.wechat.mp.sdk.util.biz;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.usc.wechat.mp.sdk.util.HttpUtil;
import org.usc.wechat.mp.sdk.vo.BizRequest;
import org.usc.wechat.mp.sdk.vo.WechatRequest;
import org.usc.wechat.mp.sdk.vo.biz.BizJsonRtn;
import org.usc.wechat.mp.sdk.vo.token.License;

import com.google.common.collect.ImmutableMap;

/**
 *
 * @author Shunli
 */
public class BizUtil {
	/**
	 * 获取业务信息
	 * @param license
	 * @param openId
	 * 			普通用户的标识，对当前公众号唯一 
	 * @return
	 */
    public static BizJsonRtn getBizInfo(License license, String openId, String message) {
        if (StringUtils.isEmpty(openId)) {
            return null;
        }
        if (StringUtils.isEmpty(message)) {
            return null;
        }
        Map<String, String> paramMap = ImmutableMap.of("openid", openId, "msg", message);
        return HttpUtil.getRequest(BizRequest.SEND_BIZ_MSG, license, paramMap, BizJsonRtn.class);
    }

    public static BizJsonRtn getUsers(License license) {
        return getBiz(license, null);
    }

    public static BizJsonRtn getBiz(License license, String nextOpenId) {
        Map<String, String> paramMap = ImmutableMap.of("next_openid", StringUtils.defaultString(nextOpenId));
        return HttpUtil.getRequest(WechatRequest.GET_USERS, license, paramMap, BizJsonRtn.class);
    }
}
