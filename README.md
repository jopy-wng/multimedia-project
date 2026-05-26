# ⚔ Dungeon of the Forsaken King — Terminal RPG (Java)

A classic terminal RPG adventure with multiple rooms, recruitable companions, and a final boss fight.

## How to Run

### Option A — Run the pre-built JAR
```bash
java -jar TerminalRPG.jar
```

### Option B — Compile from source
```bash
cd src
javac *.java
java Game
```

> Requires Java 11+ (tested on Java 21).

---

## Gameplay

### 🗺 World Layout
| Room | Enemy | Companion |
|------|-------|-----------|
| Crumbling Entrance Hall | — | — |
| Guard Barracks | Goblin Scout | Sera the Rogue |
| Arcane Library | Skeleton Mage | Aldric the Mage |
| Flooded Crypt | Wailing Wraith | Brother Voss the Paladin |
| Forsaken Throne Room | **BOSS: King Maldrek** | — |

### ⚔ Combat Actions
| Action | Effect |
|--------|--------|
| **Attack** | Standard strike (ATK - enemy DEF) |
| **Heavy Strike** | 2× damage, no defense bonus |
| **Defend** | Halves incoming damage this turn |
| **Use Potion** | Restore 30 HP (3 potions at start) |

### 👥 Companions
Each companion you rescue **automatically assists** in every combat round, dealing bonus damage proportional to their attack stat.

### 👹 Boss Fight
King Maldrek has 200 HP and occasionally enters a **Forsaken Rage** for 1.5× damage — use Defend wisely!