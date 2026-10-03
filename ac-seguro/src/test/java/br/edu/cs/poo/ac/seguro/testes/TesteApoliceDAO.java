package br.edu.cs.poo.ac.seguro.testes;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.edu.cs.poo.ac.seguro.daos.ApoliceDAO;
import br.edu.cs.poo.ac.seguro.entidades.Apolice;

public class TesteApoliceDAO extends TesteDAO {

    private ApoliceDAO dao = new ApoliceDAO();

    @Override
    protected Class getClasse() {
        return Apolice.class;
    }

    @Test
    public void teste01() {
        String numero = "00000000";

        Apolice apolice = new Apolice(null, BigDecimal.valueOf(100),
                BigDecimal.valueOf(200), BigDecimal.valueOf(1000));
        apolice.setNumero(numero);

        cadastro.incluir(apolice, numero);

        Apolice apoliceBuscada = dao.buscar(numero);

        Assertions.assertNotNull(apoliceBuscada);
    }

    @Test
    public void teste02() {
        String numero = "10000000";

        Apolice apolice = new Apolice(null, BigDecimal.valueOf(100),
                BigDecimal.valueOf(200), BigDecimal.valueOf(1000));
        apolice.setNumero(numero);

        cadastro.incluir(apolice, numero);

        Apolice apoliceBuscada = dao.buscar("11000000");

        Assertions.assertNull(apoliceBuscada);
    }

    @Test
    public void teste03() {
        String numero = "20000000";

        Apolice apolice = new Apolice(null, BigDecimal.valueOf(100),
                BigDecimal.valueOf(200), BigDecimal.valueOf(1000));
        apolice.setNumero(numero);

        cadastro.incluir(apolice, numero);

        boolean retorno = dao.excluir(numero);

        Assertions.assertTrue(retorno);
    }

    @Test
    public void teste04() {
        String numero = "30000000";

        Apolice apolice = new Apolice(null, BigDecimal.valueOf(100),
                BigDecimal.valueOf(200), BigDecimal.valueOf(1000));
        apolice.setNumero(numero);

        cadastro.incluir(apolice, numero);

        boolean retorno = dao.excluir("31000000");

        Assertions.assertFalse(retorno);
    }

    @Test
    public void teste05() {
        String numero = "40000000";

        Apolice apolice = new Apolice(null, BigDecimal.valueOf(100),
                BigDecimal.valueOf(200), BigDecimal.valueOf(1000));
        apolice.setNumero(numero);

        boolean retorno = dao.incluir(apolice);

        Assertions.assertTrue(retorno);
        Assertions.assertNotNull(dao.buscar(numero));
    }

    @Test
    public void teste06() {
        String numero = "50000000";

        Apolice apolice = new Apolice(null, BigDecimal.valueOf(100),
                BigDecimal.valueOf(200), BigDecimal.valueOf(1000));
        apolice.setNumero(numero);

        cadastro.incluir(apolice, numero);

        boolean retorno = dao.incluir(apolice);

        Assertions.assertFalse(retorno);
    }

    @Test
    public void teste07() {
        String numero = "60000000";

        Apolice apolice = new Apolice(null, BigDecimal.valueOf(100),
                BigDecimal.valueOf(200), BigDecimal.valueOf(1000));
        apolice.setNumero(numero);

        boolean retorno = dao.alterar(apolice);

        Assertions.assertFalse(retorno);
        Assertions.assertNull(dao.buscar(numero));
    }

    @Test
    public void teste08() {
        String numero = "70000000";

        Apolice apolice = new Apolice(null, BigDecimal.valueOf(100),
                BigDecimal.valueOf(200), BigDecimal.valueOf(1000));
        apolice.setNumero(numero);

        cadastro.incluir(apolice, numero);

        Apolice apoliceAlterada = new Apolice(null, BigDecimal.valueOf(150),
                BigDecimal.valueOf(250), BigDecimal.valueOf(1500));
        apoliceAlterada.setNumero(numero);

        boolean retorno = dao.alterar(apoliceAlterada);

        Assertions.assertTrue(retorno);
    }
}