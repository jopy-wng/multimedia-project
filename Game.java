import java.util.*;

public class Game {
    private Player player;
    private final List<Room> rooms = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);
    private int stage = 0;

    public static void main(String[] args) { new Game().start(); }

    // =========================================================================
    // START
    // =========================================================================

    public void start() {
        banner();
        intro();
        buildWorld();
        gameLoop();
    }

    private void banner() {
        say("\n" + Colors.BLUE +
            "╔══════════════════════════════════════════════════════╗\n" +
            "║      JUJUTSU KAISEN: CURSED SPIRAL                  ║\n" +
            "║      a terminal RPG                                  ║\n" +
            "╚══════════════════════════════════════════════════════╝"
            + Colors.RESET);
    }

    private void intro() {
        nl();
        tw("Shinjuku, 2 AM. Every train line is dead.", 15);
        tw("You got the call an hour ago -- a Cursed Spirit tore through the station\n" +
           "and went underground. Civilians are still trapped on the lower levels.", 15);
        tw("No senior sorcerers available. Gojo is handling something worse in Shibuya.\n" +
           "It's just you.", 15);
        nl();
        print(Colors.YELLOW + "Your name: " + Colors.RESET);
        String name = sc.nextLine().trim();
        if (name.isEmpty()) name = "Itadori";
        player = new Player(name, 130, 20, 5);
        nl();
        tw("Ijichi's voice comes through the earpiece: \"" + name + ", be careful down there.\n" +
           "We don't have a read on what grade this thing is.\"", 15);
        tw("You drop onto the platform. The air tastes like rust and something wrong.", 15);
        pause(400);
    }

    // =========================================================================
    // WORLD
    // =========================================================================

    private void buildWorld() {

        // Stage 0 -- empty, just sets the scene
        rooms.add(new Room("Shinjuku Station -- Platform B1", "",
            null, null,
            null,
            "The escalators are frozen. Emergency lighting flickers red.\n" +
            "You can feel the cursed energy coming up through the floor -- thick, angry.\n" +
            "Whatever's down here, it's been building for a while."
        ));

        // Stage 1 -- Finger Bearer + Nobara
        rooms.add(new Room("Ticket Gates -- B2",
            "The gates are shredded open. The smell of blood.",
            new Enemy("Finger Bearer", 60, 15, 3, "cracked cursed finger", new String[]{
                "It swings its bloated arm like a club.",
                "The extra joint bends the wrong way. It doesn't care.",
                "It lunges low, dragging its knuckles across the floor.",
                "A low, wet snarl. Then it moves."
            }),
            new Companion(Companion.Id.NOBARA, "Nobara Kugisaki", 75, 17, 4,
                "Straw Doll Technique",
                "nobara-recruit"),
            // pre
            "A Finger Bearer. Semi-grade 1. You've seen them in textbooks.\n" +
            "Seeing one in person, crouched on a shredded ticket machine, is different.\n" +
            "It turns its head toward you like it already knows you're there.",
            // post
            "nobara-post"
        ));

        // Stage 2 -- Mouth Curse + Todo
        rooms.add(new Room("Service Tunnel -- B3",
            "The tunnel walls are wet. Cursed energy has calcified on the pipes.",
            new Enemy("Bottomless Mouth Curse", 80, 18, 4, "hollow cursed bead", new String[]{
                "Its mouth opens -- and keeps opening.",
                "Rows of teeth spiral back further than they should go.",
                "The suction from its maw pulls at your clothes.",
                "It screams from all thirty of its mouths at once."
            }),
            new Companion(Companion.Id.TODO, "Aoi Todo", 100, 24, 7,
                "Boogie Woogie",
                "todo-recruit"),
            "The thing drops from the pipe overhead. A Mouth Curse -- whole torso\n" +
            "is a vertical jaw, teeth going back further than the dark.\n" +
            "Nobara takes a half-step back. \"That's disgusting.\"\n" +
            "You agree.",
            "todo-post"
        ));

        // Stage 3 -- Eso + Megumi
        rooms.add(new Room("Cursed Nest -- Sublevel 1",
            "The walls breathe. Something organic has grown over the concrete.",
            new Enemy("Death Painting: Eso", 100, 21, 5, "blood-etched bone shard", new String[]{
                "\"This is going to be fun.\" Rot blooms across the floor.",
                "He sings to himself while the curse spreads.",
                "Wing King -- a cursed blast tears through the air.",
                "He grins. The rot accelerates."
            }),
            new Companion(Companion.Id.MEGUMI, "Megumi Fushiguro", 85, 20, 8,
                "Ten Shadows Technique",
                "megumi-recruit"),
            "Laughter before you see him.\n" +
            "Eso drops from the ceiling with his arms out like he's taking a bow.\n" +
            "\"Sorcerers. I was starting to think no one was coming.\"\n" +
            "The rot spreads from his feet the second he lands.",
            "megumi-post"
        ));

        // Stage 4 -- Kechizu + Nanami
        rooms.add(new Room("The Veil Corridor -- B4",
            "Space folds here. Two doors lead to the same room. You've walked past\n" +
            "this junction three times.",
            new Enemy("Death Painting: Kechizu", 95, 23, 4, "cursed blood vial", new String[]{
                "He vomits corrosive blood, doesn't even aim.",
                "\"Brother Eso...\" He whispers it and charges.",
                "The rot spreads faster than it should.",
                "Kechizu laughs and the walls start dripping."
            }),
            new Companion(Companion.Id.NANAMI, "Kento Nanami", 105, 21, 10,
                "Ratio Technique",
                "nanami-recruit"),
            "You find Kechizu sitting cross-legged in the middle of the corridor.\n" +
            "He looks up when you round the corner.\n" +
            "\"You killed Eso.\" Not a question. He stands up slowly.\n" +
            "\"Okay.\"",
            "nanami-post"
        ));

        // Stage 5 -- Grasshopper Fragment + Yuji
        rooms.add(new Room("The Hollow Core -- Center Spiral",
            "The Spiral's heart. The walls are made of something that used to be people.",
            new Enemy("Grasshopper Curse -- Grade S Fragment", 125, 26, 6,
                "fractured cursed tool", new String[]{
                    "It moves before you see it move.",
                    "Hypersonic kick. The air cracking is the only warning.",
                    "It folds space -- you're ten meters back.",
                    "Four legs on the ceiling. Then it drops."
            }),
            new Companion(Companion.Id.YUJI, "Yuji Itadori", 120, 26, 6,
                "Divergent Fist + Black Flash",
                "yuji-recruit"),
            "The Fragment doesn't wait. You barely hear it before it's already moving.\n" +
            "Fast. Faster than the others. Nanami clicks his tongue.\n" +
            "\"Special grade remnant. Stay sharp.\"",
            "yuji-post"
        ));

        // Stage 6 -- MAHITO
        Enemy mahito = new Enemy(
            "Mahito -- Idle Transfiguration: True Form", 300, 30, 10,
            "Mahito's soul fragment",
            new String[]{
                "\"Does it hurt? Souls always look so pretty when they're scared.\"",
                "He reaches for your face with one hand, still smiling.",
                "\"You can't hit what isn't solid.\" He reshapes mid-swing.",
                "The ground warps. He's walking on transfigured souls.",
                "\"I'm not trying to kill you. I just want to see what's inside.\"",
                "He splits into three copies. Two are decoys. Probably."
            });
        mahito.setIsBoss(true);
        rooms.add(new Room("Self-Embodiment of Perfection",
            "His domain. The real world is gone.\n" +
            "Transfigured bodies line the walls -- faces frozen mid-scream.\n" +
            "Mahito stands in the middle of it, hands in his pockets.",
            mahito, null,
            "mahito-pre",
            "mahito-post"
        ));
    }

    // =========================================================================
    // GAME LOOP
    // =========================================================================

    private void gameLoop() {
        while (stage < rooms.size()) {
            Room room = rooms.get(stage);
            enterRoom(room);
            if (!player.isAlive()) { gameOver(); return; }
            boolean last = stage == rooms.size() - 1;
            if (last && room.getEnemy() == null) { victory(); return; }
            if (!last) advance();
        }
        victory();
    }

    private void enterRoom(Room room) {
        divider(room.getName());

        // pre-story
        String pre = room.getPreStory();
        if (pre != null) {
            tw(expandStory(pre), 13);
            prompt("[enter]");
        }

        if (!room.getDescription().isEmpty()) {
            nl();
            say(Colors.WHITE + room.getDescription() + Colors.RESET);
        }

        if (room.getEnemy() != null) {
            pause(400);
            combat(room.getEnemy(), room);
        }

        if (player.isAlive()) {
            String post = room.getPostStory();
            if (post != null) { pause(500); tw(expandStory(post), 13); }
            if (room.getCompanion() != null) recruit(room.getCompanion());
        }
    }

    // =========================================================================
    // COMBAT
    // =========================================================================

    private void combat(Enemy enemy, Room room) {
        nl();
        say(Colors.RED + Colors.BOLD + "  -- " + enemy.getName().toUpperCase() + " --" + Colors.RESET);
        pause(500);

        while (enemy.isAlive() && player.isAlive()) {
            printStatus(enemy);
            playerTurn(enemy);
            if (!enemy.isAlive()) break;
            if (!player.isAlive()) break;
            companionTurns(enemy);
            if (!enemy.isAlive()) break;
            enemyTurn(enemy);
        }

        if (!player.isAlive()) return;

        nl();
        say(Colors.GREEN + "  " + enemy.getName() + " -- exorcised." + Colors.RESET);
        if (!enemy.getDrop().isEmpty())
            say(Colors.YELLOW + "  dropped: " + enemy.getDrop() + Colors.RESET);
        room.clearEnemy();
        pause(600);
    }

    // --- status bar ---
    private void printStatus(Enemy enemy) {
        nl();
        say(Colors.BLUE + "  -----------------------------------------------" + Colors.RESET);
        say(Colors.GREEN + "  YOU   " + bar(player.getHp(), player.getMaxHp())
            + "  " + player.getHp() + "/" + player.getMaxHp()
            + "  [reversals: " + player.getReversals() + "]" + Colors.RESET);
        for (Companion c : player.getCompanions()) {
            String col = c.isAlive() ? Colors.CYAN : Colors.RED;
            String status = c.isAlive() ? bar(c.getHp(), c.getMaxHp()) + "  " + c.getHp() + "/" + c.getMaxHp()
                                        : "DOWN";
            say(col + "  " + padR(c.getName(), 14) + status + Colors.RESET);
        }
        say(Colors.RED + "  " + padR(enemy.getName(), 14) + bar(enemy.getHp(), enemy.getMaxHp())
            + "  " + enemy.getHp() + "/" + enemy.getMaxHp() + Colors.RESET);
        say(Colors.BLUE + "  -----------------------------------------------" + Colors.RESET);
    }

    // --- player turn ---
    private void playerTurn(Enemy enemy) {
        nl();
        say(Colors.YELLOW + "  YOUR TURN" + Colors.RESET);
        say("  [1] Divergent Fist     standard hit");
        say("  [2] Black Flash        2.5x dmg, 20% chance to partially miss");
        say("  [3] Barrier            halve incoming damage this turn");
        say("  [4] Reverse Cursed     restore 40 CE  (" + player.getReversals() + " left)");
        print("  > ");
        switch (readInt(1, 4)) {
            case 1 -> {
                int d = player.divergentFist(enemy);
                say(Colors.GREEN + "  Divergent Fist -- " + d + " dmg" + Colors.RESET);
            }
            case 2 -> {
                int d = player.blackFlash(enemy);
                if (d < 20)
                    say(Colors.YELLOW + "  Black Flash -- missed the timing. " + d + " dmg" + Colors.RESET);
                else
                    say(Colors.YELLOW + Colors.BOLD + "  BLACK FLASH -- " + d + " dmg" + Colors.RESET);
            }
            case 3 -> {
                player.defend();
                say(Colors.CYAN + "  Barrier up." + Colors.RESET);
            }
            case 4 -> {
                if (player.useReversal())
                    say(Colors.BLUE + "  Reverse cursed technique -- wounds close." + Colors.RESET);
                else
                    say(Colors.RED + "  Nothing left to reverse with." + Colors.RESET);
            }
        }
    }

    // --- companion turns ---
    private void companionTurns(Enemy enemy) {
        for (Companion c : player.getCompanions()) {
            if (!c.isAlive()) { say(Colors.RED + "  " + c.getName() + " is down." + Colors.RESET); continue; }
            if (!enemy.isAlive()) break;
            nl();
            say(Colors.CYAN + "  " + c.getName().toUpperCase() + "'S TURN" + Colors.RESET);
            String[] names = c.getMoveNames();
            String[] descs = c.getMoveDescs();
            for (int i = 0; i < names.length; i++)
                say("  [" + (i+1) + "] " + padR(names[i], 28) + descs[i]);
            say("  [" + (names.length+1) + "] Hold back this turn");
            print("  > ");
            int choice = readInt(1, names.length + 1);
            if (choice == names.length + 1) {
                say(Colors.WHITE + "  " + c.getName() + " holds back." + Colors.RESET);
            } else {
                int moveIdx = choice - 1;
                int d = c.useMove(moveIdx, enemy);
                say(Colors.CYAN + "  " + c.getMoveQuip(moveIdx) + Colors.RESET);
                say(Colors.CYAN + "  " + d + " dmg" + Colors.RESET);
            }
        }
    }

    // --- enemy turn ---
    private void enemyTurn(Enemy enemy) {
        if (!enemy.isAlive()) return;
        nl();
        say(Colors.RED + "  " + enemy.nextTaunt() + Colors.RESET);

        // Boss targets a random alive fighter
        Fighter target = pickTarget();
        int dmg = enemy.attackTarget(target);
        String tname = target == player ? "you" : target.getName();
        say(Colors.RED + "  hits " + tname + " for " + dmg + Colors.RESET);
        pause(300);
    }

    private Fighter pickTarget() {
        List<Fighter> alive = new ArrayList<>();
        alive.add(player);
        for (Companion c : player.getCompanions()) if (c.isAlive()) alive.add(c);
        return alive.get(new Random().nextInt(alive.size()));
    }

    // =========================================================================
    // RECRUIT
    // =========================================================================

    private void recruit(Companion c) {
        nl();
        say(Colors.CYAN + Colors.BOLD + "  " + c.getName() + " joins." + Colors.RESET);
        say(Colors.CYAN + "  technique: " + c.getTechnique() + Colors.RESET);
        player.addCompanion(c);
        pause(600);
    }

    // =========================================================================
    // STORY STRINGS  (kept here so they're easy to edit)
    // =========================================================================

    private String expandStory(String key) {
        return switch (key) {

            case "nobara-post" ->
                "The Finger Bearer dissolves.\n" +
                "You hear footsteps behind you -- not cursed, just boots on tile.\n" +
                "Nobara steps out from behind a wrecked kiosk, hammer on her shoulder.\n" +
                "\"About time. I've been waiting for backup for twenty minutes.\"\n" +
                "\"I didn't know you were down here.\"\n" +
                "She shrugs. \"Now you do. Where are we going?\"";

            case "nobara-recruit" ->
                "Nobara shoulders her hammer.\n" +
                "\"I'm not joining because I need help. I just want to hit things in the same direction.\"\n" +
                "Close enough.";

            case "todo-post" ->
                "The Mouth Curse collapses.\n" +
                "The echo fades. Then, from further down the tunnel -- clapping. Slow.\n" +
                "A very large person steps into the light.\n" +
                "\"Not bad. You have good instincts.\" Aoi Todo. Third-year from Kyoto.\n" +
                "\"...what are you doing here?\"\n" +
                "\"Same as you. Now.\" He cracks his knuckles.\n" +
                "\"Tell me something -- what kind of women do you like?\"\n" +
                "Nobara stares at him. \"We're underground being stalked by cursed spirits.\"\n" +
                "\"Exactly,\" Todo says. \"What better time.\"";

            case "todo-recruit" ->
                "\"You're my best friend now,\" Todo announces.\n" +
                "No one asked. He falls in at the front of the group anyway.";

            case "megumi-post" ->
                "Eso's body cracks apart. The rot fades off the walls.\n" +
                "The nest goes quiet.\n" +
                "Megumi drops from a maintenance shaft in the ceiling, landing without sound.\n" +
                "\"I cleared the outer tunnels. There are two more Death Paintings deeper in.\"\n" +
                "\"How long have you been down here?\"\n" +
                "\"Long enough.\" He doesn't look like he wants to elaborate.\n" +
                "Todo slaps him on the back hard enough to make him stumble.\n" +
                "\"Fushiguro! I knew you'd turn up.\"";

            case "megumi-recruit" ->
                "Megumi's shadows fold back into his hands.\n" +
                "\"Let's finish this.\" He doesn't wait for an answer.";

            case "nanami-post" ->
                "Kechizu hits the floor.\n" +
                "You're catching your breath when you hear footsteps -- measured, unhurried.\n" +
                "Nanami comes around the corner adjusting his tie.\n" +
                "\"I see you've been busy.\" He eyes the smeared rot on the walls.\n" +
                "\"You're working overtime,\" you say.\n" +
                "\"Yes.\" He sounds resigned to it. \"I always am, when Gojo is occupied.\"\n" +
                "He checks his watch.\n" +
                "\"One more. Then the source. Let's not waste time.\"";

            case "nanami-recruit" ->
                "Nanami straightens his collar.\n" +
                "\"I'll be billing Jujutsu High for this entire evening.\" He falls into step.";

            case "yuji-post" ->
                "The Fragment shatters. Its legs fall separately.\n" +
                "For a second everything is just dust settling.\n" +
                "Then someone drops in through a hole in the ceiling you didn't notice.\n" +
                "Yuji Itadori. Grinning. Bleeding from somewhere on his left side, doesn't care.\n" +
                "\"Hey! You made it this far.\"\n" +
                "\"Where did you come from?\"\n" +
                "\"East wing. It's cleared.\" He rolls his shoulder.\n" +
                "\"Mahito's at the bottom. I could feel him from three levels up.\"\n" +
                "Nobody says anything for a second.\n" +
                "\"Let's go get him,\" Yuji says.";

            case "yuji-recruit" ->
                "Yuji looks at the group -- Nobara, Todo, Megumi, Nanami -- and nods like this is exactly right.\n" +
                "\"Okay. Together.\"";

            case "mahito-pre" ->
                "The domain opens before you finish the last step.\n\n" +
                "One second you're in a tunnel. The next you're somewhere that doesn't exist.\n" +
                "Walls made of fused bodies. The ceiling is too high. There's no floor -- just a surface\n" +
                "that feels like walking on something that was once alive.\n\n" +
                "Mahito is standing in the middle of it with his hands in his pockets.\n" +
                "He looks at your group and his face does something that isn't quite a smile.\n\n" +
                "\"Five of you. That's almost flattering.\"\n\n" +
                "Yuji steps forward. \"Mahito.\"\n" +
                "\"Yuji.\" Mahito tilts his head. \"You brought friends. Good.\n" +
                " I've been wondering what your soul looks like when you're afraid.\"\n\n" +
                "\"DOMAIN EXPANSION.\"\n\n" +
                "Self-Embodiment of Perfection slams shut around you.";

            case "mahito-post" ->
                "Mahito's form collapses.\n" +
                "Not dramatically. It just -- stops holding together.\n" +
                "The transfigurations unravel. The domain goes dark, then silent, then gone.\n\n" +
                "You're standing in a tunnel again. Concrete. Pipes. Normal.\n" +
                "The cursed energy is fading. Slowly, like a fever breaking.\n\n" +
                "Yuji is staring at where Mahito was.\n" +
                "Nobody says anything for a while.\n\n" +
                "Ijichi's voice in your ear: \"I'm reading the Spiral collapsing. Is it done?\"\n" +
                "You look at what's left. \"Yeah.\"\n" +
                "\"...okay. Good. Come up when you can.\"";

            default -> key;  // raw string passthrough
        };
    }

    // =========================================================================
    // END STATES
    // =========================================================================

    private void advance() {
        nl();
        say(Colors.WHITE + "  party: " + player.getPartyString() + Colors.RESET);
        say(Colors.WHITE + "  CE: " + player.getHp() + "/" + player.getMaxHp()
            + "  reversals: " + player.getReversals() + Colors.RESET);
        prompt("[enter to keep moving]");
        stage++;
    }

    private void gameOver() {
        nl();
        say(Colors.RED + Colors.BOLD +
            "╔══════════════════════════════════════════╗\n" +
            "║           SORCERER DOWN                  ║\n" +
            "╚══════════════════════════════════════════╝" + Colors.RESET);
        say(Colors.RED + "  " + player.getName() + " didn't make it out." + Colors.RESET);
        say(Colors.WHITE + "\n  \"A sorcerer's death is never in vain.\"" + Colors.RESET);
    }

    private void victory() {
        nl();
        say(Colors.YELLOW + Colors.BOLD +
            "╔══════════════════════════════════════════╗\n" +
            "║           CURSED SPIRAL -- CLEARED       ║\n" +
            "╚══════════════════════════════════════════╝" + Colors.RESET);
        tw(expandStory("mahito-post"), 13);
        nl();
        say(Colors.CYAN + "  sorcerers who made it:" + Colors.RESET);
        for (Companion c : player.getCompanions())
            say(Colors.CYAN + "    " + c.getName() + (c.isAlive() ? "" : " (fell in combat)") + Colors.RESET);
        nl();
    }

    // =========================================================================
    // HELPERS
    // =========================================================================

    private String bar(int cur, int max) {
        int f = (int) Math.round((double) cur / max * 12);
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < 12; i++) sb.append(i < f ? "█" : "░");
        return sb + "]";
    }

    private String padR(String s, int w) {
        if (s.length() >= w) return s.substring(0, w);
        return s + " ".repeat(w - s.length());
    }

    private void divider(String title) {
        nl();
        say(Colors.BLUE + "  -- " + title + Colors.RESET);
    }

    private void tw(String text, int ms) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            System.out.flush();
            try {
                if (c == '.' || c == '!' || c == '?') Thread.sleep(ms * 5);
                else if (c == ',') Thread.sleep(ms * 2);
                else if (c == '\n') Thread.sleep(ms * 3);
                else Thread.sleep(ms);
            } catch (InterruptedException ignored) {}
        }
        System.out.println();
    }

    private void say(String s)   { System.out.println(s); }
    private void print(String s) { System.out.print(s); System.out.flush(); }
    private void nl()            { System.out.println(); }
    private void prompt(String s){ print(Colors.BLUE + "  " + s + " " + Colors.RESET); sc.nextLine(); }

    private int readInt(int min, int max) {
        while (true) {
            try {
                int v = Integer.parseInt(sc.nextLine().trim());
                if (v >= min && v <= max) return v;
            } catch (NumberFormatException ignored) {}
            print("  " + min + "-" + max + ": ");
        }
    }

    private void pause(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }
}