/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.logger.adapter;

import br.ufes.usuarios.logger.adapted.JSONManager;
import br.ufes.usuarios.logger.Log;
import java.util.List;

/**
 *
 * @author Rafael
 */
public class LoggerJSONAdapter extends LoggerAdapter {
    private final String nomeArquivo;
    private JSONManager jsonManager;
    
    LoggerJSONAdapter(String nomeArquivo) {
        if (!nomeArquivo.toLowerCase().endsWith("json")) {
            throw new RuntimeException("Informe um arquivo JSON válido");
        }
        
        this.nomeArquivo = nomeArquivo;
        jsonManager = new JSONManager(nomeArquivo);
    }

    @Override
    public void grava(String... log) {
        for(String message: log) {
            jsonManager.writeLog(message);
        }
    }

    @Override
    public List<String> getLogs() {
        return jsonManager.getLogs();
    }

    @Override
    public void grava(Log log) {
        jsonManager.writeLog(log.getMensagem());
    }

    @Override
    public String getNomeArquivo() {
       return this.nomeArquivo;
    }
    
}
