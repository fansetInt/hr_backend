package com.fanset.dms.assets.service.implement;


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
public class AssetTypeService {

    @PersistenceContext
    private final EntityManager entityManager;
    /**
     * Create or Save an AssetType
     */
    @Transactional
    public AssetType save(AssetType assetType) {
        if (assetType.getId() == null) {
            entityManager.persist(assetType); // Create new asset type
        } else {
            entityManager.merge(assetType); // Update existing asset type
        }
        return assetType;
    }

    /**
     * Find AssetType by ID
     */
    @Transactional(readOnly = true)
    public Optional<AssetType>  findById(Long assetTypeId){
        return Optional.ofNullable(entityManager.find(AssetType.class, assetTypeId));
    }
    /**
     * Get All AssetTypes
     */
    @Transactional(readOnly = true)
    public List<AssetType> getAllAssetType(){
        return entityManager.createQuery("SELECT at FROM AssetType at", AssetType.class).getResultList();
    }
    /**
     * Update an AssetType
     */
    @Transactional
    public AssetType update(Long id, String name) {
        AssetType assetType = entityManager.find(AssetType.class, id);
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
        AssetType assetType = entityManager.find(AssetType.class, id);
        if (assetType != null) {
            entityManager.remove(assetType);
        } else {
            throw new IllegalArgumentException("AssetType with ID " + id + " not found");
        }
    }
}
