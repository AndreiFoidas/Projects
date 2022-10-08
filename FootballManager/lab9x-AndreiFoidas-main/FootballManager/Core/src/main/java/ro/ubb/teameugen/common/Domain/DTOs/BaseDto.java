package ro.ubb.teameugen.common.Domain.DTOs;

import java.io.Serializable;

public class BaseDto implements Serializable {
    private int id;

    public BaseDto() {}

    public BaseDto(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BaseDto{" +
                "id=" + id +
                '}';
    }
}
