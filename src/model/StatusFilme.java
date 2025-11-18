package model;

public class StatusFilme {
    public Enum status {
        ASSISTIDO,
        PARA_ASSISTIR;
    }

    public Enum getStatus() {
        return status;
    }

    public void setStatus(Enum status) {
        this.status = status;
    }
}

