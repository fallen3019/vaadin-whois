package com.example.vaadin.DTO;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DomainDTO {
    private DomainInfo domainInfo;
    private ContactInfo contactInfo;
    private List<HostInfo> hostInfo;
    private List<StatusInfo> statusInfo;
}
