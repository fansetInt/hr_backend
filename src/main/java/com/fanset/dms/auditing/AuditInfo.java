package com.fanset.dms.auditing;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditInfo {
    private Long userId;
    private String userName;

}

