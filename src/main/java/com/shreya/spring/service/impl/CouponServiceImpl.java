package com.shreya.spring.service.impl;

import com.shreya.spring.model.Coupon;
import com.shreya.spring.repository.CouponRepository;
import com.shreya.spring.service.CouponService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {

    private final Logger log = LoggerFactory.getLogger(CouponServiceImpl.class);

    @Autowired
    private CouponRepository couponRepository;

    @Override
    public boolean saveCoupon(Coupon coupon) throws SQLException {
        log.info("Saving coupon: {}", coupon);
        return couponRepository.saveCoupon(coupon);
    }

    @Override
    public List<Coupon> getAllCoupons() {
        log.info("Fetching all coupons");
        return couponRepository.getAllCoupons();
    }

    @Override
    public Coupon getCouponById(Long id) {
        log.info("Fetching coupon by id: {}", id);
        return couponRepository.getCouponById(id);
    }

    @Override
    public boolean deleteCoupon(Long id) {
        log.info("Deleting coupon with id: {}", id);
        return couponRepository.deleteCoupon(id);
    }

    @Override
    public boolean updateCoupon(Coupon coupon) {
        log.info("Updating coupon: {}", coupon);
        return couponRepository.updateCoupon(coupon);
    }
}
