var CY1 = "┣       - 创意 -       ┫";
var CY2 = "┣ 玩法仿制  　定制脚本 ┫";
var CY3 = "┣ 技术支持 　 游戏顾问 ┫";
var CY4 = "┣ ＷＺ添加　  地图制作 ┫";
var CY5 = "┣ 加盾防御　  售登陆器 ┫";
var CY7 = "┣ 手游开服    端游开服 ┫";
var CY8 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
var CY9 = "┣    唯一微信:ZerekY   ┫";
var CY0 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
var status = 0;
function start() {
    cm.sendYesNo("嗨，我是#p9010000# 想要和你的朋友在这个服留下美好的照片嗎？？");
}

function action(mode, type, selection) {
    if (mode != 1) {
        if (mode == 0)
        cm.sendOk("需要的時候，再來找我吧。");
        cm.dispose();
        return;
    }
    status++;
    if (status == 1) {
		cm.saveLocation("DONGDONGCHIANG");
		cm.warp(970000000, 0);
        cm.dispose();
}
}