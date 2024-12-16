package com.fanset.dms.assets.model;

import lombok.*;

import jakarta.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AdditionalCost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "asset_id", nullable = false)
    private Asset asset;

    private String name; // E.g., Maintenance, Accessories
    private String description;
    private Double cost;
}
