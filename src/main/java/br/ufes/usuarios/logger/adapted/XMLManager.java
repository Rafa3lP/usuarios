/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.logger.adapted;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael
 */
public class XMLManager {
    private File file;

    public XMLManager(String nomeArquivo) {
        file = new File(nomeArquivo);
    }

    public void write(String message) {
        FileWriter fw;
        try {
            fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write("<log>" + message + "</log>");
            bw.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public List<String> getMessages() {
        BufferedReader buffRead = null;
        List<String> messages = new ArrayList<>();
        try {
            
            buffRead = new BufferedReader(new FileReader(file.getPath()));
            String linha = buffRead.readLine();
            while(linha != null) {
                System.out.println(linha);
                
                linha = buffRead.readLine();
            }
            
            buffRead.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XMLManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return messages;
    }
}
