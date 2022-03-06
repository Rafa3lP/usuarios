/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.presenter;

import br.ufes.usuarios.model.NotificacaoTableModel;
import br.ufes.usuarios.observer.Observer;
import br.ufes.usuarios.service.UsuarioService;
import br.ufes.usuarios.view.BuscarNotificacaoView;
import javax.swing.JTable;

/**
 *
 * @author Rafael
 */
public class BuscarNotificacaoPresenter implements Observer {
    private BuscarNotificacaoView view;
    private MainPresenter mainPresenter;
    private JTable tabelaNotificacoes;
    private NotificacaoTableModel modelo;
    private UsuarioService usuarioService;

    public BuscarNotificacaoPresenter(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
        this.view = new BuscarNotificacaoView();
        this.tabelaNotificacoes = this.view.getTabelaNotificacoes();
        this.usuarioService = UsuarioService.getInstancia();
        this.usuarioService.registerObserver(this);
        lerTabela();
        
        getView().getBtnFechar().addActionListener((e) -> {
            this.view.dispose();
            this.usuarioService.removeObserver(this);
        });
        
        getView().getBtnVisualizar().addActionListener((e) -> {
            new ManterNotificacaoPresenter(
                mainPresenter,
                modelo.getNotificacao(tabelaNotificacoes.getSelectedRow())
            );
        });
        
        this.mainPresenter.addToDesktopPane(view);
        this.view.setVisible(true);
    }
    
    public BuscarNotificacaoView getView() {
        return this.view;
    }
    
    private void lerTabela() {
        modelo = new NotificacaoTableModel(Application.getSession().getUsuario().getNotificacoes());
        tabelaNotificacoes.setModel(modelo);
    }

    @Override
    public void update() {
        lerTabela();
    }
    
    
}
