package Livraria.UI;

import Livraria.bancoDados.ConexaoBD;
import Livraria.bancoDados.ConexaoMySql;
import Livraria.Serviços.LivrariaServicos;

import javax.swing.*;
import java.awt.*;

public class LivrariaUI extends JFrame {
    private LivrariaServicos livrariaServicos;

    public static void main(String[] args) {
        ConexaoBD conexao = new ConexaoMySql();
        LivrariaServicos livrariaServicos = new LivrariaServicos(conexao);
        new LivrariaUI(livrariaServicos);
    }

    public LivrariaUI(LivrariaServicos livrariaServicos) {
        this.livrariaServicos = livrariaServicos;
        setTitle("Sistema de Livraria");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel painelTitulo = new JPanel();
        painelTitulo.setBackground(new Color(25, 118, 210));
        JLabel rotuloTitulo = new JLabel("Sistema de Livraria");
        rotuloTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        rotuloTitulo.setForeground(Color.WHITE);
        rotuloTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        painelTitulo.add(rotuloTitulo);

        add(painelTitulo, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JButton btnCadastrarLivro = criarBotao("Cadastrar Livro");
        JButton btnConsultarLivros = criarBotao("Consultar Livros");
        JButton btnCadastrarUsuario = criarBotao("Cadastrar Usuário");
        JButton btnRealizarReserva = criarBotao("Realizar Reserva");
        JButton btnRemoverLivro = criarBotao("Remover Livro");
        JButton btnRemoverUsuario = criarBotao("Remover Usuário");
        JButton btnRemoverReserva = criarBotao("Remover Reserva");
        JButton btnAtualizarLivro = criarBotao("Atualizar Livro");

        btnCadastrarLivro.addActionListener(eventoDeAçao -> abrirJanelaCadastroLivro());
        btnConsultarLivros.addActionListener(eventoDeAçao -> abrirJanelaConsultaLivros());
        btnCadastrarUsuario.addActionListener(eventoDeAçao -> abrirJanelaCadastroUsuario());
        btnRealizarReserva.addActionListener(eventoDeAçao -> abrirJanelaReserva());
        btnRemoverLivro.addActionListener(eventoDeAçao -> abrirJanelaRemocaoLivro());
        btnRemoverUsuario.addActionListener(eventoDeAçao -> abrirJanelaRemocaoUsuario());
        btnRemoverReserva.addActionListener(eventoDeAçao -> abrirJanelaRemocaoReserva());
        btnAtualizarLivro.addActionListener(eventoDeAçao -> abrirJanelaAtualizarLivro());

        mainPanel.add(btnCadastrarLivro);
        mainPanel.add(btnConsultarLivros);
        mainPanel.add(btnCadastrarUsuario);
        mainPanel.add(btnRealizarReserva);
        mainPanel.add(btnRemoverLivro);
        mainPanel.add(btnRemoverUsuario);
        mainPanel.add(btnRemoverReserva);
        mainPanel.add(btnAtualizarLivro);

        add(mainPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("SansSerif", Font.BOLD, 14));
        botao.setBackground(new Color(33, 150, 243));
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return botao;
    }

    private void abrirJanelaCadastroLivro() {
        JPanel painel = new JPanel(new GridLayout(3, 2, 10, 10));

        JTextField tituloCampo = new JTextField();
        JTextField categoriaCampo = new JTextField();
        JTextField autorCampo = new JTextField();
        painel.add(new JLabel("Título:"));
        painel.add(tituloCampo);
        painel.add(new JLabel("Categoria:"));
        painel.add(categoriaCampo);
        painel.add(new JLabel("Autor:"));
        painel.add(autorCampo);

        int result = JOptionPane.showConfirmDialog(this, painel, "Cadastrar Livro", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String titulo = tituloCampo.getText();
            String categoria = categoriaCampo.getText();
            String autor = autorCampo.getText();
            if (!titulo.isEmpty() && !categoria.isEmpty() && !autor.isEmpty()) {
                livrariaServicos.cadastrarLivro(titulo, categoria, autor);
            }
        }
    }

    private void abrirJanelaCadastroUsuario() {
        JPanel painel = new JPanel(new GridLayout(3, 2, 10, 10));

        JTextField nomeCampo = new JTextField();
        JTextField emailCampo = new JTextField();
        JTextField telefoneCampo = new JTextField();
        painel.add(new JLabel("Nome:"));
        painel.add(nomeCampo);
        painel.add(new JLabel("Email:"));
        painel.add(emailCampo);
        painel.add(new JLabel("Telefone:"));
        painel.add(telefoneCampo);

        int result = JOptionPane.showConfirmDialog(this, painel, "Cadastrar Usuário", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String nome = nomeCampo.getText();
            String email = emailCampo.getText();
            String telefone = telefoneCampo.getText();
            if (!nome.isEmpty() && !email.isEmpty() && !telefone.isEmpty()) {
                livrariaServicos.cadastrarUsuario(nome, email, telefone);
            }
        }
    }

    private void abrirJanelaReserva() {
        JPanel painel = new JPanel(new GridLayout(2, 2, 10, 10));

        JTextField usuarioCampo = new JTextField();
        JTextField livroCampo = new JTextField();
        painel.add(new JLabel("Nome do Usuário:"));
        painel.add(usuarioCampo);
        painel.add(new JLabel("Nome do Livro:"));
        painel.add(livroCampo);

        int result = JOptionPane.showConfirmDialog(this, painel, "Realizar Reserva", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String usuario = usuarioCampo.getText();
            String livro = livroCampo.getText();
            if (!usuario.isEmpty() && !livro.isEmpty()) {
                livrariaServicos.realizarReserva(livro, usuario);
            }
        }
    }

    private void abrirJanelaConsultaLivros() {
        JDialog dialog = new JDialog(this, "Consultar Livros", true);
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel rotuloTitulo = new JLabel("Consultar Livros");
        rotuloTitulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        rotuloTitulo.setForeground(new Color(25, 118, 210));
        rotuloTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        painel.add(rotuloTitulo, BorderLayout.NORTH);

        JPanel entradaPainel = new JPanel(new BorderLayout(10, 10));
        JLabel rotuloPesquisa = new JLabel("Digite o nome, categoria ou autor:");
        JTextField txtPesquisa = new JTextField(15);
        JButton btnBuscar = criarBotao("Buscar");
        entradaPainel.add(rotuloPesquisa, BorderLayout.NORTH);
        entradaPainel.add(txtPesquisa, BorderLayout.CENTER);
        entradaPainel.add(btnBuscar, BorderLayout.EAST);

        painel.add(entradaPainel, BorderLayout.CENTER);
        JTextArea resultadoArea = new JTextArea(10, 30);
        resultadoArea.setEditable(false);
        resultadoArea.setLineWrap(true);
        resultadoArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(resultadoArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Resultados da Consulta"));
        painel.add(scrollPane, BorderLayout.SOUTH);

        btnBuscar.addActionListener(eventoDeAçao -> {
            String consulta = txtPesquisa.getText().trim();
            if (!consulta.isEmpty()) {
                String resultado = livrariaServicos.consultarLivros(consulta);
                resultadoArea.setText(resultado);
            } else {
                resultadoArea.setText("Por favor, insira um termo para pesquisa");
            }
        });

        dialog.setContentPane(painel);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void abrirJanelaRemocaoLivro() {
        String titulo = JOptionPane.showInputDialog(this, "Informe o nome do livro a ser removido:");
        if (titulo != null && !titulo.isEmpty()) {
            livrariaServicos.removerLivro(titulo);
        }
    }

    private void abrirJanelaRemocaoUsuario() {
        String nome = JOptionPane.showInputDialog(this, "Informe o nome do usuário a ser removido:");
        if (nome != null && !nome.isEmpty()) {
            livrariaServicos.removerUsuario(nome);
        }
    }

    private void abrirJanelaRemocaoReserva() {
        JPanel painel = new JPanel(new GridLayout(2, 2, 10, 10));

        JTextField livroCampo = new JTextField();
        JTextField usuarioCampo = new JTextField();

        painel.add(new JLabel("Nome do Livro:"));
        painel.add(livroCampo);
        painel.add(new JLabel("Nome do Usuário:"));
        painel.add(usuarioCampo);

        int result = JOptionPane.showConfirmDialog(this, painel, "Remover Reserva", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String livro = livroCampo.getText();
            String usuario = usuarioCampo.getText();
            if (!livro.isEmpty() && !usuario.isEmpty()) {
                livrariaServicos.removerReserva(livro, usuario);
            }
        }
    }

    private void abrirJanelaAtualizarLivro() {
        String tituloAntigo = JOptionPane.showInputDialog(this, "Informe o título do livro a ser atualizado:");
        if (tituloAntigo != null && !tituloAntigo.isEmpty()) {
            JPanel painel = new JPanel(new GridLayout(3, 2, 10, 10));

            JTextField tituloCampo = new JTextField();
            JTextField categoriaCampo = new JTextField();
            JTextField autorCampo = new JTextField();
            painel.add(new JLabel("Novo Título:"));
            painel.add(tituloCampo);
            painel.add(new JLabel("Nova Categoria:"));
            painel.add(categoriaCampo);
            painel.add(new JLabel("Novo Autor:"));
            painel.add(autorCampo);

            int result = JOptionPane.showConfirmDialog(this, painel, "Atualizar Livro", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String novoTitulo = tituloCampo.getText();
                String novaCategoria = categoriaCampo.getText();
                String novoAutor = autorCampo.getText();

                if (!novoTitulo.isEmpty() && !novaCategoria.isEmpty() && !novoAutor.isEmpty()) {
                    livrariaServicos.atualizarLivro(tituloAntigo, novoTitulo, novaCategoria, novoAutor);
                }
            }
        }
    }
}
