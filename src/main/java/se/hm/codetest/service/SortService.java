package se.hm.codetest.service;

import se.hm.codetest.controller.dto.SortedDto;

import java.util.List;

/**
 * Service to sort integers using Radix sort.
 */
public interface SortService {
    /**
     * Retrieves all the previous sorting results.
     *
     * @return the results from previous sorting
     */
    List<SortedDto> getAll();

    /**
     * Sorts the integers in inputList and returns the sorted array along with meta data.
     *
     * @param inputList the integers to be sorted, they are assumed to be between 0 and 100 (not inclusive)
     * @return the result of the sorting.
     */
    SortedDto sortArray(List<Integer> inputList);
}
