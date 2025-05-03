package ru.my.calculator.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Product {
    String name;
    double calories;
    double protein;
    double fats;
    double carbohydrates;
    double weight;
}

