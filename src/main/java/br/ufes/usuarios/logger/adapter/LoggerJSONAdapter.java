/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.logger.adapter;

import br.ufes.usuarios.logger.Log;
import java.util.List;

/**
 *
 * @author Rafael
 */
public class LoggerJSONAdapter extends LoggerAdapter {
    private final String nomeArquivo;
    
    
    LoggerJSONAdapter(String nomeArquivo) {
        if (!nomeArquivo.toLowerCase().endsWith("json")) {
            throw new RuntimeException("Informe um arquivo JSON válido");
        }
        
        this.nomeArquivo = nomeArquivo;
    }

    @Override
    public void grava(String... log) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<String> getLogs() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void grava(Log log) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getNomeArquivo() {
       return this.nomeArquivo;
    }
    
}
