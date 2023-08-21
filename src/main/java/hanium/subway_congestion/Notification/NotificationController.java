package hanium.subway_congestion.Notification;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class NotificationController {

    @GetMapping("/notification")
    public String getNotification(){
        return "notification";
    }
}
