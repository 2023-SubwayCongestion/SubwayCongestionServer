package hanium.subway_congestion.FCM;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import hanium.subway_congestion.User.User;
import hanium.subway_congestion.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
@Service
public class FCMService {
    private final FirebaseMessaging firebaseMessaging;
    private final UserRepository userRepository;

    public String sendNotification(FCMRequestDto requestDto) throws ExecutionException, InterruptedException {
        List<User> userList = userRepository.findAllUser();
        List<String> tempList = new ArrayList<>();

        if(!userList.isEmpty()){
            try {
                for (User it : userList){
                    Notification notification = Notification.builder()
                            .setTitle(requestDto.getTitle())
                            .setBody(requestDto.getBody())
                            .build();

                    Message message = Message.builder()
                            .setToken(it.getToken())
                            .setNotification(notification)
                            .build();
                    firebaseMessaging.send(message);
                    tempList.add(it.getToken());
                }
                return "모든 알림을 성공적으로 전송했습니다." + tempList.toString();
            } catch (FirebaseMessagingException e) {
                e.printStackTrace();
                return "모든 알림 보내기 실패했습니다." + tempList.toString();
            }
        }

        return "서버에 저장된 유저의 FirebaseToken이 존재하지 않습니다";
    }
}
