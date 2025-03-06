package org.fotonotix;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.Map;

public class Demo {
    private static final String INPUT_FILE = "input.txt";
    private static final String OUTPUT_FILE = "output.txt";

    public static void main(String[] args) {
        AddressParserService parserService = new AddressParserService();

        try {
            List<String> lines = Files.readAllLines(Paths.get(INPUT_FILE));
            StringBuilder output = new StringBuilder();

            for (String line : lines) {
                System.out.println("Original: " + line);
                output.append("Original: ").append(line).append("\n");

                Map<String, String> parsedAddress = parserService.parseAddress(line);

                if (parsedAddress.isEmpty()) {
                    System.out.println("No valid address found.");
                    output.append("No valid address found.\n");
                } else {
                    for (Map.Entry<String, String> entry : parsedAddress.entrySet()) {
                        String formattedLine = entry.getKey() + ": " + entry.getValue();
                        System.out.println(formattedLine);
                        output.append(formattedLine).append("\n");
                    }
                }
                System.out.println(); // Blank line in console
                output.append("\n"); // Blank line in output file
            }

            Files.write(Paths.get(OUTPUT_FILE), output.toString().getBytes());
            System.out.println("Results written to " + OUTPUT_FILE);

        } catch (IOException e) {
            System.err.println("Error reading or writing files: " + e.getMessage());
        }
    }
}
