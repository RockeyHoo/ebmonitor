package org.rockey.wechat.mp.web.servlet;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.rockey.wechat.mp.sdk.factory.PushEnumFactory;
import org.rockey.wechat.mp.sdk.util.XmlUtil;
import org.rockey.wechat.mp.sdk.vo.Signature;
import org.rockey.wechat.mp.sdk.vo.message.reply.Reply;
import org.rockey.wechat.mp.sdk.vo.push.Push;
import org.rockey.wechat.mp.web.util.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AccessServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(AccessServlet.class);

    private static final String TOKEN = "Qwer1234"; // TODO please custom it.

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            Signature signature = new Signature();
            boolean hasRights = checkSignature(signature, request);
            if (hasRights)
            {
                log.info("signature-success:{}", signature);
                WebUtil.outputString(response, signature.getEchostr());
            }
            else
            {
                log.info("signature-not-match:{}", signature);
            }
        }
        catch (Exception e)
        {
            log.error("signature-error", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            log.info("========= post begin ===========");
            log.info("--							");
            log.info("========= post begin ===========");
            Signature signature = new Signature();
            if (!checkSignature(signature, request))
            {
                log.info("signature-not-match:{}", signature);
                return;
            }
            String sessionid = request.getSession().getId();
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            String message = WebUtil.getPostString(request.getInputStream());
            log.info("接收wechat推送消息====> push-xml:{},{}", sessionid, message);
            String messageType = getMsgType(message);
            PushEnumFactory pushEnum = EnumUtils.getEnum(PushEnumFactory.class, StringUtils.upperCase(messageType));
            Validate.notNull(pushEnum, "don't-support-%s-type-message", messageType);
            Push push = pushEnum.convert(message);
            Reply reply = pushEnum.parse(push);
            String replyXml = XmlUtil.marshal(reply);
            if (StringUtils.isNotEmpty(replyXml))
            {
                log.info("回复推送消息====> reply-xml:{},{}", sessionid, replyXml);
                WebUtil.outputString(response, replyXml);
            }
            else
            {
                log.info("no-reply:{},{}", sessionid, reply);
            }
        }
        catch (Exception e)
        {
            log.error("push-reply-error", e);
        }
        log.info("=========== post end ==============");
        log.info("--						");
        log.info("=========== post end ==============");
    }

    private boolean checkSignature(Signature signature, HttpServletRequest request) throws IllegalAccessException, InvocationTargetException
    {
        BeanUtils.populate(signature, request.getParameterMap());
        signature.setToken(TOKEN);
        String sign = Hashing.sha1().hashString(buildSignatureText(signature), Charsets.UTF_8).toString();
        return sign.equalsIgnoreCase(signature.getSignature());
    }

    private String buildSignatureText(Signature signature)
    {
        List<String> list = new ArrayList<String>();
        list.add(Validate.notNull(signature.getToken(), "missing-token"));
        list.add(Validate.notNull(signature.getTimestamp(), "missing-timestamp"));
        list.add(Validate.notNull(signature.getNonce(), "missing-nonce"));
        Collections.sort(list);
        return StringUtils.join(list, "");
    }

    private String getMsgType(String message)
    {
        return StringUtils.substringBetween(message, "<MsgType><![CDATA[", "]]></MsgType>");
    }
}
