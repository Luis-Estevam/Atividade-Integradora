package View;
import DAO.*;
import Models.*;
import jakarta.persistence.EntityManager;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Dashboard extends JFrame {

    private CardLayout cardLayout;
    private JPanel painelConteudo;
    private EntityManager em;

    // Declaração de todos os DAOs
    private UsuarioDAO usuarioDAO;
    private TipoSoloDAO tipoSoloDAO;
    private ClasseSoloDAO classeSoloDAO;
    private ComplexoAgricolaDAO complexoAgricolaDAO;
    private ContornoDAO contornoDAO;
    private FaixaSoloDAO faixaSoloDAO;


    public interface CrudOperation {
        List<Object[]> listar();
        void inserir(String[] campos) throws Exception;
        void atualizar(Object id, String[] campos) throws Exception;
        void deletar(Object id) throws Exception;
    }

    public Dashboard(EntityManager em) {
        this.em = em;

        // Inicialização dos DAOs
        this.usuarioDAO = new UsuarioDAO(em);
        this.tipoSoloDAO = new TipoSoloDAO(em);
        this.classeSoloDAO = new ClasseSoloDAO(em);
        this.complexoAgricolaDAO = new ComplexoAgricolaDAO(em);
        this.contornoDAO = new ContornoDAO(em);
        this.faixaSoloDAO = new FaixaSoloDAO(em);

        setTitle("Sistema de Gestão Agrícola - Ligado ao Banco de Dados");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        // MENU
        JPanel painelLateral = new JPanel();
        painelLateral.setBackground(new Color(43, 43, 43));
        painelLateral.setPreferredSize(new Dimension(220, 0));
        painelLateral.setLayout(new GridLayout(8, 1, 0, 5));

        JButton btnUsuarios = criarBotaoMenu("Usuários");
        JButton btnTipoSolo = criarBotaoMenu("Tipos de Solo");
        JButton btnClasseSolo = criarBotaoMenu("Classes de Solo");
        JButton btnComplexos = criarBotaoMenu("Complexos Agrícolas");
        JButton btnContornos = criarBotaoMenu("Contornos");
        JButton btnFaixas = criarBotaoMenu("Faixas de Solo");
        JButton btnSair = criarBotaoMenu("Sair");

        painelLateral.add(btnUsuarios);
        painelLateral.add(btnTipoSolo);
        painelLateral.add(btnClasseSolo);
        painelLateral.add(btnComplexos);
        painelLateral.add(btnContornos);
        painelLateral.add(btnFaixas);
        painelLateral.add(new JLabel(""));
        painelLateral.add(btnSair);


        // SUBTELAS
        cardLayout = new CardLayout();
        painelConteudo = new JPanel(cardLayout);

        // Definição das subtelas mapeando os inputs gráficos para os DAOs reais
        JPanel telaUsuarios = criarPainelCrud("Gerenciamento de Usuários",
                new String[]{"Nome", "Email", "Senha", "Perfil"}, obterOpUsuarios());

        JPanel telaTipoSolo = criarPainelCrud("Tipos de Solo",
                new String[]{"Descrição", "Origem Geológica"}, obterOpTipoSolo());

        JPanel telaClasseSolo = criarPainelCrud("Classes de Solo",
                new String[]{"Nome", "Propriedades Físicas", "ID Tipo Solo"}, obterOpClasseSolo());

        JPanel telaComplexos = criarPainelCrud("Complexos Agrícolas",
                new String[]{"Nome", "Área", "Utilização"}, obterOpComplexos());

        JPanel telaContornos = criarPainelCrud("Contornos",
                new String[]{"Coordenadas", "Área", "ID Classe Solo", "ID Complexo"}, obterOpContornos());

        JPanel telaFaixas = criarPainelCrud("Faixas de Solo",
                new String[]{"Profundidade", "Localização", "ID Complexo"}, obterOpFaixas());

        painelConteudo.add(telaUsuarios, "usuarios");
        painelConteudo.add(telaTipoSolo, "tipoSolo");
        painelConteudo.add(telaClasseSolo, "classeSolo");
        painelConteudo.add(telaComplexos, "complexos");
        painelConteudo.add(telaContornos, "contornos");
        painelConteudo.add(telaFaixas, "faixas");

        // Eventos de Navegação
        btnUsuarios.addActionListener(e -> cardLayout.show(painelConteudo, "usuarios"));
        btnTipoSolo.addActionListener(e -> cardLayout.show(painelConteudo, "tipoSolo"));
        btnClasseSolo.addActionListener(e -> cardLayout.show(painelConteudo, "classeSolo"));
        btnComplexos.addActionListener(e -> cardLayout.show(painelConteudo, "complexos"));
        btnContornos.addActionListener(e -> cardLayout.show(painelConteudo, "contornos"));
        btnFaixas.addActionListener(e -> cardLayout.show(painelConteudo, "faixas"));

        btnSair.addActionListener(e -> {
            int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente sair?", "Sair", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) System.exit(0);
        });

        add(painelLateral, BorderLayout.WEST);
        add(painelConteudo, BorderLayout.CENTER);
    }

    private JButton criarBotaoMenu(String texto) {
        JButton botao = new JButton(texto);
        botao.setForeground(Color.WHITE);
        botao.setBackground(new Color(60, 63, 65));
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        return botao;
    }


    // gerador de tabelas
    private JPanel criarPainelCrud(String titulo, String[] colunas, CrudOperation operacao) {
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        painel.add(lblTitulo, BorderLayout.NORTH);

        // colunas da JTable
        String[] colunasTabela = new String[colunas.length + 1];
        colunasTabela[0] = "ID";
        System.arraycopy(colunas, 0, colunasTabela, 1, colunas.length);

        DefaultTableModel modeloTabela = new DefaultTableModel(colunasTabela, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        JTable tabela = new JTable(modeloTabela);
        painel.add(new JScrollPane(tabela), BorderLayout.CENTER);


        Runnable recarregarDados = () -> {
            modeloTabela.setRowCount(0);
            try {
                List<Object[]> linhas = operacao.listar();
                for (Object[] linha : linhas) {
                    modeloTabela.addRow(linha);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(painel, "Erro ao carregar dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        };

        // Carrega os dados assim que a tela abre
        recarregarDados.run();


        JPanel painelSul = new JPanel(new BorderLayout());
        JPanel painelForm = new JPanel(new GridLayout(2, colunas.length, 10, 5));
        JTextField[] textFields = new JTextField[colunas.length];

        for (int i = 0; i < colunas.length; i++) painelForm.add(new JLabel(colunas[i] + ":"));
        for (int i = 0; i < colunas.length; i++) {
            textFields[i] = new JTextField();
            painelForm.add(textFields[i]);
        }

        // Botões de Ação
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnDeletar = new JButton("Deletar");

        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnDeletar);

        painelSul.add(painelForm, BorderLayout.CENTER);
        painelSul.add(painelBotoes, BorderLayout.SOUTH);
        painel.add(painelSul, BorderLayout.SOUTH);

        // evento dos botões

        btnAdicionar.addActionListener(e -> {
            try {
                String[] valores = new String[textFields.length];
                for (int i = 0; i < textFields.length; i++) valores[i] = textFields[i].getText();

                operacao.inserir(valores);
                JOptionPane.showMessageDialog(painel, "Registo salvo com sucesso!");
                recarregarDados.run();
                for (JTextField tf : textFields) tf.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(painel, "Erro ao salvar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnDeletar.addActionListener(e -> {
            int linhaSelecionada = tabela.getSelectedRow();
            if (linhaSelecionada >= 0) {
                Object id = modeloTabela.getValueAt(linhaSelecionada, 0);
                int conf = JOptionPane.showConfirmDialog(painel, "Excluir o registo ID " + id + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (conf == JOptionPane.YES_OPTION) {
                    try {
                        operacao.deletar(id);
                        recarregarDados.run();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(painel, "Erro ao deletar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(painel, "Selecione um registo na tabela para deletar.");
            }
        });

        btnAtualizar.addActionListener(e -> {
            int linhaSelecionada = tabela.getSelectedRow();
            if (linhaSelecionada >= 0) {
                Object id = modeloTabela.getValueAt(linhaSelecionada, 0);
                try {
                    String[] valores = new String[textFields.length];
                    for (int i = 0; i < textFields.length; i++) valores[i] = textFields[i].getText();

                    operacao.atualizar(id, valores);
                    JOptionPane.showMessageDialog(painel, "Registo atualizado!");
                    recarregarDados.run();
                    for (JTextField tf : textFields) tf.setText("");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(painel, "Erro ao atualizar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(painel, "Selecione uma linha na tabela e preencha os campos abaixo para atualizar.");
            }
        });

        // Evento visual para preencher os inputs ao clicar numa linha da tabela
        tabela.getSelectionModel().addListSelectionListener(e -> {
            int linha = tabela.getSelectedRow();
            if (linha >= 0) {
                for (int i = 0; i < textFields.length; i++) {
                    Object val = tabela.getValueAt(linha, i + 1);
                    textFields[i].setText(val != null ? val.toString() : "");
                }
            }
        });

        return painel;
    }

    // Classes Operation

    private CrudOperation obterOpUsuarios() {
        return new CrudOperation() {
            public List<Object[]> listar() {
                List<Object[]> linhas = new ArrayList<>();
                for (Usuario u : usuarioDAO.listarUsuarios()) {
                    linhas.add(new Object[]{u.getId(), u.getNome(), u.getEmail(), "******", u.getPerfil()});
                }
                return linhas;
            }
            public void inserir(String[] c) {
                usuarioDAO.cadastrar(new Usuario(c[0], c[1], c[2], c[3]));
            }
            public void atualizar(Object id, String[] c) {

                em.getTransaction().begin();
                Usuario u = em.find(Usuario.class, id);
                if (u != null) { u.setNome(c[0]); u.setEmail(c[1]); u.setSenha(c[2]); u.setPerfil(c[3]); }
                em.getTransaction().commit();
            }
            public void deletar(Object id) {
                em.getTransaction().begin();
                Usuario u = em.find(Usuario.class, id);
                if (u != null) em.remove(u);
                em.getTransaction().commit();
            }
        };
    }

    private CrudOperation obterOpTipoSolo() {
        return new CrudOperation() {
            public List<Object[]> listar() {
                List<Object[]> linhas = new ArrayList<>();
                for (TipoSolo t : tipoSoloDAO.listarTodos()) {
                    linhas.add(new Object[]{t.getId(), t.getDescricao(), t.getOrigemGeologica()});
                }
                return linhas;
            }
            public void inserir(String[] c) { tipoSoloDAO.salvar(new TipoSolo(c[0], c[1])); }
            public void atualizar(Object id, String[] c) {
                TipoSolo t = tipoSoloDAO.buscarPorId((Integer) id);
                if (t != null) { t.setDescricao(c[0]); t.setOrigemGeologica(c[1]); tipoSoloDAO.atualizar(t); }
            }
            public void deletar(Object id) { tipoSoloDAO.deletar((Integer) id); }
        };
    }

    private CrudOperation obterOpClasseSolo() {
        return new CrudOperation() {
            public List<Object[]> listar() {
                List<Object[]> linhas = new ArrayList<>();
                for (ClasseSolo cs : classeSoloDAO.listarTodos()) {
                    linhas.add(new Object[]{cs.getId(), cs.getNome(), cs.getPropriedadesFisicas(),
                            cs.getTipoSolo() != null ? cs.getTipoSolo().getId() : "N/A"});
                }
                return linhas;
            }
            public void inserir(String[] c) {
                TipoSolo ts = tipoSoloDAO.buscarPorId(Integer.parseInt(c[2]));
                classeSoloDAO.salvar(new ClasseSolo(c[0], c[1], ts));
            }
            public void atualizar(Object id, String[] c) {
                ClasseSolo cs = classeSoloDAO.buscarPorId((Integer) id);
                if (cs != null) {
                    TipoSolo ts = tipoSoloDAO.buscarPorId(Integer.parseInt(c[2]));
                    cs.setNome(c[0]); cs.setPropriedadesFisicas(c[1]); cs.setTipoSolo(ts);
                    classeSoloDAO.atualizar(cs);
                }
            }
            public void deletar(Object id) { classeSoloDAO.deletar((Integer) id); }
        };
    }

    private CrudOperation obterOpComplexos() {
        return new CrudOperation() {
            public List<Object[]> listar() {
                List<Object[]> linhas = new ArrayList<>();
                for (ComplexoAgricola ca : complexoAgricolaDAO.listarTodos()) {
                    linhas.add(new Object[]{ca.getId(), ca.getNome(), ca.getArea(), ca.getUtilizacao()});
                }
                return linhas;
            }
            public void inserir(String[] c) {
                complexoAgricolaDAO.salvar(new ComplexoAgricola(c[0], Double.parseDouble(c[1]), c[2]));
            }
            public void atualizar(Object id, String[] c) {
                ComplexoAgricola ca = complexoAgricolaDAO.buscarPorId(((Number) id).intValue());
                if (ca != null) {
                    ca.setNome(c[0]); ca.setArea(Double.parseDouble(c[1])); ca.setUtilizacao(c[2]);
                    complexoAgricolaDAO.atualizar(ca);
                }
            }
            public void deletar(Object id) { complexoAgricolaDAO.deletar(((Number) id).intValue()); }
        };
    }

    private CrudOperation obterOpContornos() {
        return new CrudOperation() {
            public List<Object[]> listar() {
                List<Object[]> linhas = new ArrayList<>();
                for (Contorno co : contornoDAO.listarTodos()) {
                    linhas.add(new Object[]{co.getId(), co.getCoordenadas(), co.getArea(),
                            co.getClasseSolo() != null ? co.getClasseSolo().getId() : "N/A",
                            co.getComplexoAgricola() != null ? co.getComplexoAgricola().getId() : "N/A"});
                }
                return linhas;
            }
            public void inserir(String[] c) {
                ClasseSolo cs = classeSoloDAO.buscarPorId(Integer.parseInt(c[2]));
                ComplexoAgricola ca = complexoAgricolaDAO.buscarPorId(Integer.parseInt(c[3]));
                contornoDAO.salvar(new Contorno(c[0], Double.parseDouble(c[1]), cs, ca));
            }
            public void atualizar(Object id, String[] c) {
                Contorno co = contornoDAO.buscarPorId((Integer) id);
                if (co != null) {
                    ClasseSolo cs = classeSoloDAO.buscarPorId(Integer.parseInt(c[2]));
                    ComplexoAgricola ca = complexoAgricolaDAO.buscarPorId(Integer.parseInt(c[3]));
                    co.setCoordenadas(c[0]); co.setArea(Double.parseDouble(c[1])); co.setClasseSolo(cs); co.setComplexoAgricola(ca);
                    contornoDAO.atualizar(co);
                }
            }
            public void deletar(Object id) { contornoDAO.deletar((Integer) id); }
        };
    }

    private CrudOperation obterOpFaixas() {
        return new CrudOperation() {
            public List<Object[]> listar() {
                List<Object[]> linhas = new ArrayList<>();
                for (FaixaSolo f : faixaSoloDAO.listarTodos()) {
                    linhas.add(new Object[]{f.getId(), f.getProfundidade(), f.getLocalizacao(),
                            f.getComplexoAgricola() != null ? f.getComplexoAgricola().getId() : "N/A"});
                }
                return linhas;
            }
            public void inserir(String[] c) {
                ComplexoAgricola ca = complexoAgricolaDAO.buscarPorId(Integer.parseInt(c[2]));
                faixaSoloDAO.salvar(new FaixaSolo(Double.parseDouble(c[0]), c[1], ca));
            }
            public void atualizar(Object id, String[] c) {
                FaixaSolo f = faixaSoloDAO.buscarPorId((Integer) id);
                if (f != null) {
                    ComplexoAgricola ca = complexoAgricolaDAO.buscarPorId(Integer.parseInt(c[2]));
                    f.setProfundidade(Double.parseDouble(c[0])); f.setLocalizacao(c[1]); f.setComplexoAgricola(ca);
                    faixaSoloDAO.atualizar(f);
                }
            }
            public void deletar(Object id) { faixaSoloDAO.deletar((Integer) id); }
        };
    }
}