package org.java.spring.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.java.spring.spring_la_mia_pizzeria_crud.model.Pizza;
import org.java.spring.spring_la_mia_pizzeria_crud.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

  @GetMapping("/{id}")
  public String show(@PathVariable Integer id, Model model) {

    Pizza pizza = repository.findById(id).get();

    model.addAttribute("pizza", pizza);

    return "pizzas/show";
  }

  @GetMapping("/search")
  public String searchByTitle(@RequestParam String name, Model model) {

    List<Pizza> pizzas = repository.findByNameContainingIgnoreCase(name);

    model.addAttribute("pizzas", pizzas);
    model.addAttribute("name", name);

    return "pizzas/index";
  }

  @GetMapping("/create")
  public String create(Model model) {

    model.addAttribute("pizza", new Pizza());

    return "pizzas/create";
  }

  @PostMapping("/create")
  public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {

    if (bindingResult.hasFieldErrors("price")) {
      bindingResult.rejectValue("price", "typeMismatch", "Price must be a valid number (e.g. 9.99 )");
    }

    if (bindingResult.hasErrors()) {

      return "pizzas/create";
    }

    repository.save(formPizza);

    return "redirect:/pizzas";
  }

  @GetMapping("/edit/{id}")
  public String edit(@PathVariable("id") Integer id, Model model) {

    model.addAttribute("pizza", repository.findById(id).get());

    return "pizzas/edit";
  }

  @PostMapping("/edit/{id}")
  public String update(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {

    if (bindingResult.hasErrors()) {
      return "pizzas/edit";
    }

    repository.save(formPizza);

    return "redirect:/pizzas";
  }

  @PostMapping("/delete/{id}")
  public String delete(@PathVariable("id") Integer id) {

    repository.deleteById(id);

    return "redirect:/pizzas";
  }

}