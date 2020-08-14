package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import ru.netology.entity.Location;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GeoServiceImplTest {

    @Test
    void byIp() {
        GeoService geoService = mock(GeoService.class);
        LocalizationService localizationService = mock(LocalizationService.class);
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Location location = new Location("City", null, "Street", 99);

        when(geoService.byIp((String) any())).thenReturn(location);
        messageSender.send(new HashMap<String, String>(){
            {put(MessageSenderImpl.IP_ADDRESS_HEADER, "127.0.0.1");}});

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

        verify(geoService).byIp(argumentCaptor.capture());
        Assertions.assertEquals("127.0.0.1", argumentCaptor.getValue());
        verify(geoService, times(1)).byIp(GeoServiceImpl.LOCALHOST);
        Assertions.assertEquals(geoService.byIp(MessageSenderImpl.IP_ADDRESS_HEADER), location);
    }
}