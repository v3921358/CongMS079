/* 
 * 脚本类型: cm
 * 脚本用途: 钓鱼脚本
 * 脚本作者: 林一，QQ：1500663066
 * 制作时间: 2015/12/18
 */
 
 
var 小黄星 = "#fItem/Etc/0427/04270001/Icon9/0#";  //
var 红色箭头 = "#fUI/UIWindow/Quest/icon6/7#";
var status = -1;
var beauty = 0;
var tosend = 0;
var sl;
var mats;
var dds;
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
                 // cm.getChar().gainCashDD(+1000);
				 
               if (cm.haveItem(4200007, 1)){
				cm.sendOk("你是个可疑的人，不允许打开兑换。");
				cm.dispose();    
			   }
				 
            var gsjb = "";
             gsjb = "\t\t"+彩虹+"  #e#d 小 鱼 换 大 鱼 #k#n  #r  "+彩虹+"#b#k#n\r\r\n"+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+"\r\n\n";
			
            gsjb += "#L0##b#v4031627##z4031627# 兑换 #v4031636##z4031636# #b比例(#r10 = 1#b)#l\r\n";
            gsjb += "#L1##b#v4031633##z4031633# 兑换 #v4031636##z4031636# #b比例(#r6 = 1#b)#l\r\n";
            gsjb += "#L2##b#v4031634##z4031634# 兑换 #v4031636##z4031636# #b比例(#r4 = 1#b)#l\r\n";
            gsjb += "#L3##b#v4031635##z4031635# 兑换 #v4031636##z4031636# #b比例(#r2 = 1#b)#l\r\n\r\n";
            gsjb += "#L4##b#v4031630##z4031630# 兑换 #v4031640##z4031640# #b比例(#r10 = 1#b)#l\r\n";
            gsjb += "#L5##b#v4031637##z4031637# 兑换 #v4031640##z4031640# #b比例(#r6 = 1#b)#l\r\n";
            gsjb += "#L6##b#v4031638##z4031638# 兑换 #v4031640##z4031640# #b比例(#r4 = 1#b)#l\r\n";
            gsjb += "#L7##b#v4031639##z4031639# 兑换 #v4031640##z4031640# #b比例(#r2 = 1#b)#l\r\n\r\n";
            gsjb += "#L8##b#v4031628##z4031628# 兑换 #v4031644##z4031644# #b比例(#r10 = 1#b)#l\r\n";
            gsjb += "#L9##b#v4031641##z4031641# 兑换 #v4031644##z4031644# #b比例(#r6 = 1#b)#l\r\n";
            gsjb += "#L10##b#v4031642##z4031642# 兑换 #v4031644##z4031644# #b比例(#r4 = 1#b)#l\r\n";
            gsjb += "#L11##b#v4031643##z4031643# 兑换 #v4031644##z4031644# #b比例(#r2 = 1#b)#l\r\n\r\n";
            gsjb += "#L12##b#v4031631##z4031631#兑换 #v4031648##z4031648# #b比例(#r10 = 1#b)#l\r\n";
            gsjb += "#L13##b#v4031645##z4031645#兑换 #v4031648##z4031648# #b比例(#r6 = 1#b)#l\r\n";
            gsjb += "#L14##b#v4031646##z4031646#兑换 #v4031648##z4031648# #b比例(#r4 = 1#b)#l\r\n";
            gsjb += "#L15##b#v4031647##z4031647#兑换 #v4031648##z4031648# #b比例(#r2 = 1#b)#l\r\n\r\n";
            cm.sendSimple(gsjb);
        }

			/*else if (selection == 10011) {
				cm.dispose();
				cm.warp(9330045_10011);
               
	    }*/

		else if (status == 1) {
            if (cm.getPlayer() >= 1 && cm.getPlayer() <= 5) {
                cm.sendOk("GM不能参与兑换。");
                cm.dispose();
            }
            if (selection == 0) {
                if (cm.haveItem(4031627) == 0) {
                    cm.sendNext("你的物品不足兑换.");
                    status = -1;
                } else {
                    beauty = 0
           cm.sendGetNumber("请输入数量:\r\n#b比例 - (#r10 = 1#b)\r\n  当前拥有数量: #c4031627# 个#r \r\n", 1, 1, 100000 ); }

            } else if (selection == 16) {
                    beauty = 16
					cm.sendYesNo("ˇˇˇˇˇˇˇˇˇ#r当前拥有的鱼#k ˇˇˇˇˇˇˇˇˇˇˇ\r\n     #v4031627#  #r#c4031627##k条 #v4031633#  #r#c4031633##k条 #v4031634#  #r#c4031634##k条 #v4031635#  #r#c4031635##k条\r\n     #v4031630#  #r#c4031630##k条 #v4031637#  #r#c4031637##k条 #v4031638#  #r#c4031638##k条 #v4031639#  #r#c4031639##k条\r\n     #v4031628#  #r#c4031628##k条 #v4031641#  #r#c4031641##k条 #v4031642#  #r#c4031642##k条 #v4031643#  #r#c4031643##k条\r\n     #v4031631# #r#c4031631##k条 #v4031645# #r#c4031645##k条 #v4031646# #r#c4031646##k条 #v4031647# #r#c4031647##k条\r\n\r\nˇˇˇˇˇˇˇˇˇ#r当前拥有的鱼#k ˇˇˇˇˇˇˇˇˇˇˇ\r\n是否把全部兑换为最大的鱼？(管理员余夫会收#r10W#k手续费哦!)");
            } else if (selection == 1) {
                if (cm.haveItem(4031633) == 0) {
                    cm.sendNext("你的物品不足兑换.");
                    status = -1;
                } else {
                    beauty = 1
           cm.sendGetNumber("请输入数量:\r\n#b比例 - (#r6 = 1#b)\r\n  当前拥有数量: #c4031633# 个#r \r\n", 1, 1, 100000 ); }

            }  
			 else if (selection == 2) {
                if (cm.haveItem(4031634) == 0) {
                    cm.sendNext("你的物品不足兑换.");
                    status = -1;
                } else {
                    beauty = 2
           cm.sendGetNumber("请输入数量:\r\n#b比例 - (#r4 = 1#b)\r\n  当前拥有数量: #c4031634# 个#r \r\n", 1, 1, 100000 ); }

            }			
			else if (selection == 3) {
                if (cm.haveItem(4031635) == 0) {
                    cm.sendNext("你的物品不足兑换.");
                    status = -1;
                } else {
                    beauty = 3
           cm.sendGetNumber("请输入数量:\r\n#b比例 - (#r2 = 1#b)\r\n  当前拥有数量: #c4031635# 个#r \r\n", 1, 1, 100000 ); }

            } else if (selection == 4) {
                if (cm.haveItem(4031630) == 0) {
                    cm.sendNext("你的物品不足兑换.");
                    status = -1;
                } else {
                    beauty = 4
           cm.sendGetNumber("请输入数量:\r\n#b比例 - (#r10 = 1#b)\r\n  当前拥有数量: #c4031630# 个#r \r\n", 1, 1, 100000 ); }


            } else if (selection == 5) {
                if (cm.haveItem(4031637) == 0) {
                    cm.sendNext("你的物品不足兑换.");
                    status = -1;
                } else {
                    beauty = 5
           cm.sendGetNumber("请输入数量:\r\n#b比例 - (#r6 = 1#b)\r\n  当前拥有数量: #c4031637# 个#r \r\n", 1, 1, 100000 ); }

            } else if (selection == 6) {
                if (cm.haveItem(4031638) == 0) {
                    cm.sendNext("你的物品不足兑换.");
                    status = -1;
                } else {
                    beauty = 6
           cm.sendGetNumber("请输入数量:\r\n#b比例 - (#r4 = 1#b)\r\n  当前拥有数量: #c4031638# 个#r \r\n", 1, 1, 100000 ); }

            } else if (selection == 7) {
                if (cm.haveItem(4031639) == 0) {
                    cm.sendNext("你的物品不足兑换.");
                    status = -1;
                } else {
                    beauty = 7
           cm.sendGetNumber("请输入数量:\r\n#b比例 - (#r2 = 1#b)\r\n  当前拥有数量: #c4031639# 个#r \r\n", 1, 1, 100000 ); }

            }else if (selection == 8) {
                if (cm.haveItem(4031628) == 0) {
                    cm.sendNext("你的物品不足兑换.");
                    status = -1;
                } else {
                    beauty = 8
           cm.sendGetNumber("请输入数量:\r\n#b比例 - (#r10 = 1#b)\r\n  当前拥有数量: #c4031628# 个#r \r\n", 1, 1, 100000 ); }

            }else if (selection == 9) {
                if (cm.haveItem(4031641) == 0) {
                    cm.sendNext("你的物品不足兑换.");
                    status = -1;
                } else {
                    beauty = 9
           cm.sendGetNumber("请输入数量:\r\n#b比例 - (#r6 = 1#b)\r\n  当前拥有数量: #c4031641# 个#r \r\n", 1, 1, 100000 ); }

            }else if (selection == 10) {
                if (cm.haveItem(4031642) == 0) {
                    cm.sendNext("你的物品不足兑换.");
                    status = -1;
                } else {
                    beauty = 10
           cm.sendGetNumber("请输入数量:\r\n#b比例 - (#r4 = 1#b)\r\n  当前拥有数量: #c4031642# 个#r \r\n", 1, 1, 100000 ); }

            }else if (selection == 11) {
                if (cm.haveItem(4031643) == 0) {
                    cm.sendNext("你的物品不足兑换.");
                    status = -1;
                } else {
                    beauty = 11
           cm.sendGetNumber("请输入数量:\r\n#b比例 - (#r2 = 1#b)\r\n  当前拥有数量: #c4031643# 个#r \r\n", 1, 1, 100000 ); }

            }else if (selection == 12) {
                if (cm.haveItem(4031631) == 0) {
                    cm.sendNext("你的物品不足兑换.");
                    status = -1;
                } else {
                    beauty = 12
           cm.sendGetNumber("请输入数量:\r\n#b比例 - (#r10 = 1#b)\r\n  当前拥有数量: #c4031631# 个#r \r\n", 1, 1, 100000 ); }

            }else if (selection == 13) {
                if (cm.haveItem(4031645) == 0) {
                    cm.sendNext("你的物品不足兑换.");
                    status = -1;
                } else {
                    beauty = 13
           cm.sendGetNumber("请输入数量:\r\n#b比例 - (#r6 = 1#b)\r\n  当前拥有数量: #c4031645# 个#r \r\n", 1, 1, 100000 ); }

            }else if (selection == 14) {
                if (cm.haveItem(4031646) == 0) {
                    cm.sendNext("你的物品不足兑换.");
                    status = -1;
                } else {
                    beauty = 14
           cm.sendGetNumber("请输入数量:\r\n#b比例 - (#r4 = 1#b)\r\n  当前拥有数量: #c4031646# 个#r \r\n", 1, 1, 100000 ); }

            }else if (selection == 15) {
                if (cm.haveItem(4031647) == 0) {
                    cm.sendNext("你的物品不足兑换.");
                    status = -1;
                } else {
                    beauty = 15
           cm.sendGetNumber("请输入数量:\r\n#b比例 - (#r2 = 1#b)\r\n  当前拥有数量: #c4031647# 个#r \r\n", 1, 1, 100000 ); }

            }
			
			else if (selection == 10011) {
				cm.dispose();
				cm.openNpc(9330045, 10011 );

        }
			
			
			
			
			
			
			else if (selection == 100) {
                if (cm.haveItem(4031636) == 0) {
                    cm.sendNext("你的物品不足兑换.");
                    status = -1;
                } else {
                    beauty = 100
           cm.sendGetNumber("请输入数量:\r\n#b比例 - (#r1 = 50W#b)\r\n  当前拥有数量: #c4031636# 个#r \r\n", 1, 1, 100000 ); }

            }else if (selection == 101) {
                if (cm.haveItem(4031640) == 0) {
                    cm.sendNext("你的物品不足兑换.");
                    status = -1;
                } else {
                    beauty = 101
           cm.sendGetNumber("请输入数量:\r\n#b比例 - (#r5 = 1#b)\r\n  当前拥有数量: #c4031640# 个#r \r\n", 1, 1, 100000 ); }

            }else if (selection == 102) {
                if (cm.haveItem(4031644) == 0) {
                    cm.sendNext("你的物品不足兑换.");
                    status = -1;
                } else {
                    beauty = 102
           cm.sendGetNumber("请输入数量:\r\n#b比例 - (#r10 = 1#b)\r\n  当前拥有数量: #c4031644# 个#r \r\n", 1, 1, 100000 ); }

            }else if (selection == 103) {
                if (cm.haveItem(4031648) == 0) {
                    cm.sendNext("你的物品不足兑换.");
                    status = -1;
                } else {
                    beauty = 103
           cm.sendGetNumber("请输入数量:\r\n#b比例 - (#r5 = 1#b)\r\n  当前拥有数量: #c4031648# 个#r \r\n", 1, 1, 100000 ); }

            }else if (selection == 104) {
                if (cm.haveItem(4031629) == 0 && cm.haveItem(4031632) == 0 && cm.haveItem(4031640) == 0 && cm.haveItem(4031636) == 0 && cm.haveItem(4031644) == 0 && cm.haveItem(4031648) == 0) {
                    cm.sendNext("你的物品不足兑换.");
                    status = -1;
                } else {
                    beauty = 104
					cm.sendGetNumber("#v4031629##v4031632##v4031636##v4031640##v4031644##v4031648# \r\n请输入大丰收兑换的数量:\r\n#b比例 - (#r1组 = 400W#b)\r\n", 1, 1, 100000 ); }
            }

        } else if (status == 2) {
            
                 if (cm.getPlayer().getMeso() < 100000){
						 cm.sendOk("金币不足。");
						 cm.dispose();
				 }else{
						if (beauty == 16) {
								for (i = 0; i < 100; i++) {
							if (cm.haveItem(4031627, 10)){
							 cm.gainItem(4031627, -10);
							 cm.gainItem(4031636, 1);
							}
							if (cm.haveItem(4031633, 6)){
							 cm.gainItem(4031633, -6);
							 cm.gainItem(4031636, 1);
							}
							if (cm.haveItem(4031634, 4)){
							 cm.gainItem(4031634, -4);
							 cm.gainItem(4031636, 1);
							}
							if (cm.haveItem(4031635, 2)){
							 cm.gainItem(4031635, -2);
							 cm.gainItem(4031636, 1);
							}
							
							if (cm.haveItem(4031630, 10)){
							 cm.gainItem(4031630, -10);
							 cm.gainItem(4031640, 1);
							}
							if (cm.haveItem(4031637, 6)){
							 cm.gainItem(4031637, -6);
							 cm.gainItem(4031640, 1);
							}
							if (cm.haveItem(4031638, 4)){
							 cm.gainItem(4031638, -4);
							 cm.gainItem(4031640, 1);
							}
							if (cm.haveItem(4031639, 2)){
							 cm.gainItem(4031639, -2);
							 cm.gainItem(4031640, 1);
							}
							
							
							if (cm.haveItem(4031628, 10)){
							 cm.gainItem(4031628, -10);
							 cm.gainItem(4031644, 1);
							}
							if (cm.haveItem(4031641, 6)){
							 cm.gainItem(4031641, -6);
							 cm.gainItem(4031644, 1);
							}
							if (cm.haveItem(4031642, 4)){
							 cm.gainItem(4031642, -4);
							 cm.gainItem(4031644, 1);
							}
							if (cm.haveItem(4031643, 2)){
							 cm.gainItem(4031643, -2);
							 cm.gainItem(4031644, 1);
							}
							
							
							if (cm.haveItem(4031631, 10)){
							 cm.gainItem(4031631, -10);
							 cm.gainItem(4031648, 1);
							}
							if (cm.haveItem(4031645, 6)){
							 cm.gainItem(4031645, -6);
							 cm.gainItem(4031648, 1);
							}
							if (cm.haveItem(4031646, 4)){
							 cm.gainItem(4031646, -4);
							 cm.gainItem(4031648, 1);
							}
							if (cm.haveItem(4031647, 2)){
							 cm.gainItem(4031647, -2);
							 cm.gainItem(4031648, 1);
							}
								}	
						 cm.gainMeso(-100000); //加减金币
						 cm.sendOk("兑换成功！");
						 cm.dispose();	
				 }
            }
          /*  if (beauty == 16) {
				
					 xy1 = cm.getPlayer().getItemQuantity(4031627, false);
					 var xyb = xy1/10;
                if (xyb>0){
					if (cm.haveItem(4031627, 10)){
					 cm.gainItem(4031627, -xyb*10);
					 cm.gainItem(4031636, xyb);
					}
                     //cm.sendNext("兑换成功。 [#r"+selection+"#k] 兑换了:[#r"+(selection*100000)+"#k] 金币");
                }
					 xy2 = cm.getPlayer().getItemQuantity(4031633, false);
					 var xyb = xy2/6;
                if (xyb>0){
					if (cm.haveItem(4031633, 6)){
					 cm.gainItem(4031633, -xyb*6);
					 cm.gainItem(4031636, xyb);
					}
                     //cm.sendNext("兑换成功。 [#r"+selection+"#k] 兑换了:[#r"+(selection*100000)+"#k] 金币");
                }
					 xy3 = cm.getPlayer().getItemQuantity(4031634, false);
					 var xyb = xy3/4;
                if (xyb>0){
					if (cm.haveItem(4031634, 4)){
					 cm.gainItem(4031634, -xyb*4);
					 cm.gainItem(4031636, xyb);
					}
                     //cm.sendNext("兑换成功。 [#r"+selection+"#k] 兑换了:[#r"+(selection*100000)+"#k] 金币");
                }
					 xy4 = cm.getPlayer().getItemQuantity(4031635, false);
					 var xyb = xy4/2;
                if (xyb>0){
					if (cm.haveItem(4031635, 2)){
					 cm.gainItem(4031635, -xyb*2);
					 cm.gainItem(4031636, xyb);
					}
                     //cm.sendNext("兑换成功。 [#r"+selection+"#k] 兑换了:[#r"+(selection*100000)+"#k] 金币");
                }
                     cm.sendOk("兑换成功！");
					 cm.dispose();
            }*/
			
						

            if (beauty == 0) {
                if (cm.haveItem(4031627, selection*10)){
					 cm.gainItem(4031627, -selection*10);
					 cm.gainItem(4031636, selection);
                     //cm.sendNext("兑换成功。 [#r"+selection+"#k] 兑换了:[#r"+(selection*100000)+"#k] 金币");
                     cm.sendOk("兑换成功！");
					 cm.dispose();
                } else {
                    cm.sendNext("条件不足，无法兑换。");
                    cm.dispose()
                }
            } if (beauty == 1) {
                if (cm.haveItem(4031633, selection*6)){
					 cm.gainItem(4031633, -selection*6);
					 cm.gainItem(4031636, selection);
                     //cm.sendNext("兑换成功。 [#r"+selection+"#k] 兑换了:[#r"+(selection*100000)+"#k] 金币");
                     cm.sendOk("兑换成功！");
					 cm.dispose();
                } else {
                    cm.sendNext("条件不足，无法兑换。");
                    cm.dispose()
                }
            }if (beauty == 2) {
                if (cm.haveItem(4031634, selection*4)){
					 cm.gainItem(4031634, -selection*4);
					 cm.gainItem(4031636, selection);
                     //cm.sendNext("兑换成功。 [#r"+selection+"#k] 兑换了:[#r"+(selection*100000)+"#k] 金币");
                     cm.sendOk("兑换成功！");
					 cm.dispose();
                } else {
                    cm.sendNext("条件不足，无法兑换。");
                    cm.dispose()
                }
            }if (beauty == 3) {
                if (cm.haveItem(4031635, selection*2)){
					 cm.gainItem(4031635, -selection*2);
					 cm.gainItem(4031636, selection);
                     //cm.sendNext("兑换成功。 [#r"+selection+"#k] 兑换了:[#r"+(selection*100000)+"#k] 金币");
                     cm.sendOk("兑换成功！");
					 cm.dispose();
                } else {
                    cm.sendNext("条件不足，无法兑换。");
                    cm.dispose()
                }
            }if (beauty == 4) {
                if (cm.haveItem(4031630, selection*10)){
					 cm.gainItem(4031630, -selection*10);
					 cm.gainItem(4031640, selection);
                     //cm.sendNext("兑换成功。 [#r"+selection+"#k] 兑换了:[#r"+(selection*100000)+"#k] 金币");
                     cm.sendOk("兑换成功！");
					 cm.dispose();
                } else {
                    cm.sendNext("条件不足，无法兑换。");
                    cm.dispose()
                }
            }if (beauty == 5) {
                if (cm.haveItem(4031637, selection*6)){
					 cm.gainItem(4031637, -selection*6);
					 cm.gainItem(4031640, selection);
                     //cm.sendNext("兑换成功。 [#r"+selection+"#k] 兑换了:[#r"+(selection*100000)+"#k] 金币");
                     cm.sendOk("兑换成功！");
					 cm.dispose();
                } else {
                    cm.sendNext("条件不足，无法兑换。");
                    cm.dispose()
                }
            }if (beauty == 6) {
                if (cm.haveItem(4031638, selection*4)){
					 cm.gainItem(4031638, -selection*4);
					 cm.gainItem(4031640, selection);
                     //cm.sendNext("兑换成功。 [#r"+selection+"#k] 兑换了:[#r"+(selection*100000)+"#k] 金币");
                     cm.sendOk("兑换成功！");
					 cm.dispose();
                } else {
                    cm.sendNext("条件不足，无法兑换。");
                    cm.dispose()
                }
            }if (beauty == 7) {
                if (cm.haveItem(4031639, selection*2)){
					 cm.gainItem(4031639, -selection*2);
					 cm.gainItem(4031640, selection);
                     //cm.sendNext("兑换成功。 [#r"+selection+"#k] 兑换了:[#r"+(selection*100000)+"#k] 金币");
                     cm.sendOk("兑换成功！");
					 cm.dispose();
                } else {
                    cm.sendNext("条件不足，无法兑换。");
                    cm.dispose()
                }
            }if (beauty == 8) {
                if (cm.haveItem(4031628, selection*10)){
					 cm.gainItem(4031628, -selection*10);
					 cm.gainItem(4031644, selection);
                     //cm.sendNext("兑换成功。 [#r"+selection+"#k] 兑换了:[#r"+(selection*100000)+"#k] 金币");
                     cm.sendOk("兑换成功！");
					 cm.dispose();
                } else {
                    cm.sendNext("条件不足，无法兑换。");
                    cm.dispose()
                }
            }if (beauty == 9) {
                if (cm.haveItem(4031641, selection*6)){
					 cm.gainItem(4031641, -selection*6);
					 cm.gainItem(4031644, selection);
                     //cm.sendNext("兑换成功。 [#r"+selection+"#k] 兑换了:[#r"+(selection*100000)+"#k] 金币");
                     cm.sendOk("兑换成功！");
					 cm.dispose();
                } else {
                    cm.sendNext("条件不足，无法兑换。");
                    cm.dispose()
                }
            }if (beauty == 10) {
                if (cm.haveItem(4031642, selection*4)){
					 cm.gainItem(4031642, -selection*4);
					 cm.gainItem(4031644, selection);
                     //cm.sendNext("兑换成功。 [#r"+selection+"#k] 兑换了:[#r"+(selection*100000)+"#k] 金币");
                     cm.sendOk("兑换成功！");
					 cm.dispose();
                } else {
                    cm.sendNext("条件不足，无法兑换。");
                    cm.dispose()
                }
            }if (beauty == 11) {
                if (cm.haveItem(4031643, selection*2)){
					 cm.gainItem(4031643, -selection*2);
					 cm.gainItem(4031644, selection);
                     //cm.sendNext("兑换成功。 [#r"+selection+"#k] 兑换了:[#r"+(selection*100000)+"#k] 金币");
                     cm.sendOk("兑换成功！");
					 cm.dispose();
                } else {
                    cm.sendNext("条件不足，无法兑换。");
                    cm.dispose()
                }
            }if (beauty == 12) {
                if (cm.haveItem(4031631, selection*10)){
					 cm.gainItem(4031631, -selection*10);
					 cm.gainItem(4031648, selection);
                     //cm.sendNext("兑换成功。 [#r"+selection+"#k] 兑换了:[#r"+(selection*100000)+"#k] 金币");
                     cm.sendOk("兑换成功！");
					 cm.dispose();
                } else {
                    cm.sendNext("条件不足，无法兑换。");
                    cm.dispose()
                }
            }if (beauty == 13) {
                if (cm.haveItem(4031645, selection*6)){
					 cm.gainItem(4031645, -selection*6);
					 cm.gainItem(4031648, selection);
                     //cm.sendNext("兑换成功。 [#r"+selection+"#k] 兑换了:[#r"+(selection*100000)+"#k] 金币");
                     cm.sendOk("兑换成功！");
					 cm.dispose();
                } else {
                    cm.sendNext("条件不足，无法兑换。");
                    cm.dispose()
                }
            }if (beauty == 14) {
                if (cm.haveItem(4031646, selection*4)){
					 cm.gainItem(4031646, -selection*4);
					 cm.gainItem(4031648, selection);
                     //cm.sendNext("兑换成功。 [#r"+selection+"#k] 兑换了:[#r"+(selection*100000)+"#k] 金币");
                     cm.sendOk("兑换成功！");
					 cm.dispose();
                } else {
                    cm.sendNext("条件不足，无法兑换。");
                    cm.dispose()
                }
            }if (beauty == 15) {
                if (cm.haveItem(4031647, selection*2)){
					 cm.gainItem(4031647, -selection*2);
					 cm.gainItem(4031648, selection);
                     //cm.sendNext("兑换成功。 [#r"+selection+"#k] 兑换了:[#r"+(selection*100000)+"#k] 金币");
                     cm.sendOk("兑换成功！");
					 cm.dispose();
                } else {
                    cm.sendNext("条件不足，无法兑换。");
                    cm.dispose()
                }
            }if (beauty == 100) {
                if (cm.haveItem(4031636, selection)){
					 cm.gainItem(4031636, -selection);
					cm.gainMeso(500000* selection); //加减金币
					// cm.gainExp(30000* selection);
					// cm.gainNX(selection*500);	//加减点券
                     //cm.sendNext("兑换成功。 [#r"+selection+"#k] 兑换了:[#r"+(selection*100000)+"#k] 金币");
                     cm.sendOk("兑换成功！");
					//cm.worldMessage(6,"玩家：["+cm.getName()+"]钓到了大鱼！兑换了"+selection*500+"点券！");
				    cm.喇叭(1,"玩家：["+cm.getName()+"]钓到了大鱼！兑换了 "+selection*500000+" 金币！");
					 cm.dispose();
                } else {
                    cm.sendNext("条件不足，无法兑换。");
                    cm.dispose()
                }
            }/*if (beauty == 101) {
                if (cm.haveItem(4031640, selection)){
					 cm.gainItem(4031640, -selection);
					 cm.gainDY(selection*100);	//加减抵用
					 //cm.gainExp(50000* selection);
                     //cm.sendNext("兑换成功。 [#r"+selection+"#k] 兑换了:[#r"+(selection*100000)+"#k] 金币");
                     cm.sendOk("兑换成功！");
					//cm.worldMessage(6,"玩家：["+cm.getName()+"]钓到了大鱼！兑换了"+selection*500+"点券！");
				    cm.喇叭(1,"玩家：["+cm.getName()+"]钓到了大鱼！兑换了 "+selection*100+" 抵用！");
					 cm.dispose();
                } else {
                    cm.sendNext("条件不足，无法兑换。");
                    cm.dispose()
                }
            }*/
			if (beauty == 101) {
                if (cm.haveItem(4031640, selection*5)){
					 cm.gainItem(4031640, -selection*5);
					 cm.gainItem(4000463, selection);
					 cm.gainExp(40000* selection);
					 //cm.gainNX(selection*500);	//加减点券
                     //cm.sendNext("兑换成功。 [#r"+selection+"#k] 兑换了:[#r"+(selection*100000)+"#k] 金币");
                     cm.sendOk("兑换成功！");
					//cm.worldMessage(6,"玩家：["+cm.getName()+"]钓到了大鱼！兑换了"+selection*500+"点券！");
				    cm.喇叭(1,"玩家：["+cm.getName()+"]钓到了大鱼！兑换了 "+selection+" 个国庆纪念币！");
					 cm.dispose();
                } else {
                    cm.sendNext("条件不足，无法兑换。");
                    cm.dispose()
                }
            }
			if (beauty == 102) {
                if (cm.haveItem(4031644, selection*10)){
					 cm.gainItem(4031644, -selection*10);
					 cm.gainItem(4000038, selection);
					 cm.gainExp(40000* selection);
					 //cm.gainNX(selection*500);	//加减点券
                     //cm.sendNext("兑换成功。 [#r"+selection+"#k] 兑换了:[#r"+(selection*100000)+"#k] 金币");
                     cm.sendOk("兑换成功！");
					//cm.worldMessage(6,"玩家：["+cm.getName()+"]钓到了大鱼！兑换了"+selection*500+"点券！");
				    cm.喇叭(1,"玩家：["+cm.getName()+"]钓到了大鱼！兑换了 "+selection+" 个金杯！");
					 cm.dispose();
                } else {
                    cm.sendNext("条件不足，无法兑换。");
                    cm.dispose()
                }
            }if (beauty == 103) {
                if (cm.haveItem(4031648, selection*5)){
					 cm.gainItem(4031648, -selection*5);
					 cm.gainItem(4000038, selection);
					 cm.gainExp(50000* selection);
                     //cm.sendNext("兑换成功。 [#r"+selection+"#k] 兑换了:[#r"+(selection*100000)+"#k] 金币");
                     cm.sendOk("兑换成功！");
					//cm.worldMessage(6,"玩家：["+cm.getName()+"]钓到了大鱼！兑换了"+selection*500+"点券！");
				    cm.喇叭(1,"玩家：["+cm.getName()+"]钓到了大鱼！兑换了 "+selection+" 个金杯！");
					 cm.dispose();
                } else {
                    cm.sendNext("条件不足，无法兑换。");
                    cm.dispose()
                }
            }if (beauty == 104) { 
                if (cm.haveItem(4031629, selection) && cm.haveItem(4031632, selection) && cm.haveItem(4031636, selection) && cm.haveItem(4031640, selection) && cm.haveItem(4031644, selection) && cm.haveItem(4031648, selection)){
					 cm.gainItem(4031629, -selection);
					 cm.gainItem(4031632, -selection);
					 cm.gainItem(4031636, -selection);
					 cm.gainItem(4031640, -selection);
					 cm.gainItem(4031644, -selection);
					 cm.gainItem(4031648, -selection);
					 cm.gainMeso(4000000* selection); //加减金币
					 cm.gainExp(500000* selection);
                     //cm.sendNext("兑换成功。 [#r"+selection+"#k] 兑换了:[#r"+(selection*100000)+"#k] 金币");
                     cm.sendOk("兑换成功！");
					//cm.worldMessage(6,"玩家：["+cm.getName()+"]钓到了大鱼！兑换了"+selection*500+"点券！");
				    cm.喇叭(1,"玩家：["+cm.getName()+"]钓鱼大丰收！获得了 "+selection*4000000+" 金币！");
					 cm.dispose();
                } else {
                    cm.sendNext("条件不足，无法兑换。");
                    cm.dispose()
                }
            }
            status = -1;
        } else {
            cm.dispose();
        }
    }
}
var acc = "#fEffect/CharacterEff/1112903/0/0#";//红桃心
var aaa = "#fUI/UIWindow.img/Quest/icon9/0#";//红色右箭头
var zzz = "#fUI/UIWindow.img/Quest/icon8/0#";//蓝色右箭头
var sss = "#fUI/UIWindow.img/QuestIcon/3/0#";//选择道具
var 蓝色角点 = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var 蓝色箭头 = "#fUI/UIWindow/Quest/icon2/7#";
var 红色箭头 = "#fUI/UIWindow/Quest/icon6/7#";
var 圆形 = "#fUI/UIWindow/Quest/icon3/6#";
var 美化new = "#fUI/UIWindow/Quest/icon2/7#";
var 美化ne = "#fUI/UIWindow/Quest/icon6/7#";
var 正方箭头 = "#fUI/Basic/BtHide3/mouseOver/0#";
var 中条猫 ="#fUI/ChatBalloon/37/n#";
var 猫右 =  "#fUI/ChatBalloon/37/ne#";
var 猫左 =  "#fUI/ChatBalloon/37/nw#";
var 右 =    "#fUI/ChatBalloon/37/e#";
var 左 =    "#fUI/ChatBalloon/37/w#";
var 下条猫 ="#fUI/ChatBalloon/37/s#";
var 猫下右 ="#fUI/ChatBalloon/37/se#";
var 猫下左 ="#fUI/ChatBalloon/37/sw#";
var 皇冠白 ="#fUI/GuildMark/Mark/Etc/00009004/16#";
var 蓝色角点 = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var 草莓 = "#fUI/GuildMark/Mark/Plant/00003000/1#"; // 红色草莓
var 草莓1 = "#fUI/GuildMark/Mark/Plant/00003000/10#"; // 淡蓝色草莓
var 草莓2 = "#fUI/GuildMark/Mark/Plant/00003000/11#"; // 紫色草莓
var 草莓3 = "#fUI/GuildMark/Mark/Plant/00003000/15#"; // 白色草莓
var 草莓4 = "#fUI/GuildMark/Mark/Plant/00003000/3#"; // 黄色草莓
var 草莓5 = "#fUI/GuildMark/Mark/Plant/00003000/8#"; // 绿色草莓
var 小黄星 = "#fItem/Etc/0427/04270001/Icon9/0#";  //
var 彩虹 ="#fEffect/ItemEff/1071085/effect/walk1/2#";
var 大黄星 = "#fItem/Etc/0427/04270001/Icon9/1#";  //
var 小兔 = "#fEffect/CharacterEff/1112960/3/0#";  //邪恶小兔 【小】
var 金币图标 = "#fUI/UIWindow.img/QuestIcon/7/0#";
