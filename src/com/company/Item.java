package com.company;
import java.util.Comparator;

public class Item implements Comparable<Item>{
    private String name;
    private String manufacturer;
    private float price;

    public Item(String name, String manufacturer, float price) {
        this.setName(name);
        this.setProducer(manufacturer);
        this.setPrice(price);
    }

    public String getName() {
        return name;
    }
    public float getPrice() {
        return price;
    }
    public String getManufacturer() {
        return manufacturer;
    }

    public void setProducer(String producer) {
        this.manufacturer = producer;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(float value) {
        this.price = value;
    }

    @Override
    public String toString() {
        return name + " " + manufacturer + " " + price;
    }
    @Override
    public int compareTo(Item compareItem) {
        return Float.compare(compareItem.getPrice(), compareItem.price);
    }
}
