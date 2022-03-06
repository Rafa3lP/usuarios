/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.session;

import br.ufes.usuarios.model.Usuario;
import br.ufes.usuarios.service.UsuarioService;

/**
 *
 * @author Rafael
 */
public class Session {
    private static Session instancia;
    private Long idUsuario;
    private UsuarioService usuarioService;
    private static boolean autenticado;

    private Session() {
        usuarioService = UsuarioService.getInstancia();
        this.idUsuario = null;
        this.autenticado = false;
    }
    
    public static Session getInstancia() {
        if(instancia == null) {
            return new Session();
        }
        return instancia;
    }

    public Usuario getUsuario() {
        if(idUsuario == null) return null;
        return usuarioService.lerPorId(idUsuario);
    }

    public void setUsuario(Usuario usuario) {
        this.idUsuario = usuario.getId();
        setAutenticado(true);
    }

    public static boolean isAutenticado() {
        return autenticado;
    }

    public static void setAutenticado(boolean autenticado) {
        Session.autenticado = autenticado;
    }
    
}
