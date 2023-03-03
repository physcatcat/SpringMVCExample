package ru.ivanov.springmvc.models;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private int id;

    @NotEmpty(message = "Name couldn't be empty")
    @Size(min = 2, max = 30, message = "Name length couldn't be greater than 30 or less than 2 characters")
    private String name;

    @Min(value = 0, message = "Age couldn't be negative")
    @Max(value = 150, message = "Age couldn't be greater than 150 years")
    private int age;

    @NotEmpty(message = "Email couldn't be empty")
    @Email(message = "Incorrect email address")
    private String email;
}
