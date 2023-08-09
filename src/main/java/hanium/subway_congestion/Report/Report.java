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
    private String userId;
    private String title;
    private String content;
    private Date createdTime;
}
