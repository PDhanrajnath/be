package com.greencommute.jobmanagementservice.persistence.details;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationVersion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
@Configuration

public class TestPostgresConfiguration {

  @Container
  private PostgreSQLContainer fPostgreSQLContainer;

  @Bean("testDataSource")
  @Profile("test")
  DataSource configureTestDataSource(
      @Value("${test.db.container}") final String postgresContainer,
      @Value("${test.db.schema}") final String schemaName) {

	  DockerImageName myImage = DockerImageName.parse(postgresContainer).asCompatibleSubstituteFor("postgres");
    fPostgreSQLContainer = new PostgreSQLContainer(myImage);

    
    
    // See if there is a db-creation.sql script
    var createScript = this.getClass().getClassLoader().getResource("db_creation.sql");
    if (createScript != null) {
      fPostgreSQLContainer.withInitScript("db_creation.sql");
    }

    fPostgreSQLContainer.start();

    // substring(5) strips the "jdbc:" from the front of the URI, otherwise .create() fails
//    var queryParams = URI.create(jdbcUrl.substring(5));
    var url = fPostgreSQLContainer.getJdbcUrl()
        + "&password=" + fPostgreSQLContainer.getPassword()
        + "&user=" + fPostgreSQLContainer.getUsername();
//        + "&" + queryParams.getRawQuery();

    var dataSource = DataSourceBuilder.create()
        .url(fPostgreSQLContainer.getJdbcUrl())
        .username(fPostgreSQLContainer.getUsername())
        .password(fPostgreSQLContainer.getPassword())
        .build();

    runFlywayMigrations(dataSource, schemaName);

    assertTrue(fPostgreSQLContainer.isRunning());

    return dataSource;
  }

  private void runFlywayMigrations(final DataSource dataSource, final String schemaName) {
    Flyway flyway = Flyway.configure()
        .dataSource(dataSource)
        .baselineOnMigrate(true)
        .baselineVersion("0")
        .locations("db/migration")
        .schemas(schemaName)
        .target(MigrationVersion.LATEST)
        .outOfOrder(true)
        .validateOnMigrate(true)
        .load();
    flyway.migrate();
  }
}