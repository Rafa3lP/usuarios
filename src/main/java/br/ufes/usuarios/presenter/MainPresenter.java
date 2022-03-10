/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.presenter;

import br.ufes.usuarios.observer.Observer;
import br.ufes.usuarios.service.UsuarioService;
import br.ufes.usuarios.state.mainpresenter.MainPresenterState;
import br.ufes.usuarios.state.mainpresenter.NaoLogadoMainPresenteState;
import br.ufes.usuarios.view.MainView;
import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael
 */
public class MainPresenter implements Observer {
    private MainView view;
    private MainPresenterState state;
    
    public MainPresenter() {
        this.view = new MainView();
        
        setState(new NaoLogadoMainPresenteState(this));
        
        if(UsuarioService.getInstancia().getListaUsuarios(null).isEmpty()) {
            JOptionPane.showMessageDialog(
                view, 
                "Não há nenhum administrador cadastrado, realize seu cadastro e se torne um administrador",
                "Primeiro Acesso!",
                JOptionPane.INFORMATION_MESSAGE
            );
            
            new ManterUsuarioPresenter(this, null);
            
        } else {
            new LoginPresenter(this);
        }
        
        UsuarioService.getInstancia().registerObserver(this);
        
        this.view.getBtnCadastrar().addActionListener((e) -> {
            try {
                state.cadastrar();
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(getView(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        this.view.getBtnBuscar().addActionListener((e) -> {
            try {
                state.buscarUsuarios();
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(getView(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        this.view.getBtnNotificacoes().addActionListener((e) -> {
            try {
                state.buscarNotificacoes();
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(getView(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        this.view.getBtnConfigurar().addActionListener((e) -> {
            try {
                state.configurar();
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(getView(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        this.view.getBtnMeuUsuario().addActionListener((e) -> {
            try {
                state.meuUsuario();
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(getView(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
 
        this.view.setVisible(true);
   
    }
    
    public void addToDesktopPane(Component component) {
        this.view.getDesktopPane().add(component);
    }
    
    public MainView getView() {
        return this.view;
    }
    
    public void setState(MainPresenterState state) {
        this.state = state;
    }

    @Override
    public void update() {
        state.setNumNotificacoes();
    }
    
}
