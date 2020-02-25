package com.st.entity;

public enum UserRole {
    USER(1L),ADMIN(2L),MANAGER(3L);

    private Long id;

    UserRole(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
