package hanium.subway_congestion.Report;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface ReportRepository {
    List<Report> findAllReport() throws ExecutionException, InterruptedException;
    ReportResponseDto findById(String id) throws ExecutionException, InterruptedException;
    boolean updateRead(String id) throws ExecutionException, InterruptedException;
}
