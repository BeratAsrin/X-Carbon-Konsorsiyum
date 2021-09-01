package com.project.restservices;

import com.project.hibernate.HibernateOperations;
import com.project.information.Certificate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

@RestController
@RequestMapping("/certificate")
public class CertificateRestOperations {

    @PostMapping("/create")
    private void createNewCertificate(@RequestBody Certificate certificate){

        // TODO JSON ownerId, numberOfCertificates içerecek.
        // TODO TRY CATCH ILE "ERROR" DÖNMEYI KONTROL ET !!!
        // TODO owner_id doğru mu diye kontrol et !!!

        if(HibernateOperations.isTableEmpty(new Certificate()) == "EMPTY"){
            certificate.setTuppleStartId(1);
            certificate.setTuppleFinishId(certificate.getTuppleStartId() + certificate.getNumberOfCertificates() - 1);
        }
        else if(HibernateOperations.isTableEmpty(new Certificate()) == "NOT_EMPTY"){
            certificate.setTuppleStartId(HibernateOperations.getLastFinishId(new Certificate()) + 1);
            certificate.setTuppleFinishId(certificate.getTuppleStartId() + certificate.getNumberOfCertificates() - 1);
        }
        else{
        }

        LocalDate registerDate = LocalDate.now();
        certificate.setRegisterMonth(registerDate.getMonthValue());
        certificate.setExpirationMonth(registerDate.getMonthValue());
        certificate.setRegisterYear(registerDate.getYear());
        certificate.setExpirationYear(registerDate.getYear() + 1);

        HibernateOperations.addNewObject(certificate);

    }


}
