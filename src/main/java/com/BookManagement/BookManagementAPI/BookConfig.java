package com.BookManagement.BookManagementAPI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class BookConfig {


    @Bean
    public List<Book> books(){
        return new ArrayList<Book>();
    }

}
