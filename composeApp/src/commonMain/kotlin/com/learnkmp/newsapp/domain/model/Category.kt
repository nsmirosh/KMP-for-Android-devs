package com.learnkmp.newsapp.domain.model

enum class Category(val value: String) {
    BUSINESS("business"),
    CRIME("crime"),
    DOMESTIC("domestic"),
    EDUCATION("education"),
    ENTERTAINMENT("entertainment"),
    ENVIRONMENT("environment"),
    FOOD("food"),
    HEALTH("health"),
    LIFESTYLE("lifestyle"),
    POLITICS("politics"),
    SCIENCE("science"),
    SPORTS("sports"),
    TECHNOLOGY("technology"),
    TOP("top"),
    TOURISM("tourism"),
    WORLD("world"),
    OTHER("other");

    override fun toString(): String {
        return value.replaceFirstChar { it.uppercase() }
    }
}