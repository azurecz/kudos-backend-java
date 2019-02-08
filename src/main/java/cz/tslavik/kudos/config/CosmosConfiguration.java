package cz.tslavik.kudos.config;

import com.microsoft.azure.spring.data.cosmosdb.config.AbstractDocumentDbConfiguration;
import com.microsoft.azure.spring.data.cosmosdb.config.DocumentDBConfig;
import com.microsoft.azure.spring.data.cosmosdb.repository.config.EnableDocumentDbRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import cz.tslavik.kudos.repository.cosmos.KudosRepository;

@Configuration
@EnableDocumentDbRepositories(basePackageClasses = KudosRepository.class)
public class CosmosConfiguration extends AbstractDocumentDbConfiguration {

    @Value("${azure.cosmosdb.uri}")
    private String uri;

    @Value("${azure.cosmosdb.key}")
    private String key;

    @Value("${azure.cosmosdb.database}")
    private String dbName;

    public DocumentDBConfig getConfig() {
        return DocumentDBConfig.builder(uri, key, dbName).build();
    }
}
