package org.rockey.wechat.mp.sdk.util;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.rockey.wechat.mp.sdk.qqasset.QqassetEnumFactory;
import org.rockey.wechat.mp.sdk.util.platform.AccessTokenUtil;
import org.rockey.wechat.mp.sdk.vo.BizRequest;
import org.rockey.wechat.mp.sdk.vo.JsonRtn;
import org.rockey.wechat.mp.sdk.vo.WechatRequest;
import org.rockey.wechat.mp.sdk.vo.token.License;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * @author RockeyHoo
 */
public class HttpUtil
{
    private final static Logger log = LoggerFactory.getLogger(HttpUtil.class);

    private static final Function<Map.Entry<String, String>, NameValuePair> nameValueTransformFunction = new Function<Map.Entry<String, String>, NameValuePair>()
    {
        @Override
        public NameValuePair apply(final Map.Entry<String, String> input)
        {
            return new BasicNameValuePair(input.getKey(), input.getValue());
        }
    };

    /**
     * handle response's entity to utf8 text
     */
    public static final ResponseHandler<String> UTF8_CONTENT_HANDLER = new ResponseHandler<String>()
    {
        @Override
        public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException
        {
            final StatusLine statusLine = response.getStatusLine();
            if (statusLine.getStatusCode() >= 300)
            {
                throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
            }
            final HttpEntity entity = response.getEntity();
            if (entity != null)
            {
                return EntityUtils.toString(entity, "UTF-8");
            }
            return StringUtils.EMPTY;
        }
    };

    public static <T extends JsonRtn> T getRequest(WechatRequest request, License license, Class<T> jsonRtnClazz)
    {
        return getRequest(request, license, null, jsonRtnClazz);
    }

    /**
     * 向微信服务器发送请求
     *
     * @param request
     * @param license
     * @param paramMap
     * @param jsonRtnClazz
     * @return
     */
    public static <T extends JsonRtn> T getRequest(WechatRequest request, License license, Map<String, String> paramMap, Class<T> jsonRtnClazz)
    {
        String requestUrl = request.getUrl();
        String requestName = request.getName();
        List<NameValuePair> nameValuePairs = buildNameValuePairs(license, paramMap);
        URI uri = buildURI(requestUrl, nameValuePairs);
        if (uri == null)
        {
            return JsonRtnUtil.buildFaileJsonRtn(jsonRtnClazz, "build request URI failed");
        }
        try
        {
            String json = Request.Get(uri).execute().handleResponse(HttpUtil.UTF8_CONTENT_HANDLER);
            T jsonRtn = JsonRtnUtil.parseJsonRtn(json, jsonRtnClazz);
            log.info(requestName + " result:\n url={},\n rtn={},\n {}", uri, json, jsonRtn);
            return jsonRtn;
        }
        catch (Exception e)
        {
            String msg = requestName + " failed:\n url=" + uri;
            log.error(msg, e);
            return JsonRtnUtil.buildFaileJsonRtn(jsonRtnClazz, "get request server failed");
        }
    }

    /**
     * 向业务服务器发送请求 http post
     *
     * @param request
     * @param license
     * @param paramMap
     * @param jsonRtnClazz
     * @return
     */
    public static <T extends JsonRtn> T getRequest(BizRequest request, License license, Map<String, String> paramMap, Class<T> jsonRtnClazz)
    {
        String requestUrl = request.getUrl();
        String requestName = request.getName();
        List<NameValuePair> nameValuePairs = buildNameValuePairs(license, paramMap);
        URI uri = buildURI(requestUrl, nameValuePairs);
        if (uri == null)
        {
            return JsonRtnUtil.buildFaileJsonRtn(jsonRtnClazz, "build request URI failed");
        }
        try
        {
            String json = Request.Post(uri).execute().handleResponse(HttpUtil.UTF8_CONTENT_HANDLER);
            T jsonRtn = JsonRtnUtil.parseJsonRtn(json, jsonRtnClazz);
            log.info(requestName + " result: url={}, rtn={}, {}", uri, json, jsonRtn);
            return jsonRtn;
        }
        catch (Exception e)
        {
            String msg = requestName + " failed:\n url=" + uri;
            log.error(msg, e);
            return JsonRtnUtil.buildFaileJsonRtn(jsonRtnClazz, "get request server failed");
        }
    }

