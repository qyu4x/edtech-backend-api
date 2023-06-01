package com.alphaomega.alphaomegarestfulapi.payload.response;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseCategoryResponse {

    private String id;

    private String name;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
