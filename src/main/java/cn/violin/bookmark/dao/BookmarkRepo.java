package cn.violin.bookmark.dao;

import cn.violin.bookmark.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface BookmarkRepo extends JpaRepository<Bookmark, String>, QueryByExampleExecutor<Bookmark> {
}
