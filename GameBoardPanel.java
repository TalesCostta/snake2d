import java.awt.Color; // importar Color 
import java.awt.Font; // importar Font
import java.awt.Graphics; // importar Graphics class
import java.awt.Graphics2D;
import java.awt.event.ActionEvent; // importar ActionEvent class Um evento semântico que indica que ocorreu uma ação definida pelo componente. Esse evento de alto nível é gerado por um componente (como um Button) quando ocorre a ação específica do componente (como ser pressionado). O evento é passado para cada ActionListenerobjeto que se cadastrou para receber tais eventos usando o addActionListener do componente.
import java.awt.event.ActionListener; 
import java.awt.geom.Ellipse2D; // Trabalhar com Coordenadas
import java.awt.geom.Rectangle2D; // Trabalhar com Coordenadas

import javax.swing.JPanel; // importar JPanel (Container)
import javax.swing.Timer;// importar Timer

@SuppressWarnings("serial") // Anotação para suprimir avisos de serialização
public class GameBoardPanel extends JPanel implements ActionListener { 

	/** Creates a new instance of GameBoard */

	private Snake snake; // Instanciar a classe Snake
	private SnakeFood snakeFood; // Instanciar a classe SnakeFood

	private InputManger inputManager; // Instanciar a classe InputManger

	private Timer gameThread; // Instanciar a classe Timer
	private Timer timerThread; // Instanciar a classe Timer

	private boolean isGameOver = false; // Variável para verificar se o jogo acabou

	private int timer = 0; // Variável para contar o tempo
	private int playerScore = 0; // Variável para contar a pontuação

	public GameBoardPanel(int level) { // Construtor da classe GameBoardPanel

		setBackground(Color.BLACK); // Definir a cor de fundo do painel
		setFocusable(true); // Definir o painel como focável

		snake = new Snake(); // Instanciar a classe Snake
		snakeFood = new SnakeFood(); // Instanciar a classe SnakeFood

		inputManager = new InputManger(this); // Instanciar a classe InputManger

		gameThread = new Timer(getDelay(level), this); // Definir o tempo de atualização do jogo

		timerThread = new Timer(1000, new ActionListener() { // Definir o tempo de atualização do timer

			@Override // Sobrepor o método actionPerformed
			public void actionPerformed(ActionEvent e) { // Método para atualizar o timer

				if (isGameOver()) {
					stopGame();
				}

				timer++;
			}
		});

		// timerThread.setLogTimers(true);
		// gameThread.setLogTimers(true);

		addKeyListener(inputManager); // Adicionar o inputManager como KeyListener

	}

	private int getDelay(int level) { // Método para definir o tempo de atualização do jogo

		int delay = 0; // Variável para armazenar o delay

		if (level == 1) { // Se o nível for 1
			delay = 140;
		} else if (level == 2) { // Se o nível for 2
			delay = 70;

		} else if (level == 3) { // Se o nível for 3
			delay = 40;

		}
		return delay; // Retornar o delay
	}

	public void paintComponent(Graphics g) { // Método para desenhar o painel
		super.paintComponent(g); // Chamar o método paintComponent da classe pai

		doDrawing(g); // Chamar o método doDrawing
	}

	public void doDrawing(Graphics g) { // Método para desenhar o jogo

		Graphics2D g2 = (Graphics2D) g; // Instanciar a classe Graphics2D

		if (isGameRunning()) { // Se o jogo estiver rodando

			snake.move(); // Chamar o método move da classe Snake

			checkCollision(); // Chamar o método checkCollision

			DrawSnakeFood(g2); // Chamar o método DrawSnakeFood

		}

		DrawStatusbar(g2); // Chamar o método DrawStatusbar

		DrawBoundry(g2); // Chamar o método DrawBoundry

		DrawSnake(g2); // Chamar o método DrawSnake

	}

	public void DrawBoundry(Graphics2D g2) { // Método para desenhar a borda
		for (int i = 0; i < 17; i++) { // Para cada i menor que 17
			Rectangle2D.Double rect = new Rectangle2D.Double(227.0 - i, // Desenhar um retângulo
					127.0 - i, 624, 480);

			g2.setColor(Color.YELLOW); // Definir a cor

			g2.draw(rect); // Desenhar o retângulo

		}
	}

	public void DrawSnake(Graphics2D g2) { // Método para desenhar a cobra

		for (int i = 0; i < snake.getSnakeBody().size(); i++) { // Para cada i menor que o tamanho da cobra

			if (i == 0) {
				g2.setColor(Color.RED); // Definir a cor
				g2.fill(snake.getSnakeBody().get(i)); // Adicionar tamanho na cobra

			} else {
				g2.setColor(Color.ORANGE); // Definir a cor
				g2.draw(snake.getSnakeBody().get(i)); // Desenhar a cobra
			}

		}
	}

