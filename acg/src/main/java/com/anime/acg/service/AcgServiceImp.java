package com.anime.acg.service;

import com.anime.acg.dto.PageInfo;
import com.anime.acg.dao.AcgDao;
import com.anime.acg.model.AcgModel;
import com.anime.acg.dto.AcgQo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 创建人：帝歌恋雪
 * 创建时间：2017-11-08 22:33
 * 说明：
 * @Service 注解服务层的实现类，简单调用dao层的接口函数从数据库中拉取数据
 * 一般而言，服务层的操作是对数据对象（传入的查询参数和查询结果）作简单处理，
 * 比如排序、按字段搜索等，然后直接将数据返回到控制层，尽量不作状态码判断、数据异常等层面的操作
 *
 * Param注解表明必须传入参数
 */
@Service
public class AcgServiceImp implements AcgService {

    /**
     * 实现acgDao对象的自动注入（初始化）
     */
    @Autowired
    AcgDao acgDao;

    /**
     * 获得一条记录
     * @param id 使用该注解则在xml文件中不需要parameterType表明传入的参数类型
     * @return
     */
    @Override
    public AcgModel selectByPrimaryKey(@Param("id") int id) throws Exception{
        return acgDao.selectByPrimaryKey(id);
    }

    /**
     * 插入一条记录：参数必须完整
     * @param entity
     * @return 受影响的记录数
     */
    @Override
    public int insertByPrimaryKey(AcgModel entity) throws Exception{
        return acgDao.insertByPrimaryKey(entity);
    }

    /**
     * 插入一条记录：插入某些字段
     * @param entity
     * @return 受影响的记录数
     */
    @Override
    public int insertSelective(AcgModel entity) throws Exception{
        return acgDao.insertSelective(entity);
    }

    /**
     * 更新一条记录：完整记录
     * @param entity
     * @return 受影响的记录数
     */
    @Override
    public int updateByPrimaryKey(AcgModel entity) throws Exception{
        return acgDao.updateByPrimaryKey(entity);
    }

    /**
     * 更新一条记录：部分字段
     * @param entity
     * @return 受影响的记录数
     */
    @Override
    public int updateSelective(AcgModel entity) throws Exception{
        return acgDao.updateSelective(entity);
    }

    /**
     * 删除一条记录
     * @param id
     * @return 受影响的记录数
     */
    @Override
    public int deleteByPrimaryKey(@Param("id") int id) throws Exception{
        return acgDao.deleteByPrimaryKey(id);
    }

    /**
     * 根据一个关键词进行搜索：可以匹配（模糊匹配）任何字段
     * @param query 查询类
     * @return
     * @throws Exception
     */
    @Override
    public List<AcgModel> searchAllField(AcgQo query) throws Exception{
        if (query.getPageInfo()==null || query.getPageInfo().getPageSize() == 0) {
            PageInfo pageInfo = new PageInfo();
            query.setPageInfo(pageInfo);
        }
        return acgDao.searchAllField(query);
    }

    /**
     * 根据一个关键词进行搜索：模糊匹配关键字段（片名、作者、附加信息）
     * @param query 查询类
     * @return
     * @throws Exception
     */
    @Override
    public List<AcgModel> searchCriticalField(AcgQo query) throws Exception{
        if (query.getPageInfo()==null || query.getPageInfo().getPageSize() == 0) {
            PageInfo pageInfo = new PageInfo();
            query.setPageInfo(pageInfo);
        }
        return acgDao.searchCriticalField(query);
    }

    /**
     * 根据一组关键词进行高级搜索：根据不同的关键词进行综合搜索
     * 片名、作者、附加信息模糊匹配，其他字段进行精确匹配
     * @param query 包含查询关键字和分页信息
     * @return
     * @throws Exception
     */
    @Override
    public List<AcgModel> searchAdvanced(AcgQo query) throws Exception{
        if (query.getPageInfo()==null || query.getPageInfo().getPageSize() == 0) {
            PageInfo pageInfo = new PageInfo();
            query.setPageInfo(pageInfo);
        }
        return acgDao.searchAdvanced(query);
    }

    /**
     * 统计所有记录数
     * @param query
     * @return
     * @throws Exception
     */
    @Override
    public long getCount(AcgQo query) throws Exception {
        return acgDao.getCount(query);
    }
}
