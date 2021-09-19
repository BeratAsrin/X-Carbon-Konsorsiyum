package com.project.restservices;

import com.project.hibernate.HibernateOperations;
import com.project.information.Certificate;
import com.project.information.CertificateCreateRequest;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/certificate")
public class CertificateRestOperations {

    @CrossOrigin
    @PostMapping("/create")
    private void createNewCertificate(@RequestBody Certificate certificate){

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

    @CrossOrigin
    @PostMapping("/transfer/{from},{to},{tupleStartId},{transferAmount}")
    private void transferCertificate(@PathVariable int from,
                                     @PathVariable int to,
                                     @PathVariable Long tupleStartId,
                                     @PathVariable Long transferAmount){

        /*
            TODO MARKET ORTAMI GELDIKTEN SONRA MARKET DATABASE ILE BAGLANTI KURULACAK !!!
            O DURUMDA MARKET TEKLIF OBJESI ALINABILIR.
        */
        HibernateOperations.initSessionFactory(new Certificate());
        Certificate fromTuple = HibernateOperations.getCertificate(from, tupleStartId);

        if(transferAmount > fromTuple.getNumberOfCertificates()){
            // TODO throw exception
        }
        else if(transferAmount == fromTuple.getNumberOfCertificates()){

            fromTuple.setOwnerId(to);
            HibernateOperations.sessionCommit();

        }
        else if(transferAmount < fromTuple.getNumberOfCertificates()){

            HibernateOperations.deleteObjectWithoutClosingFactory(fromTuple);
            HibernateOperations.beginTransactionAgainUsingCurrentSession();

            fromTuple.setTupleFinishId(fromTuple.getTupleFinishId() - transferAmount);
            fromTuple.setNumberOfCertificates(fromTuple.getNumberOfCertificates() - transferAmount);
            HibernateOperations.addNewObjectWithoutChangingCurrentSession(fromTuple);

            Certificate toTuple = new Certificate();

            toTuple.setTupleStartId(fromTuple.getTupleFinishId() + 1);
            toTuple.setTupleFinishId(toTuple.getTupleStartId() + transferAmount - 1);
            toTuple.setOwnerId(to);
            toTuple.setNumberOfCertificates(transferAmount);
            toTuple.setRegisterMonth(fromTuple.getRegisterMonth());
            toTuple.setRegisterYear(fromTuple.getRegisterYear());
            toTuple.setExpirationMonth(fromTuple.getExpirationMonth());
            toTuple.setExpirationYear(fromTuple.getExpirationYear());

            HibernateOperations.addNewObjectWithoutChangingCurrentSession(toTuple);

            HibernateOperations.sessionCommit();

        }
        HibernateOperations.closeSessionFactory();
    }

    @CrossOrigin
    @GetMapping("/get/taxnumber={taxNumber}")
    private List<Certificate> getCertificatesUsingTaxNumber(@PathVariable Long taxNumber){
        return HibernateOperations.getAllCertificatesOfCompany(taxNumber);

    }

    @CrossOrigin
    @PostMapping("/request")
    private boolean createCertificateRequest(@RequestBody CertificateCreateRequest request){
        try {
            HibernateOperations.addNewObject(request);
        }catch (Exception error){
            error.getStackTrace();
            return false;
        }
        return true;
    }
}
