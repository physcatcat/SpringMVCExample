package ru.ivanov.springmvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.ivanov.springmvc.models.Person;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Optional<Person> show(String email) {
        return jdbcTemplate.query("select * from person where email=?", new BeanPropertyRowMapper<>(Person.class), email)
                .stream()
                .findAny();
    }
    public Person show(int id) {
        return jdbcTemplate.query("select * from person where id = ?", new BeanPropertyRowMapper<>(Person.class), id)
                .stream()
                .findAny()
                .orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("insert into person(name, age, email) values (?, ?, ?)",
                person.getName(),
                person.getAge(),
                person.getEmail()
        );
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("update person set name=?, age=?, email=? where id=?",
                updatedPerson.getName(),
                updatedPerson.getAge(),
                updatedPerson.getEmail(),
                updatedPerson.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from person where id=?", id);
    }
}
