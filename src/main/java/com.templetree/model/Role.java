package com.templetree.model;

/**
 * Created by Lalith on 12/5/15.
 */
public enum Role {
    ADMIN("admin"), EDITOR("editor"), GUEST("guest");

    private String roleName;

    private Role(String roleName) {
        this.roleName = roleName;
    }

    public String roleName() {
        return roleName;
    }
}
