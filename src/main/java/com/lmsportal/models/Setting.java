package com.lmsportal.models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "settings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Setting {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@Column(name = "setting_key")
    private String key;

    @Column(name = "setting_value", length = 2000)
    private String value;
}