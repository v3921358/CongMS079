
/* 
 * 脚本类型: cm
 * 脚本用途: 点卷中介
 * 脚本作者: 故事丶
 * 制作时间: 2014/12/18
 */

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

				
            var gsjb = "";
            gsjb = " 欢迎来到#r " + cm.getChannelServer().getServerName() + " #k这里是点卷中介兑换系统.\r\n#g================================================\r\n#r1人民币 = 1充值币#k\t\t当前充值币:#r"+cm.getrmb()+" 元\r\n";
			gsjb += "#L5##b[#r充值币#k]兑换[#r点卷#k]  #b比例 - (#r1 = 3000点#b)#l\r\n\r\n";
			gsjb += "#L13##b[#r点卷#k]兑换#v4000463#[#r货币#k]  #b比例 - (#r1000 = 1#b)#k#l\r\n\r\n";
			
            gsjb += "#L11##b#v4000463#[#r货币#k]兑换[#r金币#k]  #b比例 - (#r1 = 2200W#b)#l\r\n";
            gsjb += "#L12##b#v4000463#[#r货币#k]兑换[#r点卷#k]  #b比例 - (#r1 = 950#b)#k#l\r\n";
			gsjb += "#L1##b#v4000463##r货币 x96个 #k兑换 #b#v3010788##z3010788# #r 1个 #l\r\n"//3
			gsjb += "#L2##b#v4000463##r货币 x96个 #k兑换 #b#v3015304##z3015304# #r 1个 #l\r\n"//3
			gsjb += "#L3##b#v4310049##r货币 x20个 #k兑换 #b#v4310108##z4310108# #r 1个 #l\r\n"//3
            gsjb += "#L14##b#v4001126#[#r货币#k]兑换[#r抵用卷#k]  #b比例 - (#r100 = 1抵用卷#b)#l\r\n";
            gsjb += "#L15##b#v4000313#[#r货币#k]兑换[#r点卷#k]  #b比例 - (#r10 = 1点卷#b)#k#l\r\n";

						
            cm.sendSimple(gsjb);
        } else if (status == 1) {
            if (cm.getPlayer() >= 1 && cm.getPlayer() <= 5) {
                cm.sendOk("GM不能参与兑换。");
                cm.dispose();
            }
			
            if (selection == 0) {
                if (cm.getPlayer().getCSPoints(0) / 500 == 0) {
                    cm.sendNext("您的帐户点卷不足无法兑换国庆纪念币。");
                    status = -1;
                } else {
                    beauty = 1;
                    cm.sendGetNumber("请输入#r点卷#k兑换#b#z4000463##k的数量:\r\n#b比例 - (#r500 = 1#b)\r\n你的账户信息 - \r\n    点卷数量: #r" +
                            cm.getPlayer().getCSPoints(0) + " \r\n", 1, 1, cm.getPlayer().getCSPoints(0) / 500);

                }

            
            } else if (selection == 1) {
               if (cm.haveItem(4000463) == 64) {
                    cm.sendNext("你的货币不足。");
                    status = -1;
                } else {
                    beauty = 1;
                    cm.sendGetNumber("请输入要兑换#v3010788##z3010788# 的数量。\r\n当前货币: #r#c4000463##k \r\n\r\n\r\n", 1, 1, 10000);

                }
            } else if (selection == 2) {
               if (cm.haveItem(4000463) == 96) {
                    cm.sendNext("你的货币不足。");
                    status = -1;
                } else {
                    beauty = 2;
                    cm.sendGetNumber("请输入要兑换#v3015304##z3015304# 的数量。\r\n当前货币: #r#c4000463##k \r\n\r\n\r\n", 1, 1, 10000);

                }
				
			 } else if (selection == 3) {
               if (cm.haveItem(4310049) == 20) {
                    cm.sendNext("你的货币不足。");
                    status = -1;
                } else {
                    beauty = 3;
                    cm.sendGetNumber("请输入要兑换#v4310108##z4310108# 的数量。\r\n当前货币: #r#c4310049##k \r\n\r\n\r\n", 1, 1, 10000);

                }

			}else if (selection == 5) {//余额兑换点卷
               // var iter = cm.getChar().getInventory(MapleInventoryType.ETC).listById(4000463).iterator();
                if(cm.getmoneyb() < 1){
                    cm.sendNext("您的充值币不足兑换.请赞助后再来兑换点卷");
                    status = -1;
                } else {
                    beauty = 5
                    cm.sendGetNumber("请输入[#r充值币#k]的数量:\r\n#b比例为:(#r 1 充值币 = 3000 点卷#b)\r\n当前充值币:#r"+cm.getmoneyb()+" 元 \r\n", 1, 1, 100000 );

                }
				
				
				
				

            } else if (selection == 8) {
               if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendNext("你的点券不足。");
                    status = -1;
                } else {
                    beauty = 8;
                    cm.sendGetNumber("请输入要兑换#v5030001# 的数量。\r\n当前点券: #r"+cm.getPlayer().getCSPoints(1)+"#k \r\n\r\n\r\n", 1, 1, 1);

                }
				
			} else if (selection == 9) {
               if (cm.haveItem(4001028) == 0) {
                    cm.sendNext("你的货币不足。");
                    status = -1;
                } else {
                    beauty = 9;
                    cm.sendGetNumber("请输入要兑换#v4250602# 的数量。\r\n当前货币: #r#v4001028##c4001028##k \r\n\r\n\r\n", 1, 1, 100);

                }
				
				

				
				





   
            }else if (selection == 11) {
                if (cm.haveItem(4000463) == 0) {
                    cm.sendNext("您的货币不足兑换.");
                    status = -1;
                } else {
                    beauty = 11

   cm.sendGetNumber("请输入兑换的货币数量:\r\n#b比例 - (#r1 #z4000463# = 2200 万金币#b)\r\n  当前货币: #r #c4000463# 个 \r\n", 1, 1, 100000 );
                }



            }else if (selection == 12) {
               // var iter = cm.getChar().getInventory(MapleInventoryType.ETC).listById(4000463).iterator();
                if (cm.haveItem(4000463) == 0) {
                    cm.sendNext("您的货币不足兑换.");
                    status = -1;
                } else {
                    beauty = 12
                    cm.sendGetNumber("请输入[#r货币#k]的数量:\r\n#b比例为:(#r 1 #z4000463# = 950 点卷#b)\r\n当前货币: #r #c4000463# 个 \r\n", 1, 1, 100000 );

                }
				
            }else if (selection == 13) {
               // var iter = cm.getChar().getInventory(MapleInventoryType.ETC).listById(4000463).iterator();
                if (cm.getPlayer().getCSPoints(1)  == 0) {
                    cm.sendNext("您的点券不足兑换.");
                    status = -1;
                } else {
                    beauty = 13
                   //   cm.sendGetNumber("请输入兑换#r#z4000463##k的数量:\r\n#b比例 - (#r1500 = 1#b)\r\n你的点卷数量: #r" +
                       //     cm.getPlayer().getCSPoints(0) + " \r\n", 1, 1, cm.getPlayer().getCSPoints(0) );
 cm.sendGetNumber("请输入兑换#r#z4000463##k的数量:\r\n#b比例 - (#r1000 = 1#b)\r\n你的点卷数量: #r" +cm.getPlayer().getCSPoints(1) + " \r\n", 1, 1, 100000 );

					   
					   
                }


            }else if (selection == 14) {
               // var iter = cm.getChar().getInventory(MapleInventoryType.ETC).listById(4000463).iterator();
                if (cm.haveItem(4001126) == 0) {
                    cm.sendNext("您的枫叶不足兑换.");
                    status = -1;
                } else {
                    beauty = 14
                    cm.sendGetNumber("请输入[#r枫叶#k]的数量:\r\n#b比例为:(#r 100个 #z4001126# = 1 抵用卷#b)\r\n当前货币: #r #c4001126# 个 \r\n", 1, 1, 100000 );

                }

				            }else if (selection == 15) {
               // var iter = cm.getChar().getInventory(MapleInventoryType.ETC).listById(4000463).iterator();
                if (cm.haveItem(4000313) == 0) {
                    cm.sendNext("您的黄金枫叶不足兑换.");
                    status = -1;
                } else {
                    beauty = 15
                    cm.sendGetNumber("请输入[#r黄金枫叶#k]的数量:\r\n#b比例为:(#r 10个 #z4000313# = 1 点卷#b)\r\n当前货币: #r #c4000313# 个 \r\n", 1, 1, 100000 );

                }




            }else if (selection == 22) {
                var iter = cm.getChar().getInventory(MapleInventoryType.ETC).listById(4000463).iterator();
                if (cm.getPlayer().getCSPoints(0)  == 0) {
                    cm.sendNext("您的点券不足兑换.");
                    status = -1;
                } else {
                    beauty = 22
                      cm.sendGetNumber("请输入兑换#r#z2040710##k的数量:\r\n#b比例 - (#r10000 = 1#b)\r\n你的点卷数量: #r" +
                            cm.getPlayer().getCSPoints(0) + " \r\n", 1, 1, cm.getPlayer().getCSPoints(0) );

                }

            }
               


