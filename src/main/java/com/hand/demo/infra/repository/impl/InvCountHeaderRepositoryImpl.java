package com.hand.demo.infra.repository.impl;

import com.hand.demo.api.dto.InvCountHeaderDTO;
import org.apache.commons.collections.CollectionUtils;
import org.hzero.mybatis.base.impl.BaseRepositoryImpl;
import org.springframework.stereotype.Component;
import com.hand.demo.domain.entity.InvCountHeader;
import com.hand.demo.domain.repository.InvCountHeaderRepository;
import com.hand.demo.infra.mapper.InvCountHeaderMapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * Inventory Count Header Table(InvCountHeader)资源库
 *
 * @author
 * @since 2024-11-29 08:49:39
 */
@Component
public class InvCountHeaderRepositoryImpl extends BaseRepositoryImpl<InvCountHeader> implements InvCountHeaderRepository {
    @Resource
    private InvCountHeaderMapper invCountHeaderMapper;

    @Override
    public List<InvCountHeader> selectList(InvCountHeader invCountHeader) {
        return invCountHeaderMapper.selectList(invCountHeader);
    }

    @Override
    public InvCountHeader selectByPrimary(Long countHeaderId) {
        InvCountHeader invCountHeader = new InvCountHeader();
        invCountHeader.setCountHeaderId(countHeaderId);
        List<InvCountHeader> invCountHeaders = invCountHeaderMapper.selectList(invCountHeader);
        if (invCountHeaders.size() == 0) {
            return null;
        }
        return invCountHeaders.get(0);
    }

    @Override
    public void updateOptional(InvCountHeaderDTO invCountHeaderDTO, String fieldWorkflowId, String fieldApprovedTime, String fieldCountStatus) {

    }
    public InvCountHeader selectByCountNumber(String countNumber) {
        return invCountHeaderMapper.findByCountNumber(countNumber);
    }

}

