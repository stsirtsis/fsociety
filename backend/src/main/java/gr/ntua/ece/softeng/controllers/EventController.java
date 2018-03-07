package gr.ntua.ece.softeng.controllers;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import gr.ntua.ece.softeng.entities.Event;
import gr.ntua.ece.softeng.entities.Providers;
import gr.ntua.ece.softeng.repositories.EventRepository;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Provider;

@Controller
public class EventController {

    private static String encodeFileToBase64Binary(File file){
        String encodedfile = null;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            fileInputStreamReader.read(bytes);
            encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return encodedfile;
    }
	
	
	@Autowired
	private EventRepository eventRepository;

	@RequestMapping(path="/event_click/{event_id}")
	public synchronized @ResponseBody void countclicks (@PathVariable Long event_id) {

		Event event = eventRepository.findOne(event_id);
		Integer clicks;

		clicks=event.getClicks();
		clicks++;
		event.setClicks(clicks);
		eventRepository.save(event);
	}

	@RequestMapping(path="/eventById/{eventId}")
	public @ResponseBody Event findEvent (@PathVariable Long eventId) {
		Event e=eventRepository.findOne(eventId);
		String uri=e.getPhotoUri();
		String relpath="./upload-dir/";
		File thefile=new File(relpath+uri);
		e.setPhotoBody(encodeFileToBase64Binary(thefile));
		return e;
	}
	
	@RequestMapping(path="/one-event/{eventId}")
	public @ResponseBody Providers findProvider (@PathVariable Long eventId) {
		Event e=eventRepository.findOne(eventId);
		Providers provider=e.getProvider();
		return provider;
		
	}
	

}
