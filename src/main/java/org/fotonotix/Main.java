package org.fotonotix;

import com.mapzen.jpostal.AddressParser;
import com.mapzen.jpostal.ParsedComponent;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Initialize the AddressParser
        AddressParser parser = AddressParser.getInstance();

        // Example text containing an address
        String address = "1600 Amphitheatre Parkway, Mountain View, CA 94043, USA";

        // Parse the address
        List<ParsedComponent> parsed = List.of(parser.parseAddress(address));

        // Print the parsed address components
        System.out.println("Parsed Address Components:");
        for (ParsedComponent component : parsed) {
            System.out.println(component.getLabel() + ": " + component.getValue());
        }
    }
}
