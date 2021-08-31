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
        HibernateOperations.addNewObject(company);
        HibernateOperations.addNewObject(new FakeBank(company.getId(),1000));
    }

    @GetMapping("/get/{id}")
    private Company getCompanyById(@PathVariable Integer id){
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
