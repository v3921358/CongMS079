
var status = 0;
var 金币 = "#fUI/UIWindow.img/Item/BtCoin/normal/0#";
var 礼包物品 = "#v2022256#";
function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (mode == 0) {
            cm.dispose();
            return;
        }
        if (mode == 1)
            status++;
        else
            status--;
        if (status == 0) {
            cm.sendSimple ("\t\t"+彩虹+"  #e#d 一 键 技 能 #k#n  #r  "+彩虹+"#b#k#n\r\r\n"+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+"\r\n您好,尊敬的#k #h ##k 你是想学习技能？\r\n#r"+金币+"背包拥有金币："+cm.判断金币() +"#k#n\r\n\r\n#L2##s1004#骑兽学习#l  #L1##s8#群宠学习#l  #L0##s1007#锻造学习#l\r\n");
        } else if (status == 1) {
            switch(selection) {
        case 2:
		if (cm.getBossLog('骑兽技能') == 1) {
		    cm.sendOk("每个账号只能学习一次技能哦！");
		    cm.dispose();
		 } else {
		var jobid=cm.getPlayer().getJob();
		if(jobid==1000||jobid==1111||jobid==1100||jobid==1110||jobid==1211||jobid==1210||jobid==1200||jobid==1311||jobid==1310||jobid==1300||jobid==1411||jobid==1410||jobid==1400||jobid==1511||jobid==1510||jobid==1500||jobid==2000||jobid==2100||jobid==2110||jobid==2111||jobid==2112){
		
			var jobid=cm.getPlayer().getJob();
			if(jobid==2000||jobid==2100||jobid==2110||jobid==2111||jobid==2112){
			
            cm.teachSkill(20001004,1,1);
			cm.setBossLog('骑兽技能');
		    cm.sendOk("成功学习了战神骑兽技能，赶紧去看看吧！");
		    cm.dispose();
			}else{
			
            cm.teachSkill(10001004,1,1);
			cm.setBossLog('骑兽技能');
		    cm.sendOk("成功学习了骑士团骑兽技能，赶紧去看看吧！");
		    cm.dispose();
			}
		
		}
	    else{	
            cm.teachSkill(1004,1,1);
			cm.setBossLog('骑兽技能');
		    cm.sendOk("成功学习了冒险家骑兽技能，赶紧去看看吧！");
		    cm.dispose();
		}
		} 
            break;
        case 1: 
		if (cm.getBossLog('群宠技能') == 1) {
		    cm.sendOk("每个账号只能学习一次技能哦！");
		    cm.dispose();
		 } else {
			 
			 var jobid=cm.getPlayer().getJob();
		if(jobid==1000||jobid==1111||jobid==1100||jobid==1110||jobid==1211||jobid==1210||jobid==1200||jobid==1311||jobid==1310||jobid==1300||jobid==1411||jobid==1410||jobid==1400||jobid==1511||jobid==1510||jobid==1500||jobid==2000||jobid==2100||jobid==2110||jobid==2111||jobid==2112){

			if(jobid==2000||jobid==2100||jobid==2110||jobid==2111||jobid==2112){
            cm.teachSkill(20000024,1,1);
			cm.setBossLog('群宠技能');
		    cm.sendOk("成功学习了战神群宠技能，赶紧去看看吧！");
		    cm.dispose();
			}else{
            cm.teachSkill(10000018,1,1);
			cm.setBossLog('群宠技能');
		    cm.sendOk("成功学习了骑士团群宠技能，赶紧去看看吧！");
		    cm.dispose();
			}
		
		}
	    else{
            cm.teachSkill(8,1,1);
			cm.setBossLog('群宠技能');
		    cm.sendOk("成功学习了冒险家群宠技能，赶紧去看看吧！");
		    cm.dispose();
		
		
		} 
		 }
            break;
         case 0: 
		if (cm.getBossLog('锻造技能') == 1) {
		    cm.sendOk("每个账号只能学习一次技能哦！");
		    cm.dispose();
		 } else {
			 var jobid=cm.getPlayer().getJob();
		if(jobid==1000||jobid==1111||jobid==1100||jobid==1110||jobid==1211||jobid==1210||jobid==1200||jobid==1311||jobid==1310||jobid==1300||jobid==1411||jobid==1410||jobid==1400||jobid==1511||jobid==1510||jobid==1500||jobid==2000||jobid==2100||jobid==2110||jobid==2111||jobid==2112){
			if(jobid==2000||jobid==2100||jobid==2110||jobid==2111||jobid==2112){
			cm.teachSkill(20001007,3,3);//战神
			cm.setBossLog('锻造技能');
		    cm.sendOk("成功学习了战神锻造技能，赶紧去看看吧！");
		    cm.dispose();
			}else{
            cm.teachSkill(10001007,3,3);//骑士团
			cm.setBossLog('锻造技能');
		    cm.sendOk("成功学习了骑士团锻造技能，赶紧去看看吧！");
		    cm.dispose();
		}
		
		}
	    else{
            cm.teachSkill(1007,3,1);
			cm.setBossLog('锻造技能');
		    cm.sendOk("成功学习了冒险家锻造技能，赶紧去看看吧！");
		    cm.dispose();
		
		}
		}
            break;
            
            
            
            
            }
        }
    }
}
var 彩虹 ="#fEffect/ItemEff/1071085/effect/walk1/2#";
var 中条猫 ="#fUI/ChatBalloon/37/n#";
var 猫右 =  "#fUI/ChatBalloon/37/ne#";
var 猫左 =  "#fUI/ChatBalloon/37/nw#";
var 右 =    "#fUI/ChatBalloon/37/e#";
var 左 =    "#fUI/ChatBalloon/37/w#";
var 下条猫 ="#fUI/ChatBalloon/37/s#";
var 猫下右 ="#fUI/ChatBalloon/37/se#";
var 猫下左 ="#fUI/ChatBalloon/37/sw#";