package com.dcb.Springboot.tutorial.service;

import com.dcb.Springboot.tutorial.entity.Department;
import com.dcb.Springboot.tutorial.error.DepartmentNotFoundException;
import com.dcb.Springboot.tutorial.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository deptRepository;
    @Override
    public Department saveDepartment(Department department) {
        return deptRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartmentList() {
        return deptRepository.findAll();
    }

    @Override
    public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        Optional<Department> deptId = deptRepository.findById(departmentId);
        if(!deptId.isPresent()) {
            throw new DepartmentNotFoundException("Department Not Available");
        }

        return  deptId.get();
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        deptRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartmentById(Long departmentId, Department department) {
    Department deptDB = deptRepository.findById(departmentId).get();
        if(Objects.nonNull(department.getDepartmentName()) &&   // if any of the properties are NULL / BLANK (no update reqd for them) then skip them
                !"".equalsIgnoreCase(department.getDepartmentName())) {
            deptDB.setDepartmentName(department.getDepartmentName());
        }
        if(Objects.nonNull(department.getDepartmentCode()) &&
                !"".equalsIgnoreCase(department.getDepartmentCode())) {
            deptDB.setDepartmentCode(department.getDepartmentCode());
        }

        if(Objects.nonNull(department.getDepartmentAddress()) &&
                !"".equalsIgnoreCase(department.getDepartmentAddress())) {
            deptDB.setDepartmentAddress(department.getDepartmentAddress());
        }

        return deptRepository.save(deptDB);
    }

    @Override
    public Department findByDepartmentName(String departmentName) {
        return deptRepository.findByDepartmentName(departmentName);
    }

    @Override
    public Department findByDepartmentNameIgnoreCase(String departmentName) {
        return deptRepository.findByDepartmentNameIgnoreCase(departmentName);
    }
}
