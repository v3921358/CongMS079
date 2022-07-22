
var status = 0;
var jobName;
var job;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.sendOk("天气很好哦~~如果你改变想法记的随时来找我。祝你好运！");
        cm.dispose();
    } else {
        if (mode == 1)
            status++;
        else
            status--;
        if (status == 0) {
            cm.sendNext("嗨，我是 #b战神转职官#k 我可以帮助你快速转职哦~~！");
        } else if (status == 1) {
            if(cm.getJob() >= 2000){
                cm.sendNext("哇~~战神战起来！新职业哦~我很高兴为你服务哦！！！");
                status = 163;
                return;
            }
              else {
				cm.sendNext("只为战童服务哦！！！");
                cm.dispose();
            }

        }     else if (status == 154) {
            
            cm.sendSimple("只有战神才可以使用快速转职 ");


      
            cm.sendYesNo("不错的选择哦，确定要成为一名 #b[" + jobName + "] #k吗？"); 
        } else if (status == 156) {
                cm.changeJob(job);
                cm.sendOk("转职成功！加油锻炼，当你变的强大的时候记的来找我哦！");
				if (job == 2100) {
				cm.teachSkill(21000000,0,10); //矛连击强化
				cm.teachSkill(21001001,0,15); //战斗步伐
				cm.teachSkill(21000002,0,20); //双重重击
				cm.teachSkill(21001003,0,20); //快速矛
				}
                cm.serverNotice("[转职系统]: 恭喜 [" + cm.getPlayer().getName() + "] 在NPC：管理员 快速转职成功！");
                cm.dispose();
            
        } else if (status == 161) {
            if(cm.getJob() == 1000 && cm.getPlayer().getLevel()>=10){
                cm.sendSimple("看起来你还是一个初心者,快选择一个适合自己的职业吧!#b\r\n#L0#魂骑士#l #L1#炎术士#l #L2#风灵使者#l #L3#夜行者#l #L4#奇袭者#l#k");
            }else if(parseInt(cm.getJob() / 100) >10 && cm.getPlayer().getLevel()>=30 && cm.getJob()%100 == 0){
                cm.sendYesNo("您真的确定要进行第二次转职了吗？");
            }else if(parseInt(cm.getJob() / 100) >10 && cm.getPlayer().getLevel()>=70 && cm.getJob()%10 == 0){
                cm.sendYesNo("您真的确定要进行第三次转职了吗？");
            }else{
                cm.sendOk("您目前的条件不能使用我的服务哦!");
                cm.dispose();
            }
        }  else if (status == 164) {
            if(cm.getJob() == 2000 && cm.getPlayer().getLevel() >=10){
                cm.sendYesNo("战神战起来！\r\n看起来你还是一个战童,您确定要进行第一次转职吗？");
            } else if(cm.getJob() == 2100 && cm.getPlayer().getLevel() >=30) {
                cm.sendYesNo("战神战起来！是否给我500万金币,我可以帮您进行第二次转职哦？");
            } else if(cm.getJob() == 2110 && cm.getPlayer().getLevel() >=70){
                cm.sendYesNo("战神战起来！是否给我1000万金币,我可以帮您进行第三次转职哦？");
            } else if(cm.getJob() == 2111 && cm.getPlayer().getLevel() >=120) {
                cm.sendYesNo("战神战起来！是否给我2000万金币,我可以帮您进行第四次转职哦？");
            } else if(cm.getJob() == 2112 && cm.getPlayer().getLevel() >120) {
                cm.sendOk("你已经完成了所有的转职工作。继续加油吧！！");
            } else {
                cm.sendOk("按照您目前的条件，我还不能为您服务哦！加油吧！");
                cm.dispose();
            }
        } else if (status == 165) {
            if(cm.getJob() == 2000 && cm.getPlayer().getLevel() >=10){
                cm.changeJob(2100);
                //cm.gainItem(1142129,1);
                //cm.gainItem(1442077,1);
                //cm.gainItem(2000022,50);
                //cm.gainItem(2000023,50);
				cm.teachSkill(21000000,0,10); //矛连击强化
				cm.teachSkill(21001001,0,20); //战斗步伐
				cm.teachSkill(21000002,0,20); //双重重击
				cm.teachSkill(21001003,0,20); //快速矛
                cm.serverNotice("[转职系统]: 恭喜 [" + cm.getPlayer().getName() + "] 在NPC：战神转职官 快速转职为战神职业！");
                cm.sendOk("转职成功！加油锻炼，当你变的强大的时候记的来找我哦！");
            } else if(cm.getJob() == 2100 && cm.getPlayer().getLevel() >=30){
				
				if(cm.getMeso() < 5000000) {
                cm.sendOk("抱歉您的金币不足500万，请凑足了再来！");
                cm.dispose();}
                else{
                cm.changeJob(2110);
                cm.gainMeso(-5000000);
                cm.gainItem(1442078,1);
				cm.teachSkill(21100000,0,20); //精准矛
				cm.teachSkill(21100001,0,20); //三重重击
				cm.teachSkill(21100002,0,30); //战神突进
                                // cm.teachSkill(21101003,0,20); //抗压
				cm.teachSkill(21100004,0,20); //斗气爆裂
				cm.teachSkill(21100005,0,20); //连环吸血
                cm.serverNotice("[转职系统]: 恭喜 [" + cm.getPlayer().getName() + "] 在NPC：战神转职官花费500万金币 快速完成战神2次转职！");
				
                cm.sendOk("转职成功！加油锻炼，当你变的强大的时候记的来找我哦！");
				}
            } else if(cm.getJob() == 2110 && cm.getPlayer().getLevel() >=70){
				
				if(cm.getMeso() < 10000000) {
                cm.sendOk("抱歉您的金币不足1000万，请凑足了再来！");
                cm.dispose();}
                else{
                cm.gainMeso(-10000000);
                cm.getPlayer().gainAp(5);
                cm.changeJob(2111);
				cm.teachSkill(21110000,0,20); //爆击强化
				cm.teachSkill(21111001,0,20); //灵巧击退
				cm.teachSkill(21110002,0,20); //全力挥击
				cm.teachSkill(21110003,0,30); //终极投掷
				cm.teachSkill(21110004,0,30); //幻影狼牙
				cm.teachSkill(21111005,0,20); //冰雪矛
				cm.teachSkill(21110006,0,20); //旋风
				//cm.teachSkill(21110007,0,0); //全力挥击
				//cm.teachSkill(21110008,0,0); //全力挥击
               // cm.teachSkill(21120009,0,0); //战神之舞
               // cm.teachSkill(21120010,0,0); //战神之舞

                cm.sendOk("转职成功！加油锻炼，当你变的强大的时候记的来找我哦！");
            }
            }else if(cm.getJob() == 2111 && cm.getPlayer().getLevel() >=120){
				
				if(cm.getMeso() < 20000000) {
                cm.sendOk("抱歉您的金币不足2000万，请凑足了再来！");
                cm.dispose();}
                else{
		        cm.gainMeso(-20000000);
                //cm.gainItem(1142132,1);
                cm.getPlayer().gainAp(5);
				cm.teachSkill(21121000,0,10); //冒险岛勇士
				cm.teachSkill(21120001,0,10); //攻击策略
				cm.teachSkill(21120002,0,10); //战神之舞
               // cm.teachSkill(21120009,0,0); //战神之舞
               // cm.teachSkill(21120010,0,0); //战神之舞
				cm.teachSkill(21121003,0,10); //战神的意志
				//cm.teachSkill(21120004,0,10); //防守策略
				//cm.teachSkill(21120005,0,10); //巨熊咆哮
                                // cm.teachSkill(21120006,0,10); //钻石星辰
				// cm.teachSkill(21120007,0,10); //战神之盾
				cm.teachSkill(21121008,0,5); //勇士的意志
                cm.changeJob(2112);
				
				
				
                cm.sendOk("转职成功！希望您以后的冒险之路顺利！");
            }
			}
            cm.dispose();
        } else {
            cm.dispose();
        }  

    }
}
