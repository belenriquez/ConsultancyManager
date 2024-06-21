package com.uade.consultancymanager.repository;

import com.uade.consultancymanager.entity.AuthToken;
import org.springframework.data.repository.CrudRepository;

public interface AuthTokenRepository extends CrudRepository<AuthToken, String> {
}
