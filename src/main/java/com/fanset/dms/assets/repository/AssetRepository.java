package com.fanset.dms.assets.repository;

import com.fanset.dms.assets.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends JpaRepository<Asset,Long> {

    @Query("SELECT a FROM Asset a where id =:id")
    Asset update(@Param("id") Long id);
}
