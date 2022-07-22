
			
			
			var status = -1;
            var tex2 = "";
            var text = "";
            for (i = 0; i < 10; i++) {  //开头的定义
                text += "";
				}
				
				
function start() { //上半部分的代码，主要用来显示说明
     if (cm.getMapId() != 261000021) {
	 cm.dispose();
	 cm.openNpc(2112003,1);
	 } else {	 	 
     text += "\t\t\t#e#r#v4001129#欢迎来到朱丽叶副本任务#v4001129##k#n       \r\n "  //开头的文字
     text += " #L1##r#e#v4030000#开始组队任务#l   #L0##r#e#v4032226#喇叭召集令#l #n  \r\n " //开头的文字
     //text += " #L2##r#v4002003#领取绿水灵邮票奖励#b(经验、抵用券）#l      \r\n \r\n" //开头的文字
	 text += "             #L3##r#v4030001#兑换副本项链#v4030001##l      \r\n " //开头的文字	
	cm.sendSimple(text);
    }

}	//结束上半部分			
				
	function action(mode, type, selection) { //这里应该是大入口
	
	       if (selection == 0) { //根据的序列上方选择来运行的脚本开头指定
           if (cm.getMeso () >= 100000) { //判断是否有物品
				cm.gainMeso(-100000); //扣除或给与物品
				//cm.gainItem(4032226,-1); //扣除或给与物品
				//cm.gainDY(1000);//抵用券
                //cm.gainNX(+1000);//点劵		
				cm.sendOk("成功发布召集令!"); //显示信息
			    cm.喇叭(3,"朱丽叶副本任务准备开始，需要大家一起来组队来完成!"); //发送喇叭信息
				cm.dispose(); //结束本段代码，必须加
			}else{ //如果不满足条件
				cm.sendOk("你的金钱好像不够，无法召集。"); //显示信息
				cm.dispose(); //结束本段代码，必须加
			} 
	   
	} else if (selection == 1) { 

			cm.dispose();
			cm.openNpc(2112003,  1);
			
	} else if (selection == 2) { 
			if (cm.haveItem(4002003,1)){ //判断是否有物品
				cm.gainItem(4002003,-1); //扣除或给与物品
				cm.gainDY(100);//抵用券
				cm.gainExp(200000);//获得经验
                //cm.gainNX(+1000);//点劵		
				//cm.sendOk("成功兑换奖励!"); //显示信息
			    cm.worldMessage(6,"恭喜["+cm.getName()+"]兑换了朱丽叶副本奖励、经验值20万、抵用券100点！"); //发送喇叭信息
				cm.dispose(); //结束本段代码，必须加
			}else{ //如果不满足条件
				cm.sendOk("你好像没有#v4002003#，无法兑换奖励。"); //显示信息
				cm.dispose(); //结束本段代码，必须加
			}
	}
	 

	 else if (selection == 3) { 
	         cm.dispose();
             cm.openNpc(2112003,100);
			
					
	
				
	}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 } //结束大入口

	
	 
	 
	 
	 
	 
	 
