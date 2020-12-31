package com.hd.system.config.oss;

import com.hd.common.util.oss.OssBootUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OssBootConfiguration {

    @Value("${hd.oss.endpoint}")
    private String endpoint;
    @Value("${hd.oss.accessKey}")
    private String accessKeyId;
    @Value("${hd.oss.secretKey}")
    private String accessKeySecret;
    @Value("${hd.oss.bucketName}")
    private String bucketName;
    @Value("${hd.oss.staticDomain}")
    private String staticDomain;


    @Bean
    public void initOssBootConfiguration() {
        OssBootUtil.setEndPoint(endpoint);
        OssBootUtil.setAccessKeyId(accessKeyId);
        OssBootUtil.setAccessKeySecret(accessKeySecret);
        OssBootUtil.setBucketName(bucketName);
        OssBootUtil.setStaticDomain(staticDomain);
    }
}