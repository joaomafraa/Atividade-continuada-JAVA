package br.edu.cs.poo.ac.seguro.daos;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.edu.cs.poo.ac.seguro.entidades.Apolice;

public class ApoliceDAO extends DAOGenerico {

    public ApoliceDAO() {
        cadastro = new CadastroObjetos(Apolice.class);
    }

    public Apolice buscar(String numero) {
        return (Apolice) cadastro.buscar(numero);
    }

    public boolean incluir(Apolice apolice) {
        Apolice apoliceBuscada = buscar(apolice.getNumero());

        if (apoliceBuscada != null) {
            return false;
        }

        cadastro.incluir(apolice, apolice.getNumero());
        return true;
    }

    public boolean alterar(Apolice apolice) {
        Apolice apoliceBuscada = buscar(apolice.getNumero());

        if (apoliceBuscada == null) {
            return false;
        }

        cadastro.alterar(apolice, apolice.getNumero());
        return true;
    }

    public boolean excluir(String numero) {
        Apolice apoliceBuscada = buscar(numero);

        if (apoliceBuscada == null) {
            return false;
        }

        cadastro.excluir(numero);
        return true;
    }
}