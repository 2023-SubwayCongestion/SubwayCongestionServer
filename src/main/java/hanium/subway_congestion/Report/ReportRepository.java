package hanium.subway_congestion.Report;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface ReportRepository {
    List<Report> findAllReport() throws ExecutionException, InterruptedException;
}
