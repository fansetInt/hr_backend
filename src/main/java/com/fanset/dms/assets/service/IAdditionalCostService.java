package com.fanset.dms.assets.service;

import com.fanset.dms.assets.dto.AdditionalCostRequestDto;
import com.fanset.dms.assets.dto.UpdateAdditionalCostDto;
import com.fanset.dms.assets.model.AdditionalCost;
import org.hibernate.sql.Update;

import java.util.List;

public interface IAdditionalCostService {

    AdditionalCost addAdditionalCost(Long assetId, AdditionalCostRequestDto additionalCost);

    List<AdditionalCost> getAdditionalCostsByAssetId(Long assetId);

    AdditionalCost getAdditionalCostById(Long additionalCostId);

    AdditionalCost updateAdditionalCost(Long additionalCostId, UpdateAdditionalCostDto updatedCost);

    String deleteAdditionalCost(Long additionalCostId);
}