	public void DrawSnakeFood(Graphics2D g2) { // Método para desenhar a comida
		g2.setColor(Color.GREEN); // Definir a cor	
		g2.fill(snakeFood.getFood()); // Desenhar a comida
	}

	public void DrawStatusbar(Graphics2D g2) { // Método para desenhar a barra de status
		g2.setColor(Color.RED); // Definir a cor
		g2.setFont(new Font("Comic Sans MS", Font.BOLD, 35)); // Definir a fonte
		g2.drawString("Snake2D Game", 390, 50); // Desenhar o texto

		g2.setFont(new Font("Comic Sans MS", Font.PLAIN, 15)); // Definir a fonte
		g2.setColor(Color.WHITE); // Definir a cor
		g2.drawString("Press Esc for exit!", 5, 20); // Desenhar o texto
		g2.drawString("Press Spacebar for pause!", 5, 50); // Desenhar o texto

		g2.setFont(new Font("Comic Sans MS", Font.BOLD, 20)); // Definir a fonte
		g2.drawString("Time: ", 210, 100); // Desenhar o texto
		g2.drawString("Your Score: ", 680, 100); // Desenhar o texto
		g2.setColor(Color.BLUE); // Definir a cor
		g2.drawString("" + playerScore, 810, 100); // Desenhar o texto
		g2.drawString("" + timer, 270, 100); // Desenhar o texto

		if (isGameOver()) { // Se o jogo acabou
			g2.setColor(Color.WHITE); // Definir a cor
			g2.drawString("Game Over!", 480, 350); // Desenhar o texto

		} else if (!isGameRunning()) { // Se o jogo não estiver rodando
			g2.setColor(Color.WHITE); // Definir a cor
			g2.drawString("Press SpaceBar to Start Game!", 400, 500); // Desenhar o texto
		}

	}

	public void changeSnakeDirection(int direction) { // Método para mudar a direção da cobra
		this.snake.setDirection(direction); // Definir a direção da cobra
	}

	public void checkCollision() { // Método para verificar a colisão

		if (isSelfCollisioned() || isBoundryCollisioned()) { // Se a cobra colidiu com ela mesma ou com a borda

			isGameOver = true; // Definir o jogo como acabado

			stopGame(); // Parar o jogo

		}

		if (isFoodCollisioned()) { // Se a cobra colidiu com a comida

			snake.eat(); // Chamar o método eat da classe Snake
			snakeFood = new SnakeFood(); // Criar uma nova comida
			playerScore += 5; // Adicionar 5 pontos ao score do jogador
		}
	}

	public boolean isBoundryCollisioned() { // Método para verificar se a cobra colidiu com a borda
		if (snake.getDirection() == 1) { // Se a direção da cobra for 1
			double centerY = ((Ellipse2D.Double) snake.getSnakeBody().get(0)) // Definir o centro da cobra
					.getMinY(); 
			return centerY < 127; // Retornar se o centro da cobra é menor que 127
		} else if (snake.getDirection() == 2) { // Se a direção da cobra for 2
			double centerY = ((Ellipse2D.Double) snake.getSnakeBody().get(0)) // Definir o centro da cobra
					.getMaxY(); 
			return centerY > 591; // Retornar se o centro da cobra é maior que 591
		} else if (snake.getDirection() == 3) { // Se a direção da cobra for 3
			double centerX = ((Ellipse2D.Double) snake.getSnakeBody().get(0)).x; // Definir o centro da cobra
			return centerX > 819;
		} else if (snake.getDirection() == 4) { // Se a direção da cobra for 4
			double centerX = ((Ellipse2D.Double) snake.getSnakeBody().get(0)) // Definir o centro da cobra
					.getMinX();
			return centerX < 227.0; // Retornar se o centro da cobra é menor que 227
		}
		return false;
	}

