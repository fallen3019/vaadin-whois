package com.example.vaadin.view;

import com.example.vaadin.DTO.DomainDTO;
import com.example.vaadin.DTO.Request;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Route
@Service
public class MainView extends VerticalLayout {

    public static final String URL = "http://localhost:8080/api/whois/";
    private RestTemplate restTemplate = new RestTemplate();

    public MainView() {
        FormLayout formLayout = new FormLayout();
        TextField textField = new TextField();
        textField.setPlaceholder("please input domain");
        textField.setWidth("630px");
        textField.setHeight("50px");
        VerticalLayout v1 = new VerticalLayout();

        TextArea textArea = new TextArea("TextArea");
        textArea.setWidth("800px");
        textArea.setHeight("1000px");
        v1.add(textArea);

        Button button = new Button("SHOW");

        button.addClickListener(event -> {
                    DomainDTO dto = domainDTO(new Request(textField.getValue()));
                    String txt = report(dto);
                    textArea.setValue(txt);
                }
        );

        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        formLayout.addFormItem(textField, "DOMAIN");
        button.setWidth("30%");
        formLayout.addFormItem(button, " ");
        add(formLayout, v1);
    }

    private DomainDTO domainDTO(Request request) {
        return restTemplate.postForObject(URL, request, DomainDTO.class);
    }

    private String report(DomainDTO dto) {
        StringBuffer sb = new StringBuffer();
        sb
                .append("Domain Name............: ").append(dto.getDomainInfo().getDomainName()).append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("Organization Using Domain Name").append(System.lineSeparator())
                .append("Name...................: ").append(dto.getDomainInfo().getName()).append(System.lineSeparator())
                .append("Organization Name......: ").append(dto.getDomainInfo().getOrganizationName()).append(System.lineSeparator())
                .append("Street Address.........: ").append(dto.getDomainInfo().getStreetAddress()).append(System.lineSeparator())
                .append("City...................: ").append(dto.getDomainInfo().getCity()).append(System.lineSeparator())
                .append("State..................: ").append(dto.getDomainInfo().getState()).append(System.lineSeparator())
                .append("Postal Code............: ").append(dto.getDomainInfo().getPostalCode()).append(System.lineSeparator())
                .append("Country................: ").append(dto.getDomainInfo().getCountry()).append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("Administrative Contact/Agent").append(System.lineSeparator())
                .append("Nick Handle............: ").append(dto.getContactInfo().getHandle()).append(System.lineSeparator())
                .append("Phone Number...........: ").append(dto.getContactInfo().getVoice()).append(System.lineSeparator())
                .append("Fax....................: ").append(dto.getContactInfo().getFax()).append(System.lineSeparator())
                .append("Email Address..........: ").append(dto.getContactInfo().getEmail()).append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("Nameserver in listed order").append(System.lineSeparator())
                .append(System.lineSeparator());

        dto.getHostInfo().forEach(hInfo -> sb
                .append("Server.................: ").append(hInfo.getName()).append(System.lineSeparator())
                .append("Ip address.............: ").append(hInfo.getIpaddr()).append(System.lineSeparator())
                .append(System.lineSeparator())
        );

        sb.append(System.lineSeparator());

        dto.getStatusInfo().forEach(sInfo -> sb
                .append("Domain created: ").append(sInfo.getCreated()).append(System.lineSeparator())
                .append("Last modified: ").append(sInfo.getUpdated()).append(System.lineSeparator())
                .append("Domain status: ").append(sInfo.getStatus()).append(System.lineSeparator())
                .append("Registrar created: ").append(sInfo.getRegistarcr()).append(System.lineSeparator())
                .append("Current Registrar: ").append(sInfo.getRegistrar()).append(System.lineSeparator())
                .append(System.lineSeparator())
        );

        return sb.toString();
    }
}
