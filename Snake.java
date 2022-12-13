import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Snake { // Classe que representa a cobra

	private static final int DEFAULT_SNAKE_LENGTH = 5; // Tamanho inicial da cobra
	private static final int DEFAULT_SNAKE_DIRECTION = 3; // Direção inicial da cobra

	private ArrayList<Ellipse2D.Double> snakeBody = new ArrayList<Ellipse2D.Double>(); // Lista que armazena as partes da cobra

	private int direction; // Direção da cobra
 
	public Snake() { // Construtor da classe

		direction = DEFAULT_SNAKE_DIRECTION; // Direção inicial da cobra

		for (int i = 0; i < DEFAULT_SNAKE_LENGTH; i++) { // Cria as partes da cobra
			snakeBody.add(new Ellipse2D.Double(355 - i * 16, 191, 16, 16)); // Adiciona as partes da cobra na lista
		}
	}

	public void setDirection(int dir) { // Método que define a direção da cobra
		if (direction >= 3 && dir < 3) { // Se a direção atual for 3 ou 4 e a nova direção for 1 ou 2
			this.direction = dir; // Define a nova direção
		} else if (direction <= 2 && dir > 2) { // Se a direção atual for 1 ou 2 e a nova direção for 3 ou 4
			this.direction = dir; // Define a nova direção
		}
	}

	public void move() { // Método que move a cobra
		for (int i = getLength() - 1; i > 0; i--) { // Para cada parte da cobra
			snakeBody.set(i, (Ellipse2D.Double) snakeBody.get(i - 1)); // Define a posição da parte atual como a posição da parte anterior
		}

		if (direction == 1) { // Se a direção for 1
			decreaseY(); // Move a cobra para cima
		} else if (direction == 2) { // Se a direção for 2
			increaseY(); // Move a cobra para baixo
		} else if (direction == 3) { // Se a direção for 3
			increaseX(); // Move a cobra para a direita
		} else if (direction == 4) { // Se a direção for 4
			decreaseX(); // Move a cobra para a esquerda
		}
	}

	public void eat() { // Método que aumenta o tamanho da cobra

		snakeBody.add(snakeBody.get(getLength() - 1)); // Adiciona uma nova parte na cobra
	}

	public void increaseY() { // Método que move a cobra para baixo
		Ellipse2D.Double temp = (Ellipse2D.Double) snakeBody.get(0); // Pega a posição da cabeça da cobra
		Ellipse2D.Double elli = new Ellipse2D.Double(temp.x, temp.y + 16, 
				temp.getWidth(), temp.getHeight()); // Cria uma nova parte da cobra na posição abaixo da cabeça

		snakeBody.set(0, (Ellipse2D.Double) elli); // Define a nova posição da cabeça da cobra

	}

	public void decreaseY() { // Método que move a cobra para cima
		Ellipse2D.Double temp = (Ellipse2D.Double) snakeBody.get(0); // Pega a posição da cabeça da cobra
		Ellipse2D.Double elli = new Ellipse2D.Double(temp.x, temp.y - 16, 
				temp.getWidth(), temp.getHeight()); // Cria uma nova parte da cobra na posição acima da cabeça

		snakeBody.set(0, (Ellipse2D.Double) elli); // Define a nova posição da cabeça da cobra
	}

	public void increaseX() { // Método que move a cobra para a direita
		Ellipse2D.Double temp = (Ellipse2D.Double) snakeBody.get(0); // Pega a posição da cabeça da cobra
		Ellipse2D.Double elli = new Ellipse2D.Double(temp.x + 16, temp.y,
				temp.getWidth(), temp.getHeight()); // Cria uma nova parte da cobra na posição à direita da cabeça

		snakeBody.set(0, (Ellipse2D.Double) elli); // Define a nova posição da cabeça da cobra
	}

	public void decreaseX() { // Método que move a cobra para a esquerda
		Ellipse2D.Double temp = (Ellipse2D.Double) snakeBody.get(0); // Pega a posição da cabeça da cobra
		Ellipse2D.Double elli = new Ellipse2D.Double(temp.x - 16, temp.y,
				temp.getWidth(), temp.getHeight()); // Cria uma nova parte da cobra na posição à esquerda da cabeça

		snakeBody.set(0, (Ellipse2D.Double) elli); // Define a nova posição da cabeça da cobra
	}

	public ArrayList<Ellipse2D.Double> getSnakeBody() { // Método que retorna a lista de partes da cobra
		return snakeBody;
	}

	public int getLength() { // Método que retorna o tamanho da cobra
		return snakeBody.size();
	}

	public int getDirection() { // Método que retorna a direção da cobra

		return direction;
	}

	public Ellipse2D.Double getHead() { // Método que retorna a cabeça da cobra

		return snakeBody.get(0);

	}

}
