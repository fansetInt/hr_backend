package com.fanset.dms.assets.service;

import com.fanset.dms.assets.model.Impairment;
import com.fanset.dms.assets.model.ImpairmentRequestDto;

import java.util.List;

public interface IImpairmentService {

    Impairment addImpairment(Long assetId, ImpairmentRequestDto impairment);

    List<Impairment> getImpairmentsByAssetId(Long assetId);

    Impairment getImpairmentById(Long impairmentId);

    Impairment updateImpairment(Long impairmentId, Impairment updatedImpairment);

    void deleteImpairment(Long impairmentId);
}
