package com.example.BookMarket.featureToggle.aspect;

import com.example.BookMarket.featureToggle.FeatureToggleService;
import com.example.BookMarket.featureToggle.FeatureToggles;
import com.example.BookMarket.featureToggle.annotation.FeatureToggle;
import com.example.BookMarket.featureToggle.exception.FeatureNotAvailableException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class FeatureToggleAspect {
    private final FeatureToggleService featureToggleService;

    @Before("@annotation(featureToggle)")
    public void checkFeatureToggle(JoinPoint joinPoint, FeatureToggle featureToggle) {
        FeatureToggles toggle = featureToggle.value();
        if (!featureToggleService.check(toggle.getFeatureName())) {
            throw new FeatureNotAvailableException(toggle.getFeatureName());
        }
    }
}