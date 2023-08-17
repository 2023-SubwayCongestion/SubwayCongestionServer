package hanium.subway_congestion;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void init(){
        try{
            FileInputStream serviceAccount =
                    new FileInputStream("src/main/resources/firebase/serviceAccountKey.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://subwaycongestion-default-rtdb.asia-southeast1.firebasedatabase.app")
                    .build();

            FirebaseApp.initializeApp(options);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Bean
    FirebaseMessaging firebaseMessaging() throws IOException{
        ClassPathResource resource = new ClassPathResource("firebase/serviceAccountKey.json");
        InputStream refreshToken = resource.getInputStream();
        FirebaseApp firebaseApp = null;
        List<FirebaseApp> firebaseAppList = FirebaseApp.getApps();

        if (firebaseAppList != null || !firebaseAppList.isEmpty()){
            for (FirebaseApp app : firebaseAppList){
                if(app.getName().equals(firebaseApp.DEFAULT_APP_NAME)) {
                    firebaseApp = app;
                }
            }
        }
        else {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(refreshToken))
                    .build();

            firebaseApp = FirebaseApp.initializeApp();
        }

        return FirebaseMessaging.getInstance(firebaseApp);
    }


}
