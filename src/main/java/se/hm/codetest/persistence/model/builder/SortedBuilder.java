package se.hm.codetest.persistence.model.builder;

import se.hm.codetest.persistence.model.Sorted;

import java.time.LocalDateTime;

public final class SortedBuilder {
    private int steps;
    private String sortedArray;
    private String inputArray;
    private Long sortTime;

    private SortedBuilder() {
    }

    public static SortedBuilder aSorted() {
        return new SortedBuilder();
    }

    public SortedBuilder withSteps(int steps) {
        this.steps = steps;
        return this;
    }

    public SortedBuilder withSortedArray(String sortedArray) {
        this.sortedArray = sortedArray;
        return this;
    }

    public SortedBuilder withInputArray(String inputArray) {
        this.inputArray = inputArray;
        return this;
    }

    public SortedBuilder withSortTime(Long sortTime) {
        this.sortTime = sortTime;
        return this;
    }

    public Sorted build() {
        Sorted sorted = new Sorted();
        sorted.setSteps(steps);
        sorted.setSortedArray(sortedArray);
        sorted.setInputArray(inputArray);
        sorted.setSortTime(sortTime);
        return sorted;
    }
}
