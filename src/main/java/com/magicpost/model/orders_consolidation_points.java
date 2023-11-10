package com.magicpost.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class orders_consolidation_points {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Orders order;
    @ManyToOne
    @JoinColumn(name = "consolidation_points_id")
    private ConsolidationPoint consolidationPoint;
}
