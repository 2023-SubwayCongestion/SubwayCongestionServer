package hanium.subway_congestion.FCM;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/notification")
public class FCMController {
    private final FCMService fcmService;

    @PostMapping
    public String sendNotification(FCMRequestDto requestDto, Model model) throws ExecutionException, InterruptedException {
        String result = fcmService.sendNotification(requestDto);
        model.addAttribute("result", result);
        return "result";
    }
}
