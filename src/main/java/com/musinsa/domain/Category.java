package com.musinsa.domain;

import lombok.Getter;

@Getter
public enum Category {
    TOP("상의"),
    OUTER("아우터"),
    PANTS("바지"),
    SNEAKERS("스니커즈"),
    BAG("가방"),
    HAT("모자"),
    SOCKS("양말"),
    ACCESSORY("액세서리");

    private final String korean;

    Category(String korean) {
        this.korean = korean;
    }

}
