package com.project.information;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(CertificateCompositeKey.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "certificates")
public class Certificate {

    @Id
    @Column(name = "tuple_start_id")
    private long tupleStartId;

    @Id
    @Column(name = "tuple_finish_id")
    private long tupleFinishId;

    @Column(name = "owner_id")
    private int ownerId;

    @Column(name = "number_of_certificates")
    private long numberOfCertificates;

    @Column(name = "register_month")
    private int registerMonth;

    @Column(name = "register_year")
    private int registerYear;

    @Column(name = "expiration_month")
    private int expirationMonth;

    @Column(name = "expiration_year")
    private int expirationYear;

}

// TODO https://stackoverflow.com/questions/41143913/sql-jpa-multiple-columns-as-primary-key
class CertificateCompositeKey implements Serializable {

    private long tupleStartId;
    private long tupleFinishId;

}
