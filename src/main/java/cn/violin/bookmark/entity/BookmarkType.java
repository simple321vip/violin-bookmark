package cn.violin.bookmark.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "t_bookmark_type")
public class BookmarkType {

    private String typeId;

    private String typeName;

    private String owner;
}
