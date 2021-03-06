/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.presenter;

import br.ufes.usuarios.model.Usuario;
import br.ufes.usuarios.state.manterusuariopresenter.InclusaoUsuarioState;
import br.ufes.usuarios.state.manterusuariopresenter.ManterUsuarioPresenterState;
import br.ufes.usuarios.state.manterusuariopresenter.VisualizacaoUsuarioState;
import br.ufes.usuarios.view.ManterUsuarioView;
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
            this.setState(new VisualizacaoUsuarioState(this, usuario));
        }
        
        getView().getBtnSalvar().addActionListener((e) -> {
            try {
                this.state.salvar();
            } catch(RuntimeException ex) {
                JOptionPane.showMessageDialog(getView(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        getView().getBtnFechar().addActionListener((e) -> {
            try {
                this.state.fechar();
            }catch(RuntimeException ex) {
                JOptionPane.showMessageDialog(getView(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        getView().getBtnCancelar().addActionListener((e) -> {
            try {
                this.state.cancelar();
            } catch(RuntimeException ex) {
                JOptionPane.showMessageDialog(getView(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        getView().getBtnEditar().addActionListener((e) -> {
            try {
                this.state.editar();
            } catch(RuntimeException ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(getView(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
           
        });
        
        getView().getBtnExcluir().addActionListener((e) -> {
            try {
                this.state.excluir();
            } catch(RuntimeException ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(getView(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            
        });
        
        this.mainPresenter.addToDesktopPane(view);
        this.view.setVisible(true);
    }

    public MainPresenter getMainPresenter() {
        return mainPresenter;
    }
    
    public ManterUsuarioView getView() {
        return this.view;
    }
    
    public void setState(ManterUsuarioPresenterState state) {
        this.state = state;
    }
    
}
