package cn.violin.bookmark.dao;

import cn.violin.bookmark.entity.BookmarkSeq;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkSeqRepo extends JpaRepository<BookmarkSeq, Integer> {
}
