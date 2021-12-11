package com.tree.sky.domain.user;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	
	@Column(length = 100,  unique = true) 
	private String username; 
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String name;
	private String website; // 웹 사이트
	private String bio; // 자기 소개
	@Column(nullable = false)
	private String email;
	private String phone;
	private String gender;
	private String role; // 권한
	
	
	private String profileImageUrl;
	private LocalDateTime createDate;
	
	@PrePersist 
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}

}
