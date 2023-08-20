package hanium.subway_congestion.Report;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class ReportFirebaseRepository implements ReportRepository {
    public static final String COLLECTION_NAME = "personalReport";
    public static List<QueryDocumentSnapshot> items = null;

    public List<Report> findAllReport() throws ExecutionException, InterruptedException {
        List<Report> list = new ArrayList<>();
        items = getDocuments();
        for (QueryDocumentSnapshot document : items) {
            Report report = document.toObject(Report.class);
            report.setId(document.getId());
            list.add(report);
        }
        return list;
    }

    @Override
    public ReportResponseDto findById(String id) throws ExecutionException, InterruptedException {
        if(items == null) items = getDocuments();

        for (QueryDocumentSnapshot document : items) {
            if (document.getId().equals(id)) {
                Report report = document.toObject(Report.class);
                return ReportResponseDto.builder()
                        .title(report.getTitle())
                        .content(report.getContent())
                        .station(report.getStation())
                        .direction(report.getDirection())
                        .timestamp(report.getTimestamp())
                        .build();
            }
        }
        return null;
    }

    public List<QueryDocumentSnapshot> getDocuments() throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();
        return future.get().getDocuments();
    }

}