    public static <T extends JsonRtn> T postBodyRequest(WechatRequest request, License license, Object requestBody, Class<T> jsonRtnClazz)
    {
        return postBodyRequest(request, license, null, requestBody, jsonRtnClazz);
    }

    public static <T extends JsonRtn> T postBodyRequest(WechatRequest request, License license, Map<String, String> paramMap, Object requestBody, Class<T> jsonRtnClazz)
    {
        if (request == null || license == null || requestBody == null || jsonRtnClazz == null)
        {
            return null;
        }
        String requestUrl = request.getUrl();
        String requestName = request.getName();
        List<NameValuePair> nameValuePairs = buildNameValuePairs(license, paramMap);
        String body = JSONObject.toJSONString(requestBody);
        URI uri = buildURI(requestUrl, nameValuePairs);
        if (uri == null)
        {
            return JsonRtnUtil.buildFaileJsonRtn(jsonRtnClazz, "build request URI failed");
        }
        try
        {
            String rtnJson = Request.Post(uri)
                    .bodyString(body, ContentType.create("text/html", Consts.UTF_8))
                    .execute().handleResponse(HttpUtil.UTF8_CONTENT_HANDLER);
            T jsonRtn = JsonRtnUtil.parseJsonRtn(rtnJson, jsonRtnClazz);
            log.info(requestName + " result:\n url={},\n body={},\n rtn={},\n {}", uri, body, rtnJson, jsonRtn);
            return jsonRtn;
        }
        catch (Exception e)
        {
            String msg = requestName + " failed:\n url=" + uri + ",\n body=" + body;
            log.error(msg, e);
            return JsonRtnUtil.buildFaileJsonRtn(jsonRtnClazz, "post request server failed");
        }
    }

    private static List<NameValuePair> buildNameValuePairs(License license, Map<String, String> paramMap)
    {
        List<NameValuePair> nameValuePairs = Lists.newArrayList();
        nameValuePairs.add(new BasicNameValuePair("access_token", AccessTokenUtil.getAccessToken(license)));
        if (paramMap != null)
        {
            Iterables.addAll(nameValuePairs, Iterables.transform(paramMap.entrySet(), nameValueTransformFunction));
        }
        return nameValuePairs;
    }

    private static List<NameValuePair> buildNameValuePairs(Map<String, String> paramMap)
    {
        List<NameValuePair> nameValuePairs = Lists.newArrayList();
        if (paramMap != null)
        {
            Iterables.addAll(nameValuePairs, Iterables.transform(paramMap.entrySet(), nameValueTransformFunction));
        }
        return nameValuePairs;
    }

    private static URI buildURI(String requestUrl, List<NameValuePair> nameValuePairs)
    {
        try
        {
            return new URIBuilder(requestUrl).setParameters(nameValuePairs).build();
        }
        catch (Exception e)
        {
            String msg = "build URI failed:\n url=" + requestUrl + "\n params=" + nameValuePairs;
            log.error(msg, e);
            return null;
        }
    }

    public static String getRequest(QqassetEnumFactory request, Map<String, String> paramMap)
    {
        String requestUrl = request.getUrl();
        String requestName = request.getName();
        List<NameValuePair> nameValuePairs = buildNameValuePairs(paramMap);
        URI uri = buildURI(requestUrl, nameValuePairs);
        if (uri == null)
        {
            return "";
        }
        try
        {
            log.info(requestName + " result:\n url={}", requestUrl);
            return Request.Get(uri).execute().handleResponse(HttpUtil.UTF8_CONTENT_HANDLER);
        }
        catch (Exception e)
        {
            String msg = requestName + " failed:\n url=" + uri;
            log.error(msg, e);
            return "";
        }
    }

    public static String getRequest(QqassetEnumFactory request, String param, Map<String, String> paramMap)
    {
        String requestUrl = String.format(request.getUrl(), param);
        String requestName = request.getName();
        List<NameValuePair> nameValuePairs = buildNameValuePairs(paramMap);
        URI uri = buildURI(requestUrl, nameValuePairs);
        if (uri == null)
        {
            return "";
        }
        try
        {
            log.info(requestName + " result:\n url={}", requestUrl);
            return Request.Get(uri).execute().handleResponse(HttpUtil.UTF8_CONTENT_HANDLER);
        }
        catch (Exception e)
        {
            String msg = requestName + " failed:\n url=" + uri;
            log.error(msg, e);
            return "";
        }
    }
}
