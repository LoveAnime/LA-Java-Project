/**
 * 说明：实现排行榜功能，包括增删改、排序、分页等功能
 * 作者：帝歌恋雪
 * 创建时间：2017-9-16
 * 最后更新时间：2017-11-17
 */

//region question 编写过程中的疑惑
// window.onload = 等网页加载完后执行操作
// $(function($){}); DOM结构加载完毕后执行操作
//
// 1、全局变量的那个注解怎么用；
// 2、怎么初始化主页的组件，怎么写向后台发出请求数据的函数；  // 解决
// 3、怎么配置服务层用来接收前台的命令；  // 解决
// 4、怎么配置服务层；   // 解决
// 5、Mybatis和dao层用来访问数据库信息怎么写
//
// 1、控制层异常怎么抛出   // 解决
// 2、控制层服务层方法和属性的引入怎么注解：Autowired  // 解决
// 3、imp怎么实现接口  // 解决
// 4、dao层和数据库怎么交互
//
// 1、记录实体类和dao层接口和xml具体怎么写
//
// 1、弹框按确定按钮之后，执行的函数、传递的参数、以及执行结果怎么返回后调用者？
// 2、一进入动漫排行榜首页，数据的刷新方式是什么？
//
// 键规范用法:键建议使用引号，不用引号可能会在某些序列化和反序列化过程中产生错误
//
//endregion

