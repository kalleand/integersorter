package se.hm.codetest.service;

import com.google.common.base.Joiner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;
import se.hm.codetest.controller.dto.SortedDto;
import se.hm.codetest.persistence.SortedRepository;
import se.hm.codetest.persistence.model.Sorted;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class SortServiceImpl implements SortService {

    private static final int BASE = 10;

    private final SortedRepository sortedRepository;

    @Autowired public SortServiceImpl(SortedRepository sortedRepository) {
        this.sortedRepository = sortedRepository;
    }

    @Override
    public List<SortedDto> getAll() {
        return sortedRepository.findAll().stream()
                .sorted(Comparator.comparing(Sorted::getId))
                .map(SortedDto::convert)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public SortedDto sortArray(final List<Integer> inputList) {
        // We use the fact that we know what values are passed in to this method. Radix sort using base 10 will result in two iterations.
        // This means we need to make two passes over the input, hence O(n*2 + k) == O(n + k).

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // Sort the numbers based on least significant number (eg input = [21, 33, 5, 42, 23], output = [21, 42, 33, 23, 5])
        Pair<List<Integer>, Integer> iterationOne = radixSort(inputList, 0);
        // Sort the numbers based on most significant number (eg input = [21, 42, 33, 23, 5], output = [5, 21, 23, 33, 42])
        Pair<List<Integer>, Integer> iterationTwo = radixSort(iterationOne.getFirst(), 1);
        stopWatch.stop();

        Sorted sorted = new Sorted();
        sorted.setInputArray(Joiner.on(" ").join(inputList));
        sorted.setSortedArray(Joiner.on(" ").join(iterationTwo.getFirst()));
        sorted.setSteps(iterationOne.getSecond() + iterationTwo.getSecond());
        sorted.setSortTime(stopWatch.getLastTaskTimeMillis());
        sortedRepository.save(sorted);

        return SortedDto.convert(sorted);
    }

    private Pair<List<Integer>, Integer> radixSort(List<Integer> inputList, int exponent) {
        Map<Integer, List<Integer>> buckets = new HashMap<>();

        inputList.forEach(i -> {
            int bucketIndex = ((int) (i / Math.pow(BASE, exponent))) % 10;

            if (!buckets.containsKey(bucketIndex)) {
                buckets.put(bucketIndex, new ArrayList<>());
            }
            buckets.get(bucketIndex).add(i);
        });

        ArrayList<Integer> resultList = new ArrayList<>();
        IntStream.range(0, 10)
                .mapToObj(buckets::get)
                .filter(Objects::nonNull)
                .forEach(resultList::addAll);

        return Pair.of(resultList, inputList.size() * 2);
    }
}
