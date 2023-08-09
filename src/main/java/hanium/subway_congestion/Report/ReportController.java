package hanium.subway_congestion.Report;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/report")
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/all")
    public ResponseEntity<List<Report>> getUsers() throws ExecutionException, InterruptedException {
        List<Report> reports = reportService.getReports();
        return ResponseEntity.ok().body(reports);

    }
}
