var CY0 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
var CY1 = "┣       - 创意 -       ┫";
var CY2 = "┣ 玩法仿制  　定制脚本 ┫";
var CY3 = "┣ 技术支持 　 游戏顾问 ┫";
var CY4 = "┣ ＷＺ添加　  地图制作 ┫";
var CY5 = "┣ 加盾防御　  售登陆器 ┫";
var CY7 = "┣ 手游开服    端游开服 ┫";
var CY8 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
var CY9 = "┣    唯一微信:ZerekY   ┫";
var CY0 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";

var ca = java.util.Calendar.getInstance();
var 年 = ca.get(java.util.Calendar.YEAR); //获得年份
var 月 = ca.get(java.util.Calendar.MONTH) + 1; //获得月份
var 日 = ca.get(java.util.Calendar.DATE);//获取日
var 时 = ca.get(java.util.Calendar.HOUR_OF_DAY); //获得小时
var 分 = ca.get(java.util.Calendar.MINUTE);//获得分钟
var 秒 = ca.get(java.util.Calendar.SECOND); //获得秒
var weekday = ca.get(java.util.Calendar.DAY_OF_WEEK);

var status = -1;
var beauty = 0;
var tosend = 0;
var sl;
var mats;
var numArr = Array("#fUI/Basic.img/LevelNo/0#","#fUI/Basic.img/LevelNo/1#","#fUI/Basic.img/LevelNo/2#","#fUI/Basic.img/LevelNo/3#","#fUI/Basic.img/LevelNo/4#","#fUI/Basic.img/LevelNo/5#","#fUI/Basic.img/LevelNo/6#","#fUI/Basic.img/LevelNo/7#","#fUI/Basic.img/LevelNo/8#","#fUI/Basic.img/LevelNo/9#");
var 熊1 = "#fUI/GuildMark/Mark/Animal/00002011/6#";
var 熊2 = "#fUI/GuildMark/Mark/Animal/00002011/7#";
var 熊3 = "#fUI/GuildMark/Mark/Animal/00002011/8#";
var 熊4 = "#fUI/GuildMark/Mark/Animal/00002011/9#";
var 熊5 = "#fUI/GuildMark/Mark/Animal/00002011/5#";
var 正方形 = "#fUI/UIWindow/Quest/icon3/6#";
var 小蓝星 = "#fEffect/CharacterEff/1112925/0/0#"; //蓝星
var 警报灯 = "#fUI/StatusBar/BtClaim/normal/0#";
var 金币图标 = "#fUI/UIWindow.img/Item/BtCoin/normal/0#";
var 点卷图标 = "#fUI/CashShop/CashItem/0#";
var t2 = "#fEffect/CharacterEff/1112902/0/0#";
var 星星 ="#fMap/MapHelper/weather/witch/3#";

function start() {
    action(1, 0, 0);
	
}

