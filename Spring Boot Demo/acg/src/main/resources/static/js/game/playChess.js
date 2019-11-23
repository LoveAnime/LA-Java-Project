/**
 * Created by LA on 2017/10/17.
 */

// region 五子棋游戏（可以选择人人对战模式和人机对战模式，并设置游戏选项）
$(function($){

    // region 网页DOM加载完毕后，初始化游戏设置，并给所有按钮初始化事件
    $(function ($) {
        $().setChessOption(600, 15, "PVC", "black");
        $().bindButtonEvent();
    });
    // endregion

    // region 全局变量的定义
    var chessCanvas;  // 画布标签
    var chessBoard;  // 棋盘对象
    var gapSize;  // 棋盘格子的大小
    var blankSize;  //棋盘边线空白大小
    var curPlayer;  // 玩家："black"黑方、"white"白方
    var firstPlayer = "black";  // 先手的玩家
    var chessRecord = [];  // 二维棋谱：0没落子，1黑子，-1白子
    var winCount;  // 统计有多少种可能的赢法
    var winErgodicity = [];  // 三维数组：遍历所有可能的赢法，每一种赢法都有对应的五个连续的值为true
    var blackWin = [];  // 一维数组：统计黑方在某种赢法下了多少个子（有效值为0~5），-1表示该种赢法不能赢了（白方有子）
    var whiteWin = [];  // 一维数组：统计白方在某种赢法下了多少个子（有效值为0~5），-1表示该种赢法不能赢了（黑方有子）
    var gameOver;
    var gameType = "PVC";  // "PVP"人人对战、"PVC"人机对战
    var boardLenth = 600;  //  600px大小
    var boardSize = 15;  //  15*15的棋盘
    var defencePriority;  // 不同连线子个数的优先级
    var offencePriority;
    // endregion

    // region 扩展与五子棋游戏设置、开始、关闭等实例方法
    $.fn.extend({
        // 绑定游戏按钮事件
        bindButtonEvent:function () {
            // region 绑定开始游戏和暂停游戏：加载和卸载单击落子事件
            $("#startButton").toggle(
                function () {
                    $(this).text("暂停游戏");
                    $().startChessGame();
                },
                function () {
                    $(this).text("开始游戏");
                    $().stopChessGame();
                }
            );
            // endregion

            // region 绑定重新开始游戏：重新刷新棋局
            $("#restartButton").bind("click", function () {
                $("#chessBoard").html("");
                $().setChessOption(boardLenth, boardSize, gameType, firstPlayer);
            });
            // endregion

            // region 弹出游戏设置界面
            $("#optionButton").bind("click", function () {
                $("#gameOption").openGameOptBox(boardLenth, boardSize, gameType, firstPlayer,$().setChessOption);
            });
            // endregion

            // region 游戏说明
            $("#helpButton").bind("click", function () {
                alert("游戏名称：五子棋游戏\n" +
                    "游戏规则：当一名玩家在横线、竖线、斜线等方向连续下了五个子后即游戏胜利。" +
                    "玩家可以选择人人对战、人机对战、设置先手方、设置棋盘大小等。" +
                    "人机对战模式中，电脑只能后手下棋。" );
            });
            // endregion
        },
        // 卸载游戏按钮按钮事件
        unbindButtonEvent:function () {
            $("#startButton").unbind("click");
        },
        // 初始化游戏参数
        setChessOption:function (length,size,type,player) {
            // 游戏设置：各种参数的初始化
            initOption(length, size, type, player);
            // 画出棋盘
            drawChessBoard(size);
            // 设置游戏模式：人人对战、人机对战
            initChessRule();
        },
        // 绑定单击落子事件
        startChessGame:function () {
            $("#chessBoard").bind("click", function (event) {
                if (gameOver) {
                    alert("游戏结束了~~~");
                    $().stopChessGame();
                    return;
                }
                moveOneRound(event);
            });
        },
        // 卸载单击落子事件
        stopChessGame:function () {
            $("#chessBoard").unbind("click");
        }
    });
    // endregion

    // region 全局变量的初始化
    function initOption(length, size, type, player) {
        boardLenth = length ? length : 600;
        boardSize = size ? size : 15;
        gameType = type ? type : "PVP";
        firstPlayer = player ? player : "black";
        curPlayer = firstPlayer;
        chessCanvas = $("#chessBoard")[0];
        chessCanvas.width = boardLenth;
        chessCanvas.height = boardLenth;
        gapSize = length / size;
        blankSize = (length - gapSize * (boardSize - 1)) / 2;
        gameOver = false;
        // 初始化二维棋盘的落子情况
        for(var i=0;i<boardSize;i++) {
            chessRecord[i] = [];
            for (var j = 0; j < boardSize; j++) {
                chessRecord[i][j] = 0;
            }
        }

        // 三维数组：用来记录可能赢棋的下法
        for(i=0;i<boardSize;i++) {
            winErgodicity[i] = [];
            for (j = 0; j < boardSize; j++) {
                winErgodicity[i][j] = [];
            }
        }
        winCount = 0;
        for(i=0;i<boardSize;i++) {
            for (j = 0; j < boardSize - 4; j++) {
                for (var n = 0; n < 5; n++) {
                    winErgodicity[i][j + n][winCount] = true;  // 横向排列
                    winErgodicity[j + n][i][winCount + 1] = true;  // 竖向排列
                }
                winCount += 2;
            }
        }
        for(i=0;i<boardSize-4;i++) {  // 斜线
            for (j = 0; j < boardSize - 4; j++) {
                for (n = 0; n < 5; n++) {
                    winErgodicity[i + n][j + n][winCount] = true;  // 正斜向排列
                    winErgodicity[i + n][j + 4 - n][winCount + 1] = true;  // 反斜向排列
                }
                winCount += 2;
            }
        }

        for(n=0;n<winCount;n++) {
            blackWin[n] = 0;
            whiteWin[n] = 0;
        }
    }
    // endregion

    // region 棋盘的绘制
    function drawChessBoard(size) {
        resizeBackground(boardLenth);
        // 二维画布：棋盘对象
        chessBoard = chessCanvas.getContext("2d");

        // 棋盘水印
        var chessLogo = new Image();
        chessLogo.src = "img/chessGame/chessLogo.png";
        $(chessLogo).load(function(){  // 图片加载完成后再绘制水印，即chessLogo.onload
            // 设置透明度
            chessBoard.globalAlpha = 0.1;
            chessBoard.drawImage(chessLogo, 0, 0, chessCanvas.width, chessCanvas.height);
        });

        // 设置直线颜色
        chessBoard.strokeStyle = "#111111";

        // n*n个交叉点的棋盘
        for (var i = 0; i < size; i++) {
            chessBoard.moveTo(blankSize + i * gapSize, blankSize);  // 设置画笔起点
            chessBoard.lineTo(blankSize + i * gapSize, chessCanvas.width - blankSize);  // 设置线条类型和画笔终点
            chessBoard.stroke();  // 画出横线

            chessBoard.moveTo(blankSize, blankSize + i * gapSize);  // 设置画笔起点
            chessBoard.lineTo(chessCanvas.height - blankSize, blankSize + i * gapSize);  // 设置线条类型和画笔终点
            chessBoard.stroke();  // 画出纵线
        }
    }
    // endregion

    // region 棋子的绘制
    function drawOnePiece(i, j, player) {
        chessBoard.globalAlpha = 1;  // 绘制水印时增加了透明度，需要还原
        chessBoard.beginPath();
        chessBoard.arc(blankSize + i * gapSize, blankSize + j * gapSize, gapSize *0.4, 0, 2 * Math.PI);  // 扇形：圆心坐标，半径，弧度范围
        chessBoard.closePath();
        // 设置棋子颜色渐变样式
        var gradient = chessBoard.createRadialGradient(blankSize + i * gapSize + 2, blankSize + j * gapSize - 2,
            gapSize * 0.4, blankSize + i * gapSize + 2, blankSize + j * gapSize - 2, 0);

        if (player == "black") {
            gradient.addColorStop(0, "#0A0A0A");
            gradient.addColorStop(1, "#636766");
        } else {
            gradient.addColorStop(0, "#D1D1D1");
            gradient.addColorStop(1, "#F9F9F9");
        }
        chessBoard.fillStyle = gradient;
        chessBoard.fill();
    }
    // endregion

    // region 落子：根据不同的对战模式，有不同的下棋规则
    function moveOneStep(event) {
        var x = event.offsetX;
        var y = event.offsetY;
        var i = Math.floor(x / gapSize);
        var j = Math.floor(y / gapSize);
        if (chessRecord[i][j] == 0) {
            drawOnePiece(i, j, curPlayer);
            for(var n=0;n<winCount;n++) {
                if(winErgodicity[i][j][n]) {
                    blackWin[n] = (curPlayer == "black") ? blackWin[n] + 1 : -1;
                    whiteWin[n] = (curPlayer == "white") ? whiteWin[n] + 1 : -1;
                }
                if(blackWin[n]==5||whiteWin[n]==5) {
                    alert("恭喜" + curPlayer + "赢了~~~");
                    gameOver = true;
                    return;
                }
            }
            chessRecord[i][j] = (curPlayer == "black") ? 1 : -1;
            curPlayer = (curPlayer == "black") ? "white" : "black";
        }
    }
    // endregion

    // region 一个落子回合：人人对战一个玩家一次落子表示一个回合、人机对战玩家+电脑表示一个回合
    var moveOneRound = function () {

    };
    // endregion

    // region 设置游戏模式：人人对战、人机对战
    function initChessRule() {
        defencePriority = [0, 200, 500, 2000, 10000];    // 不同连线子个数的优先级
        offencePriority = [0, 220, 520, 2100, 20000];
        if(gameType=="PVC" || gameType.toUpperCase() == "PVC"){
            initPVCRule();
        }else{
            initPVPRule();
        }
    }
    // endregion

    // region 人人对战：只需要捕捉鼠标单击事件，黑白双方轮流下棋
    function initPVPRule() {
        moveOneRound = moveOneStep ;
    }
    // endregion

    // region 人机对战：白方为电脑，增加AI智能，玩家必须先手
    function initPVCRule() {
        moveOneRound = function (event) {
            moveOneStep(event);
            if(!gameOver){
                computerAI();
            }
        }
    }
    // endregion

    // region 电脑AI：给不同情况的下法设置优先级，寻找最优下法
     function computerAI() {
        var defenceScore = [];  // 二维数组：棋盘上每个子的得分
        var offenceScore = [];
        var maxScore = 0, maxU = 0, maxV = 0;
        for (var i = 0; i < chessCanvas.height/gapSize; i++) {
            defenceScore[i] = [];
            offenceScore[i] = [];
            for (var j = 0; j < chessCanvas.width/gapSize; j++) {
                defenceScore[i][j] = 0;
                offenceScore[i][j] = 0;
            }
        }

        for (i = 0; i < chessCanvas.height/gapSize; i++) {
            for (j = 0; j < chessCanvas.width/gapSize; j++) {
                if(chessRecord[i][j]==0){
                    for(var n=0;n<winCount;n++){
                        if(winErgodicity[i][j][n]) {
                            if(curPlayer=="black"){
                                if(whiteWin[n]>=1){
                                    defenceScore[i][j] += defencePriority[whiteWin[n]];
                                }
                                if(blackWin[n]>=1){
                                    offenceScore[i][j] += offencePriority[blackWin[n]];
                                }
                            }
                            else if(curPlayer=="white"){
                                if(blackWin[n]>=1){
                                    defenceScore[i][j] += defencePriority[blackWin[n]];
                                }
                                if(whiteWin[n]>=1){
                                    offenceScore[i][j] += offencePriority[whiteWin[n]];
                                }
                            }
                            else{
                                alert("不明身份的玩家出现了~~~");
                                gameOver = true;
                            }
                        }
                    }
                    if( defenceScore[i][j] > maxScore ){
                        maxScore = defenceScore[i][j];
                        maxU = i;
                        maxV = j;
                    }else if( defenceScore[i][j] == maxScore ){
                        if( offenceScore[i][j] > offenceScore[maxU][maxV] ){
                            maxU = i;
                            maxV = j;
                        }
                    }
                    if( offenceScore[i][j] > maxScore ){
                        maxScore = offenceScore[i][j];
                        maxU = i;
                        maxV = j;
                    }else if( offenceScore[i][j] == maxScore ){
                        if( defenceScore[i][j] > defenceScore[maxU][maxV] ){
                            maxU = i;
                            maxV = j;
                        }
                    }
                }
            }
        }
        var e = new Object();
        e.offsetX = maxU * gapSize;
        e.offsetY = maxV * gapSize;
        moveOneStep(e);
    }
    // endregion

    // region 若棋盘超出了600px，需要对背景尺寸进行调整
    function resizeBackground(length) {
        if(length<=600) {
            return false;
        }

        $(".left_body_frame").height($(".left_body_frame").height() + (length - 600));
        $(".right_body_frame").height($(".right_body_frame").height() + (length - 600));
        $(".right_body_frame").width($(".right_body_frame").width() + (length - 600));
        $(".bottom_frame").width($(".bottom_frame").width() + (length - 600));
    }
    // endregion
});
// endregion