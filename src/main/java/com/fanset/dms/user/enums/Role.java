package com.fanset.dms.user.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.fanset.dms.user.enums.Permission;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.fanset.dms.user.enums.Permission.*;
@RequiredArgsConstructor
public enum Role {
    ADMIN(
            Set.of(
                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    ADMIN_CREATE,
                    TECHNICIAN_READ,
                    TECHNICIAN_UPDATE,
                    TECHNICIAN_DELETE,
                    TECHNICIAN_CREATE,
                    CRM_READ,
                    CRM_UPDATE,
                    CRM_DELETE,
                    CRM_CREATE,
                    FINANCE_READ,
                    FINANCE_UPDATE,
                    FINANCE_DELETE,
                    FINANCE_CREATE,
                    SALES_READ,
                    SALES_UPDATE,
                    SALES_DELETE,
                    SALES_CREATE,
                    SUPERVISOR_READ,
                    SUPERVISOR_UPDATE,
                    SUPERVISOR_DELETE,
                    SUPERVISOR_CREATE,
                    TECHSUPPORT_READ,
                    TECHSUPPORT_UPDATE,
                    TECHSUPPORT_DELETE,
                    TECHSUPPORT_CREATE
            )
    ),
    TECHNICIAN(
            Set.of(
                    TECHNICIAN_READ,
                    TECHNICIAN_UPDATE,
                    TECHNICIAN_DELETE,
                    TECHNICIAN_CREATE
            )
    ),

    TECHSUPPORT(
            Set.of(
                    TECHSUPPORT_READ,
                    TECHSUPPORT_UPDATE,
                    TECHSUPPORT_DELETE,
                    TECHSUPPORT_CREATE
            )
    ),
    CRM(
            Set.of(
                    CRM_READ,
                    CRM_UPDATE,
                    CRM_DELETE,
                    CRM_CREATE
            )
    ),
    FINANCE(
            Set.of(
                    FINANCE_READ,
                    FINANCE_UPDATE,
                    FINANCE_DELETE,
                    FINANCE_CREATE
            )
    ),
    SUPERVISOR(
            Set.of(
                    SUPERVISOR_READ,
                    SUPERVISOR_UPDATE,
                    SUPERVISOR_DELETE,
                    SUPERVISOR_CREATE,
                    TECHNICIAN_READ,
                    TECHNICIAN_UPDATE,
                    TECHNICIAN_DELETE,
                    TECHNICIAN_CREATE
            )
    ),
    SALES(
            Set.of(
                    SALES_READ,
                    SALES_UPDATE,
                    SALES_DELETE,
                    SALES_CREATE
            )
    ),
    ;
    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
