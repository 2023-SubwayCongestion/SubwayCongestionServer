package hanium.subway_congestion.FCM;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import hanium.subway_congestion.User.User;
import hanium.subway_congestion.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
@Service
public class FCMService {
    private final FirebaseMessaging firebaseMessaging;
    private final UserRepository userRepository;

    public String sendNotification(FCMRequestDto requestDto) throws ExecutionException, InterruptedException {
        List<User> userList = userRepository.findAllUser();
        List<String> successTokens = new ArrayList<>();
        List<String> failedTokens = new ArrayList<>();

        if (userList.isEmpty()) {
            return "서버에 저장된 유저의 FirebaseToken이 존재하지 않습니다";
        }

        for (User it : userList) {
            try {
                Notification notification = Notification.builder()
                        .setTitle(requestDto.getTitle())
                        .setBody(requestDto.getBody())
                        .build();

                Message message = Message.builder()
                        .setToken(it.getToken())
                        .setNotification(notification)
                        .build();

                firebaseMessaging.send(message);
                successTokens.add(it.getToken());
            } catch (FirebaseMessagingException e) {
                e.printStackTrace();
                failedTokens.add(it.getToken());
            }
        }

        saveNotificationToFirebase(requestDto);
        if (failedTokens.isEmpty()) {
            return "모든 알림을 성공적으로 전송했습니다." + successTokens.toString();
        } else {
            return "일부 알림이 실패하였습니다.\n성공적으로 전송된 토큰: " + successTokens.toString() + "\n전송 실패한 토큰: " + failedTokens.toString();
        }
    }

    public void saveNotificationToFirebase(FCMRequestDto requestDto) {
        Firestore db = FirestoreClient.getFirestore();

        Map<String, Object> data = new HashMap<>();
        data.put("title", requestDto.getTitle());
        data.put("body", requestDto.getBody());
        data.put("timestamp", LocalDateTime.now().toString());

        db.collection("notifications").document().set(data);
    }
}