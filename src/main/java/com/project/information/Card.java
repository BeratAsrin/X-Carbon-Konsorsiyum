package com.project.information;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bank_accounts")
public class Card {

    @Id
    @Column(name = "card_id")
    private long cardId;

    @Column(name = "cvc")
    private int cvc;

    @Column(name = "day")
    private int day;

    @Column(name = "month")
    private int month;

    @Column(name = "year")
    private int year;

    @Column(name = "money")
    private int money;

}
