package com.ssafy.soljigi.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.soljigi.api.entity.Account;
import com.ssafy.soljigi.api.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	@Query(value = "select t from Transaction t "
		+ "where t.account = :account and t.summary = '1원 인증' "
		+ "order by t.transactionDateTime desc limit 1")
	Optional<Transaction> findLatestOneTransfer(@Param("account") Account account);
}
