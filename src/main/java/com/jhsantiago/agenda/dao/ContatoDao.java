/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhsantiago.agenda.dao;

import com.jhsantiago.agenda.model.Contato;
import com.jhsantiago.agenda.model.Telefone;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jhons
 */
public class ContatoDao implements ContatoInterface {

    Connection con;
    public ContatoDao() throws ErroDAOException {
        con = new FabricaConexao().pegaConexao();
    }

    @Override
    public void criar(Contato c) throws ErroDAOException {
        try {
            PreparedStatement ps = con.prepareStatement("insert into contato values(null, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, c.getNome());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                c.setCodigo(rs.getInt(1));
            }
            rs.close();
            ps.close();
            TelefoneDao dao = new TelefoneDao();
            List<Telefone> fones = c.getTelefones();
            for(Telefone t:fones){
                dao.criar(t,c);
                dao.sair();
            }
            
        } catch (SQLException ex) {
            throw new ErroDAOException(ex);
        }
    }

    @Override
    public Contato buscar(int codigo) throws ErroDAOException {
       return null;
    }

    @Override
    public List<Contato> buscarContatos() throws ErroDAOException {
        List<Contato> agenda = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("select codigo, nome from contato");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Contato c = new Contato();
                c.setCodigo(rs.getInt(1));
                c.setNome(rs.getString(2));
                agenda.add(c);
                TelefoneDao dao = new TelefoneDao();
                c.setTelefones(dao.buscarTelefones(c));               
                dao.sair();
            }
            rs.close();
            ps.close();
            return agenda;
        } catch (SQLException ex) {
            throw new ErroDAOException(ex);
        }
    }
    
    @Override
    public void sair() throws ErroDAOException {
        try {
            con.close();
        } catch (SQLException ex) {
           throw  new ErroDAOException(ex);
        }
    }
}
