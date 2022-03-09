/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.state.manterusuariopresenter;

import br.ufes.usuarios.command.manterusuario.ExcluirUsuarioCommand;
import br.ufes.usuarios.model.Usuario;
import br.ufes.usuarios.presenter.ManterUsuarioPresenter;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Rafael
 */
public class VisualizacaoUsuarioState extends ManterUsuarioPresenterState {
    private final Usuario usuario;
    public VisualizacaoUsuarioState(ManterUsuarioPresenter presenter, Usuario usuario) {
        super(presenter);
        this.usuario = usuario;
        this.view.setTitle("Visualizar Usuário");
        this.view.getBtnEditar().setVisible(true);
        this.view.getBtnCancelar().setVisible(false);
        this.view.getBtnExcluir().setVisible(true);
        this.view.getBtnSalvar().setVisible(false);
        this.view.getChkAdm().setVisible(true);
        this.view.getTxtSenha().setVisible(false);
        this.view.getLblSenha().setVisible(false);
        this.view.getLblDataCadastro().setVisible(true);
        
        this.view.getChkAdm().setEnabled(false);
        this.view.getTxtDataCadastro().setEnabled(false);
        this.view.getTxtNome().setEnabled(false);
        this.view.getTxtUsuario().setEnabled(false);
        
        putUsuario();
        
    }
    
    private void putUsuario() {
        this.view.getChkAdm().setSelected(usuario.isAdmin());
        this.view.getTxtNome().setText(this.usuario.getNome());
        this.view.getTxtUsuario().setText(this.usuario.getUsuario());
        this.view.getTxtDataCadastro().setText(
            this.usuario.getDataCadastro()
                .format(
                    DateTimeFormatter.ofPattern("dd/MM/yyyy")
                )
        );
    }
    
    @Override
    public void editar() {
        this.presenter.setState(new EdicaoUsuarioState(presenter, usuario));
    }
    
    @Override
    public void excluir() {
        new ExcluirUsuarioCommand(presenter, usuario).executar();
    }
    
}
