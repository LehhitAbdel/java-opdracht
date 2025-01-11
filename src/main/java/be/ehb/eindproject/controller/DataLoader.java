package be.ehb.eindproject.controller;

import be.ehb.eindproject.model.Toestellen;
import be.ehb.eindproject.model.ToestelRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final ToestelRepo toestelRepo;

    public DataLoader(ToestelRepo toestelRepo) {
        this.toestelRepo = toestelRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        if (toestelRepo.count() == 0) {
            Toestellen toestel1 = new Toestellen();
            toestel1.setName("Truss System");
            toestel1.setCategory("podium");
            toestel1.setUitleg("Modular structure to hang lighting and speakers");
            toestel1.setImage("");

            Toestellen toestel2 = new Toestellen();
            toestel2.setName("Stage Decks");
            toestel2.setCategory("podium");
            toestel2.setUitleg("Floor elements to build a raised stage");
            toestel2.setImage("");

            Toestellen toestel3 = new Toestellen();
            toestel3.setName("Backdrops");
            toestel3.setCategory("podium");
            toestel3.setUitleg("Background curtains for decoration or branding during events.");
            toestel3.setImage("");

            Toestellen toestel4 = new Toestellen();
            toestel4.setName("Stage Stairs");
            toestel4.setCategory("podium");
            toestel4.setUitleg("Stage stairs for safe access to the platform.");
            toestel4.setImage("");

            Toestellen toestel5 = new Toestellen();
            toestel5.setName("Moving Head Lights");
            toestel5.setCategory("light");
            toestel5.setUitleg("Rotating lights for dynamic light shows.");
            toestel5.setImage("");

            Toestellen toestel6 = new Toestellen();
            toestel6.setName("LED Light Panels ");
            toestel6.setCategory("light");
            toestel6.setUitleg("Energy-efficient panels for general lighting.");
            toestel6.setImage("stage-platform.jpg");

            Toestellen toestel7 = new Toestellen();
            toestel7.setName("Fog Machine");
            toestel7.setCategory("light");
            toestel7.setUitleg("Smoke machine for visual effects during performances.");
            toestel7.setImage("");

            Toestellen toestel8 = new Toestellen();
            toestel8.setName("DMX Controller");
            toestel8.setCategory("light");
            toestel8.setUitleg("Control unit for all connected lighting equipment.");
            toestel8.setImage("");

            Toestellen toestel9 = new Toestellen();
            toestel9.setName("PA Speakers");
            toestel9.setCategory("audio");
            toestel9.setUitleg("Sound boxes to amplify speech and music.");
            toestel9.setImage("");

            Toestellen toestel10 = new Toestellen();
            toestel10.setName("XLR Cables");
            toestel10.setCategory("audio");
            toestel10.setUitleg("Professional audio cables for connecting microphones and speakers.");
            toestel10.setImage("");

            toestelRepo.save(toestel1);
            toestelRepo.save(toestel2);
            toestelRepo.save(toestel3);
            toestelRepo.save(toestel4);
            toestelRepo.save(toestel5);
            toestelRepo.save(toestel6);
            toestelRepo.save(toestel7);
            toestelRepo.save(toestel8);
            toestelRepo.save(toestel9);
            toestelRepo.save(toestel10);

            System.out.println("Dummy data inserted!");
        }
    }
}