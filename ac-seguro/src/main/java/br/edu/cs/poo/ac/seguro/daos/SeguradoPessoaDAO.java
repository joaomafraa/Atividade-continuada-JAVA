package br.edu.cs.poo.ac.seguro.daos;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.edu.cs.poo.ac.seguro.entidades.SeguradoPessoa;

public class SeguradoPessoaDAO extends DAOGenerico {

    public SeguradoPessoaDAO() {
        cadastro = new CadastroObjetos(SeguradoPessoa.class);
    }

    public SeguradoPessoa buscar(String cpf) {
        return (SeguradoPessoa) cadastro.buscar(cpf);
    }

    public boolean incluir(SeguradoPessoa segurado) {
        SeguradoPessoa seguradoBuscado = buscar(segurado.getCpf());

        if (seguradoBuscado != null) {
            return false;
        }

        cadastro.incluir(segurado, segurado.getCpf());
        return true;
    }

    public boolean alterar(SeguradoPessoa segurado) {
        SeguradoPessoa seguradoBuscado = buscar(segurado.getCpf());

        if (seguradoBuscado == null) {
            return false;
        }

        cadastro.alterar(segurado, segurado.getCpf());
        return true;
    }

    public boolean excluir(String cpf) {
        SeguradoPessoa seguradoBuscado = buscar(cpf);

        if (seguradoBuscado == null) {
            return false;
        }

        cadastro.excluir(cpf);
        return true;
    }
}