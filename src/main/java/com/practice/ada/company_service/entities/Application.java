package com.practice.ada.company_service.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "app_id")
    private long appId;
    @Column(name = "app_code")
    private String appCode;
    @Column(name = "app_name")
    private String appName;
    @Column(name = "app_description")
    private String appDescription;
    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL)
    private List<Version> versions;
}
