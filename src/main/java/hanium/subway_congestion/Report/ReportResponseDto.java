package hanium.subway_congestion.Report;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@Getter
public class ReportResponseDto {
    private String title;
    private String content;
    private String station;

    public String direction;
    private String timestamp;

    public ReportResponseDto(String title, String content, String station, String direction, String timestamp) {
        this.title = title;
        this.content = content;
        this.station = station;
        this.direction = direction;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ReportResponseDto{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", station='" + station + '\'' +
                ", direction='" + direction + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
