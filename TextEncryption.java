import java.util.Scanner;

public class TextEncryption {

    public static String transformInput(String input) {
        String[] words = input.split(" "); // Split input into words
        StringBuilder transformed = new StringBuilder();

        for (String word : words) {
            if (word.matches("-?\\d+")) {
                int numWord = Integer.parseInt(word);
                transformed.append(numWord + 2501).append(" ");
            } else {
                transformed.append(transformWord(word)).append(" ");
            }
        }

        return transformed.toString().trim(); // Remove trailing space
    }

    public static String transformWord(String word) {
        if (hasTwoOrMoreEvenLetters(word)) {
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length - 1; i += 2) {
                char temp = chars[i];
                chars[i] = chars[i + 1];
                chars[i + 1] = temp;
            }
            return new String(chars);
        } else if (hasThreeOrMoreOddLetters(word)) {
            char[] chars = word.toCharArray();
            char temp = chars[0];
            chars[0] = chars[chars.length - 1];
            chars[chars.length - 1] = temp;
            return new String(chars);
        }

        return word;

    }

    public static boolean hasThreeOrMoreOddLetters(String word) {
        int oddCount = 0;
        for (char c : word.toCharArray()) {
            if (Character.isLetter(c) && (c - 'a') % 2 != 0) {
                oddCount++;
                if (oddCount >= 3) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean hasTwoOrMoreEvenLetters(String word) {
        int evenCount = 0;
        for (char c : word.toCharArray()) {
            if (Character.isLetter(c) && (c - 'a') % 2 == 0) {
                evenCount++;
                if (evenCount >= 2) {
                    return true;
                }
            }
        }
        return false;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        prompt();
        while (true) {

            System.out.print("Would you like to encrypt more text (yes/no)? ");
            String input = sc.next();
            if (input.equals("no")) {
                System.out.println("Quitting...");
                break;
            } else if (input.equals("yes")) {
                prompt();
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }

        sc.close();
    }

    public static void prompt() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a word: ");
        String input = scanner.nextLine();

        String transformedWord = transformInput(input);

        System.out.println(transformedWord.toLowerCase());
    }

}
