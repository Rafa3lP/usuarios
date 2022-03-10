/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.state.manterusuariopresenter;

import br.ufes.usuarios.presenter.ManterUsuarioPresenter;
import br.ufes.usuarios.view.ManterUsuarioView;

/**
 *
 * @author Rafael
 */
public abstract class ManterUsuarioPresenterState {
    protected ManterUsuarioPresenter presenter;
    protected ManterUsuarioView view;
    public ManterUsuarioPresenterState(ManterUsuarioPresenter presenter) {
        this.presenter = presenter;
        this.view = this.presenter.getView();
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
    
    public void cancelar() {
        throw new RuntimeException("Método cancelar não pode ser executado");
    }
    
    public void fechar() {
        this.view.dispose();
    }
    
    public final void limpaCampos() {
        this.view.getTxtNome().setText("");
        this.view.getTxtUsuario().setText("");
        this.view.getTxtSenha().setText("");
        this.view.getChkAdm().setSelected(false);
    }
    
}
