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
public class Version {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "version_id")
    private Long versionId;
    @Column(name = "version")
    private String version;
    @Column(name = "version_description")
    private String versionDescription;
    @OneToMany(mappedBy = "version", cascade = CascadeType.ALL)
    private List<VersionCompany> versionCompanies;
    @ManyToOne
    @JoinColumn(name = "app_id")
    private Application application;
}
