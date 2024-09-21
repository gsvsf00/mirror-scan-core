package tech.bielsen.mirror_scan_api.integration.model;

public enum EnumSite {
    ASURA("https://asuracomic.net/series/"),
    MANHUAPLUS("https://manhuaplus.com/manga/"),
    MANHUAUS("https://manhuaus.com/manga/");

    private final String baseUrl;

    EnumSite(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
