package cn.com.infaith.container.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Administrator on 2017/4/13.
 */
@Configuration
@MapperScan(basePackages = "cn/com/infaith/module/*/mapper")
//@PropertySource("classpath:infrastructureConfig.properties")
public class MyBatisConfig {
    @Value("${jdbc.driverClassName}")
    private String driverClass;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Value("${mybatis.typeAliasesPackage}")
    private String typeAliasesPackage;
    @Value("${mybatis.mapperLocations}")
    private String mapperLocations;

    @Bean
    public DataSource getDataSource() throws Exception {
        Properties props = new Properties();
        //<!-- 基本属性 url、user、password -->
        props.put("driverClassName", driverClass);
        props.put("url", url);
        props.put("username", username);
        props.put("password", password);
        // <!-- 配置初始化大小、最小、最大 -->
        props.put("initialSize", "1");
        props.put("minIdle", "1");
        props.put("maxActive", "20");
        //  <!-- 配置获取连接等待超时的时间 -->
        props.put("maxWait", "600000");
        // <!-- 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 -->
        props.put("timeBetweenEvictionRunsMillis", "600000");
        // <!-- 配置一个连接在池中最小生存的时间，单位是毫秒 -->
        props.put("minEvictableIdleTimeMillis", "600000");
        //连接检查sql
        props.put("validationQuery", "SELECT NOW() FROM DUAL");
        props.put("testWhileIdle", "true");
        props.put("testOnBorrow", "false");
        props.put("testOnReturn", "false");
        props.put("poolPreparedStatements", "true");
        props.put("maxPoolPreparedStatementPerConnectionSize", "20");
        props.put("filters", "stat");
        //props.put("logImpl","STDOUT_LOGGING");
        return DruidDataSourceFactory.createDataSource(props);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(getDataSource());
        fb.setTypeAliasesPackage(typeAliasesPackage);
        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
        return fb.getObject();
    }


}
