package rubik;

import java.util.ArrayList;
import java.util.Arrays;
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
        if (scrambled)
            System.out.println("Scrambled : " + summarise(instructions));
    }

    static final ArrayList<String> valid = new ArrayList<String>(Arrays.asList("F", "B", "L", "R", "U", "D", "F'", "B'",
            "L'", "R'", "U'", "D'", "F2", "B2", "L2", "R2", "U2", "D2"));

    static public boolean testInstruction(String instr) {
        // for (String s : valid) {
        // if (s.equalsIgnoreCase(instr))
        // return true;
        // }
        if (valid.contains(instr))
            return true;
        else
            return false;
    }

    public static void execute(String instr) {
        try {
            if (solution && cube.equals(solved)) {
                System.out.println(Coloreths.Green.color + "solved" + Coloreths.Reset.color);
                System.exit(0);
            }
            if (!testInstruction(instr)) {
                if (instr.contains("-")) {
                    // -i -l -nosolution -o -v -s -parts
                    throw new Exception("Incorrect flag!\n\tgiven: " + instr + "\nExpected :\n"
                            + "   -i             Manual input mode\n"
                            + "   -l             Output each result on a new line\n"
                            + "   -n             Do not display the solution\n"
                            + "   -o             No visual representation of cube\n"
                            + "   -v             Display visual state of cube after each instruction\n"
                            + "   -s             AutoScramble on\n"
                            + "   -p             Display Solution in different stages\n");
                } else
                    throw new Exception("Incorrect instruction!\n\tgiven: " + instr + "\n\tExpected : " + valid
                            + " all within in a single parameter");
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
                throw new Exception("Incorrect instruction format!\n\tgiven : " + instr);
            }
            instruct.get(instr.charAt(0)).run();
            cube.clockwise = true;
            cube.extra = false;
            moves++;
            if (verbose) {
                System.err.println(cube.toString() + "Number of Moves : " + moves);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
                throw new Exception("Invalid Input!\n\tgiven : " + input + "\n\texpected : 'Y' or 'N'");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return output;
    }

    public static void inputmode() {
        try {
            Scanner scInput = new Scanner(System.in);
            String input;
            System.out.println(cube);
            System.out.println("Enter an instruction ('Q' to stop) : ");
            while (scInput.hasNext()) {
                input = scInput.nextLine();
                if (input.equalsIgnoreCase("Q")) {
                    System.out.println(instructions);
                    break;
                }
                execute(input.toUpperCase());
                System.out.println(cube);
                System.out.println("Enter an instruction ('Q' to stop) : ");
            }
            // scInput.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    static boolean nosolution = false;
    static boolean output = false;
    static boolean debugger = false;
    static boolean scrambled = false;
    static boolean parts = false;
    static boolean lined = false;

    static String[] checkFlags(String[] arguments) {
        ArrayList<String> args = new ArrayList<String>();
        Collections.addAll(args, arguments);
        // -i -l -nosolution -o -v -s -parts
        if (args.contains("-i")) {
            args.remove("-i");
            debugger = true;
        }
        if (args.contains("-l")) {
            args.remove("-l");
            lined = true;
        }
        if (args.contains("-n")) {
            args.remove("-n");
            nosolution = true;
        }
        if (args.contains("-o")) {
            args.remove("-o");
            output = true;
        }
        if (args.contains("-v")) {
            args.remove("-v");
            verbose = true;
        }
        if (args.contains("-s")) {
            args.remove("-s");
            scrambled = true;
        }
        if (args.contains("-p")) {
            args.remove("-p");
            parts = true;
        }
        return args.toArray(new String[0]);
        // return args.toArray(String[0]);
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

            args = checkFlags(args);
            for (String arg : args)
                System.out.println(arg);
            if (output)
                System.out.println(cube);
            List<String> scramble = new ArrayList<>();
            if (!scrambled && (debugger || args.length == 0)) {

                // System.out.println("debugger mode");
                output = true;
                scrambled = false;
                inputmode();
            } else {
                // throw new Exception("Argument required");
                String inputScramble = "";
                int num_scramble = 0;
                if (args.length > 0) {
                    // inputScramble = args[0];
                    // System.out.println("args : "+args[0]);
                    try {
                        num_scramble = Integer.parseInt(args[0]);
                        // scrambled = true;
                        // System.out.println("num scr: "+num_scramble);
                    } catch (NumberFormatException nfe) {
                        inputScramble = args[0];
                        if (scrambled) {
                            System.out.println("Cannot add instruction with scramble mode on!");
                            if (binaryquestion("would you like to continue with 10 move scramble")) {
                                num_scramble = 20;
                                scrambled = true;
                            } else
                                System.exit(0);
                        }
                    }
                } else {
                    scrambled = true;
                    num_scramble = 20;
                }
                // System.out.println("input "+inputScramble);
                if (scrambled) {

                    // scrambler = new Scrambler(num_scramble);
                    // System.out.println("scrambling");
                    scrambler.setNumMoves(num_scramble);
                    scrambler.generateScramble();
                    inputScramble = scrambler.getScrambleString();
                    // System.out.println("random scramble :"+inputScramble);
                }

                Collections.addAll(scramble, inputScramble.toUpperCase().split(" "));
                // System.out.println(scramble);
                // verbose = binaryquestion("turn verbose mode on");
                invoke(scramble);
            }
            if (output)
                System.out.println("Post scramble:\n" + cube);
            // if (binaryquestion("Do you wish to see solution")) {
            // verbose = binaryquestion("With vebose mode turned on");
            // Solver.simplesolve(scramble);
            // Solver solver = new Solver();
            if (!nosolution)
                new Solver().simplesolve(scramble);
            // }
            // if(output && !nosolution){
            // System.out.println("Final solution:\n" + cube);
            // System.out.println("Number of moves in solution : " + moves);
            // }
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
        if (instructions.size() <= 1)
            return (instructions);
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
