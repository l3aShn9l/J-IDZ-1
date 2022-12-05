import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String menuCommand;
        while (true) {
            while (true) {
                System.out.println("1. Игра против компьютера посложнее");
                System.out.println("2. Игра против компьютера полегче");
                System.out.println("3. Игра против игрока");
                System.out.println("Лучший счет: " + Game.bestScore);
                System.out.println("Введите: ");
                menuCommand = scanner.nextLine();
                if (menuCommand.equals("1") || menuCommand.equals("2") || menuCommand.equals("3")) {
                    Game.init_game_board();
                    break;
                }
            }

            if (menuCommand.equals("1") || menuCommand.equals("2")) {
                Player player1 = new Player("Player");
                AIPlayer player2;
                if (menuCommand.equals("1")) {
                    player2 = new AIPlayer();
                } else {
                    player2 = new AIPlayerEasy();
                }

                boolean curPlayer = false;
                while (true) {
                    String playerName;
                    int cp = curPlayer == false ? 1 : 2;
                    playerName = cp == 1 ? player1._name : player2._name;
                    int vm = 0;
                    for (int i = 0; i < Game.MAXN; i++) {
                        for (int j = 0; j < Game.MAXM; j++) {
                            if (Game.game_board[i][j] == 1) {
                                System.out.print("● ");
                            } else if (Game.game_board[i][j] == 2) {
                                System.out.print("⊛ ");
                            } else if (Game.game_board[i][j] == 0 && Game.is_valid_move(i, j, cp)) {
                                System.out.print("⊗ ");
                                vm++;
                            } else {
                                System.out.print("◌ ");
                            }
                        }
                        System.out.print("\n");
                    }
                    System.out.print("\n");
                    if (vm == 0) {
                        int avm = 0;
                        for (int l = 0; l < Game.MAXN; l++) {
                            for (int k = 0; k < Game.MAXM; k++) {
                                if (Game.game_board[l][k] == 0 && Game.is_valid_move(l, k, cp)) {
                                    avm++;
                                }
                            }
                        }
                        if (avm == 0) {
                            int results = Game.testVin();
                            if (results == 1) {
                                System.out.println("Победа " + player1.getName() + "!");
                            } else if (results == 2) {
                                System.out.println("Победа " + player2.getName() + "!");
                            } else {
                                System.out.println("Ничья!");

                            }
                            break;
                        } else {
                            System.out.println("У " + playerName + " нет ходов! Переход хода!");
                            curPlayer = !curPlayer;
                        }

                    } else {
                        if (cp == 1) {

                            System.out.println(playerName + " введите координаты фишки: ");
                            int coordY = scanner.nextInt();
                            int coordX = scanner.nextInt();
                            coordX--;
                            coordY--;
                            if (Game.is_valid_move(coordX, coordY, cp)) {
                                Game.make_move(coordX, coordY, cp);
                                curPlayer = !curPlayer;
                            }
                        } else {
                            player2.play(2);
                            curPlayer = !curPlayer;
                        }
                    }
                }

            } else if (menuCommand.equals("3")) {
                Player player1 = new Player("Player 1");
                Player player2 = new Player("Player 2");
                boolean curPlayer = false;
                while (true) {
                    String playerName;
                    int cp = curPlayer == false ? 1 : 2;
                    playerName = cp == 1 ? player1.getName() : player2.getName();
                    int vm = 0;
                    for (int i = 0; i < Game.MAXN; i++) {
                        for (int j = 0; j < Game.MAXM; j++) {
                            if (Game.game_board[i][j] == 1) {
                                System.out.print("● ");
                            } else if (Game.game_board[i][j] == 2) {
                                System.out.print("⊛ ");
                            } else if (Game.game_board[i][j] == 0 && Game.is_valid_move(i, j, cp)) {
                                System.out.print("⊗ ");
                                vm++;
                            } else {
                                System.out.print("◌ ");
                            }
                        }
                        System.out.print("\n");
                    }
                    System.out.print("\n");
                    if (vm == 0) {
                        int avm = 0;
                        for (int l = 0; l < Game.MAXN; l++) {
                            for (int k = 0; k < Game.MAXM; k++) {
                                if (Game.game_board[l][k] == 0 && Game.is_valid_move(l, k, cp)) {
                                    avm++;
                                }
                            }
                        }
                        if (avm == 0) {
                            int results = Game.testVin();
                            if (results == 1) {
                                System.out.println("Победа " + player1.getName() + "!");
                            } else if (results == 2) {
                                System.out.println("Победа " + player2.getName() + "!");
                            } else {
                                System.out.println("Ничья!");

                            }
                            break;
                        } else {
                            System.out.println("У " + playerName + " нет ходов! Переход хода!");
                            curPlayer = !curPlayer;
                        }

                    } else {
                        System.out.println(playerName + " введите координаты фишки: ");
                        int coordY = scanner.nextInt();
                        int coordX = scanner.nextInt();
                        coordX--;
                        coordY--;
                        if (Game.is_valid_move(coordX, coordY, cp)) {
                            Game.make_move(coordX, coordY, cp);
                            curPlayer = !curPlayer;
                        }
                    }
                }
            }
        }
    }
}