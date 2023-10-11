package com.luv2code.springboot.thymeleafdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// that's it ... no need to write any code LOL!

    // Method to sort by last_name

    /*
    * Spring Data JPA repository will create the
    * query behind the scenes if the method has
    * this pattern in its name.
    *
    * Specifying what it will do and the purpose
    * in this case order by the last_name column
    * in ASC from the table.
    * */
    public List<Employee> findAllByOrderByLastNameAsc();
}
