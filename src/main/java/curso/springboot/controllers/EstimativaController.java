package curso.springboot.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import curso.springboot.model.Item;
import curso.springboot.model.Estimativa;
import curso.springboot.repository.ItemRepository;
import curso.springboot.repository.EstimativaRepository;

@Controller
public class EstimativaController {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private EstimativaRepository estimativaRepository;
	
	private ModelAndView andViewEstimativa = new ModelAndView("cadastro/estimativas");

	@GetMapping("/removerestimativa/{idestimativa}")
	public ModelAndView excluir(@PathVariable("idestimativa") Long idestimativa) {

		Item item = estimativaRepository.findById(idestimativa).get().getItem();
		
		estimativaRepository.deleteById(idestimativa);

		andViewEstimativa.addObject("itemobj", item);
		andViewEstimativa.addObject("estimativas", estimativaRepository.getEstimativas(item.getId()));
		return andViewEstimativa;

	}

	
	@GetMapping("/estimativas/{iditem}")
	public ModelAndView estimativas(@PathVariable("iditem") Long iditem) {

		Optional<Item> item = itemRepository.findById(iditem);
		
		andViewEstimativa.addObject("itemobj", item.get());
		andViewEstimativa.addObject("estimativas", estimativaRepository.getEstimativas(iditem));
		return andViewEstimativa;
	}
	
	@PostMapping("**/addestimativaItem/{itemid}")
	public ModelAndView addEstimativaItem(Estimativa estimativa, @PathVariable("itemid") Long itemid) {
		
		Item item = itemRepository.findById(itemid).get();
		estimativa.setItem(item);
		estimativaRepository.save(estimativa);
		
		andViewEstimativa.addObject("itemobj", item);
		andViewEstimativa.addObject("estimativas", estimativaRepository.getEstimativas(itemid));
		
		return andViewEstimativa;
	}

}
