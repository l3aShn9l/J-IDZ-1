
class AIPlayer extends Player {
    public AIPlayer() {
        super("Computer");
    }

    public int rate_move(int x, int y, int player) {
        int eval = 0;
        if (x >= Game.MAXN || y >= Game.MAXM || x < 0 || y < 0) {
            return 0;
        }
        if (Game.get_slot_status(x, y) != 0) {
            return 0;
        }
        if (Game.is_valid_move(x, y, player) == false) {
            return 0;
        }

        int temp = Game.flip_count(x, y, player);

        if ((x == 0) || (x == (8 - 1))) {
            temp += Game.cornerValue;
        }
        if ((y == 0) || (y == (8 - 1))) {
            temp += Game.cornerValue;
        }
        if ((x == 1) || (x == (8 - 2))) {
            temp -= Game.cornerValue;
        }
        if ((y == 1) || (y == (8 - 2))) {
            temp -= Game.cornerValue;
        }
        if (x == 1 && (y == 1 || y == 6))
            temp -= Game.cornerValue;

        if (y == 1 && (x == 1 || x == 6))
            temp -= Game.cornerValue;

        if (x == 0 && (y == 1 || y == 6))
            temp -= 5;
        if (y == 0 && (x == 1 || x == 6))
            temp -= 5;
        if (y == 0 && (x == 0 || x == 7))
            temp += Game.cornerValue;

        if (x == 0 && (y == 0 || y == 7))
            temp += Game.cornerValue;

        if (temp > eval) {
            eval = temp;
        }

        if (eval < 1) {
            eval = 1;
        }

        return eval;
    }
    void play(int player) {
        int x = 0, y = 0, cur_max = 0, best_x = 0, best_y = 0, temp = 0;
        double rand_n = 0;

        for (x = 0; x < Game.MAXN; x++) {
            for (y = 0; y < Game.MAXM; y++) {
                temp = rate_move(x, y, player);
                if (temp > cur_max) {
                    cur_max = temp;
                    best_x = x;
                    best_y = y;
                } else if (temp == cur_max) {
                    rand_n = Math.random();
                    if (rand_n >= 0.5) {
                        best_x = x;
                        best_y = y;
                    }
                }
            }
        }
        Game.make_move(best_x, best_y, player);
    }
}