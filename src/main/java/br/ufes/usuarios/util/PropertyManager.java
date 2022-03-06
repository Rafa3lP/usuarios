/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Rafael
 */
public class PropertyManager {
    private final String CONFIG_PATH = "config.ini";
    
    public String getProperty(String key) {
    
        File file = new File(CONFIG_PATH);
        Properties properties = new Properties();

        try {
            FileInputStream fis = new FileInputStream(file);
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return properties.getProperty(key);
        
    }
    
    public void setProp(String key, String value) {
        
        File file = new File(CONFIG_PATH);
        Properties properties = new Properties();

        try {
            FileInputStream fis = new FileInputStream(file);
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
       
        properties.setProperty(key, value);
        
        try { 
            FileOutputStream fos = new FileOutputStream(file); 
            properties.store(fos, "ARQUIVO DE CONFIGURACOES"); 
            fos.close(); 
        } catch (IOException ex) {  
            ex.printStackTrace(); 
        }
 
    }
   
}

