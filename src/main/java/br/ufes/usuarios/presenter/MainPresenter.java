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

/**
 *
 * @author Rafael
 */
public class MainPresenter implements Observer {
    private MainView view;
    private MainPresenterState state;
    
    public MainPresenter() {
        this.view = new MainView();
        this.view.getBtnUsuarios().setVisible(false);
        
        UsuarioService.getInstancia().registerObserver(this);
        
        setState(new NaoLogadoMainPresenteState(this));
        
        this.view.getBtnCadastrar().addActionListener((e) -> {
            state.cadastrar();
        });
        
        this.view.getBtnBuscar().addActionListener((e) -> {
            state.buscarUsuarios();
        });
        
        this.view.getBtnNotificacoes().addActionListener((e) -> {
            state.buscarNotificacoes();
        });
        
        this.view.getBtnConfigurar().addActionListener((e) -> {
            state.configurar();
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
