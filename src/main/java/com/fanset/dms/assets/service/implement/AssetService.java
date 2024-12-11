package com.fanset.dms.assets.service.implement;

import com.fanset.dms.assets.dto.AssetRequestDto;
import com.fanset.dms.assets.dto.UpdateAssetRequestedDto;
import com.fanset.dms.assets.model.Asset;
import com.fanset.dms.assets.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Transactional(readOnly = true)
@Service
public class AssetService implements IAssetService{

    private final AssetRepository assetRepository;

    public AssetService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }


    @Transactional
    @Override
    public String saveAsset(AssetRequestDto assetRequestDto) {
        System.out.printf("servie :::");
        assetRepository.save(assetDtoToAssetEntity(assetRequestDto));
        return "successfully saved";
    }

    @Transactional
    @Override
    public Asset getAssetById(Long assetId) {
        Optional<Asset> asset =assetRepository.findById(assetId);

        if(asset.isEmpty()){
            return null;
        }
        return asset.get();
    }

    @Override
    public String updateAsset(Long assetId, UpdateAssetRequestedDto updateAssetRequestedDto) {
        return null;
    }

    private Asset assetDtoToAssetEntity(AssetRequestDto assetRequestDto){
        Asset asset = new Asset();
        asset.setName(assetRequestDto.name());
        asset.setSerialNumber(assetRequestDto.serialNumber());
        asset.setDateGiven(assetRequestDto.dateGiven());
        asset.setYearPurchase(assetRequestDto.yearPurchase());
        asset.setUsefulLife(assetRequestDto.usefulLife());
        asset.setAmountPurchased(assetRequestDto.amountPurchased());
        asset.setDepressionCost(assetRequestDto.depressionCost());
        asset.setCurrentValue(assetRequestDto.currentValue());
        return asset;

    }
}
