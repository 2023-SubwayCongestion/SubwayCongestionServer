package hanium.subway_congestion.FCM;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import hanium.subway_congestion.User.User;
import hanium.subway_congestion.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
@Service
public class FCMService {
    private final FirebaseMessaging firebaseMessaging;
    private final UserRepository userRepository;

    public String sendNotification(FCMRequestDto requestDto) throws ExecutionException, InterruptedException {

        String token = "ckIV8lXtQw-FJRc8CYUlC6:APA91bFXavpsJhExAyQVhHAMeZTrb2FM-Hj_c5Pk6TA5BhPnJ5CPcvlj9OHzhZAQprKFFk8r7Nywm53Q6gchO2kwdBP5j2GzBvHyDaZO1CuAGGhU-S3z2J0jpiz6wSzI3JTl_lcIQ5K3";
        Notification notification = Notification.builder()
                .setTitle(requestDto.getTitle())
                .setBody(requestDto.getBody())
                .build();

        Message message = Message.builder()
                .setToken(token)
                .setNotification(notification)
                .build();

        try {
            firebaseMessaging.send(message);
            return "알림을 성공적으로 전송했습니다. user=" + token;
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            return "알림 보내기 실패했습니다. user=" + token;
        }

//        List<User> userList = userRepository.findAllUser();

//        if(!userList.isEmpty()){
//            for (User it : userList){
//                Notification notification = Notification.builder()
//                        .setTitle(requestDto.getTitle())
//                        .setBody(requestDto.getBody())
//                        .build();
//
//                Message message = Message.builder()
//                        .setToken(it.getFirbaseToken())
//                        .setNotification(notification)
//                        .build();
//
//                try {
//                    firebaseMessaging.send(message);
//                    return "알림을 성공적으로 전송했습니다. user=" + it.getEmail();
//                } catch (FirebaseMessagingException e) {
//                    e.printStackTrace();
//                    return "알림 보내기 실패했습니다. user=" + it.getEmail();
//                }
//            }
//        }

//        return "서버에 저장된 유저의 FirebaseToken이 존재하지 않습니다";
    }
}
