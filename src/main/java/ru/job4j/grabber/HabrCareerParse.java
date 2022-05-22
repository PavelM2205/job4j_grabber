package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.DateTimeParser;
import ru.job4j.grabber.utils.HabrCareerDateTimeParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HabrCareerParse implements Parse {
    private final DateTimeParser dateTimeParser;
    private final int pageCount;
    private static final String PAGE = "?page=";

    public HabrCareerParse(DateTimeParser dateTimeParser, int pageCount) {
        this.dateTimeParser = dateTimeParser;
        this.pageCount = pageCount;
    }

    private String retrieveDescription(String link) {
        Document document = null;
        try {
            document = Jsoup.connect(link).get();
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        return document.select(".style-ugc").first().text();
    }

    @Override
    public List<Post> list(String link) {
        List<Post> result = new ArrayList<>();
        for (int i = 1; i <= pageCount; i++) {
            try {
                Document document = Jsoup.connect(String.format(
                        "%s%s%d", link, PAGE, i)).get();
                Elements vacancies = document.select(".vacancy-card__inner");
                vacancies.forEach(vacancy -> {
                    Element titleElement = vacancy.select(".vacancy-card__title").first();
                    String title = titleElement.text();
                    String vacancyLink = titleElement.child(0).absUrl("href");
                    String date = vacancy.select(".vacancy-card__date").first().child(0)
                            .attr("datetime");
                    String description = retrieveDescription(vacancyLink);
                    result.add(new Post(
                            title,
                            vacancyLink,
                            description,
                            dateTimeParser.parse(date)
                    ));
                });
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        HabrCareerParse parser = new HabrCareerParse(
                new HabrCareerDateTimeParser(), 2);
        List<Post> posts = parser.list("https://career.habr.com/vacancies/java_developer");
        posts.forEach(System.out::println);
    }
}