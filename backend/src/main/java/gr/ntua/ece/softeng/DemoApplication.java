package gr.ntua.ece.softeng;

import gr.ntua.ece.softeng.entities.E;
import gr.ntua.ece.softeng.entities.Role;
import gr.ntua.ece.softeng.entities.User;
import gr.ntua.ece.softeng.repositories.ERepository;
import gr.ntua.ece.softeng.repositories.EventRepository;
import gr.ntua.ece.softeng.repositories.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.awt.Event;
import java.util.Arrays;

import javax.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import gr.ntua.ece.softeng.services.StorageService;

@SpringBootApplication
@EntityScan(basePackages = {"gr.ntua.ece.softeng.entities"})
@EnableJpaRepositories(basePackages = {"gr.ntua.ece.softeng.repositories"})
public class DemoApplication implements CommandLineRunner{

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ERepository eRepository;
    
    @Autowired
    private EventRepository eventRepository;
    
    
		@Resource
		StorageService storageService;
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

		@Override
		public void run(String... arg) throws Exception {
		//	storageService.deleteAll();
			storageService.init();
		}
		
	
	
    @Bean
    InitializingBean sendDatabase() {
        return () -> {
        	
        	eventRepository.findAll().forEach(e -> {
        	
        		Long myId = e.getId();
				String id = Long.toString(myId);

				String eventname = e.getEventname();
				String description = e.getDescription();
				String Area = e.getArea();
				String StreetName = e.getStreetName().replaceAll("\\s+","");
				Integer StreetNumber = e.getStreetNumber();
				Integer AgeGroup = e.getAgeGroup();
				Integer capacity = e.getCapacity();
				Integer price = e.getPrice();
				Integer category = e.getCategory();
				String company_name = e.getProvider().getcompanyName();
				String date=e.getDate().toString();
				String state="OPEN";
				String photoUri=e.getPhotoUri();
				String photoBody=e.getPhotoBody();
        		
                eRepository.save(new E(id, eventname, description , Area , StreetName , StreetNumber , AgeGroup , capacity , price , category , company_name, null, null, date, state, photoUri, photoBody));
            }
        	);

        	
        	
            String password = org.apache.commons.codec.digest.DigestUtils.sha256Hex("admin");
            userRepository.save(new User("admin", password, Arrays.asList(new Role("ADMIN"))));
        };
    }
}
