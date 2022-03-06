/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.ufes.usuarios.logger.adapter;

/**
 *
 * @author Rafael
 */
public interface ILoggerAdapterFactory {
    LoggerAdapter cria(String formato);
}
