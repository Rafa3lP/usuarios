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
import br.ufes.usuarios.logger.Log;
import br.ufes.usuarios.logger.LogInfo;
import br.ufes.usuarios.model.Notificacao;
import br.ufes.usuarios.model.Usuario;
import br.ufes.usuarios.observer.Observable;
import br.ufes.usuarios.presenter.Application;
import com.lambdaworks.crypto.SCryptUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rafael
 */
public class UsuarioService extends Observable {
    private final IUsuarioDAOFactory usuarioDAOFactory;
    private final IUsuarioDAO usuarioDAO;
    private final INotificacaoDAOFactory notificacaoDAOfactory;
    private final INotificacaoDAO notificacaoDAO;
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
        notifyObservers();
    }
    
    public static UsuarioService getInstancia() {
        if (instancia == null) {
            instancia = new UsuarioService();
        }
        return instancia;
    }
    
    public void solicitarAprovacao(Usuario usuario) {
        for(Usuario u: listaUsuarios) {
            if(u.getNivelDeAcesso() == Usuario.ACESSO_ADMINISTRADOR) {
                enviarNotificacao(
                    new Notificacao(
                        usuario.getId(),
                        u.getId(), 
                        "APROVACAO DE USUARIO", 
                        "CARO ADM, SOLICITO QUE APROVE O USUARIO " 
                            + usuario.getUsuario() + " DE NOME " 
                            + usuario.getNome(),
                        true
                    )
                );
            }
        }
    }
    
    public void criarAdministrador(Usuario usuario) {
        String senha = SCryptUtil.scrypt(
            usuario.getSenha(), 
            16384,
            8, 
            1
        );
        usuario.setSenha(senha);
        usuario.setNivelDeAcesso(Usuario.ACESSO_ADMINISTRADOR);
        usuario.setId(usuarioDAO.criar(usuario));
        aprovarUsuario(usuario.getId());
        Application.getLogger().grava(
            new LogInfo(usuario, usuario, Log.OPERACAO_INCLUSAO)
        );
    }
    
    public void criarUsuario(Usuario usuario) {
        String senha = SCryptUtil.scrypt(
            usuario.getSenha(), 
            16384,
            8, 
            1
        );
        usuario.setSenha(senha);
        usuario.setNivelDeAcesso(Usuario.ACESSO_NORMAL);
        usuario.setId(usuarioDAO.criar(usuario));
        if(Application.getSession().isAutenticado()) {
            aprovarUsuario(usuario.getId());
            Application.getLogger().grava(
                new LogInfo(usuario, Application.getSession().getUsuario(), Log.OPERACAO_INCLUSAO)
            );
        } else {
            solicitarAprovacao(usuario);
            Application.getLogger().grava(
                new LogInfo(usuario, usuario, Log.OPERACAO_INCLUSAO)
            );
        }
     
    }
    
    public void aprovarUsuario(Long idUsuario) {
        usuarioDAO.aprovar(idUsuario);
        lerLista();
    }
    
    public Usuario fazerLogin(String usuario, String senha) {
        Usuario u = usuarioDAO.lerPorUsuario(usuario);
        if(u == null) throw new RuntimeException("Usuario incorreto!");
        if(SCryptUtil.check(senha, u.getSenha())) {
            if(!u.isAprovado()) throw new RuntimeException("A solicitação de aprovação ainda não foi aceita!");
            return lerPorId(u.getId());
        }else {
            throw new RuntimeException("Senha Incorreta");
        }
    }
    
    public List<Usuario> getListaUsuarios(String filtroNome) {
        if(filtroNome == null) return this.listaUsuarios;
        return usuarioDAO.buscaUsuariosPorNome(filtroNome);
    }
    
    public Usuario lerPorId(Long id) {
        Usuario usuario = usuarioDAO.lerPorId(id);
        usuario.setNotificacoes(this.getNotificacoes(usuario));
        return usuario;
    }
    
    public void atualizar(Usuario usuario) {
        usuarioDAO.atualizar(usuario);
        lerLista();
        Application.getLogger().grava(
            new LogInfo(usuario, Application.getSession().getUsuario(), Log.OPERACAO_ALTERACAO)
        );
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
        Application.getLogger().grava(
            new LogInfo(usuario, Application.getSession().getUsuario(), Log.OPERACAO_ALTERACAO_SENHA)
        );
    }
    
    public void deletar(Usuario usuario) {
        usuarioDAO.deletar(usuario.getId());
        lerLista();
        Application.getLogger().grava(
            new LogInfo(usuario, Application.getSession().getUsuario(), Log.OPERACAO_EXCLUSAO)
        );
    }

    public void enviarNotificacao(Notificacao notificacao) {
        notificacaoDAO.criar(notificacao);
        lerLista();
        if(Application.getSession().isAutenticado()) {
            Application.getLogger().grava(
                new LogInfo(lerPorId(notificacao.getIdDestinatario()), Application.getSession().getUsuario(), Log.OPERACAO_ENVIO_NOTIFICACAO)
            );
        } else {
            Usuario usuario = lerPorId(notificacao.getIdDestinatario());
            Application.getLogger().grava(
                new LogInfo(usuario, usuario, Log.OPERACAO_ENVIO_NOTIFICACAO)
            );
        }
        
    }
    
    public void lerNotificacao(Notificacao notificacao) {
        notificacaoDAO.marcarComoLida(notificacao);
        lerLista();
        Usuario usuario = Application.getSession().getUsuario();
        Application.getLogger().grava(
            new LogInfo(usuario, usuario, Log.OPERACAO_LEITURA_NOTIFICACAO)
        );
    }
    
    public List<Notificacao> getNotificacoes(Usuario usuario) {
        return notificacaoDAO.getNotificacoes(usuario, false);
    }
    
    public List<Notificacao> getNotificacoesLidas(Usuario usuario) {
        return notificacaoDAO.getNotificacoes(usuario, true);
    }

    public void deletarNotificacao(Notificacao notificacao) {
        notificacaoDAO.deletar(notificacao.getIdNotificacao());
        lerLista();
    }
    
}
