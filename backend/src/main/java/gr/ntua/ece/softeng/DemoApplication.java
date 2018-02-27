package gr.ntua.ece.softeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import gr.ntua.ece.softeng.services.StorageService;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	
	
	@Resource
	StorageService storageService;
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Override
	public void run(String... arg) throws Exception {
		storageService.deleteAll();
		storageService.init();
	}
}
