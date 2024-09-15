package tech.bielsen.mirror_scan_api.integration.core;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.ResponseEntity;
import tech.bielsen.mirror_scan_api.integration.model.ScrapedItem;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Crawller {

    public void scrapeDefault(String url) {
        try {
            // Connect to the website and get the document
            Document doc = Jsoup.connect(url).get();

            // Extract manhua title (adapt based on the site's HTML structure)
            String title = doc.select("h1.manhua-title").text();

            // Extract manhua description
            String description = doc.select("div.manhua-description").text();

            // Extract chapters (modify selector based on actual structure)
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
            // Select only <a> elements where href starts with "series/"
            Elements data = document.select("a[href^=series]");

            for (Element element : data) {
                // Extract href (URL) that starts with "series"
                String pageUrl = element.attr("href");
                // Extract the img src within the <a>
                String imgUrl = element.select("img").attr("src");

                // Extract the title from the <span> with specific class
                String title = element.select("span.block.text-[13.3px].font-bold").text();

                // Add to the list if valid
                if (!imgUrl.isEmpty()) {
                    if (title.isEmpty()){
                        //split page url to get title its has a space in between is used - to replace space, remove the "series/" and each word is capitalized and always remove tha last word
                        String[] splitUrl = pageUrl.split("/");
                        title = splitUrl[1].replace("-", " ").substring(0, splitUrl[1].length() - 1);
                        title = title.substring(0, 1).toUpperCase() + title.substring(1);
                    }
                    ScrapedItem item = new ScrapedItem(imgUrl, title, pageUrl);
                    scrapedItems.add(item);

                    // For testing, print each item (you can remove this in production)
                    System.out.println("Page URL: " + pageUrl);
                    System.out.println("Image URL: " + imgUrl);
                    System.out.println("Title: " + title);
                }
            }

            // Return the list as a ResponseEntity with OK status
            return scrapedItems;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void scrapeCustomSource(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            // Custom parsing logic for this specific source
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
