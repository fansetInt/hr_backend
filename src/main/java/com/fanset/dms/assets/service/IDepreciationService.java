package com.fanset.dms.assets.service;

import com.fanset.dms.assets.model.Depreciation;

import java.util.List;

public interface IDepreciationService {

    Depreciation calculateDepreciation(Long assetId);

    List<Depreciation> getDepreciationsByAssetId(Long assetId);

    Depreciation getDepreciationById(Long depreciationId);

    void deleteDepreciation(Long depreciationId);
}
