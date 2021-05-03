package com.cowin.vaccineNotification.service;

import com.cowin.vaccineNotification.client.CowinClientImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@EnableScheduling
public class NotificationMachineService {
    @Autowired
    CowinClientImpl cowinClient;
    TrayIcon trayIcon;

    public NotificationMachineService() throws AWTException {
        try {
            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().createImage("");
            trayIcon = new TrayIcon(image, "Cowin Vaccine Slot Availability");
            trayIcon.setImageAutoSize(true);
            tray.add(trayIcon);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // fixedDelay in milliseconds. Add desired notification duration here by updating value of fixedDelay below
    @Scheduled(fixedDelay = 300000)
    public void turnOnVaccineAvailabilityNotification() {
        // You can set alternate age here too. Available ages: 18, 45, 60
        int age = 18;
            cowinClient
                    .getCowinVaccineAvailabilityData()
                    .subscribe(vaccineSlot-> {
                        if (vaccineSlot != null && vaccineSlot.getSessions() !=null) {
                            System.out.println("Got response from cowin servers");
                            vaccineSlot.getSessions().stream()
                                    .filter(session -> session.min_age_limit == age && session.available_capacity!=0)
                                    .findAny()
                                    .map(session -> {
                                        String key = "Vaccine Available !";
                                        String value = String.format("At %s starting from %s", session.name, session.slots.get(0));
                                        try {
                                            popupMachine(key, value);
                                        } catch (AWTException e) {
                                            e.printStackTrace();
                                        }
                                        return session;
                                    })
                                    .orElseGet(()-> {
                                        // Comment out the try & catch block if you don't want notifications when no vaccines found for the age group
                                        try {
                                            popupMachine("No vaccine available", "No Vaccine found for age group "+age);
                                        } catch (AWTException e) {
                                            e.printStackTrace();
                                        }
                                        return null;
                                    });
                        }
                    });
    }
    private void popupMachine(String key, String value) throws AWTException {
        trayIcon.displayMessage(key, value, TrayIcon.MessageType.INFO);
    }
}
