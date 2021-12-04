/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhsantiago.agenda.model;

/**
 *
 * @author jhons
 */
public class Telefone {
    int codigo;
    String numero;
    String ddd;
    String tipo;

    public Telefone(int codigo, String numero, String ddd, String tipo) {
        this.codigo = codigo;
        this.numero = numero;
        this.ddd = ddd;
        this.tipo = tipo;
    }

    public Telefone() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.codigo;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Telefone other = (Telefone) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Telefone{" + "codigo=" + codigo + ", numero=" + numero + ", ddd=" + ddd + ", tipo=" + tipo + '}';
    }
    
    
}
