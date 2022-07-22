package server;

public class StructPotentialItem
{
    public byte incSTR;
    public byte incDEX;
    public byte incINT;
    public byte incLUK;
    public byte incACC;
    public byte incEVA;
    public byte incSpeed;
    public byte incJump;
    public byte incPAD;
    public byte incMAD;
    public byte incPDD;
    public byte incMDD;
    public byte prop;
    public byte time;
    public byte incSTRr;
    public byte incDEXr;
    public byte incINTr;
    public byte incLUKr;
    public byte incMHPr;
    public byte incMMPr;
    public byte incACCr;
    public byte incEVAr;
    public byte incPADr;
    public byte incMADr;
    public byte incPDDr;
    public byte incMDDr;
    public byte incCr;
    public byte incDAMr;
    public byte RecoveryHP;
    public byte RecoveryMP;
    public byte HP;
    public byte MP;
    public byte level;
    public byte ignoreTargetDEF;
    public byte ignoreDAM;
    public byte DAMreflect;
    public byte mpconReduce;
    public byte mpRestore;
    public byte incMesoProp;
    public byte incRewardProp;
    public byte incAllskill;
    public byte ignoreDAMr;
    public byte RecoveryUP;
    public boolean boss;
    public short incMHP;
    public short incMMP;
    public short attackType;
    public short potentialID;
    public short skillID;
    public int optionType;
    public int reqLevel;
    public String face;
    
