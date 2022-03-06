/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.state.manterusuariopresenter;

import br.ufes.usuarios.command.AtualizarUsuarioCommand;
import br.ufes.usuarios.model.Usuario;
import br.ufes.usuarios.presenter.ManterUsuarioPresenter;
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
        Usuario usuarioAtualizado = getUsuarioFromFields();
        usuarioAtualizado.setId(this.usuario.getId());
        usuarioAtualizado.setDataCadastro(this.usuario.getDataCadastro());
        
        if(usuarioAtualizado.getSenha().trim().isEmpty()) {
            usuarioAtualizado.setSenha(this.usuario.getSenha());
            new AtualizarUsuarioCommand(usuarioAtualizado, false).executar();
            
        } else {
            int escolha = JOptionPane.showConfirmDialog(
                view, 
                "Deseja realmente alterar a senha?", 
                "Confirmação", 
                JOptionPane.YES_NO_OPTION
            );
            if(escolha == JOptionPane.YES_OPTION) {
                new AtualizarUsuarioCommand(usuarioAtualizado, true).executar();
                this.presenter.setState(new VisualizacaoUsuarioState(presenter, usuarioAtualizado));
            } else {
                cancelar();
                return;
            }
            
        }
        
        JOptionPane.showMessageDialog(
            this.view, 
            "Usuario Atualizado", 
            "Sucesso", 
            JOptionPane.INFORMATION_MESSAGE
        );
        
        this.presenter.setState(new VisualizacaoUsuarioState(presenter, usuarioAtualizado));
        
    }
    
    @Override
    public void cancelar() {
        this.presenter.setState(new VisualizacaoUsuarioState(presenter, this.usuario));
    }
    
}
