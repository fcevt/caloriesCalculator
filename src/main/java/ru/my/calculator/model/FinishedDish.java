package ru.my.calculator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FinishedDish {
    double totalCalories;
    double totalProtein;
    double totalFats;
    double totalCarbohydrates;
}
