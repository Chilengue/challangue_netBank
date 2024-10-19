
import java.util.Random;
import java.util.Scanner;

public class CampoMinado {
    private static final int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    private char[][] tabuleiro;
    private boolean[][] minas;
    private int linhas, colunas, numMinas;

  
    public CampoMinado(int linhas, int colunas, int numMinas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.numMinas = numMinas;
        tabuleiro = new char[linhas][colunas];
        minas = new boolean[linhas][colunas];

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                tabuleiro[i][j] = '.';
            }
        }

        // Colocar as minas
        colocarMinas();
        // Calcular números adjacentes
        calcularNumeros();
    }

    // Colocar minas aleatoriamente no tabuleiro
    private void colocarMinas() {
        Random random = new Random();
        int count = 0;

        while (count < numMinas) {
            int linha = random.nextInt(linhas);
            int coluna = random.nextInt(colunas);

            if (!minas[linha][coluna]) {
                minas[linha][coluna] = true;
                count++;
            }
        }
    }

    // Calcular números de minas adjacentes para células não-minadas
    private void calcularNumeros() {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (!minas[i][j]) {
                    int count = 0;
                    // Verificar as 8 células ao redor
                    for (int k = 0; k < 8; k++) {
                        int ni = i + dx[k];
                        int nj = j + dy[k];
                        if (ni >= 0 && ni < linhas && nj >= 0 && nj < colunas && minas[ni][nj]) {
                            count++;
                        }
                    }
                    tabuleiro[i][j] = (char) (count + '0'); // Convertendo número para char
                } else {
                    tabuleiro[i][j] = '*'; // Marcar células com minas
                }
            }
        }
    }

    // Exibir o tabuleiro
    public void exibirTabuleiro() {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Interação com o usuário (opcional)
    public void revelarCelula(int linha, int coluna) {
        if (minas[linha][coluna]) {
            System.out.println("Game Over! Você revelou uma mina!");
            exibirTabuleiro(); // Mostrar todas as minas
        } else {
            System.out.println("Célula revelada: " + tabuleiro[linha][coluna]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar tamanho do tabuleiro e número de minas
        System.out.println("Digite o tamanho do tabuleiro (linhas e colunas): ");
        int linhas = scanner.nextInt();
        int colunas = scanner.nextInt();
        
        System.out.println("Digite o número de minas: ");
        int numMinas = scanner.nextInt();

        CampoMinado jogo = new CampoMinado(linhas, colunas, numMinas);

        // Exibir o tabuleiro (opcional: comentar para exibir apenas após revelações)
        jogo.exibirTabuleiro();

        // Interação com o usuário
        while (true) {
            System.out.println("Digite a célula que deseja revelar (linha e coluna): ");
            int linha = scanner.nextInt();
            int coluna = scanner.nextInt();

            if (linha < 0 || linha >= linhas || coluna < 0 || coluna >= colunas) {
                System.out.println("Posição inválida. Tente novamente.");
                continue;
            }

            jogo.revelarCelula(linha, coluna);

            if (jogo.minas[linha][coluna]) {
                break; // Se revelou uma mina, termina o jogo
            }
        }

        scanner.close();
    }
}