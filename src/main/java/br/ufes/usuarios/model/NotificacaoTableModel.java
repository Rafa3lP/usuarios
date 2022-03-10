/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Rafael
 */
public class NotificacaoTableModel extends AbstractTableModel {
    private final String colunas[] = {"Titulo", "Lida"};
    private final List<Notificacao> lista;
    private final int COLUNA_TITULO = 0;
    private final int COLUNA_LIDA = 1;
    
    public NotificacaoTableModel(List<Notificacao> lista) {
        this.lista = lista;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }
   
    @Override
    public int getColumnCount() {
        return colunas.length;
    }
   
    @Override
    public String getColumnName(int indice) {
        return colunas[indice];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case COLUNA_TITULO:
                return String.class;
            case COLUNA_LIDA:
                return String.class;
            default:
                return String.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Notificacao notificacao = this.lista.get(rowIndex);

        switch (columnIndex) {
            case COLUNA_TITULO:
                return notificacao.getTitulo();
            case COLUNA_LIDA:
                return notificacao.isLida() ? "Sim" : "Não";
        }
        return null;
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Notificacao notificacao = this.lista.get(rowIndex);
        
        switch (columnIndex) {
            case COLUNA_TITULO:
                notificacao.setTitulo((String)aValue); 
            case COLUNA_LIDA:
                notificacao.setLida((Boolean)aValue);
        }
    }
    
    public Notificacao getNotificacao(int rowIndex) {
        return lista.get(rowIndex);
    }
    
}
