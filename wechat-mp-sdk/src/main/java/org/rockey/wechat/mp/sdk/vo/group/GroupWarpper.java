package org.rockey.wechat.mp.sdk.vo.group;

import org.rockey.wechat.mp.sdk.vo.AbstractToStringBuilder;

/**
 *
 * @author RockeyHoo
 */
public class GroupWarpper extends AbstractToStringBuilder
{
    private Group group;

    public GroupWarpper() {
    }

    public GroupWarpper(Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

}
