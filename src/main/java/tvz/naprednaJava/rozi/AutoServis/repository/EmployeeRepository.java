package tvz.naprednaJava.rozi.AutoServis.repository;

import org.springframework.data.repository.CrudRepository;
import tvz.naprednaJava.rozi.AutoServis.model.Employee;

/**
 * Created by Hrvoje
 */
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
