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
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_company")
    private Long idCompany;
    @Column(name = "codigo_company")
    private String codigoCompany;
    @Column(name = "name_company")
    private String nameCompany;
    @Column(name = "description_company")
    private String descriptionCompany;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VersionCompany> versionCompanies;
}
