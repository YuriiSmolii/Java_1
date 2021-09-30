package com.company;

public class CommonItem extends Item{
    private int DueToDate;
    private int weight;

    public CommonItem(String name, String manufacturer, float price, int DueToDate, int weight) {
        super(name, manufacturer, price);
        this.setWeight(weight);
        this.setDueToDate(DueToDate);
    }

    public int getWeight() {
        return weight;
    }
    public int getDueToDate() {
        return DueToDate;
    }

    public void setDueToDate(int dueToDate) {
        this.DueToDate = dueToDate;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return  super.toString() + " " + weight + " " + DueToDate;
    }
}
