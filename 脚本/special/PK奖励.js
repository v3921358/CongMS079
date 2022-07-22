var CY0 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
var CY1 = "┣       - 创意 -       ┫";
var CY2 = "┣ 玩法仿制  　定制脚本 ┫";
var CY3 = "┣ 技术支持 　 游戏顾问 ┫";
var CY4 = "┣ ＷＺ添加　  地图制作 ┫";
var CY5 = "┣ 加盾防御　  售登陆器 ┫";
var CY7 = "┣ 手游开服    端游开服 ┫";
var CY8 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
var CY9 = "┣   唯一QQ:3066318387  ┫";
var CY0 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
var rankRewards = [

    [41, 50, [
        [2, "金币", 1000000],
    ]],
    [31, 40, [
        [2, "金币", 5000000],
        [2, "抵用券", 1],
    ]],
    [21, 30, [
        [2, "金币", 10000000],
        [2, "抵用券", 1],
    ]],
    [11, 20, [
        [2, "金币", 50000000],
        [2, "抵用券", 1],
        [2, "点券", 5000],
        [3, "圣诞大星", 3992025, 1000],
    ]],
    [4, 10, [
        [2, "金币", 80000000],
        [2, "点券", 8000],
        [3, "圣诞大星", 3992025, 2000],
        [3, "锂", 4011008, 1],
        [3, "月石", 4011007, 1],
    ]],
    [3, 3, [
        [2, "金币", 1],
        [2, "点券", 1],
        [3, "活动奖励 第三名", 2022573, 1],
	    [3, "大吉纸条", 4031065, 1],
        [3, "国庆币", 4000463, 10],
    ]],
    [2, 2, [
        [2, "金币", 1],
        [2, "点券", 1],
        [2, "抵用券", 1],
        [3, "活动奖励 第二名", 2022572, 1],
        [3, "大吉纸条", 4031065, 1],
        [3, "国庆币", 4000463, 15],
    ]],
    [1, 1, [
        [2, "金币", 1],
        [2, "点券", 1],
        [2, "抵用券", 1],
        [3, "活动奖励 第一名", 2022571, 1],
        [3, "大吉纸条", 4031065, 1],
        [3, "国庆币", 4000463, 20],
    ]],
];
//-----------------------------------------------------
var status = -1; //模组状态
var chr = null;
var say = "";
var rank = 0;
var rewardMsg = ""; //奖励提示
var 正方箭头 = "#fUI/Basic/BtHide3/mouseOver/0#";
var _rew;
function start() {
    chr = cm["getPlayer"]();
    rank = chr["getBossLog"]("吃鸡Rank");
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
        return;
    }
    if (status == 0 && mode == 0) {
        cm.dispose();
        return;
    }
    if (mode == 1) {
        status++;
    } else {
        status--;
    }

    if (status == 0) {
         _rew = _fundReward();
        if (_rew == null) {
            cm.sendOk("抱歉，你的排名不足以领取奖励！");
            cm.dispose();
            return;
        }

        say = _getTitle("吃鸡礼包");

        say += "#b恭喜你在上次吃鸡比赛中获得了第#r [ " + rank + " ] #b名\r\n";

        say += "你将获得如下奖励：\r\n";
        rewardMsg = "";
        for (var i = 0; i < _rew[2].length; i++) {
            if (_rew[2][i][0] == 1) {
                rewardMsg += _rew[2][i][1] + " x " + _rew[2][i][4];
            } else if (_rew[2][i][0] == 2) {
                rewardMsg += _rew[2][i][1] + " x " + _rew[2][i][2];
            } else if (_rew[2][i][0] == 3) {
                rewardMsg += _rew[2][i][1] + " x " + _rew[2][i][3];
            }
            if (i + 1 < _rew[2].length) {
                rewardMsg += "  #k\r\n";
            }
        }
        /*if (rank == 1) {//xunzhang
            say += "[#v1142288#]#r#t1142288##k\r\n";
        } else if (rank == 2) {
            say += "[#v1142287#]#r#t1142288##k\r\n";
        } else if (rank == 3) {
            say += "[#v1142286#]#r#t1142288##k\r\n";
        }*/
        say += rewardMsg;
        say += "\r\n";


        cm.sendYesNo(say);
    } else if (status == 1) {
        var u1 = _fundReward();
        if (u1 == null) {
            cm["sendOk"]("抱歉，无法获取你的排名信息。");
            cm["dispose"]();
            return;
        }
        var typeCount = 0;
        var isOk = true;
        for (var i = 0; i < _rew[2].length; i++) {
            if (_rew[2][i][0] == 3) {
                typeCount += 1;
                if (isOk) {
                    if (!cm.canHold(_rew[2][i][2], _rew[2][i][3])) {
                        isOk = false;
                    }
                }
            }

        }

        if (!isOk) {
            cm.sendOk("#r#e抱歉，你的背包不够，请#b装备、消耗、其他栏#r每样最少保留#k" + typeCount + "#r个位置");
            cm.dispose();
            return;
        }

        for (var i = 0; i < _rew[2].length; i++) {
            if (_rew[2][i][0] == 1) {
                chr.setBossLog(_rew[2][i][2], _rew[2][i][3], _rew[2][i][4]);
            } else if (_rew[2][i][0] == 2) {
                if (_rew[2][i][1] == "金币") {
                    chr.gainMeso(_rew[2][i][2], true);
                } else if (_rew[2][i][1] == "点券") {
                    chr.modifyCSPoints(1, _rew[2][i][2], true);
                } else if (_rew[2][i][1] == "元宝") {
                    cm.gainzb(_rew[2][i][2]);
                } else if (_rew[2][i][1] == "抵用券") {
                    chr.modifyCSPoints(2, _rew[2][i][2], true);
                }
            } else if (_rew[2][i][0] == 3) {
                chr.gainItem(_rew[2][i][2], _rew[2][i][3]);
            }
        }
        /*if (rank == 1) {//xunzhang 
            cm["gainItem"](1142288, 50, 50, 50, 50, 888, 888, 50, 50, 50, 50, 0, 0, 0, 0, 72);
        } else if (rank == 2) {
            cm["gainItem"](1142287, 40, 40, 40, 40, 666, 666, 40, 40, 40, 40, 0, 0, 0, 0, 72);
        } else if (rank == 3) {
            cm["gainItem"](1142286, 30, 30, 30, 30, 444, 444, 40, 40, 40, 40, 0, 0, 0, 0, 72);
        }*/
        //重置排名
        chr.resetBossLog("吃鸡Rank");
        //消耗道具
        //cm.gainItem(2022670,-1);
        //喇叭提示
        say = "『吃鸡礼包』恭喜" + chr.getName() + "打开了吃鸡礼包,排名第" + rank + ",获得" + rewardMsg;
        cm.worldMessage(6, say);
        cm.dispose();
    } else {
        cm.dispose();
    }
}

var ul_cloud = "#fItem/Etc/0403/04031309/info/iconRaw#"; //
function _getTitle(t) {
    return " " + ul_cloud + ul_cloud + ul_cloud + ul_cloud + "#r#e『" + t + "』#k#n" + ul_cloud + ul_cloud + ul_cloud + ul_cloud + "\r\n\r\n";
}


function _fundReward() {
    if (rank <= 0) {
        return null;
    }
    for (var rRlaW1 = 0; rRlaW1 < rankRewards["length"]; rRlaW1++) {
        if (rank >= rankRewards[rRlaW1][0] && rank <= rankRewards[rRlaW1][1]) {
            return rankRewards[rRlaW1];
        }
    }
}