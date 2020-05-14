package com.demo.onlinebookstore.business;

import com.demo.onlinebookstore.entity.Employee;
import com.demo.onlinebookstore.repository.EmployeeRepository;
import com.demo.onlinebookstore.request.EmployeeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Employee getById(UUID id) {
        return employeeRepository.findById(id).get();
    }

    @Transactional
    public Employee create(EmployeeRequest employeeRequest) {
        Employee employee = Employee.builder()
                .firstName(employeeRequest.getFirstName())
                .lastName(employeeRequest.getLastName())
                .emailId(employeeRequest.getEmailId())
                .active(true).build();
        return employeeRepository.save(employee);
    }

    @Transactional
    public void update(UUID id, EmployeeRequest employeeRequest) {
        Optional<Employee> optional = Optional.ofNullable(employeeRepository.getOne(id));
        if(!optional.isPresent())
            throw new NoSuchElementException("Employee does not exist");
        Employee employee = optional.get();
        employee.setFirstName(employeeRequest.getFirstName());
        employee.setLastName(employeeRequest.getLastName());
        employee.setEmailId(employeeRequest.getEmailId());
        employeeRepository.save(employee);
    }

    @Transactional
    public void delete(UUID id) {
        Optional<Employee> optional = Optional.ofNullable(employeeRepository.getOne(id));
        if(!optional.isPresent())
            throw new NoSuchElementException("Employee does not exist");
        Employee employee = optional.get();
        employee.setActive(false);
        employeeRepository.save(employee);
    }
}
