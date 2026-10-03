package br.edu.cs.poo.ac.seguro.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SeguradoEmpresa extends Segurado {
    private String cnpj;
    private double faturamento;
    private boolean ehLocadoraDeVeiculos;
    public SeguradoEmpresa(String nome, Endereco endereco, LocalDate dataAbertura, BigDecimal bonus,String cnpj,double faturamento, boolean ehLocadoraDeVeiculos){
        super(nome,endereco,dataAbertura,bonus);
        this.cnpj=cnpj;
        this.faturamento=faturamento;
        this.ehLocadoraDeVeiculos=ehLocadoraDeVeiculos;
    }
    public void setCnpj(String cnpj){
        this.cnpj=cnpj;
    }
    public String getCnpj(){
        return this.cnpj;
    }

    public void setFaturamento(double faturamento){
        this.faturamento=faturamento;
    }
    public double getFaturamento(){
        return this.faturamento;
    }
    public void setEhLocadoraDeVeiculos(boolean ehLocadoraDeVeiculos){
        this.ehLocadoraDeVeiculos=ehLocadoraDeVeiculos;
    }
    public boolean getEhLocadoraDeVeiculos(){
        return ehLocadoraDeVeiculos;
    }
    public LocalDate getDataAbertura(){
        return getDataCriacao();
    }
    public void setDataAbertura(LocalDate dataAbertura){
        setDataCriacao(dataAbertura);
    }
}