package com.shreya.spring.controller;

import com.shreya.spring.exception.CouponNotFoundException;
import com.shreya.spring.exception.CouponServiceException;
import com.shreya.spring.model.Coupon;
import com.shreya.spring.service.CouponService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/couponManagement")
public class CouponController {

    private static final Logger log = LoggerFactory.getLogger(CouponController.class);

    @Autowired
    private CouponService couponService;

    @PostMapping
    public ResponseEntity<Boolean> saveCoupon(@RequestBody Coupon coupon) {
        log.info("API called: save coupon {}", coupon);
        try {
            boolean saved = couponService.saveCoupon(coupon);
            if (!saved) {
                return ResponseEntity.status(409).body(false);
            }
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            throw new CouponServiceException("Failed to save coupon", e);
        }
    }

    @GetMapping
    public ResponseEntity<List<Coupon>> getAllCoupons() {
        log.info("API called: get all coupons");
        return ResponseEntity.ok(couponService.getAllCoupons());
    }

    @GetMapping("coupon/{id}")
    public ResponseEntity<Coupon> getCouponById(@PathVariable Long id) {
        log.info("API called: get coupon by id {}", id);
        Coupon coupon = couponService.getCouponById(id);
        if (coupon == null) {
            throw new CouponNotFoundException("Coupon not found with id: " + id);
        }
        return ResponseEntity.ok(coupon);
    }

    @DeleteMapping("coupon/{id}")
    public ResponseEntity<Boolean> deleteCoupon(@PathVariable Long id) {
        log.info("API called: delete coupon by id {}", id);
        try {
            boolean deleted = couponService.deleteCoupon(id);
            if (!deleted) {
                throw new CouponNotFoundException("Coupon not found with id: " + id);
            }
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            throw new CouponServiceException("Failed to delete coupon", e);
        }
    }

    @PutMapping("coupon/{id}")
    public ResponseEntity<Boolean> updateCoupon(@PathVariable Long id, @RequestBody Coupon coupon) {
        coupon.setId(id);
        log.info("API called: update coupon {}", coupon);
        try {
            boolean updated = couponService.updateCoupon(coupon);
            if (!updated) {
                throw new CouponNotFoundException("Coupon not found with id: " + id);
            }
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            throw new CouponServiceException("Failed to update coupon", e);
        }
    }
}
