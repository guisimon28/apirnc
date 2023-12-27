package com.amperus.prospection.adapters.secondary.repositories.jpa;

import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.SyndicatJpaEntity;
import com.amperus.prospection.businesslogic.models.Copropriete;
import com.amperus.prospection.businesslogic.models.Mandat;
import com.amperus.prospection.businesslogic.models.Syndicat;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class JpaSyndicatStorage {

    private final SpringSyndicatJpaRepository springSyndicatJpaRepository;

    public JpaSyndicatStorage(SpringSyndicatJpaRepository springSyndicatJpaRepository) {
        this.springSyndicatJpaRepository = springSyndicatJpaRepository;
    }

    @Transactional
    public List<SyndicatJpaEntity> updateAndGetAllSyndicats(List<Copropriete> coproprietes) {
        List<Syndicat> syndicats = coproprietes.stream().map(Copropriete::mandat)
                .filter(Objects::nonNull).map(Mandat::syndicat)
                .filter(Objects::nonNull).distinct().toList();
        List<SyndicatJpaEntity> syndicatJpaEntities = springSyndicatJpaRepository.findAll();
        return createOrUpdate(syndicats, syndicatJpaEntities);
    }

    private List<SyndicatJpaEntity> createOrUpdate(List<Syndicat> syndicats, List<SyndicatJpaEntity> syndicatJpaEntities) {
        syndicats.forEach(syndic -> createOrUpdate(syndic, syndicatJpaEntities));
        return springSyndicatJpaRepository.saveAll(syndicatJpaEntities);
    }

    private void createOrUpdate(Syndicat syndicat, List<SyndicatJpaEntity> syndicatJpaEntities) {
        var syndicatJpaEntity = find(syndicat, syndicatJpaEntities).orElseGet(() -> {
            var newSyndicat = new SyndicatJpaEntity();
            syndicatJpaEntities.add(newSyndicat);
            return newSyndicat;
        });
        syndicatJpaEntity.update(syndicat);
    }

    public Optional<SyndicatJpaEntity> find(Syndicat syndicat, List<SyndicatJpaEntity> syndicatJpaEntities) {
        if (syndicat.siret() == null) {
            return Optional.empty();
        }
        return syndicatJpaEntities.stream()
                .filter(s -> s.isSame(syndicat))
                .findFirst();
    }

}