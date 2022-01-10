package com.backend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;


@Repository
public class StarterDao {
	
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public StarterDao() {
		super();
	}
	
	public void createInitialTables() {
		String que;
		que = "CREATE TABLE IF NOT EXISTS `branch` (`id` INT NOT NULL AUTO_INCREMENT, `name` VARCHAR(100) NOT NULL, PRIMARY KEY(`id`) ); ";
		jdbcTemplate.execute(que);
		que = "CREATE TABLE IF NOT EXISTS `hostels` (`id` INT NOT NULL AUTO_INCREMENT, `name` VARCHAR(100) NOT NULL UNIQUE, `capacity` INT NOT NULL, `status` BOOLEAN NOT NULL, PRIMARY KEY (`id`));";
		jdbcTemplate.execute(que);
		que = "CREATE TABLE IF NOT EXISTS `wardens` (`id` INT NOT NULL AUTO_INCREMENT, `name` VARCHAR(100) NOT NULL, `email` VARCHAR(100) NOT NULL UNIQUE, `password` VARCHAR(60) NOT NULL, `phone_no` VARCHAR(15) NOT NULL UNIQUE, `hostel_id` INT NULL, PRIMARY KEY (`id`), FOREIGN KEY(`hostel_id`) REFERENCES `hostels`(`id`));";
		jdbcTemplate.execute(que);
		que = "CREATE TABLE IF NOT EXISTS `students` (`reg_no` INT NOT NULL,`password` VARCHAR(60) NOT NULL,`name` VARCHAR(100) NOT NULL,`semester` INT NOT NULL,`address` VARCHAR(250) NOT NULL,`personal_mob` VARCHAR(15) NOT NULL,`parent_mob` VARCHAR(15) NOT NULL,`branch_id` int NOT NULL,`room_no` INT NOT NULL,`hostel_id` INT NOT NULL,`email` VARCHAR(100) NOT NULL,`gender` VARCHAR(6) NOT NULL,`dob` DATE NOT NULL,`adhaarcard_no` VARCHAR(45) NOT NULL,`blackdots` INT NOT NULL , PRIMARY KEY (`reg_no`), FOREIGN KEY (`hostel_id`) REFERENCES `hostels`(`id`), FOREIGN KEY (`branch_id`) REFERENCES `branch`(`id`), UNIQUE INDEX `reg_no_UNIQUE` (`reg_no` ASC));";
		jdbcTemplate.execute(que);		
		que= "CREATE TABLE IF NOT EXISTS `guards` (`id` INT NOT NULL AUTO_INCREMENT, `name` VARCHAR(100) NOT NULL, `email` VARCHAR(100) NOT NULL UNIQUE, `password` VARCHAR(60) NOT NULL, `phone_no` VARCHAR(15) NULL, `hostel_id` INT NULL, PRIMARY KEY (`id`), FOREIGN KEY(`hostel_id`) REFERENCES `hostels`(`id`));";
		jdbcTemplate.execute(que);
		que="CREATE TABLE IF NOT EXISTS `check_out` (`reg_no` INT NOT NULL, `check_out_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, PRIMARY KEY (`reg_no`), FOREIGN KEY(`reg_no`) REFERENCES students(`reg_no`));";
		jdbcTemplate.execute(que);	
		que="CREATE TABLE IF NOT EXISTS `messmenu` (`id` INT NOT NULL AUTO_INCREMENT,`hostel_id` INT NOT NULL,`day` VARCHAR(50) NOT NULL, `breakfast` VARCHAR(255) NULL DEFAULT 'not set',`lunch` VARCHAR(255) NULL DEFAULT 'not set',`dinner` VARCHAR(255) NULL DEFAULT 'not set',PRIMARY KEY (`id`),FOREIGN KEY(`hostel_id`) REFERENCES `hostels`(`id`));";
		jdbcTemplate.execute(que);
		que = "CREATE TABLE IF NOT EXISTS `notices` (`id` INT NOT NULL AUTO_INCREMENT, `notice` VARCHAR(250) NOT NULL, `hostel_id` INT NOT NULL, PRIMARY KEY (`id`));";
		jdbcTemplate.execute(que);
		insertBranches();
		insertDummyStudents(); //just for testing
		insertDummyGuards();   //just for testing
		insertDummyWardens();  //just for testing
		insertDummyHostels();  //just for testing
	}
	
