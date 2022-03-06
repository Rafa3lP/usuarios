/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rafael
 */
public class Usuario {
    public static final int ACESSO_ADMINISTRADOR = 1;
    public static final int ACESSO_NORMAL = 2;
    private Long id;
    private String nome;
    private String usuario;
    private String senha;
    private int nivelDeAcesso;
    private LocalDate dataCadastro;
    private List<Notificacao> notificacoes;
    private boolean aprovado;

    public Usuario(Long id, String nome, String usuario, String senha, LocalDate dataCadastro, int nivelDeAcesso, boolean aprovado) {
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.dataCadastro = dataCadastro;
        this.nivelDeAcesso = nivelDeAcesso;
        this.notificacoes = new ArrayList<>();
        this.aprovado = aprovado;
    }

    public Usuario(String nome, String usuario, String senha, LocalDate dataCadastro, int nivelDeAcesso) {
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.dataCadastro = dataCadastro;
        this.nivelDeAcesso = nivelDeAcesso;
        this.notificacoes = new ArrayList<>();
        this.aprovado = false;
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

    public LocalDate getDataCadastro() {
        return this.dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public List<Notificacao> getNotificacoes() {
        return notificacoes;
    }

    public void setNotificacoes(List<Notificacao> notificacoes) {
        this.notificacoes = notificacoes;
    }

    public boolean isAprovado() {
        return aprovado;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }
    
}
