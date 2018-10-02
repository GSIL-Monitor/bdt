package cn.com.infaith.container.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

import java.lang.reflect.Method;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.pool.min-idle}")
    private int maxIdl;
    @Value("${spring.redis.pool.max-idle}")
    private int minIdl;


    @Bean
    @Primary
    public RedisConnectionFactory redisConnectionFactory1() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(maxIdl);
        poolConfig.setMinIdle(minIdl);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setNumTestsPerEvictionRun(10);
        poolConfig.setTimeBetweenEvictionRunsMillis(60000);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(poolConfig);
        jedisConnectionFactory.setHostName(host);
        jedisConnectionFactory.setPort(port);
        jedisConnectionFactory.setDatabase(0);//编号为0的数据库
        return jedisConnectionFactory;
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory2() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(maxIdl);
        poolConfig.setMinIdle(minIdl);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setNumTestsPerEvictionRun(10);
        poolConfig.setTimeBetweenEvictionRunsMillis(60000);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(poolConfig);
        jedisConnectionFactory.setHostName(host);
        jedisConnectionFactory.setPort(port);
        jedisConnectionFactory.setDatabase(2);//编号为2的数据库
        return jedisConnectionFactory;
    }

    @Bean(name = "redisTemplate1")
    //此处注意定义的bean要注入到相应的service中使用 只有对应service才能处理对应的数据库
    public RedisTemplate<String, String> redisTemplate1() throws Exception {
        StringRedisTemplate redisTemplateObject = new StringRedisTemplate();
        redisTemplateObject.setConnectionFactory(redisConnectionFactory1());
        setSerializer(redisTemplateObject);
        redisTemplateObject.afterPropertiesSet();
        return redisTemplateObject;
    }

    @Bean(name = "redisTemplate2")
    //此处注意定义的bean要注入到相应的service中使用 只有对应service才能处理对应的数据库
    public RedisTemplate<String, Object> redisTemplate2() throws Exception {
        RedisTemplate<String, Object> redisTemplateObject = new RedisTemplate<String, Object>();
        redisTemplateObject.setConnectionFactory(redisConnectionFactory2());
        setRedisSerializer(redisTemplateObject);
        redisTemplateObject.afterPropertiesSet();
        return redisTemplateObject;
    }


    private void setSerializer(StringRedisTemplate template) {
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
//        template.setKeySerializer(template.getStringSerializer());
        template.setValueSerializer(jackson2JsonRedisSerializer);
//        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        //在使用String的数据结构的时候使用这个来更改序列化方式
        /*RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringSerializer );
        template.setValueSerializer(stringSerializer );
        template.setHashKeySerializer(stringSerializer );
        template.setHashValueSerializer(stringSerializer );*/
    }


    private void setRedisSerializer(RedisTemplate<String, Object> template) {
        /*Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setKeySerializer(template.getStringSerializer());
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);*/
        //在使用String的数据结构的时候使用这个来更改序列化方式
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringSerializer );
        template.setValueSerializer(stringSerializer );
        template.setHashKeySerializer(stringSerializer );
        template.setHashValueSerializer(stringSerializer );
    }
//}

    /**
     * 生成key的策略
     *
     * @return
     */
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

    /**
     * 管理缓存
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
        return rcm;
    }





//    /**
//     * RedisTemplate配置
//     */
//    @Bean
//    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
//        StringRedisTemplate template = new StringRedisTemplate(factory);
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        template.setValueSerializer(jackson2JsonRedisSerializer);
//        template.afterPropertiesSet();
//        return template;
//    }

    public static void main(String[] args) throws Exception {
//        System.out.println(URLEncoder.encode("question哪些上市公司可以豁免适用《上市公司+PE指引》？scopeId沪市主板platformweb", "UTF-8"));
        Jedis jedis = new Jedis("test.redis.localdomain", 6379);
        System.out.println(jedis.get("600569.SH"));
//        System.out.println(jedis.flushAll());
//        jedis.set("000976.SZ", "[{\"outflow\":4256966,\"name\":\"超大\",\"inflow\":3046135,\"netinflow\":-1210831},{\"outflow\":64427002,\"name\":\"大单\",\"inflow\":80524799,\"netinflow\":16097797},{\"outflow\":143794340,\"name\":\"中单\",\"inflow\":145965815,\"netinflow\":2171475},{\"outflow\":149280957,\"name\":\"小单\",\"inflow\":132222515,\"netinflow\":-17058442}]");
//        System.out.println(jedis.get("000976.SZ"));
//        JSONObject json=new JSONObject();
//        json.put("name","xxxx");
//        json.put("value","asvvv");
//        JSONArray jsonArray=new JSONArray();
//        jsonArray.add(json);
//        jsonArray.add("2");
//        jedis.set("000662.SZS",jsonArray.toJSONString());
//        System.out.println(jedis.get("000662.SZS"));
//        System.out.println(JSONArray.parseArray(jedis.get("000662.SZS")));
//        System.out.println(jedis.get("000662.SZ"));
//        jedis.flushAll();
//        Jedis jedis = new Jedis("192.168.100.37", 6379);
//        Object obj = jedis.get("ff80818159162bfe0159ca3abe331a44");
//        jedis.del("ff80818159162bfe0159ca3abe331a44");
//        System.out.println(jedis.flushAll());
//        System.out.println(URLEncoder.encode("崔丹","UTF-8"));
//        if (obj == null) {、
//            System.out.println(jedis.get("cnm"));
//        }
//        jedis.del("ff8081815696ab260156970337d10    044");
//        System.out.println(jedis.del("ff8081815bc6d5d6015c39cf08e707b0"));
    }
}