	public void insertBranches() {
		String que;
		que = "INSERT IGNORE INTO `branch` (`id`, `name`) VALUES ('1', 'Information Technology');";
		jdbcTemplate.execute(que);
		que = "INSERT IGNORE INTO `branch` (`id`, `name`) VALUES ('2', 'Computer Science Engineering');";
		jdbcTemplate.execute(que);
		que = "INSERT IGNORE INTO `branch` (`id`, `name`) VALUES ('3', 'Electrical Enginnering');";
		jdbcTemplate.execute(que);
		que = "INSERT IGNORE INTO `branch` (`id`, `name`) VALUES ('4', 'Mechanical Enginnering');";
		jdbcTemplate.execute(que);
		que = "INSERT IGNORE INTO `branch` (`id`, `name`) VALUES ('5', 'Civil Enginnering');";
		jdbcTemplate.execute(que);
		que = "INSERT IGNORE INTO `branch` (`id`, `name`) VALUES ('6', 'Chemical Enginnering');";
		jdbcTemplate.execute(que);
		que = "INSERT IGNORE INTO `branch` (`id`, `name`) VALUES ('7', 'Electronics and Communication Enginnering');";
		jdbcTemplate.execute(que);
		que = "INSERT IGNORE INTO `branch` (`id`, `name`) VALUES ('8', 'Production Enginnering');";
		jdbcTemplate.execute(que);
		que = "INSERT IGNORE INTO `branch` (`id`, `name`) VALUES ('9', 'Biotechnology');";
		jdbcTemplate.execute(que);
	}
	
	public void insertDummyStudents() {
		String encodedPassword = passwordEncoder.encode("20198018");
		String que = "INSERT IGNORE INTO `students` (`reg_no`, `password`, `name`, `semester`, `address`, `personal_mob`, `parent_mob`, `branch_id`, `room_no`, `hostel_id`, `email`, `gender`, `dob`, `adhaarcard_no`, `blackdots`) VALUES ('20198018', ?, 'Yash', '6', 'Lakhisarai', '7288228287', '8282828827', '2', '203', '2', 'yash@mnnit.ac.in', 'Male', '2000-12-02', '7339939387838', '0');";
		jdbcTemplate.update(que, encodedPassword);
		encodedPassword = passwordEncoder.encode("20194034"); 
		que = "INSERT IGNORE INTO `students` (`reg_no`, `password`, `name`, `semester`, `address`, `personal_mob`, `parent_mob`, `branch_id`, `room_no`, `hostel_id`, `email`, `gender`, `dob`, `adhaarcard_no`, `blackdots`) VALUES ('20193045', ?, 'Stuti', '7', 'MP', '7288228247', '8282828847', '6', '205', '3', 'stuti@gmail.com', 'Female', '1999-11-09', '4439939387838', '0');";
		jdbcTemplate.update(que, encodedPassword);
	}
	
	public void insertDummyGuards() {
		String encodedPassword = passwordEncoder.encode("ramesh_123");
		String que="INSERT IGNORE INTO `guards` (`name`, `email`, `password`, `phone_no`, `hostel_id`) VALUES ('Ramesh', 'ramesh@mnnit.ac.in', ?, '12345667', '2');";
		jdbcTemplate.update(que, encodedPassword);
		encodedPassword = passwordEncoder.encode("suresh_123");
		que="INSERT IGNORE INTO `guards` (`name`, `email`, `password`, `phone_no`, `hostel_id`) VALUES ('Suresh', 'suresh@mnnit.ac.in', ?, '12345667', '3');";
		jdbcTemplate.update(que, encodedPassword);
	}
	
	public void insertDummyWardens() {
		String encodedPassword = passwordEncoder.encode("admin_123");
		String que="INSERT IGNORE INTO `wardens` (`name`, `email`, `password`, `phone_no`, `hostel_id`) VALUES ('SuperAdmin', 'superadmin@mnnit.ac.in', ?, '6200075988', '1');";
		jdbcTemplate.update(que, encodedPassword);
		encodedPassword = passwordEncoder.encode("shivdutt_123");
		que="INSERT IGNORE INTO `wardens` (`name`, `email`, `password`, `phone_no`, `hostel_id`) VALUES ('ShivDutt', 'sd@mnnit.ac.in', ?, '12345667', '2');";
		jdbcTemplate.update(que, encodedPassword);
		encodedPassword = passwordEncoder.encode("monika_123");
		que="INSERT IGNORE INTO `wardens` (`name`, `email`, `password`, `phone_no`, `hostel_id`) VALUES ('Monika Gupta', 'mg@mnnit.ac.in', ?, '12345687', '3');";
		jdbcTemplate.update(que, encodedPassword);
	}
	
	public void insertDummyHostels() {
		String que = "INSERT IGNORE INTO `hostels`(`name`, `capacity`, `status`) VALUES ('superadmin', '0', '1');";
		jdbcTemplate.execute(que);
		que="INSERT IGNORE INTO `hostels` (`name`, `capacity`, `status`) VALUES ('svbh', '800', '1');";
		jdbcTemplate.execute(que);
		que="INSERT IGNORE INTO `hostels` (`name`, `capacity`, `status`) VALUES ('kngh', '600', '1');";
		jdbcTemplate.execute(que);
	}
}
