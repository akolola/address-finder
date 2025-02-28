package org.fotonotix;

import com.mapzen.jpostal.AddressParser;
import com.mapzen.jpostal.ParsedComponent;

import java.util.*;
import java.util.regex.*;

public class AddressParserService {
    private final AddressParser parser;

    public AddressParserService() {
        this.parser = AddressParser.getInstance();
    }

    /**
     * Extracts potential addresses from a given text and parses them.
     */
    public List<Map<String, String>> extractAndParseAddresses(String text) {
        List<String> extractedAddresses = extractAddresses(text);
        List<Map<String, String>> parsedResults = new ArrayList<>();

        for (String address : extractedAddresses) {
            Map<String, String> parsedAddress = parseAddress(address);
            if (!parsedAddress.isEmpty()) {
                parsedResults.add(parsedAddress);
            }
        }
        return parsedResults;
    }

    /**
     * Uses regex to extract address-like patterns from the given text.
     */
    private List<String> extractAddresses(String text) {
        List<String> addresses = new ArrayList<>();

        // Regex pattern to find address-like phrases
        String regex = "(\\d{1,5} [\\w\\s]+, [\\w\\s]+, [A-Z]{2} \\d{5}, [\\w\\s]+)|" +  // US format
                "(\\d{1,5} [\\w\\s]+, [\\w\\s]+, [\\w\\s]+)|" +                   // Generic format
                "([\\w\\s]+, \\d{1,5} [\\w\\s]+, [\\w\\s]+, [\\w\\s]+)|" +       // European format
                "([\\w\\s]+ District, [\\w\\s]+, [\\w\\s]+)";                   // District-based format

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            addresses.add(matcher.group());
        }

        return addresses;
    }

    /**
     * Parses a given address using JPostal.
     */
    private Map<String, String> parseAddress(String address) {
        Map<String, String> parsedComponents = new LinkedHashMap<>();
        ParsedComponent[] components = parser.parseAddress(address);

        for (ParsedComponent component : components) {
            parsedComponents.put(component.getLabel(), component.getValue());
        }
        return parsedComponents;
    }
}
