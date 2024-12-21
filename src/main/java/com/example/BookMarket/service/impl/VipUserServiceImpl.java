package com.example.BookMarket.service.impl;

import com.example.BookMarket.featureToggle.FeatureToggles;
import com.example.BookMarket.featureToggle.annotation.FeatureToggle;
import com.example.BookMarket.service.CustomerService;
import com.example.BookMarket.service.VipUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class VipUserServiceImpl implements VipUserService {

    private CustomerService customerService;

    VipUserServiceImpl(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    @FeatureToggle(FeatureToggles.VIP_USERS)
    public List<String> getVipUsers() {
        return List.of(
                customerService.getCustomerById(1L).getNickname(),
                customerService.getCustomerById(2L).getNickname());
    }
}