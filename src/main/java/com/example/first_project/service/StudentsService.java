package com.example.first_project.service;

import com.example.first_project.model.Gender;
import com.example.first_project.model.Students;
import com.example.first_project.repository.StudentsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class StudentsService {

    private final StudentsRepository studentsRepository;

    public StudentsService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    public Students create(Students students){
        return studentsRepository.save(students);
    }

    public List<Students> getAll(){
        return studentsRepository.findAll();
    }

    public Students getById(Long sid){
        return studentsRepository.findById(sid).orElseThrow(() -> new EntityNotFoundException(("No Such Student found with the id " + sid)));
    }

    public Students updateName(Long sid, Students students){
        Students students1 = studentsRepository.findById(sid).orElseThrow(() -> new EntityNotFoundException("No Such Student found with the id " + sid));
        if (students1 != null){
            students1.setFirstname(students.getFirstname());
            students1.setLastname(students.getLastname());
            students1.setEmail(students.getEmail());
            students1.setAge(students.getAge());
            students1.setGender(students.getGender());
            studentsRepository.save(students1);
        }
        return null;
    }

    public List<Students> search(String searchString) {
        return studentsRepository.searchStudents(searchString);
    }

    public void delete(Long sid){
        Students students =studentsRepository.findById(sid).orElseThrow(() -> new EntityNotFoundException("No such student found with the id " + sid));
        studentsRepository.delete(students);
    }

    @PostConstruct
   public void initStudent() {
        Students s = studentsRepository.findByFirstname("Minale");
        if (s == null) {

            Students students = new Students();
            students.setAge((long) 10);
            students.setFirstname("Minale");
            students.setLastname("Fete");
            students.setGender(Gender.MALE);
            studentsRepository.save(students);
        }
    }
}
