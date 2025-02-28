package com.sky.config;

import com.sky.properties.AliOssProperties;
import com.sky.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author 夙落陌上梦
 * @version 1.0
 * @ClassName OssConfiguration
 * @DateTime 2025/2/22 上午9:15
 * @Description:
 */
@Configuration
@Slf4j
public class OssConfiguration {
    @Bean
    @ConditionalOnMissingBean()
    public AliOssUtil aliOssUtil(AliOssProperties aliOssPropertiesO){
        log.info("正在创建阿里云oos工具类对象{}",aliOssPropertiesO);
        return new AliOssUtil(aliOssPropertiesO.getEndpoint(),
                aliOssPropertiesO.getAccessKeyId(),
                aliOssPropertiesO.getAccessKeySecret(),
                aliOssPropertiesO.getBucketName());
    }
}
