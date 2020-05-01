package rubik;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import scrambler.*;

/**
 *
 * @author Abduraghmaan G
 */
public class Driver {

    /**
     * @param args the command line arguments
     */
    static Rubik cube = new Rubik();
    static Rubik solved = new Rubik();
    static boolean verbose = false;
    static boolean solution = false;
    static int moves = 0;
    static HashMap<Character, Runnable> instruct;
    static Scrambler scrambler = new Scrambler();
    static List<String> instructions = new ArrayList<String>();

    public static void invoke(List<String> instructions) {

        summarise(instructions).forEach((instr) -> {
            execute(instr);
        });
        System.out.println(summarise(instructions));
    }

    public static void execute(String instr) {
        try {
            if (solution && cube.equals(solved)) {
                System.out.println(Coloreths.Green.color + "solved" + Coloreths.Reset.color);
                System.exit(0);
            }
            if (verbose)
                System.out.println("Instruction : " + instr);
            instructions.add(instr);
            if (instr.length() == 2) {
                // clockwise = (!(instr.charAt(1) == '\''));
                cube.extra = (instr.contains("2"));
                if (!cube.extra) {
                    cube.clockwise = (!(instr.contains("\'")));
                }
                // extra = (instr.charAt(1)=='2');
                instr = instr.replace("2", "");
                instr = instr.replace("\'", "");
            }

            if (instr.length() != 1) {
                throw new Exception("incorrect instruction format : " + instr);
            }
            instruct.get(instr.charAt(0)).run();
            cube.clockwise = true;
            cube.extra = false;
            moves++;
            // if (verbose) {
            // System.err.println(cube.toString() + "Number of Moves : " + moves);
            // }
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    public static boolean binaryquestion(String s) {
        String input;
        Scanner scInput;
        boolean output = false;
        scInput = new Scanner(System.in);
        try {
            System.out.println(s + "? Y/N");
            input = scInput.nextLine();
            if (input.equalsIgnoreCase("Y")) {
                output = true;
            } else if (!input.equalsIgnoreCase("N")) {
                scInput.close();
                throw new Exception("Invalid Input: 'Y' or 'N' expected");
            }
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
        return output;
    }

    public static void debug() {
        try {
            Scanner scInput = new Scanner(System.in);
            while (scInput.hasNextLine()) {
                execute(scInput.nextLine());
                System.out.println(cube);
            }
            scInput.close();
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        try {
            // Solver solver = new Solver();
            instruct = new HashMap<>();
            instruct.put('F', () -> cube.F());
            instruct.put('D', () -> cube.D());
            instruct.put('R', () -> cube.R());
            instruct.put('L', () -> cube.L());
            instruct.put('B', () -> cube.B());
            instruct.put('U', () -> cube.U());

            System.out.println(cube);
            List<String> scramble = new ArrayList<>();
            // if (!(args.length == 1)) {
            // debug();
            // }
            // throw new Exception("Argument required");
            String inputScramble = "";
            int num_scramble = 0;
            if (args.length > 0) {
                // inputScramble = args[0];
                // System.out.println("args : "+args[0]);
                try {
                    num_scramble = Integer.parseInt(args[0]);
                    // System.out.println("num scr: "+num_scramble);
                } catch (NumberFormatException nfe) {
                    inputScramble = args[0];
                }
            } else {
                num_scramble = 10;
            }
            // System.out.println("input "+inputScramble);
            if (num_scramble > 0) {

                // scrambler = new Scrambler(num_scramble);
                scrambler.setNumMoves(num_scramble);
                scrambler.generateScramble();
                inputScramble = scrambler.getScrambleString();
                // System.out.println("random scramble :"+inputScramble);
            }

            Collections.addAll(scramble, inputScramble.toUpperCase().split(" "));
            // verbose = binaryquestion("turn verbose mode on");
            invoke(scramble);
            System.out.println("Post scramble:\n" + cube);
            if (binaryquestion("Do you wish to see solution")) {
                // verbose = binaryquestion("With vebose mode turned on");
                // Solver.simplesolve(scramble);
                // Solver solver = new Solver();
                new Solver().simplesolve(scramble);
            }
            System.out.println("Final solution:\n" + cube);
            System.out.println("Number of moves in solution : " + moves);
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    static List<String> extend(List<String> instructions) {
        List<String> extension = new ArrayList<String>();
        for (String instr : instructions) {
            if (instr.length() == 2) {
                switch (instr.charAt(1)) {
                    case '2':
                        extension.add(instr.charAt(0) + "");
                        extension.add(instr.charAt(0) + "");
                        break;
                    case '\'':
                        extension.add(instr.charAt(0) + "");
                        extension.add(instr.charAt(0) + "");
                        extension.add(instr.charAt(0) + "");
                        break;
                }
            } else {
                extension.add(instr.charAt(0) + "");
            }
        }
        return extension;
    }

    static String compress(String instruction, int repeat_counter) {
        String result;
        switch (repeat_counter % 4) {
            case 2:
                result = instruction.charAt(0) + "2";
                break;
            case 3:
                result = instruction.charAt(0) + "'";
                break;
            case 1:
                result = instruction.charAt(0) + "";
                break;
            default:
                result = null;
                break;
        }
        return result;
    }

    static List<String> summarise(List<String> instructions) {
        List<String> summary = new ArrayList<String>();
        int repeat_counter = 1;
        List<String> extended = extend(instructions);
        String instr = extended.get(0);
        String previous = instr;
        for (int j = 1; j < extended.size(); j++) {
            instr = extended.get(j);
            if (!instr.equals(previous)) {
                if (compress(previous, repeat_counter) != null)
                    summary.add(compress(previous, repeat_counter));
                repeat_counter = 1;
            } else {
                repeat_counter++;
            }
            previous = instr;
        }
        if (compress(previous, repeat_counter) != null)
            summary.add(compress(previous, repeat_counter));
        return summary;
    }
}