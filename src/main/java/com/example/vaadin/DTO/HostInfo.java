package com.example.vaadin.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HostInfo {
    private String oid;
    private String name;
    private String domain;
    private String ipaddr;
    private String pver;
}
