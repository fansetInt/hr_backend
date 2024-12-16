package com.fanset.dms.assets.dto;

public record DepreciationRequestDto(
        Long assetId,
        Integer yearlyDepreciation,
        Double remainingValue,
        String calculationDate

) {
}
