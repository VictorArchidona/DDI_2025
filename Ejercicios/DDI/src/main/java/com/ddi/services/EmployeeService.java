package com.ddi.services;

import com.ddi.exceptions.EmployeeNotFoundException;
import com.ddi.models.Employee;
import com.ddi.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    public Employee newEmployee(@RequestBody Employee newEmployee) {
        return repository.save(newEmployee);
    }

    // Se podria cambiar el optional por return Employee si pusiesemos una
    // exception creada por nosotros.
    public Employee getEmployee(@PathVariable Long id) throws EmployeeNotFoundException  {
        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id = " + id + " not found."));
        // return repository.findById(id)
        //      .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    return repository.save(newEmployee);
                });
    }

    public void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
