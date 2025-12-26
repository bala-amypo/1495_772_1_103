package com.example.demo.service;

import com.example.demo.model.Department;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {

    Department create(Department department);

    Department get(Long id);

    void delete(Long id);

    List<Department> getAll();
}
