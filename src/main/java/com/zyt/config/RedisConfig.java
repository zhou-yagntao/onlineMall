package com.zyt.config;

import com.zyt.utils.FastJsonRedisSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @ProjectName: SpringBootComprehensiveCases
 * @Package: com.justin.config
 * @ClassName: RedisConfigTemplete
 * @Author: Justin
 * @Description:
 * @Date: 18:56 2020/7/20
 * @Version: 1.0
 */
@Configuration
@EnableCaching
@ConditionalOnClass(RedisOperations.class)
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {


        @Autowired(required = false)
        private FastJsonRedisSerializer fastJsonRedisSerializer;

        private  static Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);


        @Bean
        @ConditionalOnMissingBean(name = "redisTemplate")
        public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
            RedisTemplate<Object, Object> template = new RedisTemplate<>();
            //使用fastjson序列化
            //FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer(Object.class);
            // key的序列化采用StringRedisSerializer
            template.setKeySerializer(new StringRedisSerializer());
            // value值的序列化采用fastJsonRedisSerializer 使用jackson2序列化
            template.setValueSerializer(jackson2JsonRedisSerializer);
            template.setHashValueSerializer(jackson2JsonRedisSerializer);
            //template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
            //template.setHashKeySerializer(new StringRedisSerializer());
            template.setConnectionFactory(redisConnectionFactory);
            return template;
        }

        @Bean
        @ConditionalOnMissingBean(StringRedisTemplate.class)
        public StringRedisTemplate stringRedisTemplate(
                RedisConnectionFactory redisConnectionFactory) {
            StringRedisTemplate template = new StringRedisTemplate();
            template.setConnectionFactory(redisConnectionFactory);
            return template;
        }


        @Bean
        public RedisCacheConfiguration redisCacheConfiguration(){
            StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
            RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
            configuration = configuration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(stringRedisSerializer)).entryTtl(Duration.ofDays(30));
            return configuration;
        }

    }
