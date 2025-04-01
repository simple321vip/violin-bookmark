package cn.violin.bookmark.controller;

import cn.violin.bookmark.request.BookmarkForm;
import cn.violin.bookmark.service.BookmarkTypeService;
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
import java.util.stream.Collectors;

@Controller
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/v1")
public class BookmarkTypeApi {

    @Autowired
    private BookmarkTypeService bookmarkTypeService;

    @PostMapping("/bookmark_type")
    public ResponseEntity<Void> create(@Valid @RequestBody() BookmarkForm input, @CurrentUser Tenant tenant) {
        bookmarkTypeService.create(input, tenant);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping("/bookmark_type")
    public List<String> get(@CurrentUser Tenant tenant) {
        return bookmarkTypeService.get(tenant).stream()
                .map(item -> item.getTypeId() + "|" + item.getTypeName()).collect(Collectors.toList());
    }

    @ResponseBody
    @DeleteMapping("/bookmark_type/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String typeId, @CurrentUser Tenant tenant) {
        bookmarkTypeService.delete(typeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseBody
    @PutMapping("/bookmark_type/{id}")
    public ResponseEntity<Void> update(@NotBlank @PathVariable("id") String id, @Valid @RequestBody() BookmarkForm bookmarkForm, @CurrentUser Tenant tenant) {
        bookmarkTypeService.update(id, bookmarkForm);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
