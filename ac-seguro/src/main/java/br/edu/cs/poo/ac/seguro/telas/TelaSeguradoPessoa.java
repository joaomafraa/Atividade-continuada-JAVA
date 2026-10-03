package br.edu.cs.poo.ac.seguro.telas;

import javax.swing.JTextField;

import br.edu.cs.poo.ac.seguro.entidades.SeguradoPessoa;
import br.edu.cs.poo.ac.seguro.mediators.SeguradoPessoaMediator;

public class TelaSeguradoPessoa extends TelaSeguradoBase {
    private final SeguradoPessoaMediator mediator = SeguradoPessoaMediator.getInstancia();
    private final JTextField cpf = new JTextField(14);
    private final JTextField renda = new JTextField("0.00", 12);

    public TelaSeguradoPessoa() {
        adicionarCampo("CPF", cpf);
        adicionarCampo("Renda", renda);
        adicionarBotoes(e -> buscar(), e -> incluir(), e -> alterar(), e -> excluir(), e -> limparCampos());
    }

    private SeguradoPessoa lerSegurado() {
        return new SeguradoPessoa(nome.getText(), criarEndereco(), lerData(), lerBonus(),
                cpf.getText(), Double.parseDouble(renda.getText().trim().replace(',', '.')));
    }

    private void buscar() {
        SeguradoPessoa segurado = mediator.buscarSeguradoPessoa(cpf.getText());
        if (segurado == null) {
            informarErro("CPF não encontrado.");
            return;
        }
        preencherCamposComuns(segurado, segurado.getDataNascimento());
        renda.setText(String.valueOf(segurado.getRenda()));
    }

    private void incluir() {
        try {
            String mensagem = mediator.incluirSeguradoPessoa(lerSegurado());
            if (mensagem == null) {
                informar("Segurado pessoa incluído.");
            } else {
                informarErro(mensagem);
            }
        } catch (NumberFormatException e) {
            informarErro("Bônus e renda devem ser números válidos.");
        }
    }

    private void alterar() {
        try {
            String mensagem = mediator.alterarSeguradoPessoa(lerSegurado());
            if (mensagem == null) {
                informar("Segurado pessoa alterado.");
            } else {
                informarErro(mensagem);
            }
        } catch (NumberFormatException e) {
            informarErro("Bônus e renda devem ser números válidos.");
        }
    }

    private void excluir() {
        String mensagem = mediator.excluirSeguradoPessoa(cpf.getText());
        if (mensagem == null) {
            informar("Segurado pessoa excluído.");
            limparCampos();
        } else {
            informarErro(mensagem);
        }
    }

    @Override
    protected void limparCampos() {
        limparCamposComuns();
        cpf.setText("");
        renda.setText("0.00");
    }
}
