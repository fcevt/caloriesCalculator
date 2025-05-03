package ru.my.calculator.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import ru.my.calculator.model.Product;

import java.util.List;

@Getter @Setter
@ToString
@ConfigurationProperties("calories-calculator")
public class ProductListConfig {
    private final List<Product> productList;

    public ProductListConfig(List<Product> products) {
        this.productList = products.stream().toList();
    }
}
