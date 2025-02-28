package org.fotonotix;

import com.mapzen.jpostal.AddressParser;
import com.mapzen.jpostal.ParsedComponent;

import java.util.*;
import java.util.regex.*;

public class Main {

    public static void main(String[] args) {
        AddressParser parser = AddressParser.getInstance();

        // Example texts with embedded addresses
        String[] examples = {
                "Company X is located at 1 Infinite Loop, Cupertino, CA 95014, USA. It is a major tech company.",
                "The White House is located at 1600 Pennsylvania Avenue NW, Washington, DC 20500, USA.",
                "My office address is 123 Tech Park, Silicon Valley, CA 94043, USA.",
                "The Eiffel Tower is located at Champ de Mars, 5 Avenue Anatole, Paris, France.",
                "The Great Wall of China can be found at Huairou District, Beijing, China.",
                "The Book Club 100-106 Leonard St, Shoreditch, London, Greater London, EC2A 4RH, United Kingdom"
        };

        // Process each example
        for (String text : examples) {
            List<String> extractedAddresses = extractAddresses(text);

            if (extractedAddresses.isEmpty()) {
                System.out.println("No valid address found in text: " + text);
                continue;
            }

            for (String address : extractedAddresses) {
                // Parse the extracted address
                List<ParsedComponent> parsed = List.of(parser.parseAddress(address));

                // Print the parsed components
                System.out.println("\nParsed Address Components from: " + address);
                for (ParsedComponent component : parsed) {
                    System.out.println(component.getLabel() + ": " + component.getValue());
                }
            }
        }
    }

    // Improved method to extract addresses from text
    private static List<String> extractAddresses(String text) {
        List<String> addresses = new ArrayList<>();

        // Regex pattern to detect addresses in common sentence structures
        String regex = "(?i)(?:located at|found at|address is|is situated at)\\s([^\\.]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String address = matcher.group(1).trim();
            if (!address.isEmpty()) {
                addresses.add(address);
            }
        }

        return addresses;
    }
}
