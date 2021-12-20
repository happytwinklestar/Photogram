package com.tree.sky.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tree.sky.config.auth.PrincipalDetails;
import com.tree.sky.domain.image.Image;
import com.tree.sky.domain.image.ImageRepository;
import com.tree.sky.web.dto.image.ImageUploadDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {
	
	private final ImageRepository imageRepository;
	
	
	
	@Transactional(readOnly = true) 
	public Page<Image> 이미지스토리(int principalId, Pageable pageable){
		Page<Image> images = imageRepository.mStory(principalId, pageable);
		
		
		images.forEach((image)->{
			
			image.setLikeCount(image.getLikes().size());
			
			image.getLikes().forEach((like) -> {
				if(like.getUser().getId() == principalId) { 
					image.setLikeState(true);
				}
			});
			
		});
		
		
		return images;
	}
	
	
	
	
	
	@Value("${file.path}")
	private String uploadFolder;
	
	@Transactional
	public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
		UUID uuid = UUID.randomUUID(); 
		String imageFileName = uuid+"_"+imageUploadDto.getFile().getOriginalFilename(); 
		System.out.println("이미지 파일이름 : "+imageFileName);
		
		Path imageFilePath = Paths.get(uploadFolder+imageFileName);
		
		try {
			Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser()); 
		imageRepository.save(image);
	
	}
}
