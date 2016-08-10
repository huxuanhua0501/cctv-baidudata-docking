/** 
 * Copyright @2012-2016 Busap.com All Rights Reserved. 
 */
package com.busap.cctv.baidudata.docking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.busap.cctv.baidudata.docking.domain.BusLine;
import com.busap.cctv.baidudata.docking.domain.BusTerminal;

/**
 * 
 * @description: 终端数据访问层
 * @author liweizhi
 * @since 2014年11月7日
 */
public interface BusTerminalRepository extends TerminalRepository<BusTerminal> {

	public List<BusTerminal> findByBusLine(BusLine busLine);

	/**
	 * 通过线路查询所有的终端数量
	 * 
	 * @param busLine
	 * @return
	 */
	public Long countByBusLine(BusLine busLine);

	/**
	 * 通过线路查询所有的终端数量
	 * 
	 * @param busLine
	 *            线路
	 * @param Status
	 *            激活的状态
	 * @return Long
	 */
	public Integer countByBusLineAndStatus(BusLine busLine, Short Status);
	
	/**
	 * 根据mac地址查询终端
	 * @param macAddress
	 * @return
	 */
	public List<BusTerminal> findByMacAddress(String macAddress); 
	/**
	 * 根据mac地址和状态查询终端
	 * @param macAddress
	 * @return
	 */
	public List<BusTerminal> findByMacAddressAndStatusNot(String macAddress,Short status); 
	/**
	 * 根据mac地址和状态查询终端
	 * @param macAddress
	 * @return
	 */
	public BusTerminal findByAppKeyAndStatus(String appKey,Short status); 

	/**
	 * 查询所有线路等于NULL的busterminal
	 * 
	 * @return List<BusTerminal>
	 */
	public List<BusTerminal> findByModifierIdAndStatusAndBusLineIsNull(Long userId,Short status);
	
	/**
	 * 根据状态查找车载终端
	 */
	public List<BusTerminal> findByStatus(Short status);
	
	/**
	 * 根据线路id和状态反查统计车载终端数
	 * @param id
	 * @param status
	 * @return
	 */
	public int countByBusLineIdAndStatusNot(Long busLineId,Short status);
	
	@Query(value="SELECT COUNT(id) from terminal where terminal_type='bus' and status!=0 and line_id in (SELECT id from bus_line where area_id=?1)",nativeQuery = true)
	public int countByBusLineAndAreaId(Long areaId);
}
