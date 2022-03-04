/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.usuarios.dao;

import br.ufes.usuarios.model.Notificacao;
import br.ufes.usuarios.model.Usuario;
import java.util.List;

/**
 *
 * @author Rafael
 */
public interface INotificacaoDAO {
    void criar(Notificacao notificacao);
    void marcarComoLida(Notificacao notificacao);
    void deletar(Long idNotificacao);
    List<Notificacao> getNotificacoes(Usuario usuario, boolean somenteLidas);
}
