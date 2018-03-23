package ru.vsu.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.vsu.services.serviceImpl.ReferenceService;
import ru.vsu.services.serviceImpl.SessionService;

@Component
public class ScheduledTasks {
    //Id который означает, что ссылки нет
    private static final long VOID_REFERENCE_ID = 0;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ReferenceService referenceService;


    @Scheduled(cron = "0 * * ? * *")// каждая минута
    public void clearReferencesInDatabase() {
        try {
            System.out.println("perform reference table cleaning");
            referenceService.deleteByReferenceId(VOID_REFERENCE_ID);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Scheduled(cron = "0 * * ? * *")
    //0 */10 * ? * *
    //@Scheduled(cron = "0 0 0/24 ? * *")
    public void fixedDelaySchedule() {
        System.out.println("i do deleteAllOutdated");
        sessionService.deleteAllOutdated();
    }
}
