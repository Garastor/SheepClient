package com.paazl.scheduling;

import java.time.LocalDateTime;

import com.paazl.rest.ShepherdClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.paazl.gui.GuiInterface;

@Component
public class ServerStatusTask {
    /*
     * TODO The task will update the GUI as instructed by the CRON job set in the
     * properties file. But now it only prints a hard coded string. Change the code
     * so this task prints the server status obtained from the server instead of the
     * hard coded string.
     */

    private final GuiInterface guiInterface;
    private final ShepherdClient shepherdClient;



    @Autowired
    public ServerStatusTask(GuiInterface guiInterface, ShepherdClient shepherdClient) {
        this.guiInterface = guiInterface;
        this.shepherdClient = shepherdClient;
    }

    @Scheduled(cron = "${scheduling.server-status}")
    public void getServerStatus() {
        String status = shepherdClient.getServerStatus();
        guiInterface.addServerFeedback("Server status: " + status + " at " + LocalDateTime.now());
    }
}
