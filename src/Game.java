public class Game {
    static int bestScore = 0;
    static final int cornerValue = 6;
    static final int MAXN = 8;
    static final int MAXM = 8;

    static int[][] game_board = new int[MAXN][MAXM];
    static int moves = 0;


    static void init_game_board() {
        moves = 0;
        for (int i = 0; i < MAXN; i++)
            for (int j = 0; j < MAXM; j++)
                game_board[i][j] = 0;

        game_board[MAXN / 2][MAXN / 2 - 1] = 2;
        game_board[MAXN / 2 - 1][MAXN / 2] = 2;
        game_board[MAXN / 2 - 1][MAXN / 2 - 1] = 1;
        game_board[MAXN / 2][MAXN / 2] = 1;

    }

    static int get_slot_status(int x, int y) {
        return (x >= MAXN || y >= MAXM || x < 0 || y < 0) ? 0 : game_board[x][y];
    }

    static int flip_count(int x, int y, int player) {
        if (x >= MAXN || y >= MAXM || x < 0 || y < 0)
            return 0;

        int dir_x, dir_y, dist = 1, cur_x = x, cur_y = y, flip_count = 0;

        for (dir_x = -1; dir_x < 2; dir_x++) {
            for (dir_y = -1; dir_y < 2; dir_y++) {
                dist = 1;
                while (true) {
                    cur_x = x + (dist * dir_x);
                    cur_y = y + (dist * dir_y);

                    if (cur_x < 0 || cur_y < 0 || cur_x >= MAXN || cur_y >= MAXM)
                        break;

                    if (game_board[cur_x][cur_y] == 0)
                        break;
                    if (game_board[cur_x][cur_y] == player) {
                        flip_count += dist - 1;
                        break;
                    }
                    if (dir_x == 0 && dir_y == 0)
                        break;
                    dist++;
                }
            }
        }
        return flip_count;
    }

    static boolean is_valid_move(int x, int y, int player) {
        if (x >= MAXN || y >= MAXM || x < 0 || y < 0)
            return false;

        if (get_slot_status(x, y) != 0)
            return false;

        return (flip_count(x, y, player) > 0) ? true : false;
    }

    static void set_slot(int x, int y, int player) {
        if (x >= MAXN || y >= MAXM || x < 0 || y < 0)
            return;
        game_board[x][y] = player;
    }


    static int flip_slots(int x, int y, int player) {
        if (x >= MAXN || y >= MAXM || x < 0 || y < 0)
            return 0;

        int dir_x, dir_y, dist = 1, cur_x = x, cur_y = y, flip_count = 0;
        for (dir_x = -1; dir_x < 2; dir_x++) {
            for (dir_y = -1; dir_y < 2; dir_y++) {
                dist = 1;
                while (true) {
                    cur_x = x + (dist * dir_x);
                    cur_y = y + (dist * dir_y);
                    if (cur_x < 0 || cur_y < 0 || cur_x >= 8 || cur_y >= 8) {
                        break;
                    }
                    if (game_board[cur_x][cur_y] == 0) {
                        break;
                    }
                    if (game_board[cur_x][cur_y] == player) {
                        for (dist--; dist > 0; dist--) {
                            cur_x = x + (dist * dir_x);
                            cur_y = y + (dist * dir_y);
                            set_slot(cur_x, cur_y, player); // flip slot
                        }
                        break;
                    }
                    if (dir_x == 0 && dir_y == 0) {
                        break;
                    }
                    dist++;
                }
            }
        }
        return flip_count;
    }

    static boolean make_move(int x, int y, int player) {
        if (x >= MAXN || y >= MAXM || x < 0 || y < 0)
            return false;
        if (is_valid_move(x, y, player)) {
            set_slot(x, y, player);
            flip_slots(x, y, player);
            return true;
        } else
            return false;
    }

    /*boolean moves_exist(int player) {
        int x, y;
        for (x = 0; x < MAXN; x++) {
            for (y = 0; y < MAXM; y++) {
                if (get_slot_status(x, y) != 0) {
                    continue;
                } else if (flip_count(x, y, player) > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    int count_free_slots() {
        int x, y, count = 0;
        for (x = 0; x < MAXN; x++) {
            for (y = 0; y < MAXM; y++) {
                count += ((get_slot_status(x, y) == 0) ? 1 : 0);
            }
        }
        return count;
    }*/

    static int count_pts(int player) {
        int x, y, count = 0;
        for (x = 0; x < MAXN; x++) {
            for (y = 0; y < MAXM; y++) {
                count += ((get_slot_status(x, y) == player) ? 1 : 0);
            }
        }
        return count;
    }

    static int testVin() {
        if (count_pts(1) < count_pts(2)) {
            if (count_pts(2) > bestScore) {
                bestScore = count_pts(2);
            }
            return 2;
        } else if (count_pts(1) > count_pts(2)) {
            if (count_pts(1) > bestScore) {
                bestScore = count_pts(1);
            }
            return 1;
        } else {
            if (count_pts(1) > bestScore) {
                bestScore = count_pts(1);
            }
            return 3;
        }
    }
}
