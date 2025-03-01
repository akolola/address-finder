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
                output.append("Original: ").append(line).append("\n");

                List<Map<String, String>> parsedAddresses = parserService.extractAndParseAddresses(line);

                if (parsedAddresses.isEmpty()) {
                    output.append("No valid address found.\n");
                } else {
                    for (Map<String, String> address : parsedAddresses) {
                        address.forEach((key, value) -> output.append(key).append(": ").append(value).append("\n"));
                    }
                }
                output.append("\n"); // Separate results with a blank line
            }

            // Write results to output.txt
            Files.write(Paths.get(OUTPUT_FILE), output.toString().getBytes());
            System.out.println("Results written to " + OUTPUT_FILE);

        } catch (IOException e) {
            System.err.println("Error reading or writing files: " + e.getMessage());
        }
    }
}
