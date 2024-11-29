package com.hand.demo.app.service.impl;

import com.hand.demo.api.dto.InvCountHeaderDTO;
import com.hand.demo.api.dto.WorkFlowEventRequestDTO;
import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.PageHelper;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.hand.demo.app.service.InvCountHeaderService;
import org.springframework.stereotype.Service;
import com.hand.demo.domain.entity.InvCountHeader;
import com.hand.demo.domain.repository.InvCountHeaderRepository;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Inventory Count Header Table(InvCountHeader)应用服务
 *
 * @author
 * @since 2024-11-29 08:49:40
 */
@Service
public class InvCountHeaderServiceImpl implements InvCountHeaderService {
    @Autowired
    private InvCountHeaderRepository invCountHeaderRepository;
    private Logger log;

    @Override
    public Page<InvCountHeader> selectList(PageRequest pageRequest, InvCountHeader invCountHeader) {
        return PageHelper.doPageAndSort(pageRequest, () -> invCountHeaderRepository.selectList(invCountHeader));
    }

    @Override
    public void saveData(List<InvCountHeader> invCountHeaders) {
        List<InvCountHeader> insertList = invCountHeaders.stream().filter(line -> line.getCountHeaderId() == null).collect(Collectors.toList());
        List<InvCountHeader> updateList = invCountHeaders.stream().filter(line -> line.getCountHeaderId() != null).collect(Collectors.toList());
        invCountHeaderRepository.batchInsertSelective(insertList);
        invCountHeaderRepository.batchUpdateByPrimaryKeySelective(updateList);
    }


//        @Override
//    public InvCountHeaderDTO approvalCallBack(Long organizationId, WorkFlowEventRequestDTO workFlowEventRequest){
//        InvCountHeaderDTO invCountHeaderDTO = new InvCountHeaderDTO();
//        InvCountHeader invCountHeader1 = new InvCountHeader();
//        invCountHeader1.setCountNumber(workFlowEventRequest.getBusinessKey());
//        InvCountHeader invCountHeader = invCountHeaderRepository
//                .selectOne(invCountHeader1);
//        InvCountHeader invCountHeader2 = new InvCountHeader();
//        if (invCountHeader == null){
//            invCountHeader2.setCountNumber(workFlowEventRequest.getBusinessKey());
//            invCountHeader2.setCountStatus(workFlowEventRequest.getDocStatus());
//            invCountHeader2.setWorkflowId(workFlowEventRequest.getWorkflowId());
//            invCountHeader2.setTenantId(0L);
//            invCountHeaderRepository.insertSelective(invCountHeader2);
//            log.info("Inserted new InvCountHeader for businessKey: {}", workFlowEventRequest.getBusinessKey());
//        }else{
//            log.info("InvCountHeader exists!");
//        }
//        BeanUtils.copyProperties(invCountHeader2, invCountHeaderDTO);
//        invCountHeaderDTO.setWorkflowId(workFlowEventRequest.getWorkflowId());
//        invCountHeaderDTO.setCountStatus(workFlowEventRequest.getDocStatus());
//        invCountHeaderDTO.setApprovedTime(workFlowEventRequest.getApprovedTime());
//        invCountHeaderRepository.updateOptional(invCountHeaderDTO, InvCountHeader.FIELD_WORKFLOW_ID, InvCountHeader.FIELD_APPROVED_TIME, InvCountHeader.FIELD_COUNT_STATUS);
//        return invCountHeaderDTO;
//    }
@Override
public InvCountHeader insertOrUpdate(InvCountHeaderDTO invCountRequest, Long tenantId) {
    InvCountHeader invCountHeader = invCountHeaderRepository.selectByCountNumber(invCountRequest.getBusinessKey());
    if (Objects.isNull(invCountHeader)) {
        create(invCountRequest, tenantId);
        return invCountHeaderRepository.selectByCountNumber(invCountRequest.getBusinessKey());
    }
    else {
        log.info("It is in update");
        invCountHeader.setApprovedTime(Date.from(Instant.now()));
        invCountHeader.setCountStatus(invCountRequest.getStatusDoc());
        invCountHeader.setWorkflowId(invCountRequest.getWorkFlowId());
        invCountHeader.setTenantId(tenantId);
        update(invCountHeader);
    }
    return invCountHeaderRepository.selectByCountNumber(invCountRequest.getBusinessKey());
}
    public void create(InvCountHeaderDTO invCountRequest, Long tenantId) {
        log.info("It is in create");
        InvCountHeader invCountHeader = new InvCountHeader();
        invCountHeader.setCountNumber(invCountRequest.getBusinessKey());
        invCountHeader.setCountStatus(invCountRequest.getStatusDoc());
        invCountHeader.setWorkflowId(invCountRequest.getWorkFlowId());
        invCountHeader.setTenantId(tenantId);
        invCountHeaderRepository.insertSelective(invCountHeader);
    }

    public void update(InvCountHeader updateInvCountHeader) {
        invCountHeaderRepository.updateByPrimaryKey(updateInvCountHeader);
    }
}

