package ru.trustsoft;

import ru.trustsoft.parser.Parser;

public class MainClass {

    public static void main(String[] args) {

        try (Parser parser = new Parser()) {
            parser.scrolling();
            parser.createCsvFile(parser.parse());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
