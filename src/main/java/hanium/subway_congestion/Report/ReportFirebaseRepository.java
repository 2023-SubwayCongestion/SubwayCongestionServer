package hanium.subway_congestion.Report;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
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

    @Override
    public boolean updateRead(String id) throws ExecutionException, InterruptedException {
        if(items == null) items = getDocuments();

        Firestore db = FirestoreClient.getFirestore();

        for (QueryDocumentSnapshot document : items) {
            if (document.getId().equals(id)) {
                Report report = document.toObject(Report.class);

                // status가 0인 경우만 업데이트 진행
                if(report.getStatus() == 0){
                    report.setStatus(1); // 상태 변경
                    DocumentReference docRef = db.collection(COLLECTION_NAME).document(id);
                    ApiFuture<WriteResult> writeResult = docRef.update("status", report.getStatus());
                    return true;
                }
            }
        }

        return false;
    }


    public List<QueryDocumentSnapshot> getDocuments() throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();
        return future.get().getDocuments();
    }

}
