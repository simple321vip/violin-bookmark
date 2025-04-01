package cn.violin.bookmark.dao;

import cn.violin.bookmark.entity.Bookmark;
import cn.violin.bookmark.entity.BookmarkType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface BookmarkTypeRepo extends JpaRepository<BookmarkType, String>, QueryByExampleExecutor<BookmarkType> {
}
