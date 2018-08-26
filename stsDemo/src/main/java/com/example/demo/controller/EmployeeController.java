package com.example.demo.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.demo.dao.DepartmentDao;
import com.example.demo.dao.EmployeeDao;
import com.example.demo.entites.Department;
import com.example.demo.entites.Employee;


@Controller
public class EmployeeController {
	
	@Autowired
	 EmployeeDao  employeeDao;
	@Autowired
	 DepartmentDao  departmentDao;
	//查询所有员工，返回列表页面
	@GetMapping("/emps")   //@GetMapping为get请求
    public String list(Model model) {
		Collection<Employee> employees = employeeDao.getAll();
		//需要放在请求域中进行共享
		model.addAttribute("emps", employees);
    	//themyleaf就会默认拼串
    	return "emp/list";
    }
	
	//来到员工添加页面
	@GetMapping("/emp")
	public String toAddPage(Model model) {
		
		//来到添加页面 查出所有的部门在页面显示
		Collection<Department> departments = departmentDao.getDepartments();
		model.addAttribute("depts", departments);
		return "emp/add";
	}
	
	//员工添加
	//Springmvc将请求参数与对象属性一一绑定，请求参数的名字要和Javabean的名字相同
	@PostMapping("/emp")
	public String addEmp(Employee employee) {
		//添加完成 后应该来到员工列表
		//保存员工
		employeeDao.save(employee);
		//redirect：表示纯定向到列表页面
		//forward：表示转发到一个地址
		return "redirect:/emps";
	}
	
	/**
	 * 来到修改页面，查出相关的员工再进行修改 在页面回显
	 * @return
	 * @PathVariable 指的是路径变量
	 */
	@GetMapping("/emp/{id}")
	public String toEditPage(@PathVariable("id")Integer id, Model model) {
		
		Employee employee =	employeeDao.get(id);
		model.addAttribute("emp", employee);
		
		//页面要显示所有的部门列表
		Collection<Department> departments = departmentDao.getDepartments();
		model.addAttribute("depts", departments);
		//回显到编辑页面 （add页面是编辑和添加二合一）
		return "emp/add";
	}
	
	//员工修改
	@PutMapping("/emp")
	public String updateEmp(Employee employee) {
		
		employeeDao.save(employee);
		
		return "redirect:/emps";
	}
	
	//员工删除
	@DeleteMapping("/emp/{id}")
	public String deleteEmp(@PathVariable("id") Integer id) {
		employeeDao.delete(id);
	    return "redirect:/emps";
	}
}
