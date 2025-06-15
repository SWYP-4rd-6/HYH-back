package com.example.hyh.transit.presentation;

import com.example.hyh.global.dto.Response;
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
    public Response<TransitSearchResult> search(@RequestParam String keyword) {
        return Response.success(transitSearchService.search(keyword));
    }

}
