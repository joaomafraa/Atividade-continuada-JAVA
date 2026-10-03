package br.edu.cs.poo.ac.seguro.telas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

abstract class TelaCrudBase extends JPanel {
    private final JPanel formulario = new JPanel(new GridBagLayout());
    private int linha;

    protected TelaCrudBase() {
        setLayout(new BorderLayout(8, 8));
        setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        add(new JScrollPane(formulario), BorderLayout.CENTER);
    }

    protected void adicionarCampo(String rotulo, java.awt.Component componente) {
        GridBagConstraints label = new GridBagConstraints();
        label.gridx = 0;
        label.gridy = linha;
        label.anchor = GridBagConstraints.EAST;
        label.insets = new Insets(4, 4, 4, 8);
        formulario.add(new JLabel(rotulo + ":"), label);

        GridBagConstraints campo = new GridBagConstraints();
        campo.gridx = 1;
        campo.gridy = linha++;
        campo.weightx = 1;
        campo.fill = GridBagConstraints.HORIZONTAL;
        campo.insets = new Insets(4, 4, 4, 4);
        formulario.add(componente, campo);
    }

    protected void adicionarBotoes(ActionListener buscar, ActionListener incluir,
            ActionListener alterar, ActionListener excluir, ActionListener limpar) {
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 4));
        botoes.add(botao("Buscar", buscar));
        botoes.add(botao("Incluir", incluir));
        botoes.add(botao("Alterar", alterar));
        botoes.add(botao("Excluir", excluir));
        botoes.add(botao("Limpar", limpar));
        add(botoes, BorderLayout.SOUTH);
    }

    private JButton botao(String texto, ActionListener acao) {
        JButton botao = new JButton(texto);
        botao.addActionListener(acao);
        return botao;
    }

    protected void informar(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Seguro", JOptionPane.INFORMATION_MESSAGE);
    }

    protected void informarErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Atenção", JOptionPane.WARNING_MESSAGE);
    }

    protected abstract void limparCampos();
}
