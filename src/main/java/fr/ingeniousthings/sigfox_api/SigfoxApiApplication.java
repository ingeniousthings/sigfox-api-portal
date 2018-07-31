/**
 * -------------------------------------------------------------------------------
 * This file is part of IngeniousThings Sigfox-Api.
 *
 * IngeniousThings Sigfox-Api is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * IngeniousThings Sigfox-Api is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 * -------------------------------------------------------------------------------
 * Author : Paul Pinault aka disk91
 * See https://www.disk91.com
 * ----
 * More information about IngeniousThings : https://www.ingeniousthings.fr
 * ----
 * Commercial license of this software can be obtained contacting ingeniousthings
 * -------------------------------------------------------------------------------
 */
package fr.ingeniousthings.sigfox_api;

import com.google.common.base.Predicates;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

/**
 * Default start : http://localhost:8080/swagger-ui.html
 */

@SpringBootApplication
@EnableSwagger2
public class SigfoxApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SigfoxApiApplication.class, args);
	}

	@Bean
	public Docket sigfoxApi() {
		List<ResponseMessage> globalResponses = newArrayList(
				new ResponseMessageBuilder()
						.code(500)
						.message("An unexpected error occured")
						.responseModel(new ModelRef("string"))
						.build(),
				new ResponseMessageBuilder()
						.code(400)
						.message("Bad request, some required parameters are missing or their value is not valid")
						.responseModel(new ModelRef("string"))
						.build(),
				new ResponseMessageBuilder()
						.code(401)
						.message("Authentication credentials were not present or invalid")
						.responseModel(new ModelRef("string"))
						.build(),
				new ResponseMessageBuilder()
						.code(403)
						.message("Access denied, the request is not authorized with your credentials or the action is forbidden owing to some constraints")
						.responseModel(new ModelRef("string"))
						.build(),
				new ResponseMessageBuilder()
						.code(403)
						.message("Resource was not found for the given request")
						.responseModel(new ModelRef("string"))
						.build()
		);

		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
//				.paths(PathSelectors.any())
				.paths(Predicates.or(
						regex("/api/.*")
				))
				.build()
//				.protocols(newHashSet("https"))
//				.host("backend.sigfox.com/api")
//				.pathMapping("http://www.voila.fr/")
//				.pathMapping("/api/")
//				.directModelSubstitute(LocalDate.class, String.class)
//				.genericModelSubstitutes(ResponseEntity.class)
				.alternateTypeRules(
						newRule(typeResolver.resolve(DeferredResult.class,
								typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
								typeResolver.resolve(WildcardType.class)))
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET,globalResponses)
				.globalResponseMessage(RequestMethod.POST,globalResponses)
				.globalResponseMessage(RequestMethod.DELETE,globalResponses)
				.securitySchemes(newArrayList(basicAuth()))
				.securityContexts(newArrayList(securityContext()))
//				.enableUrlTemplating(true)
//				.globalOperationParameters(
//						newArrayList(new ParameterBuilder()
//								.name("someGlobalParameter")
//								.description("Description of someGlobalParameter")
//								.modelRef(new ModelRef("string"))
//								.parameterType("query")
//								.required(true)
//								.build()))
//				.tags(new Tag("Sigfox Api", "Sigfox public API"))
//				.additionalModels(typeResolver.resolve(AdditionalModel.class))
				.apiInfo( new ApiInfo(
						"IngeniousThings - Sigfox backend API Proxy",
						"This proxy is allowing to use the Sigfox backend API in a user friendly environment with a WEB based user interface. " +
								"As Sigfox backend doesn't support CORS for your 'safety', we are proxyfing all your requests from one of our servers. " +
								"We do not store any of your information during this process but for your safety we recommend you renew your API credentials " +
								"when you finished to use our service. <br/><br/>" +
								"We will do our best to keep this API in sync with sigfox but we can have a delay to implement the new endpoint.<br/>" +
								"This proxy is an helper for builing your own software: the API is exactly the same as Sigfox One, only the hostname differs. " +
								"That said, you must never use this proxy in production but directly call sigfox API.<br/>" +
								"This service is public and free, please use it in respect of our server health. Take care of your credential and use this at your own risk, " +
								"we are not responsible of any mis-usage, data loss or damages on your account. If you start using this proxy, you accept any risks " +
								"and potential consequences. <br/><br/>" +
								"You need to have a Sigfox API Access to use this API",
						"V 2018-06-24",
						"",
						new Contact("Contact", "http://www.ingeniousthings.fr", "contact AT ingeniousthings.fr"),
						"Code under GPL",
						"https://github.com/ingeniousthings/sigfox-api-portal/blob/master/LICENSE",
						Collections.emptyList()
				))
				;
	}
	@Autowired
	private TypeResolver typeResolver;

	private BasicAuth basicAuth() {
		return new BasicAuth("basicAuth");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder()
				.securityReferences(defaultAuth())
				.forPaths(regex("/.*"))
				.build();
	}

	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope
				= new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return newArrayList(
				new SecurityReference("mykey", authorizationScopes));
	}

	@Bean
	SecurityConfiguration security() {
		return SecurityConfigurationBuilder.builder()
				.clientId("test-app-client-id")
				.clientSecret("test-app-client-secret")
				.realm("test-app-realm")
				.appName("test-app")
				.scopeSeparator(",")
				.additionalQueryStringParams(null)
				.useBasicAuthenticationWithAccessCodeGrant(false)
				.build();
	}

	@Bean
	UiConfiguration uiConfig() {
		return UiConfigurationBuilder.builder()
				.deepLinking(true)
				.displayOperationId(false)
				.defaultModelsExpandDepth(1)
				.defaultModelExpandDepth(1)
				.defaultModelRendering(ModelRendering.EXAMPLE)
				.displayRequestDuration(false)
				.docExpansion(DocExpansion.NONE)
				.filter(false)
				.maxDisplayedTags(null)
				.operationsSorter(OperationsSorter.ALPHA)
				.showExtensions(false)
				.tagsSorter(TagsSorter.ALPHA)
				.supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
				.validatorUrl(null)
				.build();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.exposedHeaders("Origin, Authorization, Content-Type");
			}
		};
	}

}
