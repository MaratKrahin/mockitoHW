package ru.netology.sender;


import org.junit.jupiter.api.Assertions;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MessageSenderImplTest {

    @org.junit.jupiter.api.Test
    void send_USA() {
        Location location = new Location("1",Country.USA,"1",1);
        GeoService geoService = mock(GeoService.class);
        when(geoService.byIp((String) any())).thenReturn(location);

        LocalizationService localizationService = mock(LocalizationService.class);
        when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> map = new HashMap<String, String>();
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, "452.0.0.1");

        Assertions.assertEquals("Welcome", messageSender.send(map));
    }

    @org.junit.jupiter.api.Test
    void send_RUSSIA() {
        Location location = new Location("2",Country.RUSSIA,"2",1);
        GeoService geoService = mock(GeoService.class);
        when(geoService.byIp((String) any())).thenReturn(location);

        LocalizationService localizationService = mock(LocalizationService.class);
        when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> map = new HashMap<String, String>();
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.0.0.1");

        Assertions.assertEquals("Добро пожаловать", messageSender.send(map));
    }



}
