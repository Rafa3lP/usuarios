/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.state.buscausuariopresenter;

import br.ufes.usuarios.model.Usuario;
import br.ufes.usuarios.presenter.BuscarUsuarioPresenter;
import br.ufes.usuarios.presenter.ManterNotificacaoPresenter;
import br.ufes.usuarios.presenter.ManterUsuarioPresenter;
import br.ufes.usuarios.service.UsuarioService;

/**
 *
 * @author Rafael
 */
public class BuscaUsuarioState extends BuscarUsuarioPresenterState {

    private UsuarioService service;
    
    public BuscaUsuarioState(BuscarUsuarioPresenter presenter) {
        super(presenter);
        this.service = UsuarioService.getInstancia();
    }
    
    @Override
    public void visualizar() {
        new ManterUsuarioPresenter(
            this.presenter.getMainPresenter(), 
            service.lerPorId(
                (Long) presenter.getView().getTabelaUsuarios().getValueAt(
                    presenter.getView().getTabelaUsuarios().getSelectedRow(),
                    0
                )
            )
        );
    }
    
    @Override
    public void enviarNotificacao() {
        Usuario destinatario = service.lerPorId(
            (Long) presenter.getView().getTabelaUsuarios().getValueAt(
                presenter.getView().getTabelaUsuarios().getSelectedRow(),
                0
            )
        );
        
        new ManterNotificacaoPresenter(
            this.presenter.getMainPresenter(), 
            destinatario
        );

    }
    
    @Override
    public void buscar() {
        String nomeBuscado = this.view.getTxtNome().getText();
        if(nomeBuscado.trim().isEmpty()) {
            presenter.lerTabelaUsuarios(null);
        } else {
            presenter.lerTabelaUsuarios(nomeBuscado);
        }
    }
    
    @Override
    public void novo() {
        new ManterUsuarioPresenter(this.presenter.getMainPresenter(), null);
    }
    
}