// region begin 与排行榜相关的增删改、排序、分页等功能
$(function ($) {
    $(function ($) {
        $().initRankList();
    });

    // region 定义全局变量
    var pageInfo = {  // 主要用来与页码跳转组件进行值传递
        pageNow: 1,
        pageCount: 0,
        pageSize: 10,
        recordStart: 0,
        recordCount: 0,
        sortField: "ID",
        sortOrder: "ASC"
    };
    var initCondition = {  // 主要访问服务器
        keyword: "",
        completed: "",
        country: "",
        watchAble: "",
        memo: "",
        pageInfo: pageInfo
    };
    var searchKey={};
    var rankListVue = null;
    var hasInit = false;
    // endregion

    //region 扩展jQuery实例方法：以便闭包结构外的函数调用

    // region begin 执行初始化文件排名列表
    $.fn.initRankList = function () {
        if(!hasInit) {
            $("#animationBox").css("display", "");
            rankListVue = new Vue({
                el: "#animationBox",
                data: {
                    rankList: []
                },
                methods: {
                    addAndEdit: function (event) {
                        var objId = parseInt($(event.target).parent().parent().find("span.file_id").text());
                        var data = {};
                        for(var i = 0; i < this.rankList.length; i++ ){
                            if(this.rankList[i].id == objId) {
                                data = this.rankList[i];
                                break;
                            }
                        }
                        switch ($(event.target).attr("value")) {
                            case "add":
                            case "添加":
                                $().openEditBox("添加", data, insertOneAcg);
                                break;
                            case "edit":
                                $().openEditBox("编辑", data, updateOneAcg);
                                break;
                            case "delete":
                                $().openEditBox("删除", data, deleteOneAcg);
                                break;
                        }
                    },
                    chooseSortField:function (event) {
                        var $this = null;
                        if($(event.target).hasClass("file_list_set")){
                            $this = $(event.target).find("span");
                        }else{
                            $this = $(event.target);
                        }
                        changeSortOrder($this);
                    },
                    initSearchData: function () {
                        searchKey = initCondition;
                    },
                    search:function () {
                        searchKey = initCondition;
                        searchKey.keyword = $(".navSearchKey").val();
                        searchKey.acgCategory = $(".search_category").val();
                        searchKey.completed = $(".search_complete").val();
                        searchKey.country = $(".search_country").val();
                        searchKey.watchAble = $(".search_watchAble").val();
                        GetCount(searchKey);
                        RefreshRankList(searchKey);
                    }
                }
            });
            hasInit = true;
        }
        GetCount(searchKey);
        rankListVue.initSearchData();
        RefreshRankList(searchKey);
    };
    // endregion

    // endregion

    //region 内部方法：为了实现实例方法或者某种独立功能而抽象出来的方法

    // region 插入一条记录
    function insertOneAcg(data) {
        $.ajax({
            url: "/acg/insertOne",
            type: 'post',
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: 'json',
            async: true,
            success: function (result,textStatus) {
                if (result.code.msgId == "0000") {
                    $.tips.showPopup("您成功添加了一条记录！");
                    pageInfo.recordCount = parseInt(pageInfo.recordCount) + 1;
                    // searchKey.pageInfo.recordCount = parseInt(searchKey.pageInfo.recordCount) + 1;
                    pageInfo = $().openPageJump(pageInfo, RefreshRankList, searchKey);
                    RefreshRankList(searchKey);
                } else {
                    alert("添加失败~~~");
                }
            },
            error: function () {
                alert("访问出错了。");
            }
        });
    }
    // endregion

    // region 更新一条记录
    function updateOneAcg(data) {
        $.ajax({
            url: "/acg/updateOne?id="+data.id,
            data: JSON.stringify(data), /*将一个JSON对象转化成JSON字符串*/
            type: 'post',
            contentType: "application/json", /*提交的数据的类型：json格式的属性必须用RequestBody注解接收*/
            dataType: 'json', /*返回的数据类型*/
            async: true,
            cache:false,
            success: function (result) {
                if (result.code.msgId == "0000") {
                    $.tips.showPopup("您成功更新了一条记录！");
                    RefreshRankList(searchKey);
                } else {
                    alert("更新失败~~~");
                }
            },
            error: function () {
                alert("访问出错了。");
            }
        });
    }
    // endregion

    // region 删除一条记录
    function deleteOneAcg(data) {
        $.ajax({
            url: "/acg/deleteOne?id=" + data.id,
            type: 'post',
            dataType: 'json',
            async: true,
            success: function (result) {
                if (result.code.msgId == "0000") {
                    $.tips.showPopup("您成功删除了一条记录！");
                    pageInfo.recordCount = pageInfo.recordCount - 1;
                    pageInfo = $().openPageJump(pageInfo, RefreshRankList, searchKey);
                    searchKey.pageInfo.recordCount = pageInfo.recordCount;
                    searchKey.pageInfo.recordStart = pageInfo.recordStart;
                    searchKey.pageInfo.pageNow = pageInfo.pageNow;
                    RefreshRankList(searchKey);
                } else {
                    alert("删除失败~~~");
                }
            },
            error: function () {
                alert("URL请求出错~~~");
            }
        });
    }
    // endregion

    //region 获得所有记录
    function RefreshRankList(searchKey) {
        $.ajax({
            url: "/acg/getAllAcg",
            type: 'post',
            data: JSON.stringify(searchKey),
            contentType: "application/json",
            dataType: "json",
            async: true,
            beforeSend: function () {
                $(".loading_container").css("display", "")
            },
            complete: function () {
                $(".loading_container").fadeOut()
            },
            success: function (result, textStatus) {
                rankListVue.rankList = [];
                $(".fileMessage").html("");
                if (result.code.msgId == "0000") {
                    // 访问bo字段，result.bo==result["bo"];
                    if (result.bo == null || result.bo.length == 0) {
                        $(".fileMessage").html("<p>查询不到数据~~~</p>");
                        $().closePageJump();
                    }
                    else {
                        rankListVue.rankList = result.bo;
                    }
                }
            },
            error: function () {
                alert("访问出错了。");
            }
        });
    }
    // endregion

    //region 根据id获得一条记录
    function GetOne() {
        // var id = $(".acg_search").find("input[type='text']").val();
        var id = $(".navSearchKey").val();
        if(id==="")
        {
            alert("请输入ID~~~");
            return;
        }
        if(isNaN(+id)){  // +id 将字符串转化为数字
            alert("请输入合法的ID~~~");
            return;
        }
        $.ajax({
            url:"/acg/getAcgById?id="+id,
            type:'get',
            dataType:"json",  /*返回的数据类型*/
            async:true,
            success:function (result) {
                if(result.code.msgId=="0001")
                {
                    alert("服务器异常~~~");
                    return;
                }else if(result.code.msgId=="0003"){
                    alert("找不到数据~~~");
                    return;
                }
                if(result.code.msgId=="0000") {
                    alert("id=" + result.bo.id +
                        "\nacgName=" + result.bo.acgName +
                        "\nacgAuthor=" + result.bo.acgAuthor +
                        "\nacgCategory=" + result.bo.acgCategory +
                        "\nepisode=" + result.bo.episode +
                        "\ncompleted=" + result.bo.completed +
                        "\ncountry=" + result.bo.country +
                        "\nwatchAble=" + result.bo.watchAble)
                }
            },
            error:function () {
                alert("URL请求出错~~~");
            }
        })
    }
    // endregion

    //region 获得所有记录数
    function GetCount(searchKey) {
        $.ajax({
            url:"/acg/getCount",
            type: 'post',
            data: JSON.stringify(searchKey),
            contentType: "application/json",
            dataType:'json',  /*返回的数据类型*/
            async:false,
            success:function (result) {
                if(result.code.msgId=="0000") {
                    pageInfo.recordCount = result.bo;
                    // 可能增加总页数，需要重新渲染页码
                    pageInfo = $().openPageJump(pageInfo, RefreshRankList, searchKey);
                }else{
                    alert("查询记录数出错~~~");
                }
            },
            error:function () {
                alert("URL请求出错~~~");
            }
        })
    }
    // endregion

    //region 排序字段和排序方式
    function changeSortOrder($div) {
        if($div.hasClass("file_id")){
            if(initCondition.pageInfo.sortField!="ID"){
                initCondition.pageInfo.sortField = "ID";
                initCondition.pageInfo.sortOrder = "ASC";
            }else{
                initCondition.pageInfo.sortOrder = (initCondition.pageInfo.sortOrder == "ASC") ? "DESC" : "ASC";
            }
            RefreshRankList(searchKey);
        }else if($div.hasClass("file_name")){
            if(initCondition.pageInfo.sortField!="ACG_NAME"){
                initCondition.pageInfo.sortField = "ACG_NAME";
                initCondition.pageInfo.sortOrder = "ASC";
            }else{
                initCondition.pageInfo.sortOrder = (initCondition.pageInfo.sortOrder == "ASC") ? "DESC" : "ASC";
            }
            RefreshRankList(searchKey);
        }else if($div.hasClass("file_author")){
            if(initCondition.pageInfo.sortField!="ACG_AUTHOR"){
                initCondition.pageInfo.sortField = "ACG_AUTHOR";
                initCondition.pageInfo.sortOrder = "ASC";
            }else{
                initCondition.pageInfo.sortOrder = (initCondition.pageInfo.sortOrder == "ASC") ? "DESC" : "ASC";
            }
            RefreshRankList(searchKey);
        }else if($div.hasClass("file_category")){
            if(initCondition.pageInfo.sortField!="ACG_CATEGORY"){
                initCondition.pageInfo.sortField = "ACG_CATEGORY";
                initCondition.pageInfo.sortOrder = "ASC";
            }else{
                initCondition.pageInfo.sortOrder = (initCondition.pageInfo.sortOrder == "ASC") ? "DESC" : "ASC";
            }
            RefreshRankList(searchKey);
        }else if($div.hasClass("file_episode")){
            if(initCondition.pageInfo.sortField!="EPISODE"){
                initCondition.pageInfo.sortField = "EPISODE";
                initCondition.pageInfo.sortOrder = "ASC";
            }else{
                initCondition.pageInfo.sortOrder = (initCondition.pageInfo.sortOrder == "ASC") ? "DESC" : "ASC";
            }
            RefreshRankList(searchKey);
        }
    }
    // endregion

    // endregion

});
// endregion
