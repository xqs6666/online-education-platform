package com.xian.config;



import com.xian.interceptor.JwtInterceptor;
import com.xian.json.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.WebFluxRequestHandlerProvider;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;


@Configuration
@Slf4j
public class WebMvcConfiguration extends WebMvcConfigurationSupport {
    @Autowired
    private JwtInterceptor jwtInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/online/**") // 需要拦截的路径
                .excludePathPatterns("/online/users/login") // 排除登录路径
                .excludePathPatterns("/online/users/register","/online/images/upload"); // 排除注册路径

    }

    /**
     * 设置静态资源映射
     * @param registry
     */
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    @Bean
    public Docket docket() {
        try {
            log.info("生成接口文档");
            ApiInfo apiInfo = new ApiInfoBuilder()
                    .title("online-education-platform项目接口文档")
                    .version("2.0")
                    .description("online-education-platform接口文档")
                    .build();

            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.xian.controller"))
                    .paths(PathSelectors.any())
                    .build();
        } catch (NullPointerException e) {
            log.error("空指针异常: ", e);
            throw e; // 重新抛出异常以确保应用无法启动
        }
    }


    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        jackson2HttpMessageConverter.setObjectMapper(new JacksonObjectMapper());
        converters.add(0,jackson2HttpMessageConverter);
    }

}





