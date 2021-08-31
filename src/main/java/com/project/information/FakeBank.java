package com.project.information;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "fake_bank")
public class FakeBank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_id")
    private int bankId;

    @Column(name = "owner_id")
    private int ownerId;

    @Column(name = "money")
    private int money;

    public FakeBank(int ownerId, int money) {
        this.ownerId = ownerId;
        this.money = money;
    }

}
