package at.vsa.backend.user.entities;

import lombok.Getter;

import java.security.Permission;
import java.util.Collections;
import java.util.Set;

@Getter
public enum Role {
    USER(Collections.emptySet()),
    ADMIN(Collections.emptySet());

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}
