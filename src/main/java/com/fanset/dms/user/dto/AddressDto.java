package com.fanset.dms.user.dto;

public record AddressDto(
        String address,
        String city,
        String state,
        String country,
        String nextOfKeenAddress,
        String nextOfKeenCity,
        String nextOfKeenState,
        String nextOfKeenCountry,
        String nextOfKeenPhoneNumber,
        String nextOfKeenRelationship
) {
}
