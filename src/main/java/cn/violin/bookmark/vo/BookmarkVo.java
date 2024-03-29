package cn.violin.bookmark.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookmarkVo {

    @JsonProperty("bk_id")
    private String id;

    @JsonProperty("bk_type_id")
    private String typeId;

    @JsonProperty("bk_type_name")
    private String typeName;

    @JsonProperty("comment")
    private String comment;

    @JsonProperty("url")
    private String url;

    @JsonProperty("count")
    private long count;

}
