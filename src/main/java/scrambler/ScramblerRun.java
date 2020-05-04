package scrambler;

class ScramblerRun {
    static Scrambler scr;

    public static void main(String[] args) {
        int num_moves = 10;
        if (args.length > 0)
            num_moves = Integer.parseInt(args[0]);
        scr = new Scrambler(num_moves);
        scr.generateScramble();
        String scramble = scr.getScrambleString();
        System.out.println("\"" + scramble + "\"");
    }
}