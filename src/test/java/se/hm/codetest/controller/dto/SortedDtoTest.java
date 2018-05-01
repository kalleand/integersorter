package se.hm.codetest.controller.dto;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import se.hm.codetest.persistence.model.Sorted;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SortedDtoTest {

    @Test
    public void testConvert() {
        Sorted input = new Sorted();
        input.setInputArray("3 2 1");
        input.setSortedArray("1 2 3");
        input.setSteps(1);
        input.setSortTime(2L);
        SortedDto result = SortedDto.convert(input);
        assertNotNull(result);
        assertEquals(1, result.getSteps());
        assertEquals(2L, result.getMilliseconds());
        assertEquals(ImmutableList.of(3, 2, 1), result.getInputArray());
        assertEquals(ImmutableList.of(1, 2, 3), result.getSortedArray());
    }
}
