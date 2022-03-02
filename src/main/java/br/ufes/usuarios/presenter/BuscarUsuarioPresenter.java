/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.presenter;

import br.ufes.usuarios.model.Usuario;
import br.ufes.usuarios.service.UsuarioService;
import br.ufes.usuarios.state.BuscaUsuarioState;
import br.ufes.usuarios.state.BuscarUsuarioPresenterState;
import br.ufes.usuarios.view.BuscarUsuarioView;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rafael
 */
public class BuscarUsuarioPresenter {
    private BuscarUsuarioView view;
    private MainPresenter mainPresenter;
    private BuscarUsuarioPresenterState state;
    private JTable tabelaUsuarios;
    private UsuarioService service;

    public BuscarUsuarioPresenter(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
        this.view = new BuscarUsuarioView();
        this.tabelaUsuarios = getView().getTabelaUsuarios();
        this.service = UsuarioService.getInstancia();
        this.state = new BuscaUsuarioState(this);
        
        lerTabelaUsuarios();
        
        getView().getBtnFechar().addActionListener((e) -> {
            this.state.fechar();
        });
        
        getView().getBtnBuscar().addActionListener((e) -> {
            this.state.buscar();
        });
        
        getView().getBtnVisualizar().addActionListener((e) -> {
            this.state.visualizar();
        });
        
        this.mainPresenter.addToDesktopPane(view);
        this.view.setVisible(true);
    }
    
    private void lerTabelaUsuarios() {
        DefaultTableModel modelo = (DefaultTableModel) this.tabelaUsuarios.getModel();
        modelo.setNumRows(0);

        for(Usuario u: this.service.getListaUsuarios()) {
            modelo.addRow(new Object[]{
                u.getId(),
                u.getNome(),
                u.getDataCadastro()
            });
        }
    }
    
    public BuscarUsuarioView getView() {
        return this.view;
    }
    
    public void setState(BuscarUsuarioPresenterState state) {
        this.state = state;
    }

    public MainPresenter getMainPresenter() {
        return mainPresenter;
    }
    
}