function action(mode, type, selection) {






    if (mode == -1) {
        cm.dispose();
    } else {
        if (mode == 0 && status == 0) {
            cm.dispose();
            return;
        }
        if (mode == 1) {
            status++;
        } else {
            if (status == 0) {
                cm.sendNext("如果需要点卷中介服务在来找我吧。");
                cm.dispose();
            }
            status--;
        }
		

		
		
        if (status == 0) {
			比例 = 1;
			if (日 == 52) {
				比例 = 1000;
				比例说明 = "#e#r惊喜福利当前充值可获得 1:1000 比例【限时活动中】#n\r\n";
			} else {
				比例 = 1;
				比例说明 = "";
			}
			//赞助点 = getzanzhu();
			赞助 = getmoneyb();
			
            元宝 = getmoney();

			var 赞助积分 = cm.getBossRankCount8("赞助提现积分");
			if (赞助积分 < 0) {
				赞助积分 = 0
			}
			
			获取点卷 = 赞助*比例;
            var text = "";
            text += "\t\t\t      "+ 星星 +"#e#d爱心赞助#k#n"+ 星星 + "#l\r\n\r\n"; 
			text += "#d\t"+numArr[1]+"：本服为公益永久服,如果觉得好玩可以爱心赞助\r\n"
			text += "#d\t"+numArr[2]+"：赞助比例 1:"+比例+"    1赞助点可以提现成"+比例+"元宝\r\n\r\n"	
            //text += "#d\t"+numArr[3]+"：本服任何道具,装备,物品,点卷均可在游戏自由获得\r\n\r\n"	
            //text += "#d\t"+numArr[4]+"：本服任何道具,装备,物品,点卷均可在游戏自由获得\r\n\r\n"			
			
			//text += ""+点卷图标+"账号累计赞助："+累计充值+"\r\n"
			text += ""+点卷图标+"角色累计赞助：" + 赞助积分 +"\r\n"
			//text += ""+点卷图标+"当前赞助点："+充值点()+" \r\n"
			text += ""+点卷图标+"当前账户赞助点："+赞助+" \r\n"
			text += ""+点卷图标+"当前账户元宝数："+元宝+" \r\n"
			
			/*if(充值点()>0){
			text += "\r\n#e       您有:"+充值点()+" 赞助点可以提现成元宝哟\r"	
			text += "   #L999##e- - - <<<"+点卷图标+"赞助提现元宝"+点卷图标+">>> - - -#l#k\r\n\r\n"
			}*/
			if (赞助 > 0) {
				text += "#e#b       您有:"+赞助+" 赞助可以兑换元宝哟#n\r\n"
				text += "   #L2##e#b- - - <<<"+点卷图标+"赞助兑换元宝"+点卷图标+">>> - - -#l\r\n\r\n"
			} 
			//text += "#L5##e#r- - - <<<每日充值礼包>>> - - -#l\r\n"
			text += "#L3##e#r- <<查看赞助礼包>> -#l  #L33##e#r- <<赞助礼包福利>> -#l\r\n\r\n"
			
            text += "#L0##r#r- <<点击自助赞助>> -#l  #L44##r#r- <<点击卡密领取>> -#l\r\n"
		
			//text += "#L45##r#r- - - <<< 赞助排行榜 >>> - - -#l\r\n"	
			cm.sendSimple(text);
 
        } else if (status == 1) {
			sele1 = selection;
			if(selection ==999){
				var msg = "";
				msg += "1赞助点 = 1元宝 当前赞助点："+充值点()+"\r\n"
				msg += "请输入赞助点数量兑换元宝\r\n"
				cm.sendGetNumber(msg, 1,1, 充值点());
				
				
			}else if (selection == 0) { //自助充值接口
                    cm.openWeb("http://lk4.cn/2nj"); //打开网页
					cm.playerMessage(5, "已经为您打开自助充值网站,如果没有弹出网站请联系GM充值");
					cm.dispose();
            } else if (selection == 44) { 
			        cm.dispose();
                    cm.openNpc(3003385,"卡号充值");
					
            }else if (selection == 45) {
				cm.showBossRank8Rank("赞助提现积分")
				cm.dispose();
			}else if (selection == 1) {
				赞助 = cm.getHyPay(1);
				if (赞助 >= 1) {
					cm.写入推广值(赞助);
					cm.addHyPay(赞助);
					//cm.gainmoney(-赞助);
					cm.gain(-赞助);
					cm.worldMessage(9, cm.getC().getChannel(),"【赞助中心】" + " : " + " [" + cm.getPlayer().getName() + "] 对本服鼎力支持！ 获得了："+赞助+"元宝！推广人获得10%奖励！", true);
					cm.dispose();
				} else {
					cm.sendOk("抱歉，你没有充值！");
					cm.dispose();
				
				}
			} else if (selection == 2) {
				var msg = "";
				msg += "1赞助 = "+元宝+"元宝 当前赞助："+赞助+"\r\n"
				msg += "请输入赞助数量兑换元宝\r\n"
				cm.sendGetNumber(msg, 1,1, 9999);
			} else if (selection == 3) {
				cm.dispose();
			    cm.openNpc(3003385,2102);
			} 
			else if (selection == 33) {
				cm.dispose();
			    cm.openNpc(3003385,70);
			}
			else if (selection == 4) {	
                                      
				if (赞助 >= 1) {
					setmoneyb(0);
					cm.gainNX(获取点卷);	
                    cm.getPlayer().setBossLog("每日充值",1,+赞助*比例);			
					
					cm.dispose();
				} else {
					cm.sendOk("抱歉，你没有赞助！");
					cm.dispose();
				
				}
			} else if (selection == 5) {				
				 cm.dispose();
			     cm.openNpc(9900004,184816);
							 
			} else if (selection == 9) { //易宝兑换
                    cm.dispose();
			        cm.openNpc(9900004,27);
			} else {
				
				cm.dispose();
			}
				 							 
 

        
        } else if (status == 2) {
			
			sele2 = selection;
			
            if (sele1 == 0) {
	
			} else	if (sele1 == 1) {
			} else	if (sele1 == 2) {
				if (赞助 >= sele2) {
					cm.setmoneyb(-sele2);
					
					改变元宝(sele2*比例);
					cm.sendOk("兑换成功");
					cm.setBossRankCount8("赞助提现积分", sele2);
					if(推广人()!=0){
					cm.sendOk("提现成功");	
					推广人获得点卷(sele2*10);
					}else {
					cm.sendOk("提现成功");	
					}
                    cm.dispose();
				} else {
					cm.sendOk("赞助不足无法兑换");
                    cm.dispose();
				}
				
			

			
			}else if (sele1 == 999) {
				if (充值点() >= sele2) {
					cm.setmoneyb(sele2);
					扣除充值点(sele2);
					//cm.setBossRankCount8("赞助提现积分", sele2);+加个人赞助积分
					
					//cm.gainNX(sele2*比例);
					
                    cm.dispose();
				} else {
					cm.sendOk("赞助点不足无法兑换");
                    cm.dispose();
				}
				
			

			
			} else {
				
			}	
        
			

            status = -1;
        } else {
            cm.dispose();
        }
    }
}





