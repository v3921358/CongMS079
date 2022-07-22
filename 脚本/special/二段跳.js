//技能脚本   作者：枫叶   QQ：1848350048
//承接开服一条龙
var 点卷图标 = "#fUI/CashShop/CashItem/0#";
var 消费元宝=200;
var MaxLevel=0;
var newskillId=Array( 
14101004,
0
);

function start() {
    status = -1;

    action(1, 0, 0);
}


function action(mode, type, selection) {
    if (status == 0 && mode == 0) {
        cm.dispose();
        return;
    }
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
        if(status==0)
		{

				text="";
				text +=  "    \t"+彩虹+"  #e#d 偷 学 技 能 #k#n  #r  "+彩虹+"#b#k#n\r\r\n";
				text += "#d哈喽！有没有羡慕过别人的技能呢？如果有，给我200元宝就可以偷偷的教你学习到哦.同个技能第二,三次学习半价呢!#k\r\n"
				
				text+="#r点击学习好技能，即可在键盘设定F12键拿取技能使用！#k\r\n"
				
				text+="#r"+点卷图标+"当前元宝："+cm.getmoneyb()+"  #k\r\n"
				for(i=0;i<newskillId.length;i++)//
				{
				if(newskillId[i]!=0){	
		        text+=" #L"+newskillId[i]+"##s"+newskillId[i]+"##q"+newskillId[i]+"#  [#r学习#k] 当前等级:"+cm.getPlayer().getSkillLevel(newskillId[i])+" - 最高等级:"+cm.getPlayer().getMasterLevel(newskillId[i])+"#l \r\n"
				}
				}
				cm.sendSimple(text);
			
	    }
		else if(status ==1){
		  
	        var jobid=cm.getPlayer().getJob();
			
			
			if(selection>10000){
				var getLevelskill=0;
				var nowSkilLevel=cm.getPlayer().getSkillLevel(selection);
				var MaxLevel=cm.getPlayer().getMasterLevel(selection);
				var palyerid=cm.getPlayer().getId();
				
				if(nowSkilLevel==MaxLevel&&nowSkilLevel!=0){
					
					cm.新键盘上技能(palyerid,88,1,selection,nowSkilLevel);
					cm.sendOk("该技能已经学满了!");
					cm.dispose();
				    return;
					}
					
					if(nowSkilLevel+10>MaxLevel&&MaxLevel!=0){
						getLevelskill=MaxLevel;
						
					}else{
						getLevelskill=nowSkilLevel+10;
					}
					if(MaxLevel!=0){
					消费元宝 =消费元宝/2;//第二次起,消费半价
					}
					
					if(cm.getmoneyb() >= 消费元宝){
						
					cm.setmoneyb(-消费元宝);
					MaxLevel=cm.getPlayer().getMasterLevel(selection);
					
					text="";
					
					cm.新键盘上技能(palyerid,88,1,selection,nowSkilLevel+10);
					text+="成功学习#s"+selection+"##q"+selection+"#  Max："+MaxLevel
					cm.sendOk(text);
					cm.dispose();
					return;
					}
					else
					{
						cm.sendOk("元宝不足无法学习该技能！");
						oversend();
					}
				
			}
		}
	
}

function oversend()
{
	cm.dispose();
	return;
}
var 彩虹 ="#fEffect/ItemEff/1071085/effect/walk1/2#";
