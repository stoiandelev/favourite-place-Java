package com.example.favouritePlaceInTheWorld.web.schedule;


import com.example.favouritePlaceInTheWorld.model.entity.UserEntity;
import com.example.favouritePlaceInTheWorld.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;



@Component
public class ScheduleUnActiveUser {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleUnActiveUser.class);

    private final UserRepository userRepository;

    public ScheduleUnActiveUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Every 10 sec user cron site for test -> "*/10 * * * * */// */10 1 1 1 1 * "
    @Scheduled(cron = "*/10 1 1 1 1 *")
    public void showTimeWithCron() {
        List<UserEntity> isNotActive = userRepository.findAllByActive(false);
        for (UserEntity userEntity : isNotActive) {
            long id = userEntity.getId();
            userRepository.deleteById(id);
        }
    }

}

