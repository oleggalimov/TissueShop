package ru.urfu.tissue.dao;

import java.util.Objects;

public class OrderItem {
    //order section
    private Integer tissueId;
    private Float quantity;
    private String name;
    private Float price;
    private Float totalPrice;

    public OrderItem() {
    }

    public OrderItem(Integer tissueId, Float quantity, String name, Float price, Float totalPrice) {
        this.tissueId = tissueId;
        this.quantity = quantity;
        this.name = name;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public Integer getTissueId() {
        return tissueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTissueId(Integer tissueId) {
        this.tissueId = tissueId;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(tissueId, orderItem.tissueId) &&
                Objects.equals(name, orderItem.name) &&
                Objects.equals(price, orderItem.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tissueId, name, price);
    }
}
