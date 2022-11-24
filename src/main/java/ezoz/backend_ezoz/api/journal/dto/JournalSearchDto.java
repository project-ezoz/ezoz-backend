package ezoz.backend_ezoz.api.journal.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class JournalSearchDto {

    @NotBlank
    private String type;

    @NotBlank
    private int offset;
}
