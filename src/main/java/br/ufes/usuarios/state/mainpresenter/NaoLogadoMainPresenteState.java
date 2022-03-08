/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.state.mainpresenter;

import br.ufes.usuarios.presenter.LoginPresenter;
import br.ufes.usuarios.presenter.MainPresenter;
import br.ufes.usuarios.presenter.ManterUsuarioPresenter;
import br.ufes.usuarios.service.UsuarioService;
import javax.swing.JOptionPane;

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
        this.view.getLblTipo().setVisible(false);
        this.view.getLblUsuario().setVisible(false);
        
        if(UsuarioService.getInstancia().getListaUsuarios(null).isEmpty()) {
            JOptionPane.showMessageDialog(
                view, 
                "Não há nenhum administrador cadastrado, realize seu cadastro e se torne um administrador",
                "Primeiro Acesso!",
                JOptionPane.INFORMATION_MESSAGE
            );
            
            new ManterUsuarioPresenter(presenter, null);
            
        } else {
            new LoginPresenter(presenter);
        }
   
    }
    
}
