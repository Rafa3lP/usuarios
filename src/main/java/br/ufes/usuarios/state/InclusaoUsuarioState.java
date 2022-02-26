/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.state;

import br.ufes.usuarios.command.SalvarUsuarioCommand;
import br.ufes.usuarios.model.Usuario;
import br.ufes.usuarios.presenter.ManterUsuarioPresenter;

/**
 *
 * @author Rafael
 */
public class InclusaoUsuarioState extends ManterUsuarioPresenterState {
    
    public InclusaoUsuarioState(ManterUsuarioPresenter presenter) {
        super(presenter);
        this.presenter.getView().getLblDataCadastro().setVisible(false);
        this.presenter.getView().getTxtDataCadastro().setVisible(false);
        this.presenter.getView().getBtnExcluir().setVisible(false);
        this.presenter.getView().getBtnEditar().setVisible(false);
    }
    
    @Override
    public void salvar() {
        String nome = this.presenter.getView().getTxtNome().getText();
        String usuario = this.presenter.getView().getTxtUsuario().getText();
        String senha = this.presenter.getView().getTxtSenha().getText();
        int isAdmin = this.presenter.getView().getChkAdm().isSelected() ? 1 : 2;

        Usuario u = new Usuario(nome, usuario, senha, isAdmin);
        
        new SalvarUsuarioCommand(u).executar();
    }
    
    @Override
    public void fechar() {
        this.presenter.getView().dispose();
    }
    
}
