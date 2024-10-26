package com.ssspamqe.roomrent.service;

import com.ssspamqe.roomrent.domain.dao.interfaces.SellerDAO;
import com.ssspamqe.roomrent.domain.dao.interfaces.UserDAO;
import com.ssspamqe.roomrent.domain.entities.users.Seller;
import com.ssspamqe.roomrent.service.exceptions.user.NoSuchUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerService {

    private final SecurityUserService securityUserService;
    private final UserDAO userDAO;
    private final SellerDAO sellerDAO;

    public Seller registerWithUserId(Long userId) {
        var user = userDAO.getById(userId);

        if (sellerDAO.existsByUserId(userId)) {
            var savedSeller = sellerDAO.getByUserId(userId);
            if (savedSeller.isDeleted()) {
                return restore(userId);
            } else {
                return savedSeller;
            }
        }

        var newSeller = new Seller();
        newSeller.setUser(user);
        return sellerDAO.save(newSeller);
    }

    public Seller deleteWithUserId(Long userId) {
        if (!userDAO.existsById(userId)) {
            throw NoSuchUserException.withId(userId);
        }

        var savedSeller = sellerDAO.getByUserId(userId);
        savedSeller.setDeleted(true);
        return sellerDAO.save(savedSeller);
    }

    public Seller restore(Long userId) {
        var savedSeller = sellerDAO.getByUserId(userId);
        savedSeller.setDeleted(false);
        return sellerDAO.save(savedSeller);
    }
}
