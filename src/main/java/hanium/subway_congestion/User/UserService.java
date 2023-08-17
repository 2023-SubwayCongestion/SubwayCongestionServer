package hanium.subway_congestion.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    List<User> getAllUsers() throws ExecutionException, InterruptedException {
        return userRepository.findAllUser();
    }
}
