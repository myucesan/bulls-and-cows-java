package bullscows;

import java.util.*;

//enum CharacterEnum {
//    a(97), b(98), c(99), d(100), e(101), f(102), g(103), h(104), i(105), j(106),
//    k(107), l(108), m(109), n(110), o(111), p(112), q(113), r(114), s(115), t(116),
//    u(117), v(118), w(119), x(120), y(121), z(122),
//    ZERO(48), ONE(49), TWO(50), THREE(51), FOUR(52), FIVE(53), SIX(54), SEVEN(55), EIGHT(56), NINE(57);
//    private final int ascii;
//
//    CharacterEnum(int ascii) {
//        this.ascii = ascii;
//    }
//}

public class Main {
    public static void main(String[] args) {
        final int MAX = 36;
        // a = 97
        // z = 122
        // 0 = 48
        // 9 = 57
        Scanner input = new Scanner(System.in);
        System.out.println("Input the length of the secret code:");
        if (!input.hasNextInt()) {
            System.out.printf("Error: %s isn't a valid number.", input.nextLine());
            System.exit(0);
        }
        int length = input.nextInt();

        input.nextLine();
        if (length > MAX | length == 0) {
            System.out.printf("Error: can't generate a secret number with a length of %d because there aren't enough unique digits. (Only 0 to 9, and a to z)", length);
            System.exit(0);
        }
        System.out.println("Input the number of possible symbols in the code:");
        int noOfPossibleSymbols = input.nextInt();
        input.nextLine();
        if (noOfPossibleSymbols < length) {
            System.out.println("Error: it's not possible to generate a code with a length of 6 with 5 unique symbols.");
            System.exit(0);
        }
        if (noOfPossibleSymbols > MAX) {
            System.out.println("Error: There are only 36 values.");
            System.exit(0);
        }

        if (true) {
            String code = uniqueGuessCodeRandom(length, noOfPossibleSymbols);
            System.out.printf("The secret is prepared: %s, (%s).", "*".repeat(length), generateRangePossibleSymbols(noOfPossibleSymbols));
            char[] ccode = code.toCharArray();

            System.out.println("Okay, let's start a game!");
            int turn = 1;
            while (true) {
                System.out.printf("Turn %d:", turn);
                turn++;
                char[] guessedCodeCharred = input.nextLine().toCharArray();

                int cows = 0;
                int bulls = 0;

                for (int i = 0; i < guessedCodeCharred.length; i++) {
                    if (guessedCodeCharred[i] == ccode[i]) {
                        bulls++;
                    } else if (code.indexOf(guessedCodeCharred[i]) != -1) {
                        cows++;
                    }
                }

                System.out.printf("Grade: %d cow(s) and %d bull(s). The secret code is %s. \n", cows, bulls, code);
                if (bulls == length) {
                    System.out.println("Congratulations! You guessed the secret code.");
                    break;
                }
            }
        }


    }

    public static String generateRangePossibleSymbols(int no) {
        if (no <= 10) {
            return "0-" + (char) ('0' + no - 1);
        } else {
            return "0-9, a-" + (char) ('a' + (no - 11));
        }
    }

    public static String uniqueGuessCodeNanoTime(int length) {
        long pseudoRandomNumber = System.nanoTime();
        char[] number = Long.toString(pseudoRandomNumber).toCharArray();
        StringBuilder sb = new StringBuilder();
        Set<Character> finalSequence = new HashSet<>();
        int i = 0;
        for (char c : number) {
            if (finalSequence.add(c)) {
                sb.append(c);
                if (sb.length() == length) {
                    break;
                }
            }
        }

        return sb.toString();
    }



    public static String uniqueGuessCodeRandom (int length, int possibleSymbols) {
//        Random r = new Random();
//        int range1Min = 97;
//        int range1Max = 122;
//        int range2Min = 48;
//        int range2Max = 57;

        // Generate the random number within the chosen range

//        StringBuilder sb = new StringBuilder();
//        Set<Integer> code = new HashSet<>();
//        while (sb.length() < length) {
//            if (code.size() == possibleSymbols) break;
//            boolean useFirstRange = r.nextBoolean();
//            int randomChar = 0;
//            if (useFirstRange) {
//                randomChar = r.nextInt((range1Max - range1Min) + 1) + range1Min;
//            } else {
//                randomChar =  r.nextInt((range2Max - range2Min) + 1) + range2Min;
//            }
//            if (code.add(randomChar)) {
//                sb.append((char) randomChar);
//            }
//        }

        // Generate the pool of characters based on the possibleSymbols
        List<Character> charPool = new ArrayList<>();
        for (int i = 0; i < 10 && i < possibleSymbols; i++) {
            charPool.add((char) (48 + i)); // Adding '0'-'9'
        }
        for (int i = 0; i < (possibleSymbols - 10); i++) {
            charPool.add((char) (97 + i)); // Adding 'a'-'z'
        }

        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        Set<Character> code = new HashSet<>();

        // Generate the unique code
        while (sb.length() < length) {
            int randomIndex = r.nextInt(charPool.size());
            char randomChar = charPool.get(randomIndex);
            if (code.add(randomChar)) {
                sb.append(randomChar);
            }
        }

        return sb.toString();
    }
}
