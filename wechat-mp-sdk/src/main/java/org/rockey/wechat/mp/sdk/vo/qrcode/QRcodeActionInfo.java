package org.rockey.wechat.mp.sdk.vo.qrcode;

import com.alibaba.fastjson.annotation.JSONField;

/**
 *
 * @author RockeyHoo
 */
public class QRcodeActionInfo {
    @JSONField(name = "scene")
    private QRcodeScene scene;

    public QRcodeActionInfo() {
    }

    public QRcodeActionInfo(QRcodeScene scene) {
        this.scene = scene;
    }

    public QRcodeScene getScene() {
        return scene;
    }

    public void setScene(QRcodeScene scene) {
        this.scene = scene;
    }

}
