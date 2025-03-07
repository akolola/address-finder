package org.fotonotix;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Demo {
    private static final String INPUT_FILE = "input.txt";
    private static final String OUTPUT_FILE = "output.txt";

    public static void main(String[] args) {
        AddressParserService parserService = new AddressParserService();
        AddressExpanderService expanderService = new AddressExpanderService();

        try {
            List<String> lines = Files.readAllLines(Paths.get(INPUT_FILE));
            StringBuilder output = new StringBuilder();

            for (String line : lines) {
                System.out.println("Original: " + line);
                output.append("Original: ").append(line).append("\n");

                // Expand the address and ensure uniqueness
                Set<String> expandedAddresses = new LinkedHashSet<>(expanderService.expandAddress(line));
                boolean validAddressFound = false;

                for (String expandedAddress : expandedAddresses) {
                    // Parse only unique expanded addresses
                    Map<String, String> parsedAddress = parserService.parseAddress(expandedAddress);

                    if (!parsedAddress.isEmpty()) {
                        validAddressFound = true;
                        System.out.println("Expanded: " + expandedAddress);
                        output.append("Expanded: ").append(expandedAddress).append("\n");

                        for (Map.Entry<String, String> entry : parsedAddress.entrySet()) {
                            String formattedLine = entry.getKey() + ": " + entry.getValue();
                            System.out.println(formattedLine);
                            output.append(formattedLine).append("\n");
                        }
                        break; // Stop after the first valid parsed address
                    }
                }

                if (!validAddressFound) {
                    System.out.println("No valid address found.");
                    output.append("No valid address found.\n");
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
