package com.magicpost.model.dto;

import com.magicpost.model.Orders;
import com.magicpost.model.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Orders_ConsolidationPointDTO {
    private long id;
    private Orders order;
    private List<Type> typeList;
}
