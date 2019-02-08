package cz.tslavik.kudos.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microsoft.azure.spring.data.cosmosdb.core.mapping.Document;
import com.microsoft.azure.spring.data.cosmosdb.core.mapping.PartitionKey;
import lombok.*;
import org.springframework.data.annotation.Id;

@Document(collection = "kudos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Kudos {

    @PartitionKey
    private String receiver;
    @Id
    private String id;
    @JsonProperty("Kudosprice")
    private String description;
    @JsonProperty("yearbudget")
    private Category category;
    private String author;
    

}
