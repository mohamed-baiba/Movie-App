package ma.ac.ensias.model.entity;

import ma.ac.ensias.model.entity.enums.Status;

public abstract class BaseEntity implements Identified<Integer>{

    private Integer id;
    private Status status;

    public BaseEntity(){}

    public BaseEntity(int id){
        this.id = id;
    }


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
