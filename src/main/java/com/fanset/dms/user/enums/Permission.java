package com.fanset.dms.user.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    TECHNICIAN_READ("technician:read"),
    TECHNICIAN_UPDATE("technician:update"),
    TECHNICIAN_CREATE("technician:create"),
    TECHNICIAN_DELETE("technician:delete") ,
    CRM_READ("crm:read"),
    CRM_UPDATE("crm:update"),
    CRM_CREATE("crm:create"),
    CRM_DELETE("crm:delete"),
    FINANCE_READ("finance:read"),
    FINANCE_UPDATE("finance:update"),
    FINANCE_CREATE("finance:create"),
    FINANCE_DELETE("finance:delete"),
    SALES_READ("sales:read"),
    SALES_UPDATE("sales:update"),
    SALES_CREATE("sales:create"),
    SALES_DELETE("sales:delete"),
    SUPERVISOR_READ("supervisor:read"),
    SUPERVISOR_UPDATE("supervisor:update"),
    SUPERVISOR_CREATE("supervisor:create"),
    SUPERVISOR_DELETE("supervisor:delete"),
    TECHSUPPORT_READ("techsupport:read"),
    TECHSUPPORT_UPDATE("techsupport:update"),
    TECHSUPPORT_CREATE("techsupport:create"),
    TECHSUPPORT_DELETE("techsupport:delete"),

    ;

    @Getter
    private final String permission;
}