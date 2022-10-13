package Cryptoanalized_new;

import java.io.IOException;

import static Cryptoanalized_new.Encode.encode;
import static Cryptoanalized_new.Decode.decode;

public class Main {
    static int keyCode;
    static String filePath;

    public static void main(String[] args) throws IOException {

        filePath = args[1];
        switch (args[0]) {
            case "encode" -> {
                keyCode = Integer.parseInt(args[2]);
                encode("encode");

            }
            case "decode" -> decode();

        }

    }
}