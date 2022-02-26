/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.state;

import br.ufes.usuarios.model.Usuario;
import br.ufes.usuarios.presenter.ManterUsuarioPresenter;

/**
 *
 * @author Rafael
 */
public abstract class ManterUsuarioPresenterState {
    protected ManterUsuarioPresenter presenter;
    public ManterUsuarioPresenterState(ManterUsuarioPresenter presenter) {
        this.presenter = presenter;
    }
    
    public void salvar() {
        throw new RuntimeException("Método salvar não pode ser executado");
    }
    public void editar() {
        throw new RuntimeException("Método editar não pode ser executado");
    }
    public void excluir() {
        throw new RuntimeException("Método excluir não pode ser executado");
    }
    public void fechar() {
        throw new RuntimeException("Método fechar não pode ser executado");
    }
    
}
