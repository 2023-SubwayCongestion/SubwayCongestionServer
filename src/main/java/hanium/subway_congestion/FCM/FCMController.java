package hanium.subway_congestion.FCM;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/notification")
public class FCMController {
    private final FCMService fcmService;

    @PostMapping
    public String sendNotification(@RequestBody FCMRequestDto requestDto) throws ExecutionException, InterruptedException {
        String result = fcmService.sendNotification(requestDto);
        System.out.println(result);
        return "result";
    }
}
