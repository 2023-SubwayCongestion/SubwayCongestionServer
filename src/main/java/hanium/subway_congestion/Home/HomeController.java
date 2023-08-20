package hanium.subway_congestion.Home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        
        return "home";
    }

    @GetMapping("/result")
    public String result(){
        return "result";
    }
}
