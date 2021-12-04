/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhsantiago.agenda.dao;

/**
 *
 * @author jhons
 */
public class ErroDAOException extends Exception {
    public ErroDAOException() {
        super("Erro na base de dados");
    }

    public ErroDAOException(String message) {
        super(message);
    }

    public ErroDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public ErroDAOException(Throwable cause) {
        super(cause);
    }

    public ErroDAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
