/*

 完成时间：2014年12月28日 22:22:28
 脚本功能：物品数量提交排名
 */
importPackage(java.sql);
importPackage(java.lang);
importPackage(Packages.database);

var time = new Date();
var 开始时间 = "2019-12-10 18:00:00";//开始时间
var 结束时间 = "2019-12-20 23:00:00";//结束时间
var minLevel = 50;//要求最小等级
var minOnlineTime = 20;//需要在线时间
var hour = time.getHours(); //获得小时
var minute = time.getMinutes();//获得分钟
var second = time.getSeconds(); //获得秒
var itemid = 4001248;//4001248
var status = 0;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (status >= 0 && mode == 0) {
            cm.sendOk("#r本期活动时间："+开始时间+"至"+结束时间+"开放全部怪物掉落#r#z"+itemid+"##k#n\r\n温馨提示:前三名奖励是本职业道具哦，无法给别的职业使用\r\n活动奖励将会在12月21日发放。");
            cm.dispose();
        }
        if (mode == 1)
            status++;
        else
            status--;
        if (status == 0) { 
                cm.sendSimple("#e#d收集#z"+itemid+"#活动第#r191214#d周期#n 七天为一个活动期（1期）\r\n#b举行活动期间只要收集 #r#z"+itemid+"# #b即可上交按照排名制度奖励\r\n#r本期活动时间："+开始时间+"至"+结束时间+"#k\r\n#b\t第一名奖励 #r本职业暴君防具 x 1。#b\r\n\t第二名奖励 #r本职业140武器 x 1。#b\r\n\t第三名奖励 #r本职业140防具 x 1。#b\r\n\r\n#d所有参与本活动者均可获得 #r#e1#z"+itemid+"#x5点卷 #n#b奖励。#k#b \r\n当前背包里有#z"+itemid+"#： #r" + cm.getItemQuantity("4001248") + " #b个。目前您已经上交了： #r"+getQty()+" #b个。\r\n#L1# 我想查看排名。\r\n#L0# 我想提交物品。")//\r\n#L0# 我想提交物品。
        } else if (status == 1) {
            if (selection == 0) {
		cm.sendGetText("你想提交多少个#b#z"+itemid+"##k ?");
            } else if (selection == 1) {//排名
                Ranking();//排名
                cm.dispose();
	    }
        } else if (status == 2) {
		var quantity = parseInt(cm.getText());
                if (quantity >  0&& quantity<= 9999){
		if (cm.haveItem(itemid,quantity)){
		var i =0;
		var DataBase = cm.getConnection().prepareStatement("SELECT * FROM ItemQuantityRanking where charid = " + cm.getPlayer().getId() + " and itemid = "+itemid+"").executeQuery();
		while(DataBase.next()){
			i++;
		}
		if(i == 0){//第一次
			var insert = cm.getConnection().prepareStatement("INSERT INTO ItemQuantityRanking (id,charid,charName,itemid,quantity) VALUES(?,?,?,?,?)"); // 载入数据
			insert.setString(1, null); //载入记录ID
                	insert.setString(2, cm.getPlayer().getId());
                	insert.setString(3, cm.getPlayer().getName());
                	insert.setString(4, itemid);
                	insert.setString(5, quantity);
                	insert.executeUpdate(); //更新
		}else{ //第n次
			var UpDateData = cm.getConnection().prepareStatement("update ItemQuantityRanking set quantity=? where charid = " + cm.getPlayer().getId() + " and itemid = "+itemid+"")
    			UpDateData.setString(1, parseInt(getQty()) + quantity);
   			UpDateData.executeUpdate();//更新;
		}
		cm.sendOk("数据添加成功！\r\n目前您的捐赠数量为： "+getQty()+"\r\n\r\n");
		cm.gainItem(itemid,-quantity);
		//cm.gainNX(1, quantity*5)
		cm.gainNX(quantity*5)
		//cm.worldSpouseMessage(0x23, "『收集年卡活动』 : " + cm.getChar().getName() + " 上交 "+quantity+" 张贺年卡,获得了 "+quantity * 5+" 点卷,目前总共上交了 "+getQty()+" 张。");
		cm.全服黄色喇叭("『收集年卡活动』 : " + cm.getChar().getName() + " 上交 "+quantity+" 张贺年卡,获得了 "+quantity * 5+" 点卷,目前总共上交了 "+getQty()+" 张。")

		}else{
			cm.sendOk("对不起，你没有足够的#t"+itemid+"#");
		}
}else{
       cm.sendOk("1以上9999以下数字可以输入。");

}
		cm.dispose();
        }
    }
}



function getQty() { //得到目前总数
    var Times = 0;
    var EventDataBase = cm.getConnection().prepareStatement("SELECT quantity FROM ItemQuantityRanking where charid = " + cm.getPlayer().getId() + " and itemid = "+itemid+"").executeQuery();
  
 while (EventDataBase.next()) {
        Times = EventDataBase.getString("quantity");
    }
    return Times;
}



function Ranking() {
    var Text = "#e#d贴心提示：只有前三名可以获得奖励\r\n\r\n本期排名如下：(1~10名次) \r\n\r\n#d"
    var RankDataBase = cm.getConnection().prepareStatement("SELECT * FROM ItemQuantityRanking where itemid = "+itemid+" ORDER BY quantity DESC LIMIT 10").executeQuery();
    var i = 1;
    while (RankDataBase.next()) {
        Text += " #e#b名次:#r第 " + i + " 名#k #b角色名:#r" + RankDataBase.getString("charName") + " #b提交物品数:#r " + RankDataBase.getString("quantity") + "#k\r\n"
        //Text += "~~~~~~~~~~~~~~~~~~~\r\n"
        i++;
    }
    cm.sendOk(Text);
}