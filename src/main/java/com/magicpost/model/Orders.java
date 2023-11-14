package com.magicpost.model;

import com.magicpost.model.dto.ConsolidationPointDTO;
import com.magicpost.model.dto.OrdersDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint 0")
    private long id;
    private String image;
    private Date createOrder;
    private Date endOrder;
    private String nameSender;
    private String nameReceiver;
    private String phoneSender;
    private String phoneReceiver;
    private String addressSender;
    private String addressReceiver;
    private String width;
    private String height;
    private double weight;
    @OneToOne
    private TransactionPoint transactionPoint;
    @ManyToMany
    @JoinTable(
            name = "orders_consolidation_points",
            joinColumns = @JoinColumn(name = "orders_id"),
            inverseJoinColumns = @JoinColumn(name = "consolidation_points_id"))
    private List<ConsolidationPoint> consolidationPoints;
    @ManyToOne
    private Status status;

    public OrdersDTO orderDTO() {
        List<ConsolidationPointDTO> consolidationPointDTOS = new ArrayList<>();
        for (ConsolidationPoint c : this.consolidationPoints) {
            consolidationPointDTOS.add(c.noEmployeeConsolidationPointDTO());

        }
        return new OrdersDTO(this.id, this.image, this.createOrder, this.nameSender, this.nameReceiver, this.phoneSender, this.phoneReceiver,
                this.addressSender, this.addressReceiver, this.width, this.height, this.weight,
                this.transactionPoint.noEmployeeTransactionPointDTO(), consolidationPointDTOS, this.status);
    }


}
