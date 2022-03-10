/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.presenter;

import br.ufes.usuarios.model.NotificacaoTableModel;
import br.ufes.usuarios.observer.Observer;
import br.ufes.usuarios.service.UsuarioService;
import br.ufes.usuarios.view.BuscarNotificacaoView;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
        
        this.view.getBtnVisualizar().setEnabled(false);
        
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
        
        tabelaNotificacoes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(tabelaNotificacoes.getSelectedRow() > -1) {
                    getView().getBtnVisualizar().setEnabled(true);
                }else{
                    getView().getBtnVisualizar().setEnabled(false);
                }
            }
        });
        
        this.mainPresenter.addToDesktopPane(view);
        this.view.setVisible(true);
    }
    
    public BuscarNotificacaoView getView() {
        return this.view;
    }
    
    private void lerTabela() {
        try {
            modelo = new NotificacaoTableModel(Application.getSession().getUsuario().getNotificacoes());
            tabelaNotificacoes.setModel(modelo);
        } catch(RuntimeException ex) {
            JOptionPane.showMessageDialog(getView(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void update() {
        lerTabela();
    }
    
    
}
