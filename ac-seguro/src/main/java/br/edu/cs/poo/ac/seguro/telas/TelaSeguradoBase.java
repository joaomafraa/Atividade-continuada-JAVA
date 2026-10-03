package br.edu.cs.poo.ac.seguro.telas;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;

import br.edu.cs.poo.ac.seguro.entidades.Endereco;
import br.edu.cs.poo.ac.seguro.entidades.Segurado;

abstract class TelaSeguradoBase extends TelaCrudBase {
    protected final JTextField nome = new JTextField(25);
    protected final JSpinner data = criarCampoData();
    protected final JFormattedTextField bonus = new JFormattedTextField();
    protected final JTextField logradouro = new JTextField(25);
    protected final JTextField cep = new JTextField(10);
    protected final JTextField numeroEndereco = new JTextField(10);
    protected final JTextField complemento = new JTextField(15);
    protected final JTextField pais = new JTextField("Brasil", 15);
    protected final JComboBox<String> estado = new JComboBox<>(new String[] {
            "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS",
            "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC",
            "SP", "SE", "TO" });
    protected final JTextField cidade = new JTextField(20);

    protected TelaSeguradoBase() {
        bonus.setText("0.00");
        adicionarCampo("Nome", nome);
        adicionarCampo("Data", data);
        adicionarCampo("Bônus", bonus);
        adicionarCampo("Logradouro", logradouro);
        adicionarCampo("CEP", cep);
        adicionarCampo("Número", numeroEndereco);
        adicionarCampo("Complemento", complemento);
        adicionarCampo("País", pais);
        adicionarCampo("Estado", estado);
        adicionarCampo("Cidade", cidade);
    }

    private JSpinner criarCampoData() {
        JSpinner spinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "yyyy-MM-dd");
        spinner.setEditor(editor);
        return spinner;
    }

    protected Endereco criarEndereco() {
        return new Endereco(logradouro.getText(), cep.getText(), numeroEndereco.getText(),
                complemento.getText(), pais.getText(), (String) estado.getSelectedItem(), cidade.getText());
    }

    protected LocalDate lerData() {
        Date valor = (Date) data.getValue();
        return valor.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    protected BigDecimal lerBonus() {
        return new BigDecimal(bonus.getText().trim().replace(',', '.'));
    }

    protected void preencherCamposComuns(Segurado segurado, LocalDate dataSegurado) {
        nome.setText(segurado.getNome());
        bonus.setText(segurado.getBonus().toPlainString());
        data.setValue(Date.from(dataSegurado.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        Endereco endereco = segurado.getEndereco();
        logradouro.setText(endereco.getLogradouro());
        cep.setText(endereco.getCep());
        numeroEndereco.setText(endereco.getNumero());
        complemento.setText(endereco.getComplemento());
        pais.setText(endereco.getPais());
        estado.setSelectedItem(endereco.getEstado());
        cidade.setText(endereco.getCidade());
    }

    protected void limparCamposComuns() {
        nome.setText("");
        data.setValue(new Date());
        bonus.setText("0.00");
        logradouro.setText("");
        cep.setText("");
        numeroEndereco.setText("");
        complemento.setText("");
        pais.setText("Brasil");
        estado.setSelectedIndex(0);
        cidade.setText("");
    }
}
