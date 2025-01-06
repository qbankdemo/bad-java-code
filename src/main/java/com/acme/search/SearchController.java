package com.acme.search;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public final class SearchController {

    private SearchService searchService;

    @GetMapping("/search/federal")
    public String searchFederal(@RequestParam String q) {
        return searchService.searchFederal(q);
    }

    /** Change the code given. */
    @GetMapping("/search/federify")
    public String createFedSearchToken(@RequestParam String searchCode) {
        return "<html><body>FEDSEARCH:" + searchCode.toUpperCase().trim() + "</body></html>";
    }

}
