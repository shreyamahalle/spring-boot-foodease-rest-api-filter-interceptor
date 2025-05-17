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
        if (couponRepository.saveCoupon(coupon.getCode())) {
            throw new CouponAlreadyExistsException("Coupon already exists with code: " + coupon.getCode());
        }
        couponRepository.saveCoupon(String.valueOf(coupon));
        return true;
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
        if (!couponRepository.deleteCoupon(id)) {
            throw new CouponNotFoundException("Coupon not found with id: " + id);
        }
        couponRepository.deleteCoupon(id);
        return true;
    }

    @Override
    public boolean updateCoupon(Coupon coupon) {
        log.info("Updating coupon: {}", coupon);
        if (!couponRepository.updateCoupon(coupon.getId())) {
            throw new CouponNotFoundException("Coupon not found with id: " + coupon.getId());
        }
        couponRepository.updateCoupon(coupon.getId());
        return true;
    }
}
