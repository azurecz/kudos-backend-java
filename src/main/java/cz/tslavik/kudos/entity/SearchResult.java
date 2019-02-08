package cz.tslavik.kudos.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SearchResult {

    @JsonProperty("@odata.context")
    private String context;

    @JsonProperty("@odata.nextLink")
    private String nextLink;

    @JsonProperty("value")
    private ArrayList<SearchResultItem> searchResultItems;
}
