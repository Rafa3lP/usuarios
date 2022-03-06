/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.model;

/**
 *
 * @author Rafael
 */
public class Notificacao {
    private Long idNotificacao;
    private Long idRemetente;
    private Long idDestinatario;
    private String titulo;
    private String mensagem;
    private boolean lida;
    private boolean aprovacao;

    public Notificacao(Long idNotificacao, Long idRemetente, Long idDestinatario, String titulo, String mensagem, boolean lida, boolean isAprovacao) {
        this.idNotificacao = idNotificacao;
        this.idRemetente = idRemetente;
        this.idDestinatario = idDestinatario;
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.lida = lida;
        this.aprovacao = isAprovacao;
    }

    public Notificacao(Long idRemetente, Long idDestinatario, String titulo, String mensagem, boolean isAprovacao) {
        this.idRemetente = idRemetente;
        this.idDestinatario = idDestinatario;
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.aprovacao = isAprovacao;
    }

    public Long getIdNotificacao() {
        return idNotificacao;
    }

    public void setIdNotificacao(Long idNotificacao) {
        this.idNotificacao = idNotificacao;
    }

    public Long getIdRemetente() {
        return idRemetente;
    }

    public void setIdRemetente(Long idRemetente) {
        this.idRemetente = idRemetente;
    }

    public Long getIdDestinatario() {
        return idDestinatario;
    }

    public void setIdDestinatario(Long idDestinatario) {
        this.idDestinatario = idDestinatario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public boolean isLida() {
        return lida;
    }

    public void setLida(boolean lida) {
        this.lida = lida;
    }
    
    public boolean isAprovacao() {
        return aprovacao;
    }

    public void setAprovacao(boolean aprovacao) {
        this.aprovacao = aprovacao;
    }
    
}
