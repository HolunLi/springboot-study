package com.holun.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
//@EnableSwagger2  //开启swagger2
@EnableOpenApi
public class SwaggerConfiguration {
    private final SwaggerProperties swaggerProperties;

    public SwaggerConfiguration(SwaggerProperties swaggerProperties) {
        this.swaggerProperties = swaggerProperties;
    }

    /**
     *创建接口文档
     */
    @Bean
    public Docket createRestApi1() {
        return new Docket(DocumentationType.OAS_30) //DocumentationType.SWAGGER_2
                .apiInfo(apiInfo())
                .enable(swaggerProperties.getEnable()) //设置是否开启swagger,默认为true。如果设置为false，则无法在浏览器中使用swagger
                .host(swaggerProperties.getHost()) //设置接口调试地址
                .groupName("A") //分组
                .select()
                /**
                 * RequestHandlerSelectors 配置扫描接口的方式
                 * basePackage("包的路径")    扫描指定包下的接口
                 * any() 扫描所有的接口
                 * none() 不扫描接口
                 * withMethodAnnotation("注解类型.class")  //扫描方法上带有指定注解的接口
                 * withMethodAnnotation("注解类型.class") //扫描类上带有指定注解的接口
                 */
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any()) //过滤不需要扫描的资源路径
                .build();
    }

    @Bean
    public Docket createRestApi2() {
        return new Docket(DocumentationType.OAS_30).groupName("B");
    }

    @Bean
    public Docket createRestApi3() {
        return new Docket(DocumentationType.OAS_30).groupName("C");
    }

    /**
     *自定义接口文档中的信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swaggerProperties.getTitle()) //文档标题
                .description(swaggerProperties.getDescription())  //文档描述
                .version(swaggerProperties.getVersion())
                .contact(new Contact("Holun",null,"Holun_Li@163.com")) //联系（编写者的信息）
                .build();  //建立
    }

}
