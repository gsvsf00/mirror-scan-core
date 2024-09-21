package tech.bielsen.mirror_scan_api.integration.core;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import tech.bielsen.mirror_scan_api.integration.model.ScrapedItem;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Crawler {

    public void scrapeDefault(String url) {
        try {
            Document doc = Jsoup.connect(url).get();

            String title = doc.select("h1.manhua-title").text();

            String description = doc.select("div.manhua-description").text();

            Elements chapters = doc.select("a.chapter-link");

            System.out.println("Title: " + title);
            System.out.println("Description: " + description);
            System.out.println("Chapters:");
            for (Element chapter : chapters) {
                String chapterTitle = chapter.text();
                String chapterLink = chapter.attr("href");
                System.out.println(chapterTitle + ": " + chapterLink);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<ScrapedItem> scrapeAsura(String url) {
        List<ScrapedItem> scrapedItems = new ArrayList<>();

        try {
            Document document = Jsoup.connect(url).get();

            Elements data = document.select("a[href^=series]");

            for (Element element : data) {

                String pageUrl = element.attr("href");

                String imgUrl = element.select("img").attr("src");

                String title = element.select("span.block.text-[13.3px].font-bold").text();


                if (!imgUrl.isEmpty()) {
                    if (title.isEmpty()){

                        String[] splitUrl = pageUrl.split("/");
                        title = splitUrl[1].replace("-", " ").substring(0, splitUrl[1].length() - 1);
                        title = title.substring(0, 1).toUpperCase() + title.substring(1);
                    }
                    ScrapedItem item = new ScrapedItem(imgUrl, title, pageUrl);
                    scrapedItems.add(item);
                }
            }

            return scrapedItems;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void scrapeCustomSource(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
