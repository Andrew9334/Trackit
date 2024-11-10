package com.svprofi.trackit.model;

public enum ItemType {
    LETTER,
    PACKAGE,
    PARCEL,
    POSTCARD;

    @Override
    public String toString() {
        switch (this) {
            case LETTER:
                return "Letter";
            case PACKAGE:
                return "Package";
            case PARCEL:
                return "Parcel";
            case POSTCARD:
                return "Postcard";
            default:
                return super.toString();
        }
    }
}