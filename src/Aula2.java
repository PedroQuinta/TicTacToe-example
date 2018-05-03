
import java.util.Scanner;


/**
 *
 * @author PedroQuinta
 */
public class Aula2 {

    /*
    método com a opção instrução.
    onde imprime uns parágrafos com instruções do jogo
    e retorna para o menu anterior quando o utlizador 
    pressionar uma tecla.
     */
    public static void instrucao() {
        System.out.println("Intruções e regras do Jogo do Galo:\n" + "\n"
                + "Os dois jogadores colocam, alternadamente, as suas peças de forma a construirem uma linha com 3 peças iguais em tabuleiros 3x3.\n"
                + "A linha de peças iguais pode ser construida na vertical, na horizontal ou na oblíqua.\n"
                + "O jogador deve jogar tendo em conta as seguinte prioridades:\n"
                + "- Ganhar, completando a linha.\n"
                + "- Bloquear para impedir que o adversário complete a sua linha.\n"
                + "- Bloquear jogadas que proporcionem ao adversário mais do que uma posição de vantagem.\n"
                + "- Jogar no centro.\n"
                + "- Jogar no canto oposto ao do adversário.\n"
                + "- Jogar no canto vazio.\n"
                + "- Jogar no lado vazio.\n");

    }

    /*
    fim do método
     */

 /*
    método com o submenu() onde o utilizador irá jogar com 
    o computador em modo fácil ou dificil.
     */
    public static void submenu() {
        Jogo game = new Jogo();
        Scanner scc = new Scanner(System.in);
        String op;
        do {
            System.out.println("Jogo do Galo -> jogar contra Computador");
            System.out.println("Escolha o nível de dificuldade:");
            System.out.println("F - Fácil");
            System.out.println("D - Difícil");
            System.out.println("V - Voltar");
            System.out.print("\n");

            op = scc.nextLine();

            switch (op.toUpperCase()) {
                //modo fácil
                case "F":
                   //sortear jogador
                    int aux2 = game.randomGen(0, 2);
                    // no caso se ser sorteado o utilizador primeiro
                    //imprime um tabuleiro vazio para ser mais fácil a visualização.
                    if (aux2 == 1) {
                        game.mostrarTab();
                    }
                    while (game.jogoAtivo() && aux2 < 9) {
                        if (aux2 % 2 == 0) {
                            game.jogadaPc('O');
                        } else {
                            game.perguntaJogador('X');
                        }
                        aux2++;
                        System.out.print("\n");
                        System.out.print("\n");
                        game.mostrarTab();
                        game.verificarVencedor();

                        if (aux2 == 9 && game.jogoAtivo() == true) {
                            System.out.print("\n");
                            System.out.print("Empate!");
                            System.out.print("\n");
                            System.out.print("\n");
                        }
                    }
                    break;
                case "D":
                    int aux3 = game.randomGen(0, 2);
                   // no caso se ser sorteado o utilizador primeiro
                    //imprime um tabuleiro vazio para ser mais fácil a visualização.
                    if (aux3 == 1) {
                        game.mostrarTab();
                    }
                    while (game.jogoAtivo() && aux3 < 9) {

                        if (aux3 == 0) {
                            game.jogadaInicial('O');
                        }
                        if (aux3 % 2 == 0 && aux3 > 0) {
                            
                            switch (game.jogarParaGanhar('O', 'X')) {
                                case 1:
                                    break;
                                case -1:
                                    switch (game.jogarParaNaoPerder('O', 'X')) {
                                        case 1:
                                            break;
                                        case -1:
                                            game.jogadaPc('O');
                                            break;
                                    }
                                    break;
                            }
                        } else {
                            if (aux3 != 1) {
                                game.perguntaJogador('X');
                            } else {
                                game.perguntaJogador('X');
                            }
                        }
                        aux3++;
                        System.out.print("\n");
                        System.out.print("\n");
                        game.mostrarTab();
                        game.verificarVencedor();

                        if (aux3 == 9 && game.jogoAtivo() == true) {
                            System.out.print("\n");
                            System.out.print("Empate!");
                            System.out.print("\n");
                            System.out.print("\n");
                        }
                    }
                    break;
                case "V":
                    System.out.println("A voltar ...");
                    System.out.print("\n");
                    return;
            }
        } while (!op.toUpperCase().equals("V"));
    }

    /*
    fim do método
     */

 /*
    método com o menu principal onde o utilizador irá jogar com
    outro utilizador ou entao tem a opçao de aceder ao submenu()e
    as intruções.    
     */
    public static void menu() {
        Scanner sc = new Scanner(System.in);
        String escolha;
        do {
            System.out.println("Jogo do Galo:");
            System.out.println("I - Instruções");
            System.out.println("P - jogar contra outra Pessoa");
            System.out.println("C - jogar contra Computador");
            System.out.println("S - sair");
            System.out.println();

            escolha = sc.nextLine();

            switch (escolha.toUpperCase()) {
                case "I":
                    instrucao();
                    sc.nextLine();
                    break;
                case "P":
                    // opção 2
                    Jogo galo = new Jogo();
                    galo.mostrarTab();
                    int aux = galo.randomGen(0, 2);

                    while (galo.jogoAtivo() && aux < 9) {
                        if (aux % 2 == 0) {
                            galo.perguntaJogador('O');
                        } else {
                            galo.perguntaJogador('X');
                        }
                        aux++;
                        System.out.print("\n");
                        System.out.print("\n");
                        galo.mostrarTab();
                        galo.verificarVencedor();

                        if (aux == 9 && galo.jogoAtivo() == true) {

                            System.out.print("\n");
                            System.out.print("Empate!");
                            System.out.print("\n");
                            System.out.print("\n");

                        }
                    }
                    break;
                case "C":                    
                    submenu();
                    break;
                case "S":
                    System.out.println("A sair ...");
                    break;
                default:
                    System.out.println(escolha + " não é uma operação válida.");
            }
        } while (!escolha.toUpperCase().equals("S"));

    }

    /*
    fim do método
     */

    public static void main(String[] args) {
        menu();
        
        /*Futuras melhorias:
        1 - Algoritmo modo dificil versus Comp.
        2 - Otimização do código dos menus.
        3 - Possível ambiente gráfico com paineis, botões, etc...
        */
    }
}
