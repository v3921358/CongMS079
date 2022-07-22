/* ==================
 脚本类型:  双倍卡领取NPC	    
 脚本作者： 枫叶
 联系方式QQ： 1848350048
 =====================
 */
var SkillId=Array(//=

Array(15111004,1511),//奇袭者
Array(15111005,1511),//奇袭者

Array(14111005,1411),//夜行者
Array(14110004,1411),//夜行者

Array(13111002,1311),//风灵使者
Array(13110003,1311),//风灵使者

Array(12111003,1211),//炎术士
Array(12111004,1211),//炎术士

Array(11110005,1111),//魂骑士
Array(11111004,1111)//魂骑士


);
var  消费元宝=58;
var  技能币=20;
var MaxLevel=0;

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
        if (status == 0) {
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }
			//显示物品ID图片用的代码是  #v这里写入ID#
			text +=  " \t"+彩虹+"  #e#d 高 级 技 能 学 院 #k#n  #r  "+彩虹+"#b#k#n\r\r\n";
            text += "#d欢迎来到职业技能快速学习服务中心\r\n如果您觉得做任务学习技能麻烦，可以来这里快速直接学习到哦！#k\r\n"
			text += "#L1##r#e技能快速学习#e#l  #k  "
			
            cm.sendSimple(text);
        }
		else if(status==1){
		
			var jobid=cm.getPlayer().getJob();
			if (jobid==1111||jobid==1211||jobid==1311||jobid==1411||jobid==1511)
			{
				text="";
				text +=  " \t"+彩虹+"  #e#d 高 级 技 能 学 院 #k#n  #r  "+彩虹+"#b#k#n\r\r\n";
				text+="#d欢迎来到职业技能服务中心#k\r\n"
				text +="每次学习都将消费#r"+消费元宝+"#k元宝或#r"+技能币+"#k技能币，优先扣除技能币\r\n"
                text+="【拥有#r"+cm.getmoneyb()+"#k元宝】【拥有#r#c4001129##k技能币】#k\r\n"
				for(i=0;i<SkillId.length;i++)
				{
			    if(SkillId[i][1]==jobid)
				{
		        text+=" #L"+SkillId[i][0]+"##s"+SkillId[i][0]+"##q"+SkillId[i][0]+"#  [#r学习#k]#l \r\n"
				}
				}
				cm.sendSimple(text);
			}
				else 
				{
				 cm.sendOk("您不是骑士团或还没达到三转");
				 cm.dispose();
				 return;
			}
			//cm.sendSimple(jobid);
			
	    }
		else if(status ==2)
		{
			if(selection>100000)
			{

				if(cm.getPlayer().getSkillLevel2(selection)!=-1)//技能等级大于1
				{
					cm.sendOk("你已经获得了该技能,请不要重复学习!");
					oversend();
				}
				else 
				{   if(cm.haveItem(4001129,5))
					{
					cm.gainItem(4001129,-5);
					MaxLevel=cm.getPlayer().getMasterLevel(selection);
					if(MaxLevel<=0){
					cm.teachSkill(selection,0,10);
					}
					else{
					cm.teachSkill(selection,0,10);
					}
					cm.sendOk("成功学习#s"+selection+"##q"+selection+"# ");
					cm.dispose();
					return;
					}
					if(cm.getmoneyb() > 消费元宝)
					{
						
					cm.setmoneyb(-消费元宝);
					MaxLevel=cm.getPlayer().getMasterLevel(selection);
					if(MaxLevel<=0){
					cm.teachSkill(selection,0,10);
					}
					else{
					cm.teachSkill(selection,0,10);
					}
					cm.sendOk("成功学习#s"+selection+"##q"+selection+"# ");
					cm.dispose();
					return;
					}
					
					
					else
					{
						cm.sendOk("元宝或技能币不足无法学习该技能！");
						oversend();
					}
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