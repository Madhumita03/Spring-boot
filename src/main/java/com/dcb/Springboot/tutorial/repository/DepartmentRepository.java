package com.dcb.Springboot.tutorial.repository;

import com.dcb.Springboot.tutorial.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {// <Entity, primary key type>
    public Department findByDepartmentName(String departmentName);
    //@Query(value = "select distinct * from Department u where u.departmentName = ECE", nativeQuery = true)
    public Department findByDepartmentNameIgnoreCase(String departmentName);
}
