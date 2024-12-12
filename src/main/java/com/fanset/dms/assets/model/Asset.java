package com.fanset.dms.assets.model;

import com.fanset.dms.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String serialNumber;
    private LocalDate dateGiven;
    private LocalDate yearPurchase;
    private int usefulLife;
    private Double amountPurchased;
    private Double depressionCost;
    private Double totalDepression = 0.0;
    private Double currentValue;

    private String assetType ;
    private String status;

    @CreationTimestamp
    private LocalDate createdAt;
    @UpdateTimestamp
    private LocalDate updatedAt;
    @ManyToOne
    private User createdBy;
    @ManyToOne
    private User updatedBy;
    @ManyToOne
    private User employee;

}
