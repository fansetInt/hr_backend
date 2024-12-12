package com.fanset.dms.assets.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AssetRequestDto(
         @NotNull String name,
         @NotNull String serialNumber,
         @NotNull LocalDate dateGiven,
         @NotNull LocalDate yearPurchase,
         @NotNull int usefulLife,
         @NotNull Double amountPurchased,
         @NotNull Double depressionCost,
         @NotNull  Double currentValue,
         @NotNull String assetType,
         @NotNull String status,
         @NotNull Long userId

) {

}
