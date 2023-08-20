package hanium.subway_congestion.Report;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/")
    public String getUsers(Model model) throws ExecutionException, InterruptedException {
        List<Report> reports = reportService.getReports();
        model.addAttribute("list", reports);
        return "home";
    }
}
