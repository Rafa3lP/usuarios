/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.command.manterusuario;

import br.ufes.usuarios.model.Usuario;
import br.ufes.usuarios.presenter.ManterUsuarioPresenter;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael
 */
public class ExcluirUsuarioCommand extends ManterUsuarioCommand {
    private Usuario usuario;
    public ExcluirUsuarioCommand(ManterUsuarioPresenter presenter, Usuario usuario) {
        super(presenter);
        this.usuario = usuario;
    }
 
    @Override
    public void executar() {
       int confirmado = JOptionPane.showConfirmDialog(
            view, 
            "Deseja realmente excluir o usuario?", 
            "Confirmação",
            JOptionPane.YES_NO_OPTION
        );
        if(confirmado == JOptionPane.YES_OPTION) {
            this.service.deletar(this.usuario);
            JOptionPane.showMessageDialog(
                view, 
                "Usuario Excluido com sucesso!",
                "sucesso",
                JOptionPane.INFORMATION_MESSAGE
            );
            this.view.dispose();
        }
       
    }
    
}
