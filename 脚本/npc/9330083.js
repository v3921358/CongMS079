var CY0 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
var CY1 = "┣       - 创意 -       ┫";
var CY2 = "┣ 玩法仿制  　定制脚本 ┫";
var CY3 = "┣ 技术支持 　 游戏顾问 ┫";
var CY4 = "┣ ＷＺ添加　  地图制作 ┫";
var CY5 = "┣ 加盾防御　  售登陆器 ┫";
var CY7 = "┣ 手游开服    端游开服 ┫";
var CY8 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
var CY9 = "┣   唯一QQ:3066318387  ┫";
var CY0 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
//【配置区】-----------------------------------------------------
var thisItemId = 2019036;//当前道具ID
var lucky = 7;//奖号取值范围，
var SelectNum = 8;//玩家可刮奖数量。
//只显示，无实际意义
var showItem = [
	[1,2048717,8 ,1000],
	
	
	[2,"累计" ,1000,1],
	
	
	[3,4031040 ,30,100,"现金"],//现金

	
	
];
var rewards = [
	//类型,ID,数量,概率
	
	[1,2290133,1,1000],//进阶技能书

      	
];
//-----------------------------------------------------
var ul_nums = [
	"#fUI/UIWindow/KeyConfig/key/11#",
	"#fUI/UIWindow/KeyConfig/key/2#",
	"#fUI/UIWindow/KeyConfig/key/3#",
	"#fUI/UIWindow/KeyConfig/key/4#",
	"#fUI/UIWindow/KeyConfig/key/5#",
	"#fUI/UIWindow/KeyConfig/key/6#",
	"#fUI/UIWindow/KeyConfig/key/7#",
	"#fUI/UIWindow/KeyConfig/key/8#",
	"#fUI/UIWindow/KeyConfig/key/9#",
	"#fUI/UIWindow/KeyConfig/key/10#",
];
var ul_nums1 = [
	"#fEffect/BasicEff/NoBlue0/0#",
	"#fEffect/BasicEff/NoBlue0/1#",
	"#fEffect/BasicEff/NoBlue0/2#",
	"#fEffect/BasicEff/NoBlue0/3#",
	"#fEffect/BasicEff/NoBlue0/4#",
	"#fEffect/BasicEff/NoBlue0/5#",
	"#fEffect/BasicEff/NoBlue0/6#",
	"#fEffect/BasicEff/NoBlue0/7#",
	"#fEffect/BasicEff/NoBlue0/8#",
	"#fEffect/BasicEff/NoBlue0/9#",
];
var ul_yStar = "#fItem/Etc/0427/04270003/Icon9/5#";  //
var status = -1;//模组状态
var chr =null;
var say = "";

var curLuckyNum = -1;//中奖号码
var stateCurLucky = 0;//中奖号码刮开状态
var toLuckyNums= [];//中奖号码刮开状态
var stateNums = [];//刮奖状态存储。
var luckItems = [];//中奖物品显示存储

function start(){
    chr = cm.getPlayer();
	刮刮乐 = chr.getBossLog("刮刮乐",1);
	if(!_CheckInv(0,3)){
		cm.sendOk("请#r#e所有栏#n#k，最少保留3个空位，否则无法开启！");
		cm.dispose();
		return;
	}
	
    action(1,0,0);
}

function action(mode, type, selection) {
	//chr.dropMessage(5,"mode:"+mode+",type:"+type+",sle:"+selection+",state:"+status);
    if(mode == -1){cm.dispose();return;}
    if(mode == 1) {status++;} else {status--;}

    if(status == 0){
		if(curLuckyNum == -1){
			curLuckyNum = Math.floor(Math.random() * lucky)+1;
			for(var i = 0;i<SelectNum;i++){
				toLuckyNums.push(Math.floor(Math.random() * lucky)+1);
				stateNums.push(0);
				luckItems.push([]);
			}
		}
		
		_showInfo();
		
        cm.sendSimple(say);
    }else if(status == 1){
		
		if(stateCurLucky == 0 && selection!=9999){
			status = -1;
			cm.sendOk("请先刮开中奖号码！");
			
		}else{
			if(selection == 9999){
				if(stateCurLucky != 1){
					chr.setBossLog("刮刮乐",1,+1);
					cm.gainItem(thisItemId,-1);
					stateCurLucky = 1;
				}
			}else{
				if(stateNums[selection]!= 1){
					stateNums[selection] = 1;
						var toJP =  _ChouJiang(rewards);
						luckItems[selection] = toJP;
						if(toLuckyNums[selection] == curLuckyNum){
							if(toJP[0] == 1){
								cm.gainItem(toJP[1],toJP[2]);//道具奖励发放
								cm.全服黄色喇叭(""+toJP[1]+"在刮刮卡彩票中获得了该物品：x "+toJP[2]+"");
							}else if(toJP[0] == 2){
								if(toJP[1] == "累计"){
									增加剩余积分(cm.getPlayer().getId(),(toJP[2]));
									cm.setmoneyb(toJP[2]);
									cm.道具喇叭(3994037, "在刮刮卡彩票中获得了累计赞助和积分：+ "+toJP[2]+"");
									//...现金奖励发放
								}
							}else if(toJP[0] == 3){
								if(toJP[4] == "现金"){
									cm.gainItem(toJP[1],toJP[2]);//现金道具
									cm.道具喇叭(4031040, "在刮刮卡彩票中获得了现金大奖：["+toJP[2]+"元] 可以私聊群主兑换现金");
									//...现金奖励发放
								}
							}
						}
				}else{
					if(stateNums[selection]==0){
						luckItems[selection] = showItem[Math.floor(Math.random()*showItem.length)];
					}
				}
			}
			_showInfo();
			cm.sendSimple(say);
			status = 0;
		}
			
		}
		
		
//    }else{
  //      cm.dispose();
  //  }
}

