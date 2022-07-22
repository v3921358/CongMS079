/* ==================
 脚本类型:  双倍卡领取NPC	    
 脚本作者： 枫叶
 联系方式QQ： 1848350048
 =====================
 */
var SkillId=Array(//=
Array(1120003,112),//要加的技能ID ，职业ID
Array(1120005,112),//英雄
Array(1121000,112),//英雄
Array(1121001,112),//英雄
Array(1121002,112),//英雄
Array(1121006,112),//英雄
Array(1121008,112),//英雄
Array(1121010,112),//英雄
//Array(1121011,112),//英雄

Array(1220005,122),//圣骑
Array(1220006,122),//圣骑
Array(1220010,122),//圣骑
Array(1221000,122),//圣骑
Array(1221001,122),//圣骑
Array(1221002,122),//圣骑
Array(1221003,122),//圣骑
Array(1221004,122),//圣骑
Array(1221007,122),//圣骑
Array(1221009,122),//圣骑
Array(1221011,122),//圣骑
//Array(1221012,122),//圣骑

Array(1321001,132),//黑骑
Array(1321002,132),//黑骑
Array(1321003,132),//黑骑
Array(1320005,132),//黑骑
Array(1320006,132),//黑骑
Array(1321000,132),//黑骑
Array(1321007,132),//黑骑
Array(1320008,132),//黑骑
Array(1320009,132),//黑骑
//Array(1321010,132),//黑骑

Array(2121000,212),//火毒
Array(2121001,212),//火毒
Array(2121002,212),//火毒
Array(2121003,212),//火毒
Array(2121004,212),//火毒
Array(2121005,212),//火毒
Array(2121006,212),//火毒
Array(2121007,212),//火毒
//Array(2121008,212),//火毒

Array(2221000,222),//冰雷
Array(2221001,222),//冰雷
Array(2221002,222),//冰雷
Array(2221003,222),//冰雷
Array(2221004,222),//冰雷
Array(2221005,222),//冰雷
Array(2221006,222),//冰雷
Array(2221007,222),//冰雷
//Array(2221008,222),//冰雷

Array(2321000,232),//主教
Array(2321001,232),//主教
Array(2321002,232),//主教
Array(2321003,232),//主教
Array(2321004,232),//主教
Array(2321005,232),//主教
Array(2321006,232),//主教
Array(2321007,232),//主教
Array(2321008,232),//主教
//Array(2321009,232),//主教

Array(3121000,312),//神射
Array(3121002,312),//神射
Array(3121003,312),//神射
Array(3121004,312),//神射
Array(3120005,312),//神射
Array(3121006,312),//神射
Array(3121007,312),//神射
Array(3121008,312),//神射
//Array(3121009,312),//神射

Array(3221000,322),//箭神
Array(3221001,322),//箭神
Array(3221002,322),//箭神
Array(3221003,322),//箭神
Array(3220004,322),//箭神
Array(3221005,322),//箭神
Array(3221006,322),//箭神
Array(3221007,322),//箭神
//Array(3221008,322),//箭神

Array(4121000,412),//隐士
Array(4120002,412),//隐士
Array(4121003,412),//隐士
Array(4121004,412),//隐士
Array(4120005,412),//隐士
Array(4121006,412),//隐士
Array(4121007,412),//隐士
Array(4121008,412),//隐士
//Array(4121009,412),//隐士

Array(4221000,422),//侠盗
Array(4221001,422),//侠盗
Array(4220002,422),//侠盗
Array(4221003,422),//侠盗
Array(4221004,422),//侠盗
Array(4220005,422),//侠盗
Array(4221006,422),//侠盗
Array(4221007,422),//侠盗
//Array(4221008,422),//侠盗

Array(5121000,512),//冲锋队长
Array(5121001,512),//冲锋队长
Array(5121002,512),//冲锋队长
Array(5121003,512),//冲锋队长
Array(5121004,512),//冲锋队长
Array(5121005,512),//冲锋队长
Array(5121007,512),//冲锋队长
Array(5121009,512),//冲锋队长
Array(5121010,512),//冲锋队长
//Array(5121008,512),//冲锋队长

Array(5221000,522),//船长
Array(5220001,522),//船长
Array(5220011,522),//船长
Array(5220002,522),//船长
Array(5221003,522),//船长
Array(5221004,522),//船长
Array(5221006,522),//船长
Array(5221007,522),//船长
Array(5221008,522),//船长
Array(5221009,522),//船长
//Array(5221010,522),//船长


Array(4221000,2112),//战神(冒险岛勇士)
//Array(21121008,2112),//战神(勇士的意志)
Array(21120001,2112),//战神(攻击策略)
Array(21120002,2112),//战神(战神之舞)
Array(21120004,2112),//战神
Array(21120005,2112),//战神
Array(21120006,2112),//战神
Array(21120007,2112),//战神
Array(21121003,2112)//战神(战神的意志)

);
var  消费元宝=18;
var  技能币=5;
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
			if (jobid==112||jobid==122||jobid==132||jobid==212||jobid==222||jobid==232||jobid==312||jobid==322||jobid==412||jobid==422||jobid==512||jobid==522||jobid==2112)
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
				 cm.sendOk("请先进行四转后再来进行技能购买！");
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