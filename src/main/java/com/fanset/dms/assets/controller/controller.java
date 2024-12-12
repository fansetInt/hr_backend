package com.fanset.dms.assets.controller;


import com.fanset.dms.assets.dto.AssetRequestDto;
import com.fanset.dms.assets.dto.UpdateAssetRequestedDto;
import com.fanset.dms.assets.model.Asset;
import com.fanset.dms.assets.service.implement.AssetService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@RestController
@PreAuthorize("hasAnyRole('ADMIN', 'CRM', 'FINANCE', 'TECHNICIAN', 'TECHSUPPORT', 'SUPERVISOR')")
@RequestMapping("/api/v1/asset")
public class controller {
    private final AssetService assetService;

    public controller(AssetService assetService) {
        this.assetService = assetService;
    }


    @PostMapping
    public ResponseEntity<String> saveAsset(@Valid  @RequestBody AssetRequestDto assetRequestDto){
        System.out.printf("controller");
        return ResponseEntity.ok(assetService.saveAsset(assetRequestDto));
    }

    @GetMapping("/id")
    public ResponseEntity<Asset> getAssetById(
            @RequestParam Long assetId){
        return ResponseEntity.ok(assetService.getAssetById(assetId));
    }
    @PutMapping("/update")
    public ResponseEntity<String> updateAsset(
            @RequestParam Long assetId,
            @RequestBody UpdateAssetRequestedDto updateAssetRequestedDto
    ){
        return ResponseEntity.ok(assetService.updateAsset(assetId,updateAssetRequestedDto));

    }




    public ResponseEntity<Page<Asset>> getAllAsset(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(required = false) String searchTerm,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) LocalDate purchaseDateFrom,
            @RequestParam(required = false) LocalDate purchaseDateTo,
            @RequestParam(required = false) LocalDateTime lastUpdatedFrom,
            @RequestParam(required = false) LocalDateTime lastUpdatedTo,
            @RequestParam(required = false) String createdBy,
            @RequestParam(required = false) String updatedBy
    ) {
        // Ensure optional string parameters are set to empty if null
        searchTerm = (searchTerm == null || searchTerm.isBlank()) ? "" : searchTerm;
        status = (status == null || status.isBlank()) ? "" : status;
        type = (type == null || type.isBlank()) ? "" : type;
        createdBy = (createdBy == null || createdBy.isBlank()) ? "" : createdBy;
        updatedBy = (updatedBy == null || updatedBy.isBlank()) ? "" : updatedBy;

        // Provide default values for date/time parameters
        LocalDate defaultDate = LocalDate.of(2000, 1, 1);
        LocalDateTime defaultDateTime = LocalDateTime.of(2000, 1, 1, 0, 0);

        purchaseDateFrom = (purchaseDateFrom == null) ? defaultDate : purchaseDateFrom;
        purchaseDateTo = (purchaseDateTo == null) ? LocalDate.now() : purchaseDateTo;

        lastUpdatedFrom = (lastUpdatedFrom == null) ? defaultDateTime : lastUpdatedFrom;
        lastUpdatedTo = (lastUpdatedTo == null) ? LocalDateTime.now() : lastUpdatedTo;

        // Call the service and return the response
        return ResponseEntity.ok(
                assetService.getAllAsset(
                        pageNumber, pageSize, sortBy, direction, searchTerm, status, type,
                        purchaseDateFrom, purchaseDateTo, lastUpdatedFrom, lastUpdatedTo, createdBy, updatedBy
                )
        );
    }


}