    @Override
    public final String toString() {
        final StringBuilder ret = new StringBuilder();
        if (this.incMesoProp > 0) {
            ret.append("Gives MESO(not coded): ");
            ret.append((int)this.incMesoProp);
            ret.append(" ");
        }
        if (this.incRewardProp > 0) {
            ret.append("Gives ITEM(not coded): ");
            ret.append((int)this.incRewardProp);
            ret.append(" ");
        }
        if (this.prop > 0) {
            ret.append("Probability(not coded): ");
            ret.append((int)this.prop);
            ret.append(" ");
        }
        if (this.time > 0) {
            ret.append("Duration(not coded): ");
            ret.append((int)this.time);
            ret.append(" ");
        }
        if (this.attackType > 0) {
            ret.append("Attack Type(not coded): ");
            ret.append((int)this.attackType);
            ret.append(" ");
        }
        if (this.incAllskill > 0) {
            ret.append("Gives ALL SKILLS: ");
            ret.append((int)this.incAllskill);
            ret.append(" ");
        }
        if (this.skillID > 0) {
            ret.append("Gives SKILL: ");
            ret.append((int)this.skillID);
            ret.append(" ");
        }
        if (this.boss) {
            ret.append("BOSS ONLY, ");
        }
        if (this.face.length() > 0) {
            ret.append("Face Expression: ");
            ret.append(this.face);
            ret.append(" ");
        }
        if (this.RecoveryUP > 0) {
            ret.append("Gives Recovery % on potions: ");
            ret.append((int)this.RecoveryUP);
            ret.append(" ");
        }
        if (this.DAMreflect > 0) {
            ret.append("Reflects Damage when Hit: ");
            ret.append((int)this.DAMreflect);
            ret.append(" ");
        }
        if (this.mpconReduce > 0) {
            ret.append("Reduces MP Needed for skills: ");
            ret.append((int)this.mpconReduce);
            ret.append(" ");
        }
        if (this.ignoreTargetDEF > 0) {
            ret.append("Ignores Monster DEF %: ");
            ret.append((int)this.ignoreTargetDEF);
            ret.append(" ");
        }
        if (this.RecoveryHP > 0) {
            ret.append("Recovers HP: ");
            ret.append((int)this.RecoveryHP);
            ret.append(" ");
        }
        if (this.RecoveryMP > 0) {
            ret.append("Recovers MP: ");
            ret.append((int)this.RecoveryMP);
            ret.append(" ");
        }
        if (this.HP > 0) {
            ret.append("Recovers HP: ");
            ret.append((int)this.HP);
            ret.append(" ");
        }
        if (this.MP > 0) {
            ret.append("Recovers MP: ");
            ret.append((int)this.MP);
            ret.append(" ");
        }
        if (this.mpRestore > 0) {
            ret.append("Recovers MP: ");
            ret.append((int)this.mpRestore);
            ret.append(" ");
        }
        if (this.ignoreDAM > 0) {
            ret.append("Ignores Monster Damage: ");
            ret.append((int)this.ignoreDAM);
            ret.append(" ");
        }
        if (this.ignoreDAMr > 0) {
            ret.append("Ignores Monster Damage %: ");
            ret.append((int)this.ignoreDAMr);
            ret.append(" ");
        }
        if (this.incMHP > 0) {
            ret.append("Gives HP: ");
            ret.append((int)this.incMHP);
            ret.append(" ");
        }
        if (this.incMMP > 0) {
            ret.append("Gives MP: ");
            ret.append((int)this.incMMP);
            ret.append(" ");
        }
        if (this.incMHPr > 0) {
            ret.append("Gives HP %: ");
            ret.append((int)this.incMHPr);
            ret.append(" ");
        }
        if (this.incMMPr > 0) {
            ret.append("Gives MP %: ");
            ret.append((int)this.incMMPr);
            ret.append(" ");
        }
        if (this.incSTR > 0) {
            ret.append("Gives STR: ");
            ret.append((int)this.incSTR);
            ret.append(" ");
        }
        if (this.incDEX > 0) {
            ret.append("Gives DEX: ");
            ret.append((int)this.incDEX);
            ret.append(" ");
        }
        if (this.incINT > 0) {
            ret.append("Gives INT: ");
            ret.append((int)this.incINT);
            ret.append(" ");
        }
        if (this.incLUK > 0) {
            ret.append("Gives LUK: ");
            ret.append((int)this.incLUK);
            ret.append(" ");
        }
        if (this.incACC > 0) {
            ret.append("Gives ACC: ");
            ret.append((int)this.incACC);
            ret.append(" ");
        }
        if (this.incEVA > 0) {
            ret.append("Gives EVA: ");
            ret.append((int)this.incEVA);
            ret.append(" ");
        }
        if (this.incSpeed > 0) {
            ret.append("Gives Speed: ");
            ret.append((int)this.incSpeed);
            ret.append(" ");
        }
        if (this.incJump > 0) {
            ret.append("Gives Jump: ");
            ret.append((int)this.incJump);
            ret.append(" ");
        }
        if (this.incPAD > 0) {
            ret.append("Gives Attack: ");
            ret.append((int)this.incPAD);
            ret.append(" ");
        }
        if (this.incMAD > 0) {
            ret.append("Gives Magic Attack: ");
            ret.append((int)this.incMAD);
            ret.append(" ");
        }
        if (this.incPDD > 0) {
            ret.append("Gives Defense: ");
            ret.append((int)this.incPDD);
            ret.append(" ");
        }
        if (this.incMDD > 0) {
            ret.append("Gives Magic Defense: ");
            ret.append((int)this.incMDD);
            ret.append(" ");
        }
        if (this.incSTRr > 0) {
            ret.append("Gives STR %: ");
            ret.append((int)this.incSTRr);
            ret.append(" ");
        }
        if (this.incDEXr > 0) {
            ret.append("Gives DEX %: ");
            ret.append((int)this.incDEXr);
            ret.append(" ");
        }
        if (this.incINTr > 0) {
            ret.append("Gives INT %: ");
            ret.append((int)this.incINTr);
            ret.append(" ");
        }
        if (this.incLUKr > 0) {
            ret.append("Gives LUK %: ");
            ret.append((int)this.incLUKr);
            ret.append(" ");
        }
        if (this.incACCr > 0) {
            ret.append("Gives ACC %: ");
            ret.append((int)this.incACCr);
            ret.append(" ");
        }
        if (this.incEVAr > 0) {
            ret.append("Gives EVA %: ");
            ret.append((int)this.incEVAr);
            ret.append(" ");
        }
        if (this.incPADr > 0) {
            ret.append("Gives Attack %: ");
            ret.append((int)this.incPADr);
            ret.append(" ");
        }
        if (this.incMADr > 0) {
            ret.append("Gives Magic Attack %: ");
            ret.append((int)this.incMADr);
            ret.append(" ");
        }
        if (this.incPDDr > 0) {
            ret.append("Gives Defense %: ");
            ret.append((int)this.incPDDr);
            ret.append(" ");
        }
        if (this.incMDDr > 0) {
            ret.append("Gives Magic Defense %: ");
            ret.append((int)this.incMDDr);
            ret.append(" ");
        }
        if (this.incCr > 0) {
            ret.append("Gives Critical %: ");
            ret.append((int)this.incCr);
            ret.append(" ");
        }
        if (this.incDAMr > 0) {
            ret.append("Gives Total Damage %: ");
            ret.append((int)this.incDAMr);
            ret.append(" ");
        }
        if (this.level > 0) {
            ret.append("Level: ");
            ret.append((int)this.level);
            ret.append(" ");
        }
        return ret.toString();
    }
}
