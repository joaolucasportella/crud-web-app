package dev.portella.crudwebapp.controller;

import dev.portella.crudwebapp.model.Customer;
import dev.portella.crudwebapp.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    private static final String REDIRECT = "redirect:/customer";
    private static final String FORM = "customer-form";
    private static final String LIST = "customer-list";
    private static final String MODEL = "customer";

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute(MODEL, customerService.findAll());
        return LIST;
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute(MODEL, new Customer());
        return FORM;
    }

    @PostMapping
    public String save(Customer customer) {
        customerService.save(customer);
        return REDIRECT;
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute(MODEL, customerService.findById(id).orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id)));
        return FORM;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        customerService.deleteById(id);
        return REDIRECT;
    }
}
