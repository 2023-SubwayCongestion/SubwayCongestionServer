package hanium.subway_congestion.Report;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Report {
    private String title;
    private String content;
    private String station;
    public String direction;
    private String timestamp;
    private int status;
}
