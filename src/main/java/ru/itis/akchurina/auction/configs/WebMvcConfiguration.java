package ru.itis.akchurina.auction.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.itis.akchurina.auction.converters.CloseableHttpResponseToDoubleConverter;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new CloseableHttpResponseToDoubleConverter());
    }
}