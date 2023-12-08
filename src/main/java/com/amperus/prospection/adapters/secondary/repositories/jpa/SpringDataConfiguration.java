package com.amperus.prospection.adapters.secondary.repositories.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EntityScan("com.amperus.prospection.adapters.secondary.repositories.jpa.entities")
@EnableJpaRepositories("com.amperus.prospection.adapters.secondary.repositories.jpa")
@ComponentScan("com.amperus.prospection.adapters.secondary.repositories.jpa**")
public class SpringDataConfiguration {

}