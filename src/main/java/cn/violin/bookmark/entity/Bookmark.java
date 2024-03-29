package cn.violin.bookmark.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "t_bookmark")
@Builder
public class Bookmark {
    @Id
    private String id;
    private String deleteFlg;
    private String typeId;
    private String comment;
    private String url;
    private String owner;
}
