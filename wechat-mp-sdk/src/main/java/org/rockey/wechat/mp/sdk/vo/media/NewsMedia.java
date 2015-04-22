package org.rockey.wechat.mp.sdk.vo.media;

import java.util.List;

import org.rockey.wechat.mp.sdk.vo.AbstractToStringBuilder;

import com.alibaba.fastjson.annotation.JSONField;

/**
 *
 * @author RockeyHoo
 */
public class NewsMedia extends AbstractToStringBuilder
{
    @JSONField(name = "articles")
    private List<NewsMediaDetail> articles;

    public NewsMedia(List<NewsMediaDetail> articles) {
        this.articles = articles;
    }

    public List<NewsMediaDetail> getArticles() {
        return articles;
    }

    public void setArticles(List<NewsMediaDetail> articles) {
        this.articles = articles;
    }

}
