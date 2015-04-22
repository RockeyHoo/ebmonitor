package org.rockey.wechat.mp.sdk.util.platform;

import org.rockey.wechat.mp.sdk.vo.token.License;

/**
 *
 * @author RockeyHoo
 */
public class AccessTokenUtilTest {
    public static void main(String[] args) {
        if (args == null || args.length < 2) {
            System.out.println("please add two args: appId/appSecret");
            System.exit(0);
        }

        License license = new License("test", args[0], args[1]);
        System.out.println(AccessTokenUtil.getAccessToken(license));
    }
}
