
var status = -1;
var 自己推广的人 =Array();
var type1 =0;
var xz1;
function start() {
    action(1, 0, 0);
}

function action(mode, type, selection) {
	if (status >= 0 && mode == 0) {
		//cm.sendOk("感谢使用！");
		cm.dispose();
		return;
	}
    if (mode == -1) {
        cm.dispose();
    } else {
        if (mode == 1) {
            status++;
        } else {
            status--;
        }
		if (status == 0) {
			var text ="";
			text +="\t\t"+彩虹+"  #e#d 推 广 奖 励 #k#n  #r  "+彩虹+"#b#k#n\r\r\n"+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+"\r\n";
			text +="                 #e#d我的推广码:#r"+cm.getPlayer().getId()+"#k            \r\n";//#d我的推广积分:#r"+推广积分()+"#k
			
			text +="              #d推广获得奖励点卷:#r"+推广奖励点卷()+"#k\r\n";
			text +="          #d我填写的推广人:#k#r"+(推广人()==0?"尚未填写推广人":""+读取名字(推广人())+"")+"#k\r\n";
			
			text +="#L1##b"+正方箭头+"查看我自己推广的人\r\n";
			text +="#L2#"+正方箭头+"领取推广奖励点卷#l             #L3#推广#l\r\n";
			text +="                                #L3#说明#l \r\n";//#L5#"+正方箭头+"积分兑换推广戒指#l
			if(推广人()==0){
			text +="#L4#"+正方箭头+"填写推广人\r\n";
			}else{
			text +="#L6#"+正方箭头+"更换推广人\r\n";	
			}
			cm.sendSimple(text);
		} else if (status == 1) {
			if(selection ==4){
			type1 =1;
			cm.sendGetNumber("请输入你推广人的ID,注意输入之后无法修改",1,1,210000000);
			}else if(selection ==6){
			type1 =6;
			cm.sendGetNumber("请输入你推广人的ID,注意只有一次机会啊,本次更换需要消费500w金币",1,1,210000000);
			}else if(selection ==1){
			if(读取自己推广的人(cm.getPlayer().getId())==0){
			cm.sendOk("哦吼吼,貌似没有一个人写的是你的推广");
			cm.dispose();
			return;
			}
			var text ="";
			text +="当前填写你的推广码的人有:\r\n";
			for(var a=0;a<自己推广的人.length;a++){
			text +="#k角色名字:#r"+读取名字(自己推广的人[a])+"\r\n";		
			}
			cm.sendOk(text);
			cm.dispose();
			return;	
			}else if(selection ==2){
			type1 =2;	
			var msg = "";
				msg += "当前可领取推广奖励点卷："+推广奖励点卷()+"\r\n"
				msg += "请输入充值点数量兑换点卷\r\n"
				cm.sendGetNumber(msg, 1,1, 推广奖励点卷());	
			}else if(selection ==5){
				cm.dispose();
				cm.openNpc(3003332,  "推广戒指");
			}
			else if(selection ==3){
				cm.sendOk("\t\t"+彩虹+"  #e#d 推 广 说 明 #k#n  #r  "+彩虹+"#b#k#n\r\r\n"+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+"\r\n每推广一个人,可获得1推广积分!您推广的玩家,每赞助1元,您可以获得50点卷,以此类推，累计增加，永久有效.");
			cm.dispose();
			return;
			}
		}else if(status ==2){
			xz1=selection;
			if(type1==1){
			if(cm.getPlayer().getId()==xz1){
			cm.sendOk("自己不能填写自己哦");
			cm.dispose();
			return;
			}
			if(cm.getPlayer().getAccountID()==判定账号(xz1)){
			cm.sendOk("不可以同一个账号自己填自己哦");
			cm.dispose();
			return;
			}
			if(cm.getOneTimeLog("推广系统1")<1){
			if(判定存在(xz1)==1){	
			cm.sendYesNo("你填写的推广人是:"+读取名字(xz1)+",确定是他么？");
			}else {
			cm.sendOk("貌似没有找到你输入的这个人哦");
			cm.dispose();
			return;	
			}
			}else {
			cm.sendOk("你已经填写过推广人了");
			cm.dispose();
			return;	
			}	
			}else if(type1==6){	
			if(cm.getPlayer().getId()==xz1){
			cm.sendOk("自己不能填写自己哦");
			cm.dispose();
			return;
			}
			if(cm.getPlayer().getAccountID()==判定账号(xz1)){
			cm.sendOk("不可以同一个账号自己填自己哦");
			cm.dispose();
			return;
			}
			/*if (cm.getPlayer().getLevel() > 120) {
				cm.sendOk("你超过120级,不可以更改推广人");
			    cm.dispose();
			    return;
			} */
			if(cm.getMeso()<8000000){
			cm.sendOk("你貌似没有那么多钱");
			cm.dispose();
			return;
			}
			if(cm.getOneTimeLog("推广系统2")<1){
			if(判定存在(xz1)==1){	
			cm.sendYesNo("你填写的推广人是:"+读取名字(xz1)+",确定是他么？");
			}else {
			cm.sendOk("貌似没有找到你输入的这个人哦");
			cm.dispose();
			return;	
			}
			}else {
			cm.sendOk("你已经更换过推广人了");
			cm.dispose();
			return;	
			}





			
			}else if(type1==2){
			if (推广奖励点卷() >= xz1) {
					扣除推广奖励点卷(xz1);
					cm.gainNX(xz1);
					cm.sendOk("兑换成功");
                    cm.dispose();
				} else {
					cm.sendOk("推广奖励点卷不足无法兑换");
                    cm.dispose();
				}	
				
				
			}
		}else if(status ==3){
			
		if(type1 ==1){
		var 角色id = cm.getPlayer().getId();	
		输入推广(角色id,xz1);
		//获取积分(xz1,1);
		cm.setOneTimeLog("推广系统1");
		cm.sendOk("填写成功");
		cm.dispose();
		}else if(type1 ==6){
		var 角色id = cm.getPlayer().getId();
		cm.gainMeso(-8000000);
		输入推广(角色id,xz1);
		//获取积分(xz1,1);
		cm.setOneTimeLog("推广系统2");
		cm.sendOk("填写成功");
		cm.dispose();	
			
		}	
			
			
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