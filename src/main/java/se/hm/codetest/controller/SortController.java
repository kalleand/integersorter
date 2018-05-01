package se.hm.codetest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import se.hm.codetest.controller.dto.SortedDto;
import se.hm.codetest.service.SortService;

import java.util.List;

@Controller
public class SortController {

    private static final Logger LOG = LoggerFactory.getLogger(SortController.class);
    private final SortService sortService;

    @Autowired
    public SortController(SortService sortService) {
        this.sortService = sortService;
    }

    @PostMapping(path = "/sort", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SortedDto> sort(@RequestBody List<Integer> inputList) {
        LOG.info("Sorting new array of integers");
        if (inputList.isEmpty()
                || inputList.size() > 50
                || inputList.stream()
                .anyMatch(i -> i <= 0 || i >= 100)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(sortService.sortArray(inputList));
    }

    @GetMapping(path = "/all", produces = "application/json")
    public ResponseEntity<List<SortedDto>> getAll() {
        LOG.info("Returning all the previous sortings");
        return ResponseEntity.ok(sortService.getAll());
    }
}
