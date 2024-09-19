package tech.bielsen.mirror_scan_api.integration.model;

import lombok.Data;

@Data
public class ChapterData {
    private String title;
    private String url;
    private String date;
    private String imageManhua;

    public ChapterData(String title, String url, String date, String imageManhua) {
        this.title = title;
        this.url = url;
        this.date = date;
        this.imageManhua = imageManhua;
    }
}
