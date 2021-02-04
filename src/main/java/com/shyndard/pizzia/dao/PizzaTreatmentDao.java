package com.shyndard.pizzia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.shyndard.pizzia.entity.PizzaTreatment;

import io.agroal.api.AgroalDataSource;

@ApplicationScoped
public class PizzaTreatmentDao {

    @Inject
    AgroalDataSource defaultDataSource;

    public Set<PizzaTreatment> findAll() {
        Set<PizzaTreatment> pizzaTreatmentList = new HashSet<>();
        try (Connection connection = defaultDataSource.getConnection(); Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM pizza_treatment");
            while (rs.next()) {
                PizzaTreatment instance = new PizzaTreatment();
                instance.setId(rs.getObject("id", UUID.class));
                instance.setImageUrl(rs.getString("image_url"));
                instance.setSuccess(rs.getInt("success"));
                instance.setMessage(rs.getString("message"));
                instance.setCreatedAt(rs.getTimestamp("creation_date"));
                pizzaTreatmentList.add(instance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pizzaTreatmentList;
    }

    public Optional<PizzaTreatment> findLast() {
        Optional<PizzaTreatment> pizza = Optional.empty();
        try (Connection connection = defaultDataSource.getConnection(); Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM pizza_treatment ORDER BY creation_date DESC LIMIT 1");
            if (rs.next()) {
                PizzaTreatment instance = new PizzaTreatment();
                instance.setId(rs.getObject("id", UUID.class));
                instance.setImageUrl(rs.getString("image_url"));
                instance.setSuccess(rs.getInt("success"));
                instance.setMessage(rs.getString("message"));
                instance.setCreatedAt(rs.getTimestamp("creation_date"));
                pizza = Optional.of(instance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pizza;
    }

    public int countTotal() {
        int total = 0;
        try (Connection connection = defaultDataSource.getConnection(); Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM pizza_treatment");
            if (rs.next()) {
                total = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public int countTotal(int success) {
        int total = 0;
        String sql = "SELECT count(*) FROM pizza_treatment WHERE success = ?";
        try (Connection connection = defaultDataSource.getConnection();
                PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, success);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                total = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public void create(int success, String imageUrl, String message) {
        String sql = "INSERT INTO pizza_treatment (success, image_url, message) VALUES (?, ?, ?)";
        try (Connection connection = defaultDataSource.getConnection();
                PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, success);
            pst.setString(2, imageUrl);
            pst.setString(3, message);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int deleteAll() {
        int count = 0;
        String sql = "DELETE FROM pizza_treatment";
        try (Connection connection = defaultDataSource.getConnection();
                PreparedStatement pst = connection.prepareStatement(sql)) {
            count = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public Set<PizzaTreatment> findLast10() {
        Set<PizzaTreatment> pizzaTreatmentList = new HashSet<>();
        try (Connection connection = defaultDataSource.getConnection(); Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM pizza_treatment ORDER BY creation_date DESC LIMIT 10");
            while (rs.next()) {
                PizzaTreatment instance = new PizzaTreatment();
                instance.setId(rs.getObject("id", UUID.class));
                instance.setImageUrl(rs.getString("image_url"));
                instance.setSuccess(rs.getInt("success"));
                instance.setMessage(rs.getString("message"));
                instance.setCreatedAt(rs.getTimestamp("creation_date"));
                pizzaTreatmentList.add(instance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pizzaTreatmentList;
    }
}