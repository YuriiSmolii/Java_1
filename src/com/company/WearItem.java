package com.company;

public class WearItem extends Item{
    private String textile;
    private String size;

    public WearItem(String name, String manufacturer, float price, String textile, String size) {
        super(name, manufacturer, price);
        this.textile = textile;
        this.size = size;
    }

    public String getTextile() {
        return textile;
    }
    public String getSize() {
        return size;
    }


    public void setFabric(String textile) {
        this.textile = textile;
    }
    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return  super.toString() + " " + textile + " " + size;
    }
}
