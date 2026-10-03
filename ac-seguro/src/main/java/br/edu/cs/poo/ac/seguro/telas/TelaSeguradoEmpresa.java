package br.edu.cs.poo.ac.seguro.telas;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

import br.edu.cs.poo.ac.seguro.entidades.SeguradoEmpresa;
import br.edu.cs.poo.ac.seguro.mediators.SeguradoEmpresaMediator;

public class TelaSeguradoEmpresa extends TelaSeguradoBase {
    private final SeguradoEmpresaMediator mediator = SeguradoEmpresaMediator.getInstancia();
    private final JTextField cnpj = new JTextField(16);
    private final JTextField faturamento = new JTextField("0.00", 12);
    private final JCheckBox locadora = new JCheckBox("É locadora de veículos");

    public TelaSeguradoEmpresa() {
        adicionarCampo("CNPJ", cnpj);
        adicionarCampo("Faturamento", faturamento);
        adicionarCampo("Atividade", locadora);
        adicionarBotoes(e -> buscar(), e -> incluir(), e -> alterar(), e -> excluir(), e -> limparCampos());
    }

    private SeguradoEmpresa lerSegurado() {
        return new SeguradoEmpresa(nome.getText(), criarEndereco(), lerData(), lerBonus(),
                cnpj.getText(), Double.parseDouble(faturamento.getText().trim().replace(',', '.')),
                locadora.isSelected());
    }

    private void buscar() {
        SeguradoEmpresa segurado = mediator.buscarSeguradoEmpresa(cnpj.getText());
        if (segurado == null) {
            informarErro("CNPJ não encontrado.");
            return;
        }
        preencherCamposComuns(segurado, segurado.getDataAbertura());
        faturamento.setText(String.valueOf(segurado.getFaturamento()));
        locadora.setSelected(segurado.getEhLocadoraDeVeiculos());
    }

    private void incluir() {
        try {
            String mensagem = mediator.incluirSeguradoEmpresa(lerSegurado());
            if (mensagem == null) {
                informar("Segurado empresa incluído.");
            } else {
                informarErro(mensagem);
            }
        } catch (NumberFormatException e) {
            informarErro("Bônus e faturamento devem ser números válidos.");
        }
    }

    private void alterar() {
        try {
            String mensagem = mediator.alterarSeguradoEmpresa(lerSegurado());
            if (mensagem == null) {
                informar("Segurado empresa alterado.");
            } else {
                informarErro(mensagem);
            }
        } catch (NumberFormatException e) {
            informarErro("Bônus e faturamento devem ser números válidos.");
        }
    }

    private void excluir() {
        String mensagem = mediator.excluirSeguradoEmpresa(cnpj.getText());
        if (mensagem == null) {
            informar("Segurado empresa excluído.");
            limparCampos();
        } else {
            informarErro(mensagem);
        }
    }

    @Override
    protected void limparCampos() {
        limparCamposComuns();
        cnpj.setText("");
        faturamento.setText("0.00");
        locadora.setSelected(false);
    }
}
