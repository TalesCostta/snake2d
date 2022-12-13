import java.awt.geom.Ellipse2D; // Importa a classe Ellipse2D
import java.util.Random; // Importa a classe Random

public class SnakeFood { // Classe que representa a comida da cobra

	private Ellipse2D.Double food; // Objeto que representa a comida

	/** Creates a new instance of SnakeFood */
	public SnakeFood() { // Construtor da classe

		generateFood(); // Gera a comida
	}

	public void generateFood() { // Método que gera a comida

		Random random = new Random(); // Cria um objeto da classe Random
		int x, y; // Variáveis que armazenam a posição da comida
		do {
			x = (int) (random.nextInt(39)); // Gera uma posição aleatória para a comida
			y = (int) (random.nextInt(30)); // Gera uma posição aleatória para a comida
		} while (x == 0 || y == 0 || x == 38 || y == 29); // Enquanto a posição gerada for igual a 0 ou 38 ou 29

		x = x * 16 + 227; // Define a posição da comida
		y = y * 16 + 127; // Define a posição da comida

		food = new Ellipse2D.Double(x, y, 16, 16); // Cria a comida
	}

	public Ellipse2D.Double getFood() { // Método que retorna a comida
		return food; // Retorna a comida
	}
}
