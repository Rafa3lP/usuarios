/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.presenter;

import br.ufes.usuarios.model.Usuario;
import br.ufes.usuarios.observer.Observer;
import br.ufes.usuarios.service.UsuarioService;
import br.ufes.usuarios.view.BuscarUsuarioView;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rafael
 */
public class BuscarUsuarioPresenter implements Observer {
    private BuscarUsuarioView view;
    private MainPresenter mainPresenter;
    private JTable tabelaUsuarios;
    private UsuarioService service;

    public BuscarUsuarioPresenter(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
        this.view = new BuscarUsuarioView();
        this.tabelaUsuarios = getView().getTabelaUsuarios();
        this.service = UsuarioService.getInstancia();
        this.service.registerObserver(this);
        
        lerTabelaUsuarios(null);
        
        getView().getBtnVisualizar().setEnabled(false);
        getView().getBtnEnviarNotificacao().setEnabled(false);
        
        getView().getBtnFechar().addActionListener((e) -> {
            fechar();
            this.service.removeObserver(this);
        });
        
        getView().getBtnBuscar().addActionListener((e) -> {
            try {
                buscar();
            } catch(RuntimeException ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(getView(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            
        });
        
        getView().getBtnVisualizar().addActionListener((e) -> {
            try {
                visualizar();
            } catch(RuntimeException ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(getView(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            
        });
        
        getView().getBtnEnviarNotificacao().addActionListener((e) -> {
            try {
                enviarNotificacao();
            } catch(RuntimeException ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(getView(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        getView().getBtnNovo().addActionListener((e) -> {
            try {
                novo();
            } catch(RuntimeException ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(getView(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        tabelaUsuarios.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(tabelaUsuarios.getSelectedRow() > -1) {
                    getView().getBtnVisualizar().setEnabled(true);
                    getView().getBtnEnviarNotificacao().setEnabled(true);
                }else{
                    getView().getBtnVisualizar().setEnabled(false);
                    getView().getBtnEnviarNotificacao().setEnabled(false);
                }
            }
        });
        
        this.mainPresenter.addToDesktopPane(view);
        this.view.setVisible(true);
    }
    
    private void lerTabelaUsuarios(String filtro) {
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
    
    private BuscarUsuarioView getView() {
        return this.view;
    }
    
    private void fechar() {
        this.view.dispose();
    }
    
    private void visualizar() {
        new ManterUsuarioPresenter(
            mainPresenter,
            service.lerPorId(
                (Long) tabelaUsuarios.getValueAt(
                    tabelaUsuarios.getSelectedRow(),
                    0
                )
            )
        );
    }
  
    private void enviarNotificacao() {
        Usuario destinatario = service.lerPorId(
            (Long) tabelaUsuarios.getValueAt(
                tabelaUsuarios.getSelectedRow(),
                0
            )
        );
        
        new ManterNotificacaoPresenter(
            mainPresenter, 
            destinatario
        );

    }
  
    private void buscar() {
        String nomeBuscado = this.view.getTxtNome().getText();
        if(nomeBuscado.trim().isEmpty()) {
            lerTabelaUsuarios(null);
        } else {
            lerTabelaUsuarios(nomeBuscado);
        }
    }
   
    private void novo() {
        new ManterUsuarioPresenter(mainPresenter, null);
    }
 
    @Override
    public void update() {
        lerTabelaUsuarios(null);
    }
    
}
