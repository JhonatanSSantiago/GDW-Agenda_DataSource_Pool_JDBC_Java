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
public class TelefoneDao implements TelefoneInterface {

    Connection con;
    public TelefoneDao() throws ErroDAOException {
        con = new FabricaConexao().pegaConexao();
    }

    @Override
    public void criar(Telefone t, Contato c) throws ErroDAOException {
        try {
            PreparedStatement ps = con.prepareStatement("insert into telefone values(null, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, t.getNumero());
            ps.setString(2, t.getDdd());
            ps.setString(3, t.getTipo());
            ps.setInt(4, c.getCodigo());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                t.setCodigo(rs.getInt(1));
            }
            rs.close();
            ps.close();
            
        } catch (SQLException ex) {
            throw new ErroDAOException(ex);
        }
    }

    @Override
    public Telefone buscar(int arg0) throws ErroDAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Telefone> buscarTelefones() throws ErroDAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Telefone> buscarTelefones(Contato c) throws ErroDAOException {
        List<Telefone> fones = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("select codigo,numero,ddd,tipo,cod_contato from telefone where cod_contato=?");
            ps.setInt(1, c.getCodigo());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Telefone t = new Telefone();
                t.setCodigo(rs.getInt(1));
                t.setNumero(rs.getString(2));
                t.setDdd(rs.getString(3));
                t.setTipo(rs.getString(4));
                fones.add(t);
            }
            rs.close();
            ps.close();
            return fones;
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
