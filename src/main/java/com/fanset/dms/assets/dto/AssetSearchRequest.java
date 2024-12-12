package com.fanset.dms.assets.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
public class AssetSearchRequest {
    private int page = 0;
    private int size = 10;
    private String sortBy = "id";
    private String sortDirection = "asc";
    private String searchTerm;
    private String status;
    private String type;
    private LocalDate purchaseDateFrom;
    private LocalDate purchaseDateTo;
    private LocalDateTime lastUpdatedFrom = LocalDateTime.of(2000, 1, 1, 0, 0);;
    private LocalDateTime lastUpdatedTo = LocalDateTime.now();
    private Long createdBy;
    private Long updatedBy;

}

