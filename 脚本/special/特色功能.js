
var CY0 = "┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓";
var CY1 = "┃       - 创意 -       ┃";
var CY2 = "┃ 脚本仿制  　定制脚本 ┃";
var CY3 = "┃ 技术支持 　 游戏顾问 ┃";
var CY4 = "┃ ＷＺ添加　  地图制作 ┃";
var CY5 = "┃ 加盾防御　  售登陆器 ┃";
var CY6 = "┃ 端游开服　  手游开服 ┃";
var CY7 = "┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫";
var CY8 = "┃   唯一QQ:12384161    ┃";
var CY9 = "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛";

var 中条猫 ="#fUI/ChatBalloon/37/n#";
var 猫右 =  "#fUI/ChatBalloon/37/ne#";
var 猫左 =  "#fUI/ChatBalloon/37/nw#";
var 右 =    "#fUI/ChatBalloon/37/e#";
var 左 =    "#fUI/ChatBalloon/37/w#";
var 下条猫 ="#fUI/ChatBalloon/37/s#";
var 猫下右 ="#fUI/ChatBalloon/37/se#";
var 猫下左 ="#fUI/ChatBalloon/37/sw#";
var 皇冠白 ="#fUI/GuildMark/Mark/Etc/00009004/16#";
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
			text += ""
			text += "                 #k"+皇冠白+" #r#e#w 特 色 功 能 #n#k "+皇冠白+"\r\n";
			text += "  "+猫左+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+猫右+"\r\n";
			
			text += "#L1##k- - - - -[9800点卷]购买#v4310058#30天- - - - -#l\r\n\r\n\t\t#d可以使任何宠物触发全屏吸取金币和物品#l\r\n\r\n"
			
			text += "#L2##k- - - - -[9800点卷]购买#v3994765#30天- - - - -#l\r\n\r\n\t\t#d可以使携带的任何宠物一直不会饥饿#l\r\n\r\n"
			
			text += "#L3##k- - - - -[18000点卷]购买#i1122017:#30天- - - - -#l\r\n\r\n\t\t#d装备后可以打怪获得额外经验#l\r\n\r\n"
			
			//text += "#L4##k#b[10元宝]删除角色#l           #L5##k#b[20元宝]增加角色#l\r\n\r\n"
            cm.sendSimple(text);
		}else if(status ==1){
			
		if (selection == 1){ 
			if (!cm.checkNumSpace(4, 1)) {
			cm.sendOk("背包其他栏空间不足1格");
			cm.dispose();
			return;
		    }
		if(cm.getPlayer().getCSPoints(1)>=9800 && cm.getPlayer().getItemQuantity(4310058,false)<1){
				cm.gainNX(-9800);
				cm.gainItemPeriod(4310058,1,30*24);
				cm.sendOk("购买成功");
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『特色功能』" + " : " + "[" + cm.getChar().getName() + "]通过购买物品，获得了30天宠吸功能！")); 
				cm.dispose();
				return;
		}else {
			cm.sendOk("点卷不足9800,或者你已经有这个东西了");
			cm.dispose();
			return;
		    }
		}
			
		 if (selection == 2) {
			if (!cm.checkNumSpace(3, 1)) {
			cm.sendOk("背包其他栏空间不足1格");
			cm.dispose();
			return;
		}
			if(cm.getPlayer().getCSPoints(1)>=9800 && cm.getPlayer().getItemQuantity(3994765,false)<1){
				cm.gainNX(-9800);
				cm.gainItemPeriod(3994765,1,30*24);
				cm.sendOk("购买成功");
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『特色功能』" + " : " + "[" + cm.getChar().getName() + "]通过购买物品，获得了30天宠物零饥饿功能！")); 
				cm.dispose();
				return;
			}else {
			cm.sendOk("点卷不足9800,或者你已经有这个东西了");
			cm.dispose();
			return;
			}
		}if (selection == 3){ 
			if (!cm.checkNumSpace(1, 1)) {
			cm.sendOk("背包装备栏空间不足1格");
			cm.dispose();
			return;
		}if(cm.getPlayer().getCSPoints(1)>=18000 && cm.getPlayer().getItemQuantity(1122017,false)<1){
				cm.gainNX(-18000);
				cm.gainItemPeriod(1122017,1,30*24);
				cm.sendOk("购买成功");
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『特色功能』" + " : " + "[" + cm.getChar().getName() + "]通过购买物品，获得了30天精灵吊坠！")); 
				cm.dispose();
				return;
			}else {
			cm.sendOk("点卷不足18000,或者你已经有这个东西了");
			cm.dispose();
			return;
			}
            }
		if (selection == 4){
			cm.dispose();
            cm.openNpc(9310072, "删除角色");
			
		}
		if (selection == 5){
			cm.dispose();
            cm.openNpc(9310072, "增加角色");
			
		}
		}	
        }
    }
	
	function getmoneyb() {
	accid = cm.getPlayer().getAccountID();
	xmfhz = 0;
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT * FROM accounts WHERE id = "+accid+"   ;";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();
	if (result.next()) {
	xmfhz = result.getString("moneyb");
	}
	result.close();
	pstmt.close();	
	return xmfhz;
}
function setmoneyb(xiezhi) {
	accid = cm.getPlayer().getAccountID();
    var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "UPDATE accounts SET moneyb = moneyb+"+xiezhi+"  WHERE id = "+accid+"  ;";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
}
