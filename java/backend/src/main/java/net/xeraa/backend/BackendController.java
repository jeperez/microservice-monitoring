package net.xeraa.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class BackendController {

    private static final Logger log = Logger.getLogger(BackendApplication.class.getName());

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Random random;

    @Value("${APP_BACKEND:#{'http://localhost:8081'}}")
    private String backendUrl;

    @Value("${APP_FRONTEND:#{'http://localhost:8080'}}")
    private String frontendUrl;

    @RequestMapping("/home")
    public String home() {
        log.log(Level.INFO, "You called home");
        return "Called home!";
    }

    @RequestMapping("/call-bad")
    public String callBad() throws InterruptedException {
        String callUrl = frontendUrl + "/bad";
        log.log(Level.INFO, "Calling " + callUrl);
        Thread.sleep(this.random.nextInt(2000));
        return restTemplate.getForObject(callUrl, String.class);
    }

    @RequestMapping("/call-nested")
    public String callNested() throws InterruptedException {
        String callUrl = frontendUrl + "/call";
        log.log(Level.INFO, "Calling " + callUrl);
        Thread.sleep(this.random.nextInt(1000));
        return restTemplate.getForObject(callUrl, String.class);
    }

}
