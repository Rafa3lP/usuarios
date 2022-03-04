/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.state;

import br.ufes.usuarios.command.SalvarUsuarioCommand;
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
    }
    
    @Override
    public void salvar() {
        new SalvarUsuarioCommand(
            this.getUsuarioFromFields()
        ).executar();
        
        JOptionPane.showMessageDialog(this.view, "Usuario Inserido", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        
        limpaCampos();
        
    }
    
}
