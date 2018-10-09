package com.passcloud.common.core.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.passcloud.common.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import org.springframework.cache.interceptor.KeyGenerator;

/**
 * Redis 配置类
 *
 * @author liyuzhang
 * @version 2018/6/17 17:46
 */
@Configuration
// 必须加，使配置生效
@EnableCaching
public class RedisConfiguration extends CachingConfigurerSupport {
	
	/**
	 * Logger
	 */
	private static final Logger lg = LoggerFactory.getLogger(RedisConfiguration.class);
	
	
	@Autowired
	private JedisConnectionFactory jedisConnectionFactory;
	
	@Bean
	@Override
	public KeyGenerator keyGenerator() {
		//  设置自动key的生成规则，配置spring boot的注解，进行方法级别的缓存
		// 使用：进行分割，可以很多显示出层级关系
		// 这里其实就是new了一个KeyGenerator对象，只是这是lambda表达式的写法，我感觉很好用，大家感兴趣可以去了解下
		return (target, method, params) -> {
			StringBuilder sb = new StringBuilder();
			sb.append(target.getClass().getName());
			sb.append(":");
			sb.append(method.getName());
			for (Object obj : params) {
				sb.append(":" + String.valueOf(obj));
			}
			String rsToUse = String.valueOf(sb);
			lg.info("自动生成Redis Key -> [{}]", rsToUse);
			return rsToUse;
		};
	}
	
	@Bean
	@Override
	public CacheManager cacheManager() {
		// 初始化缓存管理器，在这里我们可以缓存的整体过期时间什么的，我这里默认没有配置
		lg.info("初始化 -> [{}]", "CacheManager RedisCacheManager Start");
		RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager
				.RedisCacheManagerBuilder
				.fromConnectionFactory(jedisConnectionFactory);
		return builder.build();
	}
	
	@Bean("redisTemplate")
	public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory ) {
		//设置序列化
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		// 配置redisTemplate
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory);
		RedisSerializer stringSerializer = new StringRedisSerializer();
		redisTemplate.setKeySerializer(stringSerializer); // key序列化
		redisTemplate.setValueSerializer(jackson2JsonRedisSerializer); // value序列化
		redisTemplate.setHashKeySerializer(stringSerializer); // Hash key序列化
		redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer); // Hash value序列化
		redisTemplate.afterPropertiesSet();
		// 开启事务
		redisTemplate.setEnableTransactionSupport(true);
		return redisTemplate;
	}
	
	/**
	 * 注入封装RedisTemplate
	 * @Title: redisUtil
	 * @return RedisUtil
	 * @autor liyuzhang
	 * @date 2018年09月06日
	 * @throws
	 */
	@Bean(name = "redisUtil")
	public RedisUtil redisUtil(RedisTemplate<String, Object> redisTemplate) {
		RedisUtil redisUtil = new RedisUtil();
		redisUtil.setRedisTemplate(redisTemplate);
		return redisUtil;
	}
	
	@Override
	@Bean
	public CacheErrorHandler errorHandler() {
		// 异常处理，当Redis发生异常时，打印日志，但是程序正常走
		lg.info("初始化 -> [{}]", "Redis CacheErrorHandler");
		CacheErrorHandler cacheErrorHandler = new CacheErrorHandler() {
			@Override
			public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
				lg.error("Redis occur handleCacheGetError：key -> [{}]", key, e);
			}
			
			@Override
			public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
				lg.error("Redis occur handleCachePutError：key -> [{}]；value -> [{}]", key, value, e);
			}
			
			@Override
			public void handleCacheEvictError(RuntimeException e, Cache cache, Object key)    {
				lg.error("Redis occur handleCacheEvictError：key -> [{}]", key, e);
			}
			
			@Override
			public void handleCacheClearError(RuntimeException e, Cache cache) {
				lg.error("Redis occur handleCacheClearError：", e);
			}
		};
		return cacheErrorHandler;
	}
	
	/**
	 * 此内部类就是把yml的配置数据，进行读取，创建JedisConnectionFactory和JedisPool，以供外部类初始化缓存管理器使用
	 * 不了解的同学可以去看@ConfigurationProperties和@Value的作用
	 *
	 */
	@Configuration
	@ConfigurationProperties(prefix = "spring.redis")
	class DataJedisProperties{
		@Value("${spring.redis.host}")
		private  String host;
		@Value("${spring.redis.password}")
		private  String password;
		@Value("${spring.redis.port}")
		private  int port;
		@Value("${spring.redis.timeout}")
		private  int timeout;
		@Value("${spring.redis.jedis.pool.max-idle}")
		private int maxIdle;
		@Value("${spring.redis.jedis.pool.max-wait}")
		private long maxWaitMillis;
		
		@Bean
		JedisConnectionFactory jedisConnectionFactory() {
			lg.info("Create JedisConnectionFactory successful");
			JedisConnectionFactory factory = new JedisConnectionFactory();
			factory.setHostName(host);
			factory.setPort(port);
			factory.setTimeout(timeout);
			factory.setPassword(password);
			return factory;
		}
		@Bean
		public JedisPool redisPoolFactory() {
			lg.info("JedisPool init successful，host -> [{}]；port -> [{}]", host, port);
			JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
			jedisPoolConfig.setMaxIdle(maxIdle);
			jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
			
			JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
			return jedisPool;
		}
	}
	
}
