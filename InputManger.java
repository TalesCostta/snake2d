import java.awt.event.KeyEvent; 
import java.awt.event.KeyListener; 

public class InputManger implements KeyListener { // Classe responsável por gerenciar as entradas do teclado

	private GameBoardPanel gameBoard; // Instanciando a classe GameBoardPanel

	public InputManger(GameBoardPanel gameBoard) { // Construtor da classe InputManger
		this.gameBoard = gameBoard; // Recebe o parâmetro gameBoard
	}

	public void keyTyped(KeyEvent e) { // Método que verifica se uma tecla foi pressionada
	}

	public void keyPressed(KeyEvent e) { // Método que verifica se uma tecla foi solta

		int key = e.getKeyCode(); // Obtém o código da tecla pressionada

		if (key == KeyEvent.VK_UP) { // Se a tecla pressionada for a seta para cima

			gameBoard.changeSnakeDirection(1); // Chama o método changeSnakeDirection da classe GameBoardPanel

		} else if (key == KeyEvent.VK_DOWN) { // Se a tecla pressionada for a seta para baixo

			gameBoard.changeSnakeDirection(2); // Chama o método changeSnakeDirection da classe GameBoardPanel

		} else if (key == KeyEvent.VK_RIGHT) { // Se a tecla pressionada for a seta para a direita

			gameBoard.changeSnakeDirection(3); // Chama o método changeSnakeDirection da classe GameBoardPanel

		} else if (key == KeyEvent.VK_LEFT) { // Se a tecla pressionada for a seta para a esquerda

			gameBoard.changeSnakeDirection(4); // Chama o método changeSnakeDirection da classe GameBoardPanel

		} else if (key == KeyEvent.VK_SPACE) { // Se a tecla pressionada for a barra de espaço

			if (gameBoard.isGameRunning()) { // Se o jogo estiver rodando
				gameBoard.pauseGame(); // Chama o método pauseGame da classe GameBoardPanel

			} else { // Se o jogo não estiver rodando
				gameBoard.startGame(); // Chama o método startGame da classe GameBoardPanel

			}

		} else if (key == KeyEvent.VK_ESCAPE) { // Se a tecla pressionada for a tecla ESC

			System.exit(0); // Encerra o jogo
		}
	}

	public void keyReleased(KeyEvent e) { // Método que verifica se uma tecla foi solta
	}

}
