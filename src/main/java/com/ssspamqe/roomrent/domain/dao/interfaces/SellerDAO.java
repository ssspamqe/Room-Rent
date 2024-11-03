package com.ssspamqe.roomrent.domain.dao.interfaces;

import com.ssspamqe.roomrent.domain.entities.users.Seller;

public interface SellerDAO {

    Seller getById(Long id);

    Seller getByUserId(Long userId);

    boolean existsByUserId(Long userId);

    boolean existsById(Long id);

    Seller save(Seller seller);
}
