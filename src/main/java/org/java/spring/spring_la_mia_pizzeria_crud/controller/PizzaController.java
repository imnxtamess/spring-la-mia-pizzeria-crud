package org.java.spring.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.java.spring.spring_la_mia_pizzeria_crud.model.Pizza;
import org.java.spring.spring_la_mia_pizzeria_crud.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

  @Autowired
  private PizzaRepository repository;

  @GetMapping
  public String index(Model model) {

    List<Pizza> pizzas = repository.findAll(); // & SELECT * FROM `pizzas` ==> list of objects with type pizza

    model.addAttribute("pizzas", pizzas);

    return "pizzas/index";
  }

}