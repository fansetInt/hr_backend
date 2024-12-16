package com.fanset.dms.assets.service.implement;


import com.fanset.dms.assets.model.Asset;
import com.fanset.dms.assets.model.Depreciation;
import com.fanset.dms.assets.service.IDepreciationService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Transactional(readOnly = true)
@AllArgsConstructor
@Service
public class DepreciationService implements IDepreciationService {

    private final EntityManager entityManager;

    @Override
    @Transactional // Ensures write operations are properly managed
    public Depreciation calculateDepreciation(Long assetId) {
        List<Depreciation> depreciationsList = new ArrayList<>();
        Asset asset = entityManager.find(Asset.class, assetId);
        if (asset == null) {
            throw new EntityNotFoundException("Asset with ID " + assetId + " not found");
        }

        Depreciation depreciation = new Depreciation();

        // Perform depreciation calculation logic
        double yearlyDepreciation = asset.getAmountPurchased() / asset.getUsefulLife();
        double totalDepreciation = yearlyDepreciation * (LocalDate.now().getYear() - asset.getYearPurchase().getYear());
        depreciation.setAsset(asset);
        depreciation.setYearlyDepreciation(yearlyDepreciation);
        depreciation.setTotalDepreciation(totalDepreciation);
        depreciation.setRemainingValue(asset.getAmountPurchased() - totalDepreciation);
        depreciation.setCalculationDate(LocalDate.now());

        if (depreciation.getId() == null) {
            entityManager.persist(depreciation); // Create new depreciation entry
        } else {
            entityManager.merge(depreciation); // Update existing depreciation entry
        }

        depreciationsList.add(depreciation);
        asset.setDepreciations(depreciationsList);
        entityManager.merge(asset);
        return depreciation;
    }

    @Override
    public List<Depreciation> getDepreciationsByAssetId(Long assetId) {
        return entityManager.createQuery("SELECT d FROM Depreciation d WHERE d.asset.id = :assetId", Depreciation.class)
                .setParameter("assetId", assetId)
                .getResultList();
    }

    @Override
    public Depreciation getDepreciationById(Long depreciationId) {
        Depreciation depreciation = entityManager.find(Depreciation.class, depreciationId);
        if (depreciation == null) {
            throw new EntityNotFoundException("Depreciation with ID " + depreciationId + " not found");
        }
        return depreciation;
    }

//    @Override
//    @Transactional
//    public Depreciation updateDepreciation(Long depreciationId) {
//        Depreciation existingDepreciation = entityManager.find(Depreciation.class, depreciationId);
//        if (existingDepreciation == null) {
//            throw new EntityNotFoundException("Depreciation with ID " + depreciationId + " not found");
//        }
//
//        // Update fields
//        existingDepreciation.setYearlyDepreciation(updatedDepreciation.getYearlyDepreciation());
//        existingDepreciation.setTotalDepreciation(updatedDepreciation.getTotalDepreciation());
//        existingDepreciation.setRemainingValue(updatedDepreciation.getRemainingValue());
//        existingDepreciation.setCalculationDate(updatedDepreciation.getCalculationDate());
//
//        return entityManager.merge(existingDepreciation);
//    }

    @Override
    @Transactional // Ensures write operations are properly managed
    public void deleteDepreciation(Long depreciationId) {
        Depreciation depreciation = entityManager.find(Depreciation.class, depreciationId);
        if (depreciation == null) {
            throw new EntityNotFoundException("Depreciation with ID " + depreciationId + " not found");
        }
        entityManager.remove(depreciation);
    }
}
