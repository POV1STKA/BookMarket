package com.example.BookMarket.featureToggle;

import lombok.Getter;

@Getter
public enum FeatureToggles {
    VIP_USERS("vipUsers"),
    TOP_BOOKS("topBooks");

    private final String featureName;

    FeatureToggles(String featureName) {
        this.featureName = featureName;
    }
}