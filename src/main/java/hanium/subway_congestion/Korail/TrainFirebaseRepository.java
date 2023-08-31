package hanium.subway_congestion.Korail;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import hanium.subway_congestion.Report.Report;
import hanium.subway_congestion.Report.ReportRepository;
import hanium.subway_congestion.Report.ReportResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class TrainFirebaseRepository implements KorailRepository {
    public static final String COLLECTION_NAME = "realTimeTrain";
    public static List<QueryDocumentSnapshot> items = null;

    public List<Train> findAllTrain() throws ExecutionException, InterruptedException {
        List<Train> list = new ArrayList<>();
        items = getDocuments();
        for (QueryDocumentSnapshot document : items) {
            Train train = document.toObject(Train.class);
            train.setId(document.getId());
            list.add(train);
        }
        return list;
    }

    @Override
    public TrainResponseDto findById(String id) throws ExecutionException, InterruptedException {
        if(items == null) items = getDocuments();

        for (QueryDocumentSnapshot document : items) {
            if (document.getId().equals(id)) {
                Train train = document.toObject(Train.class);
                return TrainResponseDto.builder()
                        .congestion(train.getCongestion())
                        .date(train.getDate())
                        .destination(train.getDestination())
                        .finaldestination(train.getFinaldestination())
                        .hocha(train.getHocha())
                        .hosun(train.getHosun())
                        .source(train.getSource())
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
