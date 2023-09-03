import java.util.Scanner;

public class TextEncryption {

    public static String transformInput(String input) {
        // Split the input into words
        String[] words = input.split(" ");
        // Use a string builder to store the transformed text
        StringBuilder transformed = new StringBuilder();

        for (String word : words) {
            // If it's a number, add 2501 to it
            if (word.matches("-?\\d+")) {
                int numWord = Integer.parseInt(word);
                transformed.append(numWord + 2501).append(" ");
            } else {
                // Apply the word transformation rules
                transformed.append(transformWord(word)).append(" ");
            }
        }

        // Remove trailing space and return the result
        return transformed.toString().trim();
    }

    public static String transformWord(String word) {
        // If the word has 3 or more odd letters, swap the first and last letter
        if (hasThreeOrMoreOddLetters(word)) {
            char[] chars = word.toCharArray();
            char temp = chars[0];
            chars[0] = chars[chars.length - 1];
            chars[chars.length - 1] = temp;
            return new String(chars);
        } else if (hasTwoOrMoreEvenLetters(word)) {
            // If the word has 2 or more even letters, swap adjacent letters
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length - 1; i += 2) {
                char temp = chars[i];
                chars[i] = chars[i + 1];
                chars[i + 1] = temp;
            }
            return new String(chars);
        } else if (word.length() == 1) {
            char c = word.charAt(0);
            if (Character.isLetter(c)) {
                // For single characters, shift to the next character or wrap around from 'z' to
                // 'a'
                if (c == 'z') {
                    return "a";
                } else {
                    return String.valueOf((char) (c + 1));
                }
            }
        }

        // If none of the conditions are met, return the original word
        return word;
    }

    public static boolean hasThreeOrMoreOddLetters(String word) {
        // Count the odd letters, return true if 3 or more
        int oddCount = 0;
        for (char c : word.toCharArray()) {
            if (Character.isLetter(c) && (c - 'a') % 2 == 1) {
                oddCount++;
                if (oddCount >= 3) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean hasTwoOrMoreEvenLetters(String word) {
        // Count the even letters, return true if 2 or more
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
            // Ask the user if they want to continue
            System.out.print("Would you like to encrypt more text (yes/no)? ");
            String input = sc.next();

            if (input.equals("no")) {
                System.out.println("Exiting...");
                break;
            } else if (input.equals("yes")) {
                prompt();
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }

            // Consume any remaining newline characters
            sc.nextLine();
        }

        sc.close();
    }

    public static void prompt() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter text: ");
        String input = scanner.nextLine();

        String transformedWord = transformInput(input);

        // Output the result in lowercase
        System.out.println(transformedWord.toLowerCase());
    }

}
