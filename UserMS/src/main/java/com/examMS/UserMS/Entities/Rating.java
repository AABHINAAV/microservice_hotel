package com.examMS.UserMS.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Transient;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    private String ratingId;
    private String userId;
    private String hotelId;
    private int rating;
    private String feedback;

    @Transient
    private Hotel hotel;
}
