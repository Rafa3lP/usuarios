/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.presenter;

import br.ufes.usuarios.model.Usuario;
import br.ufes.usuarios.observer.Observer;
import br.ufes.usuarios.service.UsuarioService;
import br.ufes.usuarios.state.buscausuariopresenter.BuscaUsuarioState;
import br.ufes.usuarios.state.buscausuariopresenter.BuscarUsuarioPresenterState;
import br.ufes.usuarios.view.BuscarUsuarioView;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rafael
 */
public class BuscarUsuarioPresenter implements Observer {
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
        this.service.registerObserver(this);
        this.state = new BuscaUsuarioState(this);
        
        lerTabelaUsuarios(null);
        
        getView().getBtnFechar().addActionListener((e) -> {
            this.state.fechar();
            this.service.removeObserver(this);
        });
        
        getView().getBtnBuscar().addActionListener((e) -> {
            try {
                this.state.buscar();
            } catch(RuntimeException ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(getView(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            
        });
        
        getView().getBtnVisualizar().addActionListener((e) -> {
            try {
                this.state.visualizar();
            } catch(RuntimeException ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(getView(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            
        });
        
        getView().getBtnEnviarNotificacao().addActionListener((e) -> {
            try {
                this.state.enviarNotificacao();
            } catch(RuntimeException ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(getView(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        getView().getBtnNovo().addActionListener((e) -> {
            try {
                this.state.novo();
            } catch(RuntimeException ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(getView(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        this.mainPresenter.addToDesktopPane(view);
        this.view.setVisible(true);
    }
    
    public void lerTabelaUsuarios(String filtro) {
        DefaultTableModel modelo = (DefaultTableModel) this.tabelaUsuarios.getModel();
        modelo.setNumRows(0);
        
        for(Usuario u: this.service.getListaUsuarios(filtro)) {
            modelo.addRow(new Object[]{
                u.getId(),
                u.getNome(),
                u.getDataCadastro().format(
                    DateTimeFormatter.ofPattern("dd/MM/yyyy")
                ),
                service.getNotificacoes(u).size(),
                service.getNotificacoesLidas(u).size()
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

    @Override
    public void update() {
        lerTabelaUsuarios(null);
    }
    
}
