package cz.tomasdvorak.gameoflife.strategy;


// Rules taken from http://conwaylife.com/wiki/Cellular_automaton
public enum Strategy {
    Gnarl("1/1", "A simple exploding rule that forms complex patterns from even a single live cell."),
    Replicator("1357/1357", "A rule in which every pattern is a replicator."),
    Fredkin("02468/1357", "A rule in which, like Replicator, every pattern is a replicator."),
    Seeds("/2", "An exploding rule in which every cell dies in every generation. It has many simple orthogonal spaceships, though it is in general difficult to create patterns that don't explode."),
    Live_Free_or_Die("0/2", "An exploding rule in which only cells with no neighbors survive. It has many spaceships, puffers, and oscillators, some of infinitely extensible size and period."),
    Serviettes("/234", "An exploding rule in which every cell dies every generation (like seeds). This rule is of interest because of the fabric-like beauty of the patterns that it produces."),
    DotLife("023/3", "An exploding rule closely related to Conway's Life. The B-heptomino is a common infinite growth pattern in this rule, though it can be stabilized into a spaceship."),
    Life_without_death("012345678/3", "An expanding rule that produces complex flakes. It also has important ladder patterns."),
    Mazectric("1234/3", "An expanding rule that crystalizes to form maze-like designs that tend to be straighter (ie. have longer \"halls\") than the standard maze rule."),
    Maze("12345/3", "An expanding rule that crystalizes to form maze-like designs."),
    Conways_Life("23/3", "A chaotic rule that is by far the most well-known and well-studied. It exhibits highly complex behavior."),
    Coral("45678/3", "An exploding rule in which patterns grow slowly and form coral-like textures."),
    _34_Life("34/34", "An exploding rule that was initially thought to be a stable alternative to Conway's Life, until computer simulation found that most patterns tend to explode. It has many small oscillators and simple period 3 orthogonal and diagonal spaceships."),
    Assimilation("4567/345", "A very stable rule that forms permanent diamond-shaped patterns with partially filled interiors."),
    Long_Life("5/345", "A stable rule that gets its name from the fact that it has many simple extremely high period oscillators."),
    Diamoeba("5678/35678", "A chaotic pattern that forms large diamonds with chaotically oscillating boundaries. Known to have quadratically-growing patterns."),
    Amoeba("1358/357", "A chaotic rule that is well balanced between life and death; it forms patterns with chaotic interiors and wildly moving boundaries."),
    Pseudo_Life("238/357", "A chaotic rule with evolution that resembles Conway's Life, but few patterns from Life work in this rule because the glider is unstable."),
    _2x2("125/36", "A chaotic rule with many simple still lifes, oscillators and spaceships. Its name comes from the fact that it sends patterns made up of 2x2 blocks to patterns made up of 2x2 blocks."),
    HighLife("23/36", "A chaotic rule very similar to Conway's Life that is of interest because it has a simple replicator."),
    Move("245/368", "A rule in which random patterns tend to stabilize extremely quickly. Has a very common slow-moving spaceship and slow-moving puffer."),
    Stains("235678/3678", "A stable rule in which most patterns tend to \"fill in\" bounded regions. Most nearby rules (such as coagulations) tend to explode."),
    Day_and_Night("34678/3678", "A stable rule that is symmetric under on-off reversal. Many patterns exhibiting highly complex behavior have been found for it."),
    DryLife("23/37", "An exploding rule closely related to Conway's Life, named after the fact that the standard spaceships bigger than the glider do not function in the rule. Has a small puffer based on the R-pentomino, which resembles the switch engine in the possibility of combining several to form a spaceship."),
    Coagulations("235678/378", "An exploding rule in which patterns tend to expand forever, producing a thick \"goo\" as it does so."),
    Walled_cities("2345/45678", "A stable rule that forms centers of pseudo-random activity separated by walls."),
    Vote_4_of_5("35678/4678", "A modification of the standard Gérard Vichniac voting rule, also known as \"Anneal\", used as a model for majority voting."),
    Vote("45678/5678", "Standard Gérard Vichniac voting rule, also known as \"Majority\", used as a model for majority voting.");

    private final GameStrategy strategy;
    private final String description;
    private final String rule;

    private Strategy(final String rule, final String description) {
        this.rule = rule;
        this.strategy = new GenericStrategy(rule);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public GameStrategy getImplementation() {
        return strategy;
    }


    @Override
    public String toString() {
        return name().replace("_", " ").trim() + " (" + rule + ")";
    }
}
