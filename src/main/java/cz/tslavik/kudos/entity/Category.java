package cz.tslavik.kudos.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microsoft.azure.spring.data.cosmosdb.core.mapping.Document;
import com.microsoft.azure.spring.data.cosmosdb.core.mapping.PartitionKey;
import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Category {


    @Id
    private String id;
    private String description;

}
