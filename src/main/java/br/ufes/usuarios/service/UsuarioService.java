/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.service;

import br.ufes.usuarios.dao.IUsuarioDAO;
import br.ufes.usuarios.dao.IUsuarioDAOFactory;
import br.ufes.usuarios.dao.UsuarioDAOFactory;
import br.ufes.usuarios.model.Usuario;
import com.lambdaworks.crypto.SCryptUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rafael
 */
public class UsuarioService {
    private IUsuarioDAOFactory factory;
    private IUsuarioDAO dao;
    private List<Usuario> listaUsuarios;
    private static UsuarioService instancia;

    private UsuarioService() {
        this.factory = new UsuarioDAOFactory(); 
        this.dao = this.factory.cria("sqlite");
        this.listaUsuarios = new ArrayList<>();
        lerLista();
    }
    
    private void lerLista() {
        this.listaUsuarios = dao.lerTodos();
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
        dao.criar(usuario);
        lerLista();
    }
    
    public Usuario fazerLogin(String usuario, String senha) {
        Usuario u = dao.lerPorUsuario(usuario);
        if(SCryptUtil.check(senha, u.getSenha())) {
            return u;
        }else {
            throw new RuntimeException("Credenciais incorretas");
        }
    }
    
    public List<Usuario> getListaUsuarios() {
        return this.listaUsuarios;
    }
    
    public Usuario lerPorId(Long id) {
        for(Usuario u: listaUsuarios) {
            if(u.getId() == id) return u;
        }
        
        throw new RuntimeException("Usuario não encontrado");
    }
    
    public void atualizar(Usuario usuario) {
        dao.atualizar(usuario);
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
        dao.atualizar(usuario);
        lerLista();
    }
    
}
