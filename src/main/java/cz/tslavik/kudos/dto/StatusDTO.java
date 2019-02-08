package cz.tslavik.kudos.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusDTO {

    private String messageCode;

    private String description;

    public StatusDTO(String messageCode, String description){
        this.messageCode = messageCode;
        this.description = description;
    }
}
