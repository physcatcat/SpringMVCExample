package ru.ivanov.springmvc.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.ivanov.springmvc.dao.PersonDAO;
import ru.ivanov.springmvc.models.Person;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {
    private PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        Optional<Person> dbPerson = personDAO.show(person.getEmail());
        if(dbPerson.isPresent() && dbPerson.get().getId() != person.getId()) {
            errors.rejectValue("email", "", "This email is already taken");
        }
    }
}
