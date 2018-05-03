
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author PedroQuinta
 */
public class Jogo {

    private char tabuleiro[][];
    private boolean naoAcabou = true;

    /*
    método constutor onde os objetos da classe jogo 
    têm um tabuleiro[3][3] associado, preenchido com espaços.
     */
    public Jogo() {
        tabuleiro = new char[3][3];
        for (int i = 0; i < tabuleiro.length; i++) {
            Arrays.fill(tabuleiro[i], ' ');
        }

    }

    /*
    fim do método
     */

 /*
    método que irá mostrar e simular o tabuleiro.
     */
    public void mostrarTab() {
        for (int linhas = 0; linhas < tabuleiro.length; linhas++) {
            for (int colunas = 0; colunas < tabuleiro[0].length; colunas++) {
                System.out.print("\t" + tabuleiro[linhas][colunas]);
                if (colunas == 0 || colunas == 1) {
                    System.out.print("|");
                }
            }
            if (linhas == 0 || linhas == 1) {
                System.out.println("\n----------------------------\n");
            }
        }
    }

    /*
    fim do método
     */

 /*
    método que irá gerar um número aleatório no intervalo de [x, y-1];
     */
    public int randomGen(int x, int y) {
        int rand;

        rand = ThreadLocalRandom.current().nextInt(x, y);
        return rand;
    }

    /*
    fim do método
     */

 /*
    método que irá verificar se a escolha de linha e coluna já está preenchida é aceitável e se sim preenche 
    com o simbolo do jogador
     */
    public void jogada(char jogador, int linha, int coluna) {
        if ((linha >= 0 && linha <= 2) && coluna >= 0 && coluna <= 2) {
            tabuleiro[linha][coluna] = jogador;
        }
    }

    /*
    fim do método
     */
 /*
    método que irá por o computador a fazer jogadas aleatorias e verificar se estas são válidas.
    se sim preenche e imprime as coordenadas escolhidas pelo computador.
     */
    public void jogadaPc(char jogador) {
        int linha, coluna;
        int c = 0;

        while (c != -1) {
            //linha = ThreadLocalRandom.current().nextInt(0, 3);
            linha = randomGen(0, 3);
            coluna = randomGen(0, 3);
            if (tabuleiro[linha][coluna] == ' ') {
                System.out.print("\n");
                System.out.println("pc escolheu:" + "(" + linha + "," + coluna + ")");
                jogada(jogador, linha, coluna);
                c = -1;
                break;
            }
        }

    }

    /*
    fim do método
     */

 /*
    método que irá verificar se ainda há jogo para jogar
    e irá retornar o valor da variavel naoAcabou
    que foi iniciada com true;
     */
    public boolean jogoAtivo() {
        return naoAcabou;
    }

    /*
    fim do método
     */
 /*
    este método irá perguntar ao utilizador para escolher uma
    linha e uma coluna, validando-as e chama o método jogada()
     */
    public void perguntaJogador(char jogador) {
        Scanner sc = new Scanner(System.in);
        int l, c;
        do {
            System.out.println();
            System.out.printf("Jogador %s Escola uma linha (1-3):", jogador);
            l = sc.nextInt();

            System.out.printf("Jogador %s Escola uma coluna (1-3):", jogador);
            c = sc.nextInt();

        } while (naoValido(l, c));

        jogada(jogador, l - 1, c - 1);

    }

    /*
    fim do método
     */

 /*
     este método irá verificar se as coordenadas linha e coluna estão entre 1 e 3
    , ou seja, serve para limitar a escolha do utilizador
    e se essas mesmas coordenadas não foram já escolhidas.
     */
    public boolean naoValido(int linha, int coluna) {
        if ((linha > 3 || linha < 1) || (coluna > 3 || coluna < 1) || !estaVazio(linha, coluna)) {
            return true;
        } else {
            return false;
        }

    }

    /*
    fim do método
     */
 /*
     este método irá verificar se no tabuleiro as coordenadas escolhidas pelo utilizador, estão
    ou não preenchidas no tabuleiro, ou seja, se o espaço do tabuleiro em questão está ou nao vazio.
    se não imprime mensagem.
     */
    public boolean estaVazio(int linha, int coluna) {
        if (tabuleiro[linha - 1][coluna - 1] == ' ') {
            return true;
        } else {
            System.out.print("\n");
            System.out.print("Essa posição já foi escolhida.\n");
            return false;
        }
    }