function _showInfo(){
	say = "";
	
	if(stateCurLucky == 0){
		say += "#L9999##r#e刮开查看中奖号码#l\r\n \r\n";
	}else{
		say += "#r#e中奖号码：【"+_showScore1(curLuckyNum)+"】#b\r\n";
	}
	
	for(var i = 0 ;i<SelectNum;i++){
		if(i!=0&&i%1==0){
			say += "";
		}else{
			if(i != 0){say += "";}
		}
		if(stateNums[i] == 0){
			say += " #L"+i+"##k#e刮开>>>>[■■■■■■]#l\r\n";
		}else{
			if(toLuckyNums[i] == curLuckyNum){
				say += "#L"+i+"##b【#e#r"+_showScore(toLuckyNums[i],2)+"#b】";//中奖数字UI
				if(luckItems[i][0] == 1){
					say += "#b[#v"+luckItems[i][1]+"##z"+luckItems[i][1]+"##rx"+luckItems[i][2]+"#b]\r\n";//道具奖励UI
				}else if(luckItems[i][0] == 2){
					say += "#b["+luckItems[i][1]+"#rx"+luckItems[i][2]+"#b]\r\n";//现金奖励UI。
				}else{
					say += "#b[#v"+luckItems[i][1]+"#现金1个=1块钱#rx"+luckItems[i][2]+"#b]\r\n";//道具奖励UI
				}
				say +="#l";
			}else{
				say += "#L"+i+"##e#k【"+_showScore(toLuckyNums[i],2)+"】";
				if(luckItems[i][0] == 1){
					say += "[#z"+luckItems[i][1]+"##kx"+luckItems[i][2]+"#k][#r未中奖#k]";//道具奖励UI
				}else if(luckItems[i][0] == 2){
					say += "["+luckItems[i][1]+"#kx"+luckItems[i][2]+"#k][#r未中奖#k]";//累计
				}else{
					say += "[现金#kx"+luckItems[i][2]+"#k][#r未中奖#k]";//现金
				}
				say +="#l\r\n";
			}
		}
		
	}
}

function _ChouJiang(jp){
	var tempList = [];
	
	for(var i = 0;i<jp.length;i++){
		for(var j = 0;j<jp[i][3];j++){
			tempList.push(jp[i]);
		}
	}
	
	return tempList[Math.floor(Math.random() *tempList.length)];
}

function _showScore(num,ext){
	var showTxt ="";
	var tempNums = num.toString().split("");
	for(var i =0; i < tempNums.length;i++){
		showTxt += ul_nums[parseInt(tempNums[i])];
	}
	var sss = "";
	for(var i =tempNums.length; i<ext;i++){
		sss += " ";
	}
	return showTxt + sss;
}
function _showScore1(num,ext){
	var showTxt ="";
	var tempNums = num.toString().split("");
	for(var i =0; i < tempNums.length;i++){
		showTxt += ul_nums1[parseInt(tempNums[i])];
	}
	var sss = "";
	for(var i =tempNums.length; i<ext;i++){
		sss += " ";
	}
	return showTxt + sss;
}


	
/**

检查背包空格
@type = {0:所有,1:装备,2:消耗,3:设置,4:其他,5:现金}	
@count = {要求空位数量}

*/
function _CheckInv(InvType,count){
	isOk = true;
	if(InvType == 0){
		for(var i = 1;i<6;i++){
			if(cm.getInventory(i).isFull(count-1)){
				isOk = false;
				break;
			}
		}
	}else{
		if(cm.getInventory(InvType).isFull(count-1)){
			isOk = false;
		}
	}
	
	return isOk;
}
function 剩余积分(a){
var sql ="SELECT characterid,syjf FROM paymoney";
var rs =cm.sql_Select(sql);	
var 数值 =0;
var 判定 =true;
for(var i=0;i<rs.size();i++){
	if(rs[i].get("characterid")==a){
	   	数值 =rs[i].get("syjf");
		判定 =false;
	}
}
if(判定 ==true){
var sql1 ="INSERT INTO paymoney(id,characterid,syjf,ljjf,kydj) VALUE(?,?,?,?,?)";
cm.sql_Insert(sql1,null,a,0,0,0);	
}
return 数值;
}

function 增加剩余积分(id,number){
var sql ="UPDATE paymoney SET syjf = ? WHERE characterid = ?";
cm.sql_Update(sql,(剩余积分(id)+number),id);	
}