package curso.springboot.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import curso.springboot.model.Item;
import curso.springboot.repository.ItemRepository;
import curso.springboot.repository.EstimativaRepository;

@Controller
public class ItemController {

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private EstimativaRepository estimativaRepository;

	private ModelAndView andView = new ModelAndView("cadastro/cadastroitem");

	@RequestMapping(method = RequestMethod.GET, value = "/cadastroitem")
	public ModelAndView inicio() {
		andView.addObject("itemobj", new Item());
		listaitens();
		return andView;
	}

	@RequestMapping(method = RequestMethod.POST, value = "**/salvaritem")
	public ModelAndView salvar(@Valid Item item, BindingResult bindingResult) {

		item.setEstimativas(estimativaRepository.getEstimativas(item.getId()));
		
		if (bindingResult.hasErrors()) {
			Iterable<Item> itensIt = itemRepository.findAll();
			andView.addObject("itemobj", item);
			andView.addObject("itens", itensIt);

			List<String> msg = new ArrayList<String>();

			for (ObjectError error : bindingResult.getAllErrors()) {
				msg.add(error.getDefaultMessage());
			}
			andView.addObject("msg", msg);

			return andView;
		}

		itemRepository.save(item);
		return listaitens();

	}

	@RequestMapping(method = RequestMethod.GET, value = "/listaitens")
	public ModelAndView listaitens() {
		Iterable<Item> itensIt = itemRepository.findAll();
		andView.addObject("itens", itensIt);
		andView.addObject("itemobj", new Item());
		return andView;
	}

	@GetMapping("/editaritem/{iditem}")
	public ModelAndView editar(@PathVariable("iditem") Long iditem) {

		Optional<Item> pessoa = itemRepository.findById(iditem);

		andView.addObject("itemobj", pessoa.get());
		return andView;

	}

	@GetMapping("/removeritem/{iditem}")
	public ModelAndView excluir(@PathVariable("iditem") Long iditem) {

		itemRepository.deleteById(iditem);

		andView.addObject("itens", itemRepository.findAll());
		andView.addObject("itemobj", new Item());
		return andView;

	}

	@PostMapping("**/pesquisaritem")
	public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa) {
		andView.addObject("itens", itemRepository.buscarItemPorNome(nomepesquisa));
		andView.addObject("itemobj", new Item());
		return andView;
	}
}
