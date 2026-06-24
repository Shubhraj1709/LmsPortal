package com.lmsportal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lmsportal.models.Reply;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
}

