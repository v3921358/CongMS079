function action(mode, type, selection) {
  if (cm.getJob() == 132) {
	  var jobname="弒龍騎士"
  } else if (cm.getJob() == 112) {
	  var jobname="最強英雄"
  } else if (cm.getJob() == 122) {
	  var jobname="聖之騎士"
  } else if (cm.getJob() == 212) {
	  var jobname="火毒魔導師"
  } else if (cm.getJob() == 222) {
	  var jobname="冰雷魔導師"
  } else if (cm.getJob() == 232) {
	  var jobname="神の主教"
  } else if (cm.getJob() == 312) {
	  var jobname="萬箭之王"
  } else if (cm.getJob() == 322) {
	  var jobname="箭神"
  } else if (cm.getJob() == 412) {
	  var jobname="隱之王"
  } else if (cm.getJob() == 422) {
	  var jobname="弒神者"
  } else if (cm.getJob() == 512) {
	  var jobname="金色閃光"
  } else if (cm.getJob() == 522) {
	  var jobname="大船長"
  } else if (cm.getJob() == 2112) {
	  var jobname="戰鬥之神"
  } else {
	  var jobname="奇葩"
  }	
  if (cm.haveItem(3700150,1)){
	  if(cm.getBossLog("理财每日工资")==0){
		  cm.setBossLog("理财每日工资");
		  cm.setzb(5);
		  cm.gainItem(4310057,1);
		  cm.全服漂浮喇叭("尊贵的理财玩家["+cm.getName()+"]今日首次登录返点:积分 × 5 兑换必成卷轴材料×1", 5121015);
		cm.喇叭(1,jobname+"：尊贵的理财玩家["+cm.getName()+"]今日首次登录小迷妹冒险岛成功领取到今日工资:兑换必成卷轴材料×1 积分×5。");
                cm.dispose();
	  } else {
	  cm.喇叭(1,jobname+"：尊贵的理财玩家["+cm.getName()+"]出现了。");
                cm.dispose();
	  }
  } else if (cm.haveItem(3700149,1)){
	  if(cm.getBossLog("理财每日工资")==0){
		  cm.setBossLog("理财每日工资");
		  cm.setzb(10);
		  cm.gainItem(4310057,1);
		  cm.全服漂浮喇叭("尊贵的经济理财玩家["+cm.getName()+"]今日首次登录返点:积分 × 10 兑换必成卷轴材料×1", 5121005);
		cm.喇叭(3,jobname+"：尊贵的经济理财玩家["+cm.getName()+"]今日首次登录小迷妹冒险岛成功领取到今日工资:兑换必成卷轴材料×1 积分×10。");
                cm.dispose();
	  } else {
	  cm.喇叭(3,jobname+"：尊贵的经济理财玩家["+cm.getName()+"]出现了。");
                cm.dispose();
	  }
  } else if (cm.haveItem(3700148,1)){  
  if(cm.getBossLog("理财每日工资")==0){
		  cm.setBossLog("理财每日工资");
		  cm.setzb(18);
		  cm.gainItem(4310057,1);
		  cm.全服漂浮喇叭("巨无霸至尊理财会员["+cm.getName()+"]今日首次登录返点:积分 × 18 兑换必成卷轴材料×1", 5121002);
		cm.喇叭(2,jobname+"：巨无霸至尊理财会员["+cm.getName()+"]今日首次登录小迷妹冒险岛成功领取到今日工资:兑换必成卷轴材料×1 积分×18。");
                cm.dispose();
	  } else {
	  cm.喇叭(2,jobname+"：巨无霸至尊理财会员["+cm.getName()+"]出现了。");
                cm.dispose();
	  }
  } else {
	cm.喇叭(1,jobname+"：["+cm.getName()+"]出现了。");
                cm.dispose();
          }

}
