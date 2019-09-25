package com.anime.dao;

import com.anime.model.AcgModel;
import com.anime.model.AcgQueryModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 创建人：帝歌恋雪
 * 创建时间：2017-11-08 22:10
 * 说明：使用mapper代理访问数据库查询数据
 * @Repository 注解数据访问组件，即DAO组件
 * @Mapper 注解dao层，实际上是一个数据查询的接口
 * 其实现类在resources/mapper/xxxMapper.xml中，即从数据库中拉取信息
 * dao层方法名与xml文件中id的值一一对应，查询结果（resultType）与model的实体类一一对应
 */
@Mapper
public interface AcgDao {

    /**
     * 获得一条记录
     * @param id 使用该注解则在xml文件中不需要parameterType表明传入的参数类型
     * @return
     */
    AcgModel selectByPrimaryKey(@Param("id") int id) throws Exception;

    /**
     * 插入一条记录：参数必须完整
     * @param entity
     * @return 受影响的记录数
     */
    int insertByPrimaryKey(AcgModel entity) throws Exception;

    /**
     * 插入一条记录：插入某些字段
     * @param entity
     * @return 受影响的记录数
     */
    int insertSelective(AcgModel entity) throws Exception;

    /**
     * 更新一条记录：完整记录
     * @param entity
     * @return 受影响的记录数
     */
    int updateByPrimaryKey(AcgModel entity) throws Exception;

    /**
     * 更新一条记录：部分字段
     * @param entity
     * @return 受影响的记录数
     */
    int updateSelective(AcgModel entity) throws Exception;

    /**
     * 删除一条记录
     * @param id
     * @return 受影响的记录数
     */
    int deleteByPrimaryKey(@Param("id") int id) throws Exception;

    /**
     * 根据一个关键词进行搜索：可以匹配（模糊匹配）任何字段
     * @param query 包含查询关键字和分页信息
     * @return
     */
    List<AcgModel> searchAllField(AcgQueryModel query) throws Exception;

    /**
     * 根据一个关键词进行搜索：模糊匹配关键字段（片名、作者、附加信息）
     * @param query 包含查询关键字和分页信息
     * @return
     */
    List<AcgModel> searchCriticalField(AcgQueryModel query) throws Exception;

    /**
     * 根据一组关键词进行高级搜索：根据不同的关键词进行综合搜索
     * 片名、作者、附加信息模糊匹配，其他字段进行精确匹配
     * @param query 包含查询关键字和分页信息
     * @return
     */
    List<AcgModel> searchAdvanced(AcgQueryModel query) throws Exception;

    /**
     * 统计所有记录数
     *
     * @param query
     * @return
     * @throws Exception
     */
    long getCount(AcgQueryModel query) throws Exception;
}
