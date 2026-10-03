package br.edu.cs.poo.ac.seguro.telas;

import java.time.Year;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import br.edu.cs.poo.ac.seguro.daos.SeguradoEmpresaDAO;
import br.edu.cs.poo.ac.seguro.daos.SeguradoPessoaDAO;
import br.edu.cs.poo.ac.seguro.daos.VeiculoDAO;
import br.edu.cs.poo.ac.seguro.entidades.CategoriaVeiculo;
import br.edu.cs.poo.ac.seguro.entidades.SeguradoEmpresa;
import br.edu.cs.poo.ac.seguro.entidades.SeguradoPessoa;
import br.edu.cs.poo.ac.seguro.entidades.Veiculo;

public class TelaVeiculo extends TelaCrudBase {
    private final VeiculoDAO dao = new VeiculoDAO();
    private final SeguradoPessoaDAO pessoaDAO = new SeguradoPessoaDAO();
    private final SeguradoEmpresaDAO empresaDAO = new SeguradoEmpresaDAO();
    private final JTextField placa = new JTextField(10);
    private final JSpinner ano = new JSpinner(new SpinnerNumberModel(Year.now().getValue(), 1900,
            Year.now().getValue() + 1, 1));
    private final JComboBox<CategoriaVeiculo> categoria = new JComboBox<>(CategoriaVeiculo.values());
    private final JRadioButton proprietarioPessoa = new JRadioButton("Pessoa", true);
    private final JRadioButton proprietarioEmpresa = new JRadioButton("Empresa");
    private final JTextField documentoProprietario = new JTextField(16);

    public TelaVeiculo() {
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(proprietarioPessoa);
        grupo.add(proprietarioEmpresa);
        JPanel tipoProprietario = new JPanel();
        tipoProprietario.add(proprietarioPessoa);
        tipoProprietario.add(proprietarioEmpresa);

        adicionarCampo("Placa", placa);
        adicionarCampo("Ano", ano);
        adicionarCampo("Categoria", categoria);
        adicionarCampo("Tipo de proprietário", tipoProprietario);
        adicionarCampo("CPF ou CNPJ", documentoProprietario);
        adicionarBotoes(e -> buscar(), e -> incluir(), e -> alterar(), e -> excluir(), e -> limparCampos());
    }

    private Veiculo lerVeiculo() {
        SeguradoPessoa pessoa = null;
        SeguradoEmpresa empresa = null;
        if (proprietarioPessoa.isSelected()) {
            pessoa = pessoaDAO.buscar(documentoProprietario.getText());
            if (pessoa == null) {
                throw new IllegalArgumentException("CPF do proprietário não encontrado.");
            }
        } else {
            empresa = empresaDAO.buscar(documentoProprietario.getText());
            if (empresa == null) {
                throw new IllegalArgumentException("CNPJ do proprietário não encontrado.");
            }
        }
        return new Veiculo(placa.getText(), (Integer) ano.getValue(), empresa, pessoa,
                (CategoriaVeiculo) categoria.getSelectedItem());
    }

    private void buscar() {
        Veiculo veiculo = dao.buscar(placa.getText());
        if (veiculo == null) {
            informarErro("Placa não encontrada.");
            return;
        }
        ano.setValue(veiculo.getAno());
        categoria.setSelectedItem(veiculo.getCategoria());
        if (veiculo.getProprietarioPessoa() != null) {
            proprietarioPessoa.setSelected(true);
            documentoProprietario.setText(veiculo.getProprietarioPessoa().getCpf());
        } else if (veiculo.getProprietarioEmpresa() != null) {
            proprietarioEmpresa.setSelected(true);
            documentoProprietario.setText(veiculo.getProprietarioEmpresa().getCnpj());
        }
    }

    private void incluir() {
        executarOperacao(true);
    }

    private void alterar() {
        executarOperacao(false);
    }

    private void executarOperacao(boolean incluir) {
        try {
            boolean sucesso = incluir ? dao.incluir(lerVeiculo()) : dao.alterar(lerVeiculo());
            if (sucesso) {
                informar(incluir ? "Veículo incluído." : "Veículo alterado.");
            } else {
                informarErro(incluir ? "Já existe veículo com esta placa." : "Placa não encontrada.");
            }
        } catch (IllegalArgumentException e) {
            informarErro(e.getMessage());
        }
    }

    private void excluir() {
        if (dao.excluir(placa.getText())) {
            informar("Veículo excluído.");
            limparCampos();
        } else {
            informarErro("Placa não encontrada.");
        }
    }

    @Override
    protected void limparCampos() {
        placa.setText("");
        ano.setValue(Year.now().getValue());
        categoria.setSelectedIndex(0);
        proprietarioPessoa.setSelected(true);
        documentoProprietario.setText("");
    }
}
