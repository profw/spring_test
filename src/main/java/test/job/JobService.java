package test.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import test.service.ZooService;

@Service
public class JobService {
    private final ZooService zooService;

    @Autowired
    public JobService(ZooService zooService) {
        this.zooService = zooService;
    }

    //@Scheduled(cron = "1 * * * * *")
    @Scheduled(fixedDelay = 3000)
    public void voice() {
        zooService.voice();
    }

}
