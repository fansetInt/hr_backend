package com.fanset.dms.assets.dto;

import lombok.Data;

import java.time.LocalDate;

public record UpdateAssetRequestedDto (
         String name,
         String serialNumber,
         LocalDate dateGiven,
         LocalDate yearPurchase,
         int usefulLife,
         Double amountPurchased,
         Double depressionCost,
         Double currentValue

){
}
