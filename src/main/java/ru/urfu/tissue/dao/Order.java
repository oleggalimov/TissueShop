package ru.urfu.tissue.dao;

import java.util.HashMap;
import java.util.HashSet;

public class Order {
    //order section
    private Integer id;
    private Integer status;
    private Long creationDate;
    private Long sendDate;
    private Long receivedDate;

    //client section
    private String clientName;
    private String clientLastName;
    private Long phoneNumber;
    private String address;

    //items section
    private HashSet <OrderItem> itemsList;

    public Order(Integer id) {
        this.id=id;
    }

    public Order() {
        this.status=0;
        this.creationDate=System.currentTimeMillis();
        this.itemsList =new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public Long getSendDate() {
        return sendDate;
    }

    public void setSendDate(Long sendDate) {
        this.sendDate = sendDate;
    }

    public Long getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Long receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public HashSet getItemsList() {
        return itemsList;
    }

    public void setItemsList(HashSet itemsList) {
        this.itemsList = itemsList;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status=" + status +
                ", creationDate=" + creationDate +
                ", sendDate=" + sendDate +
                ", receivedDate=" + receivedDate +
                ", clientName='" + clientName + '\'' +
                ", clientLastName='" + clientLastName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", address='" + address + '\'' +
                ", itemsList=" + itemsList +
                '}';
    }

    public void addOrderItem (OrderItem newItem) {
        this.itemsList.remove(newItem);
        this.itemsList.add(newItem);

    }
    public void removeOrderItem (OrderItem newItem) {
        this.itemsList.remove(newItem);
    }

}
