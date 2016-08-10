/** 
* Copyright @2012-2016 Busap.com All Rights Reserved. 
*/ 
package com.busap.cctv.baidudata.docking.repository; 

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.busap.cctv.baidudata.docking.domain.Area;
import com.busap.cctv.baidudata.docking.domain.BasePersistable.Status;
import com.busap.cctv.baidudata.docking.domain.BusLine;
import com.busap.cctv.baidudata.docking.domain.Ground;
import com.busap.cctv.baidudata.docking.domain.User;

/** 
 * 
 * @description: 公交线路数据访问层
 * @author liweizhi
 * @since 2014年11月7日 
 */
public interface BusLineRepository extends JpaRepository<BusLine, Long>,JpaSpecificationExecutor<BusLine> {

	/**
	 * 通过区域id查询公交线路
	 * @param areaId	区域ID
	 * @return
	 */
	Iterable<BusLine> findByAreaId(Long areaId);
	/**
	 * 查询公交线路
	 * @param areaId
	 * @param status
	 * @return
	 */
	Iterable<BusLine> findByAreaIdAndStatus(Long areaId,Short status);
	/**
	 * 查询公交线路
	 * @param areaId
	 * @param status
	 * @return
	 */
	Iterable<BusLine> findByBusCompanyIdAndStatus(Long areaId,Short status);
	
	/**
	 * 根据公交公司跟状态查询线路列表
	 * @param busCompany
	 * @param status
	 * @return
	 */
	List<BusLine> findByBusCompany_IdAndStatus(@Param("busCompanyId")Long busCompanyId,@Param("status")Short status,Sort sort);
	BusLine findToByOrderByIdDesc();
	
	/**
	 * 分页查询公交线路模糊查询
	 * @param name
	 * @param user
	 * @param status
	 * @param pageable
	 * @return
	 */
	Page<BusLine> findByNameLikeAndUserAndStatus(String name,User user,Short status,Pageable pageable);
	
	/**
	 * 查询用户管理都线路
	 * @param user
	 * @param status
	 * @param pageable
	 * @return
	 */
	Page<BusLine> findByUserAndStatus(User user,Short status,Pageable pageable);
	
	/**
	 * 查询线路列表
	 * @param area
	 * @param status
	 * @param pageable
	 * @return
	 */
	Page<BusLine> findByAreaAndStatus(Area area,Short status,Pageable pageable);
	
	/***
	 * 模糊查询公交线路 
	 * @param area
	 * @param status
	 * @param pageable
	 * @return
	 */
	Page<BusLine> findByNameLikeAndAreaAndStatus(Area area,Short status,Pageable pageable);
	/**
	 * 查询所有可用的线路
	 * @param status
	 * @param pageable
	 * @return
	 */
	Page<BusLine> findByStatus(Short status,Pageable pageable);
	
	/**
	 * 模糊查询公交线路
	 * @param name
	 * @param status
	 * @param pageable
	 * @return
	 */
	Page<BusLine> findByNameLikeAndStatus(String name,Short status,Pageable pageable);
	
	/**
	 * 更新公交线路状态
	 * @param status
	 * @param id
	 */
	@Modifying
	@Query("update BusLine b set b.status=?1 where b.id=?2")
	int  updateBusLineStatus(Short status,Long id);
	/**
	 * 根据状态查询公交线路列表
	 * @param status
	 * @return
	 */
	public List<BusLine> findByStatus(Short status,Sort sort);
	/**
	 * 根据线路名称，城市和状态查询线路
	 * @param name
	 * @param areaId
	 * @param status
	 * @return
	 */
	public List<BusLine> findByNameAndAreaIdAndStatus(String name,Long areaId,Short status);
	
	/**
	 * 统计线路数
	 * @param areaId
	 * @param status
	 * @param name
	 * @return
	 */
	public Long countByBusCompanyIdAndStatusNot(Long companyId,Short status);
	
	/**
	 * 查询第一条公交线路公交线路
	 * @param areaId
	 * @param normal
	 * @return
	 */
	BusLine findFirstByAreaIdAndStatus(Long areaId,Status normal,Sort sort);

}
          
