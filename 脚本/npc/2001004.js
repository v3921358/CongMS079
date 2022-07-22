/*创意冒险岛
QQ12384161
*/
var 星星 = "#fEffect/CharacterEff/1114000/2/0#";
var 爱心 = "#fEffect/CharacterEff/1022223/4/0#";
var 红色箭头 = "#fUI/UIWindow/Quest/icon6/7#";
var 正方形 = "#fUI/UIWindow/Quest/icon3/6#";
var 蓝色箭头 = "#fUI/UIWindow/Quest/icon2/7#";
var 圆形 = "#fUI/UIWindow/Quest/icon3/6#";//"+圆形+"
var 感叹号 = "#fUI/UIWindow/Quest/icon0#";
var 蝴蝶结盒 = "#fItem/Consume/0224/02240013/info/iconRaw#";  //
var warp = -1
var status = 0;
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
			text += "\t\t\t\t#e"+蝴蝶结盒+" 练功房间 "+蝴蝶结盒+"\r\n\r\n"
            text += "#b#n"+圆形+" 剩余点券数量：#r#e["+cm.getPlayer().getCSPoints(1)+"]#b#n点#b#l\r\n"+圆形+" 今日普通：#r#e["+cm.getPlayer().getBossLog('普通') +"#b/#b30#r]#n#b次#d\r\n"+圆形+" #b今日会员：#r#e["+cm.getPlayer().getBossLog('会员') +"#b/#b50#r]#n#b次#d\r\n"
            text += "#L0##k"+蓝色箭头+"[#b普通#k] 【每次开启需要#b500#k万】#l\r\n"//3
            text += "#L1##k"+蓝色箭头+"[#b会员#k] 【每次开启需要#b500#k点券】#l\r\n"//3
            text += "#L2##k"+红色箭头+"[#b完成普通领奖#k] 【#r30#k次】#l\r\n"//3
            text += "#L3##k"+红色箭头+"[#b完成会员领奖#k] 【#r50#k次】#k#n\r\n\r\n"//3"//3
            text += "#L4##b"+感叹号+"退出材料房间/换线/进商城默认退出房间"//3"//3
            cm.sendSimple(text);
        } else if (selection == 0) {
			warp = 0;
			cm.sendYesNo("#e普通：#n\r\n\r\n1.每次需要500万金币\r\n2.每天只能使用30次");
			
        } else if (selection == 1) {
			warp = 1;
			cm.sendYesNo("#e会员：#n\r\n\r\n1.每次需要500点券\r\n2.每天只能使用50次");
			
        } else if (selection == 2) {
			warp = 2;
			cm.sendYesNo("#e普通领奖：#n\r\n\r\n#b每天普通次数满30次就可以领奖\r\n奖品:#v2460005#x1 #v4000463#X10#v4000487#x3  ");
			
        } else if (selection == 3) {
			warp = 3;
			cm.sendYesNo("#e会员领奖：#n\r\n\r\n#b每天会员次数满50次就可以领奖\r\n奖品:#v2022466#x1 #v4310045#x2 #v4000463#x20 #v4000487#x5 #v2460005#x3 ");
			
        } else if (selection == 4) {
			warp = 4;
			cm.sendYesNo("#e是否要返回自由市场?");
		
        }else if(status = 1) {
			if(warp == 0){
                if (cm.getMap().getAllMonstersThreadsafe().size() > 0) {
                    cm.sendOk("当前地图有未击杀的怪物");
                    cm.dispose();
                    return;
                }
	            if (cm.getBossLog("普通") >= 30) {
	                cm.sendOk("今天满30次了还点点点,点你个头啊!");
                    cm.dispose();
                    return;
	            }
				if(cm.getPlayer().getMeso() >= 5000000 ){ //物品条件
                    cm.gainMeso(-5000000);
		            cm.spawnMobOnMap(3300002,15,-308,150,209000009);
		            cm.spawnMobOnMap(3300002,10,-166,150,209000009);
		            cm.spawnMobOnMap(3300002,15,-53,150,209000009);
		            cm.spawnMobOnMap(3300002,10,53,150,209000009);
		            cm.spawnMobOnMap(3300002,15,140,150,209000009);
		            cm.spawnMobOnMap(3300002,10,197,150,209000009);
		            cm.spawnMobOnMap(3300002,15,261,150,209000009);
					cm.setBossLog("普通")
                    Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(9,cm.getC().getChannel(),"[普通]" + " : 【" + cm.getPlayer().getName() +"】 在练功房开启了轻松升级!",true));
		            cm.dispose();
				}else{
					cm.sendOk("召唤失败：\r\n\r\n1.金币不足:#r5000000");
					cm.dispose();
				}
			}else if(warp == 1){
                if (cm.getMap().getAllMonstersThreadsafe().size() > 0) {
                    cm.sendOk("当前地图有未击杀的怪物");
                    cm.dispose();
                    return;
                }
	            if (cm.getBossLog("会员") >= 50) {
	                cm.sendOk("今日会员次数已满50次,在点电脑会炸的");
                    cm.dispose();
                    return;
	            }
				if(cm.getPlayer().getNX() >= 500){ //物品条件
                    cm.gainNX(-500);
		            cm.spawnMobOnMap(3300002,15,-308,150,209000009);
		            cm.spawnMobOnMap(3300002,10,-166,150,209000009);
		            cm.spawnMobOnMap(3300002,15,-53,150,209000009);
		            cm.spawnMobOnMap(3300002,10,53,150,209000009);
		            cm.spawnMobOnMap(3300002,15,140,150,209000009);
		            cm.spawnMobOnMap(3300002,10,197,150,209000009);
		            cm.spawnMobOnMap(3300002,15,261,150,209000009);
		            cm.spawnMobOnMap(3300002,10,140,150,209000009);
					cm.setBossLog("会员");
                    Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(9,cm.getC().getChannel(),"[会员]" + " : 【" + cm.getPlayer().getName() +"】 豪无人性的花着点券升级!",true));
		            cm.dispose();
		        }else{
					cm.sendOk("召唤失败：\r\n\r\n1.点券不足:#r500");
		            cm.dispose();
		        }
			}else if(warp == 2){
	            if (cm.getBossLog("普通") <= 30) {
	                cm.sendOk("你的普通次数不满30次,不能领奖");
				status = -1;
	            }
		        if (cm.getBossLog("普通领奖") == 1){
	                cm.sendOk("#b你今天已经领取过了");
				status = -1;
	            }
		        if(cm.getPlayer().getBossLog("普通") >= 30 && cm.getPlayer().getBossLog("普通领奖") == 0 ){ //物品条件
                    cm.gainItem(2460005,1);
					cm.gainItem(4000487,3);
                    cm.gainItem(4000463,10);
					//cm.gainAp(10);//给属性点
		            cm.setBossLog("普通领奖");
                    Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(9,cm.getC().getChannel(),"普通领奖" + " : " + cm.getPlayer().getName() +" 完成了30次普通练功房,获得丰富奖励！",true));
		            cm.dispose();
		        }else{
		            cm.sendOk("领奖失败：\r\n\r\n1.完成次数不足30次\r\n2.你已经领取过了");
		            cm.dispose();
		        }
			}else if(warp == 3){
	            if (cm.getBossLog("会员") <= 50) {
	                cm.sendOk("你的会员练功不满50次,不能领奖");
				status = -1;
	            }
		        if (cm.getBossLog("会员领奖") == 1){
	                cm.sendOk("#b你今天已经领取过了");
				status = -1;
	            }
		        if(cm.getPlayer().getBossLog("会员") >= 50 && cm.getPlayer().getBossLog("会员领奖") == 0 ){ //物品条件
                    cm.gainItem(4000487,3);
                    cm.gainItem(4310045,2);
					cm.gainItem(2022466,1);
                    cm.gainItem(2460005,3);
					cm.gainItem(4000463,20);
					//cm.gainAp(50);//给属性点
		            cm.setBossLog("会员领奖");
                    Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(9,cm.getC().getChannel(),"会员领奖" + " : " + cm.getPlayer().getName() +" 完成了50次会员练功,真是不可思议！",true));
		            cm.dispose();
		        }else{
		            cm.sendOk("领奖失败：\r\n\r\n1.完成次数不足50次\r\n2.你已经领取过了");
		            cm.dispose();
		        }
			}else if(warp == 4){
             cm.warp(910000000);
             cm.dispose();
                }
            }
        }
    }
