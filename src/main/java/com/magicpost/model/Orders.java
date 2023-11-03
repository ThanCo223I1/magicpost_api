package com.magicpost.model;

import com.magicpost.model.dto.OrdersDTO;
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
    private String image;
    private Date createOrder;
    private String nameSender;
    private String nameReceiver;
    private String phoneSender;
    private String phoneReceiver;
    private String addressSender;
    private String addressReceiver;
    private String width;
    private String height;
    private double weight;
    @ManyToOne
    private Employee employee;
    @OneToOne
    private TransactionPoint transactionPoint;
    @OneToOne
    private ConsolidationPoint consolidationPoint;
    @ManyToOne
    private Status status;
    @ManyToOne
    private Status statusMagicPost;

    public OrdersDTO orderDTO() {
        return new OrdersDTO(this.id, this.image, this.createOrder, this.nameSender, this.nameReceiver, this.phoneSender, this.phoneReceiver,
                this.addressSender, this.addressReceiver, this.width, this.height, this.weight, this.employee.employeeDTO(),
                this.transactionPoint.noEmployeeTransactionPointDTO(), this.consolidationPoint.noEmployeeConsolidationPointDTO(), this.status, this.statusMagicPost);
    }

}
