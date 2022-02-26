/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.presenter;

import br.ufes.usuarios.model.Usuario;
import br.ufes.usuarios.view.MainView;
import com.pss.senha.validacao.ValidadorSenha;
import java.awt.Component;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Rafael
 */
public class MainPresenter {
    private MainView view;
    private ValidadorSenha validator;
    private Usuario usuario;
    public MainPresenter() {
        this.view = new MainView();
        this.view.setVisible(true);
        this.validator = new ValidadorSenha();
        /*List<String> Erros = this.validator.validar("R@fa835241");
        for(String erro: Erros) {
            System.out.println(erro);
        }
        if (Erros.isEmpty()) {
            System.out.println("Senha boa chará");
        }*/
        
        new LoginPresenter(this);
        
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        this.view.getLblUsuario().setText(this.usuario.getNome());
        String tipo;
        switch(this.usuario.getNivelDeAcesso()) {
            case 1:
                tipo = "Administrador";
                break;
            case 2:
                tipo = "Normal";
                break;
            default:
                tipo = "";
                break;
        }
        this.view.getLblTipo().setText(tipo);
    }
    
    public void addToDesktopPane(Component component) {
        this.view.getDesktopPane().add(component);
    }
    
}
