package com.project.information;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "organization_name")
    private String organizationName;

    @Column(name = "tax_number")
    private String taxNumber;

    @Column(name = "mail")
    private String mail;

    @Column(name = "register_type")
    private String registerType; // Producer || Trader

    public Company(String organizationName, String taxNumber, String mail, String registerType) {
        this.organizationName = organizationName;
        this.taxNumber = taxNumber;
        this.mail = mail;
        this.registerType = registerType;
    }

}
