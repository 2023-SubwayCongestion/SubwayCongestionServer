package hanium.subway_congestion.Korail;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class KorailService {
    private final KorailRepository korailRepository;

    List<Train> getTrains() throws ExecutionException, InterruptedException {
        return korailRepository.findAllTrain();
    }

    TrainResponseDto findTrain(String trainId) throws ExecutionException, InterruptedException {
        return korailRepository.findById(trainId);
    }
}
