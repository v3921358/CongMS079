/*
 ZEVMS冒险岛(079)游戏服务端
 脚本：游戏CDK兑换系统
 使用函数：
 给个人记录
 cm.setBossRankCount("点券积分", fee);
 */


//玩家充值点券，反馈给推广员的百分比点券
var 推广员反馈百分比 = 10;


importPackage(net.sf.odinms.client);
var status = 0;
var 心 = "#fUI/GuildMark.img/Mark/Etc/00009001/14#";
var fee;
var chance = Math.floor(Math.random() * 1);
function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.对话结束();
    } else {
        if (mode == 0) {
            cm.说明文字("你没有卡号？");
            cm.对话结束();
            return;
        }
        if (mode == 1) {
            status++;
        } else {
            status--;
        }
        if (cm.getInventory(1).isFull()) {
            cm.说明文字("请保证 #b装备栏#k 至少有2个位置。");
            cm.对话结束();
            return;
        } else if (cm.getInventory(2).isFull()) {
            cm.说明文字("请保证 #b消耗栏#k 至少有2个位置。");
            cm.对话结束();
            return;
        } else if (cm.getInventory(3).isFull()) {
            cm.说明文字("请保证 #b设置栏#k 至少有2个位置。");
            cm.对话结束();
            return;
        } else if (cm.getInventory(4).isFull()) {
            cm.说明文字("请保证 #b其他栏#k 至少有2个位置。");
            cm.对话结束();
            return;
        } else if (cm.getInventory(5).isFull()) {
            cm.说明文字("请保证 #b特殊栏#k 至少有2个位置。");
            cm.对话结束();
            return;
        }
        //推广员信息
        var 角色 = cm.getPlayer().id;
        var 推广员名字 = cm.角色ID取名字(cm.getBossRank("推广员", 2));
        var 推广员ID = cm.getBossRank("推广员", 2);
        var 积分 = cm.getBossRank("点券积分", 2);
        if (积分 < 0) {
            var 积分 = 0;
        }
        if (status == 0) {
            cm.sendGetText("    Hi~#b#h ##k，这里是#b" + cm.开服名称() + "#k自助系统，如果你有Cdk兑换码的话，可以在这里使用，就可以兑换了。#k\r\n\r\n请输入合法的兑换Cdk；");
        } else if (status == 1) {
            fee = cm.getText();
            //判断卡号是否存在
            if (cm.判断兑换卡是否存在("" + fee + "") <= 0) {
                cm.说明文字("卡号不存在，或者该卡号未使用，请你稍后再试试。");
                cm.对话结束();
                return;
            }
            //判断该兑换卡是点券
            if (cm.判断兑换卡类型("" + fee + "") == 1) {
                //充值点券
                cm.gainNX(cm.判断兑换卡数额("" + fee + ""));
                //输出提示语
                cm.说明文字("恭喜你成功兑换了 #r" + cm.判断兑换卡数额("" + fee + "") + "#k 点券。");
                //记录玩家充值的点券
                cm.setBossRankCount("点券积分", cm.判断兑换卡数额("" + fee + ""));
                //判断是否有推广员，如果有，就将部分充值分享给推广员
                if (cm.getBossRank("推广员", 2) > 0) {
                    //给推广员发送返利
                    cm.Gaincharacterz("" + 推广员ID + "", 300, (cm.判断兑换卡数额("" + fee + "") / 100 * 推广员反馈百分比));
                    //给推广员发送小纸条
                    cm.小纸条("" + 推广员名字 + "", "[充值返利]:" + cm.getChar().getName() + " 充值 " + cm.判断兑换卡数额("" + fee + "") + " 点券，你获得返利 " + cm.判断兑换卡数额("" + fee + "") / 100 * 推广员反馈百分比 + " 点券。");
                }
                //判断该兑换卡是抵用券
            } else if (cm.判断兑换卡类型("" + fee + "") == 2) {
                //充值抵用券
                cm.gainD(cm.判断兑换卡数额("" + fee + ""));
                //输出提示语
                cm.说明文字("恭喜你成功兑换了 #r" + cm.判断兑换卡数额("" + fee + "") + "#k 抵用券。");
                //判断该兑换卡是礼包
            } else if (cm.判断兑换卡类型("" + fee + "") == 5) {
                //打开礼包
                var Lb = cm.判断兑换卡礼包("" + fee + "");
                switch (Lb) {
                    //体验卡
                    case 3:
                        if (cm.getBossRank("公益VIP体验", 2) <= 0) {
                            cm.对话结束();
                            cm.打开NPC(9900005, Lb);
                        } else {
                            cm.说明文字("一个角色只能使用一次体验卡，你已经使用过了，无法继续使用。");
                            cm.对话结束();
                        }
                        break;
                        //群聊私聊的奖励CDK
                    case 100:
                        cm.对话结束();
                        cm.打开NPC(9900005, Lb);
                        cm.充值卡兑换记录("群聊奖励", "" + cm.getChar().getName() + " 使用了群聊奖励，cdk" + fee + "");
                        break;
                    default:
                        cm.对话结束();
                        cm.打开NPC(9900005, Lb);
                        break;
                }
            }
            //使用成功后删除兑换卡
            cm.Deleteexchangecard("" + fee + "");
        }
    }
}