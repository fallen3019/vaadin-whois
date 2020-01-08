package com.example.vaadin.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Adil
 */
@Data
@NoArgsConstructor
public class DomainInfo {
    String domainName;
    String name;
    String organizationName;
    String streetAddress;
    String city;
    String state;
    String postalCode;
    String country;
}
