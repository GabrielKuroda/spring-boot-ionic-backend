package com.estudos.springudemy.config;

import com.estudos.springudemy.services.DBService;
import com.estudos.springudemy.services.EmailService;
import com.estudos.springudemy.services.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String stratagy;

    @Bean
    public boolean instantiateDatabase() throws ParseException {

        if(!"create".equals(stratagy)){
            return false;
        }

        dbService.instantiateDatabase();
        return true;
    }

    @Bean
    public EmailService emailService(){
        return new SmtpEmailService();
    }

}
