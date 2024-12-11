package com.fanset.dms.assets.service.implement;

import com.fanset.dms.assets.dto.AssetRequestDto;
import com.fanset.dms.assets.dto.UpdateAssetRequestedDto;
import com.fanset.dms.assets.model.Asset;

public interface IAssetService {
    String saveAsset (AssetRequestDto assetRequestDto);

    Asset getAssetById(Long assetId);

    String updateAsset(Long assetId, UpdateAssetRequestedDto updateAssetRequestedDto);
}
