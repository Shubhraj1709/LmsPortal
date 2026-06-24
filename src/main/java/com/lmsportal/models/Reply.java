package com.lmsportal.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @ManyToOne
    @JoinColumn(name = "discussion_id")
    @JsonIgnore
    private Discussion discussion;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private User instructor;
}
