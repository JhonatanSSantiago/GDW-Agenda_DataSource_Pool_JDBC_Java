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

function geraTabela(){
    if (this.readyState == 4 && this.status == 200) {
        console.log(this.responseText);
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