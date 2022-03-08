/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.state.manterusuariopresenter;

import br.ufes.usuarios.command.usuario.SalvarAdministradorCommand;
import br.ufes.usuarios.command.usuario.SalvarUsuarioCommand;
import br.ufes.usuarios.model.Usuario;
import br.ufes.usuarios.presenter.Application;
import br.ufes.usuarios.presenter.LoginPresenter;
import br.ufes.usuarios.presenter.ManterUsuarioPresenter;
import br.ufes.usuarios.service.UsuarioService;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael
 */
public class InclusaoUsuarioState extends ManterUsuarioPresenterState {
    
    public InclusaoUsuarioState(ManterUsuarioPresenter presenter) {
        super(presenter);
        this.view.setTitle("Criar Usuário");
        this.view.getLblDataCadastro().setVisible(false);
        this.view.getTxtDataCadastro().setVisible(false);
        this.view.getBtnExcluir().setVisible(false);
        this.view.getBtnEditar().setVisible(false);
        this.view.getBtnCancelar().setVisible(false);
        this.view.getChkAdm().setVisible(false);
    }
    
    @Override
    public void salvar() {
        Usuario usuario = this.getUsuarioFromFields();
        
        // se está autenticado é um administrador
        if(Application.getSession().isAutenticado()) {
            new SalvarUsuarioCommand(usuario).executar();
            JOptionPane.showMessageDialog(this.view, "Usuario Inserido", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // se não está autenticado e a lista de usuarios está vazia, salvar administrador
            if(UsuarioService.getInstancia().getListaUsuarios(null).isEmpty()) {
                new SalvarAdministradorCommand(usuario).executar();
                JOptionPane.showMessageDialog(
                    this.view, 
                    "Usuario Administrador criado com sucesso!", 
                    "Sucesso", 
                    JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                // se não é uma solicitação de usuario
                new SalvarUsuarioCommand(usuario).executar();
                JOptionPane.showMessageDialog(
                    this.view, 
                    "Uma solicitação de aprovação foi enviada ao administrador", 
                    "Solicitação enviada", 
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
            
            fechar();
            // solicitar autenticacao
            new LoginPresenter(presenter.getMainPresenter());
        }
        
        limpaCampos();
        
    }
    
}
