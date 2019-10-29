package eu.teama.drdaycare;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DrdaycareApplication {
    private static final Logger logger = LoggerFactory.getLogger(DrdaycareApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DrdaycareApplication.class, args);
        logger.info("Application started");
    }
}
