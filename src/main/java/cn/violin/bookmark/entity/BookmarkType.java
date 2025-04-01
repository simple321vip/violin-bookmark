package cn.violin.bookmark.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_BOOKMARK_TYPE")
@Builder
public class BookmarkType {

    @Id
    @Column(name = "type_id")
    private String typeId;

    @Column(name = "tenant_name")
    private String typeName;

    @Column(name = "tenant_id")
    private String tenantId;

    @OneToMany(mappedBy = "bookmarkType",
            cascade = CascadeType.ALL, // 包含 REMOVE
            orphanRemoval = true // 可选，确保子实体被彻底删除
    )
    private List<Bookmark> bookmarks;
}
