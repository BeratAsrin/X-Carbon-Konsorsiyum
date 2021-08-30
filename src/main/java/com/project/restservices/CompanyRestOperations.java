package com.project.restservices;

import com.project.hibernate.HibernateOperations;
import com.project.information.Company;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyRestOperations {

    @PostMapping("/register")
    private void addNewCompany(@RequestBody Company company){
        HibernateOperations.addNewObject(company);
    }

    @GetMapping("/get/{id}")
    private Company getCompanyById(@PathVariable Integer id){
        // TODO EĞER ID YOK İSE NULL DONDURUYOR TRY CATCH YAP
        return (Company) HibernateOperations.getObjectById(new Company(), id.intValue());
    }

    @PostMapping("/delete/{id}")
    private void deleteCompanyById(@PathVariable Integer id){
        
    }


}
