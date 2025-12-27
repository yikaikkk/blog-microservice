package com.blog.service;

import com.blog.entity.PhotoAlbum;
import com.blog.model.dto.PageResultDTO;
import com.blog.model.dto.PhotoAlbumAdminDTO;
import com.blog.model.dto.PhotoAlbumDTO;
import com.blog.model.vo.ConditionVO;
import com.blog.model.vo.PhotoAlbumVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface PhotoAlbumService extends IService<PhotoAlbum> {

    void saveOrUpdatePhotoAlbum(PhotoAlbumVO photoAlbumVO);

    PageResultDTO<PhotoAlbumAdminDTO> listPhotoAlbumsAdmin(ConditionVO condition);

    List<PhotoAlbumDTO> listPhotoAlbumInfosAdmin();

    PhotoAlbumAdminDTO getPhotoAlbumByIdAdmin(Integer albumId);

    void deletePhotoAlbumById(Integer albumId);

    List<PhotoAlbumDTO> listPhotoAlbums();

}
