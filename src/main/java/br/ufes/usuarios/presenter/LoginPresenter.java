/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.presenter;

import br.ufes.usuarios.dao.UsuarioDAOFactory;
import br.ufes.usuarios.service.UsuarioService;
import br.ufes.usuarios.view.LoginView;
import com.lambdaworks.crypto.SCryptUtil;

/**
 *
 * @author Rafael
 */
public class LoginPresenter {
    private LoginView view;
    private UsuarioService usuarioService;

    public LoginPresenter() {
        this.usuarioService = new UsuarioService(new UsuarioDAOFactory(), "sqlite");
        this.view = new LoginView();
        this.view.getLblErro().setVisible(false);
        
        this.view.getBtnFechar().addActionListener((e) -> {
            fechar();
        });
        
        this.view.getBtnEntrar().addActionListener((e) -> {
            String usuario = this.view.getTxtUsuario().getText();
            String senha = SCryptUtil.scrypt(
                new String(
                    this.view.getTxtSenha().getPassword()
                ), 
                16384, 
                8, 
                1
            );
            System.out.println("Usuario: " + usuario + "\nSenha: " + senha);
            System.out.println(SCryptUtil.check("12345678", "$s0$e0801$gMG4pvlimE3e0K18Jh91+Q==$0GmISi9SC49S1nDqe5YEry1K45udo0F384QQGKTUhx4="));
        });
        
        this.view.getBtnCriarConta().addActionListener((e) -> {
            this.view.dispose();
            new ManterUsuarioPresenter();
        });
        
        this.view.setVisible(true);
        
    }
    
    private void fechar() {
        this.view.dispose();
        System.exit(0);
    }
    
}
