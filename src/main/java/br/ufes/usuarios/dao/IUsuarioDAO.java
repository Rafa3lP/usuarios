/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.ufes.usuarios.dao;

import br.ufes.usuarios.model.Usuario;
import java.util.List;

/**
 *
 * @author Rafael
 */
public interface IUsuarioDAO {
    Long criar(Usuario usuario);
    public void aprovar(Long id);
    Usuario lerPorUsuario(String usuario);
    Usuario lerPorId(Long id);
    void atualizar(Usuario usuario);
    void alterarSenha(Usuario usuario);
    void deletar(Long id);
    List<Usuario> lerTodos();
    List<Usuario> buscaUsuariosPorNome(String filtroNome);
}
