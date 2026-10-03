package br.edu.cs.poo.ac.seguro.telas;

import java.math.BigDecimal;

import javax.swing.JTextField;

import br.edu.cs.poo.ac.seguro.daos.ApoliceDAO;
import br.edu.cs.poo.ac.seguro.daos.VeiculoDAO;
import br.edu.cs.poo.ac.seguro.entidades.Apolice;
import br.edu.cs.poo.ac.seguro.entidades.Veiculo;

public class TelaApolice extends TelaCrudBase {
    private final ApoliceDAO dao = new ApoliceDAO();
    private final VeiculoDAO veiculoDAO = new VeiculoDAO();
    private final JTextField numero = new JTextField(12);
    private final JTextField placaVeiculo = new JTextField(10);
    private final JTextField franquia = new JTextField("0.00", 12);
    private final JTextField premio = new JTextField("0.00", 12);
    private final JTextField valorMaximo = new JTextField("0.00", 12);

    public TelaApolice() {
        adicionarCampo("Número", numero);
        adicionarCampo("Placa do veículo", placaVeiculo);
        adicionarCampo("Franquia", franquia);
        adicionarCampo("Prêmio", premio);
        adicionarCampo("Valor máximo segurado", valorMaximo);
        adicionarBotoes(e -> buscar(), e -> incluir(), e -> alterar(), e -> excluir(), e -> limparCampos());
    }

    private Apolice lerApolice() {
        Veiculo veiculo = veiculoDAO.buscar(placaVeiculo.getText());
        if (veiculo == null) {
            throw new IllegalArgumentException("Veículo não encontrado.");
        }
        Apolice apolice = new Apolice(veiculo, lerDecimal(franquia), lerDecimal(premio), lerDecimal(valorMaximo));
        apolice.setNumero(numero.getText());
        return apolice;
    }

    private BigDecimal lerDecimal(JTextField campo) {
        return new BigDecimal(campo.getText().trim().replace(',', '.'));
    }

    private void buscar() {
        Apolice apolice = dao.buscar(numero.getText());
        if (apolice == null) {
            informarErro("Apólice não encontrada.");
            return;
        }
        placaVeiculo.setText(apolice.getVeiculo().getPlaca());
        franquia.setText(apolice.getValorFranquia().toPlainString());
        premio.setText(apolice.getValorPremio().toPlainString());
        valorMaximo.setText(apolice.getValorMaximoSegurado().toPlainString());
    }

    private void incluir() {
        executarOperacao(true);
    }

    private void alterar() {
        executarOperacao(false);
    }

    private void executarOperacao(boolean incluir) {
        try {
            boolean sucesso = incluir ? dao.incluir(lerApolice()) : dao.alterar(lerApolice());
            if (sucesso) {
                informar(incluir ? "Apólice incluída." : "Apólice alterada.");
            } else {
                informarErro(incluir ? "Já existe apólice com este número." : "Apólice não encontrada.");
            }
        } catch (IllegalArgumentException e) {
            informarErro(e.getMessage());
        }
    }

    private void excluir() {
        if (dao.excluir(numero.getText())) {
            informar("Apólice excluída.");
            limparCampos();
        } else {
            informarErro("Apólice não encontrada.");
        }
    }

    @Override
    protected void limparCampos() {
        numero.setText("");
        placaVeiculo.setText("");
        franquia.setText("0.00");
        premio.setText("0.00");
        valorMaximo.setText("0.00");
    }
}
