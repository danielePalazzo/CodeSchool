package org.generation.italy.codeSchool.model.data.abstractions;

import org.generation.italy.codeSchool.model.Course;
import org.generation.italy.codeSchool.model.data.exceptions.DataExceptions;
import org.generation.italy.codeSchool.model.data.exceptions.EntityNotFoundExceptions;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    Optional<Course> findById(long id) throws DataExceptions;

    List<Course> findByTitleContains(String part) throws DataExceptions;

    Course create (Course course) throws DataExceptions;

    void update(Course course) throws EntityNotFoundExceptions, DataExceptions;

    void deleteById(long id) throws EntityNotFoundExceptions, DataExceptions;
}
