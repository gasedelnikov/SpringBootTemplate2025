package ru.gri.core.api.rest;

import jakarta.validation.constraints.Min;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class ControllerUtils {

    private static final int DEF_PAGE_SIZE = 50;


    public static Pageable getPageable(@Min(0) Integer page, @Min(1) Integer limit, List<String> sort) {
        if (page == null) page = 0;
        if (limit == null || limit == 0) limit = DEF_PAGE_SIZE;

        return PageRequest.of(page, limit).withSort(convertSort(sort));
    }

    private static Sort convertSort(List<String> sort) {
        if (sort == null || sort.isEmpty()) return Sort.unsorted();

        List<Sort.Order> orders = sort.stream()
                .map(s -> new Sort.Order(getSortOrder(s), getSortField(s)))
                .toList();
        return Sort.by(orders);
    }

    private static String getSortField(String s) {
        String result = s.replaceFirst("-", "").trim();

        return prepareField(result);
    }

    private static String prepareField(String s) {
        String firstLetter = s.substring(0, 1).toLowerCase();
        s = firstLetter + s.substring(1);
        s = s.replace(".", "Entity.");

        return s;
    }

    private static Sort.Direction getSortOrder(String s) {
        return s.startsWith("-")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;
    }

}
