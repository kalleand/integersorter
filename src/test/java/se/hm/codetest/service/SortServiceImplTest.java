package se.hm.codetest.service;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import se.hm.codetest.controller.dto.SortedDto;
import se.hm.codetest.persistence.SortedRepository;
import se.hm.codetest.persistence.model.Sorted;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SortServiceImplTest {

    @Mock
    private SortedRepository sortedRepository;

    @InjectMocks
    private SortServiceImpl sortService;

    @Test
    public void testGetAll() {
        when(sortedRepository.findAll()).thenReturn(ImmutableList.of(createSorted(2L), createSorted(1L)));

        List<SortedDto> result = sortService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getSteps());
        assertEquals(2, result.get(1).getSteps());
    }

    @Test
    public void testSortArray() {
        List<Integer> inputList = ImmutableList.of(21, 33, 5, 42, 23);
        SortedDto result = sortService.sortArray(inputList);
        assertNotNull(result);
        assertEquals(inputList.size() * 4, result.getSteps());
        assertEquals(ImmutableList.of(5, 21, 23, 33, 42), result.getSortedArray());
        verify(sortedRepository).save(any(Sorted.class));
    }

    private Sorted createSorted(long id) {
        Sorted sorted = new Sorted();
        sorted.setId(id);
        sorted.setSortTime(id);
        sorted.setSteps((int) id);
        sorted.setInputArray("1 2 3");
        sorted.setSortedArray("4 5 6");
        return sorted;
    }
}
