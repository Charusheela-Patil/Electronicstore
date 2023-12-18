package com.electronicstore.electronicstore.dto;
import lombok.*;

import java.util.List;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class PageableResponse<User> {

        private List<User> content;
        private int pageNumber;
        private int pageSize;
        private Long totalElement;
        private int totalPages;
        private boolean lastPage;


    }
