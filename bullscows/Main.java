package bullscows;

import java.io.IOException;
import java.util.*;

class CowsBulls {
    int lengthCode;

    int possibleSymbols;

    void setLengthCode(String input) throws IOException {
        if (!input.matches("\\d+")) {
            throw new IOException("Error: \"" + input +"\" isn't a valid number");
        }
        if ("0".equals(input)) {
            throw new IOException("Error: code length cannot be 0");
        }
        this.lengthCode = Integer.parseInt(input);
    }

    void setPossibleSymbols(String input) throws IOException {
        if (!input.matches("\\d+")) {
            throw new IOException("Error: \"" + input +"\" isn't a valid number");
        }
        if ("0".equals(input)) {
            throw new IOException("Error: possible number of unique symbols cannot be 0");
        }
       this.possibleSymbols = Integer.parseInt(input);
    }

    void validateValues() {
        if (lengthCode > possibleSymbols) {
            throw new IllegalArgumentException("Error: it's not possible to generate a code with a length of " + lengthCode + " with " + possibleSymbols + " unique symbols.");
        }
    }

    static LinkedList<Character> toCharacterList(String number) {
        LinkedList<Character> charArray = new LinkedList<>();
        for (char c : number.toCharArray()) {
            charArray.add(c);
        }
        return charArray;
    }

    static String secretCodeToString(List<Integer> code) {
        StringBuilder out = new StringBuilder();
        for (Integer element: code) {
            out.append(element);
        }
        return out.toString();
    }

    static Pair<Integer, Integer> check(LinkedList<Character> secretNumber, List<Character> answer) {
        int cows = 0;
        int bulls = 0;
        for (int i = 0; i < answer.size(); i++) {
            if (secretNumber.get(i).equals(answer.get(i))) {
                bulls++;
            } else if (secretNumber.contains(answer.get(i))) {
                cows++;
            }
        }
        return new Pair<>(cows, bulls);
    }

    static String buildMessage(Pair<Integer, Integer> cowPair) {
        StringBuilder message = new StringBuilder("Grade: ");
        if (cowPair.getFirst() == 0 && cowPair.getSecond() == 0) {
            message.append("None");
        }
        if (cowPair.getSecond() > 0) {
            message.append(cowPair.getSecond()).append(" bull(s)");
        }
        if (cowPair.getSecond() > 0 && cowPair.getFirst() > 0) {
            message.append(" and ");
        }
        if (cowPair.getFirst() > 0) {
            message.append(cowPair.getFirst()).append(" cow(s)");
        }
        return message.toString();
    }

    public String secretGeneratedMessage() {
        StringBuilder message = new StringBuilder("The secret is prepared: ");
        message.append("*".repeat(Math.max(0, this.lengthCode)));
        message.append(" (0-9");
        if (this.possibleSymbols > 9) {
            int localMin = this.possibleSymbols - 11;
            message.append(", a-").append((char) ('a' + localMin));
        }
        message.append(").");
       return message.toString();
    }

    public LinkedList<Character> generateCode() throws IllegalArgumentException {
        LinkedList<Character> code = new LinkedList<>();
        if (this.possibleSymbols > 36) {
            throw new IllegalArgumentException("Error: can't generate a secret number with " + this.possibleSymbols + " unique alphanumerical symbols.");
        }
        String possibleValues = "0123456789abcdefghijklmopqrstuvwxyz";
        while (code.size() < this.lengthCode) {
            char localChar =  possibleValues.charAt((int) Math.abs(System.nanoTime() % this.possibleSymbols));
            if (!code.contains(localChar)) {
                code.add(localChar);
            }
        }
        return code;
    }
}

class Pair<T, U> {
    private final T t;
    private final U u;
    Pair(T t, U u) {
        this.t = t;
        this.u = u;
    }
    T getFirst() {
        return t;
    }
    U getSecond() {
        return u;
    }
}
class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            CowsBulls game = new CowsBulls();

            System.out.println("Please, enter the secret code's length:");
            game.setLengthCode(scanner.next());
            System.out.println("Input the number of possible symbols in the code:");
            game.setPossibleSymbols(scanner.next());
            game.validateValues();
            LinkedList<Character> secretCodeArray = game.generateCode();
            System.out.println(game.secretGeneratedMessage());

            int counter = 1;
            boolean running = true;
            System.out.println("Okay, let's start a game");
            while(running) {
                System.out.println("Turn " + counter + ":");
                LinkedList<Character> answer = CowsBulls.toCharacterList(scanner.next());
                System.out.println(answer);
                Pair<Integer, Integer> cowsBulls = CowsBulls.check(secretCodeArray, answer);
                System.out.println(CowsBulls.buildMessage(cowsBulls));
                if (cowsBulls.getSecond() == secretCodeArray.size()) {
                    System.out.println("Congratulations! You guessed the secret code.");
                    running = false;
                }
                counter++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}