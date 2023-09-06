package com.ssafy.soljigi.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.soljigi.api.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
}