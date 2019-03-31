package ninja.wsc.sportscountdown.models;

/**
 * This class represents an exercise for sport exercises.
 * It should be used for exercises.
 */
public class Exercise {

    public Exercise(String title, Integer exerciseDuration, Integer pauseDuration) {
        this.title = title;
        this.exerciseDuration = exerciseDuration;
        this.pauseDuration = pauseDuration;
    }

    /**
     * Name of this exercise
     */
    private String title;

    /**
     * Duration of this exercise
     */
    private Integer exerciseDuration;

    /**
     * Pause duration of this exercise
     */
    private Integer pauseDuration;

    // getters & setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getExerciseDuration() {
        return exerciseDuration;
    }

    public void setExerciseDuration(Integer exerciseDuration) {
        this.exerciseDuration = exerciseDuration;
    }

    public Integer getPauseDuration() {
        return pauseDuration;
    }

    public void setPauseDuration(Integer pauseDuration) {
        this.pauseDuration = pauseDuration;
    }
}
