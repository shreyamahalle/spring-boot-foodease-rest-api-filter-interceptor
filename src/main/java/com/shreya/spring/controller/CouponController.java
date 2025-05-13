package com.shreya.spring.controller;

import com.shreya.spring.model.Coupon;
import com.shreya.spring.service.CouponService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/couponManagement")
public class CouponController {

    private final Logger log = LoggerFactory.getLogger(CouponController.class);

    @Autowired
    private CouponService couponService;

    @PostMapping("/coupon")
    public boolean saveCoupon(@RequestBody Coupon coupon) throws SQLException {
        log.info("API called: save coupon {}", coupon);
        return couponService.saveCoupon(coupon);
    }

    @GetMapping("/coupon")
    public List<Coupon> getAllCoupons() {
        log.info("API called: Get all coupons");
        return couponService.getAllCoupons();
    }

    @GetMapping("/coupon/{id}")
    public Coupon getCouponById(@PathVariable Long id) {
        log.info("API called: Get coupon by id {}", id);
        return couponService.getCouponById(id);
    }

    @DeleteMapping("/coupon/{id}")
    public boolean deleteCoupon(@PathVariable Long id) {
        log.info("API called: Delete coupon by id {}", id);
        return couponService.deleteCoupon(id);
    }

    @PutMapping("/coupon/{id}")
    public boolean updateCoupon(@PathVariable Long id, @RequestBody Coupon coupon) {
        coupon.setId(id);
        log.info("API called: Update coupon {}", coupon);
        return couponService.updateCoupon(coupon);
    }
}
