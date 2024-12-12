package com.fanset.dms.assets.repository;

import com.fanset.dms.assets.model.Asset;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface AssetRepository extends JpaRepository<Asset,Long> {

    @Query("SELECT a FROM Asset a WHERE " +
            "(:searchTerm IS NULL OR LOWER(a.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))) AND " +
            "(:status IS NULL OR a.status = :status) AND " +
            "(:type IS NULL OR a.assetType = :type) AND " +
            "(:purchaseDateFrom IS NULL OR a.yearPurchase >= :purchaseDateFrom) AND " +
            "(:purchaseDateTo IS NULL OR a.yearPurchase <= :purchaseDateTo) AND " +
            "(:lastUpdatedFrom IS NULL OR a.updatedAt >= :lastUpdatedFrom) AND " +
            "(:lastUpdatedTo IS NULL OR a.updatedAt <= :lastUpdatedTo) AND " +
            "(:createdBy IS NULL OR a.createdBy.id = :createdBy) AND " +
            "(:updatedBy IS NULL OR a.updatedBy.id = :updatedBy)")
    Page<Asset> getAllAsset(
            @Param("searchTerm") String searchTerm,
            @Param("status") String status,
            @Param("type") String type,
            @Param("purchaseDateFrom") LocalDate purchaseDateFrom,
            @Param("purchaseDateTo") LocalDate purchaseDateTo,
            @Param("lastUpdatedFrom") LocalDateTime lastUpdatedFrom,
            @Param("lastUpdatedTo") LocalDateTime lastUpdatedTo,
            @Param("createdBy") Long createdBy,
            @Param("updatedBy") Long updatedBy,
            Pageable pageable
    );



}
