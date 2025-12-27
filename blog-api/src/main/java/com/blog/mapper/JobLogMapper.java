package com.blog.mapper;

import com.blog.entity.JobLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobLogMapper extends BaseMapper<JobLog> {

    List<String> listJobLogGroups();

}
