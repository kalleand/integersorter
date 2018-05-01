package se.hm.codetest.service;

import se.hm.codetest.controller.dto.SortedDto;

import java.util.List;

public interface SortService {
    List<SortedDto> getAll();

    SortedDto sortArray(List<Integer> inputList);
}
