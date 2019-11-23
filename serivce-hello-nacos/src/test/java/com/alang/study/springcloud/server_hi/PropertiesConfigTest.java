package com.alang.study.springcloud.server_hi;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertiesConfigTest {

    private ConfigService configService;
    private String dataId = "service-hello-nacos-dev.properties";
    private String group = "test";

    @Autowired
    PropertiesConfig propertiesConfig;

    @Before
    public void beforeClass() throws NacosException {
        Properties prop = new Properties();
        prop.setProperty(PropertyKeyConst.SERVER_ADDR, "localhost:8848");

        configService = NacosFactory.createConfigService(prop);
    }

    @After
    public void afterClass(){
        configService = null;
    }

    @Test
    public void testGetConfig() throws Exception
    {
        System.out.println(configService.getServerStatus());
        String content = configService.getConfig(dataId, group, 5000);

        assertNull(content);

        final CountDownLatch latch = new CountDownLatch(1);
        Listener listener = new TestListener(latch);
        configService.addListener(dataId, group, listener);

        String publishContent = "com.dd.publish.enabled=true";
        boolean isPublishOK = configService.publishConfig(dataId, group, publishContent);

        latch.await(5, TimeUnit.SECONDS);

        assertTrue(isPublishOK);
        assertEquals(publishContent, configService.getConfig(dataId, group, 5000));

        assertTrue(configService.removeConfig(dataId, group));

        assertNull(configService.getConfig(dataId, group, 5000));

        configService.removeListener(dataId, group, listener);

    }

    @Test
    public void testProperty() throws Exception
    {
        System.out.println("propertiesConfig age:" + propertiesConfig.getThreshold());
        assertEquals(70, propertiesConfig.getThreshold());

        String dataId = "service-hello-nacos.properties";
        String group = "DEV_GROUP";
        boolean isPublishOK = configService.publishConfig(dataId, group, "com.kevin.dd.threshold=80");
        TimeUnit.SECONDS.sleep(5);

        System.out.println("propertiesConfig age:" + propertiesConfig.getThreshold());
        configService.removeConfig(dataId, group);
    }

    static class TestListener implements Listener{
        private CountDownLatch latch;

        TestListener(CountDownLatch latch)
        {
            this.latch = latch;
        }
        @Override
        public Executor getExecutor() {
            return null;
        }

        @Override
        public void receiveConfigInfo(String configInfo) {
            System.out.println("Listener received:" + configInfo);
            latch.countDown();
        }
    }


}
