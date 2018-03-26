package ru.vsu.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.vsu.services.serviceImpl.ReferenceService;
import ru.vsu.services.serviceImpl.SessionService;

@Component
public class ScheduledTasks {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ReferenceService referenceService;

    @Scheduled(cron = "0 * * ? * *")
    //0 */10 * ? * *
    //@Scheduled(cron = "0 0 0/24 ? * *")
    public void fixedDelaySchedule() {
        System.out.println("i do deleteAllOutdated");
        sessionService.deleteAllOutdated();
    }
}
