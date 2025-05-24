import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class LoginCadastro {


    // Armazena os usuários (login -> senha)
    private HashMap<String, String> usuarios = new HashMap<>();

    // Componentes principais
    private JFrame frame;
    private JPanel painelPrincipal;
    private CardLayout layout;

    public LoginCadastro() {
        // Cria a janela principal
        frame = new JFrame("Login e Cadastro");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null); // Centraliza na tela

        layout = new CardLayout();
        painelPrincipal = new JPanel(layout);

        // Adiciona as telas
        painelPrincipal.add(telaLogin(), "login");
        painelPrincipal.add(telaCadastro(), "cadastro");
        painelPrincipal.add(telaBoasVindas(""), "boasvindas");

        frame.add(painelPrincipal);
        frame.setVisible(true);
    }

    private JPanel telaLogin() {
        JPanel painel = new JPanel(new GridLayout(5, 1));
        JTextField campoUsuario = new JTextField();
        JPasswordField campoSenha = new JPasswordField();

        JButton botaoEntrar = new JButton("Entrar");
        JButton botaoCadastro = new JButton("Cadastrar");

        botaoEntrar.addActionListener(e -> {
            String usuario = campoUsuario.getText();
            String senha = new String(campoSenha.getPassword());

            if (usuarios.containsKey(usuario) && usuarios.get(usuario).equals(senha)) {
                painelPrincipal.add(telaBoasVindas(usuario), "boasvindas");
                layout.show(painelPrincipal, "boasvindas");
            } else {
                JOptionPane.showMessageDialog(frame, "Usuário ou senha incorretos.");
            }
        });

        botaoCadastro.addActionListener(e -> layout.show(painelPrincipal, "cadastro"));

        painel.add(new JLabel("Usuário:"));
        painel.add(campoUsuario);
        painel.add(new JLabel("Senha:"));
        painel.add(campoSenha);
        painel.add(botaoEntrar);
        painel.add(botaoCadastro);

        return painel;
    }

    private JPanel telaCadastro() {
        JPanel painel = new JPanel(new GridLayout(6, 1));
        JTextField campoNovoUsuario = new JTextField();
        JPasswordField campoNovaSenha = new JPasswordField();

        JButton botaoRegistrar = new JButton("Registrar");
        JButton botaoVoltar = new JButton("Voltar");

        botaoRegistrar.addActionListener(e -> {
            String usuario = campoNovoUsuario.getText();
            String senha = new String(campoNovaSenha.getPassword());

            if (usuario.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Preencha todos os campos.");
            } else if (usuarios.containsKey(usuario)) {
                JOptionPane.showMessageDialog(frame, "Usuário já existe.");
            } else {
                usuarios.put(usuario, senha);
                JOptionPane.showMessageDialog(frame, "Usuário cadastrado com sucesso!");
                layout.show(painelPrincipal, "login");
            }
        });

        botaoVoltar.addActionListener(e -> layout.show(painelPrincipal, "login"));

        painel.add(new JLabel("Novo usuário:"));
        painel.add(campoNovoUsuario);
        painel.add(new JLabel("Nova senha:"));
        painel.add(campoNovaSenha);
        painel.add(botaoRegistrar);
        painel.add(botaoVoltar);

        return painel;
    }

    private JPanel telaBoasVindas(String nomeUsuario) {
        JPanel painel = new JPanel();
        JLabel mensagem = new JLabel("Bem-vindo, " + nomeUsuario + "!");
        mensagem.setFont(new Font("Arial", Font.BOLD, 18));
        painel.add(mensagem);
        return painel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginCadastro());
    }
}
