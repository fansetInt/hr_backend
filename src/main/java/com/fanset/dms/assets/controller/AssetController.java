package com.fanset.dms.assets.controller;


import com.fanset.dms.assets.dto.AssetRequestDto;
import com.fanset.dms.assets.dto.AssetSearchRequest;
import com.fanset.dms.assets.dto.UpdateAssetRequestedDto;
import com.fanset.dms.assets.model.Asset;
import com.fanset.dms.assets.service.implement.AssetService;
import com.fanset.dms.utils.CurrentUserUtil;
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
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }


    @PostMapping
    public ResponseEntity<String> saveAsset(@Valid  @RequestBody AssetRequestDto assetRequestDto){
        System.out.printf("controller");
        return ResponseEntity.ok(
                assetService.saveAsset(
                        assetRequestDto,
                        CurrentUserUtil.getCurrentUser()
                ));
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
        return ResponseEntity.ok(assetService.updateAsset(
                assetId,
                updateAssetRequestedDto,
                CurrentUserUtil.getCurrentUser()));
    }
    // Example of using Pageable for pagination and sorting
    @GetMapping
    public ResponseEntity<Page<Asset>> getAllAsset(
            @RequestBody AssetSearchRequest request
    ) {
        Page<Asset> assets = assetService.getAllAsset(
                request.getPage(),
                request.getSize(),
                request.getSortBy(),
                request.getSortDirection(),
                request.getSearchTerm(),
                request.getStatus(),
                request.getType(),
                request.getPurchaseDateFrom(),
                request.getPurchaseDateTo(),
                request.getLastUpdatedFrom(),
                request.getLastUpdatedTo(),
                request.getCreatedBy(),
                request.getUpdatedBy()
        );

        return ResponseEntity.ok(assets);
    }



    @PostMapping("/search")
    public ResponseEntity<Page<Asset>> searchAssets(@RequestBody AssetSearchRequest request) {
        Page<Asset> assets = assetService.getAllAsset(
                request.getPage(),
                request.getSize(),
                request.getSortBy(),
                request.getSortDirection(),
                request.getSearchTerm(),
                request.getStatus(),
                request.getType(),
                request.getPurchaseDateFrom(),
                request.getPurchaseDateTo(),
                request.getLastUpdatedFrom(),
                request.getLastUpdatedTo(),
                request.getCreatedBy(),
                request.getUpdatedBy()
        );
        return ResponseEntity.ok(assets);
    }

}
