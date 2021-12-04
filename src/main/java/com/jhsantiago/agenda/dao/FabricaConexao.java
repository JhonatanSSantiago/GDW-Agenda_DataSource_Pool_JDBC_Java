/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhsantiago.agenda.dao;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author jhons
 */
public class FabricaConexao {
     public Connection pegaConexao() throws ErroDAOException {
        try {
            Connection con;
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/agenda");
            con = ds.getConnection();
            return con;
        } catch (NamingException |SQLException ex) {
            throw new ErroDAOException(ex);
        }
    }
}
