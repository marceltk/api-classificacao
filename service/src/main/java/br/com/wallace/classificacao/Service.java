package br.com.wallace.classificacao;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "br.com.wallace.classificacao")
public class Service {


    public static void main(String[] args) {
        SpringApplication.run(Service.class, args);
    }
}