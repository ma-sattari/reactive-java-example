package com.masoud.reactivejava.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateLinkResponse {
    private String shortenedLink;
}
