package com.alang.study.springcloud.server_hi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class PropertiesConfig {

    @Value("${server.port}")
    private String serverPort;

    @Value("${external.config:}")
    private String config;

    @Value("${profile.name:}")
    private String profilerName;

    @Value("${com.kevin.dd.threshold:0}")
    private int threshold;

    //from project common.properties
    @Value("${common.name:empty_name}")
    private String commonName;

    @Value("${debug.mode:}")
    private String debugMode;

    public String getServerPort() {
        return serverPort;
    }

    public String getConfig() {
        return config;
    }

    public String getProfilerName() {
        return profilerName;
    }

    public int getThreshold() {
        return threshold;
    }

    public String getCommonName() {
        return commonName;
    }

    public String getDebugMode() {
        return debugMode;
    }
}
