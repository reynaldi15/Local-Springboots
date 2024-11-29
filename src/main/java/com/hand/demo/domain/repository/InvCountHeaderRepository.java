package com.hand.demo.domain.repository;

import com.hand.demo.api.dto.InvCountHeaderDTO;
import org.hzero.mybatis.base.BaseRepository;
import com.hand.demo.domain.entity.InvCountHeader;

import java.util.List;

/**
 * Inventory Count Header Table(InvCountHeader)资源库
 *
 * @author
 * @since 2024-11-29 08:49:39
 */
public interface InvCountHeaderRepository extends BaseRepository<InvCountHeader> {
    /**
     * 查询
     *
     * @param invCountHeader 查询条件
     * @return 返回值
     */
    List<InvCountHeader> selectList(InvCountHeader invCountHeader);

    /**
     * 根据主键查询（可关联表）
     *
     * @param countHeaderId 主键
     * @return 返回值
     */
    InvCountHeader selectByPrimary(Long countHeaderId);

    void updateOptional(InvCountHeaderDTO invCountHeaderDTO, String fieldWorkflowId, String fieldApprovedTime, String fieldCountStatus);
    InvCountHeader selectByCountNumber(String countNumber);
}
