/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.presenter;

import br.ufes.usuarios.model.Usuario;
import br.ufes.usuarios.view.MainView;
import com.pss.senha.validacao.ValidadorSenha;
import java.awt.Component;

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
        
        this.view.getBtnCadastrar().addActionListener((e) -> {
            cadastrar();
        });
        
        this.view.getBtnBuscar().addActionListener((e) -> {
            buscar();
        });
        
        this.view.getBtnNotificacoes().addActionListener((e) -> {
            new BuscarNotificacaoPresenter(this);
        });
        
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
    
    public void setUsuario() {
        this.usuario = Application.getSession().getUsuario();
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
        setNumNotificacoes();
    }
    
    public void addToDesktopPane(Component component) {
        this.view.getDesktopPane().add(component);
    }
    
    private void cadastrar() {
        new ManterUsuarioPresenter(this, null);
    }
    
    private void buscar() {
        new BuscarUsuarioPresenter(this);
    }
    
    private void setNumNotificacoes() {
        this.view.getBtnNotificacoes().setText(Integer.toString(usuario.getNotificacoes().size()));
    }
    
}
