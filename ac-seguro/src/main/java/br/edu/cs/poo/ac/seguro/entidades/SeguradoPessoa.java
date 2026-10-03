package br.edu.cs.poo.ac.seguro.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SeguradoPessoa extends Segurado{
    private String cpf;
    private double renda;
    
    public SeguradoPessoa(String nome, Endereco endereco, LocalDate dataNascimento, BigDecimal bonus,String cpf,double renda){
        super(nome,endereco,dataNascimento,bonus);
        this.cpf = cpf;
        this.renda = renda;
    }
    public void setCpf(String cpf){
        this.cpf = cpf;
    }
    public String getCpf(){
        return this.cpf;
    }

    public void setRenda(double renda){
        this.renda =renda;
    }
    public double getRenda(){
        return this.renda;
    }
    public LocalDate getDataNascimento(){
        return getDataCriacao();
    }
    public void setDataNascimento(LocalDate dataNascimento){
        setDataCriacao(dataNascimento);
    }
}
