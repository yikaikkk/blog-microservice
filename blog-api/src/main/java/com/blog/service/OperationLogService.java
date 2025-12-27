package com.blog.service;

import com.blog.entity.OperationLog;
import com.blog.model.dto.OperationLogDTO;
import com.blog.model.dto.PageResultDTO;
import com.blog.model.vo.ConditionVO;
import com.baomidou.mybatisplus.extension.service.IService;

public interface OperationLogService extends IService<OperationLog> {

    PageResultDTO<OperationLogDTO> listOperationLogs(ConditionVO conditionVO);

}
