package com.hand.demo.app.service;

import com.hand.demo.api.dto.InvCountHeaderDTO;
import com.hand.demo.api.dto.WorkFlowEventRequestDTO;
import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import com.hand.demo.domain.entity.InvCountHeader;

import java.util.List;

/**
 * Inventory Count Header Table(InvCountHeader)应用服务
 *
 * @author
 * @since 2024-11-29 08:49:39
 */
public interface InvCountHeaderService {

    /**
     * 查询数据
     *
     * @param pageRequest     分页参数
     * @param invCountHeaders 查询条件
     * @return 返回值
     */
    Page<InvCountHeader> selectList(PageRequest pageRequest, InvCountHeader invCountHeaders);

    /**
     * 保存数据
     *
     * @param invCountHeaders 数据
     */
    void saveData(List<InvCountHeader> invCountHeaders);

//    InvCountHeaderDTO approvalCallBack(Long organizationId, WorkFlowEventRequestDTO workFlowEventRequest);

    InvCountHeader insertOrUpdate(InvCountHeaderDTO invCountRequest, Long tenantId);
}

