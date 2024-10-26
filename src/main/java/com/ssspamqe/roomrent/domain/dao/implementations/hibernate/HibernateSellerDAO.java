package com.ssspamqe.roomrent.domain.dao.implementations.hibernate;

import com.ssspamqe.roomrent.domain.dao.interfaces.SellerDAO;
import com.ssspamqe.roomrent.domain.entities.users.Seller;
import com.ssspamqe.roomrent.domain.repositories.SellerRepository;
import com.ssspamqe.roomrent.service.exceptions.seller.NoSuchSellerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HibernateSellerDAO implements SellerDAO {

    private final SellerRepository sellerRepository;

    @Override
    public Seller getByUserId(Long userId) {
        return sellerRepository.findByUserId(userId)
                .orElseThrow(() -> NoSuchSellerException.withUserId(userId));
    }

    @Override
    public boolean existsByUserId(Long userId) {
        return sellerRepository.existsByUserId(userId);
    }

    @Override
    public Seller save(Seller seller) {
        return sellerRepository.save(seller);
    }
}
