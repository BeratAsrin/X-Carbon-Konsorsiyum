package com.project.restservices;

import com.project.hibernate.HibernateOperations;
import com.project.information.Admin;
import com.project.information.Company;
import com.project.information.Login;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginRestOperations {

    @CrossOrigin
    @PostMapping("/admin")
    private boolean adminLoginCheck(@RequestBody Login who){

        try {
            Admin admin = HibernateOperations.getAdminByUsername(who.getUsername());
            if(admin.getPassword().equals(who.getPassword())){
                return true;
            }
        }
        catch (Exception error){
            error.getStackTrace();
        }

        return false;
    }

    @CrossOrigin
    @PostMapping("/organization")
    private boolean organizationLoginCheck(@RequestBody Login who){

        try {
            Company company = HibernateOperations.getCompanyByTaxNumber(Long.parseLong(who.getUsername()));
            if(company.getPassword().equals(who.getPassword())){
                return true;
            }
        }
        catch (Exception error){
            error.getStackTrace();
        }

        return false;
    }
}
