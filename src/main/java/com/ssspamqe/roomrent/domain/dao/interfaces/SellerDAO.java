package com.ssspamqe.roomrent.domain.dao.interfaces;

import com.ssspamqe.roomrent.domain.entities.users.Seller;

public interface SellerDAO {

    Seller getByUserId(Long userId);

    boolean existsByUserId(Long userId);

    Seller save(Seller seller);
}
