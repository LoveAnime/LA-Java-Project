/**
 * 弹出编辑对话框，主要实现的功能包括：
 * 1、初始化对话框。
 * 2、根据用户点击“确认”、“取消”按钮执行不同的回调函数。
 * Created by LA on 2017/10/29.
 */
$(function ($) {
    var hasInit = false;
    var editBoxVue = null;

    // region jQuery实例方法

    // region begin 打开弹框：创建弹框实例对象，并打开弹框
    $.fn.openEditBox = function (operType, data, confirmFunc) {
        if (!hasInit) {
            editBoxVue = new Vue({
                el: "#editBoxWidget",
                data: {
                    acgData: {},
                    operType: operType,
                    countryList: [
                        {value: "", txt: "请选择"},
                        {value: "中国", txt: "中国"},
                        {value: "日本", txt: "日本"},
                        {value: "美国", txt: "美国"},
                        {value: "其他", txt: "其他"}],
                    completedList: [
                        {value: "", txt: "请选择"},
                        {value: "已完结", txt: "已完结"},
                        {value: "连载中", txt: "连载中"}],
                    enableList: [
                        {value: "", txt: "请选择"},
                        {value: "有", txt: "有"},
                        {value: "无", txt: "无"}]
                }
            });
            hasInit = true;
        }

        // 初始化弹框数据、加载事件，并显示弹框
        initDialog(operType, data, confirmFunc);
    };
    // endregion

    // region finish 关闭对话框：卸载事件、隐藏对话框
    $.fn.closeDialog = function () {
        $(".customize_close").unbind("click");
        $(".edit_box_button .confirm").unbind("click");
        $(".edit_box_button .cancer").unbind("click");
        $("#editBoxWidget").css("display", "none");
    };
    // endregion

    // endregion

    // region 内部方法

    // region 初始化弹框数据、加载事件，并显示弹框
    function initDialog(operType, data, confirmFunc) {
        editBoxVue.operType = operType;
        editBoxVue.acgData = data;
        switch (editBoxVue.operType){
            case "添加":
                $("#idEdit").removeAttr("readonly");
                $("#acgNameEdit").removeAttr("readonly");
                $("#authorEdit").removeAttr("readonly");
                $("#categoryEdit").removeAttr("readonly");
                $("#episodeEdit").removeAttr("readonly");
                $("#countryEdit").removeAttr("disabled");
                $("#completedEid").removeAttr("disabled");
                $("#watchAbleEid").removeAttr("disabled");
                break;
            case "编辑":
                $("#idEdit").attr("readonly","true");
                $("#acgNameEdit").removeAttr("readonly");
                $("#authorEdit").removeAttr("readonly");
                $("#categoryEdit").removeAttr("readonly");
                $("#episodeEdit").removeAttr("readonly");
                $("#countryEdit").removeAttr("disabled");
                $("#completedEid").removeAttr("disabled");
                $("#watchAbleEid").removeAttr("disabled");
                break;
            case "删除":
                $("#idEdit").attr("readonly","true");
                $("#acgNameEdit").attr("readonly","true");
                $("#authorEdit").attr("readonly","true");
                $("#categoryEdit").attr("readonly","true");
                $("#episodeEdit").attr("readonly","true");
                $("#countryEdit").attr("disabled","disabled");
                $("#completedEid").attr("disabled","disabled");
                $("#watchAbleEid").attr("disabled","disabled");
                break;
        }
        // 绑定显示框关闭事件
        $(".customize_close").off().on({
            "click": function () {
                $().closeDialog();
            }
        });

        // 绑定编辑确定事件
        $(".edit_box_button .confirm").off().on({
            "click": function () {
                if (typeof confirmFunc == "function") {
                    confirmFunc(editBoxVue.acgData);
                } else {
                    alert("传入了一个非函数的参数");
                }
                $().closeDialog();
            }
        });

        // 绑定编辑取消事件
        $(".edit_box_button .cancer").off().on({
            "click": function () {
                $().closeDialog();
                $.tips.showPopup("您取消了" + editBoxVue.operType + "操作！");
            }
        });

        // 显示编辑框
        $("#editBoxWidget").css("display", "");
    }
    // endregion

    // endregion
});