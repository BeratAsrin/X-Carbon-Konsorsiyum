package com.project.information;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "companies")
public class Company {
    // TODO KAYIT TARIHI ALMAYI UNUTMA

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "organization_name")
    private String organizationName;

    @Column(name = "tax_number")
    private String taxNumber;

    @Column(name = "mail")
    private String mail;

    public Company(String organizationName, String taxNumber, String mail) {
        this.organizationName = organizationName;
        this.taxNumber = taxNumber;
        this.mail = mail;
    }
}
