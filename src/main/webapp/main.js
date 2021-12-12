/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
document.getElementById("botao").onclick=()=>{
    criaAjax("mostrar","",geraTabela);
    
}
function criaAjax(url, dados, funcao) {
    let ajax = new XMLHttpRequest();
    ajax.onreadystatechange = funcao;
    ajax.open("GET", url +"?"+ dados, true);  
    ajax.send();
}
function ajaxPost(url, dados, funcao) {
    let ajax = new XMLHttpRequest();
    ajax.onreadystatechange = funcao;
    ajax.open("POST", url, true);
    ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    ajax.send(dados);
}
function geraTabela(){
    if (this.readyState == 4 && this.status == 200) {
       // console.log(this.responseText);
        let text="";
        let contatos=this.responseXML.documentElement.getElementsByTagName("contato");
        let tabela = document.getElementById("resultado");
        for(let contato of contatos){
            text+="<tr>";
                text+="<td>"+contato.getElementsByTagName("codigo")[0].firstChild.nodeValue+"</td>";
                text+="<td>"+contato.getElementsByTagName("nome")[0].firstChild.nodeValue+"</td>";
                text+="<td>"+listaDeTelefones(contato.getElementsByTagName("telefone"))+"</td>";               
            text+="</tr>";
        }
        tabela.innerHTML=text;
    }
}

function listaDeTelefones(telefones){
    let texto="<ul>";
    for(let fone of telefones){
        texto+="<li>("+fone.getElementsByTagName("ddd")[0].firstChild.nodeValue+") ";
        texto+=fone.getElementsByTagName("numero")[0].firstChild.nodeValue+"</li>";
    }
    return texto+"</ul>";
}

document.getElementById("btAdd").onclick=()=>{
    let texto='<div>\
                    DDD:<input type="text" class="ddd" placeholder="00">\
                    NUMERO:<input type="text" class="numero" placeholder="00000-0000">\
                    TIPO:<input type="text" class="tipo" placeholder="xxxxx">\
                    <button type="button" class="btRem" onclick=remover(this)>X</button>\
               </div>';
    document.getElementById("telefones").innerHTML+=texto;
}

function remover(botao){
    let pai = botao.parentNode;
    pai.parentNode.removeChild(pai);
}

document.getElementById("btSave").onclick=()=>{
    let texto="<contato><nome>"+document.getElementById("nome").value+"</nome>"; 
    texto+=pegaTelefones();
    texto+="</contato>";
    console.log(texto);
    ajaxPost("inserir","xml="+texto,function(){
        if (this.readyState == 4 && this.status == 200){
            alert(this.responseText);
        }
    });
}

function pegaTelefones()
{
    
    let ddds=document.querySelectorAll(".ddd");
    let numeros=document.querySelectorAll(".numero");
    let tipos=document.querySelectorAll(".tipo");
    let tam=ddds.length;
    let txt="<telefones>";
    for(let i= 0;i< tam ; i++) {
        txt+= montaTelefone(ddds[i].value, numeros[i].value, tipos[i].value);
    }
    txt+="</telefones>";
    return txt;
}
function montaTelefone(ddd,numero,tipo)
{
    return "<telefone><numero>"+numero+"</numero><ddd>"+ddd+"</ddd><tipo>"+tipo+"</tipo></telefone>";
}

const removerContato = (cod, nome)=> {
    if(confirm("Deseja realmente excluir "+nome+"?")) {
        criaAjax("excluir",`codigo=${cod}`, function() {
            this.onload = function() {
                alert(this.responseText);
                mostrar();
            }
        })
    }
}