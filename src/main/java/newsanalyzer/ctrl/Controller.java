package newsanalyzer.ctrl;

import newsapi.NewsApi;
import newsapi.NewsApiBuilder;
import newsapi.beans.Article;
import newsapi.beans.NewsReponse;
import newsapi.enums.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;




public class Controller {

	public static final String APIKEY = "57921b7703a54d128d168651c2abbd33";

	public void process(String query, Endpoint endpoint, Category category, Language language, Country country, SortBy sortBy) {
		System.out.println("Start process");

		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(APIKEY)
				.setQ("corona")
				.setEndPoint(endpoint)
				.setFrom("2021-03-21")
				.setSourceCountry(country)
				.setSourceCategory(category)
				.setLanguage(language)
				.createNewsApi();

		NewsReponse newsResponse = newsApi.getNews();
		if(newsResponse != null){
			List<Article> articles = newsResponse.getArticles();
			articles.stream().forEach(article -> System.out.println(article.toString()));
			getData(articles);
		}

		//TODO implement Error handling
		//TODO catch error 426

		//TODO load the news based on the parameters


		//TODO implement methods for analysis

		System.out.println("End process");
	}
	

	public Object getData(List<Article> data) {
		long numberArticles;
		String mostArticles;
		String authorShortestName;

		numberArticles = data.size();
		System.out.println("Number of Articles: " + numberArticles);

		authorShortestName = data.stream().filter(article -> article.getAuthor() != null).max(Comparator.comparing(Article::getAuthor)).get().getAuthor();
		System.out.println("Author with shortest name: " + authorShortestName);

		mostArticles = data.stream().min(Comparator.comparingLong((a) -> Collections.frequency(data, a.getSource().getName()))).orElseThrow(NoSuchElementException::new).getSource().getName();
		System.out.println("Most Articles by: "+ mostArticles);
		//Todo sort by length of title


		
		return null;
	}
}
