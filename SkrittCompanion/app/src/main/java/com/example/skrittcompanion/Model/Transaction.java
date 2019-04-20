package com.example.skrittcompanion.Model;

public class Transaction {

    private int item_id;
    private int price;
    private int quantity;
    private Item item;

    public Transaction(int item_id, int price, int quantity) {
        this.item_id = item_id;
        this.price = price;
        this.quantity = quantity;
    }


    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
