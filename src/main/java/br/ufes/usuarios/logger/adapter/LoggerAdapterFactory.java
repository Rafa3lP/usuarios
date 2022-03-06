/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.logger.adapter;

/**
 *
 * @author Rafael
 */
public class LoggerAdapterFactory implements ILoggerAdapterFactory {

    @Override
    public LoggerAdapter cria(String formato) {
        switch(formato) {
            case "XML":
                return new LoggerXMLAdapter("log.xml");
            case "JSON":
                return new LoggerJSONAdapter("log.json");
            case "CSV":
                return new LoggerCSVAdapter("log.csv");
            default:
                throw new RuntimeException("Formato não suportado");
        }
    }
    
}
