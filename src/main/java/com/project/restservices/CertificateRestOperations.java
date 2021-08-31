package com.project.restservices;

import com.project.information.Certificate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/certificate")
public class CertificateRestOperations {

    @PostMapping("/create")
    private void createNewCertificate(@RequestBody Certificate certificate){
        
    }

}
