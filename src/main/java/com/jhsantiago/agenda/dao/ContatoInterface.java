/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhsantiago.agenda.dao;

import com.jhsantiago.agenda.model.Contato;
import java.util.List;

/**
 *
 * @author jhons
 */
public interface ContatoInterface {
    public void criar(Contato c) throws ErroDAOException;
    public Contato buscar(int codigo) throws ErroDAOException;
    public List<Contato> buscarContatos() throws ErroDAOException;
    public void sair() throws ErroDAOException;
}
