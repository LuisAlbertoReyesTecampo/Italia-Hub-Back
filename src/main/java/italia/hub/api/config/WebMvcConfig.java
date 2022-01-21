package italia.hub.api.config;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler("/404/**")
            .addResourceLocations("classpath:/public/error/clouds-404/");
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		log.info("addInterceptors");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		/**/
		log.info("Disabling CORS");
		registry.addMapping("/**")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS",
						"HEAD");
		/**/
	}

}