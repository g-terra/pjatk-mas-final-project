package pjatk.mas.finalproject.devicemanufactureapi.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.format.DateTimeFormatter;

@Configuration
public class DateFormatter {


    @Value("${API.date-format}")
    private String pattern;

    @Bean
    @Primary
    public DateTimeFormatter dateTimeFormatter(){
        return DateTimeFormatter.ofPattern(pattern);
    }
}
