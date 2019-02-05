package ru.urfu.tissue.dao;

public class OrderItem {
    //order section
    private Integer id;
    private Integer tissueId;
    private Integer orderId;
    private Long creationDate;
    private Float quantity;
    private Float totalPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer gettissueId() {
        return tissueId;
    }

    public void settissueId(Integer tissueId) {
        this.tissueId = tissueId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
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