function 推广人(){
	var 角色id = cm.getPlayer().getId();
	var 数值 =0;
	var 数值1 =1;
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT ownid,objecid FROM invsystem WHERE ownid = "+角色id+"";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();
	if (result.next()) {
	数值 = result.getInt("objecid");
	数值1= 0;
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




function 推广人获得点卷(uit) {
	var uid = 推广人();
    var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "UPDATE tuiguangjf SET dj = dj+"+uit+"  WHERE ownid = "+uid+"";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
}

function getmoney() {
	accid = cm.getPlayer().getAccountID();
	xmfhz = 0;
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT * FROM accounts WHERE id = "+accid+"   ;";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();
	if (result.next()) {
	xmfhz = result.getString("money");
	}
	result.close();
	pstmt.close();	
	return xmfhz;
}

function 展示元宝(xiezhi) {
	accid = cm.getPlayer().getAccountID();
    var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "UPDATE accounts SET money = "+xiezhi+"  WHERE id = "+accid+"  ;";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
}


function 改变元宝(xiezhi) {
	accid = cm.getPlayer().getAccountID();
    var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "UPDATE accounts SET money = money+"+xiezhi+"  WHERE id = "+accid+"  ;";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
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

function 充值点() {
	accid = cm.getPlayer().getAccountID();
	xmfhz = 0;
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT * FROM accounts WHERE id = "+accid+"   ;";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();
	if (result.next()) {
	xmfhz = result.getString("moneyg");
	}
	result.close();
	pstmt.close();	
	return xmfhz;
}
function 扣除充值点(xiezhi) {
	accid = cm.getPlayer().getAccountID();
    var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "UPDATE accounts SET moneyg = moneyg-"+xiezhi+"  WHERE id = "+accid+"  ;";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
}





function getmoneyc() {
	accid = cm.getPlayer().getAccountID();
	xmfhz = 0;
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT * FROM accounts WHERE id = "+accid+"   ;";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();
	if (result.next()) {
	xmfhz = result.getString("moneyc");
	}
	result.close();
	pstmt.close();	
	return xmfhz;
}

function gainmoneyc(xiezhi) {
	accid = cm.getPlayer().getAccountID();
    var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "UPDATE accounts SET moneyc = moneyc+"+xiezhi+"  WHERE id = "+accid+"  ;";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
}