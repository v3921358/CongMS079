/*
 脚本功能：市场管理员
 */

var a = 0;
var icon = "#fUI/Basic.img/BtMin2/normal/0#";
var iconEvent = "#fUI/UIToolTip.img/Item/Equip/Star/Star#";
var ttt4 ="#fUI/UIWindow/Quest/icon5/1#";//"+ttt4+"//美化New
var icon2 = "#fEffect/CharacterEff/1082565/2/0#";

var List = Array(
		Array(iconEvent + " #r红包福利", 1, 1, 9310058),
		Array(iconEvent + " #r魔方福利", 2, 1, 9310382),
		Array(iconEvent + " #r测试基金", 3, 1, 9310382)
		//Array(iconEvent + " #b飞升洗髓", 1, 1, 9000174),
		//Array(iconEvent + " #b武器破功", 1000, 1),
		//Array(iconEvent + " #b蜡笔潜能", 1001, 1),
		//Array(iconEvent + " #r实惠礼包#k", 5, 1, 9000069),
		//Array(iconEvent + " #b绝版点装#k", 1, 1, 9000069),
		//Array(iconEvent + " #b稀有椅子#k", 2, 1, 9000069),
		//Array(iconEvent + " #b上乘装备#k", 4, 1, 9000069),
		//Array(iconEvent + " #b极品卷轴#k", 3, 1, 9000069),
		//Array(iconEvent + " #b品级副装#k", 999, 1, 9310144),
		//Array(iconEvent + " #b皮肤伤害#k", 12, 1, 9900004),
		//Array(iconEvent + " #b职业手册#k", 10, 1, 9900004),
		//Array(iconEvent + " #b职业副手#k", 11, 1, 9900004)
		//Array(iconEvent + " #b翅膀进阶", 500, 1, 9900004)
		//Array(iconEvent + " #r暖男女神#k", 777, 1, 9310144)
)
var text;
//是否活动，名字，模式，类别

function start() {
    a = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (mode == 1)
            a++;
        else
            a--;
        if (a == -1) {
            cm.dispose();
        } else if (a == 0) {
            text = "";
            for (var i = 0; i < 2; i++) {
                ListFor(i);
            }
			text += "\r\n\r\n\r\n#e#g\t\t\t  "+icon2+" 祝您游戏愉快 "+ icon2 +"#n#k\r\n";
            cm.sendSimple(text)
        } else if (a == 1) {
            var mode_ = List[selection][1];
            cm.dispose();
			var npcid = 9310058;
			if (List[selection][3] != null)
				npcid = List[selection][3];
            cm.openNpc(npcid, mode_);
        }//a
    }//mode
}//f


function ListFor(type) {
    switch (type) {
        case 1://便民服务
            text += "#e#g├────────#k#r 福利专区 #k#g────────┤#n#k\r\n\r\n#b管理提示：懒人请无视，此福利越勤奋获得福利越多\r\n\r\n不要说没有福利，就看你是不是勤快了\r\n\r\n";
            break;
    }
    var x = 0;
    for (var i = 0; i < List.length; i++) {
        if (List[i][2] == type) {
            if (x == 2) {
                text += "  #L" + i + "#" + List[i][0] + "#l\r\n";
                x = 0;
            } else {
                text += "  #L" + i + "#" + List[i][0] + "#l";
                x++;
            }
        }
    }
    text += "#e";
}