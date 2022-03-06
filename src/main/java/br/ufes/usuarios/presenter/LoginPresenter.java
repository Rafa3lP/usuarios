/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.presenter;

import br.ufes.usuarios.logger.Log;
import br.ufes.usuarios.logger.LogError;
import br.ufes.usuarios.logger.LogInfo;
import br.ufes.usuarios.model.Usuario;
import br.ufes.usuarios.service.UsuarioService;
import br.ufes.usuarios.view.LoginView;
import java.awt.Frame;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafael
 */
public class LoginPresenter {
    private LoginView view;
    private UsuarioService usuarioService;
    private MainPresenter mainPresenter;

    public LoginPresenter(MainPresenter main) {
        this.mainPresenter = main;
        this.usuarioService = UsuarioService.getInstancia();
        this.view = new LoginView(mainPresenter.getView(), true);
        this.view.getLblErro().setText(" ");
        
        this.view.getBtnFechar().addActionListener((e) -> {
            fechar();
        });
        
        this.view.getBtnEntrar().addActionListener((e) -> {
            try {
                entrar();
            }catch(RuntimeException ex) {
                exibeErro(ex.getMessage());
            }
        });
        
        this.view.getBtnCriarConta().addActionListener((e) -> {
            this.view.dispose();
            new ManterUsuarioPresenter(this.mainPresenter, null);
        });
        
        this.view.setVisible(true);
        
    }
    
    private void fechar() {
        this.view.dispose();
        System.exit(0);
    }
    
    private void entrar() {
        String usuario = this.view.getTxtUsuario().getText();
        String senha = new String(this.view.getTxtSenha().getPassword());
        
        Usuario u = usuarioService.fazerLogin(usuario, senha);
        Application.getSession().setUsuario(u);
        mainPresenter.setUsuario();
        this.view.dispose();
        
    }
    
    private void exibeErro(String erro) {
        new Thread() {
            @Override
            public void run() {
                try {
                    view.getLblErro().setText(erro);
                    currentThread().sleep(3000);
                    view.getLblErro().setText(" ");
                } catch (InterruptedException ex) {
                    Logger.getLogger(LoginPresenter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();
       
    }
    
}
