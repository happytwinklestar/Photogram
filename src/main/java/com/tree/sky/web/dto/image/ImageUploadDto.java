package com.tree.sky.web.dto.image;

import org.springframework.web.multipart.MultipartFile;

import com.tree.sky.domain.image.Image;
import com.tree.sky.domain.user.User;

import lombok.Data;

@Data
public class ImageUploadDto {

	private MultipartFile file;
	private String caption;	
	
	
	public Image toEntity(String postImageUrl, User user) {
		return Image.builder()
				.caption(caption)
				.postImageUrl(postImageUrl)
				.user(user)
				.build();
	}
}
