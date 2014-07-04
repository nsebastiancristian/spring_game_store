package com.sebastian.web.gamestore.controller.tmp;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sebastian.web.gamestore.dao.Company;
import com.sebastian.web.gamestore.dao.Game;
import com.sebastian.web.gamestore.dao.Tag;
import com.sebastian.web.gamestore.dao.Theme;
import com.sebastian.web.gamestore.dao.User;
import com.sebastian.web.gamestore.service.CompaniesService;
import com.sebastian.web.gamestore.service.GamesService;
import com.sebastian.web.gamestore.service.UsersService;

@Controller
public class AdminTmpController {
	
	private CompaniesService companiesService;
	private GamesService gamesService;
	@Autowired
	private UsersService usersService;

	@Autowired
	public void setCompaniesService(CompaniesService companiesService) {
		this.companiesService = companiesService;
	}

	@Autowired
	public void setGamesService(GamesService gamesService) {
		this.gamesService = gamesService;
	}

	@InitBinder(value = "game")
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, true));
		
		binder.registerCustomEditor(List.class, "themes", new CustomCollectionEditor(List.class) {

	            protected Object convertElement(Object element) {
	                int id = 0;

	                if (element instanceof String) {
	                    id = Integer.parseInt((String) element);
	                }
	                
	                Theme theme = gamesService.getThemeFromId(id);
	                
	                return theme;
	            }
	        });
		
		binder.registerCustomEditor(List.class, "tags", new CustomCollectionEditor(List.class) {
			
			protected Object convertElement(Object element) {
				int id = 0;
				
				if (element instanceof String) {
					id = Integer.parseInt((String) element);
				}
				
				Tag tag = gamesService.getTagFromId(id);
				
				return tag;
			}
		});
		
		binder.registerCustomEditor(List.class, "genres", new PropertyEditorSupport() {
			 
		    @Override
		    public void setAsText(String text) throws IllegalArgumentException {
		        List<String> list =  new ArrayList<String>();
		        for (String str : text.split(",")) {
		            list.add(str);
		        }
		        setValue(list);
		    }
		 
		    @Override
		    public String getAsText() {
		        return super.getAsText();
		    }
		});
		
		binder.registerCustomEditor(Company.class, new PropertyEditorSupport() {

			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				Company company = companiesService.getCompanyById(Integer.valueOf(text));
				setValue(company);
			}
			
		});
	}

	private void prepareGameForm(Model model, Game game) {
		model.addAttribute("game", game);

		//================List of Developers=====================
		List<Company> developersList = companiesService.getDevelopers();
		
		//Map the list into a map that has the developer's id as key and the developer's name as value
		Map<String, String> developersMap = new HashMap();
		
		for (Company company : developersList) {
			developersMap.put(String.valueOf(company.getId()), company.getName());
		}
		
		model.addAttribute("developers", developersMap);
		//=======================================================

		//================List of Publishers=====================
		List<Company> publishersList = companiesService.getPublishers();
		
		//Map the list into a map that has the developer's id as key and the developer's name as value
		Map<String, String> publishersMap = new HashMap();
		
		for (Company company : publishersList) {
			System.out.println(company.getName());
			publishersMap.put(String.valueOf(company.getId()), company.getName());
		}
		
		model.addAttribute("publishers", publishersMap);
		//=======================================================

		//================ List of Genres =====================
		List<String> genres = gamesService.getGenres();
		
		model.addAttribute("genres", genres);
		
		//=======================================================

		//================ List of Themes =====================
		List<Theme> themes = gamesService.getThemes();
		
		
		model.addAttribute("themes", themes);
		
		//=======================================================
		
		
		//================ List of Tags =====================
		List<Tag> tags = gamesService.getTags();
		
		
		model.addAttribute("tags", tags);
		
		//=======================================================
		
		//================ List of Platforms =====================
		List<String> platforms = new ArrayList<String>();
		//populate the list with the platforms
		platforms.add("PC/Windows");
		platforms.add("Mac");
		platforms.add("Linux");
		platforms.add("Android");
		
		model.addAttribute("platforms", platforms);
		
		//=======================================================
	}
	
	@RequestMapping(value = "/adminAddGameForm", method = RequestMethod.GET)
	public String adminAddGameForm(Model model) {
		Game game = new Game();
		
		prepareGameForm(model, game);
		
		return "adminAddGameForm";
	}

	@RequestMapping(value = "/adminAddGameForm", method = RequestMethod.POST)
	public String doAdminAddGameForm(@Valid @ModelAttribute("game") Game game, BindingResult result, Model model) {
		if (result.hasErrors()) {
			System.out.println("Form does not validate");
			
			List<ObjectError> errors = result.getAllErrors();

			for (ObjectError error : errors) {
				System.out.println(error);
			}
			
			prepareGameForm(model, game);
			
			return "adminAddGameForm";
		}
		
		
		
		//insert the game into the database
		gamesService.addGame(game);
		model.addAttribute("success", true);

		//-----DEBUGGING----------------------------------------------------------
		System.out.println("Name:" + game.getName());
		System.out.println("Description:" + game.getDescription());
		System.out.println("Added On:" + game.getDateAdded());
		System.out.println("Released On:" + game.getDateReleased());
		System.out.println("Developer:" + game.getDeveloper());
		System.out.println("Publisher:" + game.getPublisher());
		
		for (String genre : game.getGenres()) {
			System.out.println(genre);
		}
		
		for (String platform : game.getPlatforms()) {
			System.out.println(platform);
		}
		System.out.println("Price:" + game.getPrice());
		
		System.out.println("Themes:");
		for (Theme theme : game.getThemes()) {
			System.out.println(theme);
		}
		
		System.out.println("Tags:");
		for (Tag tag : game.getTags()) {
			System.out.println(tag);
		}
		//-------------------------------------------------------------------------
		
		
		
		return "doAdminAddGame";
	}
	
	@RequestMapping(value = "/adminAddUserForm", method = RequestMethod.GET)
	public String adminAddUserForm(Model model) {
		model.addAttribute("user", new User());
		
		return "adminAddUserForm"; 
	}
	
	@RequestMapping(value = "/adminAddUserForm", method = RequestMethod.POST)
	public String doAdminAddUserForm(@ModelAttribute("user") User user, BindingResult result, Model model, @RequestParam(value = "image") MultipartFile image) {
		if (result.hasErrors()) {
			List<ObjectError> errors   = result.getAllErrors();

			for (ObjectError error : errors) {
				System.out.println(error);
			}
		}
		
		System.out.println(user);
		usersService.createUser(user, image);
		
		return "doAdminAddUserForm";
	}
	
	@RequestMapping(value = "/adminAddThemeForm", method = RequestMethod.GET)
	public String adminAddThemeForm(Model model) {
		model.addAttribute("theme", new Theme());
		
		return "adminAddThemeForm"; 
	}
	
	@RequestMapping(value = "/adminAddThemeForm", method = RequestMethod.POST)
	public String adminAddThemeForm(@ModelAttribute("theme") Theme theme, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<ObjectError> errors   = result.getAllErrors();
			
			for (ObjectError error : errors) {
				System.out.println(error);
			}
		}
		
		
		if(gamesService.addTheme(theme))
			return "doAdminAddThemeForm";
		else {
			System.out.println("There is allready another theme like this");
			return "adminAddThemeForm";
		}
		
	}
	
	@RequestMapping(value = "/adminViewGame")
	public String adminViewGame(@RequestParam("id") String id, Model model) {
		Game game = gamesService.getGameDetails(id);
		model.addAttribute("game", game);
		
		return "adminViewGame";
	}
}
