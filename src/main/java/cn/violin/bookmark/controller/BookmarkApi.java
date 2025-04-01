package cn.violin.bookmark.controller;

import cn.violin.bookmark.request.BookmarkForm;
import cn.violin.bookmark.service.BookmarkService;
import cn.violin.bookmark.vo.BookmarkVo;
import cn.violin.common.annotation.CurrentUser;
import cn.violin.common.entity.Tenant;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Controller
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/v1")
public class BookmarkApi {

    @Autowired
    private BookmarkService bookmarkService;

    @ResponseBody
    @RequestMapping("/bookmark")
    public ResponseEntity<List<BookmarkVo>> find(@RequestParam(value = "type_id", required = false) String[] typeId,
                                                     @RequestParam(value = "comment") String comment,
                                                     @CurrentUser Tenant tenant) {
        return new ResponseEntity<>(bookmarkService.find(typeId, comment, tenant), HttpStatus.OK);
    }

    @PostMapping("/bookmark")
    public ResponseEntity<Void> create(@Valid @RequestBody() BookmarkForm input, @CurrentUser Tenant tenant) {
        bookmarkService.create(input, tenant);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/bookmark/{id}")
    public ResponseEntity<Void> update(@NotBlank @PathVariable("id") String id, @Valid @RequestBody() BookmarkForm input, @CurrentUser Tenant tenant) {
        bookmarkService.update(id, input);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/bookmark/{id}")
    public ResponseEntity<Void> delete(@NotBlank @PathVariable("id") String id, @CurrentUser Tenant tenant) {
        bookmarkService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/bookmark/count")
    public ResponseEntity<BookmarkVo> count(@CurrentUser Tenant tenant) {
        long count = bookmarkService.count(tenant);
        BookmarkVo vo = BookmarkVo.builder().count(count).build();
        return new ResponseEntity<>(vo, HttpStatus.OK);
    }
}
