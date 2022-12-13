import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class MainScreen extends JFrame implements ActionListener { // Tela principal do jogo

	private static final long serialVersionUID = -1299314404835604855L;  // Serial version UID

	JRadioButton levels[] = new JRadioButton[3]; // Array de botões de nível
	String levelStrings[] = { "Easy", "Normal", "Hard" }; // Array de strings de nível
	MainScreenPanel buttonPanel = null; // Painel de botões

	public MainScreen() { // Construtor

		buttonPanel = new MainScreenPanel(); // Instancia o painel de botões
 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define o comportamento padrão ao fechar a janela
		setBounds(320, 127, 600, 400); // Define a posição e o tamanho da janela
		setResizable(false); // Define se a janela pode ser redimensionada
		buttonPanel.setBackground(Color.BLACK); // Define a cor de fundo do painel de botões
		for (int i = 0; i < levels.length; i++) { // Laço de repetição para criar os botões de nível
			levels[i] = new JRadioButton(levelStrings[i]); // Instancia os botões de nível
			levels[i].addActionListener(this); // Adiciona o listener de ação
			levels[i].setBackground(Color.YELLOW); // Define a cor de fundo dos botões de nível
			levels[i].setBounds(260, 200 + i * 50, 80, 30); // Define a posição e o tamanho dos botões de nível
			buttonPanel.add(levels[i]); // Adiciona os botões de nível ao painel de botões
		}

		buttonPanel.setLayout(null); // Define o layout do painel de botões
		getContentPane().add(buttonPanel); // Adiciona o painel de botões à janela
		setVisible(true); // Define a visibilidade da janela
	}

	public void actionPerformed(ActionEvent e) { // Método de ação
		Object obj = e.getSource(); // Pega o objeto que gerou o evento

		if (obj == levels[0]) { // Se o objeto for o botão de nível 1

			new GameBoardWindow(1); // Instancia a janela do jogo
			setVisible(false); // Define a visibilidade da janela
			dispose(); // Libera os recursos da janela
 
		}

		if (obj == levels[1]) { // Se o objeto for o botão de nível 2

			new GameBoardWindow(2); // Instancia a janela do jogo
			setVisible(false); // Define a visibilidade da janela
			dispose(); // Libera os recursos da janela
		}

		if (obj == levels[2]) { // Se o objeto for o botão de nível 3

			new GameBoardWindow(3); // Instancia a janela do jogo
			setVisible(false); // Define a visibilidade da janela
			dispose(); // Libera os recursos da janela
		}
	}

	@SuppressWarnings("serial") // Anotação para suprimir o aviso de serialização
	class MainScreenPanel extends JPanel { // Classe interna para o painel de botões

		MainScreenPanel() {  // Construtor
			super(); // Chama o construtor da superclasse
		}

		public void paintComponent(Graphics g) { // Método de pintura
			super.paintComponent(g); // Chama o método de pintura da superclasse
			Graphics2D g2 = (Graphics2D) g; // Cria um objeto Graphics2D

			g2.setColor(Color.RED); // Define a cor do texto
			g2.setFont(new Font("Comic Sans MS", Font.BOLD, 45)); // Define a fonte do texto
			g2.drawString("Snake2D Game", 135, 85); // Desenha o texto
		}
	}
}