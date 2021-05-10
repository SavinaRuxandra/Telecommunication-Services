package intrusii.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class IDCardDto {
    private String cnp;
    private String nationality;
    private String placeOfBirth;
    private String residence;
    private String sex;
    private String series;
    private String number;
}
