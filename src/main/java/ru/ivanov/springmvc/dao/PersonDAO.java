package ru.ivanov.springmvc.dao;

import org.springframework.stereotype.Component;
import ru.ivanov.springmvc.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int size = 0;
    private List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(++size, "Alina"));
        people.add(new Person(++size, "Pavel"));
        people.add(new Person(++size, "Misha"));
        people.add(new Person(++size, "Gleb"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(p -> p.getId() == id).findAny().orElse(null);
    }
}
