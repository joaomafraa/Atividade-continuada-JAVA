package br.edu.cs.poo.ac.seguro.daos;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.edu.cs.poo.ac.seguro.entidades.Sinistro;

public class SinistroDAO extends DAOGenerico {

    public SinistroDAO() {
        cadastro = new CadastroObjetos(Sinistro.class);
    }

    public Sinistro buscar(String numero) {
        return (Sinistro) cadastro.buscar(numero);
    }

    public boolean incluir(Sinistro sinistro) {
        Sinistro sinistroBuscado = buscar(sinistro.getNumero());

        if (sinistroBuscado != null) {
            return false;
        }

        cadastro.incluir(sinistro, sinistro.getNumero());
        return true;
    }

    public boolean alterar(Sinistro sinistro) {
        Sinistro sinistroBuscado = buscar(sinistro.getNumero());

        if (sinistroBuscado == null) {
            return false;
        }

        cadastro.alterar(sinistro, sinistro.getNumero());
        return true;
    }

    public boolean excluir(String numero) {
        Sinistro sinistroBuscado = buscar(numero);

        if (sinistroBuscado == null) {
            return false;
        }

        cadastro.excluir(numero);
        return true;
    }
}