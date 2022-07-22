var 彩虹 ="#fEffect/ItemEff/1071085/effect/walk1/2#";
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
			text +=  "    \t"+彩虹+"  #e#d 专 署 技 能 #k#n  #r  "+彩虹+"#b#k#n\r\r\n";
			text += "#d哈喽！有没有羡慕过别人的影分身翻倍伤害呢？\r\n如果有，给我1778元宝就可以让你的影分身伤害提高一倍哦！#k\r\n"
			//text += "\t#L100##k[#v4170005##r#c4170005##k/35]兑换#d#fUI/Basic/BtHide3/mouseOver/0##v1912000##l\r\n\r\n"
			text += "\t#L101##k[1778元宝]兑换#v3994720#永久使用#l\r\n\r\n"
            cm.sendSimple(text);
		}else if(status ==1){
			if (!cm.checkNumSpace(4, 1)) {
			cm.sendOk("背包其他栏空间不足1格");
			cm.dispose();
			return;
		    }
			var jobid=cm.getPlayer().getJob();
			if (jobid==412||jobid==1411){

			if(getmoneyb()>=1778 && cm.getPlayer().getItemQuantity(3994720,false)<1){
				setmoneyb(-1778);
				cm.gainItem(3994720,1);
				cm.sendOk("购买成功");
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『飞侠专署』" + " : " + "[" + cm.getChar().getName() + "]通过托尼老师，获得了永久影分身伤害翻倍功能！")); 
				cm.dispose();
				return;
			}else {
			cm.sendOk("元宝不足1778,或者你已经有这个东西了");
			cm.dispose();
			return;
			}
			}else {
			cm.sendOk("亲！这里是飞侠和暗影者买了才有效果得哟！");
			cm.dispose();
			return;
			}

		 if (selection == 100) {
			if(cm.haveItem(4170005,35) ){
				cm.gainItem(4170005,-35);
				cm.gainItem(1912000,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『兑换中心』" + " : " + "[" + cm.getChar().getName() + "]通过兑换物品，获得了坐骑鞍子！")); 
				cm.dispose();
			}else{
				cm.sendOk("\t材料不足。");
				cm.dispose();
			}
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
