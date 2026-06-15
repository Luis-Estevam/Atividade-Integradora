package View;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {
    public Dashboard() {
        setTitle("Painel Principal");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza na tela

        // Adiciona apenas uma mensagem de boas-vindas para teste
        JLabel labelBemVindo = new JLabel("Bem-vindo ao Sistema!", SwingConstants.CENTER);
        labelBemVindo.setFont(new Font("Arial", Font.BOLD, 20));
        add(labelBemVindo);
    }
}
