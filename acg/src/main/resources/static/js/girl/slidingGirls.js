/**
 * Created by LA on 2017/9/13.
 */
// region begin 美女主页滑动效果
// 等页面所有元素和资源加载完毕执行函数
window.onload = function () {

    var imgBox = document.getElementById("girlList");  // 通过id获得容器对象

    var imgs = imgBox.getElementsByTagName("img");  // 通过标签获得图片NodeList对象集合

    var imgWidth = imgs[0].offsetWidth;  // 获取单张图片的宽度

    var exposeWidth = imgWidth/3;  // 设置隐藏门露出的宽度

    var imgBoxWidth = imgWidth + exposeWidth * (imgs.length - 1);

    imgBox.style.width = imgBoxWidth + "px";  // css设置容器的总宽度，单位：px

    // 设置每道门的初始位置，第一扇门的位置为零
    function initPos() {
        for(var i=1,len;i<imgs.length;i++) {
            imgs[i].style.left = imgWidth + exposeWidth * (i - 1) + "px";
        }
    }
    initPos();

    var translate = imgWidth - exposeWidth;  // 计算打开门时，应该移动的距离

    // 为每一道门绑定鼠标滑动事件
    for(var i=0;i<imgs.length;i++) {
        // 使用立即调用的匿名函数
        (function (i) {
            imgs[i].onmouseover =function () {
                initPos();  // 鼠标移出需要复位复位，不然布局会乱
                for(var j=1;j<=i;j++) {
                    imgs[j].style.left = parseInt(imgs[j].style.left,10) - translate + "px";
                }
            }
        })(i);
    }
};
// endregion