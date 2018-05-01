package se.hm.codetest.controller;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import se.hm.codetest.controller.dto.SortedDto;
import se.hm.codetest.service.SortService;

import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static se.hm.codetest.controller.dto.SortedDto.SortedDtoBuilder.aSortedDto;

@RunWith(MockitoJUnitRunner.class)
public class SortControllerTest {
    @Mock
    private SortService sortService;

    @InjectMocks
    private SortController sortController;

    @Test
    public void testSortSuccess() {
        when(sortService.sortArray(anyList())).thenReturn(aSortedDto().withSteps(2).build());
        ResponseEntity<SortedDto> result = sortController.sort(ImmutableList.of(1, 2, 3));
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(2, Objects.requireNonNull(result.getBody()).getSteps());
    }

    @Test
    public void testSortFailTooLow() {
        ResponseEntity<SortedDto> result = sortController.sort(ImmutableList.of(1, -2, 3));
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void testSortFailTooHigh() {
        ResponseEntity<SortedDto> result = sortController.sort(ImmutableList.of(1, 2, 103));
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void testSortFailTooNoInput() {
        ResponseEntity<SortedDto> result = sortController.sort(Collections.emptyList());
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void testSortFailTooManyNumbers() {
        ResponseEntity<SortedDto> result = sortController.sort(IntStream.range(0, 51).boxed().collect(Collectors.toList()));
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void testGetAll() {
        sortController.getAll();
        verify(sortService).getAll();
    }
}
