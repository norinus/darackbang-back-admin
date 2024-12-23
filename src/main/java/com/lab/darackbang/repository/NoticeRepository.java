package com.lab.darackbang.repository;

import com.lab.darackbang.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long>,
        JpaSpecificationExecutor<Notice> {
}
