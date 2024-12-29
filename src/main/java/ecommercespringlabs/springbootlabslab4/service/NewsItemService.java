package ecommercespringlabs.springbootlabslab4.service;

import ecommercespringlabs.springbootlabslab4.domain.NewsItem;
import ecommercespringlabs.springbootlabslab4.dto.news.NewsItemRequestDto;

import java.util.List;

public interface NewsItemService {
    List<NewsItem> findAllNewsItems(int page, int size);

    NewsItem addNewsItem(NewsItemRequestDto newsItem);

    NewsItem updateNewsItem(NewsItemRequestDto newsItem, String id);

    void deleteNewsItem(String newsItemId);

    NewsItem findNewsItemById(String id);
}
