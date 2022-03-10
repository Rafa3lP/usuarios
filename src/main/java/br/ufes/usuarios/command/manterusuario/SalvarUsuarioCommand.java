/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.command.manterusuario;

import br.ufes.usuarios.model.Usuario;
import br.ufes.usuarios.presenter.ManterUsuarioPresenter;
import br.ufes.usuarios.service.UsuarioService;
import br.ufes.usuarios.session.Session;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael
 */
public class SalvarUsuarioCommand extends ManterUsuarioCommand {
    private Usuario novoUsuario;
    public SalvarUsuarioCommand(ManterUsuarioPresenter presenter) {
        super(presenter);
    }

    @Override
    public void executar() {
        
        // se está autenticado é um administrador
        if(Session.isAutenticado()) {
            salvarUsuario(false);
            JOptionPane.showMessageDialog(this.view, "Usuario Inserido e aprovado", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // se não está autenticado e a lista de usuarios está vazia, salvar administrador
            if(UsuarioService.getInstancia().getListaUsuarios(null).isEmpty()) {
                salvarUsuario(true);
                service.aprovarUsuario(novoUsuario.getId());
                JOptionPane.showMessageDialog(
                    this.view, 
                    "Usuario Administrador criado com sucesso!", 
                    "Sucesso", 
                    JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                // se não é uma solicitação de usuario
                salvarUsuario(false);
                JOptionPane.showMessageDialog(
                    this.view, 
                    "Uma solicitação de aprovação foi enviada ao administrador", 
                    "Solicitação enviada", 
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
            
        }
          
    }
    
    private void salvarUsuario(boolean admin) {
        String nome = this.view.getTxtNome().getText();
        String usuario = this.view.getTxtUsuario().getText();
        String senha = this.view.getTxtSenha().getText();

        novoUsuario = new Usuario(nome, usuario, senha, LocalDate.now(), admin);
        
        validar(novoUsuario, true);
        
        this.service.criarUsuario(novoUsuario);
    }
    
}
