package com.example.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ArticleController {
	EntityManagerFactory entityManagerFactory = null;
	EntityManager entityManager = null;
	@Autowired
	ArticleService articleService;

	public ArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}

	public ArticleController() {

	}

	@GetMapping("/articles")
	public List<Article> getArticles() {

		return (List<Article>) articleService.findAll();
	}

	@GetMapping("/articles/{id}")
	@ResponseBody
	public Article findById(@PathVariable long id) {
		return (Article) articleService.findById(id);
	}

	@GetMapping("/articleExist/{id}")
	@ResponseBody
	public boolean existsById(@PathVariable long id) {
		return articleService.existsById(id);
	}

	/*
	 * @RequestMapping(value = "getByName", method = RequestMethod.GET)
	 * public @ResponseBody Article getByName(@RequestParam("name") String name) {
	 * return articleService.GetByName(name);
	 * 
	 * }
	 */

	@RequestMapping(value = "getByName", method = RequestMethod.GET)
	public @ResponseBody List<Article> getByName(@RequestParam("name") String name) {
		return articleService.GetByName(name);

	}

	@PostMapping("/articles")
	void addArticle(@RequestBody Article article) {
		try {

			articleService.insert(article);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@PutMapping("/articles")
	void UpdateArticle(@RequestBody Article article) {
		try {
			// articleRepository.save(article);
			articleService.update(article);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@DeleteMapping("/articles")
	void DeleteArticle(@RequestBody Article article) {
		try { //
			articleService.delete(article);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
