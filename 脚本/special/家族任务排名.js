var status = -1;
var ca = java.util.Calendar.getInstance();
var weekday = ca.get(java.util.Calendar.DAY_OF_WEEK);
var pm = -1;
var gifts = [
    ["100", 100, 3 * 1000 * 10000],
    ["60 ", 60, 2 * 1000 * 10000],
    ["30 ", 30,  1000 * 10000]
];

var eff1 = "#fUI/GuildMark/Mark/Etc/00009023/11#";
var eff2 = "#fUI/GuildMark/Mark/Etc/00009023/1#";
var eff3 = "#fUI/GuildMark/Mark/Etc/00009023/7#";
var eff = "#fUI/GuildMark/Mark/Etc/00009004/4#";

var effs = [
	"#fUI/GuildMark/Mark/Letter/00005030/1#",
	"#fUI/GuildMark/Mark/Letter/00005031/10#",
	"#fUI/GuildMark/Mark/Letter/00005032/12#",
	"#fUI/GuildMark/Mark/Letter/00005033/9#",
	"#fUI/GuildMark/Mark/Letter/00005034/15#",
	"#fUI/GuildMark/Mark/Letter/00005035/7#"
];

function start() {
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 0) {
        cm.dispose();
        return;
    }
    status++;
    if (status == 0) {
        var text = "#d┏━━━━━━━━家族任务一周排名━━━━━━━━┓#k\r\n\r\n";
        text += "#L0#本周排行#l\t\t";
        text += "#L1#上周排行#l\t\t";
        text += "#L3#奖励说明#l\r\n\r\n\r\n";
		text += "#d每一星期刷新一次  排在前三名的家族  只有族长才可领奖#k\r\n";
		text += "#d┗━━━━━━━━━━━━━━━━━━━━━━━━┛#k";
		//cm.gainGP(45);
        cm.sendSimple(text);
    } else if (status == 1) {
        if (selection == 3) {
            var txt = "#d┏━━━━━━━━家族任务排名奖励━━━━━━━━┓#k\r\n\r\n"
			if (weekday == 1) {
				weekday = "天"
			} else{
				weekday -= 1;
			}
			txt += "\t\t\t\t\t今天是：#g星期"+weekday+"\r\n"
            for (var i in gifts) {
                txt += "\t#d- 第 " + (parseInt(i) + 1) + " 名    *<#r奖励#d>元宝x" + gifts[i][0] + "  金币" + gifts[i][2] + "\r\n";
            }
            txt += "#d┗━━━━━━━━━━━━━━━━━━━━━━━━┛#k";
            cm.sendOk(txt);
            cm.dispose();
        } else if (selection == 1 || selection == 0) {
            var sql = "select g.guildid, g.name, l.rvalue from guilds g, ranklist l where g.guildid = l.rid and l.rtype = 2 and WEEKOFYEAR(rtime) = WEEKOFYEAR(NOW()) - ? order by l.rvalue desc limit 9";
            var list = cm.sql_Select(sql, selection);
            var text = "\t\t\t\t#e#d"+eff+" - 家族任务排行 - "+eff+"#k#n\r\n\r\n";
            var i = 1;
            for (var ii in list) {
                if (cm.getPlayer().getGuildId() == parseInt(list[ii].get("guildid"))) {
                    pm = i;
                }
				if (i == 1) {
					text += eff1;
				} else if (i == 2) {
					text += eff2;
				} else if (i == 3) {
					text += eff3;
				} else {
					text += effs[i-4]
				}
                text += "#r ";
                text += "第 " + i + " 名";
				for (var j = 13 - ("第 " + i + " 名").toString().getBytes().length; j > 0; j--) {
                    text += " ";
                }
                text += "#k"+list[ii].get("name");
                for (var j = 16 - list[ii].get("name").toString().getBytes().length; j > 0; j--) {
                    text += " ";
                }
                text += "\t#b " + list[ii].get("rvalue")+" 分";
                for (var j = 16 - (list[ii].get("rvalue")+" 分").toString().getBytes().length; j > 0; j--) {
                    text += " ";
                }
                text += "\t #k";
                text += "\r\n";
				if (i == 3) {
					text += "------------------------------------------------------\r\n"
				}
                i++;
            }
            if (pm > 0 && pm < 4 && selection == 1) {
                text += "\r\n#d#e#L99#你的家族上周排名第" + pm + "，#r领取上周奖励#l";
            } else {
                cm.dispose();
            }
            cm.sendOk(text);
        }
    } else if (status == 2) {
        if (selection == 99) {
			if (cm.getPlayer().getGuild().getLeaderId() != cm.getPlayer().getId()) {
				cm.sendOk("只有家族长才可以领取家族积分排行奖励");
			} else if (getlog(cm.getPlayer().getGuildId()) > 0) {
                cm.sendOk("你的家族本周已经领取过此奖励了");
            } else if (pm > 0 && pm < 4) {
                //给金币
                cm.gainMeso(gifts[pm-1][2]);
                //给元宝
				cm.setmoneyb(gifts[pm-1][1]);
                cm.sendOk("恭喜你领取成功");
				Packages.server.custom.bossrank.BossRankManager.getInstance().addRanklistGP(cm.getPlayer().getGuildId(), 4, 1);
            }
        }
        cm.dispose();
    }
}

function getlog(guildid) {
    var sql = cm.sql_Select("select * from ranklist where rid = ? and rtype = ? and WEEKOFYEAR(rtime) = WEEKOFYEAR(NOW())", guildid, 4);
    if (sql.length > 0) {
        return 1;
    } else {
        return 0;
    }
}

