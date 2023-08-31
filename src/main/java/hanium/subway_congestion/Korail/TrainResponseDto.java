package hanium.subway_congestion.Korail;



import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.util.Date;
import java.time.LocalDateTime;
@NoArgsConstructor
@Builder
@Getter
public class TrainResponseDto {
    private String congestion;
    private String date;
    private String destination;
    private String finaldestination;
    private int hocha;
    private String hosun;
    private String source;


    public TrainResponseDto(String congestion, String date, String destination, String finaldestination, int hocha, String hosun, String source) {
        this.congestion = congestion;
        this.date = date;
        this.destination = destination;
        this.finaldestination = finaldestination;
        this.hocha = hocha;
        this.hosun = hosun;
        this.source = source;
    }
}
