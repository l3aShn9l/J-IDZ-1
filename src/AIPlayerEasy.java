class AIPlayerEasy extends AIPlayer {
    public AIPlayerEasy() {
    }
    @Override
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
        if (temp > eval) {
            eval = temp;
        }
        if (eval < 1) {
            eval = 1;
        }

        return eval;
    }

}
