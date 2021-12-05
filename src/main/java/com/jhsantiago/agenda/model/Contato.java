/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhsantiago.agenda.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jhons
 */
public class Contato {
    private int codigo;
    private String nome;
    private List<Telefone> telefones= new ArrayList<>();

    public Contato(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }
    
     public Contato() {
        
    }
   
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.codigo;
        return hash;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }
    
    public void setTelefones(List<Telefone> fones){
        this.telefones= fones;
    }

    public void addTelefone(Telefone fone){
        telefones.add(fone);
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
        final Contato other = (Contato) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
       String texto= "<contato>"+
                        "<codigo>"+codigo+"</codigo>"+
                        "<nome>"+nome+"</nome>"+
                        "<telefones>";
                            for(Telefone t:telefones){
                                texto+=t.toString();
                            }
                        texto+="</telefones>"+
                    "</contato>";
       return texto;
    }
    
    
}
