package com.masoud.reactivejava.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Link {
    private String originalLink;
    private String key;
}
