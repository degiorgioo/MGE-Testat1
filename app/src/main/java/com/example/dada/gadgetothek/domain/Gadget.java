package com.example.dada.gadgetothek.domain;

import java.util.UUID;

public class Gadget {

    public enum Condition {NEW, GOOD, DAMAGED, WASTE, LOST}

    private final String inventoryNumber;
    private Condition condition;
    private double price;
    private String manufacturer;
    private String name;

    public Gadget(String name) {
        this.name = name;
        inventoryNumber = "" + Math.abs(UUID.randomUUID().getMostSignificantBits());
        condition = Condition.NEW;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((inventoryNumber == null) ? 0 : inventoryNumber.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Gadget other = (Gadget) obj;
        if (inventoryNumber == null) {
            if (other.inventoryNumber != null)
                return false;
        } else if (!inventoryNumber.equals(other.inventoryNumber))
            return false;
        return true;
    }
}
