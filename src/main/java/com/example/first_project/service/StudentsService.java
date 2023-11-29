package com.example.first_project.service;

import com.example.first_project.model.Students;
import com.example.first_project.repository.StudentsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

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

    public Students getById(Long id){
        return studentsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(("No Such Student found with the id " + id)));
    }

    public Students updateName(Long id, Students students){
        Students students1 = studentsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No Such Student found with the id " + id));
        if (students1 != null){
            students1.setFirstname(students.getFirstname());
            students1.setLastname(students.getLastname());
            students1.setAge(students.getAge());
            students1.setGender(students.getGender());
            studentsRepository.save(students1);
        }
        return null;
    }

    public List<Students> search(String searchString) {
        return studentsRepository.searchStudents(searchString);
    }

    public void delete(Long id){
        Students students =studentsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No such student found with the id " + id));
        studentsRepository.delete(students);
    }
}
