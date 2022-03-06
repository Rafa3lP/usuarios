/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.logger;

import br.ufes.usuarios.model.Usuario;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Rafael
 */
public abstract class Log {
    public static final int OPERACAO_INCLUSAO = 1;
    public static final int OPERACAO_ALTERACAO = 2;
    public static final int OPERACAO_EXCLUSAO = 3;
    public static final int OPERACAO_ENVIO_NOTIFICACAO = 4;
    public static final int OPERACAO_LEITURA_NOTIFICACAO = 5;
    public static final int OPERACAO_ALTERACAO_SENHA = 6;
    public static final int OPERACAO_AUTORIZACAO = 7;
    
    
    private final Map<Integer, String> operacaoTxt = Stream.of(new Object[][] { 
        { OPERACAO_INCLUSAO, "INCLUSAO" }, 
        { OPERACAO_ALTERACAO, "ALTERACAO" }, 
        { OPERACAO_EXCLUSAO, "EXCLUSAO" },
        { OPERACAO_ENVIO_NOTIFICACAO, "ENVIO DE NOTIFICACAO" },
        { OPERACAO_LEITURA_NOTIFICACAO, "LEITURA DE NOTIFICACAO" },
        { OPERACAO_ALTERACAO_SENHA, "ALTERACAO DA SENHA" },
        { OPERACAO_AUTORIZACAO, "AUTORIZACAO DE USUARIO" },
    }).collect(Collectors.collectingAndThen(
        Collectors.toMap(data -> (Integer) data[0], data -> (String) data[1]), 
        Collections::<Integer, String> unmodifiableMap)
    );
    
    private String mensagem;
    private Usuario usuarioAlvo;
    private Usuario usuarioAutenticado;
    private String operacao;

    public Log(Usuario usuarioAlvo, Usuario usuarioAutenticado, int operacao) {
        this.usuarioAlvo = usuarioAlvo;
        this.usuarioAutenticado = usuarioAutenticado;
        this.operacao = operacaoTxt.get(operacao);
        if(this.operacao == null) throw new RuntimeException("Operacao não suportada");
        createMensagem();
    }
    
    protected abstract void createMensagem();
    
    public final String getMensagem() {
        return this.mensagem;
    }
    
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Usuario getUsuarioAlvo() {
        return usuarioAlvo;
    }

    public void setUsuarioAlvo(Usuario usuarioAlvo) {
        this.usuarioAlvo = usuarioAlvo;
    }

    public Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    public void setUsuarioAutenticado(Usuario usuarioAutenticado) {
        this.usuarioAutenticado = usuarioAutenticado;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }
    
}
