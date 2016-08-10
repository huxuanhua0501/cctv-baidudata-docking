package com.busap.cctv.baidudata.docking.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.busap.cctv.baidudata.docking.domain.Area;
import com.busap.cctv.baidudata.docking.domain.User;


/**
 * 区域城市数据访问层
 * 
 * @author liuyu
 * 
 * @since 2014年11月11日
 */
@Repository
public interface AreaRepository extends JpaRepository<Area, Long>, JpaSpecificationExecutor<Area> {

	/**
	 * 根据终端的appKey查询终端对应的Area
	 * 
	 * @param appKey
	 *            终端key
	 * @return
	 */
	@Query("select a from Area a, BusLine b, BusTerminal t where t.busLine = b.id and b.area = a.id and t.appKey = :appKey")
	Area getAreaByBusAppKey(@Param("appKey") String appKey);

	/**
	 * 根据终端的appKey查询终端对应的Area
	 * 
	 * @param appKey
	 *            终端key
	 * @return
	 */
	@Query("select a from Area a, Ground g, StationTerminal s where s.ground = g.id and g.area = a.id and s.appKey = :appKey")
	Area getAreaByStationAppKey(@Param("appKey") String appKey);

	/**
	 * 根据区号获取地区记录
	 * @param code 区号
	 * @return 区号对应的地区
	 */
	Area findFirstByCode(String code);

	/**
	 * 分页查询城市模糊查询
	 * 
	 * @param areaName
	 * @param user
	 * @param pageable
	 * @return
	 */
	Page<Area> findByNameLikeAndUser(String areaName, User user,Pageable pageable);
	
	/**
	 * 分页查询城市模糊查询
	 * 
	 * @param areaName
	 * @param user
	 * @param pageable
	 * @return
	 */
	Page<Area> findByNameLikeAndUsers(String areaName, Set<User> users,Pageable pageable);
	
	/**
	 * 分页查询城市模糊查询
	 * 
	 * @param areaName
	 * @param user
	 * @param pageable
	 * @param status
	 * @return
	 */
	Page<Area> findByNameLikeAndUserAndStatus(String areaName, User user,Pageable pageable,Short status);

	/**
	 * 分页查询城市模糊查询
	 * 
	 * @param areaName
	 * @param user
	 * @param pageable
	 * @param status
	 * @return
	 */
	Page<Area> findByNameLikeAndUsersAndStatus(String areaName, Set<User> users,Pageable pageable,Short status);
	
	/**
	 * 查询用户管理的城市列表
	 * 
	 * @param user
	 * @param pageable
	 * @return
	 */
	Page<Area> findByUser(User user,  Pageable pageable);
	/**
	 * 查询用户管理的城市列表
	 * 
	 * @param user
	 * @param pageable
	 * @return
	 */
	Page<Area> findByUsers(Set<User> users,  Pageable pageable);
	/**
	 * 查询用户管理的城市列表
	 * 
	 * @param user
	 * @param pageable
	 * @param status
	 * @return
	 */
	Page<Area> findByUserAndStatus(User user,  Pageable pageable,Short status);
	
	/**
	 * 查询用户管理的城市列表
	 * 
	 * @param user
	 * @param pageable
	 * @param status
	 * @return
	 */
	Page<Area> findByUsersAndStatus(Set<User> users,  Pageable pageable,Short status);
	
	/**
	 * 查询用户管理的城市列表
	 * 
	 * @param user
	 * @param status
	 * @return
	 */
	List<Area> findByUserAndStatus(User user,  Short status);

	/**
	 * 查询所有可用的城市列表
	 * 
	 * @param status
	 * @param pageable
	 * @return
	 */
	Page<Area> findByStatus(Short status, Pageable pageable);

	/**
	 * 模糊查询城市列表
	 * 
	 * @param areaName
	 * @param pageable
	 * @return
	 */
	Page<Area> findByNameLike(String areaName, Pageable pageable);
	
	/**
	 * 模糊查询城市列表
	 * 
	 * @param areaName
	 * @param pageable
	 * @param status
	 * @return
	 */
	Page<Area> findByNameLikeAndStatus(String areaName, Pageable pageable,Short status);

	/**
	 * 更新城市状态
	 * 
	 * @param status
	 * @param id
	 */
	@Modifying
	@Query("update Area a set a.status=?1 where a.id=?2")
	int updateAreaStatus(Short status, Long id);

	/**
	 * 查询所有的区域parent的值为null的数据
	 * 
	 * @return
	 */
	List<Area> findByParentIsNull();

	/**
	 * 根据状态查询城市列表
	 * 
	 * @param status
	 * @return
	 */
	public List<Area> findByStatus(Short status);

	/**
	 * 通过用户ID查询用户的城市
	 * 
	 * @param user
	 * @return Area
	 */
	Area findByUser(User user);

	/**
	 *  查询区号是否被使用
	 * @param code
	 * @param status
	 * @return
	 */
	List<Area> findAreaByCodeAndStatus(String code,Short status);
	/**
	 *  查询区号是否被使用
	 * @param code
	 * @return
	 */
	List<Area> findAreaByCode(String code);
	
	/***
	 * 查询当前用户下的所有城市
	 * @param user
	 * @return
	 */
	List<Area> findByStatusAndUser(Short status,User user);
	
	/**
	 * 根据城市名称和状态查找城市
	 * @param name
	 * @param status
	 * @return
	 */
	List<Area> findByStatusAndName(Short status,String name);
	/**
	 * 根据城市名称和状态查找城市
	 * @param name
	 * @return
	 */
	List<Area> findByName(String name);
	
	public Area findById(Long id);
	
	public Area findByStatusAndNameAndCode(Short status,String name,String code);
}
