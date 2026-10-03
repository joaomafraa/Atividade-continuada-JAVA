package br.edu.cs.poo.ac.seguro.entidades;
import java.time.LocalDate;
import java.time.Period;
import java.math.BigDecimal;

public class Segurado {
    private String nome;
    private Endereco endereco;
    private LocalDate dataCriacao;
    private BigDecimal bonus;

    public Segurado(String nome, Endereco endereco,LocalDate dataCriacao, BigDecimal bonus){
        this.nome=nome;
        this.endereco=endereco;
        this.dataCriacao=dataCriacao;
        this.bonus=bonus;
    }
    public void setNome(String nome){
        this.nome=nome;
    }
    public String getNome(){
        return this.nome;
    }
    public void setEndereco(Endereco endereco){
        this.endereco = endereco;
    }
    public Endereco getEndereco(){
        return this.endereco;
    }
    protected void setDataCriacao(LocalDate datacriacao){
        this.dataCriacao=datacriacao;
    }
    protected LocalDate getDataCriacao(){
        return this.dataCriacao;
    }
    public BigDecimal getBonus(){
        return this.bonus;
    }

    public int getIdade(){
        return Period.between(dataCriacao, LocalDate.now()).getYears();
    }
    public void creditarBonus(BigDecimal valor){
        this.bonus = this.bonus.add(valor);
    }
    public void debitarBonus(BigDecimal valor){
        this.bonus=this.bonus.subtract(valor);
    }
}
