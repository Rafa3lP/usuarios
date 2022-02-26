/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.service;

import br.ufes.usuarios.dao.IUsuarioDAO;
import br.ufes.usuarios.dao.IUsuarioDAOFactory;
import br.ufes.usuarios.model.Usuario;
import com.lambdaworks.crypto.SCryptUtil;
import java.io.IOException;

/**
 *
 * @author Rafael
 */
public class UsuarioService {
    private IUsuarioDAOFactory factory;
    private IUsuarioDAO dao;

    public UsuarioService(IUsuarioDAOFactory factory, String tipo) {
        this.factory = factory;
        this.dao = this.factory.cria(tipo);
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
    }
    
    public Usuario fazerLogin(String usuario, String senha) {
        Usuario u = dao.lerPorUsuario(usuario);
        if(SCryptUtil.check(senha, u.getSenha())) {
            return u;
        }else {
            throw new RuntimeException("Credenciais incorretas");
        }
    }
    
}
