package hanium.subway_congestion.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface UserRepository {
    List<User> findAllUser() throws ExecutionException, InterruptedException;
}
