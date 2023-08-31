package hanium.subway_congestion.Korail;

import hanium.subway_congestion.Report.Report;
import hanium.subway_congestion.Report.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
@RequiredArgsConstructor
public class KorailController {

    private final KorailService korailService;
    @GetMapping("/korail")
    public String getKorail(Model model) throws ExecutionException, InterruptedException {
        List<Train> trains = korailService.getTrains();
        model.addAttribute("list", trains);
        return "korail";
    }
}
