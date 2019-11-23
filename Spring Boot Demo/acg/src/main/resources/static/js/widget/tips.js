/**
 * Created by LA on 2017/9/14.
 */
$(function ($) {
    $.tips = {
        showPopup: function (tipInfo, fadeOutTime) {
            $("#popTip .tip_container").html(tipInfo);
            $("#popTip").css({
                position: "absolute",
                left: ($(window).width() - $("#popTip").outerWidth()) / 2,
                top: ($(window).height() - $("#popTip").outerHeight()) / 2
            });
            $("#popTip").css("z-index", 999);
            $("#popTip").show().fadeOut(fadeOutTime ? fadeOutTime : 1500);
        }
    };
});