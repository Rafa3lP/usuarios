/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.session;

import br.ufes.usuarios.model.Usuario;

/**
 *
 * @author Rafael
 */
public class Session {
    private static Session instancia;
    private Usuario usuario;

    private Session() {
        this.usuario = null;
    }
    
    public static Session getInstancia() {
        if(instancia == null) {
            return new Session();
        }
        return instancia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
