package cn.violin.bookmark.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_BOOKMARK")
@Builder
public class Bookmark {
    @Id
    private String id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "url")
    private String url;

    @Column(name = "tenant_id")
    private String tenantId;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private BookmarkType bookmarkType;
}
