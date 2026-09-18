package com.backendMiniProject.OrderAndInventoryManagement.enums;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import static com.backendMiniProject.OrderAndInventoryManagement.enums.Permission.*;

public enum Role {

    ROLE_CUSTOMER(Set.of(
            ORDER_CREATE,
            ORDER_READ
    )),

    ROLE_ADMIN(Set.of(
            ORDER_CREATE,
            ORDER_UPDATE,
            ORDER_READ,
            ORDER_DELETE,

            INVENTORY_READ,
            INVENTORY_UPDATE,
            INVENTORY_CREATE,
            INVENTORY_DELETE,

            PRODUCT_CREATE,
            PRODUCT_UPDATE,
            PRODUCT_DELETE
    ));

    private final Set<Permission> permissions;

    // Constructor
    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public List<SimpleGrantedAuthority> getAuthorities(Role role) {
        List<SimpleGrantedAuthority> list=new ArrayList<>(permissions.stream()
                .map(permission ->
                        new SimpleGrantedAuthority(permission.name())
                )
                .toList());

        list.add(new SimpleGrantedAuthority(role.name()));

        return list;
    }
}