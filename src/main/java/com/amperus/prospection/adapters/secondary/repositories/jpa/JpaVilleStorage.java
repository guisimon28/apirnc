package com.amperus.prospection.adapters.secondary.repositories.jpa;

import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.DepartementJpaEntity;
import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.VilleJpaEntity;
import com.amperus.prospection.businesslogic.models.Adresse;
import com.amperus.prospection.businesslogic.models.Copropriete;
import com.amperus.prospection.businesslogic.models.Departement;
import com.amperus.prospection.businesslogic.models.Ville;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class JpaVilleStorage {

    private final SpringVilleJpaRepository springVilleJpaRepository;

    private final JpaDepartementStorage jpaDepartementStorage;


    public JpaVilleStorage(SpringVilleJpaRepository springVilleJpaRepository, JpaDepartementStorage jpaDepartementStorage) {
        this.springVilleJpaRepository = springVilleJpaRepository;
        this.jpaDepartementStorage = jpaDepartementStorage;
    }

    public List<VilleJpaEntity> updateAndGetAllVilles(List<Copropriete> coproprietes) {
        List<Ville> villes = coproprietes.stream().map(Copropriete::adresse).filter(Objects::nonNull)
                .map(Adresse::ville).filter(Objects::nonNull).toList();
        List<DepartementJpaEntity> departementJpaEntities = jpaDepartementStorage.updateAndGetAllDepartements(villes);
        List<VilleJpaEntity> villeJpaEntities = springVilleJpaRepository.findAll();
        return createOrUpdate(villes, villeJpaEntities, departementJpaEntities);
    }

    private List<VilleJpaEntity> createOrUpdate(List<Ville> villes, List<VilleJpaEntity> villeJpaEntities, List<DepartementJpaEntity> departementJpaEntities) {
        villes.forEach(ville -> createOrUpdate(ville, villeJpaEntities, departementJpaEntities));
        return springVilleJpaRepository.saveAll(villeJpaEntities);
    }

    private void createOrUpdate(Ville ville, List<VilleJpaEntity> villeJpaEntities, List<DepartementJpaEntity> departementJpaEntities) {
        VilleJpaEntity villeJpaEntity = find(ville, villeJpaEntities).orElseGet(() -> {
            var newVille = new VilleJpaEntity();
            villeJpaEntities.add(newVille);
            findDepartement(ville.departement(), departementJpaEntities).ifPresent(newVille::setDepartement);
            return newVille;
        });
        villeJpaEntity.update(ville);
    }

    private static Optional<DepartementJpaEntity> findDepartement(Departement departement, List<DepartementJpaEntity> departementJpaEntities) {
        if (departement == null) {
            return Optional.empty();
        }
        return departementJpaEntities.stream().filter(d -> d.isSame(departement)).findFirst();
    }

    public Optional<VilleJpaEntity> find(Ville ville, List<VilleJpaEntity> villeJpaEntities) {
        return villeJpaEntities.stream().filter(v -> v.isSame(ville))
                .findFirst();
    }
}