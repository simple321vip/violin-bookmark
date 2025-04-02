package cn.violin.bookmark.service;

import cn.violin.bookmark.dao.BookmarkRepo;
import cn.violin.bookmark.dao.BookmarkTypeRepo;
import cn.violin.bookmark.entity.Bookmark;
import cn.violin.bookmark.entity.QBookmark;
import cn.violin.bookmark.entity.QBookmarkType;
import cn.violin.bookmark.request.BookmarkForm;
import cn.violin.bookmark.utils.NumberEnum;
import cn.violin.bookmark.vo.BookmarkVo;
import cn.violin.common.entity.Tenant;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static cn.violin.common.utils.CommonConstant.FORMATTER_DATETIME;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class BookmarkService {

    @Autowired
    private NumberService numberService;

    @Autowired
    private BookmarkRepo bookmarkRepo;

    @Autowired
    private BookmarkTypeRepo bookmarkTypeRepo;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * bookmark 条件検索
     *
     * @param query   検索内容
     * @param typeIds bookmark type id リスト
     * @return List<Bookmark>
     */
    public List<BookmarkVo> find(String[] typeIds, String query, Tenant tenant) {
        QBookmark qBookmark = QBookmark.bookmark;
        QBookmarkType qBookmarkType = QBookmarkType.bookmarkType;
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qBookmark.tenantId.eq(tenant.getTenantId()));

        if (Objects.nonNull(typeIds) && typeIds.length != 0) {
            builder.and(qBookmark.bookmarkType.typeId.in(typeIds));
        }

        // 动态构建 OR 条件组
        BooleanBuilder orGroup = new BooleanBuilder();
        if (StringUtils.hasText(query)) {
            orGroup.or(qBookmark.comment.like(query));
            orGroup.or(qBookmark.url.like(query));
        }

        builder.and(orGroup);

        List<Bookmark> bookmarks = queryFactory
                .select(qBookmark)
                .from(qBookmarkType)
                .innerJoin(qBookmarkType.bookmarks, qBookmark)
                .where(builder)
                .fetch();

        return bookmarks.stream().map(bookmark -> BookmarkVo.builder()
                .id(bookmark.getId())
                .comment(bookmark.getComment())
                .typeId(bookmark.getBookmarkType().getTypeId())
                .typeName(bookmark.getBookmarkType().getTypeName())
                .url(bookmark.getUrl())
                .build()).collect(Collectors.toList());
    }

    /**
     * bookmark登録
     *
     * @param bookmarkForm 登録内容
     */
    @Transactional
    public void create(BookmarkForm bookmarkForm, Tenant tenant) {
        String id = LocalDateTime.now().format(FORMATTER_DATETIME) + numberService.getNumberId(NumberEnum.T_BOOKMARK);

        Bookmark bookmark = Bookmark.builder()
                .id(id)
                .bookmarkType(bookmarkTypeRepo.getReferenceById(bookmarkForm.getTypeId()))
                .comment(bookmarkForm.getComment())
                .url(bookmarkForm.getUrl())
                .tenantId(tenant.getTenantId())
                .build();

        bookmarkRepo.save(bookmark);
    }

    /**
     * bookmark更新
     *
     * @param bookmarkForm 更新内容
     */
    @Transactional()
    public void update(String id, BookmarkForm bookmarkForm) {
        var optional = bookmarkRepo.findById(id);
        var bookmark = optional.orElseThrow();

        bookmark.setUrl(bookmarkForm.getUrl());
        bookmark.setComment(bookmarkForm.getComment());
        bookmark.setBookmarkType(bookmarkTypeRepo.findById(bookmarkForm.getTypeId()).orElseThrow());

        bookmarkRepo.saveAndFlush(bookmark);
    }

    /**
     * bookmark削除
     *
     * @param id bookmark id
     */
    @Transactional()
    public void delete(String id) {
        bookmarkRepo.deleteById(id);
    }

    /**
     * bookmark件数取得
     *
     * @param tenant テナントID
     */
    public long count(Tenant tenant) {
        Example<Bookmark> queryExample
                = Example.of(Bookmark.builder().tenantId(tenant.getTenantId()).build());
        return bookmarkRepo.count(queryExample);
    }
}
