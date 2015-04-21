package org.usc.wechat.mp.sdk.vo.group;

import org.usc.wechat.mp.sdk.vo.JsonRtn;

/**
 *
 * @author RockeyHoo
 */
public class GroupJsonRtn extends JsonRtn {
    private Group group;

    public GroupJsonRtn() {
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
