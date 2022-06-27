package br.com.java_docker.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.java_docker.modelViews.HomeView;

@RestController
public class home_controller {

    @GetMapping("/")
    public HomeView home() {
        return new HomeView();
    }
}
