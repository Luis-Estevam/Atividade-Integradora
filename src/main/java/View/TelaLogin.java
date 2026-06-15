package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLogin extends JFrame {

    public TelaLogin() {
        // Configurações básicas da janela (Title, Size, etc.)
        setTitle("Acesso ao Sistema");
        setSize(350, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setResizable(false);

        // Criando o painel principal com GridBagLayout para alinhar os componentes
        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10); // Margem entre os componentes
        constraints.anchor = GridBagConstraints.WEST;

        // --- Campo de E-mail ---
        JLabel labelEmail = new JLabel("E-mail:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        painel.add(labelEmail, constraints);

        JTextField txtEmail = new JTextField(15);
        constraints.gridx = 1;
        painel.add(txtEmail, constraints);

        // --- Campo de Senha ---
        JLabel labelSenha = new JLabel("Senha:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        painel.add(labelSenha, constraints);

        // JPasswordField oculta os caracteres digitados por segurança
        JPasswordField txtSenha = new JPasswordField(15);
        constraints.gridx = 1;
        painel.add(txtSenha, constraints);

        // --- Botão de Login ---
        JButton btnLogin = new JButton("Entrar");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2; // O botão ocupa duas colunas
        constraints.anchor = GridBagConstraints.CENTER; // Centraliza o botão
        painel.add(btnLogin, constraints);

        // --- Evento do Botão (Ação de Login) ---
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText();
                // Captura a senha de forma segura
                String senha = new String(txtSenha.getPassword());

                // Validação de teste (Substitua pela sua lógica de banco de dados)
                if (email.equals("admin@email.com") && senha.equals("1234")) {
                    JOptionPane.showMessageDialog(TelaLogin.this, "Login realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                    TelaLogin.this.dispose();

                    new Dashboard().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(TelaLogin.this, "E-mail ou senha inválidos.", "Erro de Autenticação", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Adiciona o painel configurado à janela principal
        add(painel);
    }

    public static void main(String[] args) {
        // Executa a interface gráfica na thread correta do Swing (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }
}
