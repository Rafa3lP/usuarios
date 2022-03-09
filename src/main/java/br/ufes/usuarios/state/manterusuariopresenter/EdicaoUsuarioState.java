/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.state.manterusuariopresenter;

import br.ufes.usuarios.command.manterusuario.AtualizarUsuarioCommand;
import br.ufes.usuarios.model.Usuario;
import br.ufes.usuarios.presenter.ManterUsuarioPresenter;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael
 */
public class EdicaoUsuarioState extends ManterUsuarioPresenterState {
    private final Usuario usuario;
    
    public EdicaoUsuarioState(ManterUsuarioPresenter presenter, Usuario usuario) {
        super(presenter);
        this.usuario = usuario;
        this.view.setTitle("Editar Usuário");
        this.view.getBtnEditar().setVisible(false);
        this.view.getBtnSalvar().setVisible(true);
        this.view.getBtnExcluir().setVisible(false);
        this.view.getChkAdm().setVisible(true);
        this.view.getTxtSenha().setVisible(true);
        this.view.getLblSenha().setVisible(true);
        this.view.getBtnCancelar().setVisible(true);
        
        this.view.getTxtDataCadastro().setEnabled(false);
        this.view.getChkAdm().setEnabled(true);
        this.view.getTxtNome().setEnabled(true);
        this.view.getTxtUsuario().setEnabled(true);
        
    }
    
    @Override
    public void salvar() {
        new AtualizarUsuarioCommand(presenter, usuario).executar();
        
    }
    
    @Override
    public void cancelar() {
        this.presenter.setState(new VisualizacaoUsuarioState(presenter, usuario));
    }
    
}
