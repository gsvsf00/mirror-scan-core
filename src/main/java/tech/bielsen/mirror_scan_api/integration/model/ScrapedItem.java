package tech.bielsen.mirror_scan_api.integration.model;

import lombok.Data;

@Data
public class ScrapedItem {
    private String imgUrl;
    private String title;
    private String url;

    public ScrapedItem(String imgUrl, String title, String pageUrl) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.url = pageUrl;
    }
}

