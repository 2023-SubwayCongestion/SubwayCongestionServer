package hanium.subway_congestion.Korail;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;


@NoArgsConstructor
@Getter
@Setter
@ToString
public class Train {
    private String congestion;
    private String date;
    private String destination;
    private String finaldestination;
    private int hocha;
    private String hosun;
    private String source;
    private String id;
}
