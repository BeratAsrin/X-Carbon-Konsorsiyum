package com.project.restservices;

import com.project.hibernate.HibernateOperations;
import com.project.information.Certificate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.archive.spi.AbstractArchiveDescriptor;
import org.hibernate.cfg.Configuration;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

@RestController
@RequestMapping("/certificate")
public class CertificateRestOperations {

    @PostMapping("/create")
    private void createNewCertificate(@RequestBody Certificate certificate){

        /* TODO JSON ownerId, numberOfCertificates içerecek. ownerID frontend yapıldıktan sonra kaldırılacak ve
            login ekranından alınacak.
        */
        // TODO TRY CATCH ILE "ERROR" DÖNMEYI KONTROL ET !!!
        // TODO owner_id doğru mu diye kontrol et !!!

        if(HibernateOperations.isTableEmpty(new Certificate()) == "EMPTY"){
            certificate.setTupleStartId(1);
            certificate.setTupleFinishId(certificate.getTupleStartId() + certificate.getNumberOfCertificates() - 1);
        }
        else if(HibernateOperations.isTableEmpty(new Certificate()) == "NOT_EMPTY"){
            certificate.setTupleStartId(HibernateOperations.getLastFinishId(new Certificate()) + 1);
            certificate.setTupleFinishId(certificate.getTupleStartId() + certificate.getNumberOfCertificates() - 1);
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

    @GetMapping("/transfer/{from},{to},{tupleStartId},{transferAmount}")
    private Certificate transferCertificate(@PathVariable int from,
                                     @PathVariable int to,
                                     @PathVariable Long tupleStartId,
                                     @PathVariable Long transferAmount){

        HibernateOperations.initSessionFactory(new Certificate());
        Certificate fromTuple = HibernateOperations.getCertificate(from, tupleStartId);

        if(transferAmount > fromTuple.getNumberOfCertificates()){

        }
        else if(transferAmount == fromTuple.getNumberOfCertificates()){
            fromTuple.setOwnerId(to);
            HibernateOperations.sessionCommit();
        }
        HibernateOperations.closeSessionFactory();
        return fromTuple;
    }


}
