package com.xian.config;


import com.xian.interceptor.JwtInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;




@Configuration
@Slf4j
public class WebMvcConfiguration extends WebMvcConfigurationSupport {
    @Autowired
    private JwtInterceptor jwtInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**") // 需要拦截的路径
                .excludePathPatterns("/users/login") // 排除登录路径
                .excludePathPatterns("/users/register") // 排除注册路径
                .excludePathPatterns("/swagger-ui/**")  // 排除 Swagger UI 路径
                .excludePathPatterns("/v2/api-docs/**") // 排除 Swagger 的 API 文档路径
                .excludePathPatterns("/swagger-resources/**") // 排除 Swagger 资源路径
                .excludePathPatterns("/webjars/**") // 排除 Swagger 的 webjars 资源路径
                .excludePathPatterns("/actuator/**") // 排除 Actuator 的路径（如果有使用 Actuator）
                .excludePathPatterns("/doc.html") // 排除 Swagger 的静态资源路径
                .order(1); // 设置拦截器的优先级
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
    public Docket docket(){
        log.info("生成接口文档");
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("online-education-platform项目接口文档")
                .version("2.0")
                .description("online-education-platform接口文档")
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                //指定生成接口需要扫描的包
                .apis(RequestHandlerSelectors.basePackage("com.xian.controller"))
                .paths(PathSelectors.any())
                .build();
    }






}
