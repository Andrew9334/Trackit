package com.svprofi.trackit.model;

public enum MovementType {
    ARRIVAL,
    DEPARTURE,
    DELIVERY;

    @Override
    public String toString() {
        switch (this) {
            case ARRIVAL:
                return "Arrival";
            case DEPARTURE:
                return "Departure";
            case DELIVERY:
                return "Delivery";
            default:
                return super.toString();
        }
    }
}