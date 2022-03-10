/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.state.mainpresenter;

import br.ufes.usuarios.presenter.MainPresenter;

/**
 *
 * @author Rafael
 */
public class NaoLogadoMainPresenteState extends MainPresenterState {
    
    public NaoLogadoMainPresenteState(MainPresenter presenter) {
        super(presenter);
        this.view.getBtnUsuarios().setVisible(false);
        this.view.getBtnNotificacoes().setVisible(false);
        this.view.getBtnOpcoes().setVisible(false);
        this.view.getLblNomeTipoUsuario().setVisible(false);
        this.view.getLblNomeUsuario().setVisible(false);
   
    }
    
}
