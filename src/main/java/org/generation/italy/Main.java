package org.generation.italy;

import org.generation.italy.codeSchool.model.Course;
import org.generation.italy.codeSchool.model.data.abstractions.CourseRepository;
import org.generation.italy.codeSchool.model.data.implementations.InMemoryCourseRepository;
import org.generation.italy.codeSchool.model.data.exceptions.DataExceptions;

import java.util.Optional;

public class Main {
    public static void main(String[] args) throws DataExceptions {
        CourseRepository c = new InMemoryCourseRepository();
        Optional<Course> x = c.findById(22);
        Course def = x.orElse(new Course()); // se l'optional è pieno ritorna l'oggetto che trova, se è vuoto crea un nuovo oggetto Course

        if(x.isPresent()){ // controlla se nell'optional c'è un elemento
            Course course = x.get();
            System.out.println(course.getTitle());
        }
    }
}