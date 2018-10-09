package com.paascloud.provider.mapper;

import com.paascloud.provider.model.domain.OmcOrderDetail;

import com.passcloud.common.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The interface Omc order detail mapper.
 *
 * @author liyuzhang
 */
@Mapper
@Component
public interface OmcOrderDetailMapper extends MyMapper<OmcOrderDetail> {
	/**
	 * Gets list by order no user id.
	 *
	 * @param orderNo the order no
	 * @param userId  the user id
	 *
	 * @return the list by order no user id
	 */
	List<OmcOrderDetail> getListByOrderNoUserId(@Param("orderNo") String orderNo, @Param("userId") Long userId);

	/**
	 * Gets list by order no.
	 *
	 * @param orderNo the order no
	 *
	 * @return the list by order no
	 */
	List<OmcOrderDetail> getListByOrderNo(String orderNo);

	/**
	 * Batch insert order detail int.
	 *
	 * @param orderItemList the order item list
	 *
	 * @return the int
	 */
	int batchInsertOrderDetail(@Param("orderDetailList") List<OmcOrderDetail> orderItemList);
}