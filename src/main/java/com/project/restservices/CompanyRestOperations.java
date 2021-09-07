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
            if(HibernateOperations.checkIfTaxNumberExists(company.getTaxNumber())){
                return "Tax number is already exists in database. Try another.";
            }
            HibernateOperations.addNewObject(company);
            HibernateOperations.addNewObject(new FakeBank(company.getId(),1000));
        }catch (Exception error){
            error.getStackTrace();
            return "Company could not be registered.";
        }
        return "Company is Registered.";
    }

    @CrossOrigin
    @GetMapping("/get/id={id}")
    private Company getCompanyById(@PathVariable Integer id){
        return (Company) HibernateOperations.getObjectById(new Company(), id);
    }

    @CrossOrigin
    @GetMapping("/get/taxnumber={taxNumber}")
    private Company getCompanyByTaxNumber(@PathVariable Long taxNumber){
        return HibernateOperations.getCompanyByTaxNumber(taxNumber);
    }

    @CrossOrigin
    @GetMapping("/get/name={name}")
    private Company getCompanyByName(@PathVariable String name){
        return HibernateOperations.getCompanyByName(name);
    }

    // Will be updated by getting first and last indexes
    @CrossOrigin
    @GetMapping("/getall")
    private List<?> getAllCompanies(){
        return HibernateOperations.getAll(new Company());
    }

    @CrossOrigin
    @DeleteMapping ("/delete/id={id}")
    private boolean deleteCompanyById(@PathVariable Integer id){
        boolean returnValue = false;
        try{
            HibernateOperations.deleteBankAccount(new FakeBank(), id);
            HibernateOperations.deleteObjectById(new Company(), id);
            returnValue = true;
        }
        catch (Exception error){
            error.getStackTrace();
        }
        return returnValue;
    }

    /* Not necessary
    @CrossOrigin
    @DeleteMapping ("/delete/name={name}")
    // localhost:8080/company/delete/name='aaaa' ==> format should be like this
    private void deleteCompanyByName(@PathVariable String name){
        Company company = HibernateOperations.getCompanyByName(name);
        HibernateOperations.deleteBankAccount(new FakeBank(), company.getId());
        HibernateOperations.deleteObjectById(new Company(), company.getId());
    }
    */
}
