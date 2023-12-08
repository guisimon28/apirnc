package com.amperus.prospection.adapters.secondary.repositories.jpa;

import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.CoproprieteJpaEntity;
import com.amperus.prospection.adapters.secondary.repositories.jpa.mappers.CoproprieteMapper;
import com.amperus.prospection.businesslogic.gateways.repositories.CoproprieteRepository;
import com.amperus.prospection.businesslogic.models.Copropriete;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JpaCoproprieteRepository implements CoproprieteRepository {

    private SpringCoproprieteRepository springCoproprieteRepository;

    public JpaCoproprieteRepository(SpringCoproprieteRepository springCoproprieteRepository) {
        this.springCoproprieteRepository = springCoproprieteRepository;
    }

    @Override
    public void saveAll(List<Copropriete> coproprietes) {
        coproprietes.forEach(copropriete -> {
            CoproprieteJpaEntity coproprieteJpaEntity = CoproprieteMapper.convert(copropriete);
            springCoproprieteRepository.save(coproprieteJpaEntity);
        });

    }
}