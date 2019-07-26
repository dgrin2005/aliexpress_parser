package ru.trustsoft.parser;

public class Item {

    private final String itemName;
    private final String itemCurrentPrice;
    private final String itemOriginalPrice;
    private final String itemDiscount;
    private final String itemImageUrl;

    Item(String itemName, String itemCurrentPrice, String itemOriginalPrice, String itemDiscount,
         String itemImageUrl) {
        this.itemName = itemName;
        this.itemCurrentPrice = itemCurrentPrice;
        this.itemOriginalPrice = itemOriginalPrice;
        this.itemDiscount = itemDiscount;
        this.itemImageUrl = itemImageUrl;
    }

    String getItemName() {
        return itemName;
    }

    String getItemCurrentPrice() {
        return itemCurrentPrice;
    }

    String getItemOriginalPrice() {
        return itemOriginalPrice;
    }

    String getItemDiscount() {
        return itemDiscount;
    }

    String getItemImageUrl() {
        return itemImageUrl;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemName='" + itemName + '\'' +
                ", itemCurrentPrice='" + itemCurrentPrice + '\'' +
                ", itemOriginalPrice='" + itemOriginalPrice + '\'' +
                ", itemDiscount='" + itemDiscount + '\'' +
                ", itemImageUrl='" + itemImageUrl + '\'' +
                '}';
    }
}