//=================================================================================			
			
			
			
			
        } else if (status == 2) {
           if (beauty == 1) {
               if (cm.haveItem(4000463, selection*64)){
					//cm.setmoneyb(-selection*5);  //加减充值币
					 cm.gainItem(4000463, -selection*64);
					 cm.gainItem(3010788, selection);
                     cm.sendNext("成功兑换了:[#r"+selection+"#k]个#v3010788##z3010788#");
					 cm.dispose();
                } else {
                    cm.sendNext("条件不足无法兑换。");
                    cm.dispose()
                }
            } else if (beauty == 2) {
                if (cm.haveItem(4000463, selection*96)){
					 cm.gainItem(4000463, -selection*96);
					 cm.gainItem(3015304, selection);
                     cm.sendNext("成功兑换了:[#r"+selection+"#k]个#v3015304##z3015304#");
					 cm.dispose();
                } else {
                    cm.sendNext("条件不足无法兑换。");
                    cm.dispose()
                }


            } else if (beauty == 3) {
                if (cm.haveItem(4310049, selection*20)){
					 cm.gainItem(4310049, -selection*20);
					 cm.gainItem(4310108, selection);
                     cm.sendNext("成功兑换了:[#r"+selection+"#k]个#v4310108##z4310108#");
					 cm.dispose();
                } else {
                    cm.sendNext("条件不足无法兑换。");
                    cm.dispose()
                }

            } else if (beauty == 4) {
                if (cm.haveItem(4000463, selection*20)){
					 cm.gainItem(4000463, -selection*20);
					 cm.gainItem(5570000, selection);
                     cm.sendNext("成功兑换了:[#r"+selection+"#k]个#v5570000##z5570000#");
					 cm.dispose();
                } else {
                    cm.sendNext("条件不足无法兑换。");
                    cm.dispose()
                }

            }else if (beauty == 5) {
                 if (cm.getmoneyb() >= 1){
					 cm.setmoneyb(-selection);
                } else {
                    cm.sendNext("您的输入的数量错误，无法兑换。");
                    cm.dispose();
                }
				var czb=cm.getmoneyb();
				 if (czb < 0){
					 cm.setmoneyb(+selection);
                    cm.sendNext("您的充值币不足，无法兑换。请赞助后再来兑换");
                    cm.dispose();
                } else {
                    cm.gainNX(+3000 * selection);
					cm.gaincz(selection);
                    //cm.gainItem(4000463, +selection);
                    cm.sendNext("兑换成功。 [#r"+selection+"#k] 充值币兑换了:[#r"+(selection*3000)+"#k]点卷");
                    cm.dispose();
                }

            }  else if (beauty == 6) {
                if (cm.haveItem(4000463, selection*10)){
					 cm.gainItem(4000463, -selection*10);
					 cm.gainItem(5211047, selection,3);
                     cm.sendNext("成功兑换了:[#r"+selection+"#k]个#v5211047#，*#r下线或者换线生效。#k");
					 cm.dispose();
                } else {
                    cm.sendNext("条件不足无法兑换。");
                    cm.dispose()
                }
				


            } else if (beauty == 7) {
                if (cm.haveItem(4000463, selection*30)){
					 cm.gainItem(4000463, -selection*30);
					 cm.gainItem(5030001, selection);
                     cm.sendNext("成功兑换了:[#r"+selection+"#k]个#v5030001#");
					 cm.dispose();
                } else {
                    cm.sendNext("条件不足无法兑换。");
                    cm.dispose()
                }

            } else if (beauty == 8) {
                if (cm.getPlayer().getCSPoints(1) > 199){
					cm.gainNX(-200);	//加减点券
					 cm.gainItem(5030001, 1,1);
                     cm.sendNext("购买成功。");
					 cm.dispose();
                } else {
                    cm.sendNext("条件不足无法兑换。");
                    cm.dispose()
                }

			



				
            }else if (beauty == 10) {
                if (cm.haveItem(4000463, selection)){
					 cm.gainItem(4000463, -selection);
					 cm.setmoneyb(+selection);
                    cm.sendNext("兑换成功。 [#r"+selection+"#k] 货币兑换了:[#r"+(selection)+"#k] 充值币");
                    //cm.gainNX(+1000 * selection);
					 cm.dispose();
                } else {
                    cm.sendNext("您的输入的数量错误，无法兑换。");
                    cm.dispose()
                }
				
				
				
            }else if (beauty == 111) {
                 if (cm.getmoneyb() >= 1){
					 cm.setmoneyb(-selection);
                } else {
                    cm.sendNext("您的输入的数量错误，无法兑换。");
                    cm.dispose();
                }
				var czb=cm.getmoneyb();
				 if (czb < 0){
					 cm.setmoneyb(+selection);
                    cm.sendNext("您的货币不足，无法兑换。");
                    cm.dispose();
                } else {
                    //cm.gainNX(+1000 * selection);
                    cm.gainItem(4000463, +selection);
                    cm.sendNext("兑换成功。 [#r"+selection+"#k] 充值币兑换了:[#r"+(selection*1)+"#k]个 货币");
                    cm.dispose();
                }				
				
				
				
            }else if (beauty == 11) {//国庆币兑换金币
                 if (cm.haveItem(4000463, selection)){
					 cm.gainItem(4000463, -selection);
                    cm.sendNext("兑换成功。 [#r"+selection+"#k] 货币兑换了:[#r"+(selection*22000000)+"#k]金币");
                     cm.gainMeso(+22000000* selection);
					 cm.dispose();
                } else {
                    cm.sendNext("您的输入的数量错误，无法兑换。");
                    cm.dispose()
                }

            }else if (beauty == 12) {//国庆币兑换点卷
                 if (cm.haveItem(4000463, selection)){
					 cm.gainItem(4000463, -selection);
                    cm.sendNext("兑换成功。 [#r"+selection+"#k] 货币兑换了:[#r"+(selection*950)+"#k]个 点卷");
                    cm.gainNX(+950 * selection);
					 cm.dispose();
                } else {
                    cm.sendNext("您的输入的数量错误，无法兑换。");
                    cm.dispose()
                }

				

            }else if (beauty == 13) {//点卷兑换国庆币
                 if (cm.getPlayer().getCSPoints(1) >= selection * 1000){
					
                    cm.gainNX(-selection * 1000);
                    cm.gainItem(4000463, selection);
                    cm.sendOk("您成功兑了#v4000463# x #r" + selection + " #k  个")
					 cm.dispose();
                } else {
                    cm.sendNext("您的输入的数量错误，无法兑换。");
                    cm.dispose()
                }
				
            }else if (beauty == 14) {//枫叶兑换抵用卷
                 if (cm.haveItem(4001126, selection* 100)){
					 cm.gainItem(4001126, -selection* 100);
                    cm.sendNext("兑换成功。 [#r"+selection+"#k] 个枫叶兑换了:[#r"+(selection*1)+"#k] 抵用卷");
                    cm.gainDY(+1* selection);
					 cm.dispose();
                } else {
                    cm.sendNext("您的输入的数量错误，无法兑换。");
                    cm.dispose()
                }


            }else if (beauty == 15) {//黄金枫叶兑换点卷
                 if (cm.haveItem(4000313, selection* 10)){
					 cm.gainItem(4000313, -selection* 10);
                    cm.sendNext("兑换成功。 [#r"+selection+"#k] 黄金枫叶兑换了:[#r"+(selection*1)+"#k] 点卷");
                    cm.gainNX(+1 * selection);
					 cm.dispose();
                } else {
                    cm.sendNext("您的输入的数量错误，无法兑换。");
                    cm.dispose()
                }


            }
            status = -1;
        } else {
            cm.dispose();
        }
    }
}
