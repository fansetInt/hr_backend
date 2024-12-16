package com.fanset.dms.assets.model;
//
import com.fanset.dms.user.model.User;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//
//import java.time.LocalDate;
//
//
//
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//public class Asset {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String name;
//    private String serialNumber;
//    private LocalDate dateGiven;
//    private LocalDate yearPurchase;
//    private int usefulLife;
//    private Double amountPurchased;
//    private Double depressionCost;
//    private Double totalDepression = 0.0;
//    private Double currentValue;
//
//    private String assetType ;
//    private String status;
//
//    @CreationTimestamp
//    private LocalDate createdAt;
//    @UpdateTimestamp
//    private LocalDate updatedAt;
//    @ManyToOne
//    private User createdBy;
//    @ManyToOne
//    private User updatedBy;
//    @ManyToOne
//    private User employee;
//
//
//    private String additionalName;
//    private String additionalDescription;
//    private Double additionalCost;
//
//    private String impairmentDescription;
//    private Double impairmentCost;
//}

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String serialNumber;
    private LocalDateTime yearPurchase;
    private int usefulLife;
    private Double amountPurchased;
    private LocalDate dateGiven;
    @ManyToOne
    @JoinColumn(name = "asset_type_id", nullable = false)
    private AssetType assetType;
    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private AssetStatus status;
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private User assignedTo; // Asset belongs to one user (employee)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;
    @ManyToOne
    @JoinColumn(name = "updated_by")
    private User updatedBy;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "asset", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Depreciation> depreciations;
    @OneToMany(mappedBy = "asset", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AdditionalCost> additionalCosts;
    @OneToMany(mappedBy = "asset", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Impairment> impairments;
}

