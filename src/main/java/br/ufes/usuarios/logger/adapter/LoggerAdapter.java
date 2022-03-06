/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.logger.adapter;

import br.ufes.usuarios.logger.Log;
import java.io.File;
import java.util.List;

/**
 *
 * @author Rafael
 */
public abstract class LoggerAdapter {
    public abstract void grava(Log log);
    public abstract void grava(String... log);
    public abstract String getNomeArquivo();
    public abstract List<String> getLogs();
    public final void migraLogger(LoggerAdapter oldLogger) {
        List<String> logsOld = oldLogger.getLogs();
        for(String log: logsOld) {
            this.grava(log);
        }
        new File(oldLogger.getNomeArquivo()).delete();
    }
}
