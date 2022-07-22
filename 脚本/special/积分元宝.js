var CY0 = "┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓";
var CY1 = "┃       - 创意 -       ┃";
var CY2 = "┃ 脚本仿制  　定制脚本 ┃";
var CY3 = "┃ 技术支持 　 游戏顾问 ┃";
var CY4 = "┃ ＷＺ添加　  地图制作 ┃";
var CY5 = "┃ 加盾防御　  售登陆器 ┃";
var CY6 = "┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫";
var CY7 = "┃   唯一QQ:12384161    ┃";
var CY8 = "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛";
var 四方印 = "#fUI/GuildMark/Mark/Pattern/00004014/11#"; // 紫色四方印
var status = -1;
var beauty = 0;
var tosend = 0;
var sl;
var mats;
var xx = -1;
var jiage = -1;
var 每次突破 = 200000;
var 最高突破等级 = 250;
	function start() {
		status = -1;
		action(1, 0, 0);
		}


	function action(mode, type, selection) {
		if (mode == -1) {
		cm.sendOk("感谢有你！");
		cm.dispose();
		} else {

	if (mode == 0) {
		cm.sendOk("感谢有你！");
        	cm.dispose();
        	return;
    		}

	if (mode == 1) {
		status++;
		} else {
		status--;
		}
        if (status == 0) {
			
            var gsjb = "";
				gsjb = ""+四方印+四方印+四方印+四方印+四方印+四方印+四方印+四方印+四方印+四方印+四方印+四方印+四方印+四方印+四方印+四方印+四方印+四方印+四方印+四方印+四方印+"\r\n\r\n";

				gsjb += "            #L1##e#b1000积分兑换500点卷#r[Hot]#l";
            cm.sendSimple(gsjb);
        } else if (status == 1) {  
		if (selection == 1) {
			if(cm.getPlayer().getjf3()>= 1000){ 
                    cm.getPlayer().setjf3(cm.getPlayer().getjf3()-1000);
					cm.gainNX(500);
					cm.sendOk("成功用回收积分兑换了点卷");
					
					cm.dispose();
                } else {
                    cm.sendNext("您拥有的回收积分数量不足1000");
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
	var sql = "UPDATE accounts SET moneyb = "+xiezhi+"  WHERE id = "+accid+"  ;";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
}
