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
import br.ufes.usuarios.logger.LogError;
import br.ufes.usuarios.logger.LogInfo;
import br.ufes.usuarios.model.Notificacao;
import br.ufes.usuarios.model.Usuario;
import br.ufes.usuarios.observer.Observable;
import br.ufes.usuarios.presenter.Application;
import br.ufes.usuarios.session.Session;
import com.lambdaworks.crypto.SCryptUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rafael
 */
public class UsuarioService extends Observable {
    
    private final int SCRIPT_N = 16384;
    private final int SCRIPT_R = 8;
    private final int SCRIPT_P = 1;
    
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
            if(u.isAdmin()) {
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
    
    public void criarUsuario(Usuario usuario) {
        try {
            if (usuarioDAO.lerPorUsuario(usuario.getUsuario()) != null) {
                throw new RuntimeException("Este nome de usuário já existe!");
            }
            String senha = SCryptUtil.scrypt(
                    usuario.getSenha(),
                    SCRIPT_N,
                    SCRIPT_R,
                    SCRIPT_P
            );
            usuario.setSenha(senha);
            usuario.setId(usuarioDAO.criar(usuario));
            // se está autenticado é um adm
            if (Session.isAutenticado()) {
                aprovarUsuario(usuario.getId());
                Application.getLogger().grava(
                    new LogInfo(
                        usuario, 
                        Application.getSession().getUsuario(), 
                        Log.OPERACAO_INCLUSAO
                    )
                );
            } else {
                solicitarAprovacao(usuario);
                Application.getLogger().grava(
                    new LogInfo(
                        usuario, 
                        usuario, 
                        Log.OPERACAO_INCLUSAO
                    )
                );
            }
        } catch (RuntimeException ex) {
            if (Session.isAutenticado()) {
                Application.getLogger().grava(
                    new LogError(
                        usuario, 
                        Application.getSession().getUsuario(), 
                        Log.OPERACAO_INCLUSAO,
                        ex.getMessage()
                    )
                );
            } else {
                Application.getLogger().grava(
                    new LogError(
                        usuario,
                        usuario, 
                        Log.OPERACAO_INCLUSAO,
                        ex.getMessage()
                    )
                );
            }
            throw new RuntimeException(ex);
        }
     
    }
    
    public void aprovarUsuario(Long idUsuario) {
        usuarioDAO.aprovar(idUsuario);
        lerLista();
    }
    
    public Usuario fazerLogin(String usuario, String senha) {
        try {
            Usuario u = usuarioDAO.lerPorUsuario(usuario);
            if (u == null) {
                throw new RuntimeException("Usuario incorreto!");
            }
            if (SCryptUtil.check(senha, u.getSenha())) {
                if (!u.isAprovado()) {
                    throw new RuntimeException("A solicitação de aprovação ainda não foi aceita!");
                }
                Application.getLogger().grava(
                    new LogInfo(
                        u,
                        u,
                        Log.OPERACAO_AUTORIZACAO
                    )
                );
                return lerPorId(u.getId());
            } else {
                throw new RuntimeException("Senha Incorreta!");
            }
        } catch (RuntimeException ex) {
            Application.getLogger().grava(
                new LogError(
                    null,
                    null,
                    Log.OPERACAO_AUTORIZACAO,
                    ex.getMessage()
                )
            );
            throw new RuntimeException(ex.getMessage());
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
        try {
            if(!lerPorId(usuario.getId()).getUsuario().equals(usuario.getUsuario())) {
                if(usuarioDAO.lerPorUsuario(usuario.getUsuario()) != null) {
                    throw new RuntimeException("Este nome de usuário já existe!");
                }
            }
            usuarioDAO.atualizar(usuario);
            lerLista();
            Application.getLogger().grava(
                new LogInfo(usuario, Application.getSession().getUsuario(), Log.OPERACAO_ALTERACAO)
            );
        } catch(RuntimeException ex) {
            Application.getLogger().grava(
                new LogError(
                    usuario, 
                    Application.getSession().getUsuario(), 
                    Log.OPERACAO_ALTERACAO, 
                    ex.getMessage()
                )
            );
            throw new RuntimeException(ex.getMessage());
        }
        
    }
    
    public void alterarSenha(Usuario usuario) {
        try {
            String senha = SCryptUtil.scrypt(
                    usuario.getSenha(),
                    SCRIPT_N,
                    SCRIPT_R,
                    SCRIPT_P
            );
            usuario.setSenha(senha);
            usuarioDAO.alterarSenha(usuario);
            lerLista();
            Application.getLogger().grava(
                new LogInfo(
                    usuario, 
                    Application.getSession().getUsuario(), 
                    Log.OPERACAO_ALTERACAO_SENHA
                )
            );
        } catch (RuntimeException ex) {
            Application.getLogger().grava(
                new LogError(
                    usuario, 
                    Application.getSession().getUsuario(), 
                    Log.OPERACAO_ALTERACAO_SENHA,
                    ex.getMessage()
                )
            );
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    public void deletar(Usuario usuario) {
        try {
            usuarioDAO.deletar(usuario.getId());
            lerLista();
            Application.getLogger().grava(
                new LogInfo(
                    usuario, 
                    Application.getSession().getUsuario(), 
                    Log.OPERACAO_EXCLUSAO
                )
            );
        } catch (RuntimeException ex) {
            Application.getLogger().grava(
                new LogError(
                    usuario, 
                    Application.getSession().getUsuario(), 
                    Log.OPERACAO_EXCLUSAO,
                    ex.getMessage()
                )
            );
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void enviarNotificacao(Notificacao notificacao) {
        try {
            notificacaoDAO.criar(notificacao);
            lerLista();
            if (Session.isAutenticado()) {
                Application.getLogger().grava(
                    new LogInfo(
                        lerPorId(notificacao.getIdDestinatario()), 
                        Application.getSession().getUsuario(), 
                        Log.OPERACAO_ENVIO_NOTIFICACAO
                    )
                );
            } else {
                Usuario usuario = lerPorId(notificacao.getIdDestinatario());
                Application.getLogger().grava(
                    new LogInfo(
                        usuario, 
                        usuario, 
                        Log.OPERACAO_ENVIO_NOTIFICACAO
                    )
                );
            }
        } catch (RuntimeException ex) {
            if (Session.isAutenticado()) {
                Application.getLogger().grava(
                    new LogError(
                        lerPorId(notificacao.getIdDestinatario()), 
                        Application.getSession().getUsuario(), 
                        Log.OPERACAO_ENVIO_NOTIFICACAO,
                        ex.getMessage()
                    )
                );
            } else {
                Usuario usuario = lerPorId(notificacao.getIdDestinatario());
                Application.getLogger().grava(
                    new LogError(
                        usuario, 
                        usuario, 
                        Log.OPERACAO_ENVIO_NOTIFICACAO,
                        ex.getMessage()
                    )
                );
            }
            throw new RuntimeException(ex.getMessage());
        }
        
    }
    
    public void lerNotificacao(Notificacao notificacao) {
        try {
            notificacaoDAO.marcarComoLida(notificacao);
            lerLista();
            Usuario usuario = Application.getSession().getUsuario();
            Application.getLogger().grava(
                new LogInfo(
                    usuario, 
                    usuario, 
                    Log.OPERACAO_LEITURA_NOTIFICACAO
                )
            );
        } catch (Exception ex) {
            Usuario usuario = Application.getSession().getUsuario();
            Application.getLogger().grava(
                new LogError(
                    usuario, 
                    usuario, 
                    Log.OPERACAO_LEITURA_NOTIFICACAO,
                    ex.getMessage()
                )
            );
            throw new RuntimeException(ex.getMessage());
        }
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
