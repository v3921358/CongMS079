var 微笑 = "#fUI/GuildBBS.img/GuildBBS/Emoticon/Basic/0#";
var 难受 = "#fUI/GuildBBS.img/GuildBBS/Emoticon/Basic/1#";
var 开心 = "#fUI/GuildBBS.img/GuildBBS/Emoticon/Basic/2#";
var 蓝色音符 = "#fEffect/CharacterEff/1003252/1/0#";
function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (status >= 0 && mode == 0) {
            cm.sendOk("感谢你的光临！");
            cm.dispose();
            return;
        }
        if (mode == 1) {
            status++;
        } else {
            status--;
        }
        if (status == 0) {
            var text = "";
            for (i = 0; i < 100; i++) { 
                text += "";
            }
			 
            var 心疤狮王暴力熊人数 = cm.getPlayerCount(551030200);
            var 心疤狮王暴力熊已挑战次数 = cm.getBossLog("狮王暴力熊已挑战次数");
            var 闹钟人数 = cm.getPlayerCount(220080001);
            var 闹钟已挑战次数 = cm.getBossLog("闹钟已挑战次数");
            var 鱼王人数 = cm.getPlayerCount(230040420);
            var 鱼王已挑战次数 = cm.getBossLog("鱼王已挑战次数");
            var 扎昆人数 = cm.getPlayerCount(280030000);
            var 扎昆已挑战次数 = cm.getBossLog("扎昆已挑战次数");
            var 黑龙人数 = cm.getPlayerCount(240060200);
            var 黑龙已挑战次数 = cm.getBossLog("黑龙已挑战次数");
            var 品克缤人数 = cm.getPlayerCount(910000022);
            var 品克缤已挑战次数 = cm.getBossLog("品克缤已挑战次数");
			var 贝伦人数 = cm.getPlayerCount(910000021);
            var 贝伦已挑战次数 = cm.getBossLog("贝伦已挑战次数");
			var 赤虎人数 = cm.getPlayerCount(910000020);
            var 赤虎已挑战次数 = cm.getBossLog("赤虎已挑战次数");
            var 麦格那厮人数 = cm.getPlayerCount(910000019);
            var 麦格那厮已挑战次数 = cm.getBossLog("麦格那厮已挑战次数");
            var 邪恶兜蛇兄弟人数 = cm.getPlayerCount(910000018);
            var 邪恶兜蛇兄弟已挑战次数 = cm.getBossLog("邪恶兜蛇兄弟已挑战次数");
            var 拌拌人数 = cm.getPlayerCount(910000017);
            var 拌拌已挑战次数 = cm.getBossLog("拌拌已挑战次数");
            text += "#b亲爱的#k [#r" + cm.getPlayer().getName() + "#k] #b欢迎来到#b祝您游戏愉快!!#k\r\n";
            text += "#r---------------------每日BOSS挑战---------------------";
            text += "#r\t\t#L13##b请先点我查看挑战需求和注意事项#l#k\r\n\r\n";
            text += "#rBOSS挑战状态：\r\n";
            text += "#r[心疤狮王|暴力熊：" + 心疤狮王暴力熊人数 + "人]\r\n[闹钟：" + 闹钟人数 + "人][鱼王：" + 鱼王人数 + "人]\r\n[扎昆：" + 扎昆人数 + "人][黑龙：" + 黑龙人数 + "人][品克缤：" + 品克缤人数 + "人]\r\n[贝伦：" + 贝伦人数 + "人][赤虎：" + 赤虎人数 + "人][麦格那厮：" + 麦格那厮人数 + "人]\r\n[邪恶兜蛇兄弟：" + 邪恶兜蛇兄弟人数 + "人][拌拌：" + 拌拌人数 + "人]\r\n";
            text += "#r------------------------------------------------------\r\n";
            text += "#b心疤狮王|暴力熊(限制等级120~250)\r\n[每天2次][已挑战：" + 心疤狮王暴力熊已挑战次数 + "次]\r\n\t\t\t#L1#进入挑战#l #L2#重置次数#l #L20#购买挑战物品#l#k\r\n\r\n";
            text += "#r------------------------------------------------------\r\n";
            text += "#b闹钟-(限制等级120~250)\r\n仅开放第3线可召唤出boss\r\n[每天2次][已挑战：" + 闹钟已挑战次数 + "次]\r\n\t\t\t#L3#进入挑战#l #L4#重置次数#l #L21#购买挑战物品#l#k\r\n\r\n";
            text += "#r------------------------------------------------------\r\n";
            text += "#b鱼王-(限制等级120~250)\r\n[每天2次][已挑战：" + 鱼王已挑战次数 + "次]\r\n\t\t\t#L5#进入挑战#l #L6#重置次数#l #L22#购买挑战物品#l#k\r\n\r\n";
            text += "#r------------------------------------------------------\r\n";
            text += "#b扎昆-(限制等级120~250)\r\n[每天2次][已挑战：" + 扎昆已挑战次数 + "次]\r\n\t\t\t#L7#进入挑战#l #L8#重置次数#l #L17#购买挑战物品#l#k\r\n\r\n";
            text += "#r------------------------------------------------------\r\n";
            text += "#b暗黑龙王-(限制等级140~250)\r\n[每天2次][已挑战：" + 黑龙已挑战次数 + "次]\r\n\t\t\t#L9#进入挑战#l #L10#重置次数#l #L18#购买挑战物品#l#k\r\n\r\n";
            text += "#r------------------------------------------------------\r\n";
            text += "#b品克缤-(限制等级150~250)5000W金币挑战\r\n[每天1次][已挑战：" + 品克缤已挑战次数 + "次]\r\n\t\t\t#L11#进入挑战#l #L12#重置次数#l #L19#购买挑战物品#l#k\r\n\r\n";
            text += "#r------------------------------------------------------\r\n";
			//text += "#b贝伦-(限制等级200~250)6000W金币挑战\r\n[每天3次][已挑战：" + 贝伦已挑战次数 + "次]\r\n\t\t\t#L14#进入挑战#l #L15#重置次数#l #L24#购买挑战物品#l#k\r\n\r\n";
            //text += "#r------------------------------------------------------\r\n";
			//text += "#b赤虎-(限制等级200~250)3000W金币挑战\r\n[每天3次][已挑战：" + 赤虎已挑战次数 + "次]\r\n\t\t\t#L50#进入挑战#l #L51#重置次数#l #L52#购买挑战物品#l#k\r\n\r\n";
            //text += "#r------------------------------------------------------\r\n";
			//text += "#b麦格那厮-(限制等级200~250)3000W金币挑战\r\n[每天3次][已挑战：" + 麦格那厮已挑战次数 + "次]\r\n\t\t\t#L53#进入挑战#l #L54#重置次数#l #L55#购买挑战物品#l#k\r\n\r\n";
            //text += "#r------------------------------------------------------\r\n";
			//text += "#b邪恶兜蛇兄弟-(限制等级200~250)3000W金币挑战\r\n[每天3次][已挑战：" + 邪恶兜蛇兄弟已挑战次数 + "次]\r\n\t\t\t#L56#进入挑战#l #L57#重置次数#l #L58#购买挑战物品#l#k\r\n\r\n";
            //text += "#r------------------------------------------------------\r\n";
			//text += "#b拌拌-(限制等级200~250)3000W金币挑战\r\n[每天3次][已挑战：" + 拌拌已挑战次数 + "次]\r\n\t\t\t#L59#进入挑战#l #L60#重置次数#l #L61#购买挑战物品#l#k\r\n\r\n";

            cm.sendSimpleS(text, 2);
        } else if (selection == 1) {
            if (cm.getPlayer().getLevel() >= 120) {
                if (cm.getBossLog("狮王暴力熊已挑战次数") < 2) {
                    if (cm.haveItem(4032246, 1)) {
                        if (cm.getPlayerCount(551030200) <= 0) { // Fant. Map
                            var FantMap = cm.getMap(551030200);
                            FantMap.resetFully();
                            //cm.gainItem(4032246, -1);
                            cm.warpParty(551030200, "sp");
                            cm.getPlayer().setBossLog('狮王暴力熊已挑战次数');
                            cm.喇叭(2, "玩家[" + cm.getPlayer().getName() + "]开始挑战心疤狮王、暴力熊。让我们期待他的表现。")
                            cm.dispose();
                        } else {
                            if (cm.getMap(551030200).getSpeedRunStart() == 0 && (cm.getMonsterCount(551030200) <= 0 || cm.getMap(551030200).isDisconnected(cm.getPlayer().getId()))) {
                                //cm.gainItem(4032246, -1);
                                cm.warpParty(551030200, "sp");
                                cm.getPlayer().setBossLog('狮王暴力熊已挑战次数');
                                cm.喇叭(2, "玩家[" + cm.getPlayer().getName() + "]开始挑战心疤狮王、暴力熊。让我们期待他的表现。")
                                cm.dispose();
                            } else {
                                cm.sendOk("里面的战斗已经开始了，请等待。");
                                cm.dispose();
                            }
                        }
                    } else {
                        cm.sendOk("你没有#v4032246##z4032246#，无法挑战，请到商城内购买");
                        cm.dispose();
                    }
                } else {
                    cm.sendOk("你今日的挑战次数已经用完。");
                    cm.dispose();
                }
            } else {
                cm.sendOk("等级不足120级，无法进入。");
                cm.dispose();
            }
        } else if (selection == 2) {
            if (cm.getNX(1) >= 1000) {
                cm.gainNX(-1000);
                cm.getPlayer().resetBossLog('狮王暴力熊已挑战次数');
                cm.sendOk("重置挑战次数成功！！");
                cm.喇叭(2, "恭喜玩家[" + cm.getPlayer().getName() + "]消费1000点券重置了 心疤狮王、暴力熊的今日挑战次数。")
                cm.dispose();
            } else {
                cm.sendOk("点券不足1000无法重置挑战次数");
                cm.dispose();
            }
        } else if (selection == 3) {
            if (cm.getPlayer().getLevel() >= 120) {
                if (cm.getBossLog("闹钟已挑战次数") < 2) {
                    if (cm.haveItem(4031179, 1)) {
                        if (cm.getPlayerCount(220080001) <= 0) {
                            var FantMap = cm.getMap(220080001);
                            FantMap.resetFully();
                            //cm.gainItem(4031179, -1);
                            cm.warpParty(220080001, "st00");
                            cm.getPlayer().setBossLog('闹钟已挑战次数');
                            cm.喇叭(2, "玩家[" + cm.getPlayer().getName() + "]开始挑战帕普拉图斯。让我们期待他的表现。")
                            cm.dispose();
                        } else {
                            if (cm.getMap(220080001).getSpeedRunStart() == 0 && (cm.getMonsterCount(220080001) <= 0 || cm.getMap(220080001).isDisconnected(cm.getPlayer().getId()))) {
                                //cm.gainItem(4031179, -1);
                                cm.warpParty(220080001, "st00");
                                cm.getPlayer().setBossLog('闹钟已挑战次数');
                                cm.喇叭(2, "玩家[" + cm.getPlayer().getName() + "]开始挑战帕普拉图斯。让我们期待他的表现。")
                                cm.dispose();
                            } else {
                                cm.sendOk("里面的战斗已经开始了，请等待。");
                                cm.dispose();
                            }
                        }
                    } else {
                        cm.sendOk("你没有#v4031179##z4031179#，无法挑战，请到商城内购买");
                        cm.dispose();
                    }
                } else {
                    cm.sendOk("你今日的挑战次数已经用完。");
                    cm.dispose();
                }
            } else {
                cm.sendOk("等级不足120级，无法进入。");
                cm.dispose();
            }
        } else if (selection == 4) {
            if (cm.getNX(1) >= 1000) {
                cm.gainNX(-1000);
                cm.getPlayer().resetBossLog('闹钟已挑战次数');
                cm.sendOk("重置挑战次数成功！！");
                cm.喇叭(2, "恭喜玩家[" + cm.getPlayer().getName() + "]消费1000点券重置了 帕普拉图斯的今日挑战次数。")
                cm.dispose();
            } else {
                cm.sendOk("点券不足1000无法重置挑战次数");
                cm.dispose();
            }
        } else if (selection == 5) {
            //230040420
            if (cm.getPlayer().getLevel() >= 120) {
                if (cm.getBossLog("鱼王已挑战次数") < 2) {
                    if (cm.haveItem(4000175, 1)) {
                        if (cm.getPlayerCount(230040420) <= 0) {
                            var FantMap = cm.getMap(230040420);
                            FantMap.resetFully();
                            cm.gainItem(4000175, -1);
                            cm.warpParty(230040420, "out00");
                            cm.getPlayer().setBossLog('鱼王已挑战次数');
                            cm.喇叭(2, "玩家[" + cm.getPlayer().getName() + "]开始挑战鱼王。让我们期待他的表现。")
                            cm.dispose();
                        } else {
                            if (cm.getMap(230040420).getSpeedRunStart() == 0 && (cm.getMonsterCount(230040420) <= 0 || cm.getMap(230040420).isDisconnected(cm.getPlayer().getId()))) {
                                cm.gainItem(4000175, -1);
                                cm.warpParty(230040420, "out00");
                                cm.getPlayer().setBossLog('鱼王已挑战次数');
                                cm.喇叭(2, "玩家[" + cm.getPlayer().getName() + "]开始挑战鱼王。让我们期待他的表现。")
                                cm.dispose();
                            } else {
                                cm.sendOk("里面的战斗已经开始了，请等待。");
                                cm.dispose();
                            }
                        }
                    } else {
                        cm.sendOk("你没有#v4000175##z4000175#，无法挑战，请到商城内购买");
                        cm.dispose();
                    }
                } else {
                    cm.sendOk("你今日的挑战次数已经用完。");
                    cm.dispose();
                }
            } else {
                cm.sendOk("等级不足120级，无法进入。");
                cm.dispose();
            }
        } else if (selection == 6) {
            if (cm.getNX(1) >= 1000) {
                cm.gainNX(-1000);
                cm.getPlayer().resetBossLog('鱼王已挑战次数');
                cm.sendOk("重置挑战次数成功！！");
                cm.喇叭(2, "恭喜玩家[" + cm.getPlayer().getName() + "]消费1000点券重置了 鱼王的今日挑战次数。")
                cm.dispose();
            } else {
                cm.sendOk("点券不足1000无法重置挑战次数");
                cm.dispose();
            }
        } else if (selection == 7) {
            //280030000 祭坛
			//211042300 远征
            if (cm.getPlayer().getLevel() >= 50) {
                if (cm.getBossLog("扎昆已挑战次数") < 2) {
                    if (cm.haveItem(4001017, 1)) {
                        if (cm.getPlayerCount(280030000) <= 0) {
                            var FantMap = cm.getMap(280030000);
                            FantMap.resetFully();
                            //cm.gainItem(4001017, -1);
                            cm.warpParty(211042400);
                            cm.getPlayer().setBossLog('扎昆已挑战次数');
                            cm.喇叭(2, "玩家[" + cm.getPlayer().getName() + "]开始挑战扎昆。让我们期待他的表现。")
                            cm.dispose();
                        } else {
                            if (cm.getMap(280030000).getSpeedRunStart() == 0 && (cm.getMonsterCount(280030000) <= 0 || cm.getMap(280030000).isDisconnected(cm.getPlayer().getId()))) {
                                //cm.gainItem(4001017, -1);
                                cm.warpParty(211042400);
                                cm.getPlayer().setBossLog('扎昆已挑战次数');
                                cm.喇叭(2, "玩家[" + cm.getPlayer().getName() + "]开始挑战扎昆。让我们期待他的表现。")
                                cm.dispose();
                            } else {
                                cm.sendOk("里面的战斗已经开始了，请等待。");
                                cm.dispose();
                            }
                        }
                    } else {
                        cm.sendOk("你没有#v4001017##z4001017#，无法挑战，请到商城内购买");
                        cm.dispose();
                    }
                } else {
                    cm.sendOk("你今日的挑战次数已经用完。");
                    cm.dispose();
                }
            } else {
                cm.sendOk("等级不足50级，无法进入。");
                cm.dispose();
            }
        } else if (selection == 8) {
            if (cm.getNX(1) >= 1000) {
                cm.gainNX(-1000);
                cm.getPlayer().resetBossLog('扎昆已挑战次数');
                cm.sendOk("重置挑战次数成功！！");
                cm.喇叭(2, "恭喜玩家[" + cm.getPlayer().getName() + "]消费1000点券重置了 扎昆的今日挑战次数。")
                cm.dispose();
            } else {
                cm.sendOk("点券不足1000无法重置挑战次数");
                cm.dispose();
            }
        } else if (selection == 9) {
            //240060200
            //5220006 - 黑龙入场券 - 凭此票可进入黑龙洞探险.每次进入消耗1张.
			//240050400远征
            if (cm.getPlayer().getLevel() >= 140) {
                if (cm.getBossLog("黑龙已挑战次数") < 2) {
                    if (cm.haveItem(5220006, 1)) {
                        if (cm.getPlayerCount(240060200) <= 0) {
                            var FantMap = cm.getMap(240060200);
                            FantMap.resetFully();
                            cm.gainItem(5220006, -1);
                            cm.warpParty(240050400);
                            cm.刷新地图();
                            cm.getPlayer().setBossLog('黑龙已挑战次数');
                            cm.喇叭(2, "玩家[" + cm.getPlayer().getName() + "]开始挑战暗黑龙王。让我们期待他的表现。")
                            cm.dispose();
                        } else {
                            if (cm.getMap(240060200).getSpeedRunStart() == 0 && (cm.getMonsterCount(240060200) <= 0 || cm.getMap(240060200).isDisconnected(cm.getPlayer().getId()))) {
                                cm.gainItem(5220006, -1);
                                cm.warpParty(240050400);
                                cm.getPlayer().setBossLog('黑龙已挑战次数');
                                cm.喇叭(2, "玩家[" + cm.getPlayer().getName() + "]开始挑战暗黑龙王。让我们期待他的表现。")
                                cm.dispose();
                            } else {
                                cm.sendOk("里面的战斗已经开始了，请等待。");
                                cm.dispose();
                            }
                        }
                    } else {
                        cm.sendOk("你没有#v5220006##z5220006#，无法挑战，请购买");
                        cm.dispose();
                    }
                } else {
                    cm.sendOk("你今日的挑战次数已经用完。");
                    cm.dispose();
                }
            } else {
                cm.sendOk("等级不足140级，无法进入。");
                cm.dispose();
            }
        } else if (selection == 10) {
            if (cm.getNX(1) >= 1000) {
                cm.gainNX(-1000);
                cm.getPlayer().resetBossLog('黑龙已挑战次数');
                cm.sendOk("重置挑战次数成功！！");
                cm.喇叭(2, "恭喜玩家[" + cm.getPlayer().getName() + "]消费1000点券重置了 暗黑龙王的今日挑战次数。")
                cm.dispose();
            } else {
                cm.sendOk("点券不足1000无法重置挑战次数");
                cm.dispose();
            }
        } else if (selection == 11) {
            //270050100
            if (cm.getPlayer().getLevel() >= 150) {
                if (cm.getBossLog("品克缤已挑战次数") < 1) {
                    if (cm.getPlayer().getMeso() >50000000) {
                        if (cm.getPlayerCount(910000022) <= 0) {
                            var FantMap = cm.getMap(910000022);
                            FantMap.resetFully();
                            //cm.gainNX(-1000);
                            cm.gainMeso(-50000000);
                            cm.warpParty(910000022);
							cm.刷新地图();
							cm.spawnMobStats(8820001,2980000000,300000000);
                            cm.getPlayer().setBossLog('品克缤已挑战次数');
                            cm.喇叭(2, "玩家[" + cm.getPlayer().getName() + "]开始挑战品克缤。让我们期待他的表现。")
                            cm.dispose();
                        } else {
                            if (cm.getMap(910000022).getSpeedRunStart() == 0 && (cm.getMonsterCount(910000022) <= 0 || cm.getMap(910000022).isDisconnected(cm.getPlayer().getId()))) {
                                //cm.gainNX(-1000);
                                cm.warpParty(910000022);
                                cm.getPlayer().setBossLog('品克缤已挑战次数');
                                cm.喇叭(2, "玩家[" + cm.getPlayer().getName() + "]开始挑战品克缤。让我们期待他的表现。")
                                cm.dispose();
                            } else {
                                cm.sendOk("里面的战斗已经开始了，请等待。");
                                cm.dispose();
                            }
                        }
                    } else {
                        cm.sendOk("金币不足5000W.无法挑战。");
                        cm.dispose();
                    }
                } else {
                    cm.sendOk("你今日的挑战次数已经用完。");
                    cm.dispose();
                }
            } else {
                cm.sendOk("等级不足150级，无法进入。");
                cm.dispose();
            }
        } else if (selection == 12) {
            if (cm.getNX(1) >= 10000) {
                cm.gainNX(-10000);
                cm.getPlayer().resetBossLog('品克缤已挑战次数');
                cm.sendOk("重置挑战次数成功！！");
                cm.喇叭(2, "恭喜玩家[" + cm.getPlayer().getName() + "]消费10000点券重置了 品克缤的今日挑战次数。")
                cm.dispose();
            } else {
                cm.sendOk("点券不足10000无法重置挑战次数");
                cm.dispose();
            }
        } else if (selection == 13) {
            cm.sendOk("挑战次数：\r\n心疤狮王暴力熊[每人每天2次]\r\n闹钟[每人每天2次]\r\n鱼王[每人每天2次]\r\n扎昆[每人每天2次]\r\n黑龙[每人每天2次]\r\n品克缤[每人每天1次]\r\n\r\n挑战需求：\r\n心疤狮王暴力熊[需要：#z4032246#x1个]\r\n闹钟[需要：#z4031179#x1个]\r\n鱼王[需要：#z4000175#x1个]\r\n扎昆[需要：#z4001017#x1个]\r\n暗黑龙王[需要：#z5220006#x1个]\r\n品克缤[需要：点券x1000]\r\n\r\n注意事项：\r\n1、请看清每日可挑战的次数，挑战途中，一旦死亡，被传送出去，也算一次。\r\n2、每次挑战，都会扣掉所需物品哦~！\r\n3、请量力而行，你打不过怨谁？自己评估自己的能力！");
            cm.dispose();
        } else if (selection == 14) {
            //270050100
            if (cm.getPlayer().getLevel() >= 200) {
                if (cm.getBossLog("贝伦已挑战次数") < 3) {
                    if (cm.getPlayer().getMeso() >60000000) {
                        if (cm.getPlayerCount(910000021) <= 0) {
                            var FantMap = cm.getMap(910000021);
                            FantMap.resetFully();
                            //cm.gainNX(-2000);
                            cm.gainMeso(-60000000);
                            cm.warpParty(910000021);
							cm.刷新地图();
							cm.spawnMobStats(8810000,3280000000,500000000);
                            cm.getPlayer().setBossLog('贝伦已挑战次数');
                            cm.喇叭(2, "玩家[" + cm.getPlayer().getName() + "]开始挑战贝伦。让我们期待他的表现。")
                            cm.dispose();
                        } else {
                            if (cm.getMap(910000021).getSpeedRunStart() == 0 && (cm.getMonsterCount(910000021) <= 0 || cm.getMap(910000021).isDisconnected(cm.getPlayer().getId()))) {
                                //cm.gainNX(-1000);
                                cm.warpParty(910000021);
                                cm.getPlayer().setBossLog('贝伦已挑战次数');
                                cm.喇叭(2, "玩家[" + cm.getPlayer().getName() + "]开始挑战贝伦。让我们期待他的表现。")
                                cm.dispose();
                            } else {
                                cm.sendOk("里面的战斗已经开始了，请等待。");
                                cm.dispose();
                            }
                        }
                    } else {
                        cm.sendOk("金币不足6000W.无法挑战。");
                        cm.dispose();
                    }
                } else {
                    cm.sendOk("你今日的挑战次数已经用完。");
                    cm.dispose();
                }
            } else {
                cm.sendOk("等级不足180级，无法进入。");
                cm.dispose();
            }
        } else if (selection == 15) {
            if (cm.getNX(1) >= 10000) {
                cm.gainNX(-10000);
                cm.getPlayer().resetBossLog('贝伦已挑战次数');
                cm.sendOk("重置挑战次数成功！！");
                cm.喇叭(2, "恭喜玩家[" + cm.getPlayer().getName() + "]消费10000点券重置了 贝伦的今日挑战次数。")
                cm.dispose();
            } else {
                cm.sendOk("点券不足10000无法重置挑战次数");
                cm.dispose();
            }
        } else if (selection == 16) {
            if (!cm.canHold(4000175, 1)) {
                cm.sendOk("背包已满!");
                cm.dispose();
                return;
            }
            if (cm.getNX(1) >= 300) {
                cm.gainNX(-300);
                cm.gainItem(4000175, 1);
                cm.sendOk("购买成功!");
                cm.喇叭(2,"恭喜玩家[" + cm.getPlayer().getName() + "]消费了300点券购买了鱼王BOSS的挑战物品。");
                cm.dispose();
            } else {
                cm.sendOk("您的点券不足300.无法购买");
                cm.dispose();
            }
        } else if (selection == 17) {
            if (!cm.canHold(4001017, 1)) {
                cm.sendOk("背包已满!");
                cm.dispose();
                return;
            }
            if (cm.getNX(1) >= 400) {
                cm.gainNX(-400);
                cm.gainItem(4001017, 1);
                cm.sendOk("购买成功!");
                cm.喇叭(2,"恭喜玩家[" + cm.getPlayer().getName() + "]消费了400点券购买了扎昆BOSS的挑战物品。");
                cm.dispose();
            } else {
                cm.sendOk("您的点券不足400.无法购买");
                cm.dispose();
            }
        } else if (selection == 20) {
            if (!cm.canHold(4032246, 1)) {
                cm.sendOk("背包已满!");
                cm.dispose();
                return;
            }
            if (cm.getNX(1) >= 100) {
                cm.gainNX(-100);
                cm.gainItem(4032246, 1);
                cm.sendOk("购买成功!");
                cm.喇叭(2,"恭喜玩家[" + cm.getPlayer().getName() + "]消费了100点券购买了暴力熊/心疤狮王BOSS的挑战物品。");
                cm.dispose();
            } else {
                cm.sendOk("您的点券不足100.无法购买");
                cm.dispose();
            }
        } else if (selection == 21) {
            if (!cm.canHold(4031179, 1)) {
                cm.sendOk("背包已满!");
                cm.dispose();
                return;
            }
            if (cm.getNX(1) >= 200) {
                cm.gainNX(-200);
                cm.gainItem(4031179, 1);
                cm.sendOk("购买成功!");
                cm.喇叭(2,"恭喜玩家[" + cm.getPlayer().getName() + "]消费了200点券购买了闹钟BOSS的挑战物品。");
                cm.dispose();
            } else {
                cm.sendOk("您的点券不足200.无法购买");
                cm.dispose();
            }
        } else if (selection == 22) {
            if (!cm.canHold(4000175, 1)) {
                cm.sendOk("背包已满!");
                cm.dispose();
                return;
            }
            if (cm.getNX(1) >= 300) {
                cm.gainNX(-300);
                cm.gainItem(4000175, 1);
                cm.sendOk("购买成功!");
                cm.喇叭(2,"恭喜玩家[" + cm.getPlayer().getName() + "]消费了300点券购买了鱼王BOSS的挑战物品。");
                cm.dispose();
            } else {
                cm.sendOk("您的点券不足300.无法购买");
                cm.dispose();
            }
        } else if (selection == 18) {
            //5220006 - 黑龙入场券 - 凭此票可进入黑龙洞探险.每次进入消耗1张.
            if (!cm.canHold(5220006, 1)) {
                cm.sendOk("背包已满!");
                cm.dispose();
                return;
            }
            if (cm.getNX(1) >= 1000) {
                cm.gainNX(-1000);
                cm.gainItem(5220006, 1);
                cm.sendOk("购买成功!");
                cm.喇叭(2,"恭喜玩家[" + cm.getPlayer().getName() + "]消费了1000点券购买了暗黑黑龙BOSS的挑战物品。");
                cm.dispose();
            } else {
                cm.sendOk("您的点券不足1000.无法购买");
                cm.dispose();
            }
        } else if (selection == 19) {
            cm.sendOk("品克缤无需挑战物品，直接消费1000点券，即可挑战一次。");
            cm.dispose();

            }
          else if (selection == 24) {
            cm.sendOk("贝伦无需挑战物品，直接消费1000点券，即可挑战一次。");
            cm.dispose();
          }else if (selection == 50) {
            //270050100
            if (cm.getPlayer().getLevel() >= 200) {
                if (cm.getBossLog("赤虎已挑战次数") < 3) {
                    if (cm.getPlayer().getMeso() >30000000) {
                        if (cm.getPlayerCount(910000020) == 0) {
                            var FantMap = cm.getMap(910000020);
                            FantMap.resetFully();
                            //cm.gainNX(-2000);
                            cm.gainMeso(-30000000);
							cm.刷新地图();
                            cm.warpParty(910000020);
							//cm.刷新地图();
							cm.spawnMobStats(9601014,2100000000,85019000);
                            cm.getPlayer().setBossLog('赤虎已挑战次数');
                            cm.喇叭(2, "玩家[" + cm.getPlayer().getName() + "]开始挑战赤虎。让我们期待他的表现。")
                            cm.dispose();
                        } else {
                            if (cm.getMap(910000020).getSpeedRunStart() == 0 && (cm.getMonsterCount(910000020) <= 0 || cm.getMap(910000020).isDisconnected(cm.getPlayer().getId()))) {
								//if (cm.getPlayerCount(910000020) > 0){
                                //cm.gainNX(-1000);
                                cm.warpParty(910000020);
                                cm.getPlayer().setBossLog('赤虎已挑战次数');
                                cm.喇叭(2, "玩家[" + cm.getPlayer().getName() + "]开始挑战赤虎。让我们期待他的表现。")
                                cm.dispose();
                            } else {
                                cm.sendOk("里面的战斗已经开始了，请等待。");
                                cm.dispose();
                            }
                        }
                    } else {
                        cm.sendOk("金币不足6000W.无法挑战。");
                        cm.dispose();
                    }
                } else {
                    cm.sendOk("你今日的挑战次数已经用完。");
                    cm.dispose();
                }
            } else {
                cm.sendOk("等级不足200级，无法进入。");
                cm.dispose();
            }
          }else if (selection == 51) {
            if (cm.getNX(1) >= 10000) {
                cm.gainNX(-10000);
                cm.getPlayer().resetBossLog('赤虎已挑战次数');
                cm.sendOk("重置挑战次数成功！！");
                cm.喇叭(2, "恭喜玩家[" + cm.getPlayer().getName() + "]消费10000点券重置了 赤虎的今日挑战次数。")
                cm.dispose();
            } else {
                cm.sendOk("点券不足10000无法重置挑战次数");
                cm.dispose();
            }
          }else if (selection == 52) {
            cm.sendOk("赤虎无需挑战物品，直接消费5000W金币，即可挑战一次。");
            cm.dispose();

          }else if (selection == 53) {
            //270050100
            if (cm.getPlayer().getLevel() >= 200) {
                if (cm.getBossLog("麦格那厮已挑战次数") < 3) {
                    if (cm.getPlayer().getMeso() >30000000) {
                        if (cm.getPlayerCount(910000019) == 0) {
                            var FantMap = cm.getMap(910000019);
                            FantMap.resetFully();
                            //cm.gainNX(-2000);
                            cm.gainMeso(-30000000);
							cm.刷新地图();
                            cm.warpParty(910000019);
							cm.spawnMobStats(8880000,2100000000,85019000);
                            cm.getPlayer().setBossLog('麦格那厮已挑战次数');
                            cm.喇叭(2, "玩家[" + cm.getPlayer().getName() + "]开始挑战麦格那厮。让我们期待他的表现。")
                            cm.dispose();
                        } else {
                            if (cm.getMap(910000019).getSpeedRunStart() == 0 && (cm.getMonsterCount(910000019) <= 0 || cm.getMap(910000019).isDisconnected(cm.getPlayer().getId()))) {
								//if (cm.getPlayerCount(910000019) > 0){
                                //cm.gainNX(-1000);
                                cm.warpParty(910000019);
                                cm.getPlayer().setBossLog('麦格那厮已挑战次数');
                                cm.喇叭(2, "玩家[" + cm.getPlayer().getName() + "]开始挑战麦格那厮。让我们期待他的表现。")
                                cm.dispose();
                            } else {
                                cm.sendOk("里面的战斗已经开始了，请等待。");
                                cm.dispose();
                            }
                        }
                    } else {
                        cm.sendOk("金币不足3000W.无法挑战。");
                        cm.dispose();
                    }
                } else {
                    cm.sendOk("你今日的挑战次数已经用完。");
                    cm.dispose();
                }
            } else {
                cm.sendOk("等级不足200级，无法进入。");
                cm.dispose();
            }
          }else if (selection == 54) {
            if (cm.getNX(1) >= 10000) {
                cm.gainNX(-10000);
                cm.getPlayer().resetBossLog('麦格那厮已挑战次数');
                cm.sendOk("重置挑战次数成功！！");
                cm.喇叭(2, "恭喜玩家[" + cm.getPlayer().getName() + "]消费10000点券重置了 麦格那厮的今日挑战次数。")
                cm.dispose();
            } else {
                cm.sendOk("点券不足10000无法重置挑战次数");
                cm.dispose();
            }
          }else if (selection == 55) {
            cm.sendOk("麦格那厮无需挑战物品，直接消费1000W金币，即可挑战一次。");
            cm.dispose();

          }else if (selection == 56) {
            //270050100
            if (cm.getPlayer().getLevel() >= 200) {
                if (cm.getBossLog("邪恶兜蛇兄弟已挑战次数") < 3) {
                    if (cm.getPlayer().getMeso() >30000000) {
                        if (cm.getPlayerCount(910000018) == 0) {
                            var FantMap = cm.getMap(910000018);
                            FantMap.resetFully();
                            //cm.gainNX(-2000);
                            cm.gainMeso(-30000000);
							cm.刷新地图();
                            cm.warpParty(910000018);
							//cm.刷新地图();
							cm.spawnMobStats(9601015,2100000000,91019000);
                            cm.getPlayer().setBossLog('邪恶兜蛇兄弟已挑战次数');
                            cm.喇叭(2, "玩家[" + cm.getPlayer().getName() + "]开始挑战邪恶兜蛇兄弟。让我们期待他的表现。")
                            cm.dispose();
                        } else {
                            if (cm.getMap(910000018).getSpeedRunStart() == 0 && (cm.getMonsterCount(910000018) <= 0 || cm.getMap(910000018).isDisconnected(cm.getPlayer().getId()))) {
								//if (cm.getPlayerCount(910000018) > 0){
                                //cm.gainNX(-1000);
                                cm.warpParty(910000018);
                                cm.getPlayer().setBossLog('邪恶兜蛇兄弟已挑战次数');
                                cm.喇叭(2, "玩家[" + cm.getPlayer().getName() + "]开始挑战邪恶兜蛇兄弟。让我们期待他的表现。")
                                cm.dispose();
                            } else {
                                cm.sendOk("里面的战斗已经开始了，请等待。");
                                cm.dispose();
                            }
                        }
                    } else {
                        cm.sendOk("金币不足3000W.无法挑战。");
                        cm.dispose();
                    }
                } else {
                    cm.sendOk("你今日的挑战次数已经用完。");
                    cm.dispose();
                }
            } else {
                cm.sendOk("等级不足200级，无法进入。");
                cm.dispose();
            }
          }else if (selection == 57) {
            if (cm.getNX(1) >= 10000) {
                cm.gainNX(-10000);
                cm.getPlayer().resetBossLog('邪恶兜蛇兄弟已挑战次数');
                cm.sendOk("重置挑战次数成功！！");
                cm.喇叭(2, "恭喜玩家[" + cm.getPlayer().getName() + "]消费10000点券重置了 邪恶兜蛇兄弟的今日挑战次数。")
                cm.dispose();
            } else {
                cm.sendOk("点券不足10000无法重置挑战次数");
                cm.dispose();
            }
          }else if (selection == 58) {
            cm.sendOk("邪恶兜蛇兄弟无需挑战物品，直接消费3000W金币，即可挑战一次。");
            cm.dispose();

          }else if (selection == 59) {
            //270050100
            if (cm.getPlayer().getLevel() >= 200) {
                if (cm.getBossLog("拌拌已挑战次数") < 3) {
                    if (cm.getPlayer().getMeso() >30000000) {
                        if (cm.getPlayerCount(910000017) == 0) {
                            var FantMap = cm.getMap(910000017);
                            FantMap.resetFully();
                            //cm.gainNX(-2000);
                            cm.gainMeso(-30000000);
							cm.刷新地图();
                            cm.warpParty(910000017);
							//cm.刷新地图();
							cm.spawnMobStats(9700004,2100000000,85019000);
                            cm.getPlayer().setBossLog('拌拌已挑战次数');
                            cm.喇叭(2, "玩家[" + cm.getPlayer().getName() + "]开始挑战拌拌。让我们期待他的表现。")
                            cm.dispose();
                        } else {
                            if (cm.getMap(910000017).getSpeedRunStart() == 0 && (cm.getMonsterCount(910000017) <= 0 || cm.getMap(910000017).isDisconnected(cm.getPlayer().getId()))) {
								//if (cm.getPlayerCount(910000017) > 0){
                                //cm.gainNX(-1000);
                                cm.warpParty(910000017);
                                cm.getPlayer().setBossLog('拌拌已挑战次数');
                                cm.喇叭(2, "玩家[" + cm.getPlayer().getName() + "]开始挑战拌拌。让我们期待他的表现。")
                                cm.dispose();
                            } else {
                                cm.sendOk("里面的战斗已经开始了，请等待。");
                                cm.dispose();
                            }
                        }
                    } else {
                        cm.sendOk("金币不足3000W.无法挑战。");
                        cm.dispose();
                    }
                } else {
                    cm.sendOk("你今日的挑战次数已经用完。");
                    cm.dispose();
                }
            } else {
                cm.sendOk("等级不足200级，无法进入。");
                cm.dispose();
            }
          }else if (selection == 60) {
            if (cm.getNX(1) >= 10000) {
                cm.gainNX(-10000);
                cm.getPlayer().resetBossLog('拌拌已挑战次数');
                cm.sendOk("重置挑战次数成功！！");
                cm.喇叭(2, "恭喜玩家[" + cm.getPlayer().getName() + "]消费10000点券重置了 拌拌的今日挑战次数。")
                cm.dispose();
            } else {
                cm.sendOk("点券不足10000无法重置挑战次数");
                cm.dispose();
            }
          }else if (selection == 61) {
            cm.sendOk("拌拌无需挑战物品，直接消费3000W金币，即可挑战一次。");
            cm.dispose();

            }
			
    }
}

			

