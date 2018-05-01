package se.hm.codetest.controller.dto;

import se.hm.codetest.persistence.model.Sorted;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class SortedDto {

    private List<Integer> sortedArray;
    private List<Integer> inputArray;
    private int steps;
    private long milliseconds;

    public static SortedDto convert(Sorted sorted) {
        return SortedDtoBuilder.aSortedDto()
                .withSteps(sorted.getSteps())
                .withSortedArray(Arrays.stream(sorted.getSortedArray().split("\\s"))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()))
                .withInputArray(Arrays.stream(sorted.getInputArray().split("\\s"))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()))
                .withMilliseconds(sorted.getSortTime())
                .build();
    }

    public List<Integer> getSortedArray() {
        return sortedArray;
    }

    public int getSteps() {
        return steps;
    }

    public List<Integer> getInputArray() {
        return inputArray;
    }

    public long getMilliseconds() {
        return milliseconds;
    }

    public static final class SortedDtoBuilder {
        private List<Integer> sortedArray;
        private List<Integer> inputArray;
        private int steps;
        private long milliseconds;

        private SortedDtoBuilder() {
        }

        public static SortedDtoBuilder aSortedDto() {
            return new SortedDtoBuilder();
        }

        public SortedDtoBuilder withSortedArray(List<Integer> sortedArray) {
            this.sortedArray = sortedArray;
            return this;
        }

        public SortedDtoBuilder withInputArray(List<Integer> inputArray) {
            this.inputArray = inputArray;
            return this;
        }

        public SortedDtoBuilder withSteps(int steps) {
            this.steps = steps;
            return this;
        }

        public SortedDtoBuilder withMilliseconds(long milliseconds) {
            this.milliseconds = milliseconds;
            return this;
        }

        public SortedDto build() {
            SortedDto sortedDto = new SortedDto();
            sortedDto.inputArray = this.inputArray;
            sortedDto.steps = this.steps;
            sortedDto.milliseconds = this.milliseconds;
            sortedDto.sortedArray = this.sortedArray;
            return sortedDto;
        }
    }
}
