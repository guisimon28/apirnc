package com.amperus.prospection.adapters.secondary.repositories.jpa.mappers;

import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.CadastreJpaEntity;
import com.amperus.prospection.adapters.secondary.repositories.jpa.entities.InformationCadastraleJpaEntity;
import com.amperus.prospection.businesslogic.models.Cadastre;
import com.amperus.prospection.businesslogic.models.InformationCadastrale;

import java.util.ArrayList;

public class CadastreMapper {

    private CadastreMapper() {
    }

    public static CadastreJpaEntity convert(Cadastre cadastre) {
        if (cadastre == null) {
            return null;
        }
        CadastreJpaEntity cadastreJpaEntity = new CadastreJpaEntity();
        cadastreJpaEntity.setNombreParcelles(cadastre.nombreParcelles());
        cadastreJpaEntity.setInformationsCadastrales(new ArrayList<>());
        cadastre.informations().forEach(info -> {
            cadastreJpaEntity.getInformationsCadastrales().add(convert(info));
        });
        return cadastreJpaEntity;
    }

    public static InformationCadastraleJpaEntity convert(InformationCadastrale informationCadastrale) {
        if (informationCadastrale == null) {
            return null;
        }
        InformationCadastraleJpaEntity informationCadastraleJpaEntity = new InformationCadastraleJpaEntity();
        informationCadastraleJpaEntity.setReference(informationCadastrale.reference());
        informationCadastraleJpaEntity.setNumeroParcelle(informationCadastrale.numeroParcelle());
        informationCadastraleJpaEntity.setCodeInseeCommune(informationCadastrale.codeINSEECommune());
        informationCadastraleJpaEntity.setSection(informationCadastrale.section());
        informationCadastraleJpaEntity.setPrefixe(informationCadastrale.prefixe());
        return informationCadastraleJpaEntity;
    }

}
