package client;

public class MapleBeans
{
    private final int number;
    private final int type;
    private final int pos;
    
    public MapleBeans(final int pos, final int type, final int number) {
        this.pos = pos;
        this.number = number;
        this.type = type;
    }
    
    public int getType() {
        return this.type;
    }
    
    public int getNumber() {
        return this.number;
    }
    
    public int getPos() {
        return this.pos;
    }
    
    public enum BeansType
    {
        開始打豆豆(0), 
        暫停打豆豆(1), 
        顏色求進洞(3), 
        進洞旋轉(4), 
        獎勵豆豆效果(5), 
        未知效果(6), 
        黃金狗(7), 
        獎勵豆豆效果B(8), 
        領獎npc(9);
        
        final byte type;
        
        private BeansType(final int type) {
            this.type = (byte)type;
        }
        
        public byte getType() {
            return this.type;
        }
    }
}
