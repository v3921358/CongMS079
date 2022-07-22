
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
			var 比例 = Math.round(100/cm.取火药桶总数量()*Math.pow(10,6))/Math.pow(10,6);
            var gsjb = "";
				gsjb = "#e现在冒险岛大陆已经被黑魔法师笼罩,需要冒险家收集火药桶炸开笼罩的迷雾才能突破限制，#e每次突破为10级#n\r\n";
		
				gsjb += "#v4001128##z4001128#出处：所有怪物\r\n";
				gsjb += "#r#e冒险岛世界已经被限制住了等级为:#r#e"+ cm.取限制等级() +"级#k#n\r\n";
				gsjb += "世界当前收集：#B"+ (cm.取火药桶数量()*比例)+"#["+ cm.取火药桶数量()+"/"+cm.取火药桶总数量()+"]\r\n\r\n";
				gsjb += "#L3##e#b提交突破等级#r[Hot]#l\r\n";
				gsjb += "#L33##e#b不提交兑换抵用卷#r[Hot]#l\r\n";
            cm.sendSimple(gsjb);
        } else if (status == 1) {
			var 比例 = Math.round(100/cm.取火药桶总数量()*Math.pow(10,6))/Math.pow(10,6);
            if (selection == 3) {
            if (cm.haveItem(4001128) == 0) {
                cm.sendNext("您的帐户#z4001128#数量不足。");
                status = -1;
			} else {
                beauty = 3;//下面走3
				cm.sendGetNumber("请输入对应#b#v4001128##z4001128##k\r\n目前收集最高信息 - \r\n目前已经收集: #B"+ (cm.取火药桶数量()*比例)+"#["+ cm.取火药桶数量()+"/"+cm.取火药桶总数量()+"]\r\n", 1, 1, 9999);   
			}
		}
		if (selection == 33) {
            if (cm.haveItem(4001128) == 0) {
                cm.sendNext("您的帐户#z4001128#数量不足。");
                status = -1;
			} else {
                beauty = 33;//下面走33
				cm.sendGetNumber("请输入对应#b#v4001128##z4001128##k\r\n这里不提交给炸药桶突破等级 - 直接兑换低用卷\r\n", 1, 1, 9999);   
			}
		}
        } else if (status == 2) {
			var 比例 = Math.round(100/cm.取火药桶总数量()*Math.pow(10,6))/Math.pow(10,6);
            if (beauty == 1) {
            if (selection <= 0) {
                    cm.sendOk("输入的兑换数字错误。");
                    cm.dispose();
		}
        }else if (beauty == 3) {
            if (cm.haveItem(4001128, selection) && cm.取限制等级() < 最高突破等级) {//只有限制等级小于最高突破等级才可以继续往下走，捐东西都会走这里面
                    cm.给物品(4001128, -selection);
					cm.写入火药桶数量(selection);
					//cm.gainNX(selection);//给点卷
					cm.getPlayer().modifyCSPoints(2,selection, true);//给低佣卷
					cm.refreshMaplePoints();
				if(cm.取火药桶数量()>=cm.取火药桶总数量()){//只有达到总数量才会走里面进行突破
					if(cm.取限制等级() < 150){//等级分段突破
						//只有限制等级小于200才走这里
						cm.写入限制等级(10);
					}else{//否则就是大于等于200突破5级
						cm.写入限制等级(5);	
					}
					
					cm.全服突破世界等级奖励();
					cm.扣除火药桶数量(cm.取火药桶总数量());
					cm.写入火药桶总数量(每次突破);	

				}
					cm.sendOk("成功为冒险大陆作出了伟大的贡献，谢谢\r\n世界当前收集：#B"+ (cm.取火药桶数量()*比例)+"#["+ cm.取火药桶数量()+"/"+cm.取火药桶总数量()+"]\r\n");
					cm.getChar().saveToDB(false,false);
					cm.dispose();
                } else {//否则就不能突破了，就到这里提示
                    cm.sendNext("您的输入的数量错误，拥有的炸药桶数量不足，无法突破上限。或者已经达到最高值了");
                    cm.dispose();
                }
			} 
			else if (beauty == 33) {
            if (cm.haveItem(4001128, selection)) {
                    cm.给物品(4001128, -selection);
					
					cm.getPlayer().modifyCSPoints(2,selection, true);//给低佣卷

					cm.sendOk("成功用炸药桶兑换了抵用卷");
					
					cm.dispose();
                } else {
                    cm.sendNext("您的输入的数量错误，拥有的炸药桶数量不足");
                    cm.dispose();
                }
			} 
        }
    }
}
