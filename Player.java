import java.util.*;

public class Player implements Fighter {
    private String name;
    private int hp, maxHp, attack, defense;
    private int reversals = 3;
    private boolean defending = false;
    private List<Companion> companions = new ArrayList<>();
    private final Random rng = new Random();

    public Player(String name, int hp, int attack, int defense) {
        this.name = name;
        this.hp = this.maxHp = hp;
        this.attack = attack;
        this.defense = defense;
    }

    // --- actions ---
    public int divergentFist(Enemy e) {
        int dmg = Math.max(1, attack - e.getDefense() + rng.nextInt(7) - 3);
        e.takeDamage(dmg);
        defending = false;
        return dmg;
    }

    public int blackFlash(Enemy e) {
        // 2.5x, but 20% chance to partially miss and do 0.8x instead
        double mult = rng.nextDouble() < 0.2 ? 0.8 : 2.5;
        int dmg = Math.max(1, (int)(attack * mult) - e.getDefense());
        e.takeDamage(dmg);
        defending = false;
        return dmg;
    }

    public boolean useReversal() {
        if (reversals <= 0) return false;
        hp = Math.min(maxHp, hp + 40);
        reversals--;
        return true;
    }

    public void defend() { defending = true; }

    @Override
    public void takeDamage(int dmg) {
        int actual = defending ? Math.max(1, dmg / 2) : Math.max(1, dmg - defense);
        hp = Math.max(0, hp - actual);
        defending = false;
    }

    public void addCompanion(Companion c) { companions.add(c); }

    public String getPartyString() {
        if (companions.isEmpty()) return name + " (solo)";
        StringBuilder sb = new StringBuilder(name);
        for (Companion c : companions) sb.append(", ").append(c.getName());
        return sb.toString();
    }

    @Override public boolean isAlive()  { return hp > 0; }
    @Override public String getName()   { return name; }
    @Override public int getHp()        { return hp; }
    @Override public int getMaxHp()     { return maxHp; }
    @Override public int getAttack()    { return attack; }
    @Override public int getDefense()   { return defense; }
    public int getReversals()           { return reversals; }
    public List<Companion> getCompanions() { return companions; }
    public void setDefending(boolean d) { defending = d; }
}