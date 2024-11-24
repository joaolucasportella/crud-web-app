package dev.portella.crudwebapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.portella.crudwebapp.model.Customer;
import dev.portella.crudwebapp.service.CustomerService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    private static final String REDIRECT = "redirect:/customer";
    private static final String FORM = "customer-form";
    private static final String LIST = "customer-list";
    private static final String SEARCH = "customer-search";
    private static final String MODEL = "customer";

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String list(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size,
            Model model) {

        if (size > 20 || size < 1) {
            size = 15;
        }

        Page<Customer> customerPage = customerService.findPaginated(page, size);

        model.addAttribute(MODEL, customerPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", customerPage.getTotalPages());
        model.addAttribute("hasNextPage", customerPage.hasNext());
        return LIST;
    }

    @GetMapping("/search")
    public String searchById(@RequestParam String id, Model model) {
        if (id == null || id.isEmpty()) {
            return REDIRECT;
        }

        Optional<Customer> customer = customerService.findById(Long.parseLong(id));

        if (customer.isEmpty()) {
            model.addAttribute("errorMessage", "customer.notFound");
            return SEARCH;
        }

        model.addAttribute(MODEL, List.of(customer.get()));
        return SEARCH;
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute(MODEL, new Customer());
        return FORM;
    }

    @PostMapping
    public String save(@Valid @ModelAttribute Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            return FORM;
        }
        customerService.save(customer);
        return REDIRECT;
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute(MODEL,
                customerService.findById(id).orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id)));
        return FORM;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        customerService.deleteById(id);
        return REDIRECT;
    }
}
