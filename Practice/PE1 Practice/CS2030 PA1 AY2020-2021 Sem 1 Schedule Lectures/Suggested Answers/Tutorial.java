public class Tutorial extends Lesson {

    private final int duration = 1;

    public Tutorial(String moduleCode, int classId, String venueId, 
        Instructor instructor, int startTime) {

        super(moduleCode, classId, venueId, instructor, startTime);
    }

    public boolean clashWith(Lesson otherLesson) {
        int thisEndTime = this.getStartTime() + 1;

        if (otherLesson instanceof Lecture) {
            int otherLectureStartTime = otherLesson.getStartTime();
            int otherLectureEndTime = otherLectureStartTime + 2;

            if (this.getStartTime() == otherLectureStartTime) {
                if (this.hasSameVenue(otherLesson)) {
                    return true;
                } else if (this.hasSameModule(otherLesson)) {
                    return true;
                } else if (this.hasSameInstructor(otherLesson)) {
                    return true;
                } else {
                    return false;
                }
            } else if (thisEndTime <= otherLectureStartTime) {
                return false;
            } else if (this.getStartTime() >= otherLectureEndTime) {
                return false;
            } else {
                if (this.hasSameModule(otherLesson)) {
                    return true;
                } else {
                    return false;
                }
            }

        } else {
            int otherTutorialStartTime = otherLesson.getStartTime();
            int otherTutorialEndTime = otherTutorialStartTime +1;
            if (this.getVenue() == otherLesson.getVenue()) {
                if (this.getStartTime() == otherTutorialStartTime) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (this.getInstructor().equals(otherLesson.getInstructor())) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    @Override 
    public String toString() {
        int endTime = this.getStartTime() + 1;

        return String.format("%s T%d @ %s [%s] %d--%d", this.getModuleCode(), this.getClassId(),
            this.getVenue(), this.getInstructor().getName(), this.getStartTime(), endTime);
    }

}


