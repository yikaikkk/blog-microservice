package com.blog.service;

import com.blog.entity.ExceptionLog;
import com.blog.model.dto.ExceptionLogDTO;
import com.blog.model.dto.PageResultDTO;
import com.blog.model.vo.ConditionVO;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ExceptionLogService extends IService<ExceptionLog> {

    PageResultDTO<ExceptionLogDTO> listExceptionLogs(ConditionVO conditionVO);

}
