package tech.bielsen.mirror_scan_api.model;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Map;

@Data
public class ScanMirrorPage {

    private String title;

    private String urlPage;

    private String[] chaptersUrl;

    private String[] chaptersImgUrl;
}