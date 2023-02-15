package org.generation.italy.codeSchool.model.data.implementations;

import org.generation.italy.codeSchool.model.Course;
import org.generation.italy.codeSchool.model.data.abstractions.CourseRepository;
import org.generation.italy.codeSchool.model.data.exceptions.EntityNotFoundExceptions;

import java.util.*;

public class InMemoryCourseRepository implements CourseRepository {

    private static Map<Long, Course> dataSource = new HashMap<>(); // Map <chiave, valore>
    private static long nextId;

    @Override
    public Optional<Course> findById(long id) {
        Course x = dataSource.get(id);
        if(x != null){
            return Optional.of(x); // se è pieno ritorna l'oggetto
        }
        return Optional.empty(); // se è vuoto ritorna vuoto
    }

    @Override
    public List<Course> findByTitleContains(String part) {
        Collection<Course> cs = dataSource.values();
        List<Course> result = new ArrayList<>();
        for (Course c : cs){
            if(c.getTitle().contains(part)){
                result.add(c);
            }
        }
        return result;
    }

    @Override
    public Course create(Course course) {
        nextId++;
        dataSource.put(nextId, course);
        course.setId(nextId);
        return course;
    }

    @Override
    public void update(Course course) throws EntityNotFoundExceptions {
        if(dataSource.containsKey(course.getId())){
            dataSource.put(course.getId(), course);
        } else{
//            EntityNotFoundExceptions e = new EntityNotFoundExceptions("Non esiste un corso con id " + course.getId());
//            throw e;
            throw new EntityNotFoundExceptions("Non esiste un corso con id " + course.getId());
        }
        return;
    }

    @Override
    public void deleteById(long id) throws EntityNotFoundExceptions{
        Course old = dataSource.remove(id);
        if(old == null){
            throw new EntityNotFoundExceptions("Non esiste un corso con id " + id);
        }
    }
}
