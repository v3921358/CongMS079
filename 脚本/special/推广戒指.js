/*麒麟端作者qq1500663066或327321366*/



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
			text +="\t\t"+彩虹+"  #e#d 推 广 奖 励 #k#n  #r  "+彩虹+"#b#k#n\r\r\n"+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+"\r\n";
			text +="    #e#d我的推广码:#r"+cm.getPlayer().getId()+"#k            #d我的推广积分:#r"+推广积分()+"#k\r\n";
			text += "\t#L1##k[#r金币1000万#k]+[#r"+推广积分()+"#k/10]积分兑换#d#fUI/Basic/BtHide3/mouseOver/0##i1114314:##l\r\n\r\n"
			text += "\t#L2##k#v1114314##k[百分百成功]升星#d#fUI/Basic/BtHide3/mouseOver/0##l\r\n\r\n"
			
            cm.sendSimple(text);
		}
		else if (selection == 1) {
			if (cm.getMeso() < 2000000 ) { 
			cm.sendOk("\t金币不足1000万。");
			cm.dispose();
			return;
			}
			if (!cm.checkNumSpace(1, 2)) {
			cm.sendOk("背包装备栏空间不足2格");
			cm.dispose();
			return;
		    }
			if (推广积分()<10) { 
			cm.sendOk("\t推广积分不足10分。");
			cm.dispose();
			return;
			}
			if(cm.haveItem(1114314,1)){
			cm.sendOk("\t您已经有了推广戒指，一人只能有一个。");
			cm.dispose();
			return;
			}
			扣除推广积分(10);
			cm.gainItem(1114314,1);
			Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『推广中心』" + " : " + "[" + cm.getChar().getName() + "]通过兑换积分，获得了一个积分戒指！")); 
			cm.dispose();
			
		}
		else if (selection == 2) {
			cm.dispose();
			cm.openNpc(3003332,  "推广戒指升星");
		}
		
		
		
		

		   
    }
}
function 扣除推广奖励点卷(uit) {
	var uid = cm.getPlayer().getId();
    var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "UPDATE tuiguangjf SET dj = dj-"+uit+"  WHERE ownid = "+uid+"";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
}
function 扣除推广积分(uit) {
	var uid = cm.getPlayer().getId();
    var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "UPDATE tuiguangjf SET jf = jf-"+uit+"  WHERE ownid = "+uid+"";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
}

function 推广人(){
	var 角色id = cm.getPlayer().getId();
	var 数值 =0;
	var 数值1 =1;
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT ownid,objecid FROM invsystem WHERE ownid = "+角色id+"   ;";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();
	if (result.next()) {
	数值 = result.getInt("objecid");
	数值1=0;
	}
	result.close();
	pstmt.close();
	if(数值1==1){
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "INSERT INTO invsystem(id,ownid,objecid) value(null,"+角色id+",0)";
	var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
	}
	return 数值;	
	
}
function 判定账号(uid){
	var 数值1 =0;
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT id,accountid FROM characters WHERE id = "+uid+"";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();
	if (result.next()) {
	数值1=result.getInt("accountid");
	}
	result.close();
	pstmt.close();	
	return 数值1;
}
function 判定存在(uid){
	var 数值1 =0;
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT id FROM characters WHERE id = "+uid+"";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();
	if (result.next()) {
	数值1=1;
	}
	result.close();
	pstmt.close();	
	return 数值1;
}

function 读取名字(uid){
	var 数值1 =0;
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT id,name FROM characters WHERE id = "+uid+"";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();
	if (result.next()) {

	数值1=result.getString("name");
	
	}
	result.close();
	pstmt.close();	
	return 数值1;
}

function 推广积分(){
	var 角色id = cm.getPlayer().getId();
	var 数值 =0;
	var 数值1 =1;
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT ownid,jf FROM tuiguangjf WHERE ownid = "+角色id+"";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();
	if (result.next()) {
	数值 = result.getInt("jf");
	数值1=0;
	}
	result.close();
	pstmt.close();
	if(数值1==1){
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "INSERT INTO tuiguangjf(id,ownid,jf,dj) value(null,"+角色id+",0,0)";
	var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
	}
	return 数值;	
	
}
function 推广奖励点卷(){
	var 角色id = cm.getPlayer().getId();
	var 数值 =0;
	var 数值1 =1;
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT ownid,dj FROM tuiguangjf WHERE ownid = "+角色id+"";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();
	if (result.next()) {
	数值 = result.getInt("dj");
	数值1=0;
	}
	result.close();
	pstmt.close();
	if(数值1==1){
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "INSERT INTO tuiguangjf(id,ownid,jf,dj) value(null,"+角色id+",0,0)";
	var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
	}
	return 数值;	
	
}
function 获取积分(uid,uit) {
    var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "UPDATE tuiguangjf SET jf = jf+"+uit+"  WHERE ownid = "+uid+"";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
}

function 输入推广(uid,uit) {
	accid = cm.getPlayer().getAccountID();
    var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "UPDATE invsystem SET objecid = "+uit+"  WHERE ownid = "+uid+"";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
}

function 读取自己推广的人(uid) {
	自己推广的人=Array();
	var 读取的人物;
	var 数值 =0;
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT ownid,objecid FROM invsystem WHERE objecid = "+uid+"";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();
	while (result.next()) {
	读取的人物=result.getInt("ownid");
	var conn1 = Packages.database.DatabaseConnection.getConnection();
	var sql1 = "SELECT id FROM characters WHERE id = "+读取的人物+"";
	var pstmt1 = conn1.prepareStatement(sql1);
	var result1 = pstmt1.executeQuery();
	while (result1.next()) {
	自己推广的人.push(result1.getInt("id"));
	数值++;	
	}
	result1.close();
	pstmt1.close();	
	}
	result.close();
	pstmt.close();	
	return 数值;



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
function gainmoneyb(xiezhi) {
	accid = cm.getPlayer().getAccountID();
    var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "UPDATE accounts SET moneyb = moneyb+"+xiezhi+"  WHERE id = "+accid+"  ;";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
}
var 正方箭头 = "#fUI/Basic/BtHide3/mouseOver/0#";
var 花草 ="#fEffect/SetEff/208/effect/walk2/4#";
var 花草1 ="#fEffect/SetEff/208/effect/walk2/3#";
var 小花 ="#fMap/MapHelper/weather/birthday/2#";
var 桃花 ="#fMap/MapHelper/weather/rose/4#";
var 金枫叶 ="#fMap/MapHelper/weather/maple/2#";
var 红枫叶 ="#fMap/MapHelper/weather/maple/1#";
var 彩虹 ="#fEffect/ItemEff/1071085/effect/walk1/2#";
var 中条猫 ="#fUI/ChatBalloon/37/n#";
var 猫右 =  "#fUI/ChatBalloon/37/ne#";
var 猫左 =  "#fUI/ChatBalloon/37/nw#";
var 右 =    "#fUI/ChatBalloon/37/e#";
var 左 =    "#fUI/ChatBalloon/37/w#";
var 下条猫 ="#fUI/ChatBalloon/37/s#";
var 猫下右 ="#fUI/ChatBalloon/37/se#";
var 猫下左 ="#fUI/ChatBalloon/37/sw#";