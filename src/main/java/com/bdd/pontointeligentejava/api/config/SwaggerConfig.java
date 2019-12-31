package com.bdd.pontointeligentejava.api.config;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;

import com.google.common.collect.Lists;

import io.swagger.models.auth.SecuritySchemeDefinition;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration 
@EnableSwagger2 
@Profile("dev") 
//@Import(springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration.class) 
public class SwaggerConfig { 
	
	@Bean 
	public Docket swaggerSpringfoxDocket() { 
		Docket docket = new Docket(DocumentationType.SWAGGER_2) 
				.pathMapping("/")
				.forCodeGeneration(true)
				.genericModelSubstitutes(ResponseEntity.class)
				.ignoredParameterTypes(java.sql.Date.class)
				.directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
				.directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
				.directModelSubstitute(java.time.LocalDateTime.class, Date.class)
				.securityContexts(Lists.newArrayList(securityContext()))
				.securitySchemes(Lists.newArrayList(apiKey()))
				.useDefaultResponseMessages(false); 
		
		docket = docket.select()
				.apis(RequestHandlerSelectors.basePackage("com.bdd.pontointeligentejava.api.controllers"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiEndPointsInfo());
		
		return docket; 
	} 
	
	private ApiInfo apiEndPointsInfo() { 
		return new ApiInfoBuilder().title("Ponto Inteligente API")
				.description("Documentação da API de acesso aos endpoints do Ponto Inteligente.").version("1.0")
				.build();
	} 
	
	private ApiKey apiKey() { 
		return new ApiKey("JWT", "Authorization", "header"); 
	} 
	
	private SecurityContext securityContext() { 
		return SecurityContext
				.builder()
				.securityReferences(defaultAuth())
				.forPaths(PathSelectors.any())
				.build(); 
	} 
	
	List<SecurityReference> defaultAuth() { 
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything"); 
		System.out.println(authorizationScope.getDescription() +" - "+authorizationScope.getScope());
		
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1]; 
		authorizationScopes[0] = authorizationScope;
		
		List<SecurityReference> lists = Lists.newArrayList(new SecurityReference("JWT", authorizationScopes)); 
		lists.forEach(item -> System.out.println(item.getReference())); 
		return lists; 
	} 

}
