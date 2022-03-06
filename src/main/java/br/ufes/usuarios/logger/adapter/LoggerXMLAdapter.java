/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.logger.adapter;

import br.ufes.usuarios.logger.Log;
import br.ufes.usuarios.logger.adapted.XMLManager;
import java.util.List;

/**
 *
 * @author Rafael
 */
public class LoggerXMLAdapter extends LoggerAdapter {
    private final String nomeArquivo;
    private final XMLManager fileManager;
    
    public LoggerXMLAdapter(String nomeArquivo) {
        if (!nomeArquivo.toLowerCase().endsWith("xml")) {
            throw new RuntimeException("Informe um arquivo XML válido");
        }
        fileManager = new XMLManager(nomeArquivo);
        this.nomeArquivo = nomeArquivo;
    }

    @Override
    public void grava(Log log) {
       fileManager.write(log.getMensagem());
    }

    @Override
    public List<String> getLogs() {
        return fileManager.getMessages();
    }

    @Override
    public void grava(String... log) {
        for(String message: log) {
            fileManager.write(message);
        }
        
    }
    
    @Override
    public String getNomeArquivo() {
       return this.nomeArquivo;
    }

}
