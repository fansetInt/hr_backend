package com.fanset.dms.user.dto;

import jakarta.validation.constraints.NotNull;

public record AddressDto(
        String address,
        @NotNull String city,
        @NotNull String state,
        @NotNull String country,
        String nextOfKeenAddress,
        String nextOfKeenCity,
        String nextOfKeenState,
        String nextOfKeenCountry,
        String nextOfKeenPhoneNumber,
        @NotNull String nextOfKeenRelationship
) {
}
