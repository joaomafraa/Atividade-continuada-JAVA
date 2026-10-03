package br.edu.cs.poo.ac.seguro.telas;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import br.edu.cs.poo.ac.seguro.daos.SinistroDAO;
import br.edu.cs.poo.ac.seguro.daos.VeiculoDAO;
import br.edu.cs.poo.ac.seguro.entidades.Sinistro;
import br.edu.cs.poo.ac.seguro.entidades.TipoSinistro;
import br.edu.cs.poo.ac.seguro.entidades.Veiculo;

public class TelaSinistro extends TelaCrudBase {
    private final SinistroDAO dao = new SinistroDAO();
    private final VeiculoDAO veiculoDAO = new VeiculoDAO();
    private final JTextField numero = new JTextField(12);
    private final JTextField placaVeiculo = new JTextField(10);
    private final JSpinner dataHoraSinistro = criarCampoDataHora();
    private final JSpinner dataHoraRegistro = criarCampoDataHora();
    private final JTextField usuarioRegistro = new JTextField(20);
    private final JTextField valorSinistro = new JTextField("0.00", 12);
    private final JComboBox<TipoSinistro> tipo = new JComboBox<>(TipoSinistro.values());

    public TelaSinistro() {
        adicionarCampo("Número", numero);
        adicionarCampo("Placa do veículo", placaVeiculo);
        adicionarCampo("Data e hora do sinistro", dataHoraSinistro);
        adicionarCampo("Data e hora do registro", dataHoraRegistro);
        adicionarCampo("Usuário do registro", usuarioRegistro);
        adicionarCampo("Valor do sinistro", valorSinistro);
        adicionarCampo("Tipo", tipo);
        adicionarBotoes(e -> buscar(), e -> incluir(), e -> alterar(), e -> excluir(), e -> limparCampos());
    }

    private JSpinner criarCampoDataHora() {
        JSpinner spinner = new JSpinner(new SpinnerDateModel());
        spinner.setEditor(new JSpinner.DateEditor(spinner, "yyyy-MM-dd HH:mm"));
        return spinner;
    }

    private LocalDateTime lerDataHora(JSpinner campo) {
        Date valor = (Date) campo.getValue();
        return valor.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    private Sinistro lerSinistro() {
        Veiculo veiculo = veiculoDAO.buscar(placaVeiculo.getText());
        if (veiculo == null) {
            throw new IllegalArgumentException("Veículo não encontrado.");
        }
        Sinistro sinistro = new Sinistro(veiculo, lerDataHora(dataHoraSinistro),
                lerDataHora(dataHoraRegistro), usuarioRegistro.getText(),
                new BigDecimal(valorSinistro.getText().trim().replace(',', '.')),
                (TipoSinistro) tipo.getSelectedItem());
        sinistro.setNumero(numero.getText());
        return sinistro;
    }

    private void buscar() {
        Sinistro sinistro = dao.buscar(numero.getText());
        if (sinistro == null) {
            informarErro("Sinistro não encontrado.");
            return;
        }
        placaVeiculo.setText(sinistro.getVeiculo().getPlaca());
        dataHoraSinistro.setValue(Date.from(sinistro.getDataHoraSinistro()
                .atZone(ZoneId.systemDefault()).toInstant()));
        dataHoraRegistro.setValue(Date.from(sinistro.getDataHoraRegistro()
                .atZone(ZoneId.systemDefault()).toInstant()));
        usuarioRegistro.setText(sinistro.getUsuarioRegistro());
        valorSinistro.setText(sinistro.getValorSinistro().toPlainString());
        tipo.setSelectedItem(sinistro.getTipo());
    }

    private void incluir() {
        executarOperacao(true);
    }

    private void alterar() {
        executarOperacao(false);
    }

    private void executarOperacao(boolean incluir) {
        try {
            boolean sucesso = incluir ? dao.incluir(lerSinistro()) : dao.alterar(lerSinistro());
            if (sucesso) {
                informar(incluir ? "Sinistro incluído." : "Sinistro alterado.");
            } else {
                informarErro(incluir ? "Já existe sinistro com este número." : "Sinistro não encontrado.");
            }
        } catch (IllegalArgumentException e) {
            informarErro(e.getMessage());
        }
    }

    private void excluir() {
        if (dao.excluir(numero.getText())) {
            informar("Sinistro excluído.");
            limparCampos();
        } else {
            informarErro("Sinistro não encontrado.");
        }
    }

    @Override
    protected void limparCampos() {
        numero.setText("");
        placaVeiculo.setText("");
        dataHoraSinistro.setValue(new Date());
        dataHoraRegistro.setValue(new Date());
        usuarioRegistro.setText("");
        valorSinistro.setText("0.00");
        tipo.setSelectedIndex(0);
    }
}
