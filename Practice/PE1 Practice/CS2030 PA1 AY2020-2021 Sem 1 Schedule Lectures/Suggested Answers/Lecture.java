import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Lecture extends Lesson {

    public Lecture(String moduleCode, int classId, String venueId, 
            Instructor instructor, int startTime) {
        super(moduleCode, classId, venueId, instructor, startTime);
    }

    public boolean clashWith(Lesson otherLesson) {
        int thisEndTime = this.getStartTime() + 2;

        if (otherLesson instanceof Lecture) {
            int otherLectureStartTime = otherLesson.getStartTime();
            int otherLectureEndTime = otherLectureStartTime + 2;

            if (this.getStartTime() == otherLectureStartTime) {
                // if same time, cannot same instructor
                // cannot same venue, cannot same module 
                if (this.hasSameInstructor(otherLesson)) {
                    return true;
                } else if (this.hasSameVenue(otherLesson)) {
                    return true; 
                } else if (this.hasSameModule(otherLesson)) {
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

            if (this.getStartTime() == otherTutorialStartTime) {
                if (this.hasSameInstructor(otherLesson)) {
                    return true;
                } else if (this.hasSameVenue(otherLesson)) {
                    return true;
                } else if (this.hasSameModule(otherLesson)) {
                    return true;
                } else {
                    return false; 
                }
            } else if (this.getStartTime() >=  otherTutorialEndTime) {
                return false;
            } else if (thisEndTime <= otherTutorialStartTime) {
                return false;
            } else {
                if (this.hasSameModule(otherLesson)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    @Override 
    public String toString() {
        int endTime = this.getStartTime() + 2;
        
        return String.format("%sL%d @ %s [%s] %d--%d", this.getModuleCode(), 
            this.getClassId(), this.getVenue(), this.getInstructor().getName(),
            this.getStartTime(), endTime);
    }

}
