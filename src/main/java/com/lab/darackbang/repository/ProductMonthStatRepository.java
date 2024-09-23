package com.lab.darackbang.repository;

import com.lab.darackbang.entity.ProductMonthStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductMonthStatRepository extends JpaRepository<ProductMonthStat, Long>,
        JpaSpecificationExecutor<ProductMonthStat> {

    Optional<ProductMonthStat> findByMonthAndYearAndProductName(String month, String year, String productName);
}