package ca.mcgill.ecse321.TreePLE;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.NamingConventions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import ca.mcgill.ecse321.TreePLE.controller.configuration.AndroidProperties;
import ca.mcgill.ecse321.TreePLE.controller.configuration.WebFrontendProperties;
import ca.mcgill.ecse321.TreePLE.model.TreeManager;
import ca.mcgill.ecse321.TreePLE.persistence.PersistenceXStream;

@SpringBootApplication
public class TreePLESpringApplication extends SpringBootServletInitializer {
	@Autowired
	private AndroidProperties androidProperties;

	@Autowired
	private WebFrontendProperties webFrontendProperties;


	public static void main(String[] args) {
		SpringApplication.run(TreePLESpringApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		// Let the model matcher map corresponding fields by name
		modelMapper.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(AccessLevel.PRIVATE);
		modelMapper.getConfiguration().setSourceNamingConvention(NamingConventions.NONE)
				.setDestinationNamingConvention(NamingConventions.NONE);
		return modelMapper;
	}
	@Bean
	public TreeManager treeMan() {
		return PersistenceXStream.initializeModelManager(PersistenceXStream.getFilename());
		
	}	

	//TODO add a Bean to provide a registration manager
//	@Bean
//	public RegistrationManager regMan() {
//		return PersistenceXStream.initializeModelManager(PersistenceXStream.getFilename());
//		return null;
//	}
	
	
	// Enable CORS globally

	@Bean
	  public WebMvcConfigurer corsConfigurer() {
	    return new WebMvcConfigurerAdapter() {
	      @Override
	      public void addCorsMappings(CorsRegistry registry) {
	        String frontendUrl = "http://" + webFrontendProperties.getIp() + ":" + webFrontendProperties.getPort();
		String androidUrl = "http://" + androidProperties.getIp() + ":" + androidProperties.getPort();
		// For debug purposes, allow connecting  from localhost as well
		registry.addMapping("/**").allowedOrigins(frontendUrl, androidUrl, "http://localhost:8087", "http://127.0.0.1:8087");
	      }
	    };
	}}


