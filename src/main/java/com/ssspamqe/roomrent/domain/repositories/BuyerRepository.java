package com.ssspamqe.roomrent.domain.repositories;

import com.ssspamqe.roomrent.domain.entities.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository<Long, Buyer> {
}
