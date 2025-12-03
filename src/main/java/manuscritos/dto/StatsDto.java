package manuscritos.dto;

public class StatsDto {
    private long count_clue_found;
    private long count_no_clue;
    private double ratio;

    public StatsDto(long found, long notFound, double ratio) {
        this.count_clue_found = found;
        this.count_no_clue = notFound;
        this.ratio = ratio;
    }

    public long getCount_clue_found() { return count_clue_found; }
    public long getCount_no_clue() { return count_no_clue; }
    public double getRatio() { return ratio; }

}
