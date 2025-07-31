package ru.my.calculator.service;

import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.my.calculator.config.ProductListConfig;
import ru.my.calculator.model.FinishedDish;
import ru.my.calculator.model.Product;

import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class Calculator {
    private final List<Product> products;
    private final List<Product> productList;

    public Calculator(ProductListConfig productListConfig) {
        this.products = productListConfig.getProductList();
        this.productList = new ArrayList<>();
    }


    private double getAllProtein() {
        double proteins = 0.0;
        for (Product product : productList) {
            proteins += product.getProtein();
        }
        return proteins;
    }

    private double getAllFats() {
        double fats = 0.0;
        for (Product product : productList) {
            fats += product.getFats();
        }
        return fats;
    }

    private double getAllCarbohydrates() {
        double carbohydrates = 0.0;
        for (Product product : productList) {
            carbohydrates += product.getCarbohydrates();
        }
        return carbohydrates;
    }

    private double getAllCalories() {
        double calories = 0.0;
        for (Product product : productList) {
            calories += product.getCalories();
        }
        return calories;
    }

    public double getAllWeight() {
        double weight = 0.0;
        for (Product product : productList) {
            weight += product.getWeight();
        }
        return weight;
    }

    public double productCaloriesCounter(double protein, double fats, double carbohydrates) {
        return protein * 4 + carbohydrates * 4 + fats * 9;
    }

    public void addNewProduct(double weight, double protein, double fats, double carbohydrates) {
        Product product = new Product();
        product.setWeight(weight);
        product.setProtein(protein * weight / 100);
        product.setFats(fats * weight / 100);
        product.setCarbohydrates(carbohydrates * weight / 100);
        product.setCalories(productCaloriesCounter(product.getProtein(), product.getFats(), product.getCarbohydrates()));
        productList.add(product);
    }

    public void addProductFromList(String name, double weight) {
        Product product = products.stream()
                .filter(product1 -> product1.getName().equals(name))
                .findFirst().get();
        Product product2 = new Product();
        product2.setWeight(weight);
        product2.setProtein(product.getProtein() * weight / 100);
        product2.setFats(product.getFats() * weight / 100);
        product2.setCarbohydrates(product.getCarbohydrates() * weight / 100);
        product2.setCalories(productCaloriesCounter(product2.getProtein(), product2.getFats(), product2.getCarbohydrates()));
        productList.add(product2);
    }

    public FinishedDish calculateValueOf100GramsOfReadyMadeDish(double totalWeight) {
        double allProteins = getAllProtein();
        double allFats = getAllFats();
        double allCarbohydrates = getAllCarbohydrates();
        double allCalories = getAllCalories();
        FinishedDish finishedDish = new FinishedDish(allCalories / totalWeight * 100,
                allProteins / totalWeight * 100,
                allFats / totalWeight * 100,
                allCarbohydrates / totalWeight * 100);
        productList.clear();
        return finishedDish;
    }

    public void cleanProductList() {
        productList.clear();
    }
}
