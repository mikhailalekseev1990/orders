package org.example.orders.model;

public enum Status {
    DRAFT,
    SENT,
    RECEIVED,
    REJECTED;

    @Override
    public String toString() {
        return ""+name();
    }
}