    /*
    fim do método
     */
 /*
    este método irá verificar se há 3 "x" ou 3 "o" numa linha
    numa coluna e nas diagonais
     */
    public boolean verificarVencedor() {
        //ciclo verificação linhas
        //verifica se há tres iguais em linhas e todos diferentes de espaço
        // se verdadeiro poe a variavel naoAcabou em false, o que faz com que o método jogoAtivo retorne false;
        for (int linhas = 0; linhas < tabuleiro.length; linhas++) {
            if (tabuleiro[linhas][0] == tabuleiro[linhas][1] && tabuleiro[linhas][1] == tabuleiro[linhas][2]
                    && tabuleiro[linhas][0] != ' ') {
                System.out.println();
                System.out.print("O vencedor é o Jogador" + " " + tabuleiro[linhas][0]);
                System.out.print("\n");
                System.out.print("\n");
                naoAcabou = false;
            }
        }
        //ciclo verificação colunas
        //verifica se há tres iguais em colunas e todos diferentes de espaço
        // se verdadeiro poe a variavel naoAcabou em false, o que faz com que o método jogoAtivo retorne false;
        for (int colunas = 0; colunas < tabuleiro[0].length; colunas++) {
            if (tabuleiro[0][colunas] == tabuleiro[1][colunas] && tabuleiro[1][colunas] == tabuleiro[2][colunas]
                    && tabuleiro[0][colunas] != ' ') {
                System.out.println();
                System.out.print("O vencedor é Jogador" + " " + tabuleiro[0][colunas]);
                System.out.print("\n");
                System.out.print("\n");
                naoAcabou = false;
            }
        }
        // verificação diagonais
        //verifica se há tres iguais em diagonais e todos diferentes de espaço
        // se verdadeiro poe a variavel naoAcabou em false, o que faz com que o método jogoAtivo retorne false;
        if (tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2] && tabuleiro[0][0] != ' ') {
            System.out.println();
            System.out.print("O vencedor é Jogador" + " " + tabuleiro[0][0]);
            System.out.print("\n");
            System.out.print("\n");
            naoAcabou = false;
        }
        if (tabuleiro[2][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[0][2] && tabuleiro[2][0] != ' ') {
            System.out.println();
            System.out.print("O vencedor é Jogador" + " " + tabuleiro[2][0]);
            System.out.print("\n");
            System.out.print("\n");
            naoAcabou = false;
        }

        return false;
    }

    /*
    fim do método
     */

 /*
    este método irá fazer a jogada inicial do computador no quadrado central(quando modo difícil).
    foi escolhido o quadrado central pois é o que faz mais sentido, na perspectiva de inteligência artificial
    tendo o maior numero de direcoes possíveis de ir a seguir.
     */
    public void jogadaInicial(char jogador) {
        int l = 1;
        int c = 1;
        jogada(jogador, l, c);
    }

