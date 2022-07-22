var FY0 = "┏━━━━━━━━━━━┓";
var FY1 = "┃       - 枫叶 -       ┃";
var FY2 = "┃ 脚本仿制  　定制脚本 ┃";
var FY3 = "┃ 技术支持 　 游戏顾问 ┃";
var FY4 = "┃ ＷＺ添加　  地图制作 ┃";
var FY5 = "┣━━━━━━━━━━━┫";
var FY6 = "┃ 唯一QQ:1848350048    ┃";
var FY7 = "┗━━━━━━━━━━━┛";
var select = -1;
var xx;
importPackage(java.util);
importPackage(Packages.client);
var 小黄星 = "#fItem/Etc/0427/04270001/Icon9/0#";
var 中条猫 ="#fUI/ChatBalloon/37/n#";
var 红色箭头 = "#fUI/UIWindow/Quest/icon6/7#";
var zzz = "#fUI/UIWindow.img/Quest/icon8/0#";//蓝色右箭头
importPackage(Packages.server);
importPackage(Packages.tools);
importPackage(Packages.tools.packet);
function start() {
    var Editing = false //false 开始
    if (Editing) {
        cm.sendOk("维修中");
        cm.dispose();
        return;
    }
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else if (mode == 0) {
        status--;
    } else {
        cm.dispose();
        return;
    }
    if (status == 0) {
        cm.sendSimple("#r#e             "+小黄星+"   #e#d觉 醒  中 心#k#n   "+小黄星+"  #k#n\r\n  "+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+"\r\n\r\n" +
                "#L0##r"+红色箭头+"装备突破#l#k #L10##b"+zzz+"介绍（装备栏首格要有装备才可以点）#l#k\r\n" +
                "#L1##r"+红色箭头+"装备强化#l#k #L11##b"+zzz+"介绍（装备栏首格要有装备才可以点）#l#k\r\n" +
                "#L100##r"+红色箭头+"装备觉醒#l#k #L101##b"+zzz+"介绍（装备栏首格要有装备才可以点）#l#k\r\n\r\n"+
				"#L109##d                 "+小黄星+"装备解锁"+小黄星+"#l#k \r\n" 
			
				);
            
    } else if (status == 1) {
		if (selection == 109) {
                cm.dispose();
                cm.openNpc(9310071, 109);
                return;
            }
	    if (selection == 100) {
                cm.dispose();
                cm.openNpc(9310071, 1);
                return;
            } 
		
		if(cm.getInventory(1).getItem(1) == null) {
            cm.sendOk("第一格没有装备.");
            cm.dispose();
            return;
        }
        var xx = cm.getInventory(1).getItem(1).getOwner();
        var statup = new java.util.ArrayList();
        var itemId1 = cm.getInventory(1).getItem(1).getItemId();
        var item = cm.getInventory(1).getItem(1).copy();
        var ii = Packages.server.MapleItemInformationProvider.getInstance();
        var type =  Packages.constants.GameConstants.getInventoryType(itemId1);
        var sx0 = item.getStr();
        var sx1 = item.getDex();
        var sx2 = item.getInt();
        var sx3 = item.getLuk();
        var sx4 = item.getHp();
        var sx5 = item.getMp();
        var sx6 = item.getWatk();
        var sx7 = item.getMatk();
        var sx8 = item.getWdef();
        var sx9 = item.getMdef();
        var sx10= item.getAcc();
        var sx11= item.getAvoid();
        var sx12= item.getHands();
        var sx13= item.getSpeed();
        var sx14= item.getJump();
        if(cm.getInventory(1).getItem(1).getExpiration() != -1) {
            cm.sendOk("限时装备不能升星.");
            cm.dispose();
            return;
        }
        if (cm.getInventory(1).getItem(1).getUniqueId() !=-1) {
            cm.sendOk("现金装备无法升星。");
            cm.dispose();
            return;
        }
        if (Math.floor(item.getItemId()/10000) == 114 || Math.floor(item.getItemId()/10000) == 190 || Math.floor(item.getItemId()/10000) == 111 || Math.floor(item.getItemId()/10000) ==191) {
            cm.sendOk("第一格装备是#v" + item.getItemId() + "#,无法觉醒的装备类型!"); 		
            cm.dispose();    
            return;
        }if (Math.floor(item.getItemId()/10000) == 113 ) {
            cm.sendOk("第一格装备#v" + item.getItemId() + "#,是无法觉醒的装备类型，但是可以在拍卖页面饰品里面升星!"); 		
            cm.dispose();    
            return;
        }if (Math.floor(item.getItemId()/10000) == 101 ) {
            cm.sendOk("第一格装备#v" + item.getItemId() + "#,是无法觉醒的装备类型，但是可以在拍卖页面饰品里面升星!"); 		
            cm.dispose();    
            return;
        }
            if (selection == 0) {
                if(!cm.haveItem(4310034,1)){
                    cm.sendOk("#v4310034#物品数量不足1个！");
                    cm.dispose();
                    return;
                }
                if(!cm.haveItem(4001126,200)){
                    cm.sendOk("#v4001126#物品数量不足200个！");
                    cm.dispose();
                    return;
                }
                if (xx != ""){
		    		cm.sendOk("该装备无法突破");
                    cm.dispose();
                    return;
                }
                cm.gainItem(4310034,-1);
                cm.gainItem(4001126,-200);
                var 四维属性 = 1;
                var 攻魔 = 1;
		    	item.setFlag(1);//0为没上锁，1为上锁
		    	item.setStr(sx0+四维属性);
		    	item.setDex(sx1+四维属性);
		    	item.setInt(sx2+四维属性);
		    	item.setLuk(sx3+四维属性);
		    	item.setHp(sx4);
                item.setMp(sx5);
                var RandomChance = Math.floor(Math.random()*101);
                    if(RandomChance <= 60){
                        item.setWatk(sx6+攻魔);
		    	        item.setMatk(sx7+攻魔);
                    }
		    	item.setWatk(sx6+攻魔);
		    	item.setMatk(sx7+攻魔);
		    	item.setWdef(sx8);
		    	item.setMdef(sx9);
		    	item.setAcc(sx10);
		    	item.setAvoid(sx11);
		    	item.setHands(sx12);
		    	item.setSpeed(sx13);
		    	item.setJump(sx14);
		    	item.setOwner("突破");				
		    	cm.dispose();
		    	MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
		    	MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
		    	cm.ShowWZEffect("Effect/BasicEff.img/SkillBook/Success/0"); //成功
		    	cm.sendOk("突破成功！");
				cm.worldMessage(  " 【升星公告】" + " : " + "["+cm.getPlayer().getName()+"]某件装备成功突破！大家祝贺他(她)吧！！")
                cm.dispose();
                return;
            } else if (selection == 1) {
                if(!cm.haveItem(4310034,5)){
                    cm.sendOk("#v4310034#物品数量不足5个！");
                    cm.dispose();
                    return;
                }
                if(!cm.haveItem(4001126,400)){
                    cm.sendOk("#v4001126#物品数量不足400个！");
                    cm.dispose();
                    return;
                }
                if (xx != "突破"){
		    		cm.sendOk("请先突破。");
                    cm.dispose();
                    return;
                }
                cm.gainItem(4310034,-5);
                cm.gainItem(4001126,-400);
                var 四维属性 = Math.floor(Math.random()*6);//向下取整   生成0-5之间的整数   Math.random()为0-1的随机数
                var 攻魔 = Math.round(Math.random()*3);//四舍五入取整   取0-3的随机整数
		    	item.setFlag(1);
		    	item.setStr(sx0+四维属性);
		    	item.setDex(sx1+四维属性);
		    	item.setInt(sx2+四维属性);
		    	item.setLuk(sx3+四维属性);
		    	item.setHp(sx4);
                item.setMp(sx5);
                var RandomChance = Math.floor(Math.random()*101);
                if(RandomChance <= 30){
                    item.setWatk(sx6+攻魔);
                    item.setMatk(sx7+攻魔);
                }
		    	item.setWdef(sx8);
		    	item.setMdef(sx9);
		    	item.setAcc(sx10);
		    	item.setAvoid(sx11);
		    	item.setHands(sx12);
		    	item.setSpeed(sx13);
		    	item.setJump(sx14);
		    	item.setOwner("强化");
		    	cm.dispose();
		    	MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
		    	MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
		    	cm.ShowWZEffect("Effect/BasicEff.img/SkillBook/Success/0"); //成功
		    	cm.sendOk("强化成功！");
				cm.worldMessage(  " 【觉醒中心】" + " : " + "["+cm.getPlayer().getName()+"]某件装备成功强化！！")
                cm.dispose();
                return;
            }  else if (selection == 10) {
                cm.sendOk("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前等级为：#r"+xx+"#k\r\n全部四维：随机必加1\r\n#r\r\n攻魔 百分百概率添加0-2\r\n#r\n需要物品：\r\n#v4310034#*1#v4001126#*200\r\n");
                cm.dispose();
                return;
            }//突破介绍

			else if (selection == 11) {
                cm.sendOk("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前等级为：#r"+xx+"#k\r\n全部四维：随机加0-6\r\n#r\r\n攻魔 百分百概率添加0-3\r\n#r\n需要物品：\r\n#v4310034#*5#v4001126#*400\r\n");
                cm.dispose();
                return;
            }//强化介绍

			else if (selection == 12) {
                cm.sendOk("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前等级为：#r"+xx+"#k\r\n全部四维“随机必加2-5\r\n#r\r\n攻 百分10概率添加1~2、百分90概率不添加”\r\n#r\n需要物品：十字币X1下等五彩水晶X1祝福卷轴X1混沌卷轴X1枫叶X200\r\n\r\n");
                cm.dispose();
                return;
            } else if (selection == 13) {
                cm.sendOk("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前等级为：#r"+xx+"#k\r\n全部四维“随机必加2-5\r\n#r\r\n攻 百分10概率添加1~2、百分90概率不添加”\r\n#r\n需要物品：十字币X1下等五彩水晶X1祝福卷轴X1混沌卷轴X1枫叶X200\r\n\r\n");
                cm.dispose();
                return;
            } else if (selection == 14) {
                cm.sendOk("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前等级为：#r"+xx+"#k\r\n全部四维“随机必加2-5\r\n#r\r\n攻 百分10概率添加1~2、百分90概率不添加”\r\n#r\n需要物品：十字币X1下等五彩水晶X1祝福卷轴X1混沌卷轴X1枫叶X200\r\n\r\n");
                cm.dispose();
                return;
            } else if (selection == 15) {
                cm.sendOk("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前等级为：#r"+xx+"#k\r\n全部四维“随机必加2-5\r\n#r\r\n攻 百分10概率添加1~2、百分90概率不添加”\r\n#r\n需要物品：十字币X1下等五彩水晶X1祝福卷轴X1混沌卷轴X1枫叶X200\r\n\r\n");
                cm.dispose();
                return;
            } else if (selection == 16) {
                cm.sendOk("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前等级为：#r"+xx+"#k\r\n全部四维“随机必加2-5\r\n#r\r\n攻 百分10概率添加1~2、百分90概率不添加”\r\n#r\n需要物品：十字币X1下等五彩水晶X1祝福卷轴X1混沌卷轴X1枫叶X200\r\n\r\n");
                cm.dispose();
                return;
            } else if (selection == 17) {
                cm.sendOk("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前等级为：#r"+xx+"#k\r\n全部四维“随机必加2-5\r\n#r\r\n攻 百分10概率添加1~2、百分90概率不添加”\r\n#r\n需要物品：十字币X1下等五彩水晶X1祝福卷轴X1混沌卷轴X1枫叶X200\r\n\r\n");
                cm.dispose();
                return;
            } else if (selection == 18) {
                cm.sendOk("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前等级为：#r"+xx+"#k\r\n\r\n#r\r\n装备解锁：解除上锁后可变为能交易\r\n不可交易：可以在商城购买宿命剪刀使用后可变为能交易\r\n不可交换：为锁定永久不能交换和交易\r\n\r\n");
                cm.dispose();
                return;
            }
			
			else if (selection == 101) {
                cm.sendOk("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前等级为：#r"+xx+"#k\r\n\r\n\r\n#r突破和强化后的装备还可以进行觉醒哦\r\n觉醒分为初，中，高等觉醒\r\n觉醒初等的装备才可以参与中等觉醒，以此类推\r\n\r\n");
                cm.dispose();
                return;
        } else {
            cm.dispose();
            return;
        }

    }
}

function openNpc(npcid) {
    openNpc(npcid, null);
}

function openNpc(npcid, script) {
    var mapid = cm.getMapId();
    cm.dispose();
    if (cm.getPlayerStat("LVL") < 10) {
        cm.sendOk("你的等级不能小于10等.");
    } else if (
            cm.hasSquadByMap() ||
            cm.hasEventInstance() ||
            cm.hasEMByMap() ||
            mapid >= 990000000 ||
            (mapid >= 680000210 && mapid <= 680000502) ||
            (mapid / 1000 === 980000 && mapid !== 980000000) ||
            mapid / 100 === 1030008 ||
            mapid / 100 === 922010 ||
            mapid / 10 === 13003000
            ) {
        cm.sendOk("你不能在这里使用这个功能.");
    } else {
        if (script == null) {
            cm.openNpc(npcid);
        } else {
            cm.openNpc(npcid, script);
        }
    }
}