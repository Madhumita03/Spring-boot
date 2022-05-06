package com.dcb.Springboot.tutorial.controller;

import com.dcb.Springboot.tutorial.entity.Department;
import com.dcb.Springboot.tutorial.error.DepartmentNotFoundException;
import com.dcb.Springboot.tutorial.service.DepartmentService;
import com.dcb.Springboot.tutorial.service.DepartmentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DepartmentController {

    @Autowired      // tells spring to transfer or wire the object of DepartmentService from its container to deptService reference
    private DepartmentService deptService;


    private final Logger log =
            LoggerFactory.getLogger(DepartmentController.class);

    @PostMapping("/departments")
    public Department saveDepartment(@Valid @RequestBody Department department){ // whatever json object from Request Body is received it has to be passed into Department object
        log.info("Inside saveDepartment of DepartmentController");
        return deptService.saveDepartment(department);//RequestBody will be validated for validation constraints given in Entity like departmentName
    }

    @GetMapping("/departments")
    public List<Department> fetchDepartmentList(){
        log.info("Inside fetchDepartmentList of DepartmentController");
        return deptService.fetchDepartmentList();
    }

    @GetMapping("/departments/{id}")
    public Department fetchDepartmentById(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException {
    return  deptService.fetchDepartmentById(departmentId);
    }

    @DeleteMapping ("/departments/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long departmentId){
        deptService.deleteDepartmentById(departmentId);
        return  "Department deleted";
    }

    @PutMapping("/departments/{id}")
    public Department updateDepartmentById(@PathVariable("id") Long departmentId, @RequestBody Department department){
        return  deptService.updateDepartmentById(departmentId, department);
    }

    @GetMapping("/departments/name/{name}")
    public Department fetchDepartmentByName(@PathVariable("name") String departmentName) {
        return deptService.findByDepartmentNameIgnoreCase(departmentName);
    }
}
