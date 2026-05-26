import java.util.*;

// Each companion has their own moveset and personality quips
public class Companion implements Fighter {
    public enum Id { NOBARA, TODO, MEGUMI, NANAMI, YUJI }

    private final Id id;
    private final String name;
    private int hp, maxHp, attack, defense;
    private final String technique;
    private final String recruitStory;
    private final Random rng = new Random();

    public Companion(Id id, String name, int hp, int attack, int defense,
                     String technique, String recruitStory) {
        this.id = id;
        this.name = name;
        this.hp = this.maxHp = hp;
        this.attack = attack;
        this.defense = defense;
        this.technique = technique;
        this.recruitStory = recruitStory;
    }

    // --- move names and descriptions shown in the menu ---
    public String[] getMoveNames() {
        return switch (id) {
            case NOBARA  -> new String[]{ "Straw Doll - Resonance", "Hairpin (throw nail)", "Hobgoblin (curse the ground)" };
            case TODO    -> new String[]{ "Boogie Woogie (swap positions)", "Headbutt", "Granite Fist" };
            case MEGUMI  -> new String[]{ "Divine Dogs", "Nue (lightning strike)", "Max Elephant" };
            case NANAMI  -> new String[]{ "Ratio Technique: Collapse", "Binding Vow (overtime)", "Blunt Blade" };
            case YUJI    -> new String[]{ "Divergent Fist", "Manji Kick", "Black Flash" };
        };
    }

    public String[] getMoveDescs() {
        return switch (id) {
            case NOBARA  -> new String[]{ "medium dmg, ignores defense", "low dmg + bleeding debuff", "area curse, medium dmg" };
            case TODO    -> new String[]{ "medium dmg, confuses enemy", "low dmg, stuns briefly", "high dmg, straightforward" };
            case MEGUMI  -> new String[]{ "medium dmg, persistent", "medium dmg, piercing", "high dmg, slow" };
            case NANAMI  -> new String[]{ "high dmg, targets weak point", "very high dmg, costs HP", "medium dmg, reliable" };
            case YUJI    -> new String[]{ "medium dmg", "medium dmg + knockback", "high dmg, space distortion" };
        };
    }

    // returns damage dealt
    public int useMove(int moveIndex, Enemy enemy) {
        int dmg = switch (id) {
            case NOBARA -> switch (moveIndex) {
                case 0 -> resonance(enemy);
                case 1 -> hairpin(enemy);
                default -> hobgoblin(enemy);
            };
            case TODO -> switch (moveIndex) {
                case 0 -> boogieWoogie(enemy);
                case 1 -> headbutt(enemy);
                default -> graniteFist(enemy);
            };
            case MEGUMI -> switch (moveIndex) {
                case 0 -> divineDogs(enemy);
                case 1 -> nue(enemy);
                default -> maxElephant(enemy);
            };
            case NANAMI -> switch (moveIndex) {
                case 0 -> ratioCollapse(enemy);
                case 1 -> bindingVow(enemy);
                default -> bluntBlade(enemy);
            };
            case YUJI -> switch (moveIndex) {
                case 0 -> divergentFist(enemy);
                case 1 -> manjiKick(enemy);
                default -> blackFlash(enemy);
            };
        };
        enemy.takeDamage(dmg);
        return dmg;
    }

    // flavour lines per move
    public String getMoveQuip(int moveIndex) {
        return switch (id) {
            case NOBARA -> switch (moveIndex) {
                case 0 -> "\"Straw doll technique -- resonance!\"";
                case 1 -> "She flicks a nail straight at its eye.";
                default -> "\"Stay down!\" Nobara slams her hammer into the ground.";
            };
            case TODO -> switch (moveIndex) {
                case 0 -> "\"Boogie Woogie!\" Todo claps -- positions swap instantly.";
                case 1 -> "Todo headbutts it without hesitation. No technique. Just skull.";
                default -> "\"This is what real strength looks like.\" Granite Fist.";
            };
            case MEGUMI -> switch (moveIndex) {
                case 0 -> "Two divine dogs lunge from the shadows.";
                case 1 -> "Nue drops from above -- lightning tears through.";
                default -> "Max Elephant materializes overhead and crushes down.";
            };
            case NANAMI -> switch (moveIndex) {
                case 0 -> "Nanami traces a 7:3 line. \"Collapse.\"";
                case 1 -> "\"Binding vow -- I'll work past my limit. Just this once.\"";
                default -> "A clean, heavy swing. Nanami doesn't waste movement.";
            };
            case YUJI -> switch (moveIndex) {
                case 0 -> "Yuji's fist lands and the cursed energy hits half a second late.";
                case 1 -> "Manji kick -- it goes flying sideways.";
                default -> "\"Black Flash!\" Space around his fist bends black.";
            };
        };
    }

    // --- individual move calculations ---
    private int resonance(Enemy e)    { return base(1.0, e, false); }
    private int hairpin(Enemy e)      { return base(0.6, e, true);  }  // pierces defense
    private int hobgoblin(Enemy e)    { return base(0.9, e, false); }
    private int boogieWoogie(Enemy e) { return base(0.85, e, false); }
    private int headbutt(Enemy e)     { return base(0.55, e, false); }
    private int graniteFist(Enemy e)  { return base(1.3, e, false); }
    private int divineDogs(Enemy e)   { return base(0.9, e, false); }
    private int nue(Enemy e)          { return base(1.0, e, true);  }  // pierces defense
    private int maxElephant(Enemy e)  { return base(1.4, e, false); }
    private int ratioCollapse(Enemy e){ return base(1.5, e, true);  }
    private int bindingVow(Enemy e)   {
        int dmg = base(2.0, e, true);
        takeDamage(15);  // costs own HP
        return dmg;
    }
    private int bluntBlade(Enemy e)   { return base(1.0, e, false); }
    private int divergentFist(Enemy e){ return base(0.9, e, false); }
    private int manjiKick(Enemy e)    { return base(1.0, e, false); }
    private int blackFlash(Enemy e)   { return base(2.2, e, true);  }

    private int base(double mult, Enemy e, boolean pierce) {
        int def = pierce ? 0 : e.getDefense();
        int raw = (int)(attack * mult) - def + rng.nextInt(6) - 2;
        return Math.max(1, raw);
    }

    @Override public void takeDamage(int dmg) {
        int actual = Math.max(1, dmg - defense);
        hp = Math.max(0, hp - actual);
    }

    @Override public boolean isAlive()  { return hp > 0; }
    @Override public String getName()   { return name; }
    @Override public int getHp()        { return hp; }
    @Override public int getMaxHp()     { return maxHp; }
    @Override public int getAttack()    { return attack; }
    @Override public int getDefense()   { return defense; }
    public String getTechnique()        { return technique; }
    public String getRecruitStory()     { return recruitStory; }
    public Id getId()                   { return id; }
}