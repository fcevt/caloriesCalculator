package ru.my.calculator.service;

import org.springframework.stereotype.Component;
import ru.my.calculator.model.FinishedDish;
import ru.my.calculator.model.Product;

import java.util.Scanner;

@Component
public class ConsoleUI {
    private final Scanner input;
    private final Calculator calculator;

    public ConsoleUI(Calculator calculator) {
        this.calculator = calculator;
        this.input = new Scanner(System.in);
    }

    public void startCalc() {
        while (true) {
            System.out.println("Что Хотите сделать?");
            System.out.println("1 - добавить новый продукт");
            System.out.println("2 - добавить продукт из списка сохраненных");
            System.out.println("3 - посчитать КБЖУ готового блюда");
            System.out.println("4 - удалить последний добавленный продукт");
            System.out.println("0 - закончить работу");
            int choice = input.nextInt();
            switch (choice) {
                case 1 -> addNewProd();
                case 2 -> addProdFromList();
                case 3 -> calculate();
//                case 4 -> deleteLast();
                case 0 -> {
                    return;
                }
                default -> System.out.println("В меню нет варианта с таким номером");
            }
        }
    }

//    private void deleteLast() {
//        calculator.deleteLast();
//        System.out.println("Последний добавленный продукт удален из списка");
//    }

    private void addNewProd() {
        double weight;
        double proteins;
        double fats;
        double carbohydrates;
        System.out.println("Введите вес продукта");
        weight = input.nextDouble();
        System.out.println("Введите количество белка в 100г продукта");
        proteins = input.nextDouble();
        System.out.println("Введите количество жиров в 100г продукта");
        fats = input.nextDouble();
        System.out.println("Введите количество углеводов в 100г продукта");
        carbohydrates = input.nextDouble();
        calculator.addNewProduct(weight, proteins, fats, carbohydrates);
        System.out.println("Продукт добавлен");
        System.out.println();
    }

    private void addProdFromList() {
        String name;
        double weight;
        System.out.println("Введите номер пункта который соответствует нужному продукту либо 0 чтобы вернуться в" +
            " предыдущее меню");
        int i = 1;
        for (Product product : calculator.getProducts()) {
            System.out.println(i + " - " + product.getName());
            i++;
        }
        System.out.println("0 - нет нужного продукта вернуться в предыдущее меню");
        int choice = input.nextInt();
        if (choice > calculator.getProducts().size()) {
            System.out.println("Пункта с таким номером нет, возвращаемся в предыдущее меню");
            return;
        } else if (choice == 0) {
            System.out.println("Возвращаемся");
            return;
        }
        name = calculator.getProducts().get(choice - 1).getName();
        System.out.println("Введите вес продукта");
        weight = input.nextDouble();
        calculator.addProductFromList(name, weight);
        System.out.println("Продукт добавлен");
        System.out.println();
    }

    private void calculate() {
        double weight;
        System.out.println("Введите вес готового блюда либо 0 если общий вес продуктов не изменился");
        weight = input.nextDouble();
        if (weight == 0) {
            weight = calculator.getAllWeight();
        }
        FinishedDish finishedDish = calculator.calculateValueOf100GramsOfReadyMadeDish(weight);
        System.out.println("КБЖУ на сто грамм готового блюда");
        System.out.println("Калорий - " + finishedDish.getTotalCalories());
        System.out.println("Белков - " + finishedDish.getTotalProtein());
        System.out.println("Жиров - " + finishedDish.getTotalFats());
        System.out.println("Углеводов - " + finishedDish.getTotalCarbohydrates());
        calculator.cleanProductList();
        System.out.println("Можно считать следующее блюдо");
    }
}
