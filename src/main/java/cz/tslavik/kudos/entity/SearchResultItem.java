package cz.tslavik.kudos.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SearchResultItem {

    @JsonProperty("@search.score")
    private Double searchScore;

    private String usernumber;
    private String firstname;
    private String surname;
    private String id;
    private String email;
    private String username;
    private String departmentname;
    private String rid;

}
