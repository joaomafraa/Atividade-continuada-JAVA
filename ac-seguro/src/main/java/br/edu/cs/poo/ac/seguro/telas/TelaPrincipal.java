package br.edu.cs.poo.ac.seguro.telas;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class TelaPrincipal extends JFrame {
    public TelaPrincipal() {
        super("Sistema de Seguros");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(620, 620));

        JTabbedPane abas = new JTabbedPane();
        abas.addTab("Segurado pessoa", new TelaSeguradoPessoa());
        abas.addTab("Segurado empresa", new TelaSeguradoEmpresa());
        abas.addTab("Veículo", new TelaVeiculo());
        abas.addTab("Apólice", new TelaApolice());
        abas.addTab("Sinistro", new TelaSinistro());
        setContentPane(abas);
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                // A aparência padrão do Swing será usada se o tema do sistema não estiver disponível.
            }
            new TelaPrincipal().setVisible(true);
        });
    }
}
