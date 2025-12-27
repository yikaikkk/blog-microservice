package com.blog.service.impl;

import com.blog.entity.Photo;
import com.blog.entity.PhotoAlbum;
import com.blog.exception.BizException;
import com.blog.mapper.PhotoAlbumMapper;
import com.blog.mapper.PhotoMapper;
import com.blog.model.dto.PageResultDTO;
import com.blog.model.dto.PhotoAlbumAdminDTO;
import com.blog.model.dto.PhotoAlbumDTO;
import com.blog.model.vo.ConditionVO;
import com.blog.model.vo.PhotoAlbumVO;
import com.blog.service.PhotoAlbumService;
import com.blog.util.BeanCopyUtil;
import com.blog.util.PageUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.blog.constant.CommonConstant.FALSE;
import static com.blog.constant.CommonConstant.TRUE;
import static com.blog.enums.PhotoAlbumStatusEnum.PUBLIC;

@Service
public class PhotoAlbumServiceImpl extends ServiceImpl<PhotoAlbumMapper, PhotoAlbum> implements PhotoAlbumService {

    @Autowired
    private PhotoAlbumMapper photoAlbumMapper;

    @Autowired
    private PhotoMapper photoMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdatePhotoAlbum(PhotoAlbumVO photoAlbumVO) {
        PhotoAlbum album = photoAlbumMapper.selectOne(new LambdaQueryWrapper<PhotoAlbum>()
                .select(PhotoAlbum::getId)
                .eq(PhotoAlbum::getAlbumName, photoAlbumVO.getAlbumName()));
        if (Objects.nonNull(album) && !album.getId().equals(photoAlbumVO.getId())) {
            throw new BizException("相册名已存在");
        }
        PhotoAlbum photoAlbum = BeanCopyUtil.copyObject(photoAlbumVO, PhotoAlbum.class);
        this.saveOrUpdate(photoAlbum);
    }

    @Override
    public PageResultDTO<PhotoAlbumAdminDTO> listPhotoAlbumsAdmin(ConditionVO conditionVO) {
        Integer count = photoAlbumMapper.selectCount(new LambdaQueryWrapper<PhotoAlbum>()
                .like(StringUtils.isNotBlank(conditionVO.getKeywords()), PhotoAlbum::getAlbumName, conditionVO.getKeywords())
                .eq(PhotoAlbum::getIsDelete, FALSE));
        if (count == 0) {
            return new PageResultDTO<>();
        }
        List<PhotoAlbumAdminDTO> photoAlbumBacks = photoAlbumMapper.listPhotoAlbumsAdmin(PageUtil.getLimitCurrent(), PageUtil.getSize(), conditionVO);
        return new PageResultDTO<>(photoAlbumBacks, count);
    }

    @Override
    public List<PhotoAlbumDTO> listPhotoAlbumInfosAdmin() {
        List<PhotoAlbum> photoAlbums = photoAlbumMapper.selectList(new LambdaQueryWrapper<PhotoAlbum>()
                .eq(PhotoAlbum::getIsDelete, FALSE));
        return BeanCopyUtil.copyList(photoAlbums, PhotoAlbumDTO.class);
    }

    @Override
    public PhotoAlbumAdminDTO getPhotoAlbumByIdAdmin(Integer albumId) {
        PhotoAlbum photoAlbum = photoAlbumMapper.selectById(albumId);
        Integer photoCount = photoMapper.selectCount(new LambdaQueryWrapper<Photo>()
                .eq(Photo::getAlbumId, albumId)
                .eq(Photo::getIsDelete, FALSE));
        PhotoAlbumAdminDTO album = BeanCopyUtil.copyObject(photoAlbum, PhotoAlbumAdminDTO.class);
        album.setPhotoCount(photoCount);
        return album;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePhotoAlbumById(Integer albumId) {
        Integer count = photoMapper.selectCount(new LambdaQueryWrapper<Photo>()
                .eq(Photo::getAlbumId, albumId));
        if (count > 0) {
            photoAlbumMapper.updateById(PhotoAlbum.builder()
                    .id(albumId)
                    .isDelete(TRUE)
                    .build());
            photoMapper.update(new Photo(), new LambdaUpdateWrapper<Photo>()
                    .set(Photo::getIsDelete, TRUE)
                    .eq(Photo::getAlbumId, albumId));
        } else {
            photoAlbumMapper.deleteById(albumId);
        }
    }

    @Override
    public List<PhotoAlbumDTO> listPhotoAlbums() {
        List<PhotoAlbum> photoAlbumList = photoAlbumMapper.selectList(new LambdaQueryWrapper<PhotoAlbum>()
                .eq(PhotoAlbum::getStatus, PUBLIC.getStatus())
                .eq(PhotoAlbum::getIsDelete, FALSE)
                .orderByDesc(PhotoAlbum::getId));
        return BeanCopyUtil.copyList(photoAlbumList, PhotoAlbumDTO.class);
    }

}
