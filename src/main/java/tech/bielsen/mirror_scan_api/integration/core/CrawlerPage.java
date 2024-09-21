package tech.bielsen.mirror_scan_api.integration.core;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import tech.bielsen.mirror_scan_api.integration.model.ChapterData;
import tech.bielsen.mirror_scan_api.integration.model.EnumSite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrawlerPage {
    public List<ChapterData> scrapePage(EnumSite base, String url) {
        List<ChapterData> chapterList = new ArrayList<>(); // Custom list to store chapter details

        try {
            Document document = Jsoup.connect(url).get();

            // Select the elements for chapters, URLs, dates, and image based on provided paths
            Elements chapters = document.select("div h3:nth-of-type(1) a"); // For chapter titles
            Elements chapterUrls = document.select("div h3:nth-of-type(1) a"); // For chapter URLs
            Elements chapterDates = document.select("div h3:nth-of-type(2)"); // For chapter dates
            Element imageManhua = document.select("div div div div img").first(); // For image URL

            // Get the image URL
            String imageUrl = imageManhua.attr("src");

            // Get the base URL from the SiteEnum
            String baseUrl = base.toString();

            for (int i = 0; i < chapters.size(); i++) {
                Element chapter = chapters.get(i);
                Element chapterUrl = chapterUrls.get(i);
                Element chapterDate = chapterDates.get(i);

                String title = chapter.text(); // Get chapter title
                String pageUrl = baseUrl + chapterUrl.attr("href"); // Concatenate base URL with relative URL
                String date = chapterDate.text(); // Get chapter date

                // Add the chapter details to the list
                chapterList.add(new ChapterData(title, pageUrl, date, imageUrl));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return chapterList;
    }
}
