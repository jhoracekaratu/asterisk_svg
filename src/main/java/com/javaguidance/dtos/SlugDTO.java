package com.javaguidance.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Builder
@Getter
@Setter
public class SlugDTO {
    Integer id;
   Integer rating;
//    Set<String> category;
    String slug;

}
