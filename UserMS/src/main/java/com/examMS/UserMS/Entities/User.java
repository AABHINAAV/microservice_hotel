package com.examMS.UserMS.Entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_ms")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private String userEmail;

    @Transient
    private List<Rating> ratings = new ArrayList<>();
}
