
var status = -1;
var MapleItemInformationProvider = Java.type('server.MapleItemInformationProvider');
var merchant_main;
var sel_type, sr_type;
var select_good;
var sj_itemid, sj_num, sj_price;
var slot = Array();
//                 1000*60=1分钟*5*1
var 等待上架时间 = 1000*60*1*1;
var no_sj = [
1902002,//基础坐骑
1912000,//基础坐骑鞍子
3994769,//身外之身2
3994720,//飞侠影分身币
3994759,//身外之身
1114314,//推广戒指
4310058,//宠吸币
4161001,//新手指南
2380004,//红蜗牛卡片
1332242,//红色切割者
1342087,//红色小刀
1382226,//红色长杖
1402214,//红色双手剑
1422156,//红色巨锤
1432182,//红色之枪
1452220,//红色弓
1462208,//红色之弩
1472230,//红色拳甲
1482183,//红色拳甲
1492194,//红色短枪
1112422,//炫色版戒指
1132115//武公的黑腰带
];//禁止上架物品
var pass_sj = [
4170006,//天空蛋
2049100,//混沌卷轴
2340000,//祝福卷轴
1112792,
2070007,//月牙标
4000100,
1112789


];//无需验证直接通过上架装备

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
			merchant_main = cm.getMerchant_main();
			if(merchant_main.isClose()){
				cm.sendOk("系统已经关闭,数据已经提交给系统,请联系管理员开放！");
				cm.dispose();
				return;
			}
			var selStr = "#d"+美化1+""+美化2+""+美化2+""+美化2+""+美化2+""+美化2+""+美化2+"『云端交易行』"+美化2+""+美化2+""+美化2+""+美化2+""+美化2+""+美化2+""+美化3+"\r\n\r\n#b  ";
			selStr += "      #L0#销售商品列表#l";
			selStr += "   #L2#销售装备列表#l\r\n\r\n";
			selStr += "        #b#L3#等待上市商品#l";
			selStr += "   #b#L4#等待上市装备#l\r\n\r\n";
            selStr += "                 #r#L1#打开商人服务#l\r\n\r\n";
            selStr += "                 #r#L666#使用说明必看#l\r\n\r\n";
			selStr += "\r\n "+美化4+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化5+"";
			cm.sendSimple(selStr);
		} else if (status == 1) {
			sel_type = selection;
			if(sel_type == 0){
				// cm.sendNext("#b正在为您查询数据 >>>>");
				var selStr = "#d目前交易市场种类列表！\r\n\r\n#b#L0##e打开全部商品信息#n#l\r\n";
				var iz = merchant_main.getOnlygoods_list().iterator();
				var list_num = 0;
				for(var i = 0; iz.hasNext(); i++){
					var zz = iz.next();
					selStr += "#L"+zz+"##b#i"+zz+":##l ";
					list_num++;
				}
				if(list_num > 0){
					cm.sendSimple("#e#b目前为您查询到："+list_num+"#b 件物品#n\r\n"+selStr);
				} else {
					cm.sendOk("#b暂无销售商品...");
					cm.dispose();
				}
			} else if(sel_type == 2){
				// cm.sendNext("#b正在为您查询数据 >>>>");
				var selStr = "#d目前交易市场种类列表！\r\n\r\n#b#L0##e打开全部装备信息#n#l\r\n";
				var iz = merchant_main.getOnlyeq_list().iterator();
				var list_num = 0;
				for(var i = 0; iz.hasNext(); i++){
					var zz = iz.next();
					selStr += "#L"+zz+"##b#i"+zz+":##l ";
					list_num++;
				}
				if(list_num > 0){
					cm.sendSimple("#e#b目前为您查询到："+list_num+"#b 件物品#n\r\n"+selStr);
				} else {
					cm.sendOk("#b暂无销售装备...");
					cm.dispose();
				}
			} else if(sel_type == 1){
				var selStr = "#d"+美化1+""+美化2+""+美化2+""+美化2+""+美化2+""+美化2+""+美化2+""+美化2+"『商人管理』"+美化2+""+美化2+""+美化2+""+美化2+""+美化2+""+美化2+""+美化2+""+美化3+"\r\n\r\n\r\n#b";
				selStr += "\t     目前销售收益点：#r#e"+cm.getmoneym()+"#n#b 剩余元宝：#r#e"+getmoney()+"#n#b\r\n\t   ( 个别物品和装备，任务物品不予上架 )\r\n";
				if(cm.haveItem(5140005, 1)){
					selStr += "\t#b#L1#上架销售商品#l\t";
					selStr += "\t#b#L5#上架销售装备#l\r\n";
				} else {
					selStr += "\t            #b#L0#注册商人服务#l\r\n";
				}
				selStr += "\t#b#L2#下架销售商品#l\t";
				selStr += "\t#b#L6#下架销售装备#l\r\n\r\n";
				//selStr += "\t#b#L3#修改物品价格#l\t";
				//selStr += "\t#b#L7#修改装备价格#l\r\n";
				selStr += "\t\t\t\t#r#L4#收益兑换元宝#l";
				//selStr += "\t\t#r#L666#收益提现人工#l";
			    selStr += "\r\n\r\n "+美化4+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化6+""+美化5+"";
				cm.sendSimple(selStr);
			} else if (sel_type == 3){
				var selStr = "#d目前等待上架物品！\r\n\r\n#b";
				var iz = merchant_main.getGoods_list().iterator();
				var list_num = 0;
				for(var i = 0; iz.hasNext(); i++){
					var zz = iz.next();
					if((zz.getGood_num() <= 0) || (zz.getCreateData() + 等待上架时间  <= new Date().getTime() || zz.getGood_num() <= 0)){
						continue;
					}
					selStr += "#b#i"+zz.getGood_id()+":# 数量：#r#e"+printf(zz.getGood_num(), 3)+"#n#b 售价：#r#e"+printf(zz.getGood_price(), 3)+"#n#b 商人：#r#e"+zz.getCharacters_name()+"#n#b \r\n\t\t倒计时:#r#e"+ parseInt((zz.getCreateData() + 等待上架时间 - new Date().getTime())/(1000*60*60)) +"时"+ parseInt((zz.getCreateData() + 等待上架时间 - new Date().getTime() - parseInt((zz.getCreateData() + 等待上架时间 - new Date().getTime())/(1000*60*60))*(1000*60*60))/(1000*60)) +"分#n#b\r\n";
					selStr += "\r\n";
					list_num++;
				}
				cm.sendOk(selStr);
				cm.dispose();
			} else if (sel_type == 4){
				var selStr = "#d目前等待上架物品！\r\n\r\n#b";
				var iz = merchant_main.getEqs_list().iterator();
				var list_num = 0;
				for(var i = 0; iz.hasNext(); i++){
					var zz = iz.next();
					if((zz.getGood_num() <= 0) || (zz.getCreateData() + 等待上架时间  <= new Date().getTime())){
						continue;
					}
					selStr += "#b#i"+zz.getGood_id()+":# 售价：#r#e"+printf(zz.getGood_price(), 3)+"#n#b 商人：#r#e"+zz.getCharacters_name()+"#n#b 倒计时:#r#e"+ parseInt((zz.getCreateData() + 等待上架时间 - new Date().getTime())/(1000*60*60)) +"时"+ parseInt((zz.getCreateData() + 等待上架时间 - new Date().getTime() - parseInt((zz.getCreateData() + 等待上架时间 - new Date().getTime())/(1000*60*60))*(1000*60*60))/(1000*60)) +"分#n#b\r\n"+zz+"\r\n";
					selStr += "\r\n";
					list_num++;
				}
				cm.sendOk(selStr);
				cm.dispose();
			} else if (sel_type == 666){
				cm.sendNext("交易行,可以上架装备和物品,交易使用元宝交易,交易成功可以获得收益点,收益点可以进行兑换元宝或者可以进行提现.");
				status = -1;
			}
		
		//} else if (sel_type == 666) {
              // cm.dispose();
			   //cm.sendOk("#b1:元宝提现必须是收益点进行提现为元宝\r\n2.满100才可提现\r\n3.客服会查询交易的信息\r\n4.不明来意的装备严重者将封号处理\r\n5.提现RMB请联系群主");
                
			//if(sel_type == 0 ){
			//	var selStr = "#d目前交易市场商品列表！\r\n\r\n#b";
				//var iz = merchant_main.getGoods_list().iterator();
				//var list_num = 0;
			//	for(var i = 0; iz.hasNext(); i++){
			//		var zz = iz.next();
			//		if(zz.getCreateData() + 1000*60*60*24  >= new Date().getTime() || zz.getGood_num() <= 0 || (selection != 0 && zz.getGood_id() != selection)){
			//			continue;
				//	}
			//		selStr += "#L"+i+"##b#i"+zz.getGood_id()+":# 剩余数量：#r#e"+printf(zz.getGood_num(), 3)+"#n#b 售价：#r#e"+printf(zz.getGood_price(), 3)+"#n#b 商人：#r#e"+zz.getCharacters_name()+"#n#b#l\r\n";
			//		selStr += "\r\n";
			//		list_num++;
				//}
				//if(list_num > 0){
				//	cm.sendSimple("#e#b目前为您查询到："+list_num+"#b 件物品#n\r\n"+selStr);
				//} else {
				//	cm.sendOk("#b列表无信息...");
				//	cm.dispose();
				//}
		} else if (status == 2) {
			if(sel_type == 0 ){
				var selStr = "#d目前交易市场商品列表！\r\n\r\n#b";
				var iz = merchant_main.getGoods_list().iterator();
				var list_num = 0;
				for(var i = 0; iz.hasNext(); i++){
					var zz = iz.next();
					if(zz.getCreateData() + 等待上架时间  >= new Date().getTime() || zz.getGood_num() <= 0 || (selection != 0 && zz.getGood_id() != selection)){
						continue;
					}
					selStr += "#L"+i+"##b#i"+zz.getGood_id()+":# 剩余数量：#r#e"+printf(zz.getGood_num(), 3)+"#n#b 售价：#r#e"+printf(zz.getGood_price(), 3)+"#n#b 商人：#r#e"+zz.getCharacters_name()+"#n#b#l\r\n";
					selStr += "\r\n";
					list_num++;
				}
				if(list_num > 0){
					cm.sendSimple("#e#b目前为您查询到："+list_num+"#b 件物品#n\r\n"+selStr);
				} else {
					cm.sendOk("#b列表无信息...");
					cm.dispose();
				}
			} else if(sel_type == 2 ){
				var selStr = "#d目前交易市场商品列表！\r\n\r\n#b";
				var iz = merchant_main.getEqs_list().iterator();
				var list_num = 0;
				for(var i = 0; iz.hasNext(); i++){
					var zz = iz.next();
					if(zz.getCreateData() + 等待上架时间  >= new Date().getTime() || (selection != 0 && zz.getGood_id() != selection)){
						continue;
					}
					selStr += "#L"+i+"##b#i"+zz.getGood_id()+":# 售价：#r#e"+printf(zz.getGood_price(), 3)+"#n#b 商人：#r#e"+zz.getCharacters_name()+"#n#b#l\r\n\r\n"+zz+"\r\n";
					selStr += "\r\n";
					list_num++;
				}
				if(list_num > 0){
					cm.sendSimple("#e#b目前为您查询到："+list_num+"#b 件物品#n\r\n"+selStr);
				} else {
					cm.sendOk("#b列表无信息...");
					cm.dispose();
				}
			} else if(sel_type == 1){
				sr_type = selection;
				if(sr_type == 0){
					cm.sendYesNo("#b注册商人即可享受以下服务：\r\n\t#r#e上架物品 修改物品 下架物品 利益兑换元宝#n#b\r\n\r\n开通时间：#r#e30天#n#b\t价格：#r#e10点卷#n#b\r\n\r\n是否要开通？");
				} else if(sr_type == 5){
					
					if(cm.getInventory(1).getItem(1) == null){
						cm.sendOk("请把你要上架的装备放置第一格");
						cm.dispose();
						return;
					}
					var pass_eq = false;//赋值于pass_eq是错的
					for(var m = 0; m < pass_sj.length; m++){
						if(cm.getInventory(1).getItem(1).getItemId() == pass_sj[m]){
							pass_eq = true;
							break;
						}
					}
					if (!pass_eq) {//不是错的才输出
						if(cm.getInventory(1).getItem(1).getExpiration() != -1){
							cm.sendOk("目前放置第一格装备必须没有时间限制.");
							cm.dispose();
							return;
						}
						/*if(cm.getInventory(1).getItem(1).getFlag() == 0){
							cm.sendOk("目前放置第一格装备必须为不可交易的物品.");
							cm.dispose();
							return;
						}*/
						if(cm.getInventory(1).getItem(1).copy().getFlag()==1){//判断装备上锁
                           cm.sendOk("上锁的装备，需要解锁才可以上架哦!"); 		
                           cm.dispose();    
                        return;
                        }
						
						if(cm.isCash(cm.getInventory(1).getItem(1).getItemId())){
							cm.sendOk("点装不允许上架");
							cm.dispose();
							return;
						}
					}
					
					for(var k = 0; k < no_sj.length; k++){
						if(cm.getInventory(1).getItem(1).getItemId() == no_sj[k]){
							cm.sendOk("目前这个装备禁止上架!.");
							cm.dispose();
							return;
						}
					}
					cm.sendNext("目前放置第一格装备检测通过请继续！！");
				} else if(sr_type == 1){
					var item_nums = 0;
					var avail = "";
					for (var j = 2; j <= 5; j++) {
						for (var i = 0; i < 96; i++) {
							if(cm.getInventory(j).getItem(i) == null){
								continue;
							}
							var is_no_sj = false;
							//for循环(变量k = 0   判断k大于变量no_sj.长度    k++)
							for(var k = 0; k < no_sj.length; k++){
								if(cm.getInventory(j).getItem(i).getItemId() == no_sj[k]){
									is_no_sj = true;
									break;
								}
							}
							if(is_no_sj){
								continue;
							}
							// 
					        for(var m = 0; m < pass_sj.length; m++){
								if(cm.getInventory(j).getItem(i).getItemId() == pass_sj[m]){
									avail += "#L" + cm.getInventory(j).getItem(i).getItemId() + "##i" + cm.getInventory(j).getItem(i).getItemId() + ":##l";
								}
					        }

							if (/*判断不是时间物品才输出*/
								cm.getInventory(j).getItem(i).getExpiration() == -1 && 
								/*判断是不可以交易的物品才输出*/
								MapleItemInformationProvider.getInstance().isDropRestricted(cm.getInventory(j).getItem(i).getItemId()) && 
								/*判断不是任务的物品才输出*/
								!MapleItemInformationProvider.getInstance().isQuestItem(cm.getInventory(j).getItem(i).getItemId()) ) {
								
								avail += "#L" + cm.getInventory(j).getItem(i).getItemId() + "##i" + cm.getInventory(j).getItem(i).getItemId() + ":##l";
								
							}
							
							//avail += "#L" + cm.getInventory(j).getItem(i).getItemId() + "##i" + cm.getInventory(j).getItem(i).getItemId() + ":##l";
								
							
							slot.push(i);
							item_nums++;
						}
					}
					if(item_nums == 0){
						cm.sendOk("没有物品可以上架!上架物品必须是没有时间限制,不能交易的物品!");
						cm.dispose();
						return;
					} else {
						cm.sendSimple("#b请选择你要上架的物品:\r\n#r#e请勿恶意乱上架/乱标价,否则可能会受到没收商品处罚#n\r\n#b" + avail);
					}
				} else if(sr_type == 2 || sr_type == 3){
					var selStr = "#d目前上架物品列表！\r\n\r\n#b";
					var iz = merchant_main.getGoods_list().iterator();
					var list_num = 0;
					for(var i = 0; iz.hasNext(); i++){
						var zz = iz.next();
						if(zz.getGood_num() <= 0 || cm.getPlayer().getAccountID() != zz.getAcc_id()){
							continue;
						}
						selStr += "#L"+i+"##b#i"+zz.getGood_id()+":##z"+zz.getGood_id()+":# 商人：#r#e"+zz.getCharacters_name()+"#n#b#l\r\n\r\n";
						selStr += "剩余数量：#r#e"+zz.getGood_num()+"#n#b\t当前售价：#r#e"+zz.getGood_price()+"#n#b\r\n\r\n";
						list_num++;
					}
					if(list_num > 0){
						cm.sendSimple("#e#b目前为您查询到："+list_num+"#b 件物品#n\r\n"+selStr);
					} else {
						cm.sendOk("#b列表无信息...");
						cm.dispose();
					}
				} else if(sr_type == 6 || sr_type == 7){
					var selStr = "#d目前上架物品列表！\r\n\r\n#b";
					var iz = merchant_main.getEqs_list().iterator();
					var list_num = 0;
					for(var i = 0; iz.hasNext(); i++){
						var zz = iz.next();
						if(zz.getGood_num() <= 0 || cm.getPlayer().getAccountID() != zz.getAcc_id()){
							continue;
						}
						selStr += "#L"+i+"##b#i"+zz.getGood_id()+":##z"+zz.getGood_id()+":# 商人：#r#e"+zz.getCharacters_name()+"#n#b#l\r\n\r\n";
						selStr += zz+"\r\n当前售价：#r#e"+zz.getGood_price()+"#n#b\r\n\r\n";
						list_num++;
					}
					if(list_num > 0){
						cm.sendSimple("#e#b目前为您查询到："+list_num+"#b 件物品#n\r\n"+selStr);
					} else {
						cm.sendOk("#b列表无信息...");
						cm.dispose();
					}
				} else if(sr_type == 4){
					cm.sendGetNumber("#d请输入你要兑换元宝的数量：\r\n#b比列：1：1",0,1, cm.getmoneym());
				} else if(sr_type == 666){
					cm.sendNext("您好！需要提现收益点，请联系群主或管理，谢谢。");
					status = -1;
				}
			} else if(sel_type == 2 ){
				var selStr = "#d目前交易市场商品列表！\r\n\r\n#b";
				var iz = merchant_main.getEqs_list().iterator();
				var list_num = 0;
				for(var i = 0; iz.hasNext(); i++){
					var zz = iz.next();
					if(selection != 0 && zz.getGood_id() != selection){
						continue;
					}
					selStr += "#L"+i+"##b#i"+zz.getGood_id()+":# 售价：#r#e"+printf(zz.getGood_price(), 3)+"#n#b 商人：#r#e"+zz.getCharacters_name()+"#n#b#l\r\n";
					selStr += "\r\n";
					list_num++;
				}
				if(list_num > 0){
					cm.sendSimple("#e#b目前为您查询到："+list_num+"#b 件物品#n\r\n"+selStr);
				} else {
					cm.sendOk("#b列表无信息...");
					cm.dispose();
				}
			}
		} else if (status == 3) {
			if(sel_type == 0){
				select_good = merchant_main.getGoods_list().get(selection);
				cm.sendGetNumber("#d当前你选择物品信息：\r\n\t#b#i"+select_good.getGood_id()+":##z"+select_good.getGood_id()+":# 商人：#r#e"+select_good.getCharacters_name()+"#n#b\r\n\t剩余数量：#r#e"+select_good.getGood_num()+"#n#b\t当前售价：#r#e"+select_good.getGood_price()+"#n#b\r\n目前你有 #b元宝#k × #r#e"+getmoney()+"#n#k\r\n#r#e请输入你要购买的数量：#n#k",0,1,parseInt(getmoney()/select_good.getGood_price()) > select_good.getGood_num() ? select_good.getGood_num() : parseInt(getmoney()/select_good.getGood_price()));
			} else if(sel_type == 2){
				select_good = merchant_main.getEqs_list().get(selection);
				cm.sendYesNo("#d当前你选择物品信息：\r\n\t#b#i"+select_good.getGood_id()+":##z"+select_good.getGood_id()+":# 商人：#r#e"+select_good.getCharacters_name()+"#n#b\r\n\t"+select_good+"\r\n\t当前售价：#r#e"+select_good.getGood_price()+"#n#b\r\n目前你有 #b元宝#k × #r#e"+getmoney()+"#n#k\r\n#r#e是否要继续购买#n#k");
			} else if(sel_type == 1){
				if(sr_type == 0){
					if(cm.getPotion(1) >= 10){//判断元宝数量
						if(cm.checkHold(5140005, 1)){
							cm.gainItem(5140005, 1, 30*24);//给予商人卡和时间
							cm.gainPotion(1, -10);//扣除元宝
							cm.sendOk("#r#e注册成功!在卖货的道路上祝您一帆风顺！#n#b\r\n\r\n#i5140005:##z5140005#这个是您的商人凭证我帮您放在背包[设置栏]内,请不要搞丢了哦!");
							cm.dispose();
						} else {
							cm.sendNext("#b操作已被驳回,原因：背包不能装下物品");
						}
					} else {
						cm.sendNext("#b很抱歉,你没有这么多点卷!");
					}
					status = -1;
				} else if(sr_type == 1){
					sj_itemid = selection;
					cm.sendGetNumber("#d当前你选择的物品：#i"+sj_itemid+":##z"+sj_itemid+"#\r\n目前你拥有该物品：#r#e"+cm.getPlayer().getItemQuantity(sj_itemid, false)+"#n#b\r\n请输入要销售的物品数量：",0,1,cm.getPlayer().getItemQuantity(sj_itemid, false) >= 32767 ? 32767 : cm.getPlayer().getItemQuantity(sj_itemid, false));
				} else if(sr_type == 2){
					select_good = merchant_main.getGoods_list().get(selection);
					cm.sendGetNumber("#d当前你选择物品信息：\r\n\t#b#i"+select_good.getGood_id()+":##z"+select_good.getGood_id()+":# 商人：#r#e"+select_good.getCharacters_name()+"#n#b\r\n\t剩余数量：#r#e"+select_good.getGood_num()+"#n#b\t当前售价：#r#e"+select_good.getGood_price()+"#n#b\r\n#r#e请输入你要下架的数量：#n#k",0,1, select_good.getGood_num());
				} else if(sr_type == 3){
					select_good = merchant_main.getGoods_list().get(selection);
					cm.sendGetNumber("#d当前你选择物品信息：\r\n\t#b#i"+select_good.getGood_id()+":##z"+select_good.getGood_id()+":# 商人：#r#e"+select_good.getCharacters_name()+"#n#b\r\n\t剩余数量：#r#e"+select_good.getGood_num()+"#n#b\t当前售价：#r#e"+select_good.getGood_price()+"#n#b\r\n#r#e请输入你要销售的单件价格：#n#k",0,1, 60000);
				} else if(sr_type == 6){
					select_good = merchant_main.getEqs_list().get(selection);
					cm.sendYesNo("#d当前你选择物品信息：\r\n\t#b#i"+select_good.getGood_id()+":##z"+select_good.getGood_id()+":#\r\n是否要下架？");
				} else if(sr_type == 7){
					select_good = merchant_main.getEqs_list().get(selection);
					cm.sendGetNumber("#d当前你选择物品信息：\r\n\t#b#i"+select_good.getGood_id()+":##z"+select_good.getGood_id()+":# 商人：#r#e"+select_good.getCharacters_name()+"#n#b\r\n"+select_good+"\r\n当前售价：#r#e"+select_good.getGood_price()+"#n#b\r\n#r#e请输入你要销售的单件价格：#n#k",0,1, 60000);
				} else if(sr_type == 4){
					if(cm.getmoneym() >= selection){
						cm.gainmoneym(-selection);
						改变元宝(parseInt(selection));
						cm.sendOk("#b你成功兑换了 #r#e"+selection+"#n#b 元宝");
						cm.dispose();
					} else {
						cm.sendNext("#b操作已被驳回,原因：输入错误,请重新输入");
						status = -1;
					}
				} else if (sr_type == 5){
					sj_itemid = cm.getInventory(1).getItem(1).getItemId();
					cm.sendYesNo("#d当前你选择的物品：#i"+sj_itemid+":##z"+sj_itemid+"#是否继续？");
				}
			}
		} else if (status == 4){
			if(sel_type == 0){
				if(selection <= select_good.getGood_num()){
					var totel_price = selection * select_good.getGood_price();
					if(getmoney() >= totel_price){
						if(cm.checkHold(select_good.getGood_id(), selection)){
							if(select_good.gainGood_num(-selection)){
								改变元宝(parseInt(-totel_price));
								cm.gainmoneym(select_good.getAcc_id(), parseInt(totel_price*1));//扣除元宝，原0.95，百分之5扣除
								var d_dh_sl = selection;
								while(d_dh_sl > 0){
									if(d_dh_sl >= 32767){
										cm.gainItem(select_good.getGood_id(), 32767);
										d_dh_sl = d_dh_sl - 32767;
									} else {
										cm.gainItem(select_good.getGood_id(), d_dh_sl);
										break;
									}
								}
								cm.sendOk("#b恭喜你成功购买了 #i"+select_good.getGood_id()+":##z"+select_good.getGood_id()+":# #r#e"+selection+"#n#b 个!");
								//cm.喇叭(2, "商人交易", "玩家 "+cm.getPlayer().getName()+" 成功购买了 商人："+select_good.getCharacters_name()+" 上架的物品 "+selection+" 个！！");
								//cm.logToFile_chr("日志/商人交易系统/玩家购买物品.txt", "购买物品："+select_good.getGood_id()+" 购买数量："+selection+" 上架商人："+select_good.getCharacters_name());
								cm.dispose();
							} else {
								cm.sendNext("#b操作已被驳回,原因：数据发生了更新,请重新购买.");
							}
						} else {
							cm.sendNext("#b操作已被驳回,原因：背包不能装下物品");
						}
					} else {
						cm.sendNext("#b很抱歉,你没有这么多元宝!");
					}
				} else {
					cm.sendNext("#b交易数据发生了更新,暂时不能购买这么多数量!");
				}
				status = -1;
			} else if(sel_type == 2){
				selection = 1;
				if(selection <= select_good.getGood_num()){
					var totel_price = selection * select_good.getGood_price();
					if(getmoney() >= totel_price){
						if(cm.checkHold(select_good.getGood_id(), selection)){
							if(select_good.gainEq_num(-selection)){
								改变元宝(parseInt(-totel_price));
								cm.gainmoneym(select_good.getAcc_id(), parseInt(totel_price*1));//扣除元宝，原0.95，百分之5扣除
								var MaplePacketCreator = Java.type('tools.MaplePacketCreator');
								var ii = MapleItemInformationProvider.getInstance();
								var newEq = ii.randomizeStats(ii.getEquipById(select_good.getGood_id())).copy();
                                newEq.setOwner(select_good.getOwner());
								newEq.setUpgradeSlots(select_good.getUpgradeslots());
								newEq.setLevel(select_good.getLevel());
								newEq.setStr(select_good.getStr());
								newEq.setDex(select_good.getDex());
								newEq.setInt(select_good.getInt_());
								newEq.setLuk(select_good.getLuk());
								newEq.setHp(select_good.getHp());
								newEq.setMp(select_good.getMp());
								newEq.setWatk(select_good.getWatk());
								newEq.setMatk(select_good.getMatk());
								newEq.setWdef(select_good.getWdef());
								newEq.setMdef(select_good.getMdef());
								newEq.setAcc(select_good.getAcc());
								newEq.setAvoid(select_good.getAvoid());
								newEq.setHands(select_good.getHands());
								newEq.setSpeed(select_good.getSpeed());
								newEq.setJump(select_good.getJump());
								newEq.setViciousHammer(select_good.getViciousHammer());
								newEq.setItemEXP(select_good.getItemEXP());
								newEq.setDurability(select_good.getDurability());
								newEq.setEnhance(select_good.getEnhance());
								newEq.setPotential1(select_good.getPotential1());
								newEq.setPotential2(select_good.getPotential2());
								newEq.setPotential3(select_good.getPotential3());
								newEq.setHpR(select_good.getHpR());
								newEq.setMpR(select_good.getMpR());
								Packages.server.MapleInventoryManipulator.addFromDrop(cm.getC(), newEq, false);
								cm.sendOk("#b恭喜你成功购买了 #i"+select_good.getGood_id()+":##z"+select_good.getGood_id()+":#!");
								//cm.喇叭(2, "商人交易", "玩家 "+cm.getPlayer().getName()+" 成功购买了 商人："+select_good.getCharacters_name()+" 上架的物品 "+selection+" 个！！");
								//cm.logToFile_chr("日志/商人交易系统/玩家购买物品.txt", "购买物品："+select_good.getGood_id()+" 购买数量："+selection+" 上架商人："+select_good.getCharacters_name());
								cm.dispose();
							} else {
								cm.sendNext("#b操作已被驳回,原因：数据发生了更新,请重新购买.");
							}
						} else {
							cm.sendNext("#b操作已被驳回,原因：背包不能装下物品");
						}
					} else {
						cm.sendNext("#b很抱歉,你没有这么多元宝!");
					}
				} else {
					cm.sendNext("#b交易数据发生了更新,暂时不能购买这么多数量!");
				}
				status = -1;
			} else if(sel_type == 1){
				if(sr_type == 1){
					sj_num = selection;
					cm.sendGetNumber("#d当前你选择的物品：#i"+sj_itemid+":##z"+sj_itemid+"#\r\n你上架的数量：#r#e"+sj_num+"#n#b\r\n请输入你的单件销售金额：",0,1,60000);
				} else if(sr_type == 5){
					sj_num = 1;
					cm.sendGetNumber("#d当前你选择的物品：#i"+sj_itemid+":##z"+sj_itemid+"#\r\n你上架的数量：#r#e"+sj_num+"#n#b\r\n请输入你的单件销售金额：",0,1,60000);
				} else if(sr_type == 2){
					if(selection <= select_good.getGood_num()){
						var totel_price = selection * select_good.getGood_price();
						if(cm.checkHold(select_good.getGood_id(), selection)){
							if(select_good.gainGood_num(-selection)){
								var d_dh_sl = selection;
								while(d_dh_sl > 0){
									if(d_dh_sl >= 32767){
										cm.gainItem(select_good.getGood_id(), 32767);
										d_dh_sl = d_dh_sl - 32767;
									} else {
										cm.gainItem(select_good.getGood_id(), d_dh_sl);
										break;
									}
								}
								cm.sendOk("#b恭喜你成功下架了物品 #i"+select_good.getGood_id()+":##z"+select_good.getGood_id()+":# #r#e"+selection+"#n#b 个!");
								cm.dispose();
							} else {
								cm.sendNext("#b操作已被驳回,原因：数据发生了更新,请重新操作.");
							}
						} else {
							cm.sendNext("#b操作已被驳回,原因：背包不能装下物品");
						}
					} else {
						cm.sendNext("#b交易数据发生了更新,暂时不能操作这么多数量!");
					}
					status = -1;
				} else if(sr_type == 3){
					select_good.setGood_price(selection);
					cm.sendOk("#b当前物品 #i"+select_good.getGood_id()+":##z"+select_good.getGood_id()+":# #r#e"+selection+"#n#b 价格已被修改为：#r#e"+selection+"#n#b!");
					cm.dispose();
				} else if(sr_type == 6){
					selection = 1;
					if(selection <= select_good.getGood_num()){
						var totel_price = selection * select_good.getGood_price();
						if(cm.checkHold(select_good.getGood_id(), selection)){
							if(select_good.gainEq_num(-1)){
								var MaplePacketCreator = Java.type('tools.MaplePacketCreator');
								var ii = MapleItemInformationProvider.getInstance();
								var newEq = ii.randomizeStats(ii.getEquipById(select_good.getGood_id())).copy();
                                newEq.setOwner(select_good.getOwner());
								newEq.setUpgradeSlots(select_good.getUpgradeslots());
								newEq.setLevel(select_good.getLevel());
								newEq.setStr(select_good.getStr());
								newEq.setDex(select_good.getDex());
								newEq.setInt(select_good.getInt_());
								newEq.setLuk(select_good.getLuk());
								newEq.setHp(select_good.getHp());
								newEq.setMp(select_good.getMp());
								newEq.setWatk(select_good.getWatk());
								newEq.setMatk(select_good.getMatk());
								newEq.setWdef(select_good.getWdef());
								newEq.setMdef(select_good.getMdef());
								newEq.setAcc(select_good.getAcc());
								newEq.setAvoid(select_good.getAvoid());
								newEq.setHands(select_good.getHands());
								newEq.setSpeed(select_good.getSpeed());
								newEq.setJump(select_good.getJump());
								newEq.setViciousHammer(select_good.getViciousHammer());
								newEq.setItemEXP(select_good.getItemEXP());
								newEq.setDurability(select_good.getDurability());
								newEq.setEnhance(select_good.getEnhance());
								newEq.setPotential1(select_good.getPotential1());
								newEq.setPotential2(select_good.getPotential2());
								newEq.setPotential3(select_good.getPotential3());
								newEq.setHpR(select_good.getHpR());
								newEq.setMpR(select_good.getMpR());
								Packages.server.MapleInventoryManipulator.addFromDrop(cm.getC(), newEq, false);
								cm.sendOk("#b成功下架了 #i"+select_good.getGood_id()+":##z"+select_good.getGood_id()+":#!");
								cm.dispose();
							} else {
								cm.sendNext("#b操作已被驳回,原因：数据发生了更新,请重新操作.");
							}
						} else {
							cm.sendNext("#b操作已被驳回,原因：背包不能装下物品");
						}
					} else {
						cm.sendNext("#b交易数据发生了更新,暂时不能操作这么多数量!");
					}
					status = -1;
				} else if(sr_type == 7){
					select_good.setGood_price(selection);
					cm.sendOk("#b当前物品 #i"+select_good.getGood_id()+":##z"+select_good.getGood_id()+":# #r#e"+selection+"#n#b 价格已被修改为：#r#e"+selection+"#n#b!");
					cm.dispose();
				}
			}
		} else if (status == 5){
			var 等待时间 = 等待上架时间/60000
			if(sel_type == 1){
				if(sr_type == 1){
					if(cm.haveItem(sj_itemid, sj_num)){
						sj_price = selection;
						cm.gainItem(sj_itemid, -sj_num);
						merchant_main.add_good(cm.getPlayer(), sj_itemid, sj_num, sj_price, new Date().getTime());
						cm.sendOk("#r#e物品上架成功~!期待爆单！！"+等待时间+"分钟后可以开放出售!");
						cm.worldMessage(  " 【交易中心】" + " : " + "["+cm.getPlayer().getName()+"]上架了一件物品!赶快去抢购吧!");

						cm.dispose();
					} else {
						cm.sendNext("#b很抱歉,你没有这么多物品!");
						status = -1;
					}
					
				} else if(sr_type == 5){
					
					var Eq = cm.getInventory(1).getItem(1);
					sj_price = selection;
					Packages.server.MapleInventoryManipulator.removeFromSlot(cm.getC(), Packages.client.inventory.MapleInventoryType.EQUIP, 1, 1, false);
					merchant_main.add_eq(cm.getPlayer(),Eq.getOwner(),Eq.getUpgradeSlots(), Eq.getLevel(), Eq.getStr(), Eq.getDex(), Eq.getInt(), Eq.getLuk(), Eq.getHp(), Eq.getMp(), Eq.getWatk(), Eq.getMatk(), Eq.getWdef(), Eq.getMdef(), Eq.getAcc(), Eq.getAvoid(), Eq.getHands(), Eq.getSpeed(), Eq.getJump(), Eq.getViciousHammer(), Eq.getItemEXP(), Eq.getDurability(), Eq.getEnhance(), Eq.getPotential1(), Eq.getPotential2(), Eq.getPotential3(), Eq.getHpR(), Eq.getMpR(), sj_itemid, sj_price);
					cm.sendOk("#r#e物品上架成功~!期待爆单！！"+等待时间+"分钟后可以开放出售!");
					cm.worldMessage(  " 【交易中心】" + " : " + "["+cm.getPlayer().getName()+"]上架了一件物品!赶快去抢购吧!");

					cm.dispose();
				}
			}
		} else {
			cm.sendOk("脚本错误....");
			cm.dispose();
		}
	}
}

function printf (msg, ws){
	return msg;
}
function 改变元宝(xiezhi) {
	accid = cm.getPlayer().getAccountID();
    var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "UPDATE accounts SET money = money+"+xiezhi+"  WHERE id = "+accid+"  ;";
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
var 美化1 = "#fUI/ChatBalloon.img/pet/120/nw#";//选择道具
var 美化3 = "#fUI/ChatBalloon.img/pet/120/ne#";//选择道具
var 美化2 = "#fUI/ChatBalloon.img/pet/120/n#";//选择道具
var 美化4 = "#fUI/ChatBalloon.img/pet/120/sw#";//选择道具
var 美化5 = "#fUI/ChatBalloon.img/pet/120/se#";//选择道具
var 美化6 = "#fUI/ChatBalloon.img/pet/120/s#";//选择道具
var 美化7 = "#fUI/ChatBalloon.img/156/arrow#";//选择道具