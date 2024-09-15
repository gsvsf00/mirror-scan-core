package tech.bielsen.mirror_scan_api.model;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Map;

@Data
public class ScanMirror {

    @Id
    private String id;

    private String imgUrl;

    @Indexed(unique = true)
    private String title;

    private String urlPage;

    @JsonIgnore
    private Map<String, String> favoriteUser;  // UserId as key, PageURL as value
}