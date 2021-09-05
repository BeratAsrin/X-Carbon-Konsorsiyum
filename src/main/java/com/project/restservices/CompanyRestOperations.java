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
    private String registerCompany(@RequestBody Company company){
        try {
            HibernateOperations.addNewObject(company);
            HibernateOperations.addNewObject(new FakeBank(company.getId(),1000));
        }catch (Exception error){
            error.getStackTrace();
            return "Company could not be registered.";
        }
        return "Company is Registered.";
    }

    @CrossOrigin
    @GetMapping("/get/{id}")
    private Company getCompanyById(@PathVariable Integer id){
        return (Company) HibernateOperations.getObjectById(new Company(), id);
    }

    @CrossOrigin
    @GetMapping("/getall")
    private List<?> getAllCompanies(){
        return HibernateOperations.getAll(new Company());
    }

    @CrossOrigin
    @DeleteMapping ("/delete/{id}")
    private void deleteCompanyById(@PathVariable Integer id){
        HibernateOperations.deleteBankAccount(new FakeBank(), id);
        HibernateOperations.deleteObjectById(new Company(), id);
    }

}
