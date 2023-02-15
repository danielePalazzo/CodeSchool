package org.generation.italy.codeSchool.model.data.implementations;

import org.generation.italy.codeSchool.model.Course;
import org.generation.italy.codeSchool.model.data.abstractions.CourseRepository;
import org.generation.italy.codeSchool.model.data.exceptions.DataExceptions;
import org.generation.italy.codeSchool.model.data.exceptions.EntityNotFoundExceptions;

import javax.swing.text.html.Option;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CSVFileCourseRepository implements CourseRepository {
    private String fileName;

    public static final String DEFAULT_FILE_NAME = "corsi.csv";
    private static long nextId;

    public CSVFileCourseRepository() {
        fileName = DEFAULT_FILE_NAME;
    }

    public CSVFileCourseRepository(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Optional<Course> findById(long id) throws DataExceptions{
         try{
             List<String> lines = Files.readAllLines(Paths.get(fileName));
             for (String s: lines){
                 String splitted[] = s.split(",");
                 long courseId = Long.parseLong(splitted[0]);

                 if(courseId == id){
                     Course found = new Course(courseId, splitted[1], splitted[2], splitted[3],
                             Double.parseDouble(splitted[4]));
                     Optional<Course> result = Optional.of(found);
                     return result;
                 }
             }
             return Optional.empty();
         } catch (IOException e){
            throw new DataExceptions("Errore nella lettura del file", e);
         }
    }

    @Override
    public List<Course> findByTitleContains(String part) {
        return null;
    }

    @Override
    public Course create(Course course) throws DataExceptions{
        try(FileOutputStream output = new FileOutputStream(fileName, true); PrintWriter pw = new PrintWriter(output)){            nextId++;
            course.setId(nextId);
            String csvLine = courseToCSV(course);
            pw.println(csvLine);

            return course;

        } catch (IOException e) {
            throw new DataExceptions("Errore nel salvataggio sul file ", e);
        }
    }

    @Override
    public void update(Course course) throws EntityNotFoundExceptions {

    }

    @Override
    public void deleteById(long id) throws EntityNotFoundExceptions {

    }

    public String courseToCSV(Course c){
        return String.format("%d,%s,%s,%s,%f", c.getId(), c.getTitle(), c.getDescription(),
                c.getProgram(), c.getDuration());
    }
}
