package com.example.hyh.transit.presentation;

import com.example.hyh.transit.application.TransitSearchService;
import com.example.hyh.transit.application.dto.TransitSearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transits")
@RequiredArgsConstructor
public class TransitSearchRestController {

    private final TransitSearchService transitSearchService;

    @GetMapping("/search")
    public TransitSearchResult search(@RequestParam String keyword) {
        return transitSearchService.search(keyword);
    }

}
