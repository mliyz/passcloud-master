package com.paascloud.provider.service;

import com.paascloud.provider.model.domain.MdcProductCategory;
import com.paascloud.provider.model.dto.ProductCategoryDto;
import com.paascloud.provider.model.vo.MdcCategoryVo;
import com.passcloud.common.base.dto.LoginAuthDto;
import com.passcloud.common.base.dto.UpdateStatusDto;
import com.passcloud.common.core.support.IService;

import java.util.List;

/**
 * The interface Mdc product category service.
 *
 * @author liyuzhang
 */
public interface MdcProductCategoryService extends IService<MdcProductCategory> {

	/**
	 * Gets category dto list.
	 *
	 * @param categoryId the category id
	 *
	 * @return the category dto list
	 */
	List<ProductCategoryDto> getCategoryDtoList(Long categoryId);

	/**
	 * Gets product category list by pid.
	 *
	 * @param pid the pid
	 *
	 * @return the product category list by pid
	 */
	List<MdcProductCategory> getProductCategoryListByPid(Long pid);

	/**
	 * 递归查询本节点的id及孩子节点的id.
	 *
	 * @param categoryId the category id
	 *
	 * @return the list
	 */
	List<Long> selectCategoryAndChildrenById(Long categoryId);

	/**
	 * Gets by category id.
	 *
	 * @param categoryId the category id
	 *
	 * @return the by category id
	 */
	MdcProductCategory getByCategoryId(Long categoryId);

	/**
	 * 获取商品分类树.
	 *
	 * @return the category tree list
	 */
	List<MdcCategoryVo> getCategoryTreeList();

	/**
	 * 根据ID获取商品分类信息.
	 *
	 * @param id the id
	 *
	 * @return the mdc category vo by id
	 */
	MdcCategoryVo getMdcCategoryVoById(Long id);

	/**
	 * 更新商品分类信息.
	 *
	 * @param updateStatusDto the update status dto
	 * @param loginAuthDto    the login auth dto
	 */
	void updateMdcCategoryStatusById(UpdateStatusDto updateStatusDto, LoginAuthDto loginAuthDto);

	/**
	 * 保存商品分类信息.
	 *
	 * @param mdcCategory  the mdc category
	 * @param loginAuthDto the login auth dto
	 */
	void saveMdcCategory(MdcProductCategory mdcCategory, LoginAuthDto loginAuthDto);

	/**
	 * Check category has child category boolean.
	 *
	 * @param id the id
	 *
	 * @return the boolean
	 */
	boolean checkCategoryHasChildCategory(Long id);
}
