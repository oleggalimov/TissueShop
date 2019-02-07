package ru.urfu.tissue.dao;

public class OrderItem {
    //order section
    private Integer tissueId;
    private Float quantity;
    private Float totalPrice;

    public OrderItem() {
    }

    public Integer getTissueId() {
        return tissueId;
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
}
