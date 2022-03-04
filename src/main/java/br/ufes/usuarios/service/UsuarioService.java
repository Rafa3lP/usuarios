/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.service;

import br.ufes.usuarios.dao.INotificacaoDAO;
import br.ufes.usuarios.dao.INotificacaoDAOFactory;
import br.ufes.usuarios.dao.IUsuarioDAO;
import br.ufes.usuarios.dao.IUsuarioDAOFactory;
import br.ufes.usuarios.dao.NotificacaoDAOFactory;
import br.ufes.usuarios.dao.UsuarioDAOFactory;
import br.ufes.usuarios.model.Notificacao;
import br.ufes.usuarios.model.Usuario;
import br.ufes.usuarios.presenter.Application;
import com.lambdaworks.crypto.SCryptUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rafael
 */
public class UsuarioService {
    private IUsuarioDAOFactory usuarioDAOFactory;
    private IUsuarioDAO usuarioDAO;
    private INotificacaoDAOFactory notificacaoDAOfactory;
    private INotificacaoDAO notificacaoDAO;
    private List<Usuario> listaUsuarios;
    private static UsuarioService instancia;

    private UsuarioService() {
        this.usuarioDAOFactory = new UsuarioDAOFactory(); 
        this.usuarioDAO = this.usuarioDAOFactory.cria("sqlite");
        
        this.notificacaoDAOfactory = new NotificacaoDAOFactory();
        this.notificacaoDAO = this.notificacaoDAOfactory.cria("sqlite");
        
        this.listaUsuarios = new ArrayList<>();
        lerLista();
    }
    
    private void lerLista() {
        this.listaUsuarios = usuarioDAO.lerTodos();
        for(Usuario usuario: listaUsuarios) {
            usuario.setNotificacoes(
                this.getNotificacoes(usuario)
            );
        }
    }
    
    public static UsuarioService getInstancia() {
        if (instancia == null) {
            instancia = new UsuarioService();
        }
        return instancia;
    }
    
    public void criar(Usuario usuario) {
        String senha = SCryptUtil.scrypt(
            usuario.getSenha(), 
            16384,
            8, 
            1
        );
        usuario.setSenha(senha);
        usuarioDAO.criar(usuario);
        lerLista();
    }
    
    public Usuario fazerLogin(String usuario, String senha) {
        Usuario u = usuarioDAO.lerPorUsuario(usuario);
        u.setNotificacoes(this.getNotificacoes(u));
        if(SCryptUtil.check(senha, u.getSenha())) {
            return u;
        }else {
            throw new RuntimeException("Credenciais incorretas");
        }
    }
    
    public List<Usuario> getListaUsuarios(String filtroNome) {
        if(filtroNome == null) return this.listaUsuarios;
        return usuarioDAO.buscaUsuariosPorNome(filtroNome);
    }
    
    public Usuario lerPorId(Long id) {
        for(Usuario u: listaUsuarios) {
            if(u.getId().longValue() == id.longValue()) return u;
        }
        
        throw new RuntimeException("Usuario não encontrado");
    }
    
    public void atualizar(Usuario usuario) {
        usuarioDAO.atualizar(usuario);
        lerLista();
    }
    
    public void atualizarComSenha(Usuario usuario) {
        String senha = SCryptUtil.scrypt(
            usuario.getSenha(), 
            16384,
            8, 
            1
        );
        usuario.setSenha(senha);
        usuarioDAO.atualizar(usuario);
        lerLista();
    }
    
    public void deletar(Usuario usuario) {
        usuarioDAO.deletar(usuario.getId());
        lerLista();
    }

    public void enviarNotificacao(Notificacao notificacao) {
        notificacao.setIdRemetente(Application.getSession().getUsuario().getId());
        notificacaoDAO.criar(notificacao);
    }
    
    public void lerNotificacao(Notificacao notificacao) {
        notificacaoDAO.marcarComoLida(notificacao);
        lerLista();
    }
    
    public List<Notificacao> getNotificacoes(Usuario usuario) {
        return notificacaoDAO.getNotificacoes(usuario, false);
    }
    
    public List<Notificacao> getNotificacoesLidas(Usuario usuario) {
        return notificacaoDAO.getNotificacoes(usuario, true);
    }
    
}
