public interface Fighter {
    String getName();
    int getHp();
    int getMaxHp();
    int getAttack();
    int getDefense();
    void takeDamage(int dmg);
    boolean isAlive();
}