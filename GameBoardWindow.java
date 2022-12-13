import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial") // Anotação para suprimir avisos de serialização
public class GameBoardWindow extends JFrame implements ActionListener { // JFrame é uma classe que representa uma janela

	private JMenuBar menuBar = null; // Barra de menu
	private JMenu fileMenu = null; // Menu

	private JMenuItem newGameMenuItem = null; // Item de menu
	private JMenuItem exitGameMenuItem = null; // Item de menu

	public GameBoardWindow(int level) { // Construtor

		setTitle("Snake2D Game"); // Título da janela
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define o comportamento padrão ao fechar a janela
		setBounds(100, 5, 1100, 700); // Define a posição e o tamanho da janela
		setResizable(false); // Define se a janela pode ser redimensionada

		getContentPane().add(new GameBoardPanel(level)); // Adiciona o painel do jogo à janela

		menuBar = new JMenuBar(); // Instancia a barra de menu

		fileMenu = new JMenu("File"); // Instancia o menu

		newGameMenuItem = new JMenuItem("New Game"); // Instancia o item de menu
		exitGameMenuItem = new JMenuItem("Exit"); // Instancia o item de menu

		fileMenu.add(newGameMenuItem); // Adiciona o item de menu ao menu
		fileMenu.add(exitGameMenuItem); // Adiciona o item de menu ao menu

		menuBar.add(fileMenu); // Adiciona o menu à barra de menu

		setJMenuBar(menuBar); // Adiciona a barra de menu à janela

		newGameMenuItem.addActionListener(this); // Adiciona o GameBoardWindow como listener do item de menu
		exitGameMenuItem.addActionListener(this); // Adiciona o GameBoardWindow como listener do item de menu

		setVisible(true); // Define a janela como visível

	}

	@Override // Sobrescreve o método da interface ActionListener
	public void actionPerformed(ActionEvent event) { // Método que é chamado quando um evento é disparado

		Object source = event.getSource(); // Obtém o objeto que disparou o evento

		if (source == newGameMenuItem) { // Se o objeto que disparou o evento for o item de menu newGameMenuItem
			setVisible(false); // Define a janela como invisível
			dispose(); // Libera os recursos utilizados pela janela

			new MainScreen(); // Instancia um novo objeto MainScreen
		}

		if (source == exitGameMenuItem) { // Se o objeto que disparou o evento for o item de menu exitGameMenuItem
			System.exit(0); // Encerra a aplicação
		}

	}
}
