package com.xiaoluo.idempotent.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Project:               idempotent
 *
 * @Author: ljw
 * Company:               Big Player Group
 * Created Date:          2020/5/29
 * Description:   {类描述}
 * Copyright @ 2017-2020 BIGPLAYER.GROUP – Confidential and Proprietary
 * <p>
 * History:
 * ------------------------------------------------------------------------------
 * Date            |time        |Author    |Change Description
 */
@Configuration
public class IdempotentConfig {

    @Value("${spring.redis.host}")
    private String redisAddress;

    @Value("${spring.redis.password}")
    private String password;

    @Bean
    public Config config(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://"+redisAddress+":6379")
                .setPassword(password);
        return config;
    }

    @Bean
    public RedissonClient redisson(){
        return Redisson.create(config());
    }


}
