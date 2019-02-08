package cz.tslavik.kudos.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan({"cz.tslavik.Kudosservice"})
@EnableWebMvc
public class KudosServiceConfiguration {

    /**c
     * Used for mapping persistent entity to DTO and backwards.
     * @return
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        //builder.additionalMessageConverters(new MappingJackson2HttpMessageConverter(),new FormHttpMessageConverter());
        return builder.build();
    }
}
