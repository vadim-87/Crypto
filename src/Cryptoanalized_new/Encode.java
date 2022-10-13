package Cryptoanalized_new;

import java.io.*;
import java.nio.file.Path;

import static Cryptoanalized_new.Main.filePath;
import static Cryptoanalized_new.Main.keyCode;

public class Encode {
    static char[] alphabetTrivial = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    static char[] alphabetCapital = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    static Path path = Path.of(filePath);

    static void encode(String operation) throws IOException {
        StringBuilder originalText = new StringBuilder();
        StringBuilder encodedText = new StringBuilder();
        BufferedInputStream input = null;
        BufferedOutputStream output = null;


        try {
            input = new BufferedInputStream(new FileInputStream(path.toFile()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found");
        }
        try {
            while (input.available() > 0) {
                originalText.append((char) input.read());
            }
        } finally {
            input.close();
        }
        for (int i = 0; i < originalText.length(); i++) {
            char temp = originalText.charAt(i);
            if (isChar_trivial(temp)) {
                int index = normalizedIndexForTrivial(temp);
                encodedText.append(alphabetTrivial[index]);
            } else if (isChar_capital(temp)) {
                int index = normalizedIndexForCapital(temp);
                encodedText.append(alphabetCapital[index]);
            } else encodedText.append(temp);
        }

        try {
            output = new BufferedOutputStream(new FileOutputStream(path.toFile() + "(" + operation + ")" + ".txt"));
            String resultToFile = encodedText.toString();
            try {
                output.write(resultToFile.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error to create file");
        } finally {
            output.flush();
        }


        System.out.println("Successful!");
    }

    private static int normalizedIndexForTrivial(char ch) {
        int index = keyCode;
        for (int i = 0; i < alphabetTrivial.length; i++) {
            if (ch == alphabetTrivial[i]) {
                index = i;
                break;
            }
        }
        if ((index + keyCode) > alphabetTrivial.length - 1) {
            index = index + keyCode - alphabetTrivial.length;
        } else {
            index = index + keyCode;
        }
        return index;
    }

    private static int normalizedIndexForCapital(char ch) {
        int index = keyCode;
        for (int i = 0; i < alphabetCapital.length; i++) {
            if (ch == alphabetCapital[i]) {
                index = i;
                break;
            }
        }
        if ((index + keyCode) > alphabetCapital.length - 1) {
            index = index + keyCode - alphabetCapital.length;
        } else {
            index = index + keyCode;
        }
        return index;
    }

    private static boolean isChar_trivial(char ch) {
        for (char c : alphabetTrivial) {
            if (ch == c) {
                return true;
            }
        }
        return false;

    }

    private static boolean isChar_capital(char ch) {
        for (char c : alphabetCapital) {
            if (ch == c) {
                return true;
            }
        }
        return false;
    }

}
