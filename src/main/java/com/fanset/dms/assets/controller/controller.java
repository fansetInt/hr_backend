package com.fanset.dms.assets.controller;


import com.fanset.dms.assets.dto.AssetRequestDto;
import com.fanset.dms.assets.service.implement.AssetService;
import com.fanset.dms.assets.service.implement.IAssetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@PreAuthorize("hasAnyRole('ADMIN', 'CRM', 'FINANCE', 'TECHNICIAN', 'TECHSUPPORT', 'SUPERVISOR')")
@RequestMapping("/api/v1/asset")
public class controller {
    private final AssetService assetService;


    @PostMapping
    public ResponseEntity<String> saveAsset(@Valid  @RequestBody AssetRequestDto assetRequestDto){
        System.out.printf("controller");
        return ResponseEntity.ok(assetService.saveAsset(assetRequestDto));
    }
}
