package hanium.subway_congestion.Korail;

import hanium.subway_congestion.Korail.Train;
import hanium.subway_congestion.Korail.TrainResponseDto;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface KorailRepository {
    List<Train> findAllTrain() throws ExecutionException, InterruptedException;
    TrainResponseDto findById(String id) throws ExecutionException, InterruptedException;
}
