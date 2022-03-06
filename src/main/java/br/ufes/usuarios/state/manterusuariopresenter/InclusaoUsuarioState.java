/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.state.manterusuariopresenter;

import br.ufes.usuarios.command.SalvarUsuarioCommand;
import br.ufes.usuarios.model.Usuario;
import br.ufes.usuarios.presenter.Application;
import br.ufes.usuarios.presenter.LoginPresenter;
import br.ufes.usuarios.presenter.ManterUsuarioPresenter;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael
 */
public class InclusaoUsuarioState extends ManterUsuarioPresenterState {
    
    public InclusaoUsuarioState(ManterUsuarioPresenter presenter) {
        super(presenter);
        this.view.getLblDataCadastro().setVisible(false);
        this.view.getTxtDataCadastro().setVisible(false);
        this.view.getBtnExcluir().setVisible(false);
        this.view.getBtnEditar().setVisible(false);
        this.view.getBtnCancelar().setVisible(false);
        this.view.getChkAdm().setVisible(false);
    }
    
    @Override
    public void salvar() {
        new SalvarUsuarioCommand(
            this.getUsuarioFromFields()
        ).executar();
        
        if(Application.getSession().isAutenticado()) {
            JOptionPane.showMessageDialog(this.view, "Usuario Inserido", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this.view, "Uma solicitação de aprovação foi enviada aos administradores", "Solicitação enviada", JOptionPane.INFORMATION_MESSAGE);
            fechar();
            new LoginPresenter(presenter.getMainPresenter());
        }
        
        limpaCampos();
        
    }
    
}
