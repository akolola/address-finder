package org.fotonotix;

import com.mapzen.jpostal.AddressExpander;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Arrays;

public class AddressExpanderService {
    private final AddressExpander expander;

    public AddressExpanderService() {
        this.expander = AddressExpander.getInstance();
    }

    public Set<String> expandAddress(String address) {
        return new LinkedHashSet<>(Arrays.asList(expander.expandAddress(address)));
    }
}
