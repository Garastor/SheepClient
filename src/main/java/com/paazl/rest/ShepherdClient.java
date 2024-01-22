package com.paazl.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import com.paazl.gui.GuiInterface;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class ShepherdClient {

    @Value("${server-status-url}")
    String serverStatusUrl;
    @Value("${order-sheep-url}")
    String orderNewSheepUrl;
    RestTemplate restTemplate;

    /*
     * TODO Use a Rest client to obtain the server status, so this client can be used to obtain that status.
     * TODO Write unit tests.
     */
    @Autowired
    public ShepherdClient(GuiInterface guiInterface, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        guiInterface.addOrderRequestListener(i -> guiInterface.addServerFeedback(orderNewSheep(i)));
    }

    public String getServerStatus() {
         return Objects.requireNonNull(restTemplate.getForObject(serverStatusUrl, HttpStatus.class)).getReasonPhrase();
    }

    public String orderNewSheep(int nofSheepDesired) {
        return restTemplate.getForObject(orderNewSheepUrl+nofSheepDesired, String.class);
    }
}
