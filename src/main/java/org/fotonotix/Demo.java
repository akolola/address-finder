package org.fotonotix;

import java.util.List;
import java.util.Map;

public class Demo {
    public static void main(String[] args) {
        AddressParserService parserService = new AddressParserService();

        // Example texts with embedded addresses
        String[] examples = {
                "Company X is located at 1 Infinite Loop, Cupertino, CA 95014, USA. It is a major tech company.",
                "The White House is located at 1600 Pennsylvania Avenue NW, Washington, DC 20500, USA.",
                "My office address is 123 Tech Park, Silicon Valley, CA 94043, USA.",
                "Champ de Mars, 5 Avenue Anatole, Paris, France is a famous landmark.",
                "The Great Wall of China can be found at Huairou District, Beijing, China.",
                "The Book Club 100-106 Leonard St, Shoreditch, London, Greater London, EC2A 4RH, United Kingdom."
        };

        for (String text : examples) {
            System.out.println("\nParsing Address Components from: " + text);
            List<Map<String, String>> parsedAddresses = parserService.extractAndParseAddresses(text);

            if (parsedAddresses.isEmpty()) {
                System.out.println("No valid address found in text.");
            } else {
                for (Map<String, String> address : parsedAddresses) {
                    address.forEach((key, value) -> System.out.println(key + ": " + value));
                }
            }
        }
    }
}
