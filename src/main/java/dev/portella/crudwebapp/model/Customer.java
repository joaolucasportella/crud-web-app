package dev.portella.crudwebapp.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import dev.portella.crudwebapp.validation.Unique;
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

    @NotBlank(message = "{customer.email.notBlank}")
    @Email(message = "{customer.email.invalid}")
    @Size(max = 50, message = "{customer.email.size}")
    @Column(nullable = false, length = 50, unique = true)
    @Unique(field = "email", message = "{customer.email.notUnique}")
    private String email;

    @NotBlank(message = "{customer.phone.notBlank}")
    @Pattern(regexp = "^\\d{10,15}$", message = "{customer.phone.pattern}")
    @Size(max = 15, message = "{customer.phone.size}")
    @Column(nullable = false, length = 15)
    private String phone;

    @NotBlank(message = "{customer.address.notBlank}")
    @Size(max = 255, message = "{customer.address.size}")
    @Column(nullable = false, length = 255)
    private String address;

    @NotBlank(message = "{customer.city.notBlank}")
    @Size(max = 50, message = "{customer.city.size}")
    @Column(nullable = false, length = 50)
    private String city;

    @NotBlank(message = "{customer.state.notBlank}")
    @Size(max = 50, message = "{customer.state.size}")
    @Column(nullable = false, length = 50)
    private String state;

    @NotBlank(message = "{customer.zipCode.notBlank}")
    @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "{customer.zipCode.pattern}")
    @Size(max = 10, message = "{customer.zipCode.size}")
    @Column(name = "zip_code", nullable = false, length = 10)
    private String zipCode;

    @NotBlank(message = "{customer.document.notBlank}")
    @Size(max = 20, message = "{customer.document.size}")
    @Column(nullable = false, length = 20, unique = true)
    @Unique(field = "document", message = "{customer.document.notUnique}")
    private String document;

    @NotNull(message = "{customer.birthDate.notNull}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}
