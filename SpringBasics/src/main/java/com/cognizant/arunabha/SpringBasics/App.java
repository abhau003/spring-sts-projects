package com.cognizant.arunabha.SpringBasics;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
    public static void main(String[] args){
        ApplicationContext cx = new ClassPathXmlApplicationContext("config.xml");
        Movie movie = (Movie) cx.getBean("movie");
        System.out.println("Movie Name: " + movie.getCity());
        System.out.println("Movie Genre: " +movie.getGenre());
        ((ClassPathXmlApplicationContext) cx).close();
    }
}
