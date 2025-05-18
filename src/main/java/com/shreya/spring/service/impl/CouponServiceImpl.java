package com.shreya.spring.service.impl;

import com.shreya.spring.exception.CouponAlreadyExistsException;
import com.shreya.spring.exception.CouponNotFoundException;
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
        List<Coupon> allCoupons = couponRepository.getAllCoupons();
        boolean exists = allCoupons.stream()
                .anyMatch(c -> c.getCode().equalsIgnoreCase(coupon.getCode()));
        if (exists) {
            throw new CouponAlreadyExistsException("Coupon already exists with code: " + coupon.getCode());
        }
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
        Coupon coupon = couponRepository.getCouponById(id);
        if (coupon == null) {
            throw new CouponNotFoundException("Coupon not found with id: " + id);
        }
        return coupon;
    }

    @Override
    public boolean deleteCoupon(Long id) {
        log.info("Deleting coupon with id: {}", id);
        boolean deleted = couponRepository.deleteCoupon(id);
        if (!deleted) {
            throw new CouponNotFoundException("Coupon not found with id: " + id);
        }
        return deleted;
    }

    @Override
    public boolean updateCoupon(Coupon coupon) {
        log.info("Updating coupon: {}", coupon);
        boolean updated = couponRepository.updateCoupon(coupon);
        if (!updated) {
            throw new CouponNotFoundException("Coupon not found with id: " + coupon.getId());
        }
        return updated;
    }
}
