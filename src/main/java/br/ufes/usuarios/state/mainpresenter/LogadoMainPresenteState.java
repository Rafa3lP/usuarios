/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.state.mainpresenter;

import br.ufes.usuarios.model.Usuario;
import br.ufes.usuarios.presenter.Application;
import br.ufes.usuarios.presenter.BuscarNotificacaoPresenter;
import br.ufes.usuarios.presenter.BuscarUsuarioPresenter;
import br.ufes.usuarios.presenter.ConfiguracoesPresenter;
import br.ufes.usuarios.presenter.MainPresenter;
import br.ufes.usuarios.presenter.ManterUsuarioPresenter;

/**
 *
 * @author Rafael
 */
public class LogadoMainPresenteState extends MainPresenterState {
    private Usuario usuarioAutenticado = null;
    public LogadoMainPresenteState(MainPresenter presenter) {
        super(presenter);
        usuarioAutenticado = Application.getSession().getUsuario();
        this.view.getLblUsuario().setText(usuarioAutenticado.getNome());
        String tipo;
        switch(usuarioAutenticado.getNivelDeAcesso()) {
            case Usuario.ACESSO_ADMINISTRADOR:
                this.view.getBtnUsuarios().setVisible(true);
                tipo = "Administrador";
                break;
            case Usuario.ACESSO_NORMAL:
                tipo = "Usuario";
                break;
            default:
                tipo = "";
                break;
        }
        this.view.getLblTipo().setText(tipo);
        this.view.getBtnNotificacoes().setVisible(true);
        this.view.getBtnOpcoes().setVisible(true);
        this.view.getLblTipo().setVisible(true);
        this.view.getLblUsuario().setVisible(true);
   
    }
    
    @Override
    public void cadastrar() {
        new ManterUsuarioPresenter(presenter, null);
    }
    
    @Override
    public void buscarUsuarios() {
        new BuscarUsuarioPresenter(presenter);
    }
    
    @Override
    public void buscarNotificacoes() {
        new BuscarNotificacaoPresenter(presenter);
    }
    
    @Override
    public void configurar() {
        new ConfiguracoesPresenter(presenter);
    }
    
    @Override
    public void setNumNotificacoes() {
        if(usuarioAutenticado != null) {
            this.view.getBtnNotificacoes().setText(Integer.toString(usuarioAutenticado.getNotificacoes().size()));
        }
    }
    
}
