package org.fotonotix;

import com.mapzen.jpostal.AddressParser;
import com.mapzen.jpostal.ParsedComponent;

import java.util.*;

public class AddressParserService {
    private final AddressParser parser;

    public AddressParserService() {
        this.parser = AddressParser.getInstance();
    }

    public Map<String, String> parseAddress(String address) {
        Map<String, String> parsedComponents = new LinkedHashMap<>();
        ParsedComponent[] components = parser.parseAddress(address);

        for (ParsedComponent component : components) {
            parsedComponents.put(component.getLabel(), component.getValue());
        }
        return parsedComponents;
    }
}
