-- ============================================
-- RMS (Restaurant Management System) — MySQL Schema
-- Run this script to set up the database.
-- ============================================

CREATE DATABASE IF NOT EXISTS rms_db;
USE rms_db;

-- =====================
-- USERS TABLE
-- =====================
CREATE TABLE IF NOT EXISTS users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    age INT,
    gender VARCHAR(20),
    password VARCHAR(255) NOT NULL,
    profile_pic LONGBLOB,
    role VARCHAR(20) DEFAULT 'user'  -- 'user' or 'admin'
);

-- =====================
-- FOODS TABLE
-- =====================
CREATE TABLE IF NOT EXISTS foods (
    food_id INT PRIMARY KEY,
    food_name VARCHAR(100) NOT NULL,
    food_price INT NOT NULL,
    food_category VARCHAR(50) NOT NULL,
    food_img VARCHAR(255)
);

-- =====================
-- BOOKING TABLE
-- =====================
CREATE TABLE IF NOT EXISTS booking (
    booking_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(150),
    table_type VARCHAR(50),
    phone_no VARCHAR(15),
    placement VARCHAR(50),
    booking_date DATE,
    start_time VARCHAR(20),
    end_time VARCHAR(20),
    note TEXT
);

-- =====================
-- ORDER_DETAILS TABLE
-- =====================
CREATE TABLE IF NOT EXISTS order_details (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(100),
    phone_no VARCHAR(15),
    food_details TEXT,
    total_amount INT,
    payment_status VARCHAR(20),
    order_date DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- =====================
-- DEFAULT ADMIN USER
-- =====================
INSERT INTO users (name, email, age, gender, password, role) 
VALUES ('Admin', 'admin@gmail.com', 30, 'Male', 'Admin@123', 'admin')
ON DUPLICATE KEY UPDATE name = name;
