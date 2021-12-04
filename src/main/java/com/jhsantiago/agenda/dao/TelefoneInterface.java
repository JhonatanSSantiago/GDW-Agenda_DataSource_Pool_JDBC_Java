/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhsantiago.agenda.dao;

import com.jhsantiago.agenda.model.Contato;
import com.jhsantiago.agenda.model.Telefone;
import java.util.List;

/**
 *
 * @author jhons
 */
public interface TelefoneInterface {
    public void criar(Telefone t, Contato c) throws ErroDAOException;
    public Telefone buscar(int codigo) throws ErroDAOException;
    public List<Telefone> buscarTelefones() throws ErroDAOException;
    public List<Telefone> buscarTelefones(Contato c) throws ErroDAOException;
    public void sair() throws ErroDAOException;
}
