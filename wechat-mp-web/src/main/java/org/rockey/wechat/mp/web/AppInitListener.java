package org.rockey.wechat.mp.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppInitListener implements ServletContextListener
{
    private static final Logger LOG = LoggerFactory.getLogger(AppInitListener.class);

    public void contextDestroyed(ServletContextEvent arg0)
    {
    }

    public void contextInitialized(ServletContextEvent arg0)
    {
        ServletContext servletContext = arg0.getServletContext();
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        try
        {
            LOG.warn("//////////////////////////////////////////////////////////////////////");
            LOG.warn("//							_ooOoo_								  //");
            LOG.warn("//						   o8888888o							  //");
            LOG.warn("//						   88\" . \"88							  //");
            LOG.warn("//						   (| -_- |)							  //");
            LOG.warn("//						   O\\  =  /O							  //");
            LOG.warn("//						____/`---'\\____					      //");
            LOG.warn("//					  .'  \\|     |//  `.						  //");
            LOG.warn("//					 /  \\\\|||  :  |||//  \\					  //");
            LOG.warn("//				    /  _||||| -:- |||||-  \\					  //");
            LOG.warn("//				    |   | \\\\\\  -  /// |   |					  //");
            LOG.warn("//					| \\_|  ''\\---/''  |   |					  //");
            LOG.warn("//					\\  .-\\__  `-`  ___/-. /					  //");
            LOG.warn("//				  ___`. .'  /--.--\\ `. . ___					  //");
            LOG.warn("//				.\"\" '<  `.___\\_<|>_/___.'  >'\"\".			  //");
            LOG.warn("//			  | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |				  //");
            LOG.warn("//			  \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /             //");
            LOG.warn("//		========`-.____`-.___\\_____/___.-`____.-'========		  //");
            LOG.warn("//				             `=---='                              //");
            LOG.warn("//                         Congratulations                          //");
            LOG.warn("//            wechat-mp-web  failed to start!!!!!                   //");
            LOG.warn("//           https://github.com/RockeyHoo/ebmonito                  //");
            LOG.warn("//////////////////////////////////////////////////////////////////////");

        }
        catch (Exception e)
        {
            LOG.error("Failed to initialize ...", e);
            exitWithLog();
            return;
        }
    }

    private void exitWithLog()
    {
        LOG.error("--------------------------------------------------------------");
        LOG.error("---                                                        ---");
        LOG.error("---                         ERROR                          ---");
        LOG.error("---        wechat-mp-web  failed to start!!!!!             ---");
        LOG.error("---        https://github.com/RockeyHoo/ebmonito           ---");
        LOG.error("---                                                        ---");
        LOG.error("--------------------------------------------------------------");
        System.exit(-1);
    }

}
