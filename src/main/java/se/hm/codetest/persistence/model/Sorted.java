package se.hm.codetest.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "sorted")
public class Sorted implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "steps")
    private int steps;
    @Column(name = "sorted")
    private String sortedArray;
    @Column(name = "input")
    private String inputArray;
    @Column(name = "sort_time")
    private Long sortTime;

    public String getSortedArray() {
        return sortedArray;
    }

    public void setSortedArray(String sortedArray) {
        this.sortedArray = sortedArray;
    }

    public String getInputArray() {
        return inputArray;
    }

    public void setInputArray(String inputArray) {
        this.inputArray = inputArray;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public Long getSortTime() {
        return sortTime;
    }

    public void setSortTime(Long sortTime) {
        this.sortTime = sortTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
