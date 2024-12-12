package com.fanset.dms.assets.controller;


import com.fanset.dms.assets.dto.AssetRequestDto;
import com.fanset.dms.assets.dto.UpdateAssetRequestedDto;
import com.fanset.dms.assets.model.Asset;
import com.fanset.dms.assets.service.implement.AssetService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


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
}
