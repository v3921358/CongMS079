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
importPackage(Packages.server);
importPackage(Packages.tools);
var 小黄星 = "#fItem/Etc/0427/04270001/Icon9/0#";
var 中条猫 ="#fUI/ChatBalloon/37/n#";
var 红色箭头 = "#fUI/UIWindow/Quest/icon6/7#";
var zzz = "#fUI/UIWindow.img/Quest/icon8/0#";//蓝色右箭头
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
        cm.sendSimple("#r#e             "+小黄星+"   #e#d觉 醒  列 表#k#n   "+小黄星+"  #k#n\r\n  "+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+"\r\n\r\n" +
                "#L2##r"+红色箭头+"初等觉醒+功#l#k #L12##b"+zzz+"介绍#l#k\r\n" +
                "#L3##r"+红色箭头+"初等觉醒+魔#l#k #L13##b"+zzz+"介绍#l#k\r\n" +
                "#L4##r"+红色箭头+"中等觉醒+功#l#k #L14##b"+zzz+"介绍#l#k\r\n" +
                "#L5##r"+红色箭头+"中等觉醒+魔#l#k #L15##b"+zzz+"介绍#l#k\r\n" +
                "#L6##r"+红色箭头+"高等觉醒+功#l#k #L16##b"+zzz+"介绍#l#k\r\n" +
                "#L7##r"+红色箭头+"高等觉醒+魔#l#k #L17##b"+zzz+"介绍#l#k\r\n" +

                "\r\n");

    } else if (status == 1) {
		
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
        if (cm.getInventory(1).getItem(1).getUniqueId() > 0) {
            cm.sendOk("现金装备无法升星。");
            cm.dispose();
            return;
        }
        if (Math.floor(item.getItemId()/10000) == 114 || Math.floor(item.getItemId()/10000) == 190 || Math.floor(item.getItemId()/10000) == 111 || Math.floor(item.getItemId()/10000) ==191) {
            cm.sendOk("第一格装备是#v" + item.getItemId() + "#,无法觉醒的装备类型!"); 		
            cm.dispose();    
            return;
        }
               if (selection == 2) {
                if(!cm.haveItem(4310029,8)){
                    cm.sendOk("#v4310029#物品数量不足8个！");
                    cm.dispose();
                    return;
                }
                if(!cm.haveItem(2340000,5)){
                    cm.sendOk("#v2340000#物品数量不足5个！");
                    cm.dispose();
                    return;
                }
                if(!cm.haveItem(4001126,500)){
                    cm.sendOk("#v4001126#物品数量不足500个！");
                    cm.dispose();
                    return;
                }
                if (xx != "强化"){
		    		cm.sendOk("该装备无法觉醒。");
                    cm.dispose();
                    return;
                }
                cm.gainItem(4310029,-8);
                cm.gainItem(2340000,-5);
                cm.gainItem(4001126,-500);
                var max = 5;
                var min = 2
                var 四维属性 = Math.floor(Math.random()*(max-min+1)+min);
                var gmmax = 2;
                var gmmin = 1;
                var 攻魔 = Math.floor(Math.random()*(gmmax-gmmin+1)+gmmin);
                item.setFlag(1);
		    	item.setStr(sx0+四维属性);
		    	item.setDex(sx1+四维属性);
		    	item.setInt(sx2+四维属性);
		    	item.setLuk(sx3+四维属性);
		    	item.setHp(sx4);
                item.setMp(sx5);
                var RandomChance = Math.floor(Math.random()*101);
                if(RandomChance <= 30){
                    item.setWatk(sx6+攻魔);//功
                    item.setMatk(sx7);//魔
                }
		    	item.setWdef(sx8);
		    	item.setMdef(sx9);
		    	item.setAcc(sx10);
		    	item.setAvoid(sx11);
		    	item.setHands(sx12);
		    	item.setSpeed(sx13);
		    	item.setJump(sx14);
		    	item.setOwner("初等觉醒");
		    	cm.dispose();
		    	MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
		    	MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
                cm.ShowWZEffect("Effect/BasicEff.img/SkillBook/Success/0"); //成功
                cm.worldMessage(  " 【升星公告】" + " : " + "["+cm.getPlayer().getName()+"]某件装备成功初等觉醒！大家祝贺他(她)吧！！")
		    	cm.sendOk("觉醒成功！");
		    	
                cm.dispose();
                return;
            } else if (selection == 3) {
                if(!cm.haveItem(4310029,8)){
                    cm.sendOk("#v4310029#物品数量不足8个！");
                    cm.dispose();
                    return;
                }
                if(!cm.haveItem(2340000,5)){
                    cm.sendOk("#v2340000#物品数量不足5个！");
                    cm.dispose();
                    return;
                }
                if(!cm.haveItem(4001126,500)){
                    cm.sendOk("#v4001126#物品数量不足500个！");
                    cm.dispose();
                    return;
                }
                if (xx != "强化"){
		    		cm.sendOk("该装备无法觉醒。");
                    cm.dispose();
                    return;
                }
                cm.gainItem(4310029,-8);
                cm.gainItem(2340000,-5);
                cm.gainItem(4001126,-500);
                var max = 5;
                var min = 2
                var 四维属性 = Math.floor(Math.random()*(max-min+1)+min);
                var gmmax = 2;
                var gmmin = 1;
                var 攻魔 = Math.floor(Math.random()*(gmmax-gmmin+1)+gmmin);
                item.setFlag(1);
		    	item.setStr(sx0+四维属性);
		    	item.setDex(sx1+四维属性);
		    	item.setInt(sx2+四维属性);
		    	item.setLuk(sx3+四维属性);
		    	item.setHp(sx4);
                item.setMp(sx5);
                var RandomChance = Math.floor(Math.random()*101);
                if(RandomChance <= 30){
                    item.setWatk(sx6);
                    item.setMatk(sx7+攻魔);
                }
		    	item.setWdef(sx8);
		    	item.setMdef(sx9);
		    	item.setAcc(sx10);
		    	item.setAvoid(sx11);
		    	item.setHands(sx12);
		    	item.setSpeed(sx13);
		    	item.setJump(sx14);
		    	item.setOwner("初等觉醒");
		    	cm.dispose();
		    	MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
		    	MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
		    	cm.ShowWZEffect("Effect/BasicEff.img/SkillBook/Success/0"); //成功
		    	cm.sendOk("觉醒成功！");
		    	cm.worldMessage(  " 【升星公告】" + " : " + "["+cm.getPlayer().getName()+"]某件装备成功初等觉醒！大家祝贺他(她)吧！！")
                cm.dispose();
                return;
            } else if (selection == 4) {
                if(!cm.haveItem(4310029,10)){
                    cm.sendOk("#v4310029#物品数量不足10个！");
                    cm.dispose();
                    return;
                }
                if(!cm.haveItem(2340000,10)){
                    cm.sendOk("#v2340000#物品数量不足10个！");
                    cm.dispose();
                    return;
                }
                if(!cm.haveItem(4001126,1000)){
                    cm.sendOk("#v4001126#物品数量不足1000个！");
                    cm.dispose();
                    return;
                }
                if (xx != "初等觉醒"){
		    		cm.sendOk("该装备未初等觉醒，无法中等觉醒。");
                    cm.dispose();
                    return;
                }

                cm.gainItem(4310029,-10);
                cm.gainItem(2340000,-10);
                cm.gainItem(4001126,-1000);
                var swmax = 6;
                var swmin = 3;
                var 四维属性 = Math.floor(Math.random()*(swmax-swmin+1)+swmin);
                var gmmax = 5;
                var gmmin = 2;
                var 攻魔 = Math.floor(Math.random()*(gmmax-gmmin+1)+gmmin);
                item.setFlag(1);
		    	item.setStr(sx0+四维属性);
		    	item.setDex(sx1+四维属性);
		    	item.setInt(sx2+四维属性);
		    	item.setLuk(sx3+四维属性);
		    	item.setHp(sx4);
                item.setMp(sx5);
                var RandomChance = Math.floor(Math.random()*101);
                if(RandomChance <= 50){
                    item.setWatk(sx6+攻魔);
                    item.setMatk(sx7);
                }
		    	item.setWdef(sx8);
		    	item.setMdef(sx9);
		    	item.setAcc(sx10);
		    	item.setAvoid(sx11);
		    	item.setHands(sx12);
		    	item.setSpeed(sx13);
		    	item.setJump(sx14);
		    	item.setOwner("中等觉醒");
		    	cm.dispose();
		    	MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
		    	MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
		    	cm.ShowWZEffect("Effect/BasicEff.img/SkillBook/Success/0"); //成功
		    	cm.sendOk("觉醒成功！");
		    	cm.worldMessage(  " 【升星公告】" + " : " + "["+cm.getPlayer().getName()+"]某件装备成功中等觉醒！大家祝贺他(她)吧！！")
                cm.dispose();
                return;
            } else if (selection == 5) {
                if(!cm.haveItem(4310029,10)){
                    cm.sendOk("#v4310029#物品数量不足10个！");
                    cm.dispose();
                    return;
                }
                if(!cm.haveItem(2340000,10)){
                    cm.sendOk("#v2340000#物品数量不足10个！");
                    cm.dispose();
                    return;
                }
                if(!cm.haveItem(4001126,1000)){
                    cm.sendOk("#v4001126#物品数量不足1000个！");
                    cm.dispose();
                    return;
                }
                if (xx != "初等觉醒"){
		    		cm.sendOk("该装备未初等觉醒，无法中等觉醒。");
                    cm.dispose();
                    return;
                }
                cm.gainItem(4310029,-10);
                cm.gainItem(2340000,-10);
                cm.gainItem(4001126,-1000);
                var swmax = 6;
                var swmin = 3;
                var 四维属性 = Math.floor(Math.random()*(swmax-swmin+1)+swmin);
                var gmmax = 5;
                var gmmin = 2;
                var 攻魔 = Math.floor(Math.random()*(gmmax-gmmin+1)+gmmin);
                item.setFlag(1);
		    	item.setStr(sx0+四维属性);
		    	item.setDex(sx1+四维属性);
		    	item.setInt(sx2+四维属性);
		    	item.setLuk(sx3+四维属性);
		    	item.setHp(sx4);
                item.setMp(sx5);
                var RandomChance = Math.floor(Math.random()*101);
                if(RandomChance <= 50){
                    item.setWatk(sx6);
                    item.setMatk(sx7+攻魔);
                }
		    	item.setWdef(sx8);
		    	item.setMdef(sx9);
		    	item.setAcc(sx10);
		    	item.setAvoid(sx11);
		    	item.setHands(sx12);
		    	item.setSpeed(sx13);
		    	item.setJump(sx14);
		    	item.setOwner("中等觉醒");
		    	cm.dispose();
		    	MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
		    	MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
		    	cm.ShowWZEffect("Effect/BasicEff.img/SkillBook/Success/0"); //成功
		    	cm.sendOk("觉醒成功！");
		    	cm.worldMessage(  " 【升星公告】" + " : " + "["+cm.getPlayer().getName()+"]某件装备成功中等觉醒！大家祝贺他(她)吧！！")
                cm.dispose();
                return;
            } else if (selection == 6) {
                if(!cm.haveItem(4310029,12)){
                    cm.sendOk("#v4310029#物品数量不足12个！");
                    cm.dispose();
                    return;
                }
                if(!cm.haveItem(2340000,12)){
                    cm.sendOk("#v2340000#物品数量不足12个！");
                    cm.dispose();
                    return;
                }
                if(!cm.haveItem(4001126,2000)){
                    cm.sendOk("#v4001126#物品数量不足2000个！");
                    cm.dispose();
                    return;
                }
                if (xx != "中等觉醒"){
		    		cm.sendOk("该装备未中等觉醒，无法高等觉醒。");
                    cm.dispose();
                    return;
                }
                cm.gainItem(4310029,-12);
                cm.gainItem(2340000,-12);
                cm.gainItem(4001126,-2000);
                var swmax = 7;
                var swmin = 4;
                var 四维属性 = Math.floor(Math.random()*(swmax-swmin+1)+swmin);
                var gmmax = 5;
                var gmmin = 3;
                var 攻魔 = Math.floor(Math.random()*(gmmax-gmmin+1)+gmmin);
                item.setFlag(1);
		    	item.setStr(sx0+四维属性);
		    	item.setDex(sx1+四维属性);
		    	item.setInt(sx2+四维属性);
		    	item.setLuk(sx3+四维属性);
		    	item.setHp(sx4);
                item.setMp(sx5);
                var RandomChance = Math.floor(Math.random()*101);
                if(RandomChance <= 10){
                    item.setWatk(sx6+攻魔);
                    item.setMatk(sx7);
                }
		    	item.setWdef(sx8);
		    	item.setMdef(sx9);
		    	item.setAcc(sx10);
		    	item.setAvoid(sx11);
		    	item.setHands(sx12);
		    	item.setSpeed(sx13);
		    	item.setJump(sx14);
		    	item.setOwner("高等觉醒");
		    	cm.dispose();
		    	MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
		    	MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
		    	cm.ShowWZEffect("Effect/BasicEff.img/SkillBook/Success/0"); //成功
		    	cm.sendOk("觉醒成功！");
		    	cm.worldMessage(  " 【升星公告】" + " : " + "["+cm.getPlayer().getName()+"]某件装备成功高等觉醒！大家祝贺他(她)吧！！")
                cm.dispose();
                return;
            } else if (selection == 7) {
                if(!cm.haveItem(4310029,12)){
                    cm.sendOk("#v4310029#物品数量不足12个！");
                    cm.dispose();
                    return;
                }
                if(!cm.haveItem(2340000,12)){
                    cm.sendOk("#v2340000#物品数量不足12个！");
                    cm.dispose();
                    return;
                }
                if(!cm.haveItem(4001126,2000)){
                    cm.sendOk("#v4001126#物品数量不足2000个！");
                    cm.dispose();
                    return;
                }
                if (xx != "中等觉醒"){
		    		cm.sendOk("该装备未中等觉醒，无法高等觉醒。");
                    cm.dispose();
                    return;
                }
                cm.gainItem(4310029,-12);
                cm.gainItem(2340000,-12);
                cm.gainItem(4001126,-2000);
                var swmax = 7;
                var swmin = 4;
                var 四维属性 = Math.floor(Math.random()*(swmax-swmin+1)+swmin);
                var gmmax = 5;
                var gmmin = 3;
                var 攻魔 = Math.floor(Math.random()*(gmmax-gmmin+1)+gmmin);
                item.setFlag(1);
		    	item.setStr(sx0+四维属性);
		    	item.setDex(sx1+四维属性);
		    	item.setInt(sx2+四维属性);
		    	item.setLuk(sx3+四维属性);
		    	item.setHp(sx4);
                item.setMp(sx5);
                var RandomChance = Math.floor(Math.random()*101);
                if(RandomChance <= 10){
                    item.setWatk(sx6);
                    item.setMatk(sx7+攻魔);
                }
		    	item.setWdef(sx8);
		    	item.setMdef(sx9);
		    	item.setAcc(sx10);
		    	item.setAvoid(sx11);
		    	item.setHands(sx12);
		    	item.setSpeed(sx13);
		    	item.setJump(sx14);
		    	item.setOwner("高等觉醒");
		    	cm.dispose();
		    	MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
		    	MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
		    	cm.ShowWZEffect("Effect/BasicEff.img/SkillBook/Success/0"); //成功
		    	cm.sendOk("觉醒成功！");
		    	cm.worldMessage(  " 【升星公告】" + " : " + "["+cm.getPlayer().getName()+"]某件装备成功高等觉醒！大家祝贺他(她)吧！！")
                cm.dispose();
                return;
            }  
			else if (selection == 12) {//初等觉醒+功
                cm.sendOk("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前等级为：#r"+xx+"#k\r\n全部四维：随机必加2-5\r\n#r\r\n攻 一定概率添加0-2\r\n#r\n需要物品：\r\n#k[#v4310029##r#c4310029##k/8][#v2340000##r#c2340000##k/5][#v4001126##r#c4001126##k/500]\r\n\r\n");
                cm.dispose();
                return;
            }
			
			else if (selection == 13) {//初等觉醒+魔
                cm.sendOk("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前等级为：#r"+xx+"#k\r\n全部四维：随机必加2-5\r\n#r\r\n魔 一定概率添加0-2\r\n#r\n需要物品：\r\n#k[#v4310029##r#c4310029##k/8][#v2340000##r#c2340000##k/5][#v4001126##r#c4001126##k/500]\r\n\r\n");
                cm.dispose();
                return;
            }
			
			else if (selection == 14) {//中等觉醒+功
                cm.sendOk("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前等级为：#r"+xx+"#k\r\n全部四维：随机必加3-6\r\n#r\r\n攻 一定概率添加0-5\r\n#r\n需要物品：：\r\n#k[#v4310029##r#c4310029##k/10][#v2340000##r#c2340000##k/10][#v4001126##r#c4001126##k/1000]\r\n\r\n");
                cm.dispose();
                return;
            } 
			
			else if (selection == 15) {//中等觉醒+魔
                cm.sendOk("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前等级为：#r"+xx+"#k\r\n全部四维：随机必加3-6\r\n#r\r\n魔 一定概率添加0-5\r\n#r\n需要物品：：\r\n#k[#v4310029##r#c4310029##k/10][#v2340000##r#c2340000##k/10][#v4001126##r#c4001126##k/1000]\r\n\r\n");
                cm.dispose();
                return;
            } 
			
			else if (selection == 16) {//高等觉醒+功
                cm.sendOk("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前等级为：#r"+xx+"#k\r\n全部四维：随机必加4-7\r\n#r\r\n攻 高概率添加0-5\r\n#r\n需要物品：：\r\n#k[#v4310029##r#c4310029##k/12][#v2340000##r#c2340000##k/12][#v4001126##r#c4001126##k/2000]\r\n\r\n");
                cm.dispose();
                return;
            }

			else if (selection == 17) {//高等觉醒+魔
                cm.sendOk("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前等级为：#r"+xx+"#k\r\n全部四维：随机必加4-7\r\n#r\r\n魔 高概率添加0-5\r\n#r\n需要物品：：\r\n#k[#v4310029##r#c4310029##k/12][#v2340000##r#c2340000##k/12][#v4001126##r#c4001126##k/2000]\r\n\r\n");
                cm.dispose();
                return;
            }

			else if (selection == 100) {
                cm.dispose();
                cm.openNpc(NPCID_1);
                return;
            } else if (selection == 101) {
                cm.sendOk("您选择的装备是：#v"+cm.getInventory(1).getItem(1).getItemId()+"##t"+cm.getInventory(1).getItem(1).getItemId()+"#\r\n\r\n当前等级为：#r"+xx+"#k\r\n全部四维“随机必加2-5\r\n#r\r\n攻 百分10概率添加1~2、百分90概率不添加”\r\n#r\n需要物品：十字币X1下等五彩水晶X1祝福卷轴X1混沌卷轴X1枫叶X200\r\n\r\n");
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