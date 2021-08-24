package com.holun.controller;

import com.holun.dao.EmployeeDao;
import com.holun.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeDao employeeDao;

    @RequestMapping("/admin_employee_list")
    public String list(Model model) {
        Collection<Employee> employees = employeeDao.getEmployees();
        model.addAttribute("employees", employees);

        return "employee/list";
    }

    @PostMapping("/admin_employee_add")
    public String addEmployee(Employee employee) {
        employeeDao.addEmployee(employee);

        return "redirect:admin_employee_list";
    }

    @PostMapping("/admin_employee_update")
    public String updateEmployee(Employee employee) {
        employeeDao.updateEmployee(employee);

        return "redirect:admin_employee_list";
    }

    @RequestMapping("/admin_employee_delete/{id}")
    public String deleteEmployee(@PathVariable("id") int id) {
        employeeDao.deleteEmployeeById(id);

        return "redirect:/admin_employee_list";
    }
}