	public boolean isSelfCollisioned() { // Método para verificar se a cobra colidiu com ela mesma

		if (snake.getDirection() == 1) { // Se a direção da cobra for 1
			for (int i = 1; i < snake.getSnakeBody().size(); i++) { // Para cada parte da cobra
				if ((((Ellipse2D.Double) snake.getSnakeBody().get(0)).getMinY() == ((Ellipse2D.Double) snake // Se o centro da cobra for igual ao centro de outra parte da cobra
						.getSnakeBody().get(i)).getMaxY()) // Retornar verdadeiro
						&& (((Ellipse2D.Double) snake.getSnakeBody().get(0)) // Se o centro da cobra for igual ao centro de outra parte da cobra
								.getCenterX() == ((Ellipse2D.Double) snake // Retornar verdadeiro
								.getSnakeBody().get(i)).getCenterX())) { 
					return true;
				}
			}

		} else if (snake.getDirection() == 2) { 
			for (int i = 1; i < snake.getSnakeBody().size(); i++) {
				if ((((Ellipse2D.Double) snake.getSnakeBody().get(0)).getMaxY() == ((Ellipse2D.Double) snake
						.getSnakeBody().get(i)).getMinY())
						&& (((Ellipse2D.Double) snake.getSnakeBody().get(0))
								.getCenterX() == ((Ellipse2D.Double) snake
								.getSnakeBody().get(i)).getCenterX())) {
					return true;
				}
			}

		} else if (snake.getDirection() == 3) {
			for (int i = 1; i < snake.getSnakeBody().size(); i++) {
				if ((((Ellipse2D.Double) snake.getSnakeBody().get(0)).getMaxX() == ((Ellipse2D.Double) snake
						.getSnakeBody().get(i)).getMinX())
						&& (((Ellipse2D.Double) snake.getSnakeBody().get(0))
								.getCenterY() == ((Ellipse2D.Double) snake
								.getSnakeBody().get(i)).getCenterY())) {
					return true;
				}
			}

		} else if (snake.getDirection() == 4) {
			for (int i = 1; i < snake.getSnakeBody().size(); i++) {
				if ((((Ellipse2D.Double) snake.getSnakeBody().get(0)).getMinX() == ((Ellipse2D.Double) snake
						.getSnakeBody().get(i)).getMaxX())
						&& (((Ellipse2D.Double) snake.getSnakeBody().get(0))
								.getCenterY() == ((Ellipse2D.Double) snake
								.getSnakeBody().get(i)).getCenterY())) {
					return true;
				}
			}
		}

		return false;

	}

	public boolean isFoodCollisioned() { // Método para verificar se a cobra colidiu com a comida

		boolean collisionedWithFood = false; // Definir se a cobra colidiu com a comida como falso

		int direction = snake.getDirection(); // Definir a direção da cobra

		Ellipse2D.Double head = snake.getHead(); // Definir a cabeça da cobra

		if (direction == 1) { // Se a direção da cobra for 1
			if ((head.getCenterY() == snakeFood.getFood().getCenterY()) // Se o centro da cobra for igual ao centro da comida
					&& (head.getCenterX() == snakeFood.getFood().getCenterX())) { // Retornar verdadeiro
				collisionedWithFood = true;
			} else
				collisionedWithFood = false;
		} else if (direction == 2) {

			if ((head.getCenterY() == snakeFood.getFood().getCenterY())
					&& (head.getCenterX() == snakeFood.getFood().getCenterX())) {
				collisionedWithFood = true;
			} else
				collisionedWithFood = false;

		} else if (direction == 3) {
			if ((head.getCenterX() == snakeFood.getFood().getCenterX())
					&& (head.getCenterY() == snakeFood.getFood().getCenterY())) {
				collisionedWithFood = true;
			} else
				collisionedWithFood = false;
		} else if (direction == 4) {
			if ((head.getCenterX() == snakeFood.getFood().getCenterX())
					&& (head.getCenterY() == snakeFood.getFood().getCenterY())) {
				collisionedWithFood = true;
			} else
				collisionedWithFood = false;
		}

		return collisionedWithFood;

	}

	public void startGame() { // Iniciar o jogo

		if (gameThread.isRunning()) { // Se o jogo estiver rodando
			gameThread.restart(); // Reiniciar o jogo
			timerThread.restart(); // Reiniciar o timer

		} else {
			gameThread.start(); // Iniciar o jogo
			timerThread.start(); // Iniciar o timer
		}

	}

	public void pauseGame() { // Pausar o jogo

		gameThread.stop(); // Parar o jogo
		timerThread.stop(); // Parar o timer
		repaint(); // Repintar o jogo

	}

	public void stopGame() { // Parar o jogo	

		gameThread.stop(); // Parar o jogo
		timerThread.stop(); // Parar o timer

	}

	public boolean isGameRunning() { // Verificar se o jogo está rodando
		return gameThread.isRunning() && !isGameOver(); // Retornar verdadeiro se o jogo estiver rodando e não estiver game over
	}

	public boolean isGameOver() { // Verificar se o jogo acabou
		return isGameOver;
	}

	@Override // Sobrescrever o método paintComponent
	public void actionPerformed(ActionEvent arg0) { // Método para verificar se o jogo acabou

		repaint(); // Repintar o jogo

	}

}
