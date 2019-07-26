package ru.trustsoft.parser;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Parser implements AutoCloseable{

    private final WebDriver driver;
    private final Writer writer;

    public Parser() throws FileNotFoundException, UnsupportedEncodingException {
        String driverFile = "geckodriver.exe";
        final File file = new File(driverFile);
        System.setProperty("webdriver.firefox.driver", file.getAbsolutePath());
        driver = new FirefoxDriver();
        driver.navigate().to("https://flashdeals.aliexpress.com/en.htm?");
        String csvFile = "items.csv";
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile),
                "utf-8"));
    }

    public void scrolling() {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        for (int i = 0; i < 10; i++) {
            jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Item> parse() {
        List<Item> itemList = new ArrayList<>();
        List<WebElement> allItems =  driver.findElements(By.className("deals-item-inner"));
        int i = 0;
        while (i < 100 && i < allItems.size()) {
            WebElement webItem = allItems.get(i);
            webItem.findElements(By.className("item-image")).get(0).getAttribute("src");
            String itemAsText= webItem.getText();
            String arrText[] = itemAsText.split("\n");
            String itemName = arrText[0];
            String itemCurrentPrice = arrText[1];
            String itemOriginalPrice = arrText[1];
            String itemDiscount = "0";
            if (arrText.length > 2) {
                String arrOriginalPrice[] = arrText[2].split(" \\| ");
                itemOriginalPrice = arrOriginalPrice[0];
                itemDiscount = arrOriginalPrice[1];
            }
            String itemImageUrl = webItem.findElements(By.tagName("img")).get(0).getAttribute("src");
            itemList.add(new Item(itemName, itemCurrentPrice, itemOriginalPrice, itemDiscount, itemImageUrl));
            i++;
        }
        return itemList;
    }

    public void createCsvFile(List<Item> itemList) throws IOException {
        StringBuilder fileText = new StringBuilder("");
        for (Item item : itemList) {
            fileText.append(item.getItemName()).append(",")
                    .append(item.getItemOriginalPrice()).append(",")
                    .append(item.getItemDiscount()).append(",")
                    .append(item.getItemCurrentPrice()).append(",")
                    .append(item.getItemImageUrl()).append("\n");
        }
        writer.write(fileText.toString());
    }

    public void close() throws Exception {
        writer.close();
        driver.close();
    }
}
