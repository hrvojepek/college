package tvz.naprednaJava.rozi.AutoServis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tvz.naprednaJava.rozi.AutoServis.repository.EmployeeRepository;

/**
 * Created by Hrvoje
 */

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

}
