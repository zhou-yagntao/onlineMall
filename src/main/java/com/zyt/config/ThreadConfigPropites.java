package com.zyt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.config
 * @ClassName: ThreadConfigPropites
 * @Author: zhou_yangtao@yeah.net
 * @Description:
 * @Date: 14:45 2021/2/25
 * @Version: 1.0
 */
@Component
@ConfigurationProperties(prefix = "drinkmall.thread")
public class ThreadConfigPropites {


    private Integer coreSize = 20;

    private Integer maxSize = 200;

    private Integer keepAliveTim = 10;

    public Integer getCoreSize() {
        return coreSize;
    }

    public void setCoreSize(Integer coreSize) {
        this.coreSize = coreSize;
    }

    public Integer getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }

    public Integer getKeepAliveTim() {
        return keepAliveTim;
    }

    public void setKeepAliveTim(Integer keepAliveTim) {
        this.keepAliveTim = keepAliveTim;
    }
}
