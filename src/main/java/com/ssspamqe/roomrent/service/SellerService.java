package com.ssspamqe.roomrent.service;

import com.ssspamqe.roomrent.domain.entities.users.Seller;
import com.ssspamqe.roomrent.domain.repositories.SellerRepository;
import com.ssspamqe.roomrent.service.exceptions.seller.NoSuchSellerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerService {

    private final SellerRepository sellerRepository;
    private final SecurityUserService securityUserService;

    public Seller register() {
        var currentUser = securityUserService.getCurrentUser();
        var currentUserId = currentUser.getId();

        var oldSeller = sellerRepository.findByUserId(currentUserId);
        if (oldSeller.isPresent()) {
            return oldSeller.get();
        }

        var newSeller = new Seller();
        newSeller.setUser(currentUser);
        return sellerRepository.save(newSeller);
    }

    public Seller delete() {
        var currentUserId = securityUserService.getCurrentUser().getId();

        var savedSeller = sellerRepository.findByUserId(currentUserId)
                .orElseThrow(
                        () -> NoSuchSellerException.forUserId(currentUserId)
                );
        savedSeller.setDeleted(true);
        return sellerRepository.save(savedSeller);
    }

    public Seller restore() {
        var currentUserId = securityUserService.getCurrentUser().getId();

        var savedSeller = sellerRepository.findByUserId(currentUserId)
                .orElseThrow(
                        () -> NoSuchSellerException.forUserId(currentUserId)
                );
        savedSeller.setDeleted(false);
        return sellerRepository.save(savedSeller);
    }
}
