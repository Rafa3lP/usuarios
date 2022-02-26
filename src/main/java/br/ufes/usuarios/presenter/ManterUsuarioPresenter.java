/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.presenter;

import br.ufes.usuarios.model.Usuario;
import br.ufes.usuarios.state.InclusaoUsuarioState;
import br.ufes.usuarios.state.ManterUsuarioPresenterState;
import br.ufes.usuarios.view.ManterUsuarioView;
import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael
 */
public class ManterUsuarioPresenter {
    private ManterUsuarioView view;
    private MainPresenter mainPresenter;
    private ManterUsuarioPresenterState state;
    private Usuario usuario;
    
    public ManterUsuarioPresenter(MainPresenter mainPresenter, Usuario usuario) {
        this.view = new ManterUsuarioView();
        this.mainPresenter = mainPresenter;
        this.usuario = usuario;
        if(usuario == null) {
            this.setState(new InclusaoUsuarioState(this));
        } else {
            // estado de visualizacao
        }
        
        getView().getBtnSalvar().addActionListener((e) -> {
            try {
                this.state.salvar();
                JOptionPane.showMessageDialog(getView(), "Usuario inserido", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                // teste
                this.view.dispose();
                new LoginPresenter(mainPresenter);
            } catch(RuntimeException ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(getView(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        this.mainPresenter.addToDesktopPane(view);
        this.view.setVisible(true);
    }
    
    public ManterUsuarioView getView() {
        return this.view;
    }
    
    public void setState(ManterUsuarioPresenterState state) {
        this.state = state;
    }
    
}
