package org.rockey.wechat.mp.web.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(locations = {"classpath:/config/spring/local/applicationContext-core.xml"})
public class AutoSavePngTest extends AbstractTestNGSpringContextTests
{

    @Autowired
    private AutoSavePng autoSavePng;

    @Test
    public void testExcute() throws Exception
    {
        autoSavePng.testSendMsg();
    }
}