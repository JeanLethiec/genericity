package com.ceihtel.genericity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.function.Supplier;

@Slf4j
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        var result = 0;

        String operation1 = "addingToVariable";
        int finalResult = operationWrapper(operation1, () -> addTwo(result));

        String operation2 = "loggingStuff";
        operationWrapper(operation2, () -> {
            log.info("Result is now: " + finalResult);
        });

        String operation3 = "loggingPreviousOperations";
        operationWrapper(operation3, () -> {
            log.info("Operations that have been executed: " + operation1 + ", " + operation2);
        });

        log.info("Result = " + result);
    }

    private static int addTwo(int x) {
        return x + 2;
    }

    private static void operationWrapper(String operation, Runnable r) {
        log.info("Start processing " + operation);
        r.run();
        log.info("End processing " + operation);
    }

    private static <T> T operationWrapper(String operation, Supplier<T> r) {
        log.info("Start processing " + operation);
        var result = r.get();
        log.info("End processing " + operation);
        return result;
    }
}
