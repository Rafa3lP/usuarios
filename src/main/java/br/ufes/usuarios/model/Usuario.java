/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.model;

/**
 *
 * @author Rafael
 */
public class Usuario {
    private Long id;
    private String nome;
    private String usuario;
    private String senha;
    private int nivelDeAcesso;

    public Usuario(Long id, String nome, String usuario, String senha, int nivelDeAcesso) {
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.nivelDeAcesso = nivelDeAcesso;
    }

    public Usuario(String nome, String usuario, String senha, int nivelDeAcesso) {
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.nivelDeAcesso = nivelDeAcesso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getNivelDeAcesso() {
        return nivelDeAcesso;
    }

    public void setNivelDeAcesso(int nivelDeAcesso) {
        this.nivelDeAcesso = nivelDeAcesso;
    }
    
}
