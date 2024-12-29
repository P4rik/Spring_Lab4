package ecommercespringlabs.springbootlabslab4.web;

import ecommercespringlabs.springbootlabslab4.domain.NewsItem;
import ecommercespringlabs.springbootlabslab4.dto.news.NewsItemRequestDto;
import ecommercespringlabs.springbootlabslab4.service.NewsItemService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/news")
@Validated
public class NewsItemController {

    private final NewsItemService newsItemService;

    public NewsItemController(NewsItemService newsItemService) {
        this.newsItemService = newsItemService;
    }


    @GetMapping
    public ResponseEntity<List<NewsItem>> getAllNewsItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(newsItemService.findAllNewsItems(page, size));
    }

    @GetMapping("/{newsId}")
    public ResponseEntity<NewsItem> getNewsItemById(@PathVariable String newsId) {
        return ResponseEntity.ok(newsItemService.findNewsItemById(newsId));
    }

    @PostMapping
    public ResponseEntity<NewsItem> addNewsItem(@RequestBody @Valid NewsItemRequestDto newsItemRequestDto) {
        NewsItem newsItem = newsItemService.addNewsItem(newsItemRequestDto);
        return ResponseEntity.ok(newsItem);
    }

    @DeleteMapping("/{newsId}")
    public ResponseEntity<Void> deleteNewsItem(@PathVariable String newsId) {
        newsItemService.deleteNewsItem(newsId);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{newsId}")
    public ResponseEntity<NewsItem> updateNewsItem(@RequestBody @Valid NewsItemRequestDto newsItemRequestDto, @PathVariable String newsId) {
        NewsItem newsItem = newsItemService.updateNewsItem(newsItemRequestDto, newsId);
        return ResponseEntity.ok(newsItem);
    }
}
