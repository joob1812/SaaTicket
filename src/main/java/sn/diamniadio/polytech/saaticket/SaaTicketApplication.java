package sn.diamniadio.polytech.saaticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class SaaTicketApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaaTicketApplication.class, args);
    }

}
