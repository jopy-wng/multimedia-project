public class Room {
    private final String name;
    private final String description;
    private Enemy enemy;
    private final Companion companion;
    private final String preStory;
    private final String postStory;

    public Room(String name, String desc, Enemy enemy, Companion companion,
                String preStory, String postStory) {
        this.name = name;
        this.description = desc;
        this.enemy = enemy;
        this.companion = companion;
        this.preStory = preStory;
        this.postStory = postStory;
    }

    public void clearEnemy()        { enemy = null; }
    public String getName()         { return name; }
    public String getDescription()  { return description; }
    public Enemy getEnemy()         { return enemy; }
    public Companion getCompanion() { return companion; }
    public String getPreStory()     { return preStory; }
    public String getPostStory()    { return postStory; }
}