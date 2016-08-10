/** 
 * Copyright @2012-2016 Busap.com All Rights Reserved. 
 */
package com.busap.cctv.baidudata.docking.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.busap.cctv.baidudata.docking.domain.Terminal;
import com.busap.cctv.baidudata.docking.domain.TerminalModel;

/**
 * 
 * @description: 终端数据访问层
 * @author liweizhi
 * @since 2014年11月7日
 */
public interface TerminalRepository<T extends Terminal> extends JpaRepository<T, Long>,JpaSpecificationExecutor<T>  {

	/**
	 * 根据appKey和状态查询最Terminal
	 * 
	 * @param app
	 *            app
	 * @return
	 */
	T findByAppKeyAndStatus(String appKey, Short status);
	
	/**
	 * 根据deviceCode查询Terminal
	 * @param deviceCode		设备编码
	 * @return
	 */
	T findByDeviceCodeAndStatusNot(String deviceCode, Short status);
	
	/**
	 * 根据设备编号与状态查询设备(有效设备)
	 * @param terminalNo
	 * @param status
	 * @return
	 */
	List<T> findByTerminalNoLikeAndStatusGreaterThan(String terminalNo,Short status);
	
	/**
	 * 分页模糊查询终端
	 * @param terminalNo
	 * @param status
	 * @param pageable
	 * @return
	 */
	Page<T> findByTerminalNoLikeAndStatusNot(String terminalNo,Short status,Pageable pageable);
	
	/**
	 * 分页模糊查询终端
	 * @param terminalNo
	 * @param status
	 * @param pageable
	 * @param macAddress 
	 * @return
	 */
	Page<T> findByTerminalNoLikeAndStatusNotAndMacAddress(String terminalNo,Short status,Pageable pageable,String macAddress);
	
	/**
	 * 分页查询终端
	 * @param status
	 * @param pageable
	 * @return
	 */
	Page<T> findByStatusNot(Short status,Pageable pageable);
	/**
	 * 分页查询终端
	 * @param status
	 * @param pageable
	 * @param macAddress 
	 * @return
	 */
	Page<T> findByStatusNotAndMacAddress(Short status,Pageable pageable,String macAddress);
	
	/**
	 * 更新设备状态
	 * @param status
	 * @param id
	 */
	@Modifying
	@Query("update Terminal t set t.status=?1 where t.id=?2")
	int  updateTerminalStatus(Short status,Long id);
	
	/**
	 * 查询mac地址是否被占用
	 * @param macAddress
	 * @param status
	 * @return
	 */
	List<T> findTerminalBymacAddressAndStatusNot(String macAddress,Short status);
	/**
	 * 根据mac地址查询终端
	 * @param macAddress
	 * @param status
	 * @return
	 */
	List<T> findTerminalBymacAddressAndStatus(String macAddress,Short status);
	/**
	 * 根据状态查询终端
	 * @param status
	 * @return
	 */
	List<T> findByStatus(Short status);
	
	/**
	 * 根据终端模型和状态查询终端总数
	 * @param terminalModel 
	 * @param status
	 * @return
	 */
	Long countByTerminalModelAndStatus(TerminalModel terminalModel, Short status);
	
	/**
	 * 根据终端模型和状态反查终端总数
	 * @param terminalModelId
	 * @param status
	 * @return
	 */
	Long countByTerminalModelIdAndStatusNot(Long terminalModelId, Short status);
	/**
	 * 
	 * @param status
	 * @param id
	 * @return
	 */
	@Modifying
	@Query("update Terminal t set t.terminalNo=?1 ,macAddress=?2,terminalModel=?3 where t.id=?4")
	int  updateMacAddressAndTerminalNo(String terminalNo,
			String macAddress, TerminalModel terminalModel,Long id);
	
	
	/**
	 * 
	 * @param status
	 * @param id
	 * @return
	 */
	@Modifying
	@Query("update Terminal t set t.terminalNo=?1 ,macAddress=?2,device_code=?3,terminalModel=?4 where t.id=?5")
	int  updateMacAddressAndTerminalNoAndDeviceCode(String terminalNo,
			String macAddress,String device_code,TerminalModel terminalModel,Long id);
	

	/**
	 * 
	 * @param status
	 * @param id
	 * @return
	 */
	@Modifying
	@Query("update Terminal t set t.terminalNo=?1 ,macAddress=?2,device_code=?3,terminalModel=?4,busNo=?5 where t.id=?6")
	int  updateMacAddressAndTerminalNoAndDeviceCodeAndBusNo(String terminalNo,
			String macAddress,String device_code,TerminalModel terminalModel,String busNo,Long id);
}
