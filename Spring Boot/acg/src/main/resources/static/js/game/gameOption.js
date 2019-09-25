/**
 * Created by LA on 2017/11/7.
 */

$(function ($) {
    var hasInit = false;
    var editBoxVue = null;
    // region 打开弹框：创建弹框实例对象，并打开弹框
    $.fn.openGameOptBox = function ( length,size,type,player, confirmFunc) {
        if (!hasInit) {
            editBoxVue = new Vue({
                el: "#gameOption",
                data: {
                    length:length,
                    size:size,
                    firstPlayer:player,
                    gameType:type,
                    typeList: [
                        {value: "PVP", txt: "人人对战"},
                        {value: "PVC", txt: "人机对战"}],
                    playerList: [
                        {value: "black", txt: "黑子"},
                        {value: "white", txt: "白子"}]
                }
            });
            hasInit = true;
        }

        // 初始化弹框数据、加载事件，并显示弹框
        initDialog(confirmFunc);
    };
    // endregion

    // region 关闭对话框：卸载事件、隐藏对话框
    function closeDialog() {
        $(".customize_close").unbind("click");
        $(".edit_box_button .confirm").unbind("click");
        $(".edit_box_button .cancer").unbind("click");
        $("#gameOption").css("display", "none");
    }
    // endregion

    // region 初始化弹框数据、加载事件，并显示弹框
    function initDialog(confirmFunc) {
        // 绑定显示框关闭事件
        $(".customize_close").off().on({
            "click": function () {
                closeDialog();
            }
        });

        // 绑定编辑确定事件
        $(".edit_box_button .confirm").off().on({
            "click": function () {
                if (typeof confirmFunc == "function") {
                    confirmFunc(editBoxVue.length,editBoxVue.size,editBoxVue.gameType,editBoxVue.firstPlayer);
                } else {
                    alert("传入了一个非函数的参数");
                }
                closeDialog();
            }
        });

        // 绑定编辑取消事件
        $(".edit_box_button .cancer").off().on({
            "click": function () {
                closeDialog();
            }
        });

        // 显示编辑框
        $("#gameOption").css("display", "");
    }
    // endregion
});
