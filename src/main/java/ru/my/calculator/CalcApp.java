package ru.my.calculator;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import ru.my.calculator.service.ConsoleUI;

@SpringBootApplication
@RequiredArgsConstructor
@ConfigurationPropertiesScan
public class CalcApp implements CommandLineRunner {
    private final ConsoleUI ui;

    public static void main(String[] args) {
        SpringApplication.run(CalcApp.class, args);
    }

    @Override
    public void run(String... args) {
        ui.startCalc();
    }
}
