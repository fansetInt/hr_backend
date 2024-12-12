package com.fanset.dms.assets.service.implement;

import com.fanset.dms.assets.dto.AssetRequestDto;
import com.fanset.dms.assets.dto.UpdateAssetRequestedDto;
import com.fanset.dms.assets.model.Asset;
import com.fanset.dms.assets.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.Consumer;
import java.time.LocalDate;
import java.time.LocalDateTime;



@Transactional(readOnly = true)
@Service
public class AssetService implements IAssetService{

    private final AssetRepository assetRepository;

    public AssetService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }


    @Override
    public String saveAsset(AssetRequestDto assetRequestDto) {
        System.out.printf("servie :::");
        assetRepository.save(assetDtoToAssetEntity(assetRequestDto));
        return "successfully saved";
    }

    @Transactional
    @Override
    public Asset getAssetById(Long assetId) {
        return findById(assetId);
    }

    @Transactional
    @Override
    public String updateAsset(Long assetId, UpdateAssetRequestedDto updateAssetRequestedDto) {
        Asset asset = findById(assetId);
        if (asset == null) {
            throw new RuntimeException("Asset with ID " + assetId + " not found.");
        }

        updateField(asset::setName, updateAssetRequestedDto.name());
        updateField(asset::setSerialNumber, updateAssetRequestedDto.serialNumber());
        updateField(asset::setDateGiven, updateAssetRequestedDto.dateGiven());
        updateField(asset::setYearPurchase, updateAssetRequestedDto.yearPurchase());

        if (updateAssetRequestedDto.usefulLife() > 0) {
            asset.setUsefulLife(updateAssetRequestedDto.usefulLife());
        }

        updateField(asset::setAmountPurchased, updateAssetRequestedDto.amountPurchased());
        updateField(asset::setDepressionCost, updateAssetRequestedDto.depressionCost());
        updateField(asset::setCurrentValue, updateAssetRequestedDto.currentValue());

        assetRepository.save(asset);
        return "Update successful with ID :: " + assetId;
    }

    // Generic helper method for updating fields
    private <T> void updateField(Consumer<T> setter, T value) {
        if (value != null ) {
            setter.accept(value);
        }
    }



    private Asset assetDtoToAssetEntity(UpdateAssetRequestedDto updateAssetRequestedDto) {
        return null;
    }

    @Override
    public Page<Asset> getAllAsset(int page, int size, String sortBy, String sortDirection,
                                   String searchTerm, String status, String type,
                                   LocalDate purchaseDateFrom, LocalDate purchaseDateTo,
                                   LocalDateTime lastUpdatedFrom, LocalDateTime lastUpdatedTo,
                                   String createdBy, String updatedBy) {

        Pageable pageable = PageRequest.of(
                page, size,
                sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending()
        );

        return assetRepository.getAllAsset(
                searchTerm, status, type, purchaseDateFrom, purchaseDateTo,
                lastUpdatedFrom, lastUpdatedTo, createdBy, updatedBy, pageable
        );
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


    private Asset findById(Long asssetId){
        Optional<Asset> assetOptional = assetRepository.findById(asssetId);
        return assetOptional.orElse(null);
    }
}
