package com.svprofi.trackit.model;

public enum PostStatus {
    REGISTERED,
    IN_TRANSIT,
    DELIVERED,
    RETURNED;

    @Override
    public String toString() {
        switch (this) {
            case REGISTERED:
                return "Registered";
            case IN_TRANSIT:
                return "In Transit";
            case DELIVERED:
                return "Delivered";
            case RETURNED:
                return "Returned";
            default:
                return super.toString();
        }
    }
}