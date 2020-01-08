package com.example.vaadin.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContactInfo {
    private String handle;
    private String name;
    private String voice;
    private String fax;
    private String email;
}
