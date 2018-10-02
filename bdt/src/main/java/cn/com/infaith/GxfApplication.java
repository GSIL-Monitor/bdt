package cn.com.infaith;

import cn.com.infaith.container.filter.UserAuthorizeFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.MultipartConfigElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/13.
 */
@SpringBootApplication
@EnableSwagger2
@EnableScheduling
@MapperScan(basePackages = "cn.com.infaith.module.mapper")
public class GxfApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(GxfApplication.class);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(GxfApplication.class);
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        UserAuthorizeFilter httpBasicFilter = new UserAuthorizeFilter();
        registrationBean.setFilter(httpBasicFilter);
        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add("/*");
        urlPatterns.add("/*/*");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }


    /**
     * low version redis serve will occour : Unable to configure Redis to keyspace notifications.
     * https://docs.spring.io/spring-session/docs/current/reference/html5/#api-redisoperationssessionrepository-sessiondestroyedevent
     *
     * @return
     */
    @Bean
    public static ConfigureRedisAction configureRedisAction() {
        return ConfigureRedisAction.NO_OP;
    }
//
//    @Bean
//    public MultipartConfigElement multipartConfigElement() {
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//        //单个文件最大
//        factory.setMaxFileSize("200MB"); //KB,MB
//        /// 设置总上传数据总大小
//        factory.setMaxRequestSize("400MB");
//        return factory.createMultipartConfig();
//    }

}
