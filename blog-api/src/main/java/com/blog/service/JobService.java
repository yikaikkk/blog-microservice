package com.blog.service;

import com.blog.entity.Job;
import com.blog.model.dto.JobDTO;
import com.blog.model.dto.PageResultDTO;
import com.blog.model.vo.*;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface JobService extends IService<Job> {

    void saveJob(JobVO jobVO);

    void updateJob(JobVO jobVO);

    void deleteJobs(List<Integer> tagIds);

    JobDTO getJobById(Integer jobId);

    PageResultDTO<JobDTO> listJobs(JobSearchVO jobSearchVO);

    void updateJobStatus(JobStatusVO jobStatusVO);

    void runJob(JobRunVO jobRunVO);

    List<String> listJobGroups();

}
