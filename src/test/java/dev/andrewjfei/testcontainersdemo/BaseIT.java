package dev.andrewjfei.testcontainersdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseIT {

    private static String POSTGRESQL_DOCKER_IMAGE = "postgres:latest";

    @Autowired
    protected TestRestTemplate testRestTemplate;

    @Container
    public static PostgreSQLContainer postgreSqlContainer = new PostgreSQLContainer(POSTGRESQL_DOCKER_IMAGE)
            .withUsername("andrewjfei")
            .withPassword("password")
            .withDatabaseName("test");

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSqlContainer::getUsername);
        registry.add("spring.datasource.password", postgreSqlContainer::getPassword);
    }
}
