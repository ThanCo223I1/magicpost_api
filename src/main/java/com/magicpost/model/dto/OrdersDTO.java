package com.magicpost.model.dto;

import com.magicpost.model.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class OrdersDTO {
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
    private String weight;
    private TransactionPointDTO transactionPoint;
    private List<ConsolidationPointDTO> consolidationPoints;
    private Status status;

    public OrdersDTO(long id, String image, Date createOrder, Date endOrder,String nameSender, String nameReceiver, String phoneSender,
                     String phoneReceiver, String addressSender, String addressReceiver, String width, String height, String weight,
                     TransactionPointDTO transactionPoint, List<ConsolidationPointDTO> consolidationPoint, Status status) {
        this.id = id;
        this.image = image;
        this.createOrder = createOrder;
        this.endOrder = endOrder;
        this.nameSender = nameSender;
        this.nameReceiver = nameReceiver;
        this.phoneSender = phoneSender;
        this.phoneReceiver = phoneReceiver;
        this.addressSender = addressSender;
        this.addressReceiver = addressReceiver;
        this.width = width;
        this.height = height;
        this.weight = weight;
        this.transactionPoint = transactionPoint;
        this.consolidationPoints = consolidationPoint;
        this.status = status;
    }
}
