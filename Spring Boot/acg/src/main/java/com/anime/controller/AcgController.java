package com.anime.controller;

import com.anime.config.RetCode;
import com.anime.config.ServiceData;
import com.anime.model.AcgModel;
import com.anime.model.AcgQueryModel;
import com.anime.service.AcgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

// region 控制层涉及的知识点整理
// 控制层主要用来接收和响应http请求，后台（服务器）接收到http请求后,
// 首先由控制层对传入参数的合法性、身份验证等信息进行判断；
// 通过控制层验证后，数据在业务层进行包装，比如将id和排序字段打包成HashMap，
// 然后调用Dao层方法进行数据查询，并将查询结果返回给Service层；
// 最后服务层根据查询结果，判断该返回给用户什么服务数据。
// 项目较大时，可以在控制层和服务层中增加AO层进行数据校验和服务信息包装，但是比较难降低耦合。
//
// 控制层可以返回一个页面或者数据：
// 返回页面时，使用Controller注解；
// 返回数据时使用@RestController，等效于@Controller + @ResponseBody 的组合。
//
// @Autowired 用来实现服务层类的自动装配。
// @params 前台请求必须包含参数；headers请求必须包含header值。
// @RequestBody 注解参数，ajax请求需要传入一个data数据，或者允许请求参数不是直接在网址上，而是在request体中。
// @RequestMapping 注解前端的请求地址：value请求地址；method请求方法；
// consumes提交内容的类型（contentType），有application/json、text、html；produces返回数据的类型；
//
// 控制层/服务层的方法需要抛出一个异常。
// endregion

/**
 * 创建人：帝歌恋雪
 * 创建时间：2017-11-08 22:50
 * 说明：
 */

@Controller
@RequestMapping(value="/acg")
public class AcgController {

    @Autowired
    AcgService acgService;

    /**
     * 根据id读取一条记录
     * @param request
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/getOne",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ServiceData getOneById(HttpServletRequest request,
                                  int id) throws Exception{
        ServiceData sd = new ServiceData();
        AcgModel acgModel = null;
        try {
            acgModel = acgService.selectByPrimaryKey(id);
        }catch (Exception e){
            e.printStackTrace();
            sd.setCode(request,RetCode.PermissionDenied);
            sd.setOther("数据查询失败~~~");
            return sd;
        }
        if(acgModel == null){
            sd.setCode(request,RetCode.ServerError);
            sd.setOther("找不到数据~~~");
        }
        sd.setBo(acgModel);
        return sd;
    }

    /**
     * 插入一条记录
     * @param request
     * @param entity 使用@RequestBody注解，前台传入的数据必须配置所有非空参数，不然会出错
     * @param result BindingResult 用于验证传入的entity数据是否满足entity中的注解要求：id不能为空、日期格式等
     * @return 返回值是受影响的记录数
     * @throws Exception
     */
    @RequestMapping(value="/insertOne",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ServiceData insertOneAcg(HttpServletRequest request,
                                 @RequestBody AcgModel entity,
                                 BindingResult result) throws Exception {
        ServiceData sd = new ServiceData();
        if (result != null && result.hasErrors()) {
            sd.setCode(request,RetCode.ValidationError);
            sd.setBo(0);
            sd.setOther("传入数据异常~~~");
            return sd;
        }

        if (acgService.selectByPrimaryKey(entity.getId()) != null) {
            sd.setCode(request, RetCode.PermissionDenied);
            sd.setOther("数据已存在，不允许插入~~~");
        }else {
            entity.setCreatedDate(new Date());
            entity.setLastUpdatedDate(new Date());
            int count = acgService.insertSelective(entity);
            if (count == 0) {
                sd.setCode(request, RetCode.ServerError);
                sd.setOther("数据插入失败~~~");
            }
            sd.setBo(count);
        }
        return sd;
    }

    /**
     * 更新一条记录
     * @param request
     * @param entity
     * @param id
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/updateOne",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ServiceData updateOneAcg(HttpServletRequest request,
                                 @RequestBody AcgModel entity,
                                 int id,
                                 BindingResult result) throws Exception {
        ServiceData sd = new ServiceData();
        if (result != null && result.hasErrors()) {
            sd.setCode(request,RetCode.ValidationError);
            sd.setOther("传入数据异常~~~");
        }else if (acgService.selectByPrimaryKey(entity.getId()) == null) {
            sd.setCode(request, RetCode.PermissionDenied);
            sd.setOther("更新数据不存在~~~");
        }else {
            entity.setLastUpdatedDate(new Date());
            int count = 0;
            try {
                count=acgService.updateSelective(entity);
            }catch (Exception e){
            }
            if (count == 0) {
                sd.setCode(request, RetCode.ServerError);
                sd.setOther("更新数据失败~~~");
            }
            sd.setBo(count);
        }
        return sd;
    }

    /**
     * 删除一条记录
     * @param request
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/deleteOne",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ServiceData deleteOneAcg(HttpServletRequest request,
                                    int id) throws Exception{
        ServiceData sd = new ServiceData();
        if (acgService.selectByPrimaryKey(id) == null) {
            sd.setCode(request, RetCode.PermissionDenied);
            sd.setOther("删除不存在的记录~~~");
        }else {
            int count = 0;
            count = acgService.deleteByPrimaryKey(id);
            if (count == 0) {
                sd.setCode(request, RetCode.ServerError);
            }
            sd.setBo(count);
        }
        return sd;
    }

    /**
     * 获得符合要求的所有记录
     * @param request
     * @param query
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/getAllAcg",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ServiceData getAllAcg(HttpServletRequest request,
                                 @RequestBody AcgQueryModel query,
                                 BindingResult result) throws Exception{
        ServiceData sd = new ServiceData();
        try {
            long len = acgService.getCount(query);
            if(len == 0){
                sd.setCode(request, RetCode.Success);
                sd.setBo(null);
                sd.setOther("查不到数据~~~");
            }else{
                List<AcgModel> list=null;
                list = acgService.searchAdvanced(query);
                sd.setBo(list);
            }
        }catch (Exception e){
            e.printStackTrace();
            sd.setCode(request, RetCode.ServerError);
        }
        return sd;
    }

    /**
     * 统计符合要求的记录总数
     * @param request
     * @param query
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/getCount",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ServiceData getCount(HttpServletRequest request,
                                 @RequestBody AcgQueryModel query,
                                 BindingResult result) throws Exception{
        ServiceData sd = new ServiceData();
        try {
            long len = acgService.getCount(query);
            if(len == 0){
                sd.setCode(request, RetCode.Success);
                sd.setOther("查不到数据~~~");
            }
            sd.setBo(len);
        }catch (Exception e){
            e.printStackTrace();
            sd.setCode(request, RetCode.ServerError);
        }
        return sd;
    }
}
