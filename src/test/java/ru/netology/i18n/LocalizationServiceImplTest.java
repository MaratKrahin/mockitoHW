package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LocalizationServiceImplTest {

    @Test
    void locale() {
        GeoService geoService = mock(GeoService.class);
        LocalizationService localizationService = mock(LocalizationService.class);

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Location location = new Location("1", Country.USA,"1",1);

        when(localizationService.locale(Country.USA)).thenReturn("Welcome");
        when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
        when(geoService.byIp((String) any())).thenReturn(location);


        Assertions.assertEquals("Welcome", localizationService.locale(Country.USA));
        Assertions.assertEquals("Добро пожаловать", localizationService.locale(Country.RUSSIA));
    }
}

