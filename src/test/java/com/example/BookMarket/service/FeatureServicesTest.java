package com.example.BookMarket.service;

import com.example.BookMarket.config.FeatureToggleProperties;
import com.example.BookMarket.featureToggle.FeatureToggleExtension;
import com.example.BookMarket.featureToggle.FeatureToggleService;
import com.example.BookMarket.featureToggle.FeatureToggles;
import com.example.BookMarket.featureToggle.annotation.DisabledFeatureToggle;
import com.example.BookMarket.featureToggle.annotation.EnabledFeatureToggle;
import com.example.BookMarket.featureToggle.aspect.FeatureToggleAspect;
import com.example.BookMarket.featureToggle.exception.FeatureNotAvailableException;
import com.example.BookMarket.service.impl.CustomerServiceImpl;
import com.example.BookMarket.service.impl.ProductServiceImpl;
import com.example.BookMarket.service.impl.TopBookServiceImpl;
import com.example.BookMarket.service.impl.VipUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(
        classes = {
                TopBookServiceImpl.class,
                ProductServiceImpl.class,
                VipUserServiceImpl.class,
                CustomerServiceImpl.class,
                FeatureToggleService.class,
                FeatureToggleAspect.class
        })
@DisplayName("VIP users Service Tests")
@ExtendWith(FeatureToggleExtension.class)
@EnableAspectJAutoProxy
public class FeatureServicesTest {
    @Autowired
    private VipUserService vipUserService;

    @Autowired
    private TopBookService topBookService;
    @MockBean
    private FeatureToggleProperties featureToggleProperties;

    private static List<String> vipUsers = List.of("Ivan Zelinski", "Vasil Petrov");

    private static List<String> topBooks = List.of("Animal Farm", "Ten Little Niggers");

    @BeforeEach
    private void setToggles() {
        Map<String, Boolean> map = new HashMap<>();
        for (FeatureToggles toggle : FeatureToggles.values()) {
            map.put(toggle.getFeatureName(), false);
        }
        featureToggleProperties.setToggles(map);
    }

    @Test
    @DisabledFeatureToggle(FeatureToggles.VIP_USERS)
    void shouldThrowFeatureNotAvailableException() {
        assertThrows(FeatureNotAvailableException.class, vipUserService::getVipUsers);
    }

    @Test
    @EnabledFeatureToggle(FeatureToggles.VIP_USERS)
    void shouldReturnNames() {
        assertEquals(vipUsers, vipUserService.getVipUsers());
    }

    @Test
    @DisabledFeatureToggle(FeatureToggles.TOP_BOOKS)
    void shouldThrowFeatureNotAvailableException2() {
        assertThrows(FeatureNotAvailableException.class, topBookService::getTopBooks);
    }

    @Test
    @EnabledFeatureToggle(FeatureToggles.TOP_BOOKS)
    void shouldReturnNames2() {
        assertEquals(topBooks, topBookService.getTopBooks());
    }
}