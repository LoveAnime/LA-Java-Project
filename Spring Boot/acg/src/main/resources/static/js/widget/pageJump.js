/**
 * 说明：页面跳转控制
 * 创建人：帝歌恋雪
 * 创建时间：2017-11-18 9:28
 */
$(function ($) {
    var pageVue = null;
    var hasInit = false;

    //region begin 初始化页面跳转组件，显示页码，关联文件列表
    /**
     * @param pageInfo 分页信息，需要的字段包括：当前页数（默认显示第一页）、每页显示的记录数、总记录数
     * @param refreshList 页面跳转时需要执行的回调函数
     * @returns pageInfo 切换页面时，需要返回分页信息
     */
    $.fn.openPageJump = function (pageInfo,refreshList,key) {
        if (!hasInit) {
            $("#pageJump").css("display", "");
            pageVue = new Vue({
                el: "#pageJump",
                data: {
                    numList: [],    /* 要显示的所有页码 */
                    paginationStart: 1,  /* 第一个页码 */
                    paginationSize: 10,  /* 页码最大个数 */
                    pageInfo: pageInfo   /* 分页信息 */
                },
                methods: {
                    initPageVueData: function () {
                        this.pageInfo.recordStart = (this.pageInfo.pageNow - 1) * this.pageInfo.pageSize;
                        this.pageInfo.pageCount = parseInt((this.pageInfo.recordCount - 1) / this.pageInfo.pageSize) + 1;
                        this.numList = [];
                        for (var i = 0; i < this.pageInfo.pageCount; i++) {
                            this.numList.push(i + 1);
                        }
                        // 可能删除了一条记录，导致当前所在页比最后一页大
                        if(this.pageInfo.pageNow>this.pageInfo.pageCount){
                            this.pageInfo.pageNow = this.pageInfo.pageCount;
                            this.pageInfo.recordStart = (this.pageInfo.pageNow - 1) * this.pageInfo.pageSize;
                        }
                    },
                    switchPage: function () {
                        var $this = null;
                        if ($(event.target)[0].tagName == "SPAN") {
                            $this = $(event.target);
                        } else if ($(event.target)[0].tagName == "A") {
                            $this = $(event.target).parent();
                        }
                        this.pageInfo.pageNow = parseInt($this.find("a").text());
                        this.pageInfo.recordStart = (this.pageInfo.pageNow - 1) * this.pageInfo.pageSize;
                        key.pageInfo = this.pageInfo;
                        refreshList(key);
                    },
                    switchPrevPage: function () {
                        if(this.pageInfo.pageNow<=1){
                            alert("您当前所在页面不能切换到前一页~~~");
                            return false;
                        }
                        this.pageInfo.pageNow -= 1;
                        this.pageInfo.recordStart -= this.pageInfo.pageSize;
                        key.pageInfo = this.pageInfo;
                        refreshList(key);
                    },
                    switchNextPage: function () {
                        this.pageInfo.pageNow = parseInt(this.pageInfo.pageNow) + 1;
                        this.pageInfo.recordStart = parseInt(this.pageInfo.recordStart) + this.pageInfo.pageSize;
                        key.pageInfo = this.pageInfo;
                        refreshList(key);
                    }
                }
            });
            hasInit = true;
        }
        pageVue.initPageVueData();
        return pageVue.pageInfo;
    };
    //endregion

    // region finish 关闭页码
    $.fn.closePageJump = function () {
        $("#pageJump").css("display", "none");
    };
    // endregion

});