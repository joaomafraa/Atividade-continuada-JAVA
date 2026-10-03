package br.edu.cs.poo.ac.seguro.entidades;

public class Endereco {
    private String logradouro;
    private String cep;
    private String numero;
    private String complemento;
    private String pais;
    private String estado;
    private String cidade;

    public Endereco(String logradouro, String cep, String numero, String complemento, String pais,String estado, String cidade){
        this.logradouro=logradouro;
        this.cep = cep;
        this.numero=numero;
        this.complemento=complemento;
        this.pais=pais;
        this.estado=estado;
        this.cidade=cidade;
    }
    //metodos set e get
    public void setLogradouro(String logradouro){
        this.logradouro=logradouro;
    }
    public String getLogradouro(){
        return this.logradouro;
    }


    public void setCep(String cep){
        this.cep=cep;
    }
    public String getCep(){
        return this.cep;
    }

    public void setNumero(String numero){
        this.numero=numero;
    }
    public String getNumero(){
        return this.numero;
    }

    public void setComplemento(String complemento){
        this.complemento=complemento;
    }
    public String getComplemento(){
        return this.complemento;
    }

    public void setPais(String pais){
        this.pais = pais;
    }
    public String getPais(){
        return this.pais;
    }

    public void setEstado(String estado){
        this.estado=estado;
    }

    public String getEstado(){
        return this.estado;
    }

    public void setCidade(String cidade){
        this.cidade=cidade;
    }
    public String getCidade(){
        return this.cidade;
    }
}
