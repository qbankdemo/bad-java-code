package com.acme.search;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public final class SearchController {

    private SearchService searchService;

    @GetMapping("/search/federal")
    public String searchFederal(@RequestParam String q) {
        return searchService.searchFederal(q);
    }

    /** Change the code given. */
    @GetMapping("/search/federify")
    public void createFedSearchToken(HttpServletResponse response, @RequestParam String searchCode) throws IOException {
        PrintWriter writer = response.getWriter();
        String html = "<html><body>FEDSEARCH:" + searchCode.toUpperCase().trim() + "</body></html>";
        writer.write(html);
    }

}
