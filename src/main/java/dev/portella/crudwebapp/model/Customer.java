package dev.portella.crudwebapp.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import dev.portella.crudwebapp.validation.UniqueCustomer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@UniqueCustomer
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{customer.firstName.notBlank}")
    @Size(max = 25, message = "{customer.firstName.size}")
    @Column(name = "first_name", nullable = false, length = 25)
    private String firstName;

    @NotBlank(message = "{customer.lastName.notBlank}")
    @Size(max = 25, message = "{customer.lastName.size}")
    @Column(name = "last_name", nullable = false, length = 25)
    private String lastName;

    @NotBlank(message = "{customer.cpf.notBlank}")
    @Pattern(regexp = "^\\d{11}$", message = "{customer.cpf.pattern}")
    @Column(nullable = false, length = 11, unique = true)
    private String cpf;

    @NotNull(message = "{customer.birthDate.notNull}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @NotBlank(message = "{customer.email.notBlank}")
    @Size(max = 320, message = "{customer.email.size}")
    @Email(message = "{customer.email.invalid}")
    @Column(nullable = false, length = 320, unique = true)
    private String email;

    @NotBlank(message = "{customer.phone.notBlank}")
    @Pattern(regexp = "^\\d{11}$", message = "{customer.phone.pattern}")
    @Column(nullable = false, length = 11)
    private String phone;

    @NotBlank(message = "{customer.cep.notBlank}")
    @Pattern(regexp = "^\\d{8}$", message = "{customer.cep.pattern}")
    @Column(nullable = false, length = 8)
    private String cep;

    @NotBlank(message = "{customer.state.notBlank}")
    @Size(max = 2, message = "{customer.state.size}")
    @Column(nullable = false, length = 2)
    private String state;

    @NotBlank(message = "{customer.city.notBlank}")
    @Size(max = 50, message = "{customer.city.size}")
    @Column(nullable = false, length = 50)
    private String city;

    @NotBlank(message = "{customer.address.notBlank}")
    @Size(max = 255, message = "{customer.address.size}")
    @Column(nullable = false, length = 255)
    private String address;

    @Column(name = "registration_date", nullable = false, updatable = false, insertable = false)
    private LocalDate registrationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}
