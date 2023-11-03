package com.magicpost.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`Orders`")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date createOrder;
    private String nameSender;
    private String nameReceiver;
    private String phoneSender;
    private String phoneReceiver;
    private String addressSender;
    private String addressReceiver;
    private double weight;
    @OneToOne
    private TransactionPoint transactionPoint;
    @OneToOne
    private ConsolidationPoint consolidationPoint;
    @ManyToOne
    private Status status;

}
