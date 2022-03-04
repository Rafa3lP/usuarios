/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.presenter;

import br.ufes.usuarios.model.NotificacaoTableModel;
import br.ufes.usuarios.model.Usuario;
import br.ufes.usuarios.view.BuscarNotificacaoView;
import javax.swing.JTable;

/**
 *
 * @author Rafael
 */
public class BuscarNotificacaoPresenter {
    private BuscarNotificacaoView view;
    private MainPresenter mainPresenter;
    private JTable tabelaNotificacoes;
    private NotificacaoTableModel modelo;
    private Usuario usuario;

    public BuscarNotificacaoPresenter(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
        this.view = new BuscarNotificacaoView();
        this.tabelaNotificacoes = this.view.getTabelaNotificacoes();

        this.usuario = Application.getSession().getUsuario();
        lerTabela();
        
        getView().getBtnFechar().addActionListener((e) -> {
            this.view.dispose();
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
        modelo = new NotificacaoTableModel(usuario.getNotificacoes());
        tabelaNotificacoes.setModel(modelo);
    }
    
    
}
