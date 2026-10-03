package br.edu.cs.poo.ac.seguro.testes;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.edu.cs.poo.ac.seguro.daos.SinistroDAO;
import br.edu.cs.poo.ac.seguro.entidades.Sinistro;
import br.edu.cs.poo.ac.seguro.entidades.TipoSinistro;

public class TesteSinistroDAO extends TesteDAO {

    private SinistroDAO dao = new SinistroDAO();

    @Override
    protected Class getClasse() {
        return Sinistro.class;
    }

    @Test
    public void teste01() {
        String numero = "00000000";

        Sinistro sinistro = new Sinistro(null, LocalDateTime.now(),
                LocalDateTime.now(), "JOAO", BigDecimal.valueOf(1000),
                TipoSinistro.COLISAO);
        sinistro.setNumero(numero);

        cadastro.incluir(sinistro, numero);

        Sinistro sinistroBuscado = dao.buscar(numero);

        Assertions.assertNotNull(sinistroBuscado);
    }

    @Test
    public void teste02() {
        String numero = "10000000";

        Sinistro sinistro = new Sinistro(null, LocalDateTime.now(),
                LocalDateTime.now(), "JOAO", BigDecimal.valueOf(1000),
                TipoSinistro.COLISAO);
        sinistro.setNumero(numero);

        cadastro.incluir(sinistro, numero);

        Sinistro sinistroBuscado = dao.buscar("11000000");

        Assertions.assertNull(sinistroBuscado);
    }

    @Test
    public void teste03() {
        String numero = "20000000";

        Sinistro sinistro = new Sinistro(null, LocalDateTime.now(),
                LocalDateTime.now(), "JOAO", BigDecimal.valueOf(1000),
                TipoSinistro.COLISAO);
        sinistro.setNumero(numero);

        cadastro.incluir(sinistro, numero);

        boolean retorno = dao.excluir(numero);

        Assertions.assertTrue(retorno);
    }

    @Test
    public void teste04() {
        String numero = "30000000";

        Sinistro sinistro = new Sinistro(null, LocalDateTime.now(),
                LocalDateTime.now(), "JOAO", BigDecimal.valueOf(1000),
                TipoSinistro.COLISAO);
        sinistro.setNumero(numero);

        cadastro.incluir(sinistro, numero);

        boolean retorno = dao.excluir("31000000");

        Assertions.assertFalse(retorno);
    }

    @Test
    public void teste05() {
        String numero = "40000000";

        Sinistro sinistro = new Sinistro(null, LocalDateTime.now(),
                LocalDateTime.now(), "JOAO", BigDecimal.valueOf(1000),
                TipoSinistro.COLISAO);
        sinistro.setNumero(numero);

        boolean retorno = dao.incluir(sinistro);

        Assertions.assertTrue(retorno);
        Assertions.assertNotNull(dao.buscar(numero));
    }

    @Test
    public void teste06() {
        String numero = "50000000";

        Sinistro sinistro = new Sinistro(null, LocalDateTime.now(),
                LocalDateTime.now(), "JOAO", BigDecimal.valueOf(1000),
                TipoSinistro.COLISAO);
        sinistro.setNumero(numero);

        cadastro.incluir(sinistro, numero);

        boolean retorno = dao.incluir(sinistro);

        Assertions.assertFalse(retorno);
    }

    @Test
    public void teste07() {
        String numero = "60000000";

        Sinistro sinistro = new Sinistro(null, LocalDateTime.now(),
                LocalDateTime.now(), "JOAO", BigDecimal.valueOf(1000),
                TipoSinistro.COLISAO);
        sinistro.setNumero(numero);

        boolean retorno = dao.alterar(sinistro);

        Assertions.assertFalse(retorno);
        Assertions.assertNull(dao.buscar(numero));
    }

    @Test
    public void teste08() {
        String numero = "70000000";

        Sinistro sinistro = new Sinistro(null, LocalDateTime.now(),
                LocalDateTime.now(), "JOAO", BigDecimal.valueOf(1000),
                TipoSinistro.COLISAO);
        sinistro.setNumero(numero);

        cadastro.incluir(sinistro, numero);

        Sinistro sinistroAlterado = new Sinistro(null, LocalDateTime.now(),
                LocalDateTime.now(), "MARIA", BigDecimal.valueOf(2000),
                TipoSinistro.INCENDIO);
        sinistroAlterado.setNumero(numero);

        boolean retorno = dao.alterar(sinistroAlterado);

        Assertions.assertTrue(retorno);
    }
}