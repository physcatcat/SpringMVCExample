package ru.ivanov.springmvc.dao;

import org.springframework.stereotype.Component;
import ru.ivanov.springmvc.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT = 0;
    private List<Person> people;
    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT, "Alina"));
        people.add(new Person(++PEOPLE_COUNT, "Pavel"));
        people.add(new Person(++PEOPLE_COUNT, "Misha"));
        people.add(new Person(++PEOPLE_COUNT, "Gleb"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(p -> p.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person person) {
        Person personToUpdate = show(id);
        personToUpdate.setName(person.getName());
    }

    public void delete(int id) {
        people.removeIf(p -> p.getId() == id);
    }
}
