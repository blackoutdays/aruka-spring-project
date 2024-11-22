package com.mjc.school.aruka.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mjc.school.aruka.logic.ShopService;
import com.mjc.school.aruka.model.Author;
import com.mjc.school.aruka.model.AuthorBean;
import com.mjc.school.aruka.model.Book;
import com.mjc.school.aruka.model.BookBean;

@Controller
public class AdminController {

	@GetMapping("/addAuthor")
	public ModelAndView addAuthor(AuthorBean authorBean, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView("author");
		mv.addObject("authorBean", authorBean);
		return mv;
	}

	@PostMapping("/addAuthor")
	public ModelAndView addAuthorPost(@Valid AuthorBean authorBean, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView("author");
		mv.addObject("authorBean", authorBean);
		String successMessage;
		if (!bindingResult.hasErrors()) {
			ShopService shopService = new ShopService();
			Author author = new Author(authorBean.getFirstName(), authorBean.getLastName());
			shopService.addAuthor(author);
			successMessage = "Author added";
			mv.addObject("successMessage", successMessage);
			mv.addObject("authorBean", new AuthorBean());
		} else {
			mv.addObject("authorBean", authorBean);
		}
		return mv;
	}

	@GetMapping("/addBook")
	public ModelAndView addBook(BookBean bookBean, BindingResult bindingResult, HttpSession session) {
		List<Author> authors = new ArrayList<>();
		session.setAttribute("authors", authors);
		ModelAndView mv = new ModelAndView("book");
		mv.addObject("bookBean", bookBean);
		mv.addObject("authors", getAllAuthors());
		mv.addObject("languages", getLanguages());
		mv.addObject("selectedAuthors", authors);
		return mv;
	}

	private Map<String, String> getAllAuthors() {
		Map<String, String> authorMap = new HashMap<>();
		ShopService shopService = new ShopService();
		List<Author> list = shopService.getAllAuthors();
		for (Author author : list) {
			authorMap.put(Integer.toString(author.getId()), author.getName());
		}
		return authorMap;
	}

	private Map<String, String> getLanguages() {
		Map<String, String> languages = new HashMap<>();
		languages.put("Deutsch", "Deutsch");
		languages.put("English", "English");
		languages.put("Francais", "Fran" + String.valueOf('\u00E7') + "ais");
		return languages;
	}

	@PostMapping("/addBook")
	public ModelAndView addBookPost(@RequestParam(value = "button") String selectedButton, @Valid BookBean bookBean,
			BindingResult bindingResult, HttpSession session) {
		ShopService shopService = new ShopService();
		ModelAndView mv = new ModelAndView("book");
		if (selectedButton.equals("addAuthor")) {
			List<Author> authors = (List<Author>) session.getAttribute("authors");
			int authorId = bookBean.getAuthorId();
			authors.add(shopService.findAuthor(authorId));
			mv.addObject("bookBean", bookBean);
			mv.addObject("authors", getAllAuthors());
			mv.addObject("languages", getLanguages());
			mv.addObject("selectedAuthors", authors);
			mv.addObject("successMessage", "Author added");
		} else if (selectedButton.equals("addBook")) {
			mv.addObject("bookBean", bookBean);
			if (!bindingResult.hasErrors()) {
				Book book = new Book(bookBean.getTitle(), bookBean.getIsbn13(), bookBean.getPublisher(),
						bookBean.getNumberOfPages(), bookBean.getPublishedDate(), bookBean.getLanguage(),
						bookBean.getPrice());
				List<Author> authors = (List<Author>) session.getAttribute("authors");
				book.setAuthors(authors);
				try {
					shopService.addBook(book);
					mv.addObject("successMessage", "Book added");
					mv.addObject("bookBean", new BookBean());
					mv.addObject("authors", getAllAuthors());
					mv.addObject("languages", getLanguages());
					mv.addObject("selectedAuthors", new ArrayList<Author>());
					session.setAttribute("authors", new ArrayList<Author>());
				} catch (Exception e) {
					mv.addObject("errorMessage", "Book with this ISBN 13 already exists");
					mv.addObject("bookBean", bookBean);
					mv.addObject("languages", getLanguages());
					mv.addObject("selectedAuthors", authors);
				}
			} else { // validation errors
				mv.addObject("bookBean", bookBean);
				mv.addObject("languages", getLanguages());
				List<Author> authors = (List<Author>) session.getAttribute("authors");
				mv.addObject("selectedAuthors", authors);
			}

		} else { // remove author
			List<Author> authors = (List<Author>) session.getAttribute("authors");
			removeAuthor(Integer.parseInt(selectedButton), authors);
			mv.addObject("successMessage", "Author removed");
			mv.addObject("bookBean", new BookBean());
			mv.addObject("authors", getAllAuthors());
			mv.addObject("languages", getLanguages());
			mv.addObject("selectedAuthors", authors);
		}
		return mv;
	}

	public void removeAuthor(int id, List<Author> authors) {
		for (Author author : authors) {
			if (author.getId() == id) {
				authors.remove(author);
				break;
			}
		}
	}

}
