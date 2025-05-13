package com.shreya.spring.repository;

import com.shreya.spring.model.Coupon;
import com.shreya.spring.service.ConnectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CouponRepository {

    private final Logger log = LoggerFactory.getLogger(CouponRepository.class);

    public boolean saveCoupon(Coupon coupon) {
        String query = "INSERT INTO coupon (code, description, discount_amount, active) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionService.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, coupon.getCode());
            ps.setString(2, coupon.getDescription());
            ps.setDouble(3, coupon.getDiscountAmount());
            ps.setBoolean(4, coupon.isActive());

            boolean result = ps.executeUpdate() > 0;
            log.info("Coupon saved: {}", coupon);
            return result;
        } catch (SQLException e) {
            log.error("Error saving coupon: {}", coupon, e);
            throw new RuntimeException("Error saving coupon", e);
        }
    }

    public List<Coupon> getAllCoupons() {
        List<Coupon> coupons = new ArrayList<>();
        String query = "SELECT * FROM coupon";
        try (Connection conn = ConnectionService.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                coupons.add(new Coupon(
                        rs.getLong("id"),
                        rs.getString("code"),
                        rs.getString("description"),
                        rs.getDouble("discount_amount"),
                        rs.getBoolean("active")
                ));
            }
            log.info("Retrieved {} coupons", coupons.size());
        } catch (SQLException e) {
            log.error("Error retrieving coupons", e);
            throw new RuntimeException("Error retrieving coupons", e);
        }
        return coupons;
    }

    public Coupon getCouponById(Long id) {
        String query = "SELECT * FROM coupon WHERE id = ?";
        try (Connection conn = ConnectionService.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Coupon coupon = new Coupon(
                            rs.getLong("id"),
                            rs.getString("code"),
                            rs.getString("description"),
                            rs.getDouble("discount_amount"),
                            rs.getBoolean("active")
                    );
                    log.info("Found coupon with id: {}", id);
                    return coupon;
                }
            }
        } catch (SQLException e) {
            log.error("Error finding coupon with id: {}", id, e);
        }
        return null;
    }

    public boolean deleteCoupon(Long id) {
        String query = "DELETE FROM coupon WHERE id = ?";
        try (Connection conn = ConnectionService.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setLong(1, id);
            boolean result = ps.executeUpdate() > 0;
            if (result) {
                log.info("Coupon deleted with id: {}", id);
            } else {
                log.warn("Coupon with id {} not found", id);
            }
            return result;
        } catch (SQLException e) {
            log.error("Error deleting coupon with id: {}", id, e);
            throw new RuntimeException("Error deleting coupon", e);
        }
    }

    public boolean updateCoupon(Coupon coupon) {
        String query = "UPDATE coupon SET code = ?, description = ?, discount_amount = ?, active = ? WHERE id = ?";
        try (Connection conn = ConnectionService.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, coupon.getCode());
            ps.setString(2, coupon.getDescription());
            ps.setDouble(3, coupon.getDiscountAmount());
            ps.setBoolean(4, coupon.isActive());
            ps.setLong(5, coupon.getId());

            boolean result = ps.executeUpdate() > 0;
            if (result) {
                log.info("Updated coupon with id: {}", coupon.getId());
            } else {
                log.warn("Coupon with id {} not found", coupon.getId());
            }
            return result;
        } catch (SQLException e) {
            log.error("Error updating coupon with id: {}", coupon.getId(), e);
            throw new RuntimeException("Error updating coupon", e);
        }
    }
}
