package cn.violin.bookmark.service;

import cn.violin.bookmark.dao.BookmarkSeqRepo;
import cn.violin.bookmark.entity.BookmarkSeq;

import cn.violin.bookmark.utils.NumberEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NumberService {

    @Autowired
    private BookmarkSeqRepo bookmarkSeqRepo;

    @Transactional
    public String getNumberId(NumberEnum table) {

        String numberId = null;
        switch (table.value()) {

            case "t_bookmark":
                BookmarkSeq seq1 = new BookmarkSeq();
                numberId = String.format("%014d", bookmarkSeqRepo.save(seq1).getBkSeqId());
                break;
        }

        return numberId;
    }


}
