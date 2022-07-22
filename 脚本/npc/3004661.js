/*
适合QILIN服务端
QQ 1500663066
*/
var 躺熊 = "#fItem/Cash/0502/05021001/info/iconRaw#";  //
var 正在进行中 = "#fUI/UIWindow/Quest/Tab/enabled/1#";
var 完成 = "#fUI/UIWindow/Quest/Tab/enabled/2#";
var 正在进行中蓝 = "#fUI/UIWindow/MonsterCarnival/icon1#";
var 完成红 = "#fUI/UIWindow/MonsterCarnival/icon0#";
var 爱心 = "#fEffect/CharacterEff/1022223/4/0#";
var 红色箭头 = "#fUI/UIWindow/Quest/icon6/7#"
var 正方形 = "#fUI/UIWindow/Quest/icon3/6#";
var 蓝色箭头 = "#fUI/UIWindow/Quest/icon2/7#";
var 蓝色角点 = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var 正在进行中 = "#fUI/UIWindow/Quest/Tab/enabled/1#";
var 完成 = "#fUI/UIWindow/Quest/Tab/enabled/2#";
var 正在进行中蓝 = "#fUI/UIWindow/MonsterCarnival/icon1#";
var 完成红 = "#fUI/UIWindow/MonsterCarnival/icon0#";
var 大心 = "#fEffect/CharacterEff/1051295/0/0#";
var 琴符 = "#fEffect/CharacterEff/1003252/0/0#";
var 小雪花 = "#fEffect/CharacterEff/1003393/0/0#";
var 音符 = "#fEffect/CharacterEff/1032063/0/0#";
var 感叹号 = "#fUI/UIWindow/Quest/icon0#";
var 风之大陆 = "#fUI/Login/WorldSelect/BtGateway/0#";
var 移动到任务NPC处 = "#fUI/UIWindow/Quest/BtGotoNpc/normal/0#";
var 任务简述 = "#fUI/UIWindow/Quest/summary#";
var 奖励 = "#fUI/UIWindow/Quest/reward#";
var 表情高兴 = "#fUI/GuildBBS/GuildBBS/Emoticon/Basic/2#";
var 小金币 = "#fUI/UIWindow.img/Item/BtCoin/normal/0#";
var 点券图标 = "#fUI/CashShop/CashItem/0#";
var 经验值 = "#fUI/UIWindow/QuestIcon/8/0#";
var 点券 = "#fUI/CashShop/CashItem/0#";
var 箭头 = "#fUI/Basic/BtHide3/mouseOver/0#";
function start() {
    status = -1;

    action(1, 0, 0);
}
function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    }
    else {
        if (status >= 0 && mode == 0) {

            cm.sendOk("感谢你的光临！");
            cm.dispose();
            return;
        }
        if (mode == 1) {
            status++;
        }
        else {
            status--;
        }
        if (status == 0) {
            var tex2 = "";
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }
			text += "\t\t"+表情高兴+" #e 开 心 剧 情 主 线 任 务 #n "+表情高兴+"\r\n\r\n"
			
			if(cm.getPlayer().getOneTimeLog("主线任务") == 0){
					text += "\t"+正在进行中+"#L1#剧情主线任务第一关(#r可开始#k)#n#l\r\n\r\n"//3
					//text += "\t#L27#"+箭头+"快速通道(#z4000002#+100#z4000017#+100)需要200点券#n#l\r\n\r\n"
				} else if(cm.getPlayer().getOneTimeLog("主线任务") > 0){
					text += "\t\t\t\t   剧情主线任务第一关#n#l"+完成+"#k\r\n"//3
			}
			
			if(cm.getPlayer().getOneTimeLog("主线任务") == 1 && cm.getLevel() > 9){
					text += "\t"+正在进行中+"#L2#剧情主线任务第二关(#r可开始#k)#n#l\r\n\r\n"//3
					//text += "\t#L28#"+箭头+"快速通道(#z4000015#+100#z4000008#+100)需要300点券#n#l\r\n\r\n"
				} else if(cm.getPlayer().getOneTimeLog("主线任务") > 1 && cm.getLevel() > 9){
					text += "\t\t\t\t   剧情主线任务第二关#n#l"+完成+"#k\r\n"//3
				} else {
					text += "\t\t\t(#rlv.10#k)剧情主线任务第二关#l\r\n"//3
			}
			
			if(cm.getPlayer().getOneTimeLog("主线任务") == 2 && cm.getLevel() > 19){
					text += "\t"+正在进行中+"#L3#剧情主线任务第三关(#r可开始#k)#n#l\r\n\r\n"//3
					//text += "\t#L29#"+箭头+"快速通道(#z4170000#+10)需要400点券#n#l\r\n\r\n"
				} else if(cm.getPlayer().getOneTimeLog("主线任务") > 2 && cm.getLevel() > 19){
					text += "\t\t\t\t   剧情主线任务第三关#n#l"+完成+"#k\r\n"//3
				} else {
					text += "\t\t\t(#rlv.20#k)剧情主线任务第三关#l\r\n"//3
			}
			
			if(cm.getPlayer().getOneTimeLog("主线任务") == 3 && cm.getLevel() > 29){
					text += "\t"+正在进行中+"#L4#剧情主线任务第四关(#r可开始#k)#n#l\r\n\r\n"//3
					//text += "\t#L30#"+箭头+"快速通道(#z4000106#+150#z4000107#+150)需要500点券#n#l\r\n\r\n"
				} else if(cm.getPlayer().getOneTimeLog("主线任务") > 3 && cm.getLevel() > 29){
					text += "\t\t\t\t   剧情主线任务第四关#n#l"+完成+"#k\r\n"//3
				} else {
					text += "\t\t\t(#rlv.30#k)剧情主线任务第四关#l\r\n"//3
			}
			
			if(cm.getPlayer().getOneTimeLog("主线任务") == 4 && cm.getLevel() > 39){
					text += "\t"+正在进行中+"#L5#剧情主线任务第五关(#r可开始#k)#n#l\r\n\r\n"//3
					//text += "\t#L31#"+箭头+"快速通道(#z4000170#+150#z4000169#+150)需要600点券#n#l\r\n\r\n"
				} else if(cm.getPlayer().getOneTimeLog("主线任务") > 4 && cm.getLevel() > 39){
					text += "\t\t\t\t   剧情主线任务第五关#n#l"+完成+"#k\r\n"//3
				} else {
					text += "\t\t\t(#rlv.40#k)剧情主线任务第五关#l\r\n"//3
			}
			
			if(cm.getPlayer().getOneTimeLog("主线任务") == 5 && cm.getLevel() > 49){
					text += "\t"+正在进行中+"#L6#剧情主线任务第六关(#r可开始#k)#n#l\r\n\r\n"//3
					//text += "\t#L32#"+箭头+"快速通道(#z4000276#+200#z4000277#+200)需要700点券#n#l\r\n\r\n"
				} else if(cm.getPlayer().getOneTimeLog("主线任务") > 5 && cm.getLevel() > 49){
					text += "\t\t\t\t   剧情主线任务第六关#n#l"+完成+"#k\r\n"//3
				} else {
					text += "\t\t\t(#rlv.50#k)剧情主线任务第六关#l\r\n"//3
			}
			
			
			if(cm.getPlayer().getOneTimeLog("主线任务") == 6 && cm.getLevel() > 59){
					text += "\t"+正在进行中+"#L7#剧情主线任务第七关(#r可开始#k)#n#l\r\n\r\n"//3
					//text += "\t#L33#"+箭头+"快速通道(#z4000115#+400)需要800点券#n#l\r\n\r\n"
				} else if(cm.getPlayer().getOneTimeLog("主线任务") > 6 && cm.getLevel() > 59){
					text += "\t\t\t\t   剧情主线任务第七关#n#l"+完成+"#k\r\n"//3
				} else {
					text += "\t\t\t(#rlv.60#k)剧情主线任务第七关#l\r\n"//3
			}
			if(cm.getPlayer().getOneTimeLog("主线任务") == 7 && cm.getLevel() > 69){
					text += "\t"+正在进行中+"#L8#剧情主线任务第八关(#r可开始#k)#n#l\r\n\r\n"//3
					//text += "\t#L34#"+箭头+"快速通道(#z4000088#+200#z4000086#+200)需要900点券#n#l\r\n\r\n"
				} else if(cm.getPlayer().getOneTimeLog("主线任务") > 7 && cm.getLevel() > 69){
					text += "\t\t\t\t   剧情主线任务第八关#n#l"+完成+"#k\r\n"//3
				} else {
					text += "\t\t\t(#rlv.70#k)剧情主线任务第八关#l\r\n"//3
			}
			
			
			if(cm.getPlayer().getOneTimeLog("主线任务") == 8 && cm.getLevel() > 79){
					text += "\t"+正在进行中+"#L9#剧情主线任务第九关(#r可开始#k)#n#l\r\n\r\n"//3
					//text += "\t#L35#"+箭头+"快速通道(#z4000177#+300#z4000025#+300)需要1000点券#n#l\r\n\r\n"
				} else if(cm.getPlayer().getOneTimeLog("主线任务") > 8 && cm.getLevel() > 79){
					text += "\t\t\t\t   剧情主线任务第九关#n#l"+完成+"#k\r\n"//3
				} else {
					text += "\t\t\t(#rlv.80#k)剧情主线任务第九关#l\r\n"//3
			}
			
			if(cm.getPlayer().getOneTimeLog("主线任务") == 9 && cm.getLevel() > 89){
					text += "\t"+正在进行中+"#L10#剧情主线任务第十关(#r可开始#k)#n#l\r\n\r\n"//3
					//text += "\t#L36#"+箭头+"快速通道(#z4000289#+300#z4000299#+300)需要1100点券#n#l\r\n\r\n"
				} else if(cm.getPlayer().getOneTimeLog("主线任务") > 9 && cm.getLevel() > 89){
					text += "\t\t\t\t   剧情主线任务第十关#n#l"+完成+"#k\r\n"//3
				} else {
					text += "\t\t\t(#rlv.90#k)剧情主线任务第十关#l\r\n"//3
			}
			
			if(cm.getPlayer().getOneTimeLog("主线任务") == 10 && cm.getLevel() > 99){
					text += "\t"+正在进行中+"#L11#剧情主线任务第十一关(#r可开始#k)#n#l\r\n\r\n"
					//text += "\t#L37#"+箭头+"快速通道(#z4000032#+300#z4000034#+300)需要1200点券#n#l\r\n\r\n"
				} else if(cm.getPlayer().getOneTimeLog("主线任务") > 10 && cm.getLevel() > 99){
					text += "\t\t\t\t   剧情主线任务第十一关#n#l"+完成+"#k\r\n"//3
				} else {
					text += "\t\t\t(#rlv.100#k)剧情主线任务第十一关#l\r\n"//3
			}
			
			if(cm.getPlayer().getOneTimeLog("主线任务") == 11 && cm.getLevel() > 109){
					text += "\t"+正在进行中+"#L12#剧情主线任务第十二关(#r可开始#k)#n#l\r\n\r\n"
					//text += "\t#L38#"+箭头+"快速通道(#z4000040#+16#z4000176#+16)需要1300点券#n#l\r\n\r\n"
				} else if(cm.getPlayer().getOneTimeLog("主线任务") > 11 && cm.getLevel() > 109){
					text += "\t\t\t\t   剧情主线任务第十二关#n#l"+完成+"#k\r\n"//3
				} else {
					text += "\t\t\t(#rlv.110#k)剧情主线任务第十二关#l\r\n"//3
			}
			
			if(cm.getPlayer().getOneTimeLog("主线任务") == 12 && cm.getLevel() > 119){
					text += "\t"+正在进行中+"#L13#剧情主线任务第十三关(#r可开始#k)#n#l\r\n\r\n"//3
					//text += "\t#L39#"+箭头+"快速通道(#z4000020#+300#z4000178#+300)需要1400点券#n#l\r\n\r\n"
				} else if(cm.getPlayer().getOneTimeLog("主线任务") > 12 && cm.getLevel() > 119){
					text += "\t\t\t\t   剧情主线任务第十三关#n#l"+完成+"#k\r\n"//3
				} else {
					text += "\t\t\t(#rlv.120#k)剧情主线任务第十三关#l\r\n"//3
			}

			if(cm.getPlayer().getOneTimeLog("主线任务") == 13 && cm.getLevel() > 129){
				text += "\t"+正在进行中+"#L14#剧情主线任务第十四关(#r可开始#k)#n#l\r\n\r\n"//3
				//text += "\t#L40#"+箭头+"快速通道(#z4000023#+300#z4000030#+300)需要1500点券#n#l\r\n\r\n"
			} else if(cm.getPlayer().getOneTimeLog("主线任务") > 13 && cm.getLevel() > 129){
				text += "\t\t\t\t   剧情主线任务第十四关#n#l"+完成+"#k\r\n"//3
			} else {
				text += "\t\t\t(#rlv.130#k)剧情主线任务第十四关#l\r\n"//3
			}

			if(cm.getPlayer().getOneTimeLog("主线任务") == 14 && cm.getLevel() > 139){
				text += "\t"+正在进行中+"#L15#剧情主线任务第十五关(#r可开始#k)#n#l\r\n\r\n"//3
				//text += "\t#L41#"+箭头+"快速通道(#z4000206#+350#z4000207#+350)需要1600点券#n#l\r\n\r\n"
			} else if(cm.getPlayer().getOneTimeLog("主线任务") > 14 && cm.getLevel() > 139){
				text += "\t\t\t\t   剧情主线任务第十五关#n#l"+完成+"#k\r\n"//3
			} else {
				text += "\t\t\t(#rlv.140#k)剧情主线任务第十五关#l\r\n"//3
			}

			if(cm.getPlayer().getOneTimeLog("主线任务") == 15 && cm.getLevel() > 149){
				text += "\t"+正在进行中+"#L16#剧情主线任务第十六关(#r可开始#k)#n#l\r\n\r\n"//3
				//text += "\t#L42#"+箭头+"快速通道(#z4000029#+400#z4000031#+400)需要1700点券#n#l\r\n\r\n"
			} else if(cm.getPlayer().getOneTimeLog("主线任务") > 15 && cm.getLevel() > 149){
				text += "\t\t\t\t   剧情主线任务第十六关#n#l"+完成+"#k\r\n"//3
			} else {
				text += "\t\t\t(#rlv.150#k)剧情主线任务第十六关#l\r\n"//3
			}

			if(cm.getPlayer().getOneTimeLog("主线任务") == 16 && cm.getLevel() > 159){
				text += "\t"+正在进行中+"#L17#剧情主线任务第十七关(#r可开始#k)#n#l\r\n\r\n"//3
				//text += "\t#L43#"+箭头+"快速通道(#z4000070#+220#z4000071#+220#z4000072#+220)需要1800点券#n#l\r\n\r\n"
			} else if(cm.getPlayer().getOneTimeLog("主线任务") > 16 && cm.getLevel() > 159){
				text += "\t\t\t\t   剧情主线任务第十七关#n#l"+完成+"#k\r\n"//3
			} else {
				text += "\t\t\t(#rlv.160#k)剧情主线任务第十七关#l\r\n"//3
			}

			if(cm.getPlayer().getOneTimeLog("主线任务") == 17 && cm.getLevel() > 169){
				text += "\t"+正在进行中+"#L18#剧情主线任务第十八关(#r可开始#k)#n#l\r\n\r\n"//3
				//text += "\t#L44#"+箭头+"快速通道(#z4000060#+450#z4000061#+450)需要1900点券#n#l\r\n\r\n"
			} else if(cm.getPlayer().getOneTimeLog("主线任务") > 17 && cm.getLevel() > 169){
				text += "\t\t\t\t   剧情主线任务第十八关#n#l"+完成+"#k\r\n"//3
			} else {
				text += "\t\t\t(#rlv.170#k)剧情主线任务第十八关#l\r\n"//3
			}

			if(cm.getPlayer().getOneTimeLog("主线任务") == 18 && cm.getLevel() > 179){
				text += "\t"+正在进行中+"#L19#剧情主线任务第十九关(#r可开始#k)#n#l\r\n\r\n"//3
				//text += "\t#L45#"+箭头+"快速通道(#z4000051#+450#z4000052#+450)需要2000点券#n#l\r\n\r\n"
			} else if(cm.getPlayer().getOneTimeLog("主线任务") > 18 && cm.getLevel() > 179){
				text += "\t\t\t\t   剧情主线任务第十九关#n#l"+完成+"#k\r\n"//3
			} else {
				text += "\t\t\t(#rlv.180#k)剧情主线任务第十九关#l\r\n"//3
			}
			
			if(cm.getPlayer().getOneTimeLog("主线任务") == 19 && cm.getLevel() > 189){
				text += "\t"+正在进行中+"#L20#剧情主线任务第二十关(#r可开始#k)#n#l\r\n\r\n"//3
				//text += "\t#L46#"+箭头+"快速通道(#z4000049#+200#z4000050#+200)需要2100点券#n#l\r\n\r\n"
			} else if(cm.getPlayer().getOneTimeLog("主线任务") > 19 && cm.getLevel() > 189){
				text += "\t\t\t\t   剧情主线任务第二十关#n#l"+完成+"#k\r\n"//3
			} else {
				text += "\t\t\t(#rlv.190#k)剧情主线任务第二十关#l\r\n"//3
			}

			if(cm.getPlayer().getOneTimeLog("主线任务") == 20 && cm.getLevel() > 199){
				text += "\t"+正在进行中+"#L21#剧情主线任务第二十一关(#r可开始#k)#n#l\r\n\r\n"//3
				//text += "\t#L47#"+箭头+"快速通道(#z4000069#+450#z4000076#+450)需要2200点券#n#l\r\n\r\n"
			} else if(cm.getPlayer().getOneTimeLog("主线任务") > 20 && cm.getLevel() > 199){
				text += "\t\t\t\t   剧情主线任务第二十一关#n#l"+完成+"#k\r\n"//3
			} else {
				text += "\t\t\t(#rlv.200#k)剧情主线任务第二十一关#l\r\n"//3
			}

			if(cm.getPlayer().getOneTimeLog("主线任务") == 21 && cm.getLevel() > 209){
				text += "\t"+正在进行中+"#L22#剧情主线任务第二十二关(#r可开始#k)#n#l\r\n\r\n"//3
				//text += "\t#L48#"+箭头+"快速通道(#z4000284#+500#z4000285#+500)需要2300点券#n#l\r\n\r\n"
			} else if(cm.getPlayer().getOneTimeLog("主线任务") > 21 && cm.getLevel() > 209){
				text += "\t\t\t\t   剧情主线任务第二十二关#n#l"+完成+"#k\r\n"//3
			} else {
				text += "\t\t\t(#rlv.210#k)剧情主线任务第二十二关#l\r\n"//3
			}

			if(cm.getPlayer().getOneTimeLog("主线任务") == 22 && cm.getLevel() > 219){
				text += "\t"+正在进行中+"#L23#剧情主线任务第二十三关(#r可开始#k)#n#l\r\n\r\n"//3
				//text += "\t#L49#"+箭头+"快速通道(#z4000171#+600#z4000172#+600)需要2400点券#n#l\r\n\r\n"
			} else if(cm.getPlayer().getOneTimeLog("主线任务") > 22 && cm.getLevel() > 219){
				text += "\t\t\t\t   剧情主线任务第二十三关#n#l"+完成+"#k\r\n"//3
			} else {
				text += "\t\t\t(#rlv.220#k)剧情主线任务第二十三关#l\r\n"//3
			}

			if(cm.getPlayer().getOneTimeLog("主线任务") == 23 && cm.getLevel() > 229){
				text += "\t"+正在进行中+"#L24#剧情主线任务第二十四关(#r可开始#k)#n#l\r\n\r\n"//3
				//text += "\t#L50#"+箭头+"快速通道(#z4000160#+600#z4000161#+600)需要2500点券#n#l\r\n\r\n"
			} else if(cm.getPlayer().getOneTimeLog("主线任务") > 23 && cm.getLevel() > 229){
				text += "\t\t\t\t   剧情主线任务第二十四关#n#l"+完成+"#k\r\n"//3
			} else {
				text += "\t\t\t(#rlv.230#k)剧情主线任务第二十四关#l\r\n"//3
			}

			if(cm.getPlayer().getOneTimeLog("主线任务") == 24 && cm.getLevel() > 239){
				text += "\t"+正在进行中+"#L25#剧情主线任务第二十五关(#r可开始#k)#n#l\r\n\r\n"//3
				//text += "\t#L51#"+箭头+"快速通道(#z4000118#+700#z4000119#+700)需要2600点券#n#l\r\n\r\n"
			} else if(cm.getPlayer().getOneTimeLog("主线任务") > 24 && cm.getLevel() > 239){
				text += "\t\t\t\t   剧情主线任务第二十五关#n#l"+完成+"#k\r\n"//3
			} else {
				text += "\t\t\t(#rlv.240#k)剧情主线任务第二十五关#l\r\n"//3
			}

			if(cm.getPlayer().getOneTimeLog("主线任务") == 25 && cm.getLevel() > 249){
				text += "\t"+正在进行中+"#L26#剧情主线任务第二十六关(#r可开始#k)#n#l\r\n\r\n"//3
				//text += "\t#L52#"+箭头+"快速通道(全部道具+50)需要8888点券#n#l\r\n\r\n"
			} else if(cm.getPlayer().getOneTimeLog("主线任务") > 25 && cm.getLevel() > 249){
				text += "\t\t\t\t   剧情主线任务第二十六关#n#l"+完成+"#k\r\n"//3
				text += "#r\t\t\t\t   恭喜你完成所有剧情主线任务!#n#l#k\r\n"//3
				text += "#r\t\t\t\t\t\t\t剧情完结#n#l#k\r\n"//3
			} else {
				text += "\t\t\t(#rlv.250#k)剧情主线任务第二十六关#l\r\n"//3
			}
	
            cm.sendSimple(text);
			}else if (selection == 27) {
			if (cm.getPlayer().getCSPoints(1) > 200){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("背包空间不足2格");
			cm.dispose();
			return;
		}
				cm.gainNX(-200);//加减点券
                cm.gainItem(4000002,100);
				cm.gainItem(4000017,100);
                cm.dispose();
			}else{
                cm.sendOk("200点券不足，购买不到哦！");
                cm.dispose();
			}
        } else if (selection == 1) {
			if (cm.haveItem(4000002,100) && cm.haveItem(4000017,100)){
				if (!cm.checkNumSpace(0, 3)) {
			cm.sendOk("背包空间不足3格");
			cm.dispose();
			return;
		}
				cm.gainItem(4000002, -100);//物品
				cm.gainItem(4000017, -100);//物品
				cm.gainMeso(+40000); //加减金币
				cm.gainItem(4250200,5);//获得物品石榴石下等
				cm.gainItem(4250400,5);//获得物品紫水晶下等
				cm.gainItem(1142001,1);//获得物品组队狂人勋章
				cm.gainExp(5000);//经验值
				//cm.gainDY(400);//给个人抵用卷
				cm.worldMessage(6,"恭喜玩家:["+cm.getName()+"]完成了剧情主线任务第一关，获得奖励:经验值,金币,道具等奖品超级丰厚!");
				cm.getPlayer().setOneTimeLog("主线任务");
				cm.sendOk("恭喜你完成了剧情主线任务第一关,下面第二关奖励也是很丰厚哦！");
				cm.dispose();
		}else{

			cm.sendOk(""+任务简述+"\r\n还记得初次来到冒险岛的时候吗?我们从彩虹岛一点一滴的故事,来到明珠港以后,我们不断前行,掉落在一个叫#r(猪的海岸)#k的地方,请你到那里寻找那一片回忆!\r\n#e收集材料:#k#n\r\n#v4000002#[#c4000002#]/100个#v4000017#[#c4000017#]/100个\r\n"+奖励+"\r\n"+经验值+"5000  \r\n"+小金币+" 金币值*40000\r\n #v4250200#*5 #v4250400#*5 #v1142001#*1");
			cm.dispose();
			}
		}else if (selection == 28) {
			if (cm.getPlayer().getCSPoints(1) > 300){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("背包空间不足2格");
			cm.dispose();
			return;
		}
				cm.gainNX(-300);//加减点券
                cm.gainItem(4000015,100);
				cm.gainItem(4000008,100);
                cm.dispose();
			}else{
                cm.sendOk("300点券不足，购买不到哦！");
                cm.dispose();
			}
        } else if (selection == 2) {
		if (cm.haveItem(4000015,100) && cm.haveItem(4000008,100)){
			if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("背包空间不足5格");
			cm.dispose();
			return;
		}
			cm.gainItem(4000015, -100);//减少物品
			cm.gainItem(4000008, -100);//减少物品
			cm.gainMeso(+60000); //加减金币
			cm.gainItem(4250500,5);//获得物品海蓝宝石下等
			cm.gainItem(1112444,1);//获得物品六周年戒指
			cm.gainItem(4310150,5);//获得物品商业币
			cm.gainExp(cm.getLevel()*20000);//经验值
			cm.gainDY(300);//给个人抵用卷
			cm.getPlayer().setOneTimeLog("主线任务");
			cm.worldMessage(6,"玩家:["+cm.getName()+"]完成了剧情主线任务第二关,获得奖励:经验值,抵用卷,金币,道具等奖品超级丰厚!");
			cm.sendOk("恭喜你完成了剧情主线任务第二关，下面第三关奖励也是很丰厚哦！");
			cm.dispose();
		    }else{

			cm.sendOk(""+任务简述+"\r\n是在某个深处,让我们停留了无数次,无脑的敲打着键盘!\r\n请到一个叫#r（蚂蚁洞Ⅰ）#k的地方\r\n#e收集材料:#k#n\r\n#v4000015#[#c4000015#]100个#v4000008#[#c4000008#]100个\r\n"+奖励+"\r\n"+经验值+"*20000  (随着等级提升Exp)\r\n"+点券+" 抵用卷*300\r\n"+小金币+" 金币值*60000\r\n #v4250500#*5 #v4310150#*5 #v1112444#*1");
			cm.dispose();
	    }
		}else if (selection == 29) {
			if (cm.getPlayer().getCSPoints(1) > 400){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("背包空间不足2格");
			cm.dispose();
			return;
		}
				cm.gainNX(-400);//加减点券
                cm.gainItem(4170000,10);
                cm.dispose();
			}else{
                cm.sendOk("400点券不足，购买不到哦！");
                cm.dispose();
			}
        } else if (selection == 3) {
		if (cm.haveItem(4170000,10)){
			if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("背包空间不足5格");
			cm.dispose();
			return;
		}
			
			cm.gainItem(4170000,-10);
			cm.gainMeso(+80000); //加减金币
			cm.gainItem(4250700,5);//获得物品祖母绿下等
			cm.gainItem(4250300,5);//获得物品蛋白石下等
			cm.gainItem(1112742,1);//获得物品紫金枫叶戒指
			cm.gainItem(1050018,1);//蓝色桑拿服
			cm.gainExp(cm.getLevel()*20000);//经验值
			cm.gainDY(350);//给个人抵用卷
			cm.getPlayer().setOneTimeLog("主线任务");
			cm.worldMessage(6,"玩家:["+cm.getName()+"]完成了剧情主线任务第三关,获得奖励:经验值,抵用卷,金币,道具等奖品超级丰厚!");
			cm.sendOk("恭喜你完成了剧情主线任务第三关，下面第四关奖励也是很丰厚哦！");
			cm.dispose();	
		}else{

		 cm.sendOk(""+任务简述+"\r\n你还记得一起跟朋友打副本的样子?\r\n请到一个叫#r（月妙组队副本）的地方#k\r\n#e收集材料：#k#n\r\n#v4170000#[#c4170000#]10个\r\n"+奖励+"\r\n"+经验值+"*20000  (随着等级提升Exp)\r\n"+点券+" 抵用卷*350\r\n"+小金币+" 金币值*80000\r\n #v4250700#*5 #v4250300#*5 #v1112742#*1 #v1050018#*1");
		 cm.dispose();
		
	    }
		}else if (selection == 30) {
			if (cm.getPlayer().getCSPoints(1) > 500){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("背包空间不足2格");
			cm.dispose();
			return;
		}
				cm.gainNX(-500);//加减点券
                cm.gainItem(4000106,150);
				cm.gainItem(4000107,150);
                cm.dispose();
			}else{
                cm.sendOk("500点券不足，购买不到哦！");
                cm.dispose();
		}
        } else if (selection == 4) {
			
		if (cm.haveItem(4000106,150) && cm.haveItem(4000107,150)){
			if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("背包空间不足5格");
			cm.dispose();
			return;
		}
			cm.gainItem(4000106, -150);//获得物品
			cm.gainItem(4000107, -150);//获得物品
			cm.gainMeso(+100000); //加减金币
			cm.gainItem(4250100,5);//获得物品蓝宝石下等
			cm.gainItem(4250600,5);//获得物品黄晶下等
			cm.gainItem(1132000,1);//获得物品白色腰带
			cm.gainItem(4001126,10);//获得物品枫叶
			cm.gainExp(cm.getLevel()*20000);//经验值
			cm.gainDY(400);//给个人抵用卷
			cm.getPlayer().setOneTimeLog("主线任务");
			cm.worldMessage(6,"玩家:["+cm.getName()+"]完成了剧情主线任务第四关,获得奖励:经验值,抵用卷,金币,道具等奖品超级丰厚!");
			cm.sendOk("恭喜你完成了剧情主线任务第四关，下面第五关奖励也是很丰厚哦！");
			cm.dispose();
		}else{
			cm.sendOk(""+任务简述+"\r\n你还记得在玩具城一起跟朋友练级?\r\n请到一个叫#r（露台大厅）的地方#k\r\n#e收集材料：#k#n\r\n#v4000106#[#c4000106#]150个\r\n#v4000107#[#c4000107#]150个\r\n"+奖励+"\r\n"+经验值+"*20000  (随着等级提升Exp)\r\n"+点券+" 抵用卷*1000\r\n"+小金币+" 金币值*100000\r\n #v4250100#*5 #v4250600#*5\r\n #v1132000#*1  材料:#v4001126#*10");
			cm.dispose();
	    }
		}else if (selection == 31) {
			if (cm.getPlayer().getCSPoints(1) > 600){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("背包空间不足2格");
			cm.dispose();
			return;
		}
				cm.gainNX(-600);//加减点券
                cm.gainItem(4000170,150);
				cm.gainItem(4000169,150);
                cm.dispose();
			}else{
                cm.sendOk("600点券不足，购买不到哦！");
                cm.dispose();
		}
        } else if (selection == 5) {
			
		if (cm.haveItem(4000170,150) && cm.haveItem(4000169,150)){
			if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("背包空间不足5格");
			cm.dispose();
			return;
		}
			cm.gainItem(4000170, -150);//获得物品
			cm.gainItem(4000169, -150);//获得物品
			cm.gainItem(4250000,5);//获得物品钻石下等
			cm.gainItem(4251300,5);//获得物品黑水晶下等
			cm.gainItem(4000463,5);//获得物品国庆纪念币
			cm.gainItem(1112743,1);//获得物品专属紫金枫叶戒指
			cm.gainMeso(+120000); //加减金币
			cm.gainExp(cm.getLevel()*20000);//经验值
			cm.gainDY(450);//给个人抵用卷
			cm.getPlayer().setOneTimeLog("主线任务");
			cm.worldMessage(6,"玩家:["+cm.getName()+"]完成了剧情主线任务第五关,获得奖励:经验值,抵用卷,金币,道具等奖品超级丰厚!");
			cm.sendOk("恭喜你完成了剧情主线任务第五关，下面第六关奖励也是很丰厚哦！");
			cm.dispose();
		}else{
			cm.sendOk(""+任务简述+"\r\n等你高级了,是否也想成为大佬呢!\r\n请到一个叫#r（童话村-乌山入口收集）的地方#k\r\n#e收集材料：#k#n\r\n#v4000170#[#c4000170#]150个#v4000169#[#c4000169#]150个\r\n"+奖励+"\r\n"+经验值+"*20000  (随着等级提升Exp)\r\n"+点券+" 抵用卷*450\r\n"+小金币+" 金币值*120000\r\n #v4250000#*5 #v4251300#*5\r\n #v1112743#*1\t #v4000463#*5");
			cm.dispose();
	    }
				}else if (selection == 32) {
			if (cm.getPlayer().getCSPoints(1) > 700){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("背包空间不足2格");
			cm.dispose();
			return;
		}
				cm.gainNX(-700);//加减点券
                cm.gainItem(4000276,200);
				cm.gainItem(4000277,200);
                cm.dispose();
			}else{
                cm.sendOk("700点券不足，购买不到哦！");
                cm.dispose();
		}
        } else if (selection == 6) {
			if (cm.haveItem(4000276 ,200) && cm.haveItem(4000277 ,200)){
				if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("背包空间不足5格");
			cm.dispose();
			return;
		}
			cm.gainItem(4000276, -200);//获得物品
			cm.gainItem(4000277, -200);//获得物品
			cm.gainMeso(+140000); //加减金币
			cm.gainItem(4250201,2);//获得物品石榴石中等
			cm.gainItem(4250401,2);//获得物品紫水晶中等
			cm.gainItem(4004000,20);//获得物品力量母矿
			cm.gainItem(4004001,20);//获得物品智慧母矿
			cm.gainItem(1142005,1);//获得物品传说中的冒险家勋章
			cm.gainExp(cm.getLevel()*20000);//经验值
			cm.gainDY(500);//给个人抵用卷
			cm.getPlayer().setOneTimeLog("主线任务");
			cm.worldMessage(6,"玩家:["+cm.getName()+"]完成了剧情主线任务第六关,获得奖励:经验值,抵用卷,金币,道具等奖品超级丰厚!");
			cm.sendOk("恭喜你完成了剧情主线任务第六关，下面第七关奖励也是很丰厚哦！");
			cm.dispose();
		}else{
			cm.sendOk(""+任务简述+"\r\n当初去过武陵的玩家好像听说那里有很厉害怪物!\r\n请到一个叫#r（通天林入口）的地方#k\r\n#e收集材料：#k#n\r\n#v4000276#[#c4000276#]200个#v4000277#[#c4000277#]200个\r\n"+奖励+"\r\n"+经验值+"*20000  (随着等级提升Exp)\r\n"+点券+" 抵用卷*400\r\n"+小金币+" 金币值*140000\r\n #v4250201#*2 #v4250401#*2\r\n #v4004001#*20 #v4004000#*20\r\n #v1142005#*1");
			cm.dispose();
	    }
		}else if (selection == 33) {
			if (cm.getPlayer().getCSPoints(1) > 800){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("背包空间不足2格");
			cm.dispose();
			return;
		}
				cm.gainNX(-800);//加减点券
                cm.gainItem(4000115,400);
                cm.dispose();
			}else{
                cm.sendOk("800点券不足，购买不到哦！");
                cm.dispose();
		}
        } else if (selection == 7) {
			if (cm.haveItem(4000115 ,400)){
				if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("背包空间不足5格");
			cm.dispose();
			return;
		}
			cm.gainItem(4000115, -400);//获得物品
			cm.gainMeso(+160000); //加减金币
			cm.gainItem(1132139,1);//金梦猪腰带
			cm.gainItem(4250501,2);//材料海蓝宝石中等
			cm.gainItem(4250700,2);//材料祖母绿宝石中等
			cm.gainItem(4004002,20);//材料敏捷母矿
			cm.gainItem(4004003,20);//材料幸运母矿
			cm.gainExp(cm.getLevel()*20000);//经验值
			cm.gainDY(550);//给个人抵用卷
			cm.getPlayer().setOneTimeLog("主线任务");
			cm.worldMessage(6,"玩家:["+cm.getName()+"]完成了剧情主线任务第七关,获得奖励:经验值,抵用卷,金币,道具等奖品超级丰厚!");
			cm.sendOk("恭喜你完成了剧情主线任务第七关，下面第八关奖励也是很丰厚哦！");
			cm.dispose();
		}else{
			cm.sendOk(""+任务简述+"\r\n你还记得去往打闹钟的路?我们只是经过而已!\r\n请到一个叫#r（时间之路4）的地方#k\r\n#e收集材料：#k#n\r\n#v4000115#[#c4000115#]400个\r\n"+奖励+"\r\n"+经验值+"*20000  (随着等级提升Exp)\r\n"+点券+" 抵用卷*550\r\n"+小金币+" 金币值*160000\r\n #v1132139#*1 #v4250501#*2\r\n #v4250701#*2 #v4004003#*20 #v4004002#*20");
			cm.dispose();
	    }
		}else if (selection == 34) {
			if (cm.getPlayer().getCSPoints(1) > 900){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("背包空间不足2格");
			cm.dispose();
			return;
		}
				cm.gainNX(-900);//加减点券
                cm.gainItem(4000088,250);
				cm.gainItem(4000086,250);
                cm.dispose();
			}else{
                cm.sendOk("900点券不足，购买不到哦！");
                cm.dispose();
		}
        } else if (selection == 8) {
			if (cm.haveItem(4000088 ,250) && cm.haveItem(4000086 ,250)){
				if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("背包空间不足5格");
			cm.dispose();
			return;
		}
			cm.gainItem(4000086, -250);//扣除材料
			cm.gainItem(4000088, -250);//扣除材料
			cm.gainMeso(+180000); //加减金币
			cm.gainItem(1112795,1);//瑞贝卡的枫叶戒指
			cm.gainItem(4250301,2);//蛋白石中等
			cm.gainItem(4250101,2);//蓝宝石中等
			cm.gainItem(4004004,10);//黑暗水晶母矿
			cm.gainItem(3010094,1);//漂漂猪椅子
			cm.gainExp(cm.getLevel()*20000);//经验值
			cm.gainDY(600);//给个人抵用卷
			cm.getPlayer().setOneTimeLog("主线任务");
			cm.worldMessage(6,"玩家:["+cm.getName()+"]完成了剧情主线任务第八关,获得奖励:经验值,抵用卷,金币,道具等奖品超级丰厚!");
			cm.sendOk("恭喜你完成了剧情主线任务第八关，下面第九关奖励也是很丰厚哦！");
			cm.dispose();
		}else{
			cm.sendOk(""+任务简述+"\r\n有一个来自神秘岛的小企鹅跟小白雪鬼!\r\n请到一个叫#r（雪域附近）的地方#k\r\n#e收集材料：#k#n\r\n#v4000088#[#c4000088#]250个\r\n#v4000086#[#c4000086#]250个\r\n"+奖励+"\r\n"+经验值+"*20000  (随着等级提升Exp)\r\n"+点券+" 抵用卷*600\r\n"+小金币+" 金币值*180000\r\n #v1112795#*1 #v4250301#*2 #v4250101#*2 #v4004004#*10 #v3010094#*1");
			cm.dispose();
	    }
	    }else if (selection == 35) {
			if (cm.getPlayer().getCSPoints(1) > 1000){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("背包空间不足2格");
			cm.dispose();
			return;
		}
				cm.gainNX(-1000);//加减点券
                cm.gainItem(4000177,300);
				cm.gainItem(4000025,300);
                cm.dispose();
			}else{
                cm.sendOk("1000点券不足，购买不到哦！");
                cm.dispose();
		}
		} else if (selection == 9) {
			if (cm.haveItem(4000177 ,300) && cm.haveItem(4000025 ,300)){
				if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("背包空间不足5格");
			cm.dispose();
			return;
		}
			cm.gainItem(4000177, -300);//获得物品
			cm.gainItem(4000025, -300);//获得物品
			cm.gainMeso(+200000); //加减金币
			cm.gainItem(4250601,2);//黄晶中
			cm.gainItem(4250001,2);//钻石中
			cm.gainItem(1142298,1);//冒险岛8周年大勋章
			cm.gainItem(4310150,2);//商业币
			cm.gainItem(4000313,10);//进阶币
			cm.gainExp(cm.getLevel()*20000);//经验值
			cm.gainDY(650);//给个人抵用卷
			cm.getPlayer().setOneTimeLog("主线任务");
			cm.worldMessage(6,"玩家:["+cm.getName()+"]完成了剧情主线任务第九关,获得奖励:经验值,抵用卷,金币,道具等奖品超级丰厚!");
			cm.sendOk("恭喜你完成了剧情主线任务第九关，下面第十关奖励也是很丰厚哦！");
			cm.dispose();
		}else{
			cm.sendOk(""+任务简述+"\r\n你还记得林中之城密密麻麻石头人吗?\r\n请到一个叫#r（巨人之林）的地方#k\r\n#e收集材料：#k#n\r\n#v4000177#[#c4000177#]300个#v4000025#[#c4000025#]300个\r\n"+奖励+"\r\n"+经验值+" X20000  (随着等级提升Exp)\r\n"+点券+" 抵用卷 X650\r\n"+小金币+" 金币值 *200000\r\n #v4250601#*2 #v4250001#*2 #v4310150#*2 #v4000313#*10 #v1142298#*1");
			cm.dispose();
	    }
		}else if (selection == 36) {
			if (cm.getPlayer().getCSPoints(1) > 1100){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("背包空间不足2格");
			cm.dispose();
			return;
		}
				cm.gainNX(-1100);//加减点券
                cm.gainItem(4000289,300);
				cm.gainItem(4000299,300);
                cm.dispose();
			}else{
                cm.sendOk("1100点券不足，购买不到哦！");
                cm.dispose();
		}
		} else if (selection == 10) {
			if (cm.haveItem(4000289 ,300) && cm.haveItem(4000299 ,300) ){
				if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("背包空间不足5格");
			cm.dispose();
			return;
		}
			cm.gainItem(4000289, -300);
			cm.gainItem(4000299, -300);
			cm.gainItem(4251301,2);//黑水晶中
			cm.gainItem(4000463,5);//国庆纪念币
			cm.gainItem(3010110,1);//舒适大白熊椅子
			cm.gainItem(4310150,4);//商业币
			cm.gainItem(4000313,15);//进阶币
			cm.gainMeso(+220000); //加减金币
			cm.gainExp(cm.getLevel()*20000);//经验值
			cm.gainDY(700);//给个人抵用卷
			cm.getPlayer().setOneTimeLog("主线任务");
			cm.worldMessage(6,"玩家:["+cm.getName()+"]完成了剧情主线任务第十关,获得奖励:经验值,抵用卷,金币,道具等奖品超级丰厚!");
			cm.sendOk("恭喜你完成了剧情主线任务第十关，下面第十一关奖励也是很丰厚哦！");
			cm.dispose();
		}else{
			cm.sendOk(""+任务简述+"\r\n来自武陵的像猫咪一样的怪物!\r\n请到一个叫#r（妖怪森林）的地方#k\r\n#e收集材料：#k#n\r\n#v4000289#[#c4000289#]300个#v4000299#[#c4000299#]300个\r\n"+奖励+"\r\n"+经验值+" X20000  (随着等级提升Exp)\r\n"+点券+" 抵用卷 X700\r\n"+小金币+" 金币值 X220000\r\n #v4251301#*2 #v4000463#*5 #v4310150#*4 #v4000313#*15 #v3010110#*1");
			cm.dispose();
	    }
		}else if (selection == 37) {
			if (cm.getPlayer().getCSPoints(1) > 1200){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("背包空间不足2格");
			cm.dispose();
			return;
		}
				cm.gainNX(-1200);//加减点券
                cm.gainItem(4000032,300);
				cm.gainItem(4000034,300);
                cm.dispose();
			}else{
                cm.sendOk("1200点券不足，购买不到哦！");
                cm.dispose();
		}		
		} else if (selection == 11) {
			if (cm.haveItem(4000032 ,300) && cm.haveItem(4000034 ,300) ){
				if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("背包空间不足5格");
			cm.dispose();
			return;
		}
			cm.gainItem(4000032, -300);
			cm.gainItem(4000034, -300);
			cm.gainMeso(+240000); //加减金币
			cm.gainItem(4250202,1);//石榴石高
			cm.gainItem(4250402,1);//紫水晶高
			cm.gainItem(1132151,1);//紫金枫叶腰带
			cm.gainItem(4310150,6);//商业币
			cm.gainItem(4000313,20);//进阶币
			cm.gainExp(cm.getLevel()*20000);//经验值
			cm.gainDY(750);//给个人抵用卷
			cm.getPlayer().setOneTimeLog("主线任务");
			cm.worldMessage(6,"玩家:["+cm.getName()+"]完成了剧情主线任务第十一关,获得奖励:经验值,抵用卷,金币,道具等奖品超级丰厚!");
			cm.sendOk("恭喜你完成了剧情主线任务第十一关，下面第十二关奖励也是很丰厚哦！");
			cm.dispose();
		}else{
			cm.sendOk(""+任务简述+"\r\n你还记得一起跟朋友去打废弃都市副本上面有个下水道的洞口吗?\r\n请到一个叫#r（沼泽地Ⅰ）#k\r\n#e收集材料：#k#n\r\n#v4000032#[#c4000032#]300个#v4000034#[#c4000034#]300个\r\n"+奖励+"\r\n"+经验值+" X20000  (随着等级提升Exp)\r\n"+点券+" 抵用卷 X750\r\n"+小金币+" 金币值 X240000\r\n #v4250202#*1 #v4250402#*1 #v4000313#*20 #v4310150#*6 #v1132151#*1");
			cm.dispose();
	    }
	    }else if (selection == 38) {
			if (cm.getPlayer().getCSPoints(1) > 1300){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("背包空间不足2格");
			cm.dispose();
			return;
		}
				cm.gainNX(-1300);//加减点券
                cm.gainItem(4000040,16);
				cm.gainItem(4000176,16);
                cm.dispose();
			}else{
                cm.sendOk("1300点券不足，购买不到哦！");
                cm.dispose();
		}
		} else if (selection == 12) {
			if (cm.haveItem(4000040 ,16) && cm.haveItem(4000176 ,16)){
				if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("背包空间不足5格");
			cm.dispose();
			return;
		}
			cm.gainItem(4000040, -16);//获得物品
			cm.gainItem(4000176, -16);//获得物品
			cm.gainItem(4250502,1);//海蓝宝石高
			cm.gainItem(4250702,1);//祖母绿高
			cm.gainItem(1142367,1);//巧克力棒周末特别勋章
			cm.gainItem(4000463,10);//国庆纪念币
			cm.gainItem(4000038,2);//金杯
			cm.gainMeso(+300000); //加减金币
			cm.gainExp(cm.getLevel()*20000);//经验值
			cm.gainDY(800);//给个人抵用卷
			cm.getPlayer().setOneTimeLog("主线任务");
			cm.worldMessage(6,"玩家:["+cm.getName()+"]完成了剧情主线任务第十二关,获得奖励:经验值,抵用卷,金币,道具等奖品超级丰厚!");
			cm.sendOk("恭喜你完成了剧情主线任务第十二关，下面第十三关奖励也是很丰厚哦！");
			cm.dispose();
		}else{
			cm.sendOk(""+任务简述+"\r\n第一次遇见野外BOSS的时候是在铁甲猪公园Ⅲ\r\n请去打掉#r（野外BOSS蘑菇王）#k\r\n#e收集材料：#k#n\r\n#v4000176#[#c4000176#]16个#v4000040#[#c4000040#]16个\r\n"+奖励+"\r\n"+经验值+" X20000  (随着等级提升Exp)\r\n"+点券+" 抵用卷 X800\r\n"+小金币+" 金币值 X300000\r\n #v4250502#*1 #v4250702#*1 #v4000463#*10 #v4000038#*2 #v1142367#*1");
			cm.dispose();
		}
		}else if (selection == 39) {
			if (cm.getPlayer().getCSPoints(1) > 1400){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("背包空间不足2格");
			cm.dispose();
			return;
		}
				cm.gainNX(-1400);//加减点券
                cm.gainItem(4000020,300);
				cm.gainItem(4000178,300);
                cm.dispose();
			}else{
                cm.sendOk("1400点券不足，购买不到哦！");
                cm.dispose();
		}
	} else if (selection == 13) {
		if (cm.haveItem(4000020 ,300) && cm.haveItem(4000178 ,300)){
			if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("背包空间不足5格");
			cm.dispose();
			return;
		}
		cm.gainItem(4000020, -300);//获得物品
		cm.gainItem(4000178, -300);//获得物品
		cm.gainItem(4250302,1);//蛋白石高
		cm.gainItem(4250102,1);//蓝宝石高
		cm.gainItem(1002758,1);//强化版枫叶头盔
		//cm.gainItem(2290096,1);//冒险岛勇士20
		cm.gainItem(4000038,3);//金杯
		cm.gainMeso(+330000); //加减金币
		cm.gainExp(cm.getLevel()*20000);//经验值
		cm.gainDY(850);//给个人抵用卷
		cm.getPlayer().setOneTimeLog("主线任务");
		cm.worldMessage(6,"玩家:["+cm.getName()+"]完成了剧情主线任务第十三关,获得奖励:经验值,抵用卷,金币,道具等奖品超级丰厚!");
		cm.sendOk("恭喜你完成了剧情主线任务第十三关，下面第十四关奖励也是很丰厚哦！");
		cm.dispose();
	}else{
		cm.sendOk(""+任务简述+"\r\n来自金银岛的一群野猪跟钢甲猪/非常顽强!\r\n请到一个叫#r（钢之黑怪之地）的地方#k\r\n#e收集材料：#k#n\r\n#v4000020#[#c4000020#]300个#v4000178#[#c4000178#]300个\r\n"+奖励+"\r\n"+经验值+" X20000  (随着等级提升Exp)\r\n"+点券+" 抵用卷 X850\r\n"+小金币+" 金币值 *330000\r\n #v4250302#*1 #v4250102#*1 #v4000038#*3 #v1002758#*1 ");
		cm.dispose();
	}
	}else if (selection == 40) {
			if (cm.getPlayer().getCSPoints(1) > 1500){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("背包空间不足2格");
			cm.dispose();
			return;
		}
				cm.gainNX(-1500);//加减点券
                cm.gainItem(4000023,300);
				cm.gainItem(4000030,300);
                cm.dispose();
			}else{
                cm.sendOk("1500点券不足，购买不到哦！");
                cm.dispose();
		}
} else if (selection == 14) {
	if (cm.haveItem(4000023 ,300) && cm.haveItem(4000030 ,300)){
		if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("背包空间不足5格");
			cm.dispose();
			return;
		}
	cm.gainItem(4000023, -300);//获得物品
	cm.gainItem(4000030, -300);//获得物品
	cm.gainItem(4250602,1);//黄晶高
	cm.gainItem(4250002,1);//钻石高
	cm.gainItem(1142898,1);//名师出高徒勋章
	cm.gainItem(4032391,10);//破碎的卷轴碎片 1
	cm.gainItem(4032392,10);//破碎的卷轴碎片2
	cm.gainMeso(+360000); //加减金币
	cm.gainExp(cm.getLevel()*20000);//经验值
	cm.gainDY(900);//给个人抵用卷
	cm.getPlayer().setOneTimeLog("主线任务");
	cm.worldMessage(6,"玩家:["+cm.getName()+"]完成了剧情主线任务第十四关,获得奖励:经验值,抵用卷,金币,道具等奖品超级丰厚!");
	cm.sendOk("恭喜你完成了剧情主线任务第十四关，下面第十五关奖励也是很丰厚哦！");
	cm.dispose();
}else{
	cm.sendOk(""+任务简述+"\r\n来自一个像迷宫一样的地图,走的头晕脑胀,简称:蓝色忧伤之地!\r\n请到一个叫#r（龙穴）的地方#k\r\n#e收集材料：#k#n\r\n#v4000023#[#c4000023#]300个#v4000030#[#c4000030#]300个\r\n"+奖励+"\r\n"+经验值+" X20000  (随着等级提升Exp)\r\n"+点券+" 抵用卷 *900\r\n"+小金币+" 金币值 X360000\r\n #v4250602#*1 #v4250002#*1 #v4032391#*10 #v4032392#*10 #v1142898#*1");
	cm.dispose();
}
}else if (selection == 41) {
			if (cm.getPlayer().getCSPoints(1) > 1600){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("背包空间不足2格");
			cm.dispose();
			return;
		}
				cm.gainNX(-1600);//加减点券
                cm.gainItem(4000206,350);
				cm.gainItem(4000207,350);
                cm.dispose();
			}else{
                cm.sendOk("1600点券不足，购买不到哦！");
                cm.dispose();
		}
} else if (selection == 15) {
	if (cm.haveItem(4000206 ,350) && cm.haveItem(4000207 ,350)){
		if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("背包空间不足5格");
			cm.dispose();
			return;
		}
	cm.gainItem(4000206, -350);//获得物品
	cm.gainItem(4000207, -350);//获得物品
	cm.gainMeso(+400000); //加减金币
	cm.gainItem(4251302,1);//黑水晶高
	cm.gainItem(1022236,1);//大师赏金猎人太阳眼镜
	//cm.gainItem(2290125,1);//[能手册]冒险岛勇士30
	cm.gainItem(4005000,3);//力量水晶
	cm.gainItem(4005001,10);//智慧水晶
	cm.gainExp(cm.getLevel()*20000);//经验值
	cm.gainDY(950);//给个人抵用卷
	cm.getPlayer().setOneTimeLog("主线任务");
	cm.worldMessage(6,"玩家:["+cm.getName()+"]完成了剧情主线任务第十五关,获得奖励:经验值,抵用卷,金币,道具等奖品超级丰厚!");
	cm.sendOk("恭喜你完成了剧情主线任务第十五关，下面第十六关奖励也是很丰厚哦！");
	cm.dispose();
}else{
	cm.sendOk(""+任务简述+"\r\n来自金银岛的骷髅,第一次看见是否有点可怕与恐惧!\r\n请到一个叫#r（第1军营）的地方#k\r\n#e收集材料：#k#n\r\n#v4000206#[#c4000206#]350个#v4000207#[#c4000207#]350个\r\n"+奖励+"\r\n"+经验值+" X20000  (随着等级提升Exp)\r\n"+点券+" 抵用卷 X950\r\n"+小金币+" 金币值 X400000\r\n #v4251302#*1 #v1022236#*1 #v4005000#*3 #v4005001#*3 ");
	cm.dispose();
}
}else if (selection == 42) {
			if (cm.getPlayer().getCSPoints(1) > 1700){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("背包空间不足2格");
			cm.dispose();
			return;
		}
				cm.gainNX(-1700);//加减点券
                cm.gainItem(4000029,400);
				cm.gainItem(4000031,400);
                cm.dispose();
			}else{
                cm.sendOk("1700点券不足，购买不到哦！");
                cm.dispose();
		}
} else if (selection == 16) {
	if (cm.haveItem(4000029 ,400) && cm.haveItem(4000031 ,400)){
		if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("背包空间不足5格");
			cm.dispose();
			return;
		}
	cm.gainItem(4000029, -400);//获得物品
	cm.gainItem(4000031, -400);//获得物品
	cm.gainMeso(+440000); //加减金币
	cm.gainItem(3015172,1);//幻想牧场椅子
	cm.gainItem(4032391,10);//破碎的卷轴碎片 1 
	cm.gainItem(4032392,10);//破碎的卷轴碎片2 
	cm.gainItem(4005002,3);//敏捷水晶
	cm.gainItem(4005003,3);//幸运水晶
	cm.gainExp(cm.getLevel()*20000);//经验值
	cm.gainDY(1000);//给个人抵用卷
	cm.getPlayer().setOneTimeLog("主线任务");
	cm.worldMessage(6,"玩家:["+cm.getName()+"]完成了剧情主线任务第十六关,获得奖励:经验值,抵用卷,金币,道具等奖品超级丰厚!");
	cm.sendOk("恭喜你完成了剧情主线任务第十六关，下面第十七关奖励也是很丰厚哦！");
	cm.dispose();
}else{
	cm.sendOk(""+任务简述+"\r\n来自金银岛的一群的活波乱跳猴子/猴塞力!\r\n请到一个叫#r（猴子森林Ⅱ）的地方#k\r\n#e收集材料：#k#n\r\n#v4000029#[#c4000029#]400个#v4000031#[#c4000031#]400个\r\n"+奖励+"\r\n"+经验值+" X20000  (随着等级提升Exp)\r\n"+点券+" 抵用卷 X1000\r\n"+小金币+" 金币值 X440000\r\n #v4005002#*3 #v4005003#*3 #v3015172#*1 #v4032391#*10 #v4032392#*10");
	cm.dispose();
}
}else if (selection == 43) {
			if (cm.getPlayer().getCSPoints(1) > 1800){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("背包空间不足2格");
			cm.dispose();
			return;
		}
				cm.gainNX(-1800);//加减点券
                cm.gainItem(4000070,1000);
				cm.gainItem(4000071,1000);
				cm.gainItem(4000072,1000);
                cm.dispose();
			}else{
                cm.sendOk("1800点券不足，购买不到哦！");
                cm.dispose();
		}
} else if (selection == 17) {
	if (cm.haveItem(4000070 ,220) && cm.haveItem(4000071 ,220) && cm.haveItem(4000072 ,220) ){
		if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("背包空间不足5格");
			cm.dispose();
			return;
		}
	cm.gainItem(4000070, -220);//获得物品
	cm.gainItem(4000071, -220);//获得物品
	cm.gainItem(4000072, -220);//获得物品
	cm.gainMeso(+480000); //加减金币
	cm.gainItem(4000038,5);//金杯
	cm.gainItem(4310150,10);//商业币
	cm.gainItem(4000313,100);//进阶币
	cm.gainItem(4005004,3);//黑暗水晶
	cm.gainItem(4001126,100);//枫叶
	cm.gainExp(cm.getLevel()*20000);//经验值
	cm.gainDY(1100);//给个人抵用卷
	cm.getPlayer().setOneTimeLog("主线任务");
	cm.worldMessage(6,"玩家:["+cm.getName()+"]完成了剧情主线任务第十七关,获得奖励:经验值,抵用卷,金币,道具等奖品超级丰厚!");
	cm.sendOk("恭喜你完成了剧情主线任务第十七关，下面第十八关奖励也是很丰厚哦！");
	cm.dispose();
}else{
	cm.sendOk(""+任务简述+"\r\n来自神秘岛的一群的可爱小角狮\r\n请到一个叫#r（天空楼梯Ⅰ）的地方#k\r\n#e收集材料：#k#n\r\n#v4000070#[#c4000070#]220个#v4000071#[#c4000071#]220个\r\n#v4000072#[#c4000072#]220个\r\n"+奖励+"\r\n"+经验值+" X20000  (随着等级提升Exp)\r\n"+点券+" 抵用卷 *1100\r\n"+小金币+" 金币值 X480000\r\n #v4005004#*3 #v4000038#*5 #v4000313#*100 #v4001126#*100 #v4310150#*10");
	cm.dispose();
}
}else if (selection == 44) {
			if (cm.getPlayer().getCSPoints(1) > 1900){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("背包空间不足2格");
			cm.dispose();
			return;
		}
				cm.gainNX(-1900);//加减点券
                cm.gainItem(4000060,450);
				cm.gainItem(4000061,450);
                cm.dispose();
			}else{
                cm.sendOk("1900点券不足，购买不到哦！");
                cm.dispose();
		}
} else if (selection == 18) {
	if (cm.haveItem(4000060 ,450) && cm.haveItem(4000061 ,450) ){
		if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("背包空间不足5格");
			cm.dispose();
			return;
		}
	cm.gainItem(4000060, -450);
	cm.gainItem(4000061, -450);
	cm.gainItem(4250201,5);//石榴石中等
	cm.gainItem(4310150,5);//紫水晶中
	cm.gainItem(4250701,5);//海蓝宝石中
	cm.gainItem(4250301,5);//祖母绿中
	cm.gainItem(4250101,5);//蛋白石
	cm.gainMeso(+520000); //加减金币
	cm.gainExp(cm.getLevel()*20000);//经验值
	cm.gainDY(1200);//给个人抵用卷
	cm.getPlayer().setOneTimeLog("主线任务");
	cm.worldMessage(6,"玩家:["+cm.getName()+"]完成了剧情主线任务第十八关,获得奖励:经验值,抵用卷,金币,道具等奖品超级丰厚!");
	cm.sendOk("恭喜你完成了剧情主线任务第十八关，下面第二十关奖励也是很丰厚哦！");
	cm.dispose();
}else{
	cm.sendOk(""+任务简述+"\r\n来自神秘岛的一群的日光精灵\r\n请到一个叫#r（云彩公园Ⅵ）的地方#k\r\n#e收集材料：#k#n\r\n#v4000060#[#c4000060#]450个\r\n#v4000061#[#c4000061#]450个\r\n"+奖励+"\r\n"+经验值+" X20000  (随着等级提升Exp)\r\n"+点券+" 抵用卷 X1200\r\n"+小金币+" 金币值 X520000\r\n #v4250201#*5 #v4250401#*5 #v4250501#*5#v4250701#*5#v4250301#*5");
	cm.dispose();
}
}else if (selection == 45) {
			if (cm.getPlayer().getCSPoints(1) > 2000){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("背包空间不足2格");
			cm.dispose();
			return;
		}
				cm.gainNX(-2000);//加减点券
                cm.gainItem(4000051,450);
				cm.gainItem(4000052,450);
                cm.dispose();
			}else{
                cm.sendOk("2000点券不足，购买不到哦！");
                cm.dispose();
		}
} else if (selection == 19) {
	if (cm.haveItem(4000051 ,450) && cm.haveItem(4000052 ,450) ){
		if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("背包空间不足5格");
			cm.dispose();
			return;
		}
	cm.gainItem(4000051, -450);
	cm.gainItem(4000052, -450);
	cm.gainItem(4250601,5);//蓝宝石
	cm.gainItem(4250001,5);//黄晶
	cm.gainItem(4251301,5);//钻石
    cm.gainItem(4251301,5);//黑水晶
    cm.gainItem(4032391,20);//破碎的卷轴碎片 1
    cm.gainItem(4032392,20);//破碎的卷轴碎片2
	cm.gainMeso(+550000); //加减金币
	cm.gainExp(cm.getLevel()*20000);//经验值
	cm.gainDY(1300);//给个人抵用卷
	cm.getPlayer().setOneTimeLog("主线任务");
	cm.worldMessage(6,"玩家:["+cm.getName()+"]完成了剧情主线任务第十九关,获得奖励:经验值,抵用卷,金币,道具等奖品超级丰厚!");
	cm.sendOk("恭喜你完成了剧情主线任务第十九关，下面第二十关奖励也是很丰厚哦！");
	cm.dispose();
}else{
	cm.sendOk(""+任务简述+"\r\n来自神秘岛的一群极其凶恶的野狼\r\n请到一个叫#r（结冰的平原Ⅰ）的地方#k\r\n#e收集材料：#k#n\r\n#v4000051#[#c4000051#]450个#v4000052#[#c4000052#]450个\r\n"+奖励+"\r\n"+经验值+" X20000  (随着等级提升Exp)\r\n"+点券+" 抵用卷 X1300\r\n"+小金币+" 金币值 X550000\r\n #v4250101#*5 #v4250601#*5 #v4250001#*5 #v4251301#*5 #v4032391#*20 #v4032392#*20");
	cm.dispose();
}
}else if (selection == 46) {
			if (cm.getPlayer().getCSPoints(1) > 2100){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("背包空间不足2格");
			cm.dispose();
			return;
		}
				cm.gainNX(-2100);//加减点券
                cm.gainItem(4000049,200);
				cm.gainItem(4000050,200);
                cm.dispose();
			}else{
                cm.sendOk("2100点券不足，购买不到哦！");
                cm.dispose();
		}
} else if (selection == 20) {
	if (cm.haveItem(4000049 ,200) && cm.haveItem(4000050 ,200) ){
		if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("背包空间不足5格");
			cm.dispose();
			return;
		}
	cm.gainItem(4000049, -200);
	cm.gainItem(4000050, -200);
	cm.gainItem(4250202,2);//石榴石高
	cm.gainItem(4250402,2);//紫水晶
	cm.gainItem(4250502,2);//海蓝宝
	cm.gainItem(4250702,2);//祖母绿
	cm.gainItem(4250302,2);//蛋白
	cm.gainItem(4250102,2);//蓝宝石
	cm.gainMeso(+580000); //加减金币
	cm.gainExp(cm.getLevel()*20000);//经验值
	cm.gainDY(1400);//给个人抵用卷
	cm.getPlayer().setOneTimeLog("主线任务");
	cm.worldMessage(6,"玩家:["+cm.getName()+"]完成了剧情主线任务第二十关,获得奖励:经验值,抵用卷,金币,道具等奖品超级丰厚!");
	cm.sendOk("恭喜你完成了剧情主线任务第二十关，下面第二十一关奖励也是很丰厚哦！");
	cm.dispose();
}else{
	cm.sendOk(""+任务简述+"\r\n来自神秘岛的一群可爱纯白的白雪人\r\n请到一个叫#r（冰雪峡谷Ⅱ）的地方#k\r\n#e收集材料：#k#n\r\n#v4000049#[#c4000049#]200个#v4000050#[#c4000050#]200个\r\n"+奖励+"\r\n"+经验值+" X20000  (随着等级提升Exp)\r\n"+点券+" 抵用卷 X1400\r\n"+小金币+" 金币值 X580000\r\n #v4250202#*2 #v4250402#*2 #v4250502#*2 #v4250702#*2 #v4250102#*2 #v4250302#*2");
	cm.dispose();
}
}else if (selection == 47) {
			if (cm.getPlayer().getCSPoints(1) > 2200){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("背包空间不足2格");
			cm.dispose();
			return;
		}
				cm.gainNX(-2200);//加减点券
                cm.gainItem(4000069,450);
				cm.gainItem(4000076,450);
                cm.dispose();
			}else{
                cm.sendOk("2200点券不足，购买不到哦！");
                cm.dispose();
		}
} else if (selection == 21) {
	if (cm.haveItem(4000069 ,450) && cm.haveItem(4000076 ,450) ){
		if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("背包空间不足5格");
			cm.dispose();
			return;
		}
	cm.gainItem(4000069, -450);
	cm.gainItem(4000076, -450);
	cm.gainItem(4250602,2);//黄晶高
	cm.gainItem(4250002,2);//钻石
	cm.gainItem(4251302,2);//黑水晶
	cm.gainItem(4032391,50);//破碎的卷轴碎片 1
	cm.gainItem(4032392,50);//破碎的卷轴碎片2
	cm.gainItem(1072872,1);//黏糊鞋子
	cm.gainMeso(+620000); //加减金币
	cm.gainExp(cm.getLevel()*20000);//经验值
	cm.gainDY(1500);//给个人抵用卷
	cm.getPlayer().setOneTimeLog("主线任务");
	cm.worldMessage(6,"玩家:["+cm.getName()+"]完成了剧情主线任务第二十一关,获得奖励:经验值,抵用卷,金币,道具等奖品超级丰厚!");
	cm.sendOk("恭喜你完成了剧情主线任务第二十一关，下面第二十二关奖励也是很丰厚哦！");
	cm.dispose();
}else{
	cm.sendOk(""+任务简述+"\r\n来自神秘岛的一群日本僵尸跟疫情蝙蝠\r\n请到一个叫#r（废矿区Ⅲ）的地方#k\r\n#e收集材料：#k#n\r\n#v4000069#[#c4000069#]450个#v4000076#[#c4000076#]450个\r\n"+奖励+"\r\n"+经验值+" X20000  (随着等级提升Exp)\r\n"+点券+" 抵用卷 X1500\r\n"+小金币+" 金币值 X620000\r\n #v4250602#*2 #v4250002#*2 #v4251302#*2 #v4032391#*50 #v4032392#*50 #v1072872#*1");
	cm.dispose();
}
}else if (selection == 48) {
			if (cm.getPlayer().getCSPoints(1) > 2300){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("背包空间不足2格");
			cm.dispose();
			return;
		}
				cm.gainNX(-2300);//加减点券
                cm.gainItem(4000284,500);
				cm.gainItem(4000285,500);
                cm.dispose();
			}else{
                cm.sendOk("2300点券不足，购买不到哦！");
                cm.dispose();
		}
} else if (selection == 22) {
	if (cm.haveItem(4000284 ,500) && cm.haveItem(4000285 ,500) ){
		if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("背包空间不足5格");
			cm.dispose();
			return;
		}
	cm.gainItem(4000284, -500);
	cm.gainItem(4000285, -500);
	cm.gainItem(2340000,2);//祝福卷
	cm.gainItem(2049117,2);//混沌卷
	cm.gainItem(3010142,1);//水族馆椅子
	cm.gainItem(1102163,1);//贵族披风
	cm.gainItem(4251200,1);//下等五彩水晶
	cm.gainItem(4000463,20);//国庆纪念币
	cm.gainMeso(+660000); //加减金币
	cm.gainExp(cm.getLevel()*20000);//经验值
	cm.gainDY(1600);//给个人抵用卷
	cm.getPlayer().setOneTimeLog("主线任务");
	cm.worldMessage(6,"玩家:["+cm.getName()+"]完成了剧情主线任务第二十二关,获得奖励:经验值,抵用卷,金币,道具等奖品超级丰厚!");
	cm.sendOk("恭喜你完成了剧情主线任务第二十二关，下面第二十三关奖励也是很丰厚哦！");
	cm.dispose();
}else{
	cm.sendOk(""+任务简述+"\r\n来自武陵的一群胖嘟嘟的熊\r\n请到一个叫#r（野生熊的地盘3）的地方#k\r\n#e收集材料：#k#n\r\n#v4000284#[#c4000284#]500个#v4000285#[#c4000285#]500个\r\n"+奖励+"\r\n"+经验值+" X20000  (随着等级提升Exp)\r\n"+点券+" 抵用卷 X1600\r\n"+小金币+" 金币值 X660000\r\n #v2340000#*2 #v2049117#*2 #v1102163#*1 #v4251200#*1 #v3010142#*1 #v4000463#*20");
	cm.dispose();
}
}else if (selection == 49) {
			if (cm.getPlayer().getCSPoints(1) > 2400){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("背包空间不足2格");
			cm.dispose();
			return;
		}
				cm.gainNX(-2400);//加减点券
                cm.gainItem(4000171,600);
				cm.gainItem(4000172,600);
                cm.dispose();
			}else{
                cm.sendOk("2400点券不足，购买不到哦！");
                cm.dispose();
		}
} else if (selection == 23) {
	if (cm.haveItem(4000171 ,600) && cm.haveItem(4000172 ,600) ){
		if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("背包空间不足5格");
			cm.dispose();
			return;
		}
	cm.gainItem(4000171, -600);
	cm.gainItem(4000172, -600);
	cm.gainItem(2340000,2);//祝福卷
	cm.gainItem(2049117,2);//混沌卷
	cm.gainItem(3010144,1);//七夕椅子
	cm.gainItem(1032099,1);//葡萄耳环
	cm.gainItem(4251200,1);//下等五彩水晶
	cm.gainItem(4310150,10);//商业币
	cm.gainMeso(+700000); //加减金币
	cm.gainExp(cm.getLevel()*20000);//经验值
	cm.gainDY(1700);//给个人抵用卷
	cm.getPlayer().setOneTimeLog("主线任务");
	cm.worldMessage(6,"玩家:["+cm.getName()+"]完成了剧情主线任务第二十三关,获得奖励:经验值,抵用卷,金币,道具等奖品超级丰厚!");
	cm.sendOk("恭喜你完成了剧情主线任务第二十三关，下面第二十四关奖励也是很丰厚哦！");
	cm.dispose();
}else{
	cm.sendOk(""+任务简述+"\r\n来自童话村的一群强壮老虎跟三尾狐狸\r\n请到一个叫#r（乌山峡谷）的地方#k\r\n#e收集材料：#k#n\r\n#v4000171#[#c4000171#]600个#v4000172#[#c4000172#]600个\r\n"+奖励+"\r\n"+经验值+" X20000  (随着等级提升Exp)\r\n"+点券+" 抵用卷 X1700\r\n"+小金币+" 金币值 X700000\r\n #v2340000#*2 #v2049117#*2 #v1032099#*1 #v3010144#*1 #v4251200#*1 #v4310150#*10");
	cm.dispose();
}
}else if (selection == 50) {
			if (cm.getPlayer().getCSPoints(1) > 2500){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("背包空间不足2格");
			cm.dispose();
			return;
		}
				cm.gainNX(-2500);//加减点券
                cm.gainItem(4000160,600);
				cm.gainItem(4000161,600);
                cm.dispose();
			}else{
                cm.sendOk("2500点券不足，购买不到哦！");
                cm.dispose();
		}
} else if (selection == 24) {
	if (cm.haveItem(4000160 ,600) && cm.haveItem(4000161 ,600) ){
		if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("背包空间不足5格");
			cm.dispose();
			return;
		}
	cm.gainItem(4000160, -600);
	cm.gainItem(4000161, -600);
	cm.gainItem(2340000,2);//祝福卷
	cm.gainItem(2049117,2);//混沌卷
	cm.gainItem(3010448,1);//泡泡浴椅子
	cm.gainItem(1072872,1);//金十字戒指
	cm.gainItem(4251200,1);//下等五彩水晶
	cm.gainItem(4000038,5);//金杯
	cm.gainMeso(+750000); //加减金币
	cm.gainExp(cm.getLevel()*20000);//经验值
	cm.gainDY(1800);//给个人抵用卷
	cm.getPlayer().setOneTimeLog("主线任务");
	cm.worldMessage(6,"玩家:["+cm.getName()+"]完成了剧情主线任务第二十四关,获得奖励:经验值,抵用卷,金币,道具等奖品超级丰厚!");
	cm.sendOk("恭喜你完成了剧情主线任务第二十四关，下面第二十五关奖励也是很丰厚哦！");
	cm.dispose();
}else{
	cm.sendOk(""+任务简述+"\r\n来自水下世界的一群黑漆漆的海胆跟冷清清的小海马\r\n请到一个叫#r（矩形地带）的地方#k\r\n#e收集材料：#k#n\r\n#v4000160#[#c4000160#]600个#v4000161#[#c4000161#]600个\r\n"+奖励+"\r\n"+经验值+" X20000  (随着等级提升Exp)\r\n"+点券+" 抵用卷 X1800\r\n"+小金币+" 金币值 X750000\r\n #v2340000#*2 #v2049117#*2 #v3010448#*1 #v1072872#*1 #v4251200#*1 #v4000038#*5");
	cm.dispose();
}
}else if (selection == 51) {
			if (cm.getPlayer().getCSPoints(1) > 2600){
				if (!cm.checkNumSpace(0, 2)) {
			cm.sendOk("背包空间不足2格");
			cm.dispose();
			return;
		}
				cm.gainNX(-2600);//加减点券
                cm.gainItem(4000118,700);
				cm.gainItem(4000119,700);
                cm.dispose();
			}else{
                cm.sendOk("2600点券不足，购买不到哦！");
                cm.dispose();
		}
} else if (selection == 25) {
	if (cm.haveItem(4000118 ,700) && cm.haveItem(4000119 ,700) ){
		if (!cm.checkNumSpace(0, 5)) {
			cm.sendOk("背包空间不足5格");
			cm.dispose();
			return;
		}
	cm.gainItem(4000118, -700);
	cm.gainItem(4000119, -700);
	cm.gainItem(2340000,2);//祝福卷
	cm.gainItem(2049117,2);//混沌卷
	cm.gainItem(3010449,1);//痊愈10周椅子
	cm.gainItem(1132086,1);//三国名将挑战者腰带
	cm.gainItem(4251201,1);//中等五彩水晶
	cm.gainItem(4000038,5);//金杯
	cm.gainMeso(+800000); //加减金币
	cm.gainExp(cm.getLevel()*20000);//经验值
	cm.gainDY(2000);//给个人抵用卷
	cm.getPlayer().setOneTimeLog("主线任务");
	cm.worldMessage(6,"玩家:["+cm.getName()+"]完成了剧情主线任务第二十五关,获得奖励:经验值,抵用卷,金币,道具等奖品超级丰厚!");
    cm.sendOk("恭喜你完成了剧情主线任务第二十五关，下面第二十六关奖励也是很丰厚哦！");
	cm.dispose();
}else{
	cm.sendOk(""+任务简述+"\r\n来自地球防御本部的一群高智商的外星人,让我们一起携手击败他们!["+cm.getName()+"]/#e加油#k#n\r\n请到一个叫#r（库尔兰草原）的地方#k\r\n#e收集材料：#k#n\r\n#v4000118#[#c4000118#]700个#v4000119#[#c4000119#]700个\r\n"+奖励+"\r\n"+经验值+" X20000  (随着等级提升Exp)\r\n"+点券+" 抵用卷 X2000\r\n"+小金币+" 金币值 X800000\r\n #v2340000#*2 #v2049117#*2 #v3010449#*1 #v1132086#*1 #v4251201#*1 #v4000038#*5");
	cm.dispose();
}
}else if (selection == 52) {
			if (cm.getPlayer().getCSPoints(1) > 8888){
				if (!cm.checkNumSpace(0, 8)) {
			cm.sendOk("背包空间不足8格");
			cm.dispose();
			return;
		}
				cm.gainNX(-8888);//加减点券
                cm.gainItem(4000235,50);
				cm.gainItem(4000243,50);
				cm.gainItem(4000460,50);
				cm.gainItem(4000461,50);
				cm.gainItem(4001084,50);
				cm.gainItem(4001083,50);
				cm.gainItem(4001085,50);
				cm.gainItem(4000462,50);
                cm.dispose();
			}else{
                cm.sendOk("8888点券不足，购买不到哦！");
                cm.dispose();
		}		
		} else if (selection == 26) {
			if (cm.haveItem(4000235 ,50) && cm.haveItem(4000243 ,50) && cm.haveItem(4000460 ,50) && cm.haveItem(4000461 ,50) && cm.haveItem(4000462 ,50) && cm.haveItem(4001083 ,50) && cm.haveItem(4001084 ,50) && cm.haveItem(4001085 ,50)){
				if (!cm.checkNumSpace(0, 8)) {
			cm.sendOk("背包空间不足8格");
			cm.dispose();
			return;
		}
			cm.gainItem(4000235, -50);//减物品
			cm.gainItem(4000243, -50);//减物品
			cm.gainItem(4000460, -50);//减物品
			cm.gainItem(4000461, -50);//减物品
			cm.gainItem(4001083, -50);//减物品
			cm.gainItem(4001084, -50);//减物品
			cm.gainItem(4001085, -50);//减物品
			cm.gainItem(4000462, -50);//减物品
			cm.gainItem(2340000,5);//祝福卷
			cm.gainItem(2049117,5);//混沌卷
			cm.gainItem(3010938,1);//异界的血腥女王椅子
			cm.gainItem(1142788,1);//进击的巨人勋章
			cm.gainItem(4251202,1);//高等五彩水晶
			cm.gainItem(4000038,10);//金杯
			cm.gainItem(4310150,20);//商业币
			cm.gainItem(4000463,50);//国庆纪念币
			cm.gainMeso(+5000000); //加减金币
			cm.gainExp(cm.getLevel()*20000);//经验值
	        cm.gainDY(6666);//给个人抵用卷
			cm.getPlayer().setOneTimeLog("主线任务");
			cm.worldMessage(6,"玩家:["+cm.getName()+"]完成了剧情主线任务第二十六关,获得奖励:经验值,抵用卷,金币,道具等奖品超级丰厚!");
			cm.sendOk("恭喜你完成了剧情主线任务第二十六关，恭喜你完成所有关卡！");
			cm.dispose();
		}else{
			cm.sendOk(""+任务简述+"\r\n来自资深的玩家一封信,相信你回忆了许多的冒险岛,你有没有真正的去走过自已回忆路!\r\n#e请打败Boss怪物:#k#n\r\n#r（喷火龙,天鹰,多多,玄冰独角兽,雷卡,扎昆,黑龙,鱼王）#k\r\n#e收集材料：#k#n\r\n#v4000235#[#c4000235#]50个 #v4000243#[#c4000243#]50个 #v4000460#[#c4000460#]50个\r\n#v4000461#[#c4000461#]50个 #v4000462#[#c4000462#]50个 #v4001083#[#c4001083#]50个\r\n#v4001084#[#c4001084#]50个 #v4001085#[#c4001085#]50个\r\n"+奖励+"\r\n"+经验值+" X20000  (随着等级提升Exp)\r\n"+点券+" 抵用卷 X6666\r\n"+小金币+" 金币值 X5000000\r\n #v2340000#*5 #v2049117#*5 #v3010938#*1 #v1142788#*1 #v4251202#*1 #v4000038#*10 #v4310150#*20 #v4000463#*50");
			cm.dispose();
			}	
		}
    }
}
