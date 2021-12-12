/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhsantiago.agenda.xml;

import com.jhsantiago.agenda.model.Contato;
import com.jhsantiago.agenda.model.Telefone;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author jhons
 */
public class ManipulaXml {
    
    public Contato TrasnformaContato(String xml){
        
        try{
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            DocumentBuilder construtor = fabrica.newDocumentBuilder();       
            InputStream fluxo = new ByteArrayInputStream(xml.getBytes());
            Document doc = construtor.parse(fluxo);
            return criaContato(doc.getDocumentElement());
        } catch (SAXException | IOException |ParserConfigurationException ex){
            return null;
        } 
    }
    
    private Contato criaContato (Element raiz){
        Contato c= new Contato();
        c.setNome(raiz.getElementsByTagName("nome").item(0).getFirstChild().getNodeValue());
        c.setTelefones(pegaTelefones(raiz.getElementsByTagName("telefone")));
        return c;
    }
    
    private List<Telefone> pegaTelefones(NodeList nodeFones){
        List<Telefone> telefones = new ArrayList<>();       
        int tam = nodeFones.getLength();
        for (int i=0; i<tam; i++){
            Telefone t= new Telefone();
            Element noFone = (Element)nodeFones.item(i);
            t.setDdd(noFone.getElementsByTagName("ddd").item(0).getFirstChild().getNodeValue());
            t.setNumero(noFone.getElementsByTagName("numero").item(0).getFirstChild().getNodeValue());
            t.setTipo(noFone.getElementsByTagName("tipo").item(0).getFirstChild().getNodeValue());
            telefones.add(t);
        }
        return telefones;
    }
}
