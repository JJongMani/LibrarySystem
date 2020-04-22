package inProject_jjm;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="LibrarySystem_table")
public class LibrarySystem {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long studentId;
    private Long totalCount;

    @PostPersist
    public void onPostPersist(){
        TotalCountChanged totalCountChanged = new TotalCountChanged();
        BeanUtils.copyProperties(this, totalCountChanged);
        totalCountChanged.publish();


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }




}
