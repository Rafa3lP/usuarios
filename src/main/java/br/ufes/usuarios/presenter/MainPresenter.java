/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.presenter;

import br.ufes.usuarios.model.Usuario;
import br.ufes.usuarios.observer.Observer;
import br.ufes.usuarios.service.UsuarioService;
import br.ufes.usuarios.view.MainView;
import com.pss.senha.validacao.ValidadorSenha;
import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael
 */
public class MainPresenter implements Observer {
    private MainView view;
    private ValidadorSenha validator;
    private Usuario usuario;
    private UsuarioService usuarioService;
    
    public MainPresenter() {
        this.view = new MainView();
        this.view.getBtnUsuarios().setVisible(false);
        usuarioService = UsuarioService.getInstancia();
        
        this.view.getBtnCadastrar().addActionListener((e) -> {
            cadastrar();
        });
        
        this.view.getBtnBuscar().addActionListener((e) -> {
            buscar();
        });
        
        this.view.getBtnNotificacoes().addActionListener((e) -> {
            new BuscarNotificacaoPresenter(this);
        });
        
        this.view.getBtnConfigurar().addActionListener((e) -> {
            new ConfiguracoesPresenter(this);
        });
 
        this.view.setVisible(true);
        
        if(this.usuarioService.getListaUsuarios(null).isEmpty()) {
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
        
    }
    
    public void setUsuario() {
        this.usuario = Application.getSession().getUsuario();
        this.view.getLblUsuario().setText(this.usuario.getNome());
        String tipo;
        switch(this.usuario.getNivelDeAcesso()) {
            case Usuario.ACESSO_ADMINISTRADOR:
                this.view.getBtnUsuarios().setVisible(true);
                tipo = "Administrador";
                break;
            case Usuario.ACESSO_NORMAL:
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
    
    public MainView getView() {
        return this.view;
    }

    @Override
    public void update() {
        setNumNotificacoes();
    }
    
}
