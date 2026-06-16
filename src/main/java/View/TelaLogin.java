package View;

import DAO.UsuarioDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLogin extends JFrame {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
    EntityManager em = emf.createEntityManager();

    public TelaLogin() {


        // Configurações básicas da janela (Title, Size, etc.)
        setTitle("Acesso ao Sistema");
        setSize(350, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setResizable(false);


        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10); // Margem entre os componentes
        constraints.anchor = GridBagConstraints.WEST;

        //E-mail
        JLabel labelEmail = new JLabel("E-mail:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        painel.add(labelEmail, constraints);

        JTextField txtEmail = new JTextField(15);
        constraints.gridx = 1;
        painel.add(txtEmail, constraints);

        // Senha
        JLabel labelSenha = new JLabel("Senha:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        painel.add(labelSenha, constraints);


        JPasswordField txtSenha = new JPasswordField(15);
        constraints.gridx = 1;
        painel.add(txtSenha, constraints);

        // Botão de Login
        JButton btnLogin = new JButton("Entrar");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2; // O botão ocupa duas colunas
        constraints.anchor = GridBagConstraints.CENTER; // Centraliza o botão
        painel.add(btnLogin, constraints);

        // Login
        btnLogin.addActionListener(e -> {
            String email = txtEmail.getText();
            String senha = new String(txtSenha.getPassword());


            UsuarioDAO usuarioDAO = new UsuarioDAO(this.em);

            if (usuarioDAO.autenticar(email, senha)) {
                JOptionPane.showMessageDialog(this, "Login realizado com sucesso!");


                Dashboard Dashboard = new Dashboard(this.em);
                Dashboard.setVisible(true);

                this.dispose(); // Fecha a tela de login
            } else {
                JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(painel);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }
}
