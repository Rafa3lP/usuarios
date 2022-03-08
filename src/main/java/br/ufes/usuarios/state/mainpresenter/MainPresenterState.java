/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.state.mainpresenter;

import br.ufes.usuarios.presenter.MainPresenter;
import br.ufes.usuarios.view.MainView;

/**
 *
 * @author Rafael
 */
public abstract class MainPresenterState {
    protected MainPresenter presenter;
    protected MainView view;
    
    public MainPresenterState(MainPresenter presenter) {
        this.presenter = presenter;
        this.view = this.presenter.getView();
    }
    
    public void cadastrar() {
        throw new RuntimeException("Método cadastrar não pode ser executado");
    }
    public void buscarUsuarios() {
        throw new RuntimeException("Método buscar usuarios não pode ser executado");
    }
    public void buscarNotificacoes() {
        throw new RuntimeException("Método buscar notificacoes não pode ser executado");
    }
    public void configurar() {
        throw new RuntimeException("Método configurar não pode ser executado");
    }
    public void setNumNotificacoes() {
        
    }
}
