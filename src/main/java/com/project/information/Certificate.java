package com.project.information;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "certificates")
public class Certificate {

    // sıradaki için tuple_finish_id'ye 1 eklenecek
    @Id
    @Column(name = "tuple_start_id")
    private long tuppleStartId;

    @Id
    @Column(name = "tuple_finish_id")
    private long tuppleFinishId;

    @Column(name = "owner_id")
    private long ownerId;

    @Column(name = "register_month")
    private int registerMonth;

    @Column(name = "register_year")
    private int registerYear;

    @Column(name = "expiration_month")
    private int expirationMonth;

    @Column(name = "expiration_year")
    private int expirationYear;

}
