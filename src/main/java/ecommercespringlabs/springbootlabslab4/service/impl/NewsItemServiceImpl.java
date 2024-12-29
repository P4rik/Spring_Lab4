package ecommercespringlabs.springbootlabslab4.service.impl;

import ecommercespringlabs.springbootlabslab4.domain.NewsItem;
import ecommercespringlabs.springbootlabslab4.dto.news.NewsItemRequestDto;
import ecommercespringlabs.springbootlabslab4.service.NewsItemService;
import ecommercespringlabs.springbootlabslab4.service.exception.NewsItemNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class NewsItemServiceImpl implements NewsItemService {

    List<NewsItem> news = new ArrayList<>();

    public NewsItemServiceImpl() {
        news.add(NewsItem.builder()
                .id(UUID.randomUUID())
                .title("Usyk breaks Fury's")
                .description("Oleksandr Usyk defeats Briton Tyson Fury to retain his unified heavyweight world titles and prove his status as a generational great.")
                .category("Sport")
                .build());
        news.add(NewsItem.builder()
                .id(UUID.randomUUID())
                .title("Donald Trump president")
                .description("Donald Trump wins 2024 US election in historic comeback")
                .category("Politics")
                .build());
    }

    @Override
    public List<NewsItem> findAllNewsItems(int page, int size) {
        return news.stream().skip(page * size).limit(size).toList();
    }

    @Override
    public NewsItem findNewsItemById(String id) {
        return news.stream().filter(newsItem -> newsItem.getId().equals(UUID.fromString(id)))
                .findFirst()
                .orElseThrow(() -> new NewsItemNotFoundException(id));
    }

    @Override
    public NewsItem addNewsItem(NewsItemRequestDto newsItemRequestDto) {
        NewsItem newsItem = NewsItem.builder()
                .id(UUID.randomUUID())
                .title(newsItemRequestDto.getTitle())
                .description(newsItemRequestDto.getDescription())
                .category(newsItemRequestDto.getCategory())
                .build();
        news.add(newsItem);
        return newsItem;
    }

    @Override
    public NewsItem updateNewsItem(NewsItemRequestDto newsItemRequestDto, String id) {
        NewsItem newsItem = findNewsItemById(id);
        newsItem.setTitle(newsItemRequestDto.getTitle());
        newsItem.setDescription(newsItemRequestDto.getDescription());
        newsItem.setCategory(newsItemRequestDto.getCategory());
        return newsItem;
    }

    @Override
    public void deleteNewsItem(String id) {
        NewsItem newsItem = findNewsItemById(id);
        news.remove(newsItem);
    }
}
