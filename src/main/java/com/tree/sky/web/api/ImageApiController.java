package com.tree.sky.web.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tree.sky.config.auth.PrincipalDetails;
import com.tree.sky.domain.image.Image;
import com.tree.sky.service.ImageService;
import com.tree.sky.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ImageApiController {

	private final ImageService imageService;
	
	@GetMapping("/api/image")
	public ResponseEntity<?> imageStory(@AuthenticationPrincipal PrincipalDetails principalDetails, 
			@PageableDefault(size=3) Pageable pageable){
		Page<Image> images =  imageService.이미지스토리(principalDetails.getUser().getId(), pageable);
		return new ResponseEntity<>(new CMRespDto<>(1, "성공", images), HttpStatus.OK);
	}
}
