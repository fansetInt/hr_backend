package com.fanset.dms.assets.dto;

import com.fanset.dms.assets.model.*;
import com.fanset.dms.user.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record AssetRequestDto(
         @NotNull String name,
         @NotNull String serialNumber,
         @NotNull LocalDate dateGiven,
         @NotNull LocalDateTime yearPurchase,
         @NotNull int usefulLife,
         @NotNull Double amountPurchased,
         @NotNull Double depressionCost,
         @NotNull  Double currentValue,
         @NotNull Long userId,
         @NotNull Long assetTypeId,
         @NotNull Long statusId

) {
}
