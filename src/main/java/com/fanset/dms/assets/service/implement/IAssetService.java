package com.fanset.dms.assets.service.implement;

import com.fanset.dms.assets.dto.AssetRequestDto;
import com.fanset.dms.assets.dto.UpdateAssetRequestedDto;
import com.fanset.dms.assets.model.Asset;
import com.fanset.dms.assets.model.Asset;
import com.fanset.dms.user.model.User;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface IAssetService {
    String saveAsset(AssetRequestDto assetRequestDto, Long editor);

    Asset getAssetById(Long assetId);

    String updateAsset(Long assetId, UpdateAssetRequestedDto updateAssetRequestedDto,Long editor);
    Page<Asset> getAllAsset(
            int page, int size, String sortBy, String sortDirection, String searchTerm, String status,
            String type, LocalDate purchaseDateFrom, LocalDate purchaseDateTo, LocalDateTime lastUpdatedFrom,
            LocalDateTime lastUpdatedTo, Long createdBy, Long updatedBy
    );
}
