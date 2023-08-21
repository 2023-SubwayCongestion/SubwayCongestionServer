package hanium.subway_congestion.FCM;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FCMRequestDto {
    private Long targetUserId;
    private String title;
    private String body;

    @Builder
    public FCMRequestDto(Long targetUserId, String title, String body) {
        this.targetUserId = targetUserId;
        this.title = title;
        this.body = body;
    }
}
