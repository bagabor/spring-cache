package com.example.cachetest.resource;

import java.util.Optional;

import com.example.cachetest.dao.model.Student;
import com.example.cachetest.resource.dto.StudentDto;
import com.example.cachetest.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StudentResource {

    private final StudentService studentService;

    @GetMapping(path = "/student/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable("id") Long id) throws InterruptedException {
        final Optional<Student> optionalStudentstudent = studentService.getStudentById(id);
        if (optionalStudentstudent.get() != null) {
            Student student = optionalStudentstudent.get();
            return ResponseEntity.ok(StudentDto.builder().id(student.getId()).name(student.getName()).age(student.getAge()).build());
        }
        return ResponseEntity.notFound().build();
    }
}
