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
        if (word.length() == 1 && Character.isLetter(word.charAt(0))) {
            char originalChar = word.charAt(0);
            char nextChar = (char) (originalChar + 1);

            // Check for wrapping around from 'z' to 'a' or 'Z' to 'A'
            if (originalChar == 'z') {
                nextChar = 'a';
            } else if (originalChar == 'Z') {
                nextChar = 'A';
            }

            return String.valueOf(nextChar);
        }

        return word;

        // Start here and finish rest of code
        // CODE GOES BELOW HERE
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a word: ");
        String input = scanner.nextLine();
        scanner.close();

        String transformedWord = transformInput(input);

        System.out.println(transformedWord);

    }
}
