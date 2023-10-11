package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	private EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService)
	{
		this.employeeService = employeeService;
	}

	// add mapping for "/list"
	@GetMapping("/list")
	public String listEmployees(Model theModel)
	{
		// Get the employees from DB
		List<Employee> employees = this.employeeService.findAll();

		// add to the spring model
		// >> The model attribute doesn't have to have
		//    the same name of the java variable <<
		theModel.addAttribute("employees", employees);

		// We have to define the dir where the file is.
		return "employees/list-employees";
	}

	@GetMapping("/show-form-add")
	public String showFormForAdd(Model model)
	{
		Employee employee = new Employee();

		/*
		* Adding an attribute to the model and named it
		* like employee that references the employee Class.
		* */
		model.addAttribute("employee", employee);

		return "employees/employee-form";
	}

	@GetMapping("/show-form-update")
	public String showFormUpdate(@RequestParam("employeeId") int id, Model model)
	{
		// Get the employee from the service
		Employee employee = employeeService.findById(id);

		// Set the employee in the model to populate the form
		model.addAttribute("employee", employee);

		// Send over to our form
		return "employees/employee-form";
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee employee)
	{
		// save the employee
		employeeService.save(employee);

		// Use a redirect to prevent duplicate submissions

		/*
		* This redirect to a Request Mapping
		* */
		return  "redirect:/employees/list";
	}
}









