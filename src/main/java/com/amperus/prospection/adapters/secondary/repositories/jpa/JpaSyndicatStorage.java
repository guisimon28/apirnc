package com.amperus.prospection.adapters.secondary.repositories.jpa;

import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.SyndicatJpaEntity;
import com.amperus.prospection.businesslogic.models.Syndicat;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JpaSyndicatStorage {

    private final SpringSyndicatJpaRepository springSyndicatJpaRepository;

    public JpaSyndicatStorage(SpringSyndicatJpaRepository springSyndicatJpaRepository) {
        this.springSyndicatJpaRepository = springSyndicatJpaRepository;
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void saveAll(List<Syndicat> syndicats) {
        syndicats.forEach(this::createOrUpdate);
    }

    public void createOrUpdate(Syndicat syndicat) {
        SyndicatJpaEntity syndicatJpaEntity =
                springSyndicatJpaRepository.findBySiret(syndicat.siret()).orElseGet(SyndicatJpaEntity::new);
        syndicatJpaEntity.update(syndicat);
        springSyndicatJpaRepository.save(syndicatJpaEntity);
    }

    public Optional<SyndicatJpaEntity> findBySiret(String siret) {
        return springSyndicatJpaRepository.findBySiret(siret);
    }
}