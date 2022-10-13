package Cryptoanalized_new;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

import static Cryptoanalized_new.Encode.*;
import static Cryptoanalized_new.Main.*;
import static Cryptoanalized_new.Main.filePath;

public class Decode {
    static char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    static Path path = Path.of(filePath);


    static void decode() throws IOException {
        //мапа с алфавитом
        Map<Character, Integer> mapEncodedChars = new HashMap<>();
        for (int i = 97; i <= 122; i++) {
            mapEncodedChars.put((char) i, 0);
        }
        Map<Character, Integer> mapOriginalChars = new HashMap<>(mapEncodedChars);//вторая мапа

        StringBuilder allCharsEncodedText = new StringBuilder();
        allCharsEncodedText = toCharsMassive(allCharsEncodedText, path); //закодированный текст одной строкой лоукейс, без знаков

        StringBuilder allCharsOriginalText = new StringBuilder();
        allCharsOriginalText = toCharsMassive(allCharsOriginalText, Path.of("C:\\javarush\\Project_module1\\FaB.txt"));//контрольный текст одной строкой лоукейс, без знаков

        countingLetters(mapEncodedChars, allCharsEncodedText);
        countingLetters(mapOriginalChars, allCharsOriginalText);
        char a = MostOftenChar(mapEncodedChars);//самая используемая буква в закодированном тексте
        char b = MostOftenChar(mapOriginalChars);//самая используемая буква в оригинальном (контрольном) тексте

        keyCode = 26 - (a - b);
        Encode.path = path;
        encode("decode");


    }

    //делаю мапу с подсчётом кол-ва каждой буквы
    public static void countingLetters(Map<Character, Integer> map, StringBuilder str) {
        for (int i = 0; i < str.length(); i++) {
            Character ch = str.charAt(i);
            map.put(ch, (map.get(ch) + 1));
        }
    }

    //делаю одну строку лоукейс без знаков и пробелов
    public static StringBuilder toCharsMassive(StringBuilder str, Path path) {
        BufferedInputStream buff = null;
        try {
            buff = new BufferedInputStream(new FileInputStream(path.toFile()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                if (!(buff.available() > 0)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                str.append((char) buff.read());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        str = new StringBuilder(str.toString().toLowerCase());//все буквы к строчным
        char[] array = str.toString().toCharArray();//в массив
        str.delete(0, str.length());//очистил стрингбилдер
        //избавляюсь от пробелов, знаков препинания, добавляю в стрингбилдер
        for (char ch : array) {
            if (isChar(ch)) {
                str.append(ch);
            }
        }
        return str;
    }


    //наиболее часто используемая буква в тексте
    private static char MostOftenChar(Map<Character, Integer> map) {
        char temp = 0;
        int d = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() > d) {
                temp = entry.getKey();
                d = entry.getValue();
            }
        }
        return temp;
    }


    //для удаления всего кроме чаров
    private static boolean isChar(char ch) {
        for (char c : alphabet) {
            if (ch == c) {
                return true;
            }
        }
        return false;

    }
}

    


