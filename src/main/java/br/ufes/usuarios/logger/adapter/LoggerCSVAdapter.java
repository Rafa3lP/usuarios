/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.logger.adapter;

import br.ufes.usuarios.logger.Log;
import br.ufes.usuarios.logger.adapted.CSVTool;
import java.util.List;

/**
 *
 * @author Rafael
 */
public class LoggerCSVAdapter extends LoggerAdapter {
    private final String nomeArquivo;
    private final CSVTool csvTool;
    
    LoggerCSVAdapter(String nomeArquivo) {
        if (!nomeArquivo.toLowerCase().endsWith("csv")) {
            throw new RuntimeException("Informe um arquivo CSV válido");
        }
        
        this.csvTool = new CSVTool(nomeArquivo);
        this.nomeArquivo = nomeArquivo;
    }
    
    @Override
    public void grava(Log log) {
       csvTool.escreve(log.getMensagem());
    }

    @Override
    public List<String> getLogs() {
        return csvTool.getMessages();
    }

    @Override
    public void grava(String... log) {
        for(String message: log) {
            csvTool.escreve(message);
        }
        
    }

    @Override
    public String getNomeArquivo() {
       return this.nomeArquivo;
    }


 
}
