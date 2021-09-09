package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {

    private static FileInputStream fis;
    private static Properties PROPERTIES;

    static {
        try {
            fis = new FileInputStream("src/test/resources/config.properties");
            PROPERTIES = new Properties();
            PROPERTIES.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null)
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace(); } } }

    //метод получения строки из файла
    public static String getProperty(String nameProperty) { return PROPERTIES.getProperty(nameProperty); }
}
