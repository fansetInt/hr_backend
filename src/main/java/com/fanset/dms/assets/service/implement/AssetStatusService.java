package com.fanset.dms.assets.service.implement;

import com.fanset.dms.assets.model.AssetStatus;
import com.fanset.dms.assets.model.AssetType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;



@AllArgsConstructor
@Transactional(readOnly = true)
@Service
public class AssetStatusService {

    @PersistenceContext
    private final EntityManager entityManager;
    /**
     * Create or Save an AssetType
     */
    @Transactional
    public AssetStatus save(AssetStatus assetStatus) {
        if (assetStatus.getId() == null) {
            entityManager.persist(assetStatus); // Create new asset type
        } else {
            entityManager.merge(assetStatus); // Update existing asset type
        }
        return assetStatus;
    }

    /**
     * Find AssetType by ID
     */
    @Transactional(readOnly = true)
    public Optional<AssetStatus> findById(Long assetTypeId){
        return Optional.ofNullable(entityManager.find(AssetStatus.class, assetTypeId));
    }
    /**
     * Get All AssetTypes
     */
    @Transactional(readOnly = true)
    public List<AssetStatus> getAllAssetType(){
        return entityManager.createQuery("SELECT at FROM AssetType at", AssetStatus.class).getResultList();
    }
    /**
     * Update an AssetType
     */
    @Transactional
    public AssetStatus update(Long id, String name) {
        AssetStatus assetType = entityManager.find(AssetStatus.class, id);
        if (assetType == null) {
            throw new IllegalArgumentException("AssetType with ID " + id + " not found");
        }
        assetType.setName(name);
        return entityManager.merge(assetType);
    }
    /**
     * Delete AssetType by ID
     */
    @Transactional
    public void deleteById(Long id) {
        AssetStatus assetType = entityManager.find(AssetStatus.class, id);
        if (assetType != null) {
            entityManager.remove(assetType);
        } else {
            throw new IllegalArgumentException("AssetType with ID " + id + " not found");
        }
    }
}
