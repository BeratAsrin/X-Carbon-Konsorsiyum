package com.project.information;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "certificate_requests")
public class CertificateCreateRequest {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "owner_tax_number")
    private long ownerTaxNumber;

    @Column(name = "number_of_certificates")
    private int numberOfCertificates;

}
