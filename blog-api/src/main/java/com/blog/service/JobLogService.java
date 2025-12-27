package com.blog.service;


import com.blog.entity.JobLog;
import com.blog.model.dto.JobLogDTO;
import com.blog.model.dto.PageResultDTO;
import com.blog.model.vo.JobLogSearchVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface JobLogService extends IService<JobLog> {

    PageResultDTO<JobLogDTO> listJobLogs(JobLogSearchVO jobLogSearchVO);

    void deleteJobLogs(List<Integer> ids);

    void cleanJobLogs();

    List<String> listJobLogGroups();

}
