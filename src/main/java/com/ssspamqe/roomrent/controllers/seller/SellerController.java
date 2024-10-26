package com.ssspamqe.roomrent.controllers.seller;

import com.ssspamqe.roomrent.controllers.global.RoomRentResponse;
import com.ssspamqe.roomrent.domain.entities.users.Seller;
import com.ssspamqe.roomrent.service.SecurityUserService;
import com.ssspamqe.roomrent.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;
    private final SecurityUserService securityUserService;

    @PutMapping
    public ResponseEntity<RoomRentResponse<Seller>> register() {
        var currentUser = securityUserService.getCurrentUser();
        var seller = sellerService.registerWithUserId(currentUser.getId());
        return ResponseEntity
                .ok()
                .body(RoomRentResponse.of(seller));
    }

    @DeleteMapping
    public ResponseEntity<RoomRentResponse<Seller>> delete() {
        var currentUser = securityUserService.getCurrentUser();
        var seller = sellerService.deleteWithUserId(currentUser.getId());
        return ResponseEntity
                .ok()
                .body(RoomRentResponse.of(seller));
    }

    @PostMapping("/restore")
    public ResponseEntity<RoomRentResponse<Seller>> restore() {
        var currentUser = securityUserService.getCurrentUser();
        var seller = sellerService.restore(currentUser.getId());
        return ResponseEntity
                .ok()
                .body(RoomRentResponse.of(seller));
    }
}
