package com.mjc.school.aruka.logic;

import java.util.List;

import com.mjc.school.aruka.model.Author;
import com.mjc.school.aruka.model.Book;
import com.mjc.school.aruka.persistent.Persistent;

public class ShopService {

	public void addAuthor(Author author) {
		Persistent.getInstance().persist(author);
	}

	public Author findAuthor(int id) {
		return Persistent.getInstance().findAuthor(id);
	}

	public List<Author> getAllAuthors() {
		return Persistent.getInstance().getAllAuthors();
	}

	public void addBook(Book book) {
		Persistent.getInstance().persist(book);
	}

	public Book findBook(String isbn13) {
		return Persistent.getInstance().findBook(isbn13);
	}

	public List<Book> searchBook(String searchString, boolean includeAuthor, boolean includeTitle) {
		return Persistent.getInstance().searchBook(searchString, includeAuthor, includeTitle);
	}
}
