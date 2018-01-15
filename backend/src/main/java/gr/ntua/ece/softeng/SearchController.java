package gr.ntua.ece.softeng;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import gr.ntua.ece.softeng.E;
import gr.ntua.ece.softeng.ERepository;

@Controller  
@RequestMapping(path="/search")
public class SearchController {

	@Autowired
	private ERepository eRepository;
	
	
	@GetMapping(path="/text")
	public @ResponseBody List<String> textsearch(@RequestParam String text)
	{
		TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingAny(text);
		List<E> results=eRepository.findAllByOrderByScoreDesc(criteria);
		List<String> names = results.stream()
                .map(E::getEventname)
                .collect(Collectors.toList());
		return names;
	}
	
	
	
	
}
