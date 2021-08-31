package com.project.restservices;

import com.project.hibernate.HibernateOperations;
import com.project.information.Individual;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/individual")
public class IndividualRestOperations {

    @PostMapping("/register")
    private void registerIndividual(@RequestBody Individual individual){
        HibernateOperations.addNewObject(individual);
    }

    @GetMapping("/get/{tckn}")
    private Individual getCompanyById(@PathVariable Long tckn){
        return (Individual)HibernateOperations.getObjectById(new Individual(), tckn);
    }

    @GetMapping("/getall")
    private List<?> getAllCompanies(){
        return HibernateOperations.getAll(new Individual());
    }

    @DeleteMapping("/delete/{tckn}")
    private void deleteCompanyById(@PathVariable Long tckn){
        HibernateOperations.deleteObjectById(new Individual(), tckn);
    }
}