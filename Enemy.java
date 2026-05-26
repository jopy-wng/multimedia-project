import java.util.Random;

public class Enemy {
    private String name;
    private int hp;
    private final int maxHp;
    private int attack;
    private int defense;
    private String drop;
    private boolean isBoss;
    private String[] taunts;
    private int tauntIndex = 0;
    private final Random rng = new Random();

    public Enemy(String name, int hp, int attack, int defense, String drop, String[] taunts) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attack = attack;
        this.defense = defense;
        this.drop = drop;
        this.taunts = taunts;
    }

    public int attackTarget(Fighter target) {
        int dmg = Math.max(1, attack + rng.nextInt(7) - 3);
        if (isBoss && rng.nextDouble() < 0.3) {
            dmg = (int)(dmg * 1.6);
            System.out.println(Colors.RED + Colors.BOLD + "  !! ENRAGED STRIKE !!" + Colors.RESET);
        }
        target.takeDamage(dmg);
        return dmg;
    }

    public void takeDamage(int dmg) { hp = Math.max(0, hp - dmg); }

    public String nextTaunt() {
        String t = taunts[tauntIndex % taunts.length];
        tauntIndex++;
        return t;
    }

    public boolean isAlive()     { return hp > 0; }
    public String getName()      { return name; }
    public int getHp()           { return hp; }
    public int getMaxHp()        { return maxHp; }
    public int getAttack()       { return attack; }
    public int getDefense()      { return defense; }
    public String getDrop()      { return drop; }
    public void setIsBoss(boolean b) { isBoss = b; }
}