    /*
    fim do método
     */
 /*
    este método irá verificar em linhas, colunas e diagonais se existem 2 símbolos iguais
    caso existam, para ganhar, o espaço restante da respetiva linha, coluna ou diagonal
    é preenchido. 
     */
    public int jogarParaGanhar(char j1, char j2) {

        int ocupado_j1;
        int ocupado_j2;

        //linhas
        // conta casas ocupadas
        for (int i = 0; i < 3; i++) {

            ocupado_j1 = 0;
            ocupado_j2 = 0;

            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == j1) {
                    ocupado_j1++;
                } else if (tabuleiro[i][j] == j2) {
                    ocupado_j2++;
                }
            }
            // verifica se j1 tem oportunidade de ganhar, mas 
            // que a LINHA não está preenchida totalmente
            if (ocupado_j1 == 2 && (ocupado_j1 + ocupado_j2 != 3)) {
                // encontra posicao vazia
                // joga nela
                // faz return
                for (int k = 0; k < 3; k++) {
                    if (tabuleiro[i][k] == ' ') {
                        tabuleiro[i][k] = j1;
                        return 1;
                    }
                }

            }

        }

        //colunas
        for (int j = 0; j < 3; j++) {

            ocupado_j1 = 0;
            ocupado_j2 = 0;

            for (int i = 0; i < 3; i++) {
                if (tabuleiro[i][j] == j1) {
                    ocupado_j1++;
                } else if (tabuleiro[i][j] == j2) {
                    ocupado_j2++;
                }
            }

            // verifica se j1 tem oportunidade de ganhar, mas 
            // que a COLUNA nao está preenchida
            if (ocupado_j1 == 2 && (ocupado_j1 + ocupado_j2 != 3)) {
                // encontra posicao vazia
                // joga nela
                // faz return
                for (int k = 0; k < 3; k++) {
                    if (tabuleiro[k][j] == ' ') {
                        tabuleiro[k][j] = j1;
                        return 1;
                    }
                }
            }

        }
        // diagonal i=i
        ocupado_j1 = 0;
        ocupado_j2 = 0;
        for (int i = 0; i < tabuleiro.length; i++) {

            if (tabuleiro[i][i] == j1) {
                ocupado_j1++;
            } else if (tabuleiro[i][i] == j2) {
                ocupado_j2++;
            }

            // verifica se j1 tem oportunidade de ganhar, mas 
            // que a DIAGONAL  nao está preenchida
            if (ocupado_j1 == 2 && (ocupado_j1 + ocupado_j2 != 3)) {
                // encontra posicao vazia
                // joga nela
                // faz return
                if (tabuleiro[i][i] == ' ') {
                    tabuleiro[i][i] = j1;
                    return 1;
                }

            }

        }

        // diagonal inversa
        ocupado_j1 = 0;
        ocupado_j2 = 0;
        for (int i = 0; i < tabuleiro.length; i++) {

            if (tabuleiro[i][2 - i] == j1) {
                ocupado_j1++;
            } else if (tabuleiro[i][2 - i] == j2) {
                ocupado_j2++;
            }

            // verifica se j1 tem oportunidade de ganhar, mas 
            // que a DIAGONAL  nao está preenchida
            if (ocupado_j1 == 2 && (ocupado_j1 + ocupado_j2 != 3)) {
                // encontra posicao vazia
                // joga nela
                // return
                for (int h = 0; h < 3; h++) {
                    if (tabuleiro[h][2 - h] == ' ') {
                        tabuleiro[h][2 - h] = j1;
                        return 1;
                    }
                }
            }

        }
        return -1;
    }

    /*
    fim do método
     */
 /*
    este método irá verificar em linhas, colunas e diagonais se existem 2 símbolos iguais
    do oponente e caso existam, para não perder, o espaço restante da respetiva linha, coluna ou diagonal
    é preenchido.
     */
    public int jogarParaNaoPerder(char j1, char j2) {
        int ocupado_j1;
        int ocupado_j2;

        //linhas
        // conta casas ocupadas
        for (int i = 0; i < 3; i++) {

            ocupado_j1 = 0;
            ocupado_j2 = 0;

            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == j1) {
                    ocupado_j1++;
                } else if (tabuleiro[i][j] == j2) {
                    ocupado_j2++;
                }
            }

            // verifica se j1 deve bloquear, mas 
            // que a LINHA não está preenchida totalmente
            if (ocupado_j2 == 2 && (ocupado_j1 + ocupado_j2 != 3)) {
                // encontra posicao vazia
                // joga nela
                // faz return
                for (int k = 0; k < 3; k++) {
                    if (tabuleiro[i][k] == ' ') {
                        tabuleiro[i][k] = j1;
                        return 1;
                    }
                }

            }

        }

        //colunas
        for (int j = 0; j < 3; j++) {

            ocupado_j1 = 0;
            ocupado_j2 = 0;

            for (int i = 0; i < 3; i++) {
                if (tabuleiro[i][j] == j1) {
                    ocupado_j1++;
                } else if (tabuleiro[i][j] == j2) {
                    ocupado_j2++;
                }
            }

            // verifica se j1 deve bloquear, mas 
            // que a COLUNA nao está preenchida
            if (ocupado_j2 == 2 && (ocupado_j1 + ocupado_j2 != 3)) {
                // encontra posicao vazia
                // joga nela
                // return
                for (int k = 0; k < 3; k++) {
                    if (tabuleiro[k][j] == ' ') {
                        tabuleiro[k][j] = j1;
                        return 1;
                    }
                }
            }

        }
        // diagonal i=i
        ocupado_j1 = 0;
        ocupado_j2 = 0;
        for (int i = 0; i < tabuleiro.length; i++) {

            if (tabuleiro[i][i] == j1) {
                ocupado_j1++;
            } else if (tabuleiro[i][i] == j2) {
                ocupado_j2++;
            }

            // verifica se j1 deve bloquear, mas 
            // que a DIAGONAL  nao está preenchida
            if (ocupado_j2 == 2 && (ocupado_j1 + ocupado_j2 != 3)) {
                // encontra posicao vazia
                // joga nela
                // return
                if (tabuleiro[i][i] == ' ') {
                    tabuleiro[i][i] = j1;
                    return 1;
                }

            }

        }

        // diagonal inversa
        ocupado_j1 = 0;
        ocupado_j2 = 0;
        for (int i = 0; i < tabuleiro.length; i++) {

            if (tabuleiro[i][2 - i] == j1) {
                ocupado_j1++;
            } else if (tabuleiro[i][2 - i] == j2) {
                ocupado_j2++;
            }

            // verifica se j1 deve bloquear, mas 
            // que a DIAGONAL  nao está preenchida
            if (ocupado_j2 == 2 && (ocupado_j1 + ocupado_j2 != 3)) {
                // encontra posicao vazia
                // joga nela
                // return
                for (int h = 0; h < 3; h++) {
                    if (tabuleiro[h][2 - h] == ' ') {
                        tabuleiro[h][2 - h] = j1;
                        return 1;
                    }
                }
            }

        }
        return -1;
    }

    /*
    fim do método
     */
}
