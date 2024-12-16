package com.fanset.dms.assets.service.implement;

import com.fanset.dms.assets.dto.AssetRequestDto;
import com.fanset.dms.assets.dto.UpdateAssetRequestedDto;
import com.fanset.dms.assets.model.Asset;
import com.fanset.dms.assets.model.AssetStatus;
import com.fanset.dms.assets.model.AssetType;
import com.fanset.dms.assets.repository.AssetRepository;
import com.fanset.dms.assets.service.IAssetService;
import com.fanset.dms.user.model.User;
import com.fanset.dms.user.service.implementation.UserServiceImpl;
import com.fanset.dms.utils.error_handling.ResourceNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.Consumer;
import java.time.LocalDate;
import java.time.LocalDateTime;



@Transactional(readOnly = true)
@Service
public class AssetService implements IAssetService {

    private final AssetRepository assetRepository;
    private final UserServiceImpl userServiceImpl;
    private final AssetTypeService assetTypeService;
    private final AssetStatusService assetStatusService;

    public AssetService(AssetRepository assetRepository, UserServiceImpl userServiceImpl, AssetTypeService assetTypeService, AssetStatusService assetStatusService) {
        this.assetRepository = assetRepository;
        this.userServiceImpl = userServiceImpl;
        this.assetTypeService = assetTypeService;
        this.assetStatusService = assetStatusService;
    }


    @Override
    public String saveAsset(AssetRequestDto assetRequestDto,Long editor) {
        User editorUser = userServiceImpl.findUserById(editor);
        assetRepository.save(assetDtoToAssetEntity(assetRequestDto,editorUser));
        return "successfully saved";
    }

    @Transactional
    @Override
    public Asset getAssetById(Long assetId) {
        return findById(assetId);
    }

    @Transactional
    @Override
    public String updateAsset(
            Long assetId,
            UpdateAssetRequestedDto updateAssetRequestedDto,
            Long editorId) {
        Asset asset = findById(assetId);
        if (asset == null) {
            throw new RuntimeException("Asset with ID " + assetId + " not found.");
        }
        updateField(asset::setName, updateAssetRequestedDto.name());
        updateField(asset::setSerialNumber, updateAssetRequestedDto.serialNumber());
        updateField(asset::setDateGiven, updateAssetRequestedDto.dateGiven());
        if (updateAssetRequestedDto.usefulLife() > 0) {
            asset.setUsefulLife(updateAssetRequestedDto.usefulLife());
        }
        updateField(asset::setAmountPurchased, updateAssetRequestedDto.amountPurchased());
        User user = userServiceImpl.findUserById(editorId);
        asset.setCreatedBy(user);

        assetRepository.save(asset);
        return "Update successful with ID :: " + assetId;
    }

    // Generic helper method for updating fields



    @Override
    public Page<Asset> getAllAsset(int page, int size, String sortBy, String sortDirection,
                                   String searchTerm, String status, String type,
                                   LocalDate purchaseDateFrom, LocalDate purchaseDateTo,
                                   LocalDateTime lastUpdatedFrom, LocalDateTime lastUpdatedTo,
                                   Long createdBy, Long updatedBy) {

        Pageable pageable = PageRequest.of(
                page, size,
                sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending()
        );

        return assetRepository.getAllAsset(
                searchTerm, status, type, purchaseDateFrom, purchaseDateTo,
                lastUpdatedFrom, lastUpdatedTo, createdBy, updatedBy, pageable
        );
    }



    @Transactional
    @Override
    public Asset assignAssetToUser(Long assetId, Long userId) {
        Asset asset = findById(assetId);
        User assignedTo = userServiceImpl.findUserById(userId);
        if (asset == null) {
            throw new RuntimeException("Asset with ID " + assetId + " not found.");
        }
//        if (asset.getAssignedTo()!= null) {
//            throw new RuntimeException("Asset with ID " + assetId + " is already assigned to user.");
//        }
        asset.setAssignedTo(assignedTo);
        return assetRepository.save(asset);
    }




    @Override
    public String calculateAssetValue(Long assetId) {
        return "";
    }


    @Transactional
    private Asset assetDtoToAssetEntity(AssetRequestDto assetRequestDto,User editor) {
        Asset asset = new Asset();
        updateField(asset::setName, assetRequestDto.name());
        updateField(asset::setSerialNumber, assetRequestDto.serialNumber());
        updateField(asset::setDateGiven, assetRequestDto.dateGiven());
        updateField(asset::setYearPurchase, assetRequestDto.yearPurchase());
        updateField(asset::setUsefulLife, assetRequestDto.usefulLife());
        updateField(asset::setAmountPurchased, assetRequestDto.amountPurchased());
        User employee = userServiceImpl.findUserById(assetRequestDto.userId());
        if (employee != null){
            asset.setAssignedTo(employee);
        }
        asset.setAssetType(
                assetTypeService.findById(assetRequestDto.assetTypeId())
                        .orElseThrow(() -> new ResourceNotFoundException("AssetType not found for ID: " + assetRequestDto.assetTypeId()))
        );
        asset.setStatus(
                assetStatusService.findById(assetRequestDto.statusId())
                        .orElseThrow(() -> new ResourceNotFoundException("AssetStatus not found for ID: " + assetRequestDto.statusId()))
        );
        asset.setUpdatedBy(editor);;
        asset.setCreatedBy(editor);
        return asset;

    }

    private Asset findById(Long asssetId){
        Optional<Asset> assetOptional = assetRepository.findById(asssetId);
        return assetOptional.orElse(null);
    }


    private <T> void updateField(Consumer<T> setter, T value) {
        if (value != null ) {
            setter.accept(value);
        }
    }
}
