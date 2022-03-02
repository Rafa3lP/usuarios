/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.state;

import br.ufes.usuarios.presenter.BuscarUsuarioPresenter;
import br.ufes.usuarios.view.BuscarUsuarioView;

/**
 *
 * @author Rafael
 */
public abstract class BuscarUsuarioPresenterState {
    protected BuscarUsuarioPresenter presenter;
    private BuscarUsuarioView view;
    public BuscarUsuarioPresenterState(BuscarUsuarioPresenter presenter) {
        this.presenter = presenter;
        this.view = this.presenter.getView();
    }
    
    public void visualizar() {
        throw new RuntimeException("Método visualizar não pode ser executado");
    }
    
    public void buscar() {
        throw new RuntimeException("Método buscar não pode ser executado");
    }
  
    public void fechar() {
        this.view.dispose();
    }
    
}
