package com.example.first_project.controller;


import com.example.first_project.model.Students;
import com.example.first_project.service.StudentsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentsController {

    private final StudentsService studentsService;

    public StudentsController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }
    @PostMapping("/create")
    public Students create(@RequestBody Students students){
        return studentsService.create(students);
    }

    @GetMapping("/all")
    public List<Students> getAll(){
        return studentsService.getAll();
    }

    @GetMapping("/byid")
    public Students getById(Long sid){
        return studentsService.getById(sid);
    }

    @PutMapping("/updateName")
    public Students updateName(Long sid, Students students){
        return studentsService.updateName(sid, students);
    }

    @GetMapping("/search")
    public List<Students> search (String searchString){
        return studentsService.search(searchString);
    }

    @DeleteMapping("delete")
    public void delete(Long sid){
        studentsService.delete(sid);
    }


}
