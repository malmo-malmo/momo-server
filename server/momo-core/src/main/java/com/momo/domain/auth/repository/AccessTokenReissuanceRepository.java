package com.momo.domain.auth.repository;


import com.momo.domain.auth.entity.AccessTokenReissuance;
import org.springframework.data.repository.CrudRepository;

public interface AccessTokenReissuanceRepository extends CrudRepository<AccessTokenReissuance, String> {

}
