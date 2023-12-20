package tarea.jpa.swagger.springboot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import tarea.jpa.swagger.springboot.app.security.JWTAuthorizationFilter;

@SpringBootApplication
public class A902005CrmTareaJPASwaggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(A902005CrmTareaJPASwaggerApplication.class, args);
	}
	
	@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Club naútico")
                        .version("0.1")
                        .description("Gestión de un club náutico con base de datos"));
	}
	@EnableWebSecurity
	@Configuration
	public class SecurityConfiguration {
	         
	    @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

			http.cors().and()
			.csrf().disable()
				.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
			    .authorizeHttpRequests(authorize -> authorize
			      .requestMatchers(HttpMethod.POST, "/login").permitAll() // TODO: requestMatcher en vez de ant
			      .requestMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
				  .anyRequest().authenticated()
				);
			return http.build();
	    	
	     
	    }
	    @Bean
		public WebMvcConfigurer corsConfigurer() {
			return new WebMvcConfigurer() {
				@Override
				public void addCorsMappings(CorsRegistry registry) {
					registry.addMapping("/login")
					.allowedOrigins("http://localhost:4200")
					.allowedMethods("GET", "POST")
					.exposedHeaders("*")
					.allowCredentials(true);
					
					registry.addMapping("/api/**")
					.allowedOrigins("http://localhost:4200")
					.allowedMethods("GET", "POST", "PUT", "DELETE")
					.allowCredentials(true);
				}
			};
		}
	    /*
	    @Bean
		public WebMvcConfigurer corsConfigurer() {
			return new WebMvcConfigurer() {
				@Override
				public void addCorsMappings(CorsRegistry registry) {
					registry.addMapping("**")
					.allowedOrigins("http://localhost:4200")
					.allowedHeaders("POST", "Content-Type","X-Requested-With","accept","Origin",
							"Access-Control-Request-Method","Access-Control-Request-Headers")
					.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
					registry.addMapping("**")
					.allowedOrigins("http://localhost:4200")
					.allowedHeaders("GET", "Content-Type","X-Requested-With","accept","Origin",
							"Access-Control-Request-Method","Access-Control-Request-Headers")
					.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
		};
		};

		}*/
	         
	}
}
