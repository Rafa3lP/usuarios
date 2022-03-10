/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.command.manterusuario;

import br.ufes.usuarios.model.Usuario;
import br.ufes.usuarios.presenter.ManterUsuarioPresenter;
import br.ufes.usuarios.state.manterusuariopresenter.VisualizacaoUsuarioState;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael
 */
public class AtualizarUsuarioCommand extends ManterUsuarioCommand {
    private Usuario usuario;
    
    public AtualizarUsuarioCommand(ManterUsuarioPresenter presenter, Usuario usuario) {
        super(presenter);
        this.usuario = usuario;
    }
   
    @Override
    public void executar() {
        String nome = this.view.getTxtNome().getText();
        String usuario = this.view.getTxtUsuario().getText();
        String senha = this.view.getTxtSenha().getText();
        boolean admin = this.view.getChkAdm().isSelected();

        Usuario usuarioAtualizado = new Usuario(nome, usuario, senha, LocalDate.now(), admin);
        usuarioAtualizado.setId(this.usuario.getId());
        usuarioAtualizado.setDataCadastro(this.usuario.getDataCadastro());
        
        // se o campo de senha estiver em branco não atualiza a senha
        if(usuarioAtualizado.getSenha().trim().isEmpty()) {
            usuarioAtualizado.setSenha(this.usuario.getSenha());
            validar(usuarioAtualizado, false);
            service.atualizar(usuarioAtualizado);
            this.presenter.setState(new VisualizacaoUsuarioState(presenter, usuarioAtualizado));
        } else {
            // se o campo de senha estiver preenchido atualiza a senha
            int escolha = JOptionPane.showConfirmDialog(
                view, 
                "Deseja realmente alterar a senha?", 
                "Confirmação", 
                JOptionPane.YES_NO_OPTION
            );
            if(escolha == JOptionPane.YES_OPTION) {
                validar(usuarioAtualizado, true);
                service.atualizar(usuarioAtualizado);
                service.alterarSenha(usuarioAtualizado);
                this.presenter.setState(new VisualizacaoUsuarioState(presenter, usuarioAtualizado));
            } else {
                this.presenter.setState(new VisualizacaoUsuarioState(presenter, this.usuario));
                return;
            }
            
        }
        
        JOptionPane.showMessageDialog(
            this.view, 
            "Usuario Atualizado", 
            "Sucesso", 
            JOptionPane.INFORMATION_MESSAGE
        );

    }
    
}
