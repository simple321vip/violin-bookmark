package cn.violin.bookmark.service;

import cn.violin.bookmark.dao.BookmarkTypeRepo;
import cn.violin.bookmark.entity.BookmarkType;
import cn.violin.bookmark.request.BookmarkForm;
import cn.violin.common.entity.Tenant;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static cn.violin.common.utils.CommonConstant.FORMATTER_DATETIME;

@Service
@AllArgsConstructor
public class BookmarkTypeService {

    @Autowired
    private BookmarkTypeRepo bookmarkTypeRepo;

    /**
     * bookmarkType登録
     *
     * @param bookmarkForm 登録内容
     */
    @Transactional
    public void create(BookmarkForm bookmarkForm, Tenant tenant) {
        String id = LocalDateTime.now().format(FORMATTER_DATETIME);
        BookmarkType bookmarkType = new BookmarkType();
        bookmarkType.setTypeId(id);
        bookmarkType.setTypeName(bookmarkForm.getTypeName());
        bookmarkType.setTenantId(tenant.getTenantId());
        bookmarkTypeRepo.save(bookmarkType);
    }

    /**
     * BookmarkType一覧取得
     *
     * @return List<BookmarkType>
     */
    public List<BookmarkType> get(Tenant tenant) {
        Example<BookmarkType> queryExample
                = Example.of(BookmarkType.builder().tenantId(tenant.getTenantId()).build());
        return bookmarkTypeRepo.findAll(queryExample);
    }

    /**
     * delete bookmarkType by type_id
     */
    @Transactional
    public void delete(String id) {
        bookmarkTypeRepo.deleteById(id);
    }

    /**
     * bookmarkType更新
     *
     * @param bookmarkForm 更新内容
     */
    @Transactional
    public void update(String id, BookmarkForm bookmarkForm) {
        var bookmarkType = bookmarkTypeRepo.findById(id).orElseThrow();
        bookmarkType.setTypeName(bookmarkForm.getTypeName());
        bookmarkTypeRepo.save(bookmarkType);
    }
}
