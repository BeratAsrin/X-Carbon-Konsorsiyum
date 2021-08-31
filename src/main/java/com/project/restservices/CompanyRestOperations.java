package com.project.restservices;

import com.project.hibernate.HibernateOperations;
import com.project.information.Company;
import com.project.information.FakeBank;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyRestOperations {

    @CrossOrigin
    @PostMapping("/register")
    private void registerCompany(@RequestBody Company company){
        int lastPrimaryKey = HibernateOperations.getLastId(new Company());
        HibernateOperations.addNewObject(new FakeBank(lastPrimaryKey+1,1000));
        HibernateOperations.addNewObject(company);
    }

    @GetMapping("/get/{id}")
    private Company getCompanyById(@PathVariable Integer id){
        HibernateOperations.getLastId(new Company());
        return (Company) HibernateOperations.getObjectById(new Company(), id);
    }

    @GetMapping("/getall")
    private List<?> getAllCompanies(){
        return HibernateOperations.getAll(new Company());
    }

    @DeleteMapping ("/delete/{id}")
    private void deleteCompanyById(@PathVariable Integer id){
        HibernateOperations.deleteObjectById(new Company(), id);
    }

}
