package com.blog.mapper;

import com.blog.entity.Job;
import com.blog.model.dto.JobDTO;
import com.blog.model.vo.JobSearchVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobMapper extends BaseMapper<Job> {

    Integer countJobs(@Param("jobSearchVO") JobSearchVO jobSearchVO);

    List<JobDTO> listJobs(@Param("current") Long current, @Param("size") Long size, @Param("jobSearchVO")JobSearchVO jobSearchVO);

    List<String> listJobGroups();

}